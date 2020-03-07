package dao;

import java.util.List;

import bean.LandMessage;

public interface LandMessageDao {
	
	//��ӵ�½ʱ��
	public boolean addLandTimeMes(int uid, String userip) throws Exception;

	//����˳�ʱ��
	public boolean addCancelTimeMes(int uid, String userip, String landtime) throws Exception;
	
	//��ѯ��½��Ϣ
	public List<LandMessage> getLandMessage(int uid, String userip) throws Exception;

}
