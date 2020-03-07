package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.LandMessage;
import dao.LandMessageDao;

public class LandMessageDaoImpl implements LandMessageDao{
	
	private Connection conn = null;

	private PreparedStatement pstmt = null;

	public LandMessageDaoImpl(Connection conn) {
		this.conn = conn;
	}

	//��ӵ�½ʱ��
	@Override
	public boolean addLandTimeMes(int uid, String userip) throws Exception{
		pstmt = null;
		String sql = "insert into landmessage(uid,userip,landtime) value(?,?,now());";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		pstmt.setString(2, userip);
		result = pstmt.executeUpdate();
		pstmt.close();
		if (result == 1) {
			return true;
		}
		return false;
	}

	//����˳�ʱ��
	@Override
	public boolean addCancelTimeMes(int uid) throws Exception{
		pstmt = null;
		String sql = "update landmessage set cancentime=now() where uid=? ORDER lmid DESC LIMIT 1;";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		result = pstmt.executeUpdate();
		pstmt.close();
		if (result == 1) {
			return true;
		}
		return false;
	}
		
	//��ѯ��½��Ϣ
	@Override
	public List<LandMessage> getLandMessage(int uid, String userip) throws Exception{
		List<LandMessage> landmessageList = new ArrayList<LandMessage>();
		LandMessage landmessage = null;
		ResultSet rs = null;
		String sql = "select * from landmessage where if(?=0,1=1,uid=?) and if(?='ȫ��',1=1,userip=?);";
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		pstmt.setInt(2, uid);
		pstmt.setString(3, userip);
		pstmt.setString(4, userip);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			landmessage = new LandMessage();
			landmessage.setLmid(rs.getInt("lmid"));
			landmessage.setUid(uid);
			landmessage.setUserip(userip);
			landmessage.setLandtime(rs.getString("landtime"));
			landmessage.setCanceltime(rs.getString("canceltime"));
			landmessageList.add(landmessage);
		}
		return landmessageList;
	}
}
