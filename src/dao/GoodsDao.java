package dao;

import java.util.List;

import bean.Goods;

public interface GoodsDao {

	// �����Ʒ
	public boolean addGoods(Goods goods) throws Exception;
	
	// ����ָ�����͵������ӵ��ļ���Ʒ
	public List<Goods> getLatestGoods(int gid, String type) throws Exception;

	// ����������Ʒ
	public List<Goods> getAllGoods() throws Exception;

	// �༭��Ʒ��Ϣ
	public boolean editInfo(Goods goods) throws Exception;
	
	// �����ƷͼƬ
	public boolean addPho(int gid, String pname) throws Exception;

	// ɾ����Ʒ
	public boolean deleteGoods(int gid) throws Exception;

	// ����ָ��id����Ʒ
	public Goods queryById(int gid) throws Exception;
	
	// ����ָ���û�id����Ʒ
	public Goods queryByUid(int uid) throws Exception;

	// ��ѯָ��id����Ʒ������
	public int queryNumberById(int gid) throws Exception;
	
	// ��ѯ��Ʒ��id
	public int queryGid(Goods goods) throws Exception;

	// ��ѯָ��id����Ʒ������
	public int querySaleUid(int gid) throws Exception;
	
	// ��ѯָ��id����Ʒ��ͼƬ
	public String queryPho(int gid) throws Exception;
	
	// ��ѯָ��id����Ʒ������
	public String queryTypesByGid(int gid) throws Exception;
	
	// ����������Ʒ������
	public String[] queryTypes() throws Exception;
	
	// ��ѯָ��id��������
	public String queryUName(int gid) throws Exception;

	// ����ָ�����͵�������Ʒ
	public List<Goods> getTypeGoodsList(String type) throws Exception;
	
	// ����ָ�����͵�������Ʒ
	public List<Goods> getUidGoodsList(int uid) throws Exception;
		
	// ����ָ�����͵�������Ʒ
	public List<Goods> selectGoodsList(int uid, String type, String usage, int lowp, int highp, String gname) throws Exception;
	
}
