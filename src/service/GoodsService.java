package service;

import java.sql.SQLException;
import java.util.List;

import bean.Goods;
import dao.GoodsDao;
import db.DBConnection;
import db.GoodsDaoImpl;

public class GoodsService implements GoodsDao {

	private DBConnection dbconn = null;

	private GoodsDao dao = null;

	public GoodsService() throws SQLException {
		this.dbconn = new DBConnection();
		this.dao = new GoodsDaoImpl(this.dbconn.getConnection());
	}

	@Override
	public boolean addGoods(Goods goods) throws Exception {
		if (goods != null) {
			return this.dao.addGoods(goods);
		}
		return false;
	}

	@Override
	public boolean editInfo(Goods goods) throws Exception {
		if (goods != null) {
			return this.dao.editInfo(goods);
		}
		return false;
	}
	
	@Override
	public boolean addPho(int gid, String pname) throws Exception {
		if (isInt(gid) && pname !=null) {
			return this.dao.addPho(gid, pname);
		}
		return false;
	}

	@Override
	public boolean deleteGoods(int gid) throws Exception {
		if (this.dao.queryById(gid) != null && isInt(gid)) {
			return this.dao.deleteGoods(gid);
		}
		return false;
	}

	@Override
	public Goods queryById(int gid) throws Exception {
		if (isInt(gid)) {
			return this.dao.queryById(gid);
		}
		return null;
	}
	
	@Override
	public String queryPho(int gid) throws Exception {
		if (isInt(gid)) {
			return this.dao.queryPho(gid);
		}
		return null;
	}
	
	@Override
	public String queryUName(int gid) throws Exception {
		if (isInt(gid)) {
			return this.dao.queryUName(gid);
		}
		return null;
	}
	
	@Override
	public int queryGid(Goods goods) throws Exception {
		if (goods != null) {
			return this.dao.queryGid(goods);
		}
		return 0;
	}
	
	@Override
	public Goods queryByUid(int uid) throws Exception {
		if (isInt(uid)) {
			return this.dao.queryByUid(uid);
		}
		return null;
	}

	@Override
	public int queryNumberById(int gid) throws Exception {
		if (isInt(gid)) {
			return this.dao.queryNumberById(gid);
		}
		return 0;
	}
	
	@Override
	public int querySaleUid(int gid) throws Exception {
		if (isInt(gid)) {
			return this.dao.querySaleUid(gid);
		}
		return 0;
	}

	@Override
	public List<Goods> getAllGoods() throws Exception {
		return this.dao.getAllGoods();
	}

	@Override
	public List<Goods> getLatestGoods(int gid, String type) throws Exception {
		if (isInt(gid) & type != null) {
			return this.dao.getLatestGoods(gid, type);
		}
		return null;
	}
	
	@Override
	public String queryTypesByGid(int gid) throws Exception {
		if (isInt(gid)) {
			return this.dao.queryTypesByGid(gid);
		}
		return null;
	}

	@Override
	public String[] queryTypes() throws Exception {
		return this.dao.queryTypes();
	}

	@Override
	public List<Goods> getTypeGoodsList(String type) throws Exception {
		if (type != null) {
			return this.dao.getTypeGoodsList(type);
		}
		return null;
	}
	
	@Override
	public List<Goods> getUidGoodsList(int uid) throws Exception {
		if (isInt(uid)) {
			return this.dao.getUidGoodsList(uid);
		}
		return null;
	}
	
	@Override
	public List<Goods> selectGoodsList(int uid, String type, String usage, int lowp, int highp, String gname) throws Exception{
		return this.dao.selectGoodsList(uid, type, usage, lowp, highp, gname);

	}
	
	public boolean isInt(int index) {
		if (index == 0) {
			return false;
		}
		String str = String.valueOf(index);
		return str.matches("[0-9]+$");
	}

}
