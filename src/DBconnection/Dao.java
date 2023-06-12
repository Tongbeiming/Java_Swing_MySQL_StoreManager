package DBconnection;

import java.util.List;

public interface Dao<T> {

    boolean add(T t);

    boolean delete(T t);

    boolean update(T t);

    T queryById(Object id);

    List<T> queryAll();
}

