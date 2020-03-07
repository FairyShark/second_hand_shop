package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.OperationMes;
import dao.OperationMesDao;

public class OperationMesDaoImpl implements OperationMesDao{
	
	private Connection conn = null;

	private PreparedStatement pstmt = null;

	public OperationMesDaoImpl(Connection conn) {
		this.conn = conn;
	}
	
	//添加操作信息
	@Override
	public boolean addOperationMes(int uid, String userip, String opcontent) throws Exception{
		pstmt = null;
		String sql = "insert into operationmes(uid,userip,opcontent,operationtime) value(?,?,?,now());";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		pstmt.setString(2, userip);
		pstmt.setString(3, opcontent);
		result = pstmt.executeUpdate();
		pstmt.close();
		if (result == 1) {
			return true;
		}
		return false;
	}
			
	//查询浏览信息
	@Override
	public List<OperationMes> getOperationMes(int uid, String userip) throws Exception{
		List<OperationMes> operationmesList = new ArrayList<OperationMes>();
		OperationMes operationmes = null;
		ResultSet rs = null;
		String sql = "select * from operationmes where if(?=0,1=1,uid=?) and if(?='全部',1=1,userip=?);";
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		pstmt.setInt(2, uid);
		pstmt.setString(3, userip);
		pstmt.setString(4, userip);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			operationmes = new OperationMes();
			operationmes.setOmid(rs.getInt("omid"));
			operationmes.setUid(uid);
			operationmes.setUserip(userip);
			operationmes.setOpcontent(rs.getString("opcontent"));
			operationmes.setOperationtime(rs.getString("operationtime"));
			operationmesList.add(operationmes);
		}
		return operationmesList;
	}

}
