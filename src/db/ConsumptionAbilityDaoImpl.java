package db;

import bean.ConsumptionAbility;
import dao.ConsumptionAbilityDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ConsumptionAbilityDaoImpl implements ConsumptionAbilityDao {

    private Connection conn;

    private PreparedStatement pstmt = null;

    public ConsumptionAbilityDaoImpl(Connection conn) {
        this.conn = conn;
    }

    // 添加收藏商品
    @Override
    public boolean addConsumptionAbility(int uid, String uname, int number, float price) throws Exception {
        String sql;
        pstmt = null;
        int result;
        if (this.getConsumptionAbility(uid) == null) {
            sql = "insert into consumptionability(uid,uname,pnumber,ptotal)value(?,?,?,?)";
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1, uid);
            pstmt.setString(2, uname);
            pstmt.setInt(3, number);
            pstmt.setFloat(4, price);
        } else {
            sql = "update consumptionability set pnumber=pnumber+?,ptotal=ptotal+? where uid=?";
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1, number);
            pstmt.setFloat(2, price);
            pstmt.setInt(3, uid);
        }
        result = pstmt.executeUpdate();
        pstmt.close();
        if (result == 1) {
            ConsumptionAbility ca = getConsumptionAbility(uid);
            float avg = ca.getTotal_pay() / ca.getPay_number();
            String layered = "";
            if (avg < 30) {
                layered = "[0，30]";
            } else if (avg < 50) {
                layered = "[30，50]";
            } else if (avg < 100) {
                layered = "[50，100]";
            } else if (avg < 200) {
                layered = "[100，200]";
            } else if (avg < 300) {
                layered = "[200，300]";
            } else {
                layered = "300及以上";
            }
            pstmt = null;
            sql = "update consumptionability set layered=? where uid=?";
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setString(1, layered);
            pstmt.setInt(2, uid);
            result = pstmt.executeUpdate();
            pstmt.close();
            if (result == 1) {
                return true;
            }
            return false;
        }
        return false;
    }

    // 获取所有用户的消费能力
    @Override
    public List<ConsumptionAbility> getAllConsumptionAbility() throws Exception {
        pstmt = null;
        ResultSet rs;
        List<ConsumptionAbility> cList = null;
        String sql = "select * from consumptionability order by uid";
        pstmt = this.conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        ConsumptionAbility ca;
        cList = new ArrayList<ConsumptionAbility>();
        while (rs.next()) {
            ca = new ConsumptionAbility();
            ca.setBid(rs.getInt("bid"));
            ca.setUid(rs.getInt("uid"));
            ca.setUser_name(rs.getString("uname"));
            ca.setPay_number(rs.getInt("pnumber"));
            ca.setTotal_pay(rs.getFloat("ptotal"));
            ca.setLayered(rs.getString("layered"));
            cList.add(ca);
        }
        return cList;
    }

    // 获取指定用户的消费能力
    @Override
    public ConsumptionAbility getConsumptionAbility(int uid) throws Exception {
        pstmt = null;
        ResultSet rs;
        String sql = "select * from consumptionability where uid=?";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, uid);
        rs = pstmt.executeQuery();
        ConsumptionAbility ca = null;
        if (rs.next()) {
            ca = new ConsumptionAbility();
            ca.setBid(rs.getInt("bid"));
            ca.setUid(uid);
            ca.setUser_name(rs.getString("uname"));
            ca.setPay_number(rs.getInt("pnumber"));
            ca.setTotal_pay(rs.getFloat("ptotal"));
            ca.setLayered(rs.getString("layered"));
            return ca;
        }
        return null;
    }

}
