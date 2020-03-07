package dao;

import java.util.List;

import bean.VisitMessage;

public interface VisitMessageDao {
	
	//��Ӵ�ʱ��
	public boolean addLandTimeMes(int uid, int gid, String gtype) throws Exception;

	//����˳�ʱ��
	public boolean addCancelTimeMes(int uid, int gid, String landtime) throws Exception;
		
	//��ѯ�����Ϣ
	public List<VisitMessage> getVisitMessage(int uid, int gid) throws Exception;
	
}
