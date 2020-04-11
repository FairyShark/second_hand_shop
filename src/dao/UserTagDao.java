package dao;

import bean.UserTag;

import java.util.List;

public interface UserTagDao {

    //����û���ǩ
    boolean addUserTag(int uid, String uname, String acttype, String tagtype, int tagweight) throws Exception;

    //��ѯ���в�����־
    List<UserTag> getAllUserTag() throws Exception;

    //��ѯ������־
    List<UserTag> getUserTag(int uid, String uname, String acttype, String tagtype, String tagtime) throws Exception;
    
}
