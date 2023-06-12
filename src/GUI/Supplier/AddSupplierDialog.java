package GUI.Supplier;

import DBconnection.Account;
import DBconnection.TGoods;
import GUI.Person.AddPersonPanel;
import GUI.Person.PersonPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSupplierDialog extends JDialog {

    private AddSupplierPanel addSupplierPanel;
    private SupplierPanel supplierPanel;

    public AddSupplierDialog(JFrame parent, SupplierPanel supplierPanel) {
        super(parent, "Add Person", true);
        setSize(300, 300);
        setLocationRelativeTo(parent);

        this.supplierPanel = supplierPanel;
        addSupplierPanel = new AddSupplierPanel();

        add(addSupplierPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("添加商家");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String name = addSupplierPanel.getname();
                String numb = addSupplierPanel.getnumber();
                String sto = addSupplierPanel.getsto();
                int nu = addSupplierPanel.getnum();
                Double pri = addSupplierPanel.getpri();

                System.out.println("name:"+name);


                //看这里wc  这个是对的
                TGoods suply = new TGoods(name, numb, sto, nu, pri,pri*nu);
                //这里是供应商对 应该添加到供应商这张表对吧 对
                suply.addToDb();

                supplierPanel.addsupplier(suply);
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