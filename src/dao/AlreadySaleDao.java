package dao;

import java.util.List;

import bean.AlreadySale;

public interface AlreadySaleDao {

    // 添加已销售商品
    boolean addSaleGoods(int uid, int buy_uid, String buy_name, int gid, String gtype, int number, float price, float carriage) throws Exception;

    // 删除已销售商品
    boolean deleteSaleGoods(int gid) throws Exception;

    // 获取指定用户的购买商品记录
    List<AlreadySale> getAllSaleGoods(int uid) throws Exception;

    // 获取指定商品记录
    List<AlreadySale> getAllSaleGoodsByGid(int gid) throws Exception;

    // 获取月份
    int getMonth(String date) throws Exception;

}
