package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import bean.LandMessage;
import dao.LandMessageDao;

public class LandMessageDaoImpl implements LandMessageDao {

	private Connection conn = null;

	private PreparedStatement pstmt = null;

	public LandMessageDaoImpl(Connection conn) {
		this.conn = conn;
	}

	// 添加登陆时间
	@Override
	public boolean addLandTimeMes(int uid, String uname, String userip) throws Exception {
		pstmt = null;
		String sql = "insert into landmessage(uid,uname,userip,landtime,canceltime) value(?,?,?,now(),now());";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		pstmt.setString(2, uname);
		pstmt.setString(3, userip);
		result = pstmt.executeUpdate();
		pstmt.close();
		if (result == 1) {
			return true;
		}
		return false;
	}

	// 添加退出时间
	@Override
	public boolean addCancelTimeMes(int uid, String userip, String landtime) throws Exception {
		pstmt = null;
		String sql = "update landmessage set canceltime=now() where uid=? and userip=? and landtime=?;";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		pstmt.setString(2, userip);
		pstmt.setString(3, landtime);
		result = pstmt.executeUpdate();
		pstmt.close();
		if (result == 1) {
			return true;
		}
		return false;
	}

	// 查询登陆信息
	@Override
	public String getLandtime(int uid, String userip) throws Exception {
		String landtime = null;
		ResultSet rs = null;
		String sql = "select * from landmessage where uid=? and userip=? order by lmid desc limit 1;";
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		pstmt.setString(2, userip);
		rs = pstmt.executeQuery();
		rs.next();
		landtime = rs.getString("landtime");
		return landtime;
	}

	// 查询所有登陆信息
	@Override
	public List<LandMessage> getAllLandMessage() throws Exception {
		List<LandMessage> landmessageList = new ArrayList<>();
		LandMessage landmessage = null;
		String sql = "select * from landmessage order by lmid desc;";
		Statement st = (Statement) conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			landmessage = new LandMessage();
			int lmid_t = rs.getInt("lmid");
			int uid_t = rs.getInt("uid");
			String uname_t = rs.getString("uname");
			String userip_t = rs.getString("userip");
			String landtime_t = rs.getString("landtime");
			String canceltime_t = rs.getString("canceltime");
			landmessage.setLmid(lmid_t);
			landmessage.setUid(uid_t);
			landmessage.setUname(uname_t);
			landmessage.setUserip(userip_t);
			landmessage.setLandtime(landtime_t);
			landmessage.setCanceltime(canceltime_t);
			landmessageList.add(landmessage);
		}
		return landmessageList;
	}

	// 查询登陆信息
	@Override
	public List<LandMessage> getLandMessage(int uid, String uname, String userip, String landtime) throws Exception {
		List<LandMessage> landmessageList = new ArrayList<LandMessage>();
		LandMessage landmessage = null;
		ResultSet rs = null;
		String sql = null;
		if(Objects.equals(landtime, "%&ALL&%")) {
			sql = "select * from landmessage where if(?=-1,1=1,uid=?) and if(?='%&ALL&%',1=1,uname like ?) and if(?='%&ALL&%',1=1,userip=?) order by lmid desc;";
			pstmt = this.conn.prepareStatement(sql);
		}else {
			sql = "select * from landmessage where if(?=-1,1=1,uid=?) and if(?='%&ALL&%',1=1,uname like ?) and if(?='%&ALL&%',1=1,userip=?)and Date(landtime)=? order by lmid desc;";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(7, landtime);
		}
		pstmt.setInt(1, uid);
		pstmt.setInt(2, uid);
		pstmt.setString(3, uname);
		pstmt.setString(4, '%' + uname + '%');
		pstmt.setString(5, userip);
		pstmt.setString(6, userip);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			landmessage = new LandMessage();
			landmessage.setLmid(rs.getInt("lmid"));
			landmessage.setUid(rs.getInt("uid"));
			landmessage.setUname(rs.getString("uname"));
			landmessage.setUserip(rs.getString("userip"));
			landmessage.setLandtime(rs.getString("landtime"));
			landmessage.setCanceltime(rs.getString("canceltime"));
			landmessageList.add(landmessage);
		}
		return landmessageList;
	}
}
