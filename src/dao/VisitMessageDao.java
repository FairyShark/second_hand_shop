package dao;

import java.util.List;

import bean.VisitMessage;

public interface VisitMessageDao {
	
	//��Ӵ�ʱ��
	public boolean addLandTimeMes(int uid, int gid, String uname, String gname, String gtype) throws Exception;

	//����˳�ʱ��
	public boolean addCancelTimeMes(int uid, int gid, String landtime) throws Exception;
	
	//��ѯ��½ʱ��
	public String getVisitlandtime(int uid, int gid) throws Exception;

	//��ѯ���������Ϣ
	public List<VisitMessage> getAllVisitMessage() throws Exception;
		
	//��ѯ�����Ϣ
	public List<VisitMessage> getVisitMessage(int uid, int gid, String uname, String gname, String gtype, String landtime) throws Exception;
	
}
