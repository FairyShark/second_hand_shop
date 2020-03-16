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

	// ��ӵ�½ʱ��
	@Override
	public boolean addLandTimeMes(int uid, String uname, String userip) throws Exception {
		if (isInt(uid) && uname!=null && userip != null) {
			return this.dao.addLandTimeMes(uid, uname, userip);
		}
		return false;
	}

	// ����˳�ʱ��
	@Override
	public boolean addCancelTimeMes(int uid, String userip, String landtime) throws Exception {
		if (isInt(uid) && userip != null && landtime != null) {
			return this.dao.addCancelTimeMes(uid, userip, landtime);
		}
		return false;
	}

	// ��ѯ��½��Ϣ
	@Override
	public String getLandtime(int uid, String userip) throws Exception {
		if (isInt(uid) && userip != null) {
			return this.dao.getLandtime(uid, userip);
		}
		return null;
	}

	// ��ѯ���е�½��Ϣ
	@Override
	public List<LandMessage> getAllLandMessage() throws Exception {
		return this.dao.getAllLandMessage();
	}

	// ��ѯ��½��Ϣ
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
