package GUI;

import GUI.Login.LoginFrame;
import GUI.Person.PersonPanel;
import GUI.Store.GoodsPanel;
import GUI.Supplier.SupplierPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel cardsPanel;

    public MainFrame() {
        setTitle("仓库管理系统");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        GoodsPanel goodsPanel = new GoodsPanel();
        cardsPanel.add(goodsPanel, "Goods");

        PersonPanel personPanel = new PersonPanel();
        cardsPanel.add(personPanel, "Person");

        SupplierPanel supplierPanel = new SupplierPanel();
        cardsPanel.add(supplierPanel, "Supplier");

        setJMenuBar(createMenuBar());

        setContentPane(cardsPanel);

        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu tableMenu = new JMenu("Tables");
        menuBar.add(tableMenu);

        JMenuItem goodsItem = new JMenuItem("商品");
        goodsItem.addActionListener(e -> cardLayout.show(cardsPanel, "Goods"));
        tableMenu.add(goodsItem);

        JMenuItem personItem = new JMenuItem("人员");
        personItem.addActionListener(e -> cardLayout.show(cardsPanel, "Person"));
        tableMenu.add(personItem);

        JMenuItem supplierItem = new JMenuItem("供货商");
        supplierItem.addActionListener(e -> cardLayout.show(cardsPanel, "Supplier"));
        tableMenu.add(supplierItem);

        return menuBar;
    }

    public static void main(String[] args) throws SQLException {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
//        new MainFrame();
    }
}