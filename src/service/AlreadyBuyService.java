package service;

import java.sql.SQLException;
import java.util.List;

import bean.AlreadyBuy;
import dao.AlreadyBuyDao;
import db.AlreadyBuyDaoImpl;
import db.DBConnection;

public class AlreadyBuyService implements AlreadyBuyDao {

	private DBConnection dbconn = null;

	private AlreadyBuyDao dao = null;

	public AlreadyBuyService() throws SQLException {
		this.dbconn = new DBConnection();
		this.dao = new AlreadyBuyDaoImpl(this.dbconn.getConnection());
	}

	@Override
	public boolean addBuyGoods(int uid, String sale_name, int gid, int number) throws Exception {
		if (isInt(uid) && sale_name!=null && isInt(gid) && isInt(number)) {
			return this.dao.addBuyGoods(uid, sale_name, gid, number);
		}
		return false;
	}
	
	@Override
	public boolean deleteBuyGoods(int gid) throws Exception {
		if (isInt(gid)) {
			return this.dao.deleteBuyGoods(gid);
		}
		return false;
	}

	@Override
	public List<AlreadyBuy> getAllBuyGoods(int uid) throws Exception {
		if (isInt(uid)) {
			return this.dao.getAllBuyGoods(uid);
		}
		return null;
	}
	
	@Override
	public List<AlreadyBuy> getAllBuyGoodsByGid(int gid) throws Exception {
		if (isInt(gid)) {
			return this.dao.getAllBuyGoods(gid);
		}
		return null;
	}

	public boolean isInt(int index) {
		if (index == 0) {
			return false;
		}
		String str = String.valueOf(index);
		return str.matches("[0-9]+$");
	}

}
