package DBconnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 商品实体类
public class Store {
    private String number;
    private String name;
    private int num;
    private String locati;
    private java.sql.Date date;
    private double price;
    private double sunprice;
    private boolean isdangerous;

    public Store(String number, String name, int num, String locati, Date date, double price, double sunprice, boolean isdangerous) {
        this.number = number;
        this.name = name;
        this.num = num;
        this.locati = locati;
        this.date = date;
        this.price = price;
        this.sunprice = sunprice;
        this.isdangerous = isdangerous;
    }

    // 添加相应的getter和setter

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getLocati() {
        return locati;
    }

    public void setLocati(String locati) {
        this.locati = locati;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSunprice() {
        return sunprice;
    }

    public void setSunprice(double sunprice) {
        this.sunprice = sunprice;
    }

    public boolean getIsdangerous() {
        return isdangerous;
    }

    public void setIsdangerous(boolean isdangerous) {
        this.isdangerous = isdangerous;
    }
    public void updateToDb() {
        String sql = "UPDATE store SET name=?, num=?, date=?, price=?, location=?, sunprice=?, isdangerous=? WHERE number=?";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, num);
            pstmt.setDate(3, date);
            pstmt.setDouble(4, price);
            pstmt.setString(5, locati);
            pstmt.setDouble(6, price*num);
            pstmt.setBoolean(7, isdangerous);
            pstmt.setString(8, number); // 别忘了设置第8个参数

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
