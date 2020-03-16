package service;

import java.sql.SQLException;
import java.util.List;

import bean.LandMessage;
import dao.LandMessageDao;
import db.LandMessageDaoImpl;
import db.DBConnection;

public class LandMessageService implements LandMessageDao {
	private DBConnection dbconn = null;

	private LandMessageDao dao = null;

	public LandMessageService() throws SQLException {
		this.dbconn = new DBConnection();
		this.dao = new LandMessageDaoImpl(this.dbconn.getConnection());
	}

	// 添加登陆时间
	@Override
	public boolean addLandTimeMes(int uid, String uname, String userip) throws Exception {
		if (isInt(uid) && uname!=null && userip != null) {
			return this.dao.addLandTimeMes(uid, uname, userip);
		}
		return false;
	}

	// 添加退出时间
	@Override
	public boolean addCancelTimeMes(int uid, String userip, String landtime) throws Exception {
		if (isInt(uid) && userip != null && landtime != null) {
			return this.dao.addCancelTimeMes(uid, userip, landtime);
		}
		return false;
	}

	// 查询登陆信息
	@Override
	public String getLandtime(int uid, String userip) throws Exception {
		if (isInt(uid) && userip != null) {
			return this.dao.getLandtime(uid, userip);
		}
		return null;
	}

	// 查询所有登陆信息
	@Override
	public List<LandMessage> getAllLandMessage() throws Exception {
		return this.dao.getAllLandMessage();
	}

	// 查询登陆信息
	@Override
	public List<LandMessage> getLandMessage(int uid, String uname, String userip, String landtime) throws Exception {
		return this.dao.getLandMessage(uid, uname, userip, landtime);
	}

	public boolean isInt(int index) {
		if (index == 0) {
			return false;
		}
		String str = String.valueOf(index);
		return str.matches("[0-9]+$");
	}
}
