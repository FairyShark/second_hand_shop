package db;

import bean.UserTag;
import dao.UserTagDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserTagDaoImpl implements UserTagDao {

    private Connection conn = null;

    private PreparedStatement pstmt = null;

    public UserTagDaoImpl(Connection conn) {
        this.conn = conn;
    }

    //添加操作信息
    @Override
    public boolean addUserTag(int uid, String uname, String acttype, String tagtype, int tagweight) throws Exception {
        pstmt = null;
        String sql = "insert into usertag(uid,uname,acttype,tagtype,tagtime,tagweight) value(?,?,?,?,now(),?);";
        int result = 0;
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, uid);
        pstmt.setString(2, uname);
        pstmt.setString(3, acttype);
        pstmt.setString(4, tagtype);
        pstmt.setInt(5, tagweight);
        result = pstmt.executeUpdate();
        pstmt.close();
        if (result == 1) {
            return true;
        }
        return false;
    }

    // 查询所有操作日志
    @Override
    public List<UserTag> getAllUserTag() throws Exception {
        List<UserTag> usertaglist = new ArrayList<>();
        UserTag usertag = null;
        String sql = "select * from usertag order by tid desc;";
        Statement st = (Statement) conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            usertag = new UserTag();
            int tid_t = rs.getInt("tid");
            int uid_t = rs.getInt("uid");
            String uname_t = rs.getString("uname");
            String acttype_t = rs.getString("acttype");
            String tagtype_t = rs.getString("tagtype");
            String tagtime_t = rs.getString("tagtime");
            usertag.setTid(tid_t);
            usertag.setUid(uid_t);
            usertag.setUname(uname_t);
            usertag.setActtype(acttype_t);
            usertag.setTagtype(tagtype_t);
            usertag.setTagtime(tagtime_t);
            usertaglist.add(usertag);
        }
        return usertaglist;
    }

    //查询浏览信息
    @Override
    public List<UserTag> getUserTag(int uid, String uname, String acttype, String tagtype, String tagtime) throws Exception {
        List<UserTag> usertaglist = new ArrayList<>();
        UserTag usertag = null;
        ResultSet rs = null;
        String sql = null;
        if (Objects.equals(tagtime, "%&ALL&%")) {
            sql = "select * from usertag where if(?=-1,1=1,uid=?) and if(?='%&ALL&%',1=1,uname like ?) and if(?='全部',1=1,acttype=?) and if(?='全部',1=1,tagtype=?) order by tid desc;";
            pstmt = this.conn.prepareStatement(sql);
        } else {
            sql = "select * from usertag where if(?=-1,1=1,uid=?) and if(?='%&ALL&%',1=1,uname like ?) and if(?='全部',1=1,acttype=?) and if(?='全部',1=1,tagtype=?) and Date(tagtime)=? order by tid desc;";
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setString(9, tagtime);
        }
        pstmt.setInt(1, uid);
        pstmt.setInt(2, uid);
        pstmt.setString(3, uname);
        pstmt.setString(4, '%' + uname + '%');
        pstmt.setString(5, acttype);
        pstmt.setString(6, acttype);
        pstmt.setString(7, tagtype);
        pstmt.setString(8, tagtype);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            usertag = new UserTag();
            usertag.setTid(rs.getInt("tid"));
            usertag.setUid(rs.getInt("uid"));
            usertag.setUname(rs.getString("uname"));
            usertag.setActtype(rs.getString("acttype"));
            usertag.setTagtype(rs.getString("tagtype"));
            usertag.setTagtime(rs.getString("tagtime"));
            usertaglist.add(usertag);
        }
        return usertaglist;
    }

}
