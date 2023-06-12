package GUI.Store;

import DBconnection.DbUtil;
import DBconnection.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GoodsPanel extends JPanel {
    private JTable goodsTable;
    private GoodsTableModel goodsTableModel;
    private ArrayList<Store> goodsList;

    public GoodsPanel() {
        goodsList = new ArrayList<>();
        goodsTableModel = new GoodsTableModel(goodsList);

        setLayout(new BorderLayout());
        initComponents();

        // Connect to the database and retrieve data
        try {
            Connection connection = DbUtil.getConnection();
            String query = "SELECT number, name, num, price, date, location, sunprice, isdangerous FROM store";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String number = resultSet.getString("number");
                String name = resultSet.getString("name");
                int num = resultSet.getInt("num");
                double price = resultSet.getDouble("price");
                Date date = resultSet.getDate("date");
                String locati = resultSet.getString("location");
                double sunprice = resultSet.getDouble("sunprice");
                boolean isDangerous = resultSet.getBoolean("isdangerous");

                Store store = new Store(number, name, num, locati, date, price, sunprice, isDangerous);
                goodsList.add(store);
                System.out.println("1111");
            }

            resultSet.close();
            statement.close();
            DbUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        JPanel topPanel = new JPanel();
        JButton btnAdd = new JButton("添加商品");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddGoodsDialog dialog = new AddGoodsDialog((JFrame) getRootPane().getParent(), GoodsPanel.this);
            }
        });
        topPanel.add(btnAdd);

        JButton btnDelete = new JButton("删除商品");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = goodsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int goodsId = Integer.parseInt((String) goodsTableModel.getValueAt(selectedRow, 0)); // 获取商品ID
                    String sql = "DELETE FROM store WHERE number = ?";
                    try (Connection conn = DbUtil.getConnection();
                         PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setInt(1, goodsId);
                        pstmt.executeUpdate();
                        goodsTableModel.removeGoods(selectedRow);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        topPanel.add(btnDelete);

        JButton btnUpdate = new JButton("更改商品");
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = goodsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Store goodsToEdit = goodsTableModel.getGoodsAt(selectedRow);
                    UpdateGoodsDialog dialog = new UpdateGoodsDialog((JFrame) getRootPane().getParent(), GoodsPanel.this, goodsToEdit);
                }
            }
        });
        topPanel.add(btnUpdate);

        JButton btnSearch = new JButton("查询商品");
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchGoodsDialog dialog = new SearchGoodsDialog((JFrame) getRootPane().getParent(), GoodsPanel.this);
            }
        });
        topPanel.add(btnSearch);

        JButton btnRefresh = new JButton("刷新");
        topPanel.add(btnRefresh);
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshGoodsTable();
            }
        });

        add(topPanel, BorderLayout.NORTH);

        goodsTable = new JTable(goodsTableModel);
        JScrollPane scrollPane = new JScrollPane(goodsTable);
        add(scrollPane, BorderLayout.CENTER);

    }

    void updateGoods(Store goods) {
        int selectedRow = goodsTable.getSelectedRow();
        if (selectedRow >= 0) {
            for (int columnIndex = 0; columnIndex < goodsTableModel.getColumnCount(); columnIndex++) {
                goodsTableModel.setValueAt(goods, selectedRow, columnIndex);
            }
            updateGoodsInDatabase(goods);
        }
    }

    public void addGoods(Store goods) {
        goodsTableModel.addGoods(goods);
    }


    public void updateGoodsTable(ArrayList<Store> searchResults) {
        goodsTableModel.updateData(searchResults);
    }

    public ArrayList<Store> searchGoods(String searchKeyword) {
        ArrayList<Store> storeList = new ArrayList<>();

        try {
            Connection conn = DbUtil.getConnection();
            String query = "SELECT * FROM store WHERE " +
                    "number LIKE ? OR " +
                    "name LIKE ? OR " +
                    "num LIKE ? OR " +
                    "location LIKE ? OR " +
                    "date LIKE ? OR " +
                    "price LIKE ? OR " +
                    "sunprice LIKE ? OR " +
                    "isdangerous LIKE ?;";
            PreparedStatement statement = conn.prepareStatement(query);

            for (int i = 1; i <= 8; i++) {
                statement.setString(i, "%" + searchKeyword + "%");
            }

            ResultSet results = statement.executeQuery();

            while (results.next()) {
                String id = results.getString("number");
                String name = results.getString("name");
                int shelf = results.getInt("num");
                String category = results.getString("location");
                Date description = results.getDate("date");
                double price = results.getDouble("price");
                int quantity = results.getInt("sunprice");
                boolean supplier = results.getBoolean("isdangerous");

                Store store = new Store(id, name, shelf, category, description, price, quantity, supplier);
                storeList.add(store);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return storeList;
    }

    void updateGoodsInDatabase(Store goods) {

        try {
            Connection connection = DbUtil.getConnection();
            String updateSQL = "UPDATE store SET "
                    + "number = ?, name = ?, num = ?, price = ?, date = ?, location = ?, isdangerous = ? "
                    + "WHERE number = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, goods.getNumber());
            preparedStatement.setString(2, goods.getName());
            preparedStatement.setInt(3, goods.getNum());
            preparedStatement.setDouble(4, goods.getPrice());
            preparedStatement.setDate(5, goods.getDate());
            preparedStatement.setString(6, goods.getLocati());
            preparedStatement.setDouble(7, goods.getPrice() * goods.getNum());
            preparedStatement.setBoolean(8, goods.getIsdangerous());
//            preparedStatement.setString();

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                // 异常处理，未进行任何更新
            }

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void refreshGoodsTable() {
        ArrayList<Store> goodsList = new ArrayList<>();

        try {
            Connection conn = DbUtil.getConnection();
            String query = "SELECT number, name, num, price, date, location, isdangerous FROM store";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String number = resultSet.getString("number");
                String name = resultSet.getString("name");
                int num = resultSet.getInt("num");
                double price = resultSet.getDouble("price");
                Date date = resultSet.getDate("date");
                String location = resultSet.getString("location");
                boolean isdangerous = resultSet.getBoolean("isdangerous");

                Store store = new Store(number, name, num, location, date, price, price * num, isdangerous);
                goodsList.add(store);
            }

            resultSet.close();
            statement.close();
            DbUtil.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        goodsTableModel.updateData(goodsList);
    }
}