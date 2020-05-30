package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import bean.VisitMessage;
import dao.VisitMessageDao;

public class VisitMessageDaoImpl implements VisitMessageDao {

    private Connection conn;

    private PreparedStatement pstmt = null;

    public VisitMessageDaoImpl(Connection conn) {
        this.conn = conn;
    }

    //添加打开时间
    @Override
    public boolean addLandTimeMes(int uid, int gid, String uname, String gname, String gtype) throws Exception {
        pstmt = null;
        String sql = "insert into visitmessage(uid,gid,uname,gname,types,landtime) value(?,?,?,?,?,now());";
        int result = 0;
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, uid);
        pstmt.setInt(2, gid);
        pstmt.setString(3, uname);
        pstmt.setString(4, gname);
        pstmt.setString(5, gtype);
        result = pstmt.executeUpdate();
        pstmt.close();
        if (result == 1) {
            return true;
        }
        return false;
    }

    //添加退出时间
    @Override
    public boolean addCancelTimeMes(int uid, int gid, String landtime) throws Exception {
        int lasttime = 0;
        pstmt = null;
        String sql1 = "update visitmessage set canceltime=now()  where uid=? and gid=? and landtime=?;";
        int result;
        pstmt = this.conn.prepareStatement(sql1);
        pstmt.setInt(1, uid);
        pstmt.setInt(2, gid);
        pstmt.setString(3, landtime);
        result = pstmt.executeUpdate();
        if (result == 1) {
            pstmt = null;
            String sql2 = "select * from visitmessage where uid=? and gid=? and landtime=?;";
            ResultSet rs;
            pstmt = this.conn.prepareStatement(sql2);
            pstmt.setInt(1, uid);
            pstmt.setInt(2, gid);
            pstmt.setString(3, landtime);
            rs = pstmt.executeQuery();
            rs.next();
            String canceltime = rs.getString("canceltime");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1;
            Date date2;
            try {
                date1 = sdf.parse(landtime);
                date2 = sdf.parse(canceltime);
                lasttime = Integer.parseInt(String.valueOf((date2.getTime() - date1.getTime()) / 1000));

                pstmt = null;
                String sql3 = "update visitmessage set lasttime=? where uid=? and gid=? and landtime=?";
                pstmt = this.conn.prepareStatement(sql3);
                pstmt.setInt(1, lasttime);
                pstmt.setInt(2, uid);
                pstmt.setInt(3, gid);
                pstmt.setString(4, landtime);
                result = pstmt.executeUpdate();

                if (result == 1) {
                    return true;
                }
                return false;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //查询停留时间
    @Override
    public int queryLasttime(int uid, int gid, String landtime) throws Exception {
        int lasttime = 0;
        ResultSet rs = null;
        String sql = "select * from visitmessage where uid=? and gid=? and landtime=?;";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, uid);
        pstmt.setInt(2, gid);
        pstmt.setString(3, landtime);
        rs = pstmt.executeQuery();
        rs.next();
        lasttime = rs.getInt("lasttime");
        return lasttime;
    }

    //查询登陆时间
    @Override
    public String getVisitlandtime(int uid, int gid) throws Exception {
        String landtime = null;
        ResultSet rs = null;
        String sql = "select * from visitmessage where uid=? and gid=? order by vmid desc limit 1;";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, uid);
        pstmt.setInt(2, gid);
        rs = pstmt.executeQuery();
        rs.next();
        landtime = rs.getString("landtime");
        return landtime;
    }

    // 查询所有浏览信息
    @Override
    public List<VisitMessage> getAllVisitMessage() throws Exception {
        List<VisitMessage> visitmessageList = new ArrayList<>();
        VisitMessage visitmessage = null;
        String sql = "select * from visitmessage order by vmid desc;";
        Statement st = (Statement) conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            visitmessage = new VisitMessage();
            int lmid_t = rs.getInt("vmid");
            int uid_t = rs.getInt("uid");
            int gid_t = rs.getInt("gid");
            String uname_t = rs.getString("uname");
            String gname_t = rs.getString("gname");
            String gtype_t = rs.getString("types");
            String landtime_t = rs.getString("landtime");
            String canceltime_t = rs.getString("canceltime");
            int lasttime_t = rs.getInt("lasttime");
            visitmessage.setVmid(lmid_t);
            visitmessage.setUid(uid_t);
            visitmessage.setGid(gid_t);
            visitmessage.setUname(uname_t);
            visitmessage.setGname(gname_t);
            visitmessage.setTypes(gtype_t);
            visitmessage.setLandtime(landtime_t);
            visitmessage.setCanceltime(canceltime_t);
            visitmessage.setLasttime(lasttime_t);
            visitmessageList.add(visitmessage);
        }
        return visitmessageList;
    }


    //查询登陆信息
    @Override
    public List<VisitMessage> getVisitMessage(int uid, int gid, String uname, String gname, String gtype, String landtime) throws Exception {
        List<VisitMessage> visitmessageList = new ArrayList<>();
        VisitMessage visitmessage;
        ResultSet rs;
        String sql;
        if (Objects.equals(landtime, "%&ALL&%")) {
            sql = "select * from visitmessage where if(?=-1,1=1,uid=?) and if(?='%&ALL&%',1=1,uname like ?) and if(?=-1,1=1,gid=?) and if(?='%&ALL&%',1=1,gname like ?) and if(?='全部',1=1,types=?) order by vmid desc;";
            pstmt = this.conn.prepareStatement(sql);
        } else {
            sql = "select * from visitmessage where if(?=-1,1=1,uid=?) and if(?='%&ALL&%',1=1,uname like ?) and if(?=-1,1=1,gid=?) and if(?='%&ALL&%',1=1,gname like ?) and if(?='全部',1=1,types=?) and Date(landtime)=? order by vmid desc;";
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setString(11, landtime);
        }
        pstmt.setInt(1, uid);
        pstmt.setInt(2, uid);
        pstmt.setString(3, uname);
        pstmt.setString(4, '%' + uname + '%');
        pstmt.setInt(5, gid);
        pstmt.setInt(6, gid);
        pstmt.setString(7, gname);
        pstmt.setString(8, '%' + gname + '%');
        pstmt.setString(9, gtype);
        pstmt.setString(10, gtype);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            visitmessage = new VisitMessage();
            visitmessage.setVmid(rs.getInt("vmid"));
            visitmessage.setUid(rs.getInt("uid"));
            visitmessage.setGid(rs.getInt("gid"));
            visitmessage.setUname(rs.getString("uname"));
            visitmessage.setGname(rs.getString("gname"));
            visitmessage.setTypes(rs.getString("types"));
            visitmessage.setLandtime(rs.getString("landtime"));
            visitmessage.setCanceltime(rs.getString("canceltime"));
            visitmessage.setLasttime(rs.getInt("lasttime"));
            visitmessageList.add(visitmessage);
        }
        return visitmessageList;
    }


}
