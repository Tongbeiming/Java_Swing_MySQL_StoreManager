package GUI.Supplier;

import DBconnection.Account;
import DBconnection.TGoods;
import GUI.Person.PersonPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditSupplierDialog extends JDialog {
    private JTextField nameField;
    private JTextField numberField;
    private JTextField stoField;
    private JTextField numField;
    private JTextField priceField;

    private TGoods supplier;
    private SupplierPanel supplierPanel;

    public EditSupplierDialog(JFrame parent, SupplierPanel supplierPanel, TGoods supplier) {
        super(parent, "Edit Person", true);
        setSize(300, 250);
        setLocationRelativeTo(parent);

        this.supplierPanel = supplierPanel;
        this.supplier = supplier;

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));

        JLabel nameLabel = new JLabel("姓名：");
        nameField = new JTextField(supplier.getName());
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JLabel numberLabel = new JLabel("手机：");
        numberField = new JTextField(supplier.getNumber());
        inputPanel.add(numberLabel);
        inputPanel.add(numberField);

        JLabel stoLabel = new JLabel("货物：");
        stoField = new JTextField(supplier.getSto());
        inputPanel.add(stoLabel);
        inputPanel.add(stoField);

        JLabel nLabel = new JLabel("数量：");
        numField = new JTextField(String.valueOf(supplier.getNum()));
        inputPanel.add(nLabel);
        inputPanel.add(numField);

        JLabel priLabel = new JLabel("单价：");
        priceField = new JTextField(String.valueOf(supplier.getPrice()));
        inputPanel.add(priLabel);
        inputPanel.add(priceField);

        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton saveButton = new JButton("保存");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                supplier.setName(nameField.getText());
                supplier.setNumber(numberField.getText());
                supplier.setSto(stoField.getText());//Integer.parseInt(ageField.getText())
                supplier.setNum(Integer.parseInt(numField.getText()));
                supplier.setPrice(Double.parseDouble(priceField.getText()));
//                supplier.setEmail(emailField.getText());
//                account.setWork(workField.getText());
                supplier.updateInDb();
                supplierPanel.setSupplierList((ArrayList<TGoods>) TGoods.getAllFromDb());
                dispose();
            }
        });
        buttonPanel.add(saveButton);

        JButton cancelButton = new JButton("取消");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}