package service;

import java.util.List;

import bean.User;
import dao.UserDao;
import db.DBConnection;
import db.UserDaoImpl;

public class UserService implements UserDao {

    private UserDao dao;

    public UserService() throws Exception {
        DBConnection dbconn = new DBConnection();
        this.dao = new UserDaoImpl(dbconn.getConnection());
    }

    @Override
    public boolean addUser(User user) throws Exception {
        boolean flag = false;
        if (user != null && this.dao.queryByName(user.getUname()) == null
                && this.dao.queryByEmail(user.getEmail()) == null) {
            flag = this.dao.addUser(user);
        }
        return flag;
    }

    @Override
    public boolean editEmail(int uid, String email) throws Exception {
        if (isInt(uid) && email != null) {
            return this.dao.editEmail(uid, email);
        }
        return false;
    }

    @Override
    public boolean editLoginTime(int uid) throws Exception {
        if (isInt(uid)) {
            return this.dao.editLoginTime(uid);
        }
        return false;
    }

    @Override
    public boolean editPasswd(int uid, String passwd) throws Exception {
        if (isInt(uid) && passwd != null) {
            return this.dao.editPasswd(uid, passwd);
        }
        return false;
    }

    @Override
    public boolean deleteUser(int uid) throws Exception {
        if (isInt(uid)) {
            return this.dao.deleteUser(uid);
        }
        return false;
    }

    @Override
    public String queryUName(int uid) throws Exception {
        if (isInt(uid)) {
            return this.dao.queryUName(uid);
        }
        return null;
    }

    @Override
    public User queryByName(String uname) throws Exception {
        if (uname != null) {
            return this.dao.queryByName(uname);
        }
        return null;
    }

    @Override
    public User queryByUid(int uid) throws Exception {
        if (isInt(uid)) {
            return this.dao.queryByUid(uid);
        }
        return null;
    }

    @Override
    public User queryByEmail(String email) throws Exception {
        if (email != null) {
            return this.dao.queryByEmail(email);
        }
        return null;
    }

    @Override
    public List<User> selectAllUser() throws Exception {
        return this.dao.selectAllUser();
    }

    @Override
    public List<User> selectUserList(int uid, String uname, String umail) throws Exception {
        return this.dao.selectUserList(uid, uname, umail);
    }

    @Override
    public boolean editUser(int uid, String sex, String tel, String birth, String addr) throws Exception {
        if (isInt(uid) && sex != null && tel != null && birth != null && addr != null) {
            return this.dao.editUser(uid, sex, tel, birth, addr);
        }
        return false;
    }

    public boolean isInt(int index) {
        if (index == 0) {
            return false;
        }
        String str = String.valueOf(index);
        return str.matches("[0-9]+$");
    }

}
