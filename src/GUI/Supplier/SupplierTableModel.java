package GUI.Supplier;

import DBconnection.Account;
import DBconnection.TGoods;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SupplierTableModel extends AbstractTableModel {
    private ArrayList<TGoods> supplier;
    private final String[] columnNames = { "姓名", "手机号", "货物", "数量", "单价", "总价"};

    public SupplierTableModel(ArrayList<TGoods> supplier) {
        this.supplier = supplier;
    }

    @Override
    public int getRowCount() {
        return supplier.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TGoods supply = supplier.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return supply.getName();
            case 1:
                return supply.getNumber();
            case 2:
                return supply.getSto();
            case 3:
                return supply.getNum();
            case 4:
                return supply.getPrice();
            case 5:
                return supply.getSumprice();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void addSupplier(TGoods supply) {
        supplier.add(supply);
        fireTableDataChanged();
    }

    public void removePerson(int rowIndex) {
        System.out.println("当前的人员表个数为："+this.supplier.size());
        System.out.println("删除数据索引为："+rowIndex);

        this.supplier.remove(rowIndex);
        fireTableDataChanged();
    }

    public void setSupplierList(ArrayList<TGoods> supplierList) {

        System.out.println("接收到的personList个数"+supplierList.size());
        this.supplier = supplierList;

        System.out.println("更前接收到的personList个数"+this.supplier.size());

        fireTableDataChanged();
        System.out.println("更后接收到的personList个数"+this.supplier.size());

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public void updateDate(ArrayList<TGoods> newsupplierList) {
        this.supplier = newsupplierList;
        fireTableDataChanged();
    }
}



//"姓名", "手机号", "货物", "数量", "单价", "总价"