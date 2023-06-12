package GUI.Supplier;

import DBconnection.Account;
import DBconnection.TGoods;
import GUI.Person.PerformSearch;
import GUI.Person.PersonPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchSupplierDialog extends JDialog {
    private SupplierPanel supplierPanel;

    public SearchSupplierDialog(JFrame parent, SupplierPanel supplierPanel) {
        super(parent, "Search Person", true);
        setSize(300, 100);
        setLocationRelativeTo(parent);

        this.supplierPanel = supplierPanel;

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
                ArrayList<TGoods> searchResults = new performSearch(supplierPanel.getSupplierList(), keyword).search();
                supplierPanel.setSupplierList(searchResults);
                dispose();
            }
        });
        inputPanel.add(searchButton);

        add(inputPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}