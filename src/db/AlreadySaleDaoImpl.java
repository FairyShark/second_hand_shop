package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
	public boolean addSaleGoods(int uid, int buy_uid, int gid, int number) throws Exception {
		pstmt = null;
		String sql = "insert into alreadysale(uid,buy_uid,gid,number,saletime)value(?,?,?,?,now());";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		pstmt.setInt(2, buy_uid);
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
	public List<AlreadySale> getAllSaleGoods(int uid) throws Exception {
		pstmt = null;
		ResultSet rs = null;
		List<AlreadySale> abList = null;
		String sql = "select * from alreadySale where uid=? order by saletime desc";
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		rs = pstmt.executeQuery();
		AlreadySale ab;
		abList = new ArrayList<AlreadySale>();
		while (rs.next()) {
			ab = new AlreadySale();
			ab.setUid(uid);
			ab.setAsid(rs.getInt("asid"));
			ab.setGid(rs.getInt("gid"));
			ab.setBUid(rs.getInt("buy_uid"));
			ab.setNumber(rs.getInt("number"));
			String date = rs.getDate("saletime").toString();
			String time = rs.getTime("saletime").toString();
			ab.setSaleTime(date + " " + time);
			abList.add(ab);
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
		AlreadySale ab;
		abList = new ArrayList<AlreadySale>();
		while (rs.next()) {
			ab = new AlreadySale();
			ab.setUid(gid);
			ab.setAsid(rs.getInt("asid"));
			ab.setGid(rs.getInt("uid"));
			ab.setBUid(rs.getInt("buy_uid"));
			ab.setNumber(rs.getInt("number"));
			String date = rs.getDate("saletime").toString();
			String time = rs.getTime("saletime").toString();
			ab.setSaleTime(date + " " + time);
			abList.add(ab);
		}
		return abList;
	}

}
