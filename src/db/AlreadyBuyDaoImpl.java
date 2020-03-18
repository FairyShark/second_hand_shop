package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.AlreadyBuy;
import dao.AlreadyBuyDao;

public class AlreadyBuyDaoImpl implements AlreadyBuyDao {

    private Connection conn = null;

    private PreparedStatement pstmt = null;

    public AlreadyBuyDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean addBuyGoods(int uid, String sale_name, int gid, int number) throws Exception {
        pstmt = null;
        String sql = "insert into alreadybuy(uid,sale_name,gid,number,buytime) value(?,?,?,?,now());";
        int result = 0;
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, uid);
        pstmt.setString(2, sale_name);
        pstmt.setInt(3, gid);
        pstmt.setInt(4, number);
        result = pstmt.executeUpdate();
        pstmt.close();
        if (result == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteBuyGoods(int gid) throws Exception {
        String sql = "delete from alreadyBuy where gid=?";
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
    public List<AlreadyBuy> getAllBuyGoods(int uid) throws Exception {
        pstmt = null;
        ResultSet rs = null;
        List<AlreadyBuy> abList = null;
        String sql = "select * from alreadyBuy where uid=? order by buytime desc";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, uid);
        rs = pstmt.executeQuery();
        AlreadyBuy ab;
        abList = new ArrayList<AlreadyBuy>();
        while (rs.next()) {
            ab = new AlreadyBuy();
            ab.setUid(uid);
            ab.setAid(rs.getInt("aid"));
            ab.setGid(rs.getInt("gid"));
            ab.setNumber(rs.getInt("number"));
            String date = rs.getDate("buytime").toString();
            String time = rs.getTime("buytime").toString();
            ab.setBuyTime(date + " " + time);
            abList.add(ab);
        }
        return abList;
    }

    @Override
    public List<AlreadyBuy> getAllBuyGoodsByGid(int gid) throws Exception {
        pstmt = null;
        ResultSet rs = null;
        List<AlreadyBuy> abList = null;
        String sql = "select * from alreadyBuy where uid=? order by buytime desc";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, gid);
        rs = pstmt.executeQuery();
        AlreadyBuy ab;
        abList = new ArrayList<AlreadyBuy>();
        while (rs.next()) {
            ab = new AlreadyBuy();
            ab.setUid(gid);
            ab.setAid(rs.getInt("aid"));
            ab.setGid(rs.getInt("uid"));
            ab.setNumber(rs.getInt("number"));
            String date = rs.getDate("buytime").toString();
            String time = rs.getTime("buytime").toString();
            ab.setBuyTime(date + " " + time);
            abList.add(ab);
        }
        return abList;
    }

}
