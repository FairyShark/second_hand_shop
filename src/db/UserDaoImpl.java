package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.User;
import dao.UserDao;

public class UserDaoImpl implements UserDao {

    private Connection conn = null;

    private PreparedStatement pstmt = null;

    public UserDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean addUser(User user) throws Exception {
        pstmt = null;
        String sql = "insert into users(uname,passwd,email,lastLogin)value(?,?,?,sysdate());";
        int result = 0;
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setString(1, user.getUname());
        pstmt.setString(2, user.getPasswd());
        pstmt.setString(3, user.getEmail());
        result = pstmt.executeUpdate();
        pstmt.close();
        if (result == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean editEmail(int uid, String email) throws Exception {
        User user = queryByEmail(email);
        if (user != null && user.getUid() != uid) {
            return false;
        }
        pstmt = null;
        String sql = "update users set email=? where uid=?";
        int result = 0;
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setString(1, email);
        pstmt.setInt(2, uid);
        result = pstmt.executeUpdate();
        pstmt.close();
        if (result == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean editLoginTime(int uid) throws Exception {
        pstmt = null;
        String sql = "update users set lastLogin=sysdate() where uid=?";
        int result = 0;
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, uid);
        result = pstmt.executeUpdate();
        pstmt.close();
        if (result == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean editPasswd(int uid, String passwd) throws Exception {
        String sql = "update users set passwd=? where uid=" + uid;
        int result = 0;
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setString(1, passwd);
        result = pstmt.executeUpdate();
        pstmt.close();
        if (result == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(int uid) throws Exception {
        String sql = "delete from Users where uid=?";
        int result = 0;
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, uid);
        result = pstmt.executeUpdate();
        pstmt.close();
        if (result == 1) {
            return true;
        }
        return false;
    }

    @Override
    public String queryUName(int uid) throws Exception {
        String uname = "";
        ResultSet rs = null;
        String sql = "select uname from users where uid=?";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, uid);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            uname = rs.getString("uname");
        }
        pstmt.close();
        rs.close();
        return uname;
    }

    @Override
    public User queryByName(String uname) throws Exception {
        User user = null;
        ResultSet rs = null;
        String sql = "select * from users where uname =?";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setString(1, uname);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            user = new User();
            int uid = rs.getInt("uid");
            String passwd = rs.getString("passwd");
            String email = rs.getString("email");
            user.setUid(uid);
            user.setUname(uname);
            user.setPasswd(passwd);
            user.setEmail(email);
            String date = rs.getDate("lastlogin").toString();
            String time = rs.getTime("lastlogin").toString();
            user.setLastLogin(date + " " + time);
        }
        pstmt.close();
        rs.close();
        return user;
    }

    @Override
    public User queryByUid(int uid) throws Exception {
        User user = null;
        ResultSet rs = null;
        String sql = "select * from users where uid =?";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, uid);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            user = new User();
            String uname = rs.getString("uname");
            String passwd = rs.getString("passwd");
            String email = rs.getString("email");
            String sex = rs.getString("sex");
            String addr = rs.getString("addr");
            String tel = rs.getString("tel");
            String birth = rs.getString("birth");
            int frequency = rs.getInt("frequency");
            user.setFrequency(frequency);
            user.setUid(uid);
            user.setUname(uname);
            user.setPasswd(passwd);
            user.setEmail(email);
            user.setSex(sex);
            user.setBirth(birth);
            user.setTel(tel);
            user.setAddr(addr);
            String date = rs.getDate("lastlogin").toString();
            String time = rs.getTime("lastlogin").toString();
            user.setLastLogin(date + " " + time);
        }
        pstmt.close();
        rs.close();
        return user;
    }

    @Override
    public User queryByEmail(String email) throws Exception {
        User user = null;
        ResultSet rs = null;
        String sql = "select * from users where email=?";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setString(1, email);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            user = new User();
            int uid = rs.getInt("uid");
            String uname = rs.getString("uname");
            String passwd = rs.getString("passwd");
            user.setUid(uid);
            user.setUname(uname);
            user.setPasswd(passwd);
            user.setEmail(email);
            String date = rs.getDate("lastlogin").toString();
            String time = rs.getTime("lastlogin").toString();
            user.setLastLogin(date + " " + time);
        }
        pstmt.close();
        rs.close();
        return user;
    }

    @Override
    public List<User> selectAllUser() throws Exception {
        List<User> userList = new ArrayList<User>();
        User user;
        ResultSet rs = null;
        Statement st = null;
        st = (Statement) conn.createStatement();
        String sql = "select * from users order by uid;";
        rs = st.executeQuery(sql);
        while (rs.next()) {
            int uid_t = rs.getInt("uid");
            String uname_t = rs.getString("uname");
            String umail_t = rs.getString("email");
            String passwd_t = rs.getString("passwd");
            String lastlogin_t = rs.getString("lastLogin");
            int frequency = rs.getInt("frequency");
            user = new User();
            user.setUid(uid_t);
            user.setUname(uname_t);
            user.setPasswd(passwd_t);
            user.setEmail(umail_t);
            user.setLastLogin(lastlogin_t);
            user.setFrequency(frequency);
            userList.add(user);
        }
        return userList;
    }

    @Override
    public List<User> selectUserList(int uid, String uname, String umail) throws Exception {
        List<User> userList = new ArrayList<User>();
        User user;
        ResultSet rs = null;
        String sql = "select * from users where if(?=-1,1=1,uid=?) and if(?='%&ALL&%',1=1,uname like ?) and if(?='%&ALL&%',1=1,email=?);";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, uid);
        pstmt.setInt(2, uid);
        pstmt.setString(3, uname);
        pstmt.setString(4, '%' + uname + '%');
        pstmt.setString(5, umail);
        pstmt.setString(6, umail);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            int uid_t = rs.getInt("uid");
            String uname_t = rs.getString("uname");
            String umail_t = rs.getString("email");
            String passwd_t = rs.getString("passwd");
            String lastlogin_t = rs.getString("lastlogin");
            int frequency = rs.getInt("frequency");
            user = new User();
            user.setUid(uid_t);
            user.setUname(uname_t);
            user.setPasswd(passwd_t);
            user.setEmail(umail_t);
            user.setLastLogin(lastlogin_t);
            user.setFrequency(frequency);
            userList.add(user);
        }
        return userList;
    }
    
    @Override
    public boolean editFrequency(int uid) throws Exception {
        pstmt = null;
        String sql = "select count(*) from landmessage where DATE_SUB(CURDATE(), INTERVAL  1 MONTH) <= date(landtime) and uid=?";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, uid);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            int frequency = rs.getInt(1);

            pstmt = null;
            sql = "update users set frequency=? where uid=?";
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1, frequency);
            pstmt.setInt(2, uid);
            int result = pstmt.executeUpdate();
            pstmt.close();
            if (result == 1) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean editUser(int uid, String sex, String tel, String birth, String addr) throws Exception {
        String sql = "update users set sex=?,tel=?,birth=?,addr=? where uid=?";
        int result = 0;
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setString(1, sex);
        pstmt.setString(2, tel);
        pstmt.setString(3, birth);
        pstmt.setString(4, addr);
        pstmt.setInt(5, uid);
        result = pstmt.executeUpdate();
        pstmt.close();
        if (result == 1) {
            return true;
        }
        return false;
    }

}
