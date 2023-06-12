package DBconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// 供货商实体类
public class TGoods {
    private String name;
    private String number;
    private String sto;
    private int num;
    private double price;
    private double sumprice;

    public TGoods(String name, String number, String sto, int num, double price, double sumprice) {
        this.name = name;
        this.number = number;
        this.sto = sto;
        this.num = num;
        this.price = price;
        this.sumprice = sumprice;
    }

    // 添加相应的getter和setter


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSto() {
        return sto;
    }

    public void setSto(String sto) {
        this.sto = sto;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSumprice() {
        return sumprice;
    }

    public void setSumprice(double sumprice) {
        this.sumprice = sumprice;
    }

    public void addToDb() {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            //你看看insert到哪张表了草操nb
            String sql = "INSERT INTO supplier(name, number, sto, num, price, sumprice) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, this.name);
            stmt.setString(2, this.number);
            stmt.setString(3, this.sto);
            stmt.setInt(4, this.num);
            stmt.setDouble(5, this.price);
            stmt.setDouble(6,this.sumprice);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    public static List<TGoods> getAllFromDb() {
        List<TGoods> supplierList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            String sql = "SELECT * FROM supplier";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String number = rs.getString("number");
                String sto = rs.getString("sto");
                int num = rs.getInt("num");
                double pri = rs.getDouble("price");
                double spri = rs.getDouble("sumprice");
                TGoods supplier = new TGoods(name, number, sto, num, pri, spri);
                supplierList.add(supplier);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return supplierList;
    }

    public void deleteFromDb() {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            String sql = "DELETE FROM supplier WHERE name=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, this.name);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void updateInDb() {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            String sql = "UPDATE supplier SET number=?, sto=?, num=?, price=?, sumprice=? WHERE name=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, this.number);
            stmt.setString(2, this.sto);
            stmt.setInt(3, this.num);
            stmt.setDouble(4, this.price);
            stmt.setDouble(5, this.sumprice);
            stmt.setString(6, this.name);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    // 省略...
}
