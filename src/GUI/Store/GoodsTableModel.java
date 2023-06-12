package GUI.Store;

import DBconnection.Store;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class GoodsTableModel extends AbstractTableModel {
    private ArrayList<Store> goodsList;
    private final String[] columnNames = {"商品编号", "名称", "库存", "单价", "日期", "位置", "总价", "是否危险"};

    public GoodsTableModel(ArrayList<Store> goodsList) {
        this.goodsList = goodsList;
    }

    @Override
    public int getRowCount() {
        return goodsList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public Store getGoodsAt(int rowIndex) {
        return goodsList.get(rowIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Store goods = (Store) aValue;
        Store currentGoods = goodsList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                currentGoods.setNumber(goods.getNumber());
                break;
            case 1:
                currentGoods.setName(goods.getName());
                break;
            case 2:
                currentGoods.setNum(goods.getNum());
                break;
            case 3:
                currentGoods.setPrice(goods.getPrice());
                break;
            case 4:
                currentGoods.setDate(goods.getDate());
                break;
            case 5:
                currentGoods.setLocati(goods.getLocati());
                break;
            case 6:
                currentGoods.setSunprice(goods.getSunprice());
                break;
            case 7:
                currentGoods.setIsdangerous(goods.getIsdangerous());
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Store goods = goodsList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return goods.getNumber();
            case 1:
                return goods.getName();
            case 2:
                return goods.getNum();
            case 3:
                return goods.getPrice();
            case 4:
                return goods.getDate();
            case 5:
                return goods.getLocati();
            case 6:
                return goods.getSunprice();
            case 7:
                return goods.getIsdangerous();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void addGoods(Store goods) {
        goodsList.add(goods);
        fireTableRowsInserted(goodsList.size() - 1, goodsList.size() - 1);
    }

    public void removeGoods(int index) {
        goodsList.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public int getGoodsId(int row) {
        int goodsId = -1;
        Object obj = getValueAt(row, 0); // 假设商品 ID 存储在第一列
        if (obj instanceof Integer) {
            goodsId = (Integer) obj;
        }
        return goodsId;
    }

    public void updateData(ArrayList<Store> newGoodsList) {
        this.goodsList = newGoodsList;
        fireTableDataChanged();
    }
}