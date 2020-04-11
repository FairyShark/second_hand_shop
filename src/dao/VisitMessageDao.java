package dao;

import java.util.List;

import bean.VisitMessage;

public interface VisitMessageDao {

    //��Ӵ�ʱ��
    boolean addLandTimeMes(int uid, int gid, String uname, String gname, String gtype) throws Exception;

    //����˳�ʱ��
    boolean addCancelTimeMes(int uid, int gid, String landtime) throws Exception;

    //��ѯͣ��ʱ��
    int queryLasttime(int uid, int gid, String landtime) throws Exception;

    //��ѯ��½ʱ��
    String getVisitlandtime(int uid, int gid) throws Exception;

    //��ѯ���������Ϣ
    List<VisitMessage> getAllVisitMessage() throws Exception;

    //��ѯ�����Ϣ
    List<VisitMessage> getVisitMessage(int uid, int gid, String uname, String gname, String gtype, String landtime) throws Exception;

}
