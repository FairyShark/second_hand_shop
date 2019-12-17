package dao;

import java.util.List;

import bean.ShoppingCart;
import javax.mail.*;
import java.security.GeneralSecurityException;



public interface ShoppingCartDao {

	// 为指定id用户添加购物车内商品
	public boolean addGoods(int uid, int gid, int number) throws Exception;

	// 为指定id用户删除购物车内商品
	public boolean deleteGoods(int uid, int gid, int number) throws Exception;

	// 为指定id用户查询购物车所有商品
	public List<ShoppingCart> getAllGoods(int uid) throws Exception;

	// 为指定id用户查询指定商品的数量
	public String getDesignateGoodsMs(int uid, int gid) throws Exception;

	// 为指定id用户支付商品
	public boolean payGoods(int uid, int gid, int number) throws Exception;

	// 为指定id用户支付所有商品
	public boolean payAllGoods(int uid) throws Exception;
	
	// 发送确认邮件
	public boolean sendEmail(int uid, int gid, int number) 
			throws MessagingException, GeneralSecurityException, Exception;

}
