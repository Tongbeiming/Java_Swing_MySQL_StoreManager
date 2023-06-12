package DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StoreDao implements Dao<Store> {

@Override
public boolean add(Store store) {
        // 添加逻辑，调用DbUtil.getConnection()，参考后面的update/delete.
        return false;
}

@Override
public boolean delete(Store store) {
        Connection conn = null;
        try {
        conn = DbUtil.getConnection();
        String sql = "DELETE FROM store WHERE number = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, store.getNumber());
        return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
        e.printStackTrace();
        } finally {
        DbUtil.closeConnection(conn);
        }
        return false;
        }

@Override
public boolean update(Store store) {
        // 更新逻辑，与delete类似，这里调用pstmt.setXXX设定具体参数，参考delete.
        return false;
}

@Override
public Store queryById(Object id) {
        // 根据id查询，与更新类似，这里查询后，生成新的Store对象并返回.
        return null;
}

@Override
public List<Store> queryAll() {
        // 查询所有,与删除类似，这里返回List<Store>即可.
        return null;
}
    }
