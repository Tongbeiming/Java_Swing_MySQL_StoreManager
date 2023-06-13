package GUI.Supplier;

import DBconnection.Account;
import DBconnection.DbUtil;
import DBconnection.Store;
import DBconnection.TGoods;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class SupplierPanel extends JPanel {
    private JTable supplierTable;
    private SupplierTableModel supplierTableModel;
    private ArrayList<TGoods> supplierList;

    public SupplierPanel() {
        supplierList = new ArrayList<>();
        supplierTableModel = new SupplierTableModel(supplierList);

        setLayout(new BorderLayout());
        initComponents();


//        personList = (ArrayList<Account>) Account.getAllFromDb()

        TGoods.getAllFromDb().forEach(item -> {
            supplierList.add(item);
        });
    }

    private void initComponents() {
        JPanel topPanel = new JPanel();
        JButton btnAdd = new JButton("添加商家");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //这里是添加供应商
                new AddSupplierDialog(getRootFrame(), SupplierPanel.this);
            }
        });
        topPanel.add(btnAdd);

        JButton btnDelete = new JButton("删除商家");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = supplierTable.getSelectedRows();
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    int index = selectedRows[i];
                    TGoods supply = supplierList.get(index);
                    supply.deleteFromDb();
                    supplierTableModel.removePerson(index);

//                    personList.remove(index);
                }
            }
        });
        topPanel.add(btnDelete);
//NICE
        JButton btnEdit = new JButton("编辑商家");
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = supplierTable.getSelectedRow();
                if (selectedRow != -1) {
                    TGoods supply = supplierList.get(selectedRow);
                    new EditSupplierDialog(getRootFrame(), SupplierPanel.this, supply);
                }
            }
        });
        topPanel.add(btnEdit);

        JButton btnSearch = new JButton("搜索商家");
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchSupplierDialog(getRootFrame(), SupplierPanel.this);
            }
        });
        topPanel.add(btnSearch);

        add(topPanel, BorderLayout.NORTH);

        supplierTable = new JTable(supplierTableModel);
        JScrollPane scrollPane = new JScrollPane(supplierTable);
        add(scrollPane, BorderLayout.CENTER);

        JButton btnRefresh = new JButton("刷新");
        topPanel.add(btnRefresh);
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshGoodsTable();
            }
        });

        add(topPanel, BorderLayout.NORTH);

        supplierTable = new JTable(supplierTableModel);
        JScrollPane scrollPan = new JScrollPane(supplierTable);
        add(scrollPan, BorderLayout.CENTER);
    }


    public void addsupplier(TGoods supplier) {
        supplierList.add(supplier);
        System.out.println("父层personList个数" + supplierList.size());
        this.setSupplierList(supplierList);
//        supplierTableModel.addSupplier(supplier);
    }


    private JFrame getRootFrame() {
        return (JFrame) SwingUtilities.getWindowAncestor(this);
    }

    public ArrayList<TGoods> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(ArrayList<TGoods> supplierList) {
        this.supplierList = supplierList;
        supplierTableModel.setSupplierList(supplierList);
    }


    public void refreshGoodsTable() {
        ArrayList<TGoods> supplierList = new ArrayList<>();

        try {
            Connection conn = DbUtil.getConnection();
            String query = "SELECT name, number, sto, num, price, sumprice FROM supplier";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String number = resultSet.getString("number");
                String sto = resultSet.getString("sto");
                int num = resultSet.getInt("num");
                double price = resultSet.getDouble("price");
                double sumprice = resultSet.getDouble("sumprice");

                TGoods su = new TGoods(name, number, sto, num, price, sumprice);
                supplierList.add(su);
                setSupplierList(supplierList);
            }



            resultSet.close();
            statement.close();
            DbUtil.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        supplierTableModel.updateDate(supplierList);
    }
}