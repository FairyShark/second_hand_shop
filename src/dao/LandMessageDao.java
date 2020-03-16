package dao;

import java.util.List;

import bean.LandMessage;

public interface LandMessageDao {
	
	//��ӵ�½ʱ��
	public boolean addLandTimeMes(int uid, String uname, String userip) throws Exception;

	//����˳�ʱ��
	public boolean addCancelTimeMes(int uid, String userip, String landtime) throws Exception;
	
	//��ѯ��½ʱ��
	public String getLandtime(int uid, String userip) throws Exception;

	//��ѯ���е�½��Ϣ
	public List<LandMessage> getAllLandMessage() throws Exception;

	//��ѯ��½��Ϣ
	public List<LandMessage> getLandMessage(int uid, String uname, String userip, String landtime) throws Exception;

}
