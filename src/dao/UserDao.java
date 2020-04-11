package dao;

import java.util.List;

import bean.User;

public interface UserDao {

    // 添加用户
    boolean addUser(User user) throws Exception;

    // 修改邮箱
    boolean editEmail(int uid, String email) throws Exception;

    // 修改密码
    boolean editPasswd(int uid, String passwd) throws Exception;

    // 修改最后登录时间
    boolean editLoginTime(int uid) throws Exception;

    // 删除用户
    boolean deleteUser(int uid) throws Exception;

    // 通过id查找用户名
    String queryUName(int uid) throws Exception;

    // 通过用户名查找用户
    User queryByName(String uname) throws Exception;

    // 通过用户id查找用户
    User queryByUid(int uid) throws Exception;

    // 通过email查找用户
    User queryByEmail(String email) throws Exception;

    // 查找所有用户
    List<User> selectAllUser() throws Exception;

    // 搜索用户
    List<User> selectUserList(int uid, String uname, String umail) throws Exception;

    // 修改近一个月登陆次数
    boolean editFrequency(int uid) throws Exception;

    // 修改其他信息
    boolean editUser(int uid, String sex, String tel, String birth, String addr) throws Exception;

}
