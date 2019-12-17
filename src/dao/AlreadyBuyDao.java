package dao;

import java.util.List;

import bean.AlreadyBuy;

public interface AlreadyBuyDao {

	// 添加已购买商品
	public boolean addBuyGoods(int uid, int gid, int number) throws Exception;

	// 获取指定用户的购买商品记录
	public List<AlreadyBuy> getAllBuyGoods(int uid) throws Exception;
	
	// 获取指定商品记录
	public List<AlreadyBuy> getAllBuyGoodsByGid(int gid) throws Exception;

}
