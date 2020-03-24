package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Goods;
import dao.GoodsDao;

import java.sql.Statement;

public class GoodsDaoImpl implements GoodsDao {

    private Connection conn = null;

    private PreparedStatement pstmt = null;

    public GoodsDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean addGoods(Goods goods) throws Exception {
        pstmt = null;
        String sql = "insert into goods(uid,uname,gname,gphoto,types,gusage,price,carriage,paddress,described,number,pdate)value(?,?,?,?,?,?,?,?,?,?,?,sysdate())";
        int result = 0;
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, goods.getUid());
        pstmt.setString(2, goods.getUname());
        pstmt.setString(3, goods.getGname());
        pstmt.setString(4, goods.getPhoto());
        pstmt.setString(5, goods.getType());
        pstmt.setString(6, goods.getUsage());
        pstmt.setFloat(7, goods.getPrice());
        pstmt.setFloat(8, goods.getCarriage());
        pstmt.setString(9, goods.getPaddress());
        pstmt.setString(10, goods.getDescribed());
        pstmt.setInt(11, goods.getNumber());
        result = pstmt.executeUpdate();
        pstmt.close();
        if (result == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean editInfo(Goods goods) throws Exception {
        pstmt = null;
        String sql = "update goods set gname=?,types=?,gusage=?,price=?,carriage=?,paddress=?,described=?,number=? where gid="
                + goods.getGid();
        int result = 0;
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setString(1, goods.getGname());
        pstmt.setString(2, goods.getType());
        pstmt.setString(3, goods.getUsage());
        pstmt.setFloat(4, goods.getPrice());
        pstmt.setFloat(5, goods.getCarriage());
        pstmt.setString(6, goods.getPaddress());
        pstmt.setString(7, goods.getDescribed());
        pstmt.setInt(8, goods.getNumber());
        result = pstmt.executeUpdate();
        pstmt.close();
        if (result == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addPho(int gid, String pname) throws Exception {
        pstmt = null;
        String sql = "update goods set gphoto=? where gid=" + gid;
        int result = 0;
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setString(1, pname);
        result = pstmt.executeUpdate();
        pstmt.close();
        if (result == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteGoods(int gid) throws Exception {
        String sql = "update goods set del=0 where gid=?";
        int result = 0;
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, gid);
        result = pstmt.executeUpdate();
        pstmt.close();
        if (result == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Goods queryById(int gid) throws Exception {
        Goods goods = null;
        ResultSet rs = null;
        String sql = "select * from goods where gid =?";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, gid);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            goods = new Goods();
            goods.setGid(rs.getInt("gid"));
            goods.setUid(rs.getInt("uid"));
            goods.setGname(rs.getString("gname"));
            goods.setNumber(rs.getInt("number"));
            goods.setPhoto(rs.getString("gphoto"));
            goods.setType(rs.getString("types"));
            goods.setUsage(rs.getString("gusage"));
            goods.setPrice(rs.getFloat("price"));
            goods.setCarriage(rs.getFloat("carriage"));
            goods.setPdate(rs.getString("pdate"));
            goods.setPaddress(rs.getString("paddress"));
            goods.setDescribed(rs.getString("described"));
            goods.setDel(rs.getInt("del"));
        }
        pstmt.close();
        rs.close();
        return goods;
    }

    @Override
    public Goods queryByUid(int uid) throws Exception {
        Goods goods = null;
        ResultSet rs = null;
        String sql = "select * from goods where uid =?";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, uid);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            goods = new Goods();
            goods.setGid(rs.getInt("gid"));
            goods.setUid(rs.getInt("uid"));
            goods.setGname(rs.getString("gname"));
            goods.setNumber(rs.getInt("number"));
            goods.setPhoto(rs.getString("gphoto"));
            goods.setType(rs.getString("types"));
            goods.setUsage(rs.getString("gusage"));
            goods.setPrice(rs.getFloat("price"));
            goods.setCarriage(rs.getFloat("carriage"));
            goods.setPdate(rs.getString("pdate"));
            goods.setPaddress(rs.getString("paddress"));
            goods.setDescribed(rs.getString("described"));
            goods.setDel(rs.getInt("del"));
        }
        pstmt.close();
        rs.close();
        return goods;
    }

    @Override
    public int queryNumberById(int gid) throws Exception {
        ResultSet rs = null;
        String sql = "select number from goods where gid =?";
        int number = 0;
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, gid);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            number = rs.getInt("number");
        }
        pstmt.close();
        rs.close();
        return number;
    }

