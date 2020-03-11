package dao;

import java.util.List;

import bean.AlreadySale;

public interface AlreadySaleDao {

	// �����������Ʒ
	public boolean addSaleGoods(int uid, int buy_uid, String buy_name, int gid, int number) throws Exception;
	
	// ɾ����������Ʒ
	public boolean deleteSaleGoods(int gid) throws Exception;
	
	// ��ȡָ���û��Ĺ�����Ʒ��¼
	public List<AlreadySale> getAllSaleGoods(int uid) throws Exception;
	
	// ��ȡָ����Ʒ��¼
	public List<AlreadySale> getAllSaleGoodsByGid(int gid) throws Exception;
	
}
