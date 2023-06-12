package GUI.Store;

import DBconnection.Store;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class AddGoodsPanel extends JPanel {
    private JTextField numberField;
    private JTextField nameField;
    private JTextField numField;
    private JTextField priceField;
    private JTextField dateField;
    private JTextField locatiField;
    private JCheckBox isDangerousBox;

    public AddGoodsPanel() {
        this(null);
    }

    public AddGoodsPanel(Store goodsToEdit) {
        setLayout(new GridLayout(7, 2));

        add(new JLabel("商品编号："));
        numberField = new JTextField(goodsToEdit != null ? goodsToEdit.getNumber() : "");
        add(numberField);

        add(new JLabel("商品名称："));
        nameField = new JTextField(goodsToEdit != null ? goodsToEdit.getName() : "");
        add(nameField);

        add(new JLabel("商品数量："));
        numField = new JTextField(goodsToEdit != null ? String.valueOf(goodsToEdit.getNum()) : "");
        add(numField);

        add(new JLabel("商品价格："));
        priceField = new JTextField(goodsToEdit != null ? String.valueOf(goodsToEdit.getPrice()) : "");
        add(priceField);

        add(new JLabel("生产日期："));
        dateField = new JTextField(goodsToEdit != null ? String.valueOf(goodsToEdit.getDate()) : "");
        add(dateField);

        add(new JLabel("仓库位置："));
        locatiField = new JTextField(goodsToEdit != null ? goodsToEdit.getLocati() : "");
        add(locatiField);

        add(new JLabel("是否危险品："));
        isDangerousBox = new JCheckBox();
        if (goodsToEdit != null) {
            isDangerousBox.setSelected(goodsToEdit.getIsdangerous());
        }
        add(isDangerousBox);
    }

    public String getNumber() {
        return numberField.getText();
    }

    public String getName() {
        return nameField.getText();
    }

    public int getNum() {
        return Integer.parseInt(numField.getText());
    }

    public String getLocati() {
        return locatiField.getText();
    }

    public Date getDate() {
        return Date.valueOf(dateField.getText());
    }

    public double getPrice() {
        return Double.parseDouble(priceField.getText());
    }

    public boolean isDangerous() {
        return isDangerousBox.isSelected();
    }
}