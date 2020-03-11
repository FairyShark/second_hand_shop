package dao;

import java.util.List;

import bean.Goods;

public interface GoodsDao {

	// 添加商品
	public boolean addGoods(Goods goods) throws Exception;
	
	// 返回指定类型的最近添加的四件商品
	public List<Goods> getLatestGoods(int gid, String type) throws Exception;

	// 返回所有商品
	public List<Goods> getAllGoods() throws Exception;

	// 编辑商品信息
	public boolean editInfo(Goods goods) throws Exception;
	
	// 添加商品图片
	public boolean addPho(int gid, String pname) throws Exception;

	// 删除商品
	public boolean deleteGoods(int gid) throws Exception;

	// 查找指定id的商品
	public Goods queryById(int gid) throws Exception;
	
	// 查找指定用户id的商品
	public Goods queryByUid(int uid) throws Exception;

	// 查询指定id的商品的数量
	public int queryNumberById(int gid) throws Exception;
	
	// 查询商品的id
	public int queryGid(Goods goods) throws Exception;

	// 查询指定id的商品的卖家
	public int querySaleUid(int gid) throws Exception;
	
	// 查询指定id的商品的图片
	public String queryPho(int gid) throws Exception;
	
	// 查询指定id的商品的类型
	public String queryTypesByGid(int gid) throws Exception;
	
	// 返回所有商品的类型
	public String[] queryTypes() throws Exception;
	
	// 查询指定id的卖家名
	public String queryUName(int gid) throws Exception;

	// 返回指定类型的所有商品
	public List<Goods> getTypeGoodsList(String type) throws Exception;
	
	// 返回指定类型的所有商品
	public List<Goods> getUidGoodsList(int uid) throws Exception;
		
	// 返回指定类型的所有商品
	public List<Goods> selectGoodsList(int uid, String type, String usage, int lowp, int highp, String gname) throws Exception;
	
}
