package service;

import java.sql.SQLException;
import java.util.List;

import bean.VisitMessage;
import dao.VisitMessageDao;
import db.DBConnection;
import db.VisitMessageDaoImpl;

public class VisitMessageService implements VisitMessageDao{

	private DBConnection dbconn = null;

	private VisitMessageDao dao = null;

	public VisitMessageService() throws SQLException {
		this.dbconn = new DBConnection();
		this.dao = new VisitMessageDaoImpl(this.dbconn.getConnection());
	}
	
	//���Ӵ�ʱ��
	@Override
	public boolean addLandTimeMes(int uid, int gid, String gtype) throws Exception{			
		if (isInt(uid) && isInt(gid) && gtype!=null) {
			return this.dao.addLandTimeMes(uid, gid, gtype);
		}
		return false;
	}

	//�����˳�ʱ��
	@Override
	public boolean addCancelTimeMes(int uid, int gid, String landtime) throws Exception{
		if (isInt(uid) && isInt(gid) && landtime != null) {
			return this.dao.addCancelTimeMes(uid, gid, landtime);
		}
		return false;
	}
	
	//��ѯ��½ʱ��
	@Override
	public String getVisitlandtime(int uid, int gid) throws Exception{
		if (isInt(uid) && isInt(gid)) {
			return this.dao.getVisitlandtime(uid, gid);
		}
		return null;
	}
			
	//��ѯ��½��Ϣ
	@Override
	public List<VisitMessage> getVisitMessage(int uid, int gid) throws Exception{
		if (isInt(uid) && isInt(gid)) {
			return this.dao.getVisitMessage(uid, gid);
		}
		return null;
	}
	
	public boolean isInt(int index) {
		if (index == 0) {
			return false;
		}
		String str = String.valueOf(index);
		return str.matches("[0-9]+$");
	}
}