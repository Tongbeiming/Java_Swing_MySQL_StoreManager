package GUI.Store;

import DBconnection.DbUtil;
import DBconnection.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddGoodsDialog extends JDialog {
    private AddGoodsPanel addGoodsPanel;

    public AddGoodsDialog(JFrame parent, GoodsPanel goodsPanel) {
        super(parent, "添加商品", true);
        setSize(300, 300);
        setLocationRelativeTo(parent);

        addGoodsPanel = new AddGoodsPanel();
        add(addGoodsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("添加");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number = addGoodsPanel.getNumber();
                String name = addGoodsPanel.getName();
                int num = addGoodsPanel.getNum();
                String locati = addGoodsPanel.getLocati();
                Date date = null;
                try {
                    // 获取用户输入的日期字符串
                    String dateString = String.valueOf(addGoodsPanel.getDate());
                    // 将日期字符串转换为 java.util.Date 对象
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date utilDate = format.parse(dateString);
                    // 将 java.util.Date 转换为 java.sql.Date
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                double price = addGoodsPanel.getPrice();
                boolean isDangerous = addGoodsPanel.isDangerous();

                Store store = new Store(number, name, num, locati, date, price, price * num, isDangerous);
                goodsPanel.addGoods(store);
                dispose();

                // Save data to the database
                try {
                    Connection connection = DbUtil.getConnection();
                    String query = "INSERT INTO store (number, name, num, price, date, location, sunprice, isdangerous) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, number);
                    statement.setString(2, name);
                    statement.setInt(3, num);
                    statement.setDouble(4, price);
                    // 获取用户输入的日期字符串
                    String dateString = String.valueOf(addGoodsPanel.getDate());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date utilDate = format.parse(dateString);
                    // 将 java.util.Date 转换为 java.sql.Date
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    // 将转换后的 java.sql.Date 对象传递给 PreparedStatement 的 setDate() 方法
                    statement.setDate(5, sqlDate);
                    statement.setString(6, locati);
                    statement.setDouble(7,Double.valueOf(price*num));
                    statement.setBoolean(8, isDangerous);
                    statement.executeUpdate();
                    statement.close();
                    DbUtil.closeConnection(connection);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

                dispose();
            }
        });
        buttonPanel.add(btnAdd);

        JButton btnCancel = new JButton("取消");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel.add(btnCancel);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}