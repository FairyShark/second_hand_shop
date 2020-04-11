package dao;

import java.util.List;

import bean.ShoppingCart;

public interface ShoppingCartDao {

    // 为指定id用户添加购物车内商品
    boolean addGoods(int uid, int gid, int number) throws Exception;

    // 为指定id用户删除购物车内商品
    boolean deleteGoods(int uid, int gid, int number) throws Exception;

    // 为指定id用户修改购物车内商品数量
    boolean editGoods(int uid, int gid, int number) throws Exception;

    // 为指定id用户查询购物车所有商品
    List<ShoppingCart> getAllGoods(int uid) throws Exception;

    // 为指定id用户查询指定商品的数量
    String getDesignateGoodsMs(int uid, int gid) throws Exception;

    // 为指定id用户支付商品
    boolean payGoods(int uid, int gid, int number) throws Exception;

    // 为指定id用户支付所有商品
    boolean payAllGoods(int uid) throws Exception;

    // 发送确认邮件
    boolean sendEmail(int uid, int gid, int number) throws Exception;

}
