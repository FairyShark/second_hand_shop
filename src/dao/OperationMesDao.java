package dao;

import java.util.List;

import bean.OperationMes;

public interface OperationMesDao {
	
	//添加操作信息
	public boolean addOperationMes(int uid, String userip, String opcontent) throws Exception;
			
	//查询浏览信息
	public List<OperationMes> getOperationMes(int uid, String userip) throws Exception;		

}
