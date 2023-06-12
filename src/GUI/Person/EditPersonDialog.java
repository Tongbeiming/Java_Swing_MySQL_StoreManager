package GUI.Person;

import DBconnection.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditPersonDialog extends JDialog {
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField workField;
    private JTextField roleField;
    private JTextField phoneNumField;
    private JTextField emailField;

    private Account account;
    private PersonPanel personPanel;

    public EditPersonDialog(JFrame parent, PersonPanel personPanel, Account account) {
        super(parent, "Edit Person", true);
        setSize(300, 250);
        setLocationRelativeTo(parent);

        this.personPanel = personPanel;
        this.account = account;

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));

        JLabel idLabel = new JLabel("工号:");
        idField = new JTextField(account.getId());
        inputPanel.add(idLabel);
        inputPanel.add(idField);

        JLabel nameLabel = new JLabel("姓名:");
        nameField = new JTextField(account.getName());
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JLabel ageLabel = new JLabel("年龄:");
        ageField = new JTextField(String.valueOf(account.getAge()));
        inputPanel.add(ageLabel);
        inputPanel.add(ageField);

        JLabel roleLabel = new JLabel("职称:");
        roleField = new JTextField(account.getRole());
        inputPanel.add(roleLabel);
        inputPanel.add(roleField);

        JLabel phoneNumLabel = new JLabel("电话号码:");
        phoneNumField = new JTextField(account.getPhoneNum());
        inputPanel.add(phoneNumLabel);
        inputPanel.add(phoneNumField);

        JLabel emailLabel = new JLabel("电子邮件:");
        emailField = new JTextField(account.getEmail());
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);

        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton saveButton = new JButton("保存");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                account.setId(idField.getText());
                account.setName(nameField.getText());
                account.setAge(Integer.parseInt(ageField.getText()));
                account.setRole(roleField.getText());
                account.setPhoneNum(phoneNumField.getText());
                account.setEmail(emailField.getText());
//                account.setWork(workField.getText());
                account.updateInDb();
                personPanel.setPersonList((ArrayList<Account>) Account.getAllFromDb());
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