package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.VisitMessage;
import dao.VisitMessageDao;

public class VisitMessageDaoImpl implements VisitMessageDao{
	private Connection conn = null;

	private PreparedStatement pstmt = null;

	public VisitMessageDaoImpl(Connection conn) {
		this.conn = conn;
	}

	//添加打开时间
	@Override
	public boolean addLandTimeMes(int uid, int gid, String gtype) throws Exception{
		pstmt = null;
		String sql = "insert into visitmessage(uid,gid,types,landtime) value(?,?,?,now());";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		pstmt.setInt(2, gid);
		pstmt.setString(3, gtype);
		result = pstmt.executeUpdate();
		pstmt.close();
		if (result == 1) {
			return true;
		}
		return false;
	}

	//添加退出时间
	@Override
	public boolean addCancelTimeMes(int uid, int gid, String landtime) throws Exception{
		int lasttime = 0;
		pstmt = null;
		String sql = "update visitmessage set canceltime=now()  where uid=? and gid=? and landtime=?;";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		pstmt.setInt(2, gid);
		pstmt.setString(3, landtime);
		result = pstmt.executeUpdate();
		pstmt.close();
		if (result == 1) {
			pstmt = null;
			sql = "select * from visitmessage where uid=? and gid=? and landtime=?;";
			ResultSet rs = null;
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setInt(2, gid);
			pstmt.setString(3, landtime);
			rs = pstmt.executeQuery();
			rs.next();
			String canceltime = rs.getString("canceltime");
			pstmt.close();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date1 = null;
			Date date2 = null;
			try {
				date1 = sdf.parse(landtime.toString());
				date2 = sdf.parse(canceltime.toString());
				lasttime = Integer.parseInt(String.valueOf((date1.getTime() - date2.getTime())/1000));
				
				pstmt = null;
				sql = "update visitmessage set lasttime=?  where uid=? and gid=? and landtime=?;";
				result = 0;
				pstmt = this.conn.prepareStatement(sql);
				pstmt.setInt(1, lasttime);
				pstmt.setInt(2, uid);
				pstmt.setInt(3, gid);
				pstmt.setString(4, landtime);
				result = pstmt.executeUpdate();
				pstmt.close();
				if (result == 1) {
					return true;
				}
				return false;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	//查询登陆时间
	@Override
	public String getVisitlandtime(int uid, int gid) throws Exception{
		String landtime = null;
		ResultSet rs = null;
		String sql = "select * from visitmessage where uid=? and gid=? order by vmid desc limit 1;";
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		pstmt.setInt(2, gid);
		rs = pstmt.executeQuery();
		rs.next();
		landtime = rs.getString("landtime");
		return landtime;
	}
		
	//查询登陆信息
	@Override
	public List<VisitMessage> getVisitMessage(int uid, int gid) throws Exception{
		List<VisitMessage> visitmessageList = new ArrayList<VisitMessage>();
		VisitMessage visitmessage = null;
		ResultSet rs = null;
		String sql = "select * from visitmessage where if(?=0,1=1,uid=?) and if(?=0,1=1,gid=?);";
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		pstmt.setInt(2, uid);
		pstmt.setInt(3, gid);
		pstmt.setInt(4, gid);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			visitmessage = new VisitMessage();
			visitmessage.setVmid(rs.getInt("vmid"));
			visitmessage.setUid(uid);
			visitmessage.setGid(gid);
			visitmessage.setTypes(rs.getString("types"));
			visitmessage.setLandtime(rs.getString("landtime"));
			visitmessage.setCanceltime(rs.getString("canceltime"));
			visitmessage.setLasttime(rs.getInt("lasttime"));
			visitmessageList.add(visitmessage);
		}
		return visitmessageList;
	}
	
	
}
