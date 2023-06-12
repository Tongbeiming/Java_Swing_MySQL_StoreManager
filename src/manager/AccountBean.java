package manager;

import DBconnection.Store;
import DBconnection.StoreDao;

import java.util.List;

public class AccountBean {
    private StoreDao storeDao;

    public AccountBean() {
        storeDao = new StoreDao();
    }

    public boolean addStore(Store store) {
        return storeDao.add(store);
    }

    public boolean deleteStore(Store store) {
        return storeDao.delete(store);
    }

    public boolean updateStore(Store store) {
        return storeDao.update(store);
    }

    public Store getStoreById(String id) {
        return storeDao.queryById(id);
    }

    public List<Store> getAllStores() {
        return storeDao.queryAll();
    }
}