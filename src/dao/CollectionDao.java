package dao;

import bean.Collection;

import java.util.List;

public interface CollectionDao {

    // 添加收藏商品
    public boolean addCollectionGoods(int uid, int gid) throws Exception;

    // 删除收藏商品
    public boolean deleteCollectionGoods(int uid, int gid) throws Exception;

    // 判断是否已收藏
    public boolean judgeCollection(int uid, int gid) throws Exception;

    // 获取指定用户的收藏商品记录
    public List<Collection> getAllCollectionGoods(int uid) throws Exception;

}
