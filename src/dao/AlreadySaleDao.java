package dao;

import java.util.List;

import bean.AlreadySale;

public interface AlreadySaleDao {

	// 添加已销售商品
	public boolean addSaleGoods(int uid, int buy_uid, String buy_name, int gid, int number) throws Exception;
	
	// 删除已销售商品
	public boolean deleteSaleGoods(int gid) throws Exception;
	
	// 获取指定用户的购买商品记录
	public List<AlreadySale> getAllSaleGoods(int uid) throws Exception;
	
	// 获取指定商品记录
	public List<AlreadySale> getAllSaleGoodsByGid(int gid) throws Exception;
	
}
