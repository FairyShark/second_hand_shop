package service;

import java.sql.SQLException;
import java.util.List;

import bean.AlreadyBuy;
import dao.AlreadyBuyDao;
import db.AlreadyBuyDaoImpl;
import db.DBConnection;

public class AlreadyBuyService implements AlreadyBuyDao {

    private AlreadyBuyDao dao;

    public AlreadyBuyService() throws SQLException {
        DBConnection dbconn = new DBConnection();
        this.dao = new AlreadyBuyDaoImpl(dbconn.getConnection());
    }

    @Override
    public boolean addBuyGoods(int uid, String sale_name, int gid, String gtype, int number, float price, float carriage) throws Exception {
        if (isInt(uid) && sale_name != null && isInt(gid) && gtype!=null && isInt(number)) {
            return this.dao.addBuyGoods(uid, sale_name, gid, gtype, number, price, carriage);
        }
        return false;
    }

    @Override
    public boolean deleteBuyGoods(int gid) throws Exception {
        if (isInt(gid)) {
            return this.dao.deleteBuyGoods(gid);
        }
        return false;
    }

    @Override
    public List<AlreadyBuy> getAllBuyGoods(int uid) throws Exception {
        if (isInt(uid)) {
            return this.dao.getAllBuyGoods(uid);
        }
        return null;
    }

    @Override
    public List<AlreadyBuy> getAllBuyGoodsByGid(int gid) throws Exception {
        if (isInt(gid)) {
            return this.dao.getAllBuyGoods(gid);
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
