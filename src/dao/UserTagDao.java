package dao;

import bean.UserTag;

import java.util.List;

public interface UserTagDao {

    //����û���ǩ
    public boolean addUserTag(int uid, String uname, String acttype, String tagtype, int tagweight) throws Exception;

    //��ѯ���в�����־
    public List<UserTag> getAllUserTag() throws Exception;

    //��ѯ������־
    public List<UserTag> getUserTag(int uid, String uname, String acttype, String tagtype, String tagtime) throws Exception;


}
