package dao;

import java.util.List;

import bean.LandMessage;

public interface LandMessageDao {

    //��ӵ�½ʱ��
    boolean addLandTimeMes(int uid, String uname, String userip) throws Exception;

    //����˳�ʱ��
    boolean addCancelTimeMes(int uid, String userip, String landtime) throws Exception;

    //��ѯ��½ʱ��
    String getLandtime(int uid, String userip) throws Exception;

    //��ѯ���е�½��Ϣ
    List<LandMessage> getAllLandMessage() throws Exception;

    //��ѯ��½��Ϣ
    List<LandMessage> getLandMessage(int uid, String uname, String userip, String landtime) throws Exception;

}
