package dao;

import java.util.List;

import bean.OperationMes;

public interface OperationMesDao {
	
	//��Ӳ�����Ϣ
	public boolean addOperationMes(int uid, String userip, String opcontent) throws Exception;
			
	//��ѯ�����Ϣ
	public List<OperationMes> getOperationMes(int uid, String userip) throws Exception;		

}
