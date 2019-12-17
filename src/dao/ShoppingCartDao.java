package dao;

import java.util.List;

import bean.ShoppingCart;
import javax.mail.*;
import java.security.GeneralSecurityException;



public interface ShoppingCartDao {

	// Ϊָ��id�û���ӹ��ﳵ����Ʒ
	public boolean addGoods(int uid, int gid, int number) throws Exception;

	// Ϊָ��id�û�ɾ�����ﳵ����Ʒ
	public boolean deleteGoods(int uid, int gid, int number) throws Exception;

	// Ϊָ��id�û���ѯ���ﳵ������Ʒ
	public List<ShoppingCart> getAllGoods(int uid) throws Exception;

	// Ϊָ��id�û���ѯָ����Ʒ������
	public String getDesignateGoodsMs(int uid, int gid) throws Exception;

	// Ϊָ��id�û�֧����Ʒ
	public boolean payGoods(int uid, int gid, int number) throws Exception;

	// Ϊָ��id�û�֧��������Ʒ
	public boolean payAllGoods(int uid) throws Exception;
	
	// ����ȷ���ʼ�
	public boolean sendEmail(int uid, int gid, int number) 
			throws MessagingException, GeneralSecurityException, Exception;

}
