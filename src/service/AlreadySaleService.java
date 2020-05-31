package service;

import java.sql.SQLException;
import java.util.List;

import bean.AlreadySale;
import dao.AlreadySaleDao;
import db.AlreadySaleDaoImpl;
import db.DBConnection;

public class AlreadySaleService implements AlreadySaleDao {

    private AlreadySaleDao dao;

    public AlreadySaleService() throws SQLException {
        DBConnection dbconn = new DBConnection();
        this.dao = new AlreadySaleDaoImpl(dbconn.getConnection());
    }

    @Override
    public boolean addSaleGoods(int uid, int buy_uid, String buy_name, int gid, String gtype, int number, float price, float carriage) throws Exception {
        if (isInt(uid) && isInt(buy_uid) && buy_name != null && isInt(gid) && gtype!=null && isInt(number)) {
            return this.dao.addSaleGoods(uid, buy_uid, buy_name, gid, gtype, number, price, carriage);
        }
        return false;
    }

    @Override
    public boolean deleteSaleGoods(int gid) throws Exception {
        if (isInt(gid)) {
            return this.dao.deleteSaleGoods(gid);
        }
        return false;
    }

    @Override
    public List<AlreadySale> getAllSaleGoods(int uid) throws Exception {
        if (isInt(uid)) {
            return this.dao.getAllSaleGoods(uid);
        }
        return null;
    }

    @Override
    public List<AlreadySale> getAllSaleGoodsByGid(int gid) throws Exception {
        if (isInt(gid)) {
            return this.dao.getAllSaleGoods(gid);
        }
        return null;
    }

    @Override
    public int getMonth(String date) throws Exception {
        if (date != null) {
            return this.dao.getMonth(date);
        }
        return 0;
    }

    @Override
    public int getYear(String date) throws Exception {
        if (date != null) {
            return this.dao.getYear(date);
        }
        return 0;
    }

    public boolean isInt(int index) {
        if (index == 0) {
            return false;
        }
        String str = String.valueOf(index);
        return str.matches("[0-9]+$");
    }

}
