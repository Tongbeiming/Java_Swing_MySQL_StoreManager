package DBconnection;

import DBconnection.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String id;
    private String name;
    private int age;
    private String role;
    private String phoneNum;
    private String email;

    public Account(String id, String name, int age, String role, String phoneNum, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.role = role;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addToDb() {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            String sql = "INSERT INTO personemployee(ID, name, age, role, phonenum, email) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, this.id);
            stmt.setString(2, this.name);
            stmt.setInt(3, this.age);
            stmt.setString(4, this.role);
            stmt.setString(5, this.phoneNum);
            stmt.setString(6, this.email);
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
            String sql = "UPDATE personemployee SET name=?, age=?, role=?, phonenum=?, email=? WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, this.name);
            stmt.setInt(2, this.age);
            stmt.setString(3, this.role);
            stmt.setString(4, this.phoneNum);
            stmt.setString(5, this.email);
            stmt.setString(6, this.id);
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

    public static List<Account> getAllFromDb() {
        List<Account> accountList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            String sql = "SELECT * FROM personemployee";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("ID");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String role = rs.getString("role");
                String phoneNum = rs.getString("phonenum");
                String email = rs.getString("email");
                Account account = new Account(id, name, age, role, phoneNum, email);
                accountList.add(account);
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
        return accountList;
    }

    public void deleteFromDb() {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            String sql = "DELETE FROM personemployee WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, this.id);
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

}