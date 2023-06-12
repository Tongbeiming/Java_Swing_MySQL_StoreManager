package GUI.Store;


import DBconnection.DbUtil;
import DBconnection.Store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class performSearch {
    private ArrayList<Store> performSearch(String keyword) {
        ArrayList<Store> resultList = new ArrayList<>();

        // Execute a query based on the given keyword using the same approach as in GoodsPanel
        try {
            Connection connection = DbUtil.getConnection();
            String query = "SELECT number, name, num, price, date, location, sunprice, isdangerous FROM store WHERE name LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String number = resultSet.getString("number");
                String name = resultSet.getString("name");
                int num = resultSet.getInt("num");
                double price = resultSet.getDouble("price");
                Date date = resultSet.getDate("date");
                String location = resultSet.getString("location");
                double sunprice = resultSet.getDouble("sunprice");
                boolean isdangerous = resultSet.getBoolean("isdangerous");

                Store store = new Store(number, name, num, location, (java.sql.Date) date, price, sunprice, isdangerous);
                resultList.add(store);
            }

            resultSet.close();
            statement.close();
            DbUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return resultList
        return resultList;
    }
}