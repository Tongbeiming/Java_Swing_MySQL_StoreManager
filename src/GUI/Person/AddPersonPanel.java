package GUI.Person;

import javax.swing.*;
import java.awt.*;

public class AddPersonPanel extends JPanel {
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField roleField;
    private JTextField phoneNumField;
    private JTextField emailField;

    public AddPersonPanel() {
        setLayout(new GridLayout(6, 2));

        JLabel idLabel = new JLabel("工号：");
        add(idLabel);
        idField = new JTextField();
        add(idField);

        JLabel nameLabel = new JLabel("姓名：");
        add(nameLabel);
        nameField = new JTextField();
        add(nameField);

        JLabel ageLabel = new JLabel("年龄：");
        add(ageLabel);
        ageField = new JTextField();
        add(ageField);

        JLabel roleLabel = new JLabel("职称：");
        add(roleLabel);
        roleField = new JTextField();
        add(roleField);

        JLabel phoneNumLabel = new JLabel("电话号码：");
        add(phoneNumLabel);
        phoneNumField = new JTextField();
        add(phoneNumField);

        JLabel emailLabel = new JLabel("邮箱地址：");
        add(emailLabel);
        emailField = new JTextField();
        add(emailField);
    }

    public String getID() {
        return idField.getText();
    }

    public String getName() {
        return nameField.getText();
    }

    public int getAge() {
        return Integer.parseInt(ageField.getText());
    }

    public String getRole() {
        return roleField.getText();
    }

    public String getPhoneNum() {
        return phoneNumField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }
}