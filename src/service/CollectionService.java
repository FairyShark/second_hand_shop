package service;

import bean.Collection;
import dao.CollectionDao;
import db.CollectionDaoImpl;
import db.DBConnection;

import java.sql.SQLException;
import java.util.List;

public class CollectionService implements CollectionDao {

    private DBConnection dbconn = null;

    private CollectionDao dao = null;

    public CollectionService() throws SQLException {
        this.dbconn = new DBConnection();
        this.dao = new CollectionDaoImpl(this.dbconn.getConnection());
    }

    @Override
    public boolean addCollectionGoods(int uid, int gid) throws Exception {
        if (isInt(uid) && isInt(gid)) {
            return this.dao.addCollectionGoods(uid, gid);
        }
        return false;
    }

    @Override
    public boolean deleteCollectionGoods(int uid, int gid) throws Exception {
        if (isInt(uid) && isInt(gid)) {
            return this.dao.deleteCollectionGoods(uid, gid);
        }
        return false;
    }

    @Override
    public boolean judgeCollection(int uid, int gid) throws Exception {
        if (isInt(uid) && isInt(gid)) {
            return this.dao.judgeCollection(uid, gid);
        }
        return false;
    }

    @Override
    public List<Collection> getAllCollectionGoods(int uid) throws Exception {
        if (isInt(uid)) {
            return this.dao.getAllCollectionGoods(uid);
        }
        return null;
    }

    public boolean isInt(int index) {
        if (index == 0) {
            return false;
        }
        String str = String.valueOf(index);
        return str.matches("[0-9]+$");
    }

}
