package GUI.Store;

import DBconnection.Store;
import GUI.Store.GoodsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchGoodsDialog extends JDialog {

    private JTextField tfSearch;
    private JButton btnSearch;
    private JButton btnRefresh;
    private GoodsPanel goodsPanel;

    public SearchGoodsDialog(JFrame parentFrame, GoodsPanel goodsPanel) {
        super(parentFrame, "查询商品", true);
        this.goodsPanel = goodsPanel;

        initComponents();
        setSize(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parentFrame);
        setVisible(true);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search: "));
        tfSearch = new JTextField(20);
        searchPanel.add(tfSearch);

        add(searchPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();

        btnSearch = new JButton("查找");
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchKeyword = tfSearch.getText();

                if (!searchKeyword.isEmpty()) {
                    // Perform search based on the provided keyword
                    // Update goodsPanel with the results
                    ArrayList<Store> resultList = goodsPanel.searchGoods(searchKeyword);
                    goodsPanel.updateGoodsTable(resultList);
                } else {
                    // Display all records when search keyword is empty
                    goodsPanel.updateGoodsTable(null);
                }
            }
        });
        buttonPanel.add(btnSearch);

        btnRefresh = new JButton("取消");
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Refresh the goods panel to display all records
                dispose();
            }
        });
        buttonPanel.add(btnRefresh);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}