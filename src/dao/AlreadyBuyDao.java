package dao;

import java.util.List;

import bean.AlreadyBuy;

public interface AlreadyBuyDao {

	// ����ѹ�����Ʒ
	public boolean addBuyGoods(int uid, int gid, int number) throws Exception;

	// ��ȡָ���û��Ĺ�����Ʒ��¼
	public List<AlreadyBuy> getAllBuyGoods(int uid) throws Exception;
	
	// ��ȡָ����Ʒ��¼
	public List<AlreadyBuy> getAllBuyGoodsByGid(int gid) throws Exception;

}
