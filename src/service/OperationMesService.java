package service;

import java.sql.SQLException;
import java.util.List;

import bean.OperationMes;
import dao.OperationMesDao;
import db.DBConnection;
import db.OperationMesDaoImpl;

public class OperationMesService implements OperationMesDao{
	
	private DBConnection dbconn = null;

	private OperationMesDao dao = null;

	public OperationMesService() throws SQLException {
		this.dbconn = new DBConnection();
		this.dao = new OperationMesDaoImpl(this.dbconn.getConnection());
	}
	
	//添加操作信息
	@Override
	public boolean addOperationMes(int uid, String userip, String opcontent) throws Exception{
		if (isInt(uid) && userip!=null && opcontent!=null) {
			return this.dao.addOperationMes(uid, userip, opcontent);
		}
		return false;
	}
				
	//查询浏览信息
	@Override
	public List<OperationMes> getOperationMes(int uid, String userip) throws Exception{
		if (isInt(uid) && userip!=null) {
			return this.dao.getOperationMes(uid, userip);
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
