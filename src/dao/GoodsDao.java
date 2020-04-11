package dao;

import java.util.List;

import bean.Goods;

public interface GoodsDao {

    // �����Ʒ
    boolean addGoods(Goods goods) throws Exception;

    // ����ָ�����͵������ӵ��ļ���Ʒ
    List<Goods> getLatestGoods(int gid, String type) throws Exception;

    // ����������Ʒ
    List<Goods> getAllGoods() throws Exception;

    // �༭��Ʒ��Ϣ
    boolean editInfo(Goods goods) throws Exception;

    // �����ƷͼƬ
    boolean addPho(int gid, String pname) throws Exception;

    // ɾ����Ʒ
    boolean deleteGoods(int gid) throws Exception;

    // ����ָ��id����Ʒ
    Goods queryById(int gid) throws Exception;

    // ����ָ���û�id����Ʒ
    Goods queryByUid(int uid) throws Exception;

    // ��ѯָ��id����Ʒ������
    int queryNumberById(int gid) throws Exception;

    // ��ѯ��Ʒ��id
    int queryGid(Goods goods) throws Exception;

    // ��ѯָ��id����Ʒ������
    int querySaleUid(int gid) throws Exception;

    // ��ѯָ��id����Ʒ��ͼƬ
    String queryPho(int gid) throws Exception;

    // ��ѯָ��id����Ʒ������
    String queryTypesByGid(int gid) throws Exception;

    // ����������Ʒ������
    String[] queryTypes() throws Exception;

    // ��ѯָ��id��������
    String queryUName(int gid) throws Exception;

    // ����ָ�����͵�������Ʒ
    List<Goods> getTypeGoodsList(String type) throws Exception;

    // ����ָ�����͵�������Ʒ
    List<Goods> getUidGoodsList(int uid) throws Exception;

    // ����ָ�����͵�������Ʒ
    List<Goods> selectGoodsList(int uid, String type, String usage, float lowp, float highp, String gname) throws Exception;

}
