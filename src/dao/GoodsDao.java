package dao;

import java.util.List;

import bean.Goods;

public interface GoodsDao {

    // 添加商品
    boolean addGoods(Goods goods) throws Exception;

    // 返回指定类型的最近添加的四件商品
    List<Goods> getLatestGoods(int gid, String type) throws Exception;

    // 返回所有商品
    List<Goods> getAllGoods() throws Exception;

    // 编辑商品信息
    boolean editInfo(Goods goods) throws Exception;

    // 添加商品图片
    boolean addPho(int gid, String pname) throws Exception;

    // 删除商品
    boolean deleteGoods(int gid) throws Exception;

    // 查找指定id的商品
    Goods queryById(int gid) throws Exception;

    // 查找指定用户id的商品
    Goods queryByUid(int uid) throws Exception;

    // 查询指定id的商品的数量
    int queryNumberById(int gid) throws Exception;

    // 查询商品的id
    int queryGid(Goods goods) throws Exception;

    // 查询指定id的商品的卖家
    int querySaleUid(int gid) throws Exception;

    // 查询指定id的商品的图片
    String queryPho(int gid) throws Exception;

    // 查询指定id的商品的类型
    String queryTypesByGid(int gid) throws Exception;

    // 返回所有商品的类型
    String[] queryTypes() throws Exception;

    // 查询指定id的卖家名
    String queryUName(int gid) throws Exception;

    // 返回指定类型的所有商品
    List<Goods> getTypeGoodsList(String type) throws Exception;

    // 返回指定类型的所有商品
    List<Goods> getUidGoodsList(int uid) throws Exception;

    // 返回指定类型的所有商品
    List<Goods> selectGoodsList(int uid, String type, String usage, float lowp, float highp, String gname) throws Exception;

}
