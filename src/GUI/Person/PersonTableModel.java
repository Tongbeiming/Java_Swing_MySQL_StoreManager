package GUI.Person;

import DBconnection.Account;
import DBconnection.Store;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class PersonTableModel extends AbstractTableModel {
    private ArrayList<Account> accounts;
    private final String[] columnNames = {"工号", "姓名", "年龄", "职称", "电话号码", "电子邮件"};

    public PersonTableModel(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public int getRowCount() {
        return accounts.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Account account = accounts.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return account.getId();
            case 1:
                return account.getName();
            case 2:
                return account.getAge();
            case 3:
                return account.getRole();
            case 4:
                return account.getPhoneNum();
            case 5:
                return account.getEmail();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void addPerson(Account account) {
        accounts.add(account);
        fireTableDataChanged();
    }

    public void removePerson(int rowIndex) {
        System.out.println("当前的人员表个数为："+this.accounts.size());
        System.out.println("删除数据索引为："+rowIndex);

        this.accounts.remove(rowIndex);
        fireTableDataChanged();
    }

    public void setPersonList(ArrayList<Account> personList) {

        System.out.println("接收到的personList个数"+personList.size());
        this.accounts = personList;

        System.out.println("更前接收到的personList个数"+this.accounts.size());

        fireTableDataChanged();
        System.out.println("更后接收到的personList个数"+this.accounts.size());

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public void updateDate(ArrayList<Account> newpersonList) {
        this.accounts = newpersonList;
        fireTableDataChanged();
    }
}