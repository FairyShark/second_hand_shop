package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import bean.OperationMes;
import dao.OperationMesDao;

public class OperationMesDaoImpl implements OperationMesDao {

    private Connection conn = null;

    private PreparedStatement pstmt = null;

    public OperationMesDaoImpl(Connection conn) {
        this.conn = conn;
    }

    //添加操作信息
    @Override
    public boolean addOperationMes(int uid, String uname, String userip, String otype, String opcontent) throws Exception {
        pstmt = null;
        String sql = "insert into operationmes(uid,uname,userip,otype,opcontent,operationtime) value(?,?,?,?,?,now());";
        int result = 0;
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, uid);
        pstmt.setString(2, uname);
        pstmt.setString(3, userip);
        pstmt.setString(4, otype);
        pstmt.setString(5, opcontent);
        result = pstmt.executeUpdate();
        pstmt.close();
        if (result == 1) {
            return true;
        }
        return false;
    }

    // 查询所有操作日志
    @Override
    public List<OperationMes> getAllOperationMes() throws Exception {
        List<OperationMes> operationmeslist = new ArrayList<>();
        OperationMes operationmes = null;
        String sql = "select * from operationmes order by omid desc;";
        Statement st = (Statement) conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            operationmes = new OperationMes();
            int omid_t = rs.getInt("omid");
            int uid_t = rs.getInt("uid");
            String uname_t = rs.getString("uname");
            String userip_t = rs.getString("userip");
            String otype_t = rs.getString("otype");
            String opcontent_t = rs.getString("opcontent");
            String landtime_t = rs.getString("operationtime");
            operationmes.setOmid(omid_t);
            operationmes.setUid(uid_t);
            operationmes.setUname(uname_t);
            operationmes.setUserip(userip_t);
            operationmes.setOtype(otype_t);
            operationmes.setOpcontent(opcontent_t);
            operationmes.setOperationtime(landtime_t);
            operationmeslist.add(operationmes);
        }
        return operationmeslist;
    }

    //查询浏览信息
    @Override
    public List<OperationMes> getOperationMes(int uid, String uname, String userip, String otype, String landtime) throws Exception {
        List<OperationMes> operationmesList = new ArrayList<OperationMes>();
        OperationMes operationmes = null;
        ResultSet rs = null;
        String sql = null;
        if (Objects.equals(landtime, "%&ALL&%")) {
            sql = "select * from operationmes where if(?=-1,1=1,uid=?) and if(?='%&ALL&%',1=1,uname like ?) and if(?='%&ALL&%',1=1,userip=?) and if(?='全部',1=1,otype=?) order by omid desc;";
            pstmt = this.conn.prepareStatement(sql);
        } else {
            sql = "select * from operationmes where if(?=-1,1=1,uid=?) and if(?='%&ALL&%',1=1,uname like ?) and if(?='%&ALL&%',1=1,userip=?) and if(?='全部',1=1,otype=?) and Date(operationtime)=? order by omid desc;";
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setString(9, landtime);
        }
        pstmt.setInt(1, uid);
        pstmt.setInt(2, uid);
        pstmt.setString(3, uname);
        pstmt.setString(4, uname);
        pstmt.setString(5, userip);
        pstmt.setString(6, userip);
        pstmt.setString(7, otype);
        pstmt.setString(8, otype);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            operationmes = new OperationMes();
            operationmes.setOmid(rs.getInt("omid"));
            operationmes.setUid(rs.getInt("uid"));
            operationmes.setUname(rs.getString("uname"));
            operationmes.setUserip(rs.getString("userip"));
            operationmes.setOtype(rs.getString("otype"));
            operationmes.setOpcontent(rs.getString("opcontent"));
            operationmes.setOperationtime(rs.getString("operationtime"));
            operationmesList.add(operationmes);
        }
        return operationmesList;
    }

}
