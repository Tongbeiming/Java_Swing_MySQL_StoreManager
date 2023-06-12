package GUI.Person;

import DBconnection.Account;
import DBconnection.DbUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPersonDialog extends JDialog {

    private AddPersonPanel addPersonPanel;
    private PersonPanel personPanel;

    public AddPersonDialog(JFrame parent, PersonPanel personPanel) {
        super(parent, "Add Person", true);
        setSize(300, 300);
        setLocationRelativeTo(parent);

        this.personPanel = personPanel;
        addPersonPanel = new AddPersonPanel();

        add(addPersonPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("添加员工");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = addPersonPanel.getID();
                String name = addPersonPanel.getName();
                int age = addPersonPanel.getAge();
                String role = addPersonPanel.getRole();
                String phoneNum = addPersonPanel.getPhoneNum();
                String email = addPersonPanel.getEmail();

                Account account = new Account(id, name, age, role, phoneNum, email);
                account.addToDb();

                personPanel.addPerson(account);
                dispose();
            }
        });
        buttonPanel.add(addButton);

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