    @Override
    public int queryGid(Goods goods) throws Exception {
        ResultSet rs = null;
        String sql = "select gid from goods where uid=? and gname=? and gphoto=? and types=? and gusage=? and price=? and carriage=? and paddress=? and described=? and number=?;";
        int gid = 0;
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, goods.getUid());
        pstmt.setString(2, goods.getGname());
        pstmt.setString(3, goods.getPhoto());
        pstmt.setString(4, goods.getType());
        pstmt.setString(5, goods.getUsage());
        pstmt.setFloat(6, goods.getPrice());
        pstmt.setFloat(7, goods.getCarriage());
        pstmt.setString(8, goods.getPaddress());
        pstmt.setString(9, goods.getDescribed());
        pstmt.setInt(10, goods.getNumber());
        rs = pstmt.executeQuery();
        if (rs.next()) {
            gid = rs.getInt("gid");
        }
        pstmt.close();
        rs.close();
        return gid;
    }

    @Override
    public String queryPho(int gid) throws Exception {
        ResultSet rs = null;
        String sql = "select gphoto from goods where gid=?";
        String Gphoto = "nophoto.jpg";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, gid);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            Gphoto = rs.getString("gphoto");
        }
        pstmt.close();
        rs.close();
        return Gphoto;
    }

    @Override
    public String queryUName(int gid) throws Exception {
        String uname = null;
        ResultSet rs = null;
        String sql = "select uname from goods where gid=?";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, gid);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            uname = rs.getString("uname");
        }
        pstmt.close();
        rs.close();
        return uname;
    }

    @Override
    public int querySaleUid(int gid) throws Exception {
        ResultSet rs = null;
        String sql = "select uid from goods where gid =?";
        int uid = 0;
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, gid);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            uid = rs.getInt("uid");
        }
        pstmt.close();
        rs.close();
        return uid;
    }

    @Override
    public List<Goods> getLatestGoods(int gid, String type) throws Exception {
        List<Goods> goodsList = new ArrayList<Goods>();
        Goods goods;
        ResultSet rs = null;
        String sql = "select gid,gname,price,gphoto from goods where gid != ? and types=? order by gid desc limit 4";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, gid);
        pstmt.setString(2, type);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            goods = new Goods();
            goods.setGid(rs.getInt("gid"));
            goods.setGname(rs.getString("gname"));
            goods.setPrice(rs.getFloat("price"));
            goods.setPhoto(rs.getString("gphoto"));
            goodsList.add(goods);
        }
        return goodsList;
    }

    @Override
    public List<Goods> getAllGoods() throws Exception {
        String sql = "select * from goods order by gid desc";
        Statement st = null;
        ResultSet rs = null;
        st = (Statement) conn.createStatement();
        rs = st.executeQuery(sql);
        List<Goods> goodsList = new ArrayList<Goods>();
        Goods goods;
        while (rs.next()) {
            int gid = rs.getInt("gid");
            int uid = rs.getInt("uid");
            String uname = rs.getString("uname");
            String gname = rs.getString("gname");
            int number = rs.getInt("number");
            String photo = rs.getString("gphoto");
            String type = rs.getString("types");
            String usage = rs.getString("gusage");
            float price = rs.getFloat("price");
            float carriage = rs.getFloat("carriage");
            String pdate = rs.getDate("pdate").toString();
            String paddress = rs.getString("paddress");
            String described = rs.getString("described");
            goods = new Goods(uid, uname, gname, number, photo, type, usage, price, carriage, pdate, paddress, described);
            goods.setGid(gid);
            goods.setDel(rs.getInt("del"));
            goodsList.add(goods);
        }
        return goodsList;
    }

    @Override
    public String queryTypesByGid(int gid) throws Exception {
        ResultSet rs = null;
        String sql = "select types from goods where gid =?";
        String gtype = null;
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, gid);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            gtype = rs.getString("types");
        }
        pstmt.close();
        rs.close();
        return gtype;
    }

    @Override
    public String[] queryTypes() throws Exception {
        String sql = "select distinct types from goods";
        Statement st = null;
        ResultSet rs = null;
        st = (Statement) conn.createStatement();
        rs = st.executeQuery(sql);
        String[] types = new String[10];
        int i = 0;
        while (rs.next()) {
            types[i] = rs.getString("types");
            i++;
        }
        return types;
    }

    @Override
    public List<Goods> getTypeGoodsList(String type) throws Exception {
        List<Goods> goodsList = new ArrayList<Goods>();
        Goods goods;
        ResultSet rs = null;
        String sql = "select gid,gname from goods where types=? order by gid desc;";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setString(1, type);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            goods = new Goods();
            goods.setGid(rs.getInt("gid"));
            goods.setGname(rs.getString("gname"));
            goodsList.add(goods);
        }
        return goodsList;
    }

    @Override
    public List<Goods> getUidGoodsList(int uid) throws Exception {
        List<Goods> goodsList = new ArrayList<Goods>();
        Goods goods;
        ResultSet rs = null;
        String sql = "select * from goods where uid=? order by gid desc;";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, uid);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            int gid = rs.getInt("gid");
            String uname = rs.getString("uname");
            String gname = rs.getString("gname");
            int number = rs.getInt("number");
            String photo = rs.getString("gphoto");
            String type = rs.getString("types");
            String usage = rs.getString("gusage");
            float price = rs.getFloat("price");
            float carriage = rs.getFloat("carriage");
            String pdate = rs.getDate("pdate").toString();
            String paddress = rs.getString("paddress");
            String described = rs.getString("described");
            goods = new Goods(uid, uname, gname, number, photo, type, usage, price, carriage, pdate, paddress, described);
            goods.setGid(gid);
            goods.setDel(rs.getInt("del"));
            goodsList.add(goods);
        }
        return goodsList;
    }

    @Override
    public List<Goods> selectGoodsList(int uid, String type, String usage, int lowp, int highp, String gname) throws Exception {
        List<Goods> goodsList = new ArrayList<Goods>();
        Goods goods;
        ResultSet rs = null;
        String sql = "select * from goods where if(?=8,1=1,uid=?) and if(?='全部',1=1,types=?) and if(?='全部',1=1,gusage=?) and if(?='%&ALL&%',1=1,gname like ?) and (price between ? and ?);";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, uid);
        pstmt.setInt(2, uid);
        pstmt.setString(3, type);
        pstmt.setString(4, type);
        pstmt.setString(5, usage);
        pstmt.setString(6, usage);
        pstmt.setString(7, gname);
        pstmt.setString(8, '%' + gname + '%');
        pstmt.setInt(9, lowp);
        pstmt.setInt(10, highp);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            int uid_t = rs.getInt("uid");
            int gid = rs.getInt("gid");
            String uname = rs.getString("uname");
            String gname_t = rs.getString("gname");
            int number = rs.getInt("number");
            String photo = rs.getString("gphoto");
            String type_t = rs.getString("types");
            String usage_t = rs.getString("gusage");
            float price = rs.getFloat("price");
            float carriage = rs.getFloat("carriage");
            String pdate = rs.getDate("pdate").toString();
            String paddress = rs.getString("paddress");
            String described = rs.getString("described");
            goods = new Goods(uid_t, uname, gname_t, number, photo, type_t, usage_t, price, carriage, pdate, paddress, described);
            goods.setGid(gid);
            goods.setDel(rs.getInt("del"));
            goodsList.add(goods);
        }
        return goodsList;
    }
}
