package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
		String lasttime = null;
		pstmt = null;
		String sql = "update visitmessage set canceltime=now(),lasttime=?  where uid=? and gid=? and landtime=?;";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setString(1, lasttime);
		pstmt.setInt(2, uid);
		pstmt.setInt(3, gid);
		pstmt.setString(4, landtime);
		result = pstmt.executeUpdate();
		pstmt.close();
		if (result == 1) {
			return true;
		}
		return false;
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
