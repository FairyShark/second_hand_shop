package dao;

import java.util.List;

import bean.AlreadyBuy;

public interface AlreadyBuyDao {

    // ����ѹ�����Ʒ
    public boolean addBuyGoods(int uid, String sale_name, int gid, String gtype, int number, float price, float carriage) throws Exception;

    // ɾ���ѹ�����Ʒ
    public boolean deleteBuyGoods(int gid) throws Exception;

    // ��ȡָ���û��Ĺ�����Ʒ��¼
    public List<AlreadyBuy> getAllBuyGoods(int uid) throws Exception;

    // ��ȡָ����Ʒ��¼
    public List<AlreadyBuy> getAllBuyGoodsByGid(int gid) throws Exception;

}
