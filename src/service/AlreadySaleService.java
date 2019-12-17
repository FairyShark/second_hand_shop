package service;

import java.sql.SQLException;
import java.util.List;

import bean.AlreadySale;
import dao.AlreadySaleDao;
import db.AlreadySaleDaoImpl;
import db.DBConnection;

public class AlreadySaleService implements AlreadySaleDao {

	private DBConnection dbconn = null;

	private AlreadySaleDao dao = null;

	public AlreadySaleService() throws SQLException {
		this.dbconn = new DBConnection();
		this.dao = new AlreadySaleDaoImpl(this.dbconn.getConnection());
	}

	@Override
	public boolean addSaleGoods(int uid, int buy_uid, int gid, int number) throws Exception {
		if (isInt(uid) && isInt(buy_uid) && isInt(gid) && isInt(number)) {
			return this.dao.addSaleGoods(uid, buy_uid, gid, number);
		}
		return false;
	}

	@Override
	public List<AlreadySale> getAllSaleGoods(int uid) throws Exception {
		if (isInt(uid)) {
			return this.dao.getAllSaleGoods(uid);
		}
		return null;
	}
	
	@Override
	public List<AlreadySale> getAllSaleGoodsByGid(int gid) throws Exception {
		if (isInt(gid)) {
			return this.dao.getAllSaleGoods(gid);
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
