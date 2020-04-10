package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import bean.AlreadySale;
import dao.AlreadySaleDao;

public class AlreadySaleDaoImpl implements AlreadySaleDao {

    private Connection conn = null;

    private PreparedStatement pstmt = null;

    public AlreadySaleDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean addSaleGoods(int uid, int buy_uid, String buy_name, int gid, String gtype, int number, float price, float carriage) throws Exception {
        pstmt = null;
        String sql = "insert into alreadysale(uid,buy_uid,buy_name,gid,types,number,price,carriage,saletime)value(?,?,?,?,?,?,?,?,now());";
        int result = 0;
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, uid);
        pstmt.setInt(2, buy_uid);
        pstmt.setString(3, buy_name);
        pstmt.setInt(4, gid);
        pstmt.setString(5, gtype);
        pstmt.setInt(6, number);
        pstmt.setFloat(7, price);
        pstmt.setFloat(8, carriage);
        result = pstmt.executeUpdate();
        pstmt.close();
        if (result == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteSaleGoods(int gid) throws Exception {
        String sql = "delete from alreadySale where gid=?";
        int result = 0;
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, gid);
        result = pstmt.executeUpdate();
        pstmt.close();
        if (result == 1) {
            return true;
        }
        return false;
    }

    @Override
    public List<AlreadySale> getAllSaleGoods(int uid) throws Exception {
        pstmt = null;
        ResultSet rs = null;
        List<AlreadySale> abList = null;
        String sql = "select * from alreadySale where uid=? order by saletime desc";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, uid);
        rs = pstmt.executeQuery();
        AlreadySale as;
        abList = new ArrayList<AlreadySale>();
        while (rs.next()) {
            as = new AlreadySale();
            as.setUid(uid);
            as.setAsid(rs.getInt("asid"));
            as.setGid(rs.getInt("gid"));
            as.setGtype(rs.getString("types"));
            as.setPrice(rs.getFloat("price"));
            as.setCarriage(rs.getFloat("carriage"));
            as.setBUid(rs.getInt("buy_uid"));
            as.setNumber(rs.getInt("number"));
            String date = rs.getDate("saletime").toString();
            String time = rs.getTime("saletime").toString();
            as.setSaleTime(date + " " + time);
            abList.add(as);
        }
        return abList;
    }

    @Override
    public List<AlreadySale> getAllSaleGoodsByGid(int gid) throws Exception {
        pstmt = null;
        ResultSet rs = null;
        List<AlreadySale> abList = null;
        String sql = "select * from alreadySale where uid=? order by saletime desc";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, gid);
        rs = pstmt.executeQuery();
        AlreadySale as;
        abList = new ArrayList<AlreadySale>();
        while (rs.next()) {
            as = new AlreadySale();
            as.setUid(gid);
            as.setAsid(rs.getInt("asid"));
            as.setGid(rs.getInt("uid"));
            as.setBUid(rs.getInt("buy_uid"));
            as.setGtype(rs.getString("types"));
            as.setPrice(rs.getFloat("price"));
            as.setCarriage(rs.getFloat("carriage"));
            as.setNumber(rs.getInt("number"));
            String date = rs.getDate("saletime").toString();
            String time = rs.getTime("saletime").toString();
            as.setSaleTime(date + " " + time);
            abList.add(as);
        }
        return abList;
    }

    @Override
    public int getMonth(String sale_date) {
        int smonth = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(sale_date);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            smonth = c.get(Calendar.MONTH) + 1;
            return smonth;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
