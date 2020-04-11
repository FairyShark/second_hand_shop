package dao;

import bean.Collection;

import java.util.List;

public interface CollectionDao {

    // 添加收藏商品
    boolean addCollectionGoods(int uid, int gid) throws Exception;

    // 删除收藏商品
    boolean deleteCollectionGoods(int uid, int gid) throws Exception;

    // 判断是否已收藏
    boolean judgeCollection(int uid, int gid) throws Exception;

    // 获取指定用户的收藏商品记录
    List<Collection> getAllCollectionGoods(int uid) throws Exception;

    // 获取指定商品的收藏数目
    int getCount(int gid) throws Exception;

}
