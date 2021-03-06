package service;

import bean.UserTag;
import dao.UserTagDao;
import db.DBConnection;
import db.UserTagDaoImpl;

import java.sql.SQLException;
import java.util.List;

public class UserTagService implements UserTagDao {

    private UserTagDao dao;

    public UserTagService() throws SQLException {
        DBConnection dbconn = new DBConnection();
        this.dao = new UserTagDaoImpl(dbconn.getConnection());
    }

    //添加操作信息
    @Override
    public boolean addUserTag(int uid, String uname, String acttype, String tagtype, int tagweight) throws Exception {
        if (isInt(uid) && uname != null && acttype != null && tagtype != null) {
            return this.dao.addUserTag(uid, uname, acttype, tagtype, tagweight);
        }
        return false;
    }

    //查询操作日志
    @Override
    public List<UserTag> getAllUserTag() throws Exception {
        return this.dao.getAllUserTag();
    }

    //查询浏览信息
    @Override
    public List<UserTag> getUserTag(int uid, String uname, String acttype, String tagtype, String tagtime) throws Exception {
        return this.dao.getUserTag(uid, uname, acttype, tagtype, tagtime);
    }
    
    public boolean isInt(int index) {
        if (index == 0) {
            return false;
        }
        String str = String.valueOf(index);
        return str.matches("[0-9]+$");
    }

}
