package dao;

import bean.UserTag;

import java.util.List;

public interface UserTagDao {

    //添加用户标签
    public boolean addUserTag(int uid, String uname, String acttype, String tagtype, int tagweight) throws Exception;

    //查询所有操作日志
    public List<UserTag> getAllUserTag() throws Exception;

    //查询操作日志
    public List<UserTag> getUserTag(int uid, String uname, String acttype, String tagtype, String tagtime) throws Exception;
    
}
