package dao;

import java.util.List;

import bean.AlreadyBuy;

public interface AlreadyBuyDao {

    // 添加已购买商品
    boolean addBuyGoods(int uid, String sale_name, int gid, String gtype, int number, float price, float carriage) throws Exception;

    // 删除已购买商品
    boolean deleteBuyGoods(int gid) throws Exception;

    // 获取指定用户的购买商品记录
    List<AlreadyBuy> getAllBuyGoods(int uid) throws Exception;

    // 获取指定商品记录
    List<AlreadyBuy> getAllBuyGoodsByGid(int gid) throws Exception;

}
