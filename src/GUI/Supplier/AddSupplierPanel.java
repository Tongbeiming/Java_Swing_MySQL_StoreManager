package GUI.Supplier;

import javax.swing.*;
import java.awt.*;

public class AddSupplierPanel extends JPanel {
    private JTextField nameField;
    private JTextField numberField;
    private JTextField stoField;
    private JTextField numField;
    private JTextField priceField;

    public AddSupplierPanel() {
        setLayout(new GridLayout(6, 2));

        JLabel nameLabel = new JLabel("姓名：");
        add(nameLabel);
        nameField = new JTextField();
        add(nameField);



        JLabel numberLabel = new JLabel("手机：");
        add(numberLabel);
        numberField = new JTextField();
        add(numberField);

        JLabel stoLabel = new JLabel("货物：");
        add(stoLabel);
        stoField = new JTextField();
        add(stoField);

        JLabel nLabel = new JLabel("数量：");
        add(nLabel);
        numField = new JTextField();
        add(numField);

        JLabel priLabel = new JLabel("单价：");
        add(priLabel);
        priceField = new JTextField();
        add(priceField);

    }

    public String getname() {
        return nameField.getText();
    }

    public String getnumber() {
        return numberField.getText();
    }

    public String getsto() {
        return stoField.getText();
    }

    public int getnum() {
        return Integer.parseInt(numField.getText());
    }

    public double getpri() {
        return Double.parseDouble(priceField.getText());
    }

}