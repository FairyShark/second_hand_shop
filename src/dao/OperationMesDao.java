package dao;

import java.util.List;

import bean.OperationMes;

public interface OperationMesDao {
	
	//��Ӳ�����Ϣ
	public boolean addOperationMes(int uid, String uname, String userip, String otype, String opcontent) throws Exception;

	//��ѯ���в�����־
	public List<OperationMes> getAllOperationMes() throws Exception;

	//��ѯ������־
	public List<OperationMes> getOperationMes(int uid, String uname, String userip, String otype, String landtime) throws Exception;

}
