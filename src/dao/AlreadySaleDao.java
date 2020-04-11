package dao;

import java.util.List;

import bean.AlreadySale;

public interface AlreadySaleDao {

    // �����������Ʒ
    boolean addSaleGoods(int uid, int buy_uid, String buy_name, int gid, String gtype, int number, float price, float carriage) throws Exception;

    // ɾ����������Ʒ
    boolean deleteSaleGoods(int gid) throws Exception;

    // ��ȡָ���û��Ĺ�����Ʒ��¼
    List<AlreadySale> getAllSaleGoods(int uid) throws Exception;

    // ��ȡָ����Ʒ��¼
    List<AlreadySale> getAllSaleGoodsByGid(int gid) throws Exception;

    // ��ȡ�·�
    int getMonth(String date) throws Exception;

}
