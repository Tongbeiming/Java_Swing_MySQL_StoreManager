package GUI.Person;

import DBconnection.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchPersonDialog extends JDialog {
    private PersonPanel personPanel;

    public SearchPersonDialog(JFrame parent, PersonPanel personPanel) {
        super(parent, "Search Person", true);
        setSize(300, 100);
        setLocationRelativeTo(parent);

        this.personPanel = personPanel;

        JPanel inputPanel = new JPanel();
        JLabel label = new JLabel("关键字：");
        inputPanel.add(label);

        JTextField textField = new JTextField(15);
        inputPanel.add(textField);

        JButton searchButton = new JButton("搜索");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = textField.getText();
                ArrayList<Account> searchResults = new PerformSearch(personPanel.getPersonList(), keyword).search();
                personPanel.setPersonList(searchResults);
                dispose();
            }
        });
        inputPanel.add(searchButton);

        add(inputPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}