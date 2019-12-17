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
		System.out.println(goods.getUid());
		System.out.println(goods.getGname());
		System.out.println(goods.getPhoto());
		System.out.println(goods.getType());
		System.out.println(goods.getUsage());
		System.out.println(goods.getPrice());
		System.out.println(goods.getCarriage());
		System.out.println(goods.getPaddress());
		System.out.println(goods.getDescribed());
		System.out.println(goods.getNumber());
		
		pstmt = null;
		String sql = "insert into goods(uid,gname,gphoto,types,gusage,price,carriage,paddress,described,number,pdate)value(?,?,?,?,?,?,?,?,?,?,sysdate())";
		int result = 0;
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
		String sql = "delete from goods where gid=?";
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
		int uid=0;
		ResultSet rs1 = null;
		String sql1 = "select uid from goods where gid=?";
		pstmt = this.conn.prepareStatement(sql1);
		pstmt.setInt(1, gid);
		rs1 = pstmt.executeQuery();
		if (rs1.next()) {
			uid = rs1.getInt("uid");
		}
		pstmt.close();
		rs1.close();
		
		String uname="";
		ResultSet rs2 = null;
		String sql2 = "select uname from users where uid=?";
		pstmt = this.conn.prepareStatement(sql2);
		pstmt.setInt(1, uid);
		rs2 = pstmt.executeQuery();
		if (rs2.next()) {
			uname = rs2.getString("uname");
		}		
		pstmt.close();
		rs2.close();
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
			goods = new Goods(uid, gname, number, photo, type, usage, price, carriage, pdate, paddress, described);
			goods.setGid(gid);
			goodsList.add(goods);
		}
		return goodsList;
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
			goods = new Goods(uid, gname, number, photo, type, usage, price, carriage, pdate, paddress, described);
			goods.setGid(gid);
			goodsList.add(goods);
		}
		return goodsList;
	}
}
