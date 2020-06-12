package service;

import java.sql.SQLException;
import java.util.List;

import bean.ShoppingCart;
import dao.ShoppingCartDao;
import db.DBConnection;
import db.ShoppingCartDaoImpl;

public class ShoppingCartService implements ShoppingCartDao {

    private ShoppingCartDao dao;

    public ShoppingCartService() throws SQLException {
        DBConnection dbconn = new DBConnection();
        this.dao = new ShoppingCartDaoImpl(dbconn.getConnection());
    }

    @Override
    public boolean addGoods(int uid, int gid, int number) throws Exception {
        if (isInt(uid) && isInt(gid) && isInt(number)) {
            return this.dao.addGoods(uid, gid, number);
        }
        return false;
    }

    @Override
    public boolean deleteGoods(int uid, int gid, int number) throws Exception {
        if (isInt(uid) && isInt(gid) && isInt(number)) {
            return this.dao.deleteGoods(uid, gid, number);
        }
        return false;
    }

    @Override
    public boolean editGoods(int uid, int gid, int number) throws Exception {
        if (isInt(uid) && isInt(gid) && isInt(number)) {
            return this.dao.editGoods(uid, gid, number);
        }
        return false;
    }

    @Override
    public List<ShoppingCart> getAllGoods(int uid) throws Exception {
        if (isInt(uid)) {
            return this.dao.getAllGoods(uid);
        }
        return null;
    }

    @Override
    public String getDesignateGoodsMs(int uid, int gid) throws Exception {
        if (isInt(uid) && isInt(gid)) {
            return this.dao.getDesignateGoodsMs(uid, gid);
        }
        return "";
    }

    @Override
    public boolean payGoods(int uid, int gid, int number) throws Exception {
        if (isInt(uid) && isInt(gid) && isInt(number)) {
            return this.dao.payGoods(uid, gid, number);
        }
        return false;
    }

    @Override
    public boolean payAllGoods(int uid) throws Exception {
        if (isInt(uid)) {
            return this.dao.payAllGoods(uid);
        }
        return false;
    }

    @Override
    public boolean sendEmail(int uid, int gid, int number) throws Exception {
        if (isInt(uid) && isInt(gid) && isInt(number)) {
            return this.dao.sendEmail(uid, gid, number);
        }
        return false;
    }

    public boolean isInt(int index) {
        if (index == 0) {
            return false;
        }
        String str = String.valueOf(index);
        return str.matches("[0-9]+$");
    }

}
