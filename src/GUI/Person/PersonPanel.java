package GUI.Person;

import DBconnection.Account;
import DBconnection.DbUtil;
import DBconnection.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class PersonPanel extends JPanel {
    private JTable personTable;
    private PersonTableModel personTableModel;
    private ArrayList<Account> personList;

    public PersonPanel() {
        personList = new ArrayList<>();
        personTableModel = new PersonTableModel(personList);

        setLayout(new BorderLayout());
        initComponents();


//        personList = (ArrayList<Account>) Account.getAllFromDb()

        Account.getAllFromDb().forEach(item -> {
            personList.add(item);
        });
    }

    private void initComponents() {
        JPanel topPanel = new JPanel();
        JButton btnAdd = new JButton("添加员工");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddPersonDialog(getRootFrame(), PersonPanel.this);
            }
        });
        topPanel.add(btnAdd);

        JButton btnDelete = new JButton("删除员工");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = personTable.getSelectedRows();
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    int index = selectedRows[i];
                    Account account = personList.get(index);
                    account.deleteFromDb();
                    personTableModel.removePerson(index);

//                    personList.remove(index);
                }
            }
        });
        topPanel.add(btnDelete);
//NICE
        JButton btnEdit = new JButton("编辑员工");
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = personTable.getSelectedRow();
                if (selectedRow != -1) {
                    Account account = personList.get(selectedRow);
                    new EditPersonDialog(getRootFrame(), PersonPanel.this, account);
                }
            }
        });
        topPanel.add(btnEdit);

        JButton btnSearch = new JButton("搜索员工");
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchPersonDialog(getRootFrame(), PersonPanel.this);
            }
        });
        topPanel.add(btnSearch);

        add(topPanel, BorderLayout.NORTH);

        personTable = new JTable(personTableModel);
        JScrollPane scrollPane = new JScrollPane(personTable);
        add(scrollPane, BorderLayout.CENTER);

        JButton btnRefresh = new JButton("刷新");
        topPanel.add(btnRefresh);
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshGoodsTable();
            }
        });

        add(topPanel, BorderLayout.NORTH);

        personTable = new JTable(personTableModel);
        JScrollPane scrollPan = new JScrollPane(personTable);
        add(scrollPan, BorderLayout.CENTER);
    }

    public void addPerson(Account account) {
        personList.add(account);
//        System.out.println("父层personList个数" + personList.size());
        this.setPersonList(personList);
//        personTableModel.addPerson(account);
    }

    private JFrame getRootFrame() {
        return (JFrame) SwingUtilities.getWindowAncestor(this);
    }

    public ArrayList<Account> getPersonList() {
        return personList;
    }

    public void setPersonList(ArrayList<Account> personList) {
        this.personList = personList;
        personTableModel.setPersonList(personList);
    }


    public void refreshGoodsTable() {
        ArrayList<Account> personList = new ArrayList<>();

        try {
            Connection conn = DbUtil.getConnection();
            String query = "SELECT ID, name, age, role, phonenum, email FROM personemployee";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String number = resultSet.getString("ID");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String rol = resultSet.getString("role");
                String pn = resultSet.getString("phonenum");
                String em = resultSet.getString("email");

                Account pe = new Account(number, name, age, rol, pn, em);
                personList.add(pe);
            }

            resultSet.close();
            statement.close();
            DbUtil.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        personTableModel.updateDate(personList);
    }
}