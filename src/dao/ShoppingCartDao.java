package dao;

import java.util.List;

import bean.ShoppingCart;

public interface ShoppingCartDao {

    // Ϊָ��id�û���ӹ��ﳵ����Ʒ
    boolean addGoods(int uid, int gid, int number) throws Exception;

    // Ϊָ��id�û�ɾ�����ﳵ����Ʒ
    boolean deleteGoods(int uid, int gid, int number) throws Exception;

    // Ϊָ��id�û��޸Ĺ��ﳵ����Ʒ����
    boolean editGoods(int uid, int gid, int number) throws Exception;

    // Ϊָ��id�û���ѯ���ﳵ������Ʒ
    List<ShoppingCart> getAllGoods(int uid) throws Exception;

    // Ϊָ��id�û���ѯָ����Ʒ������
    String getDesignateGoodsMs(int uid, int gid) throws Exception;

    // Ϊָ��id�û�֧����Ʒ
    boolean payGoods(int uid, int gid, int number) throws Exception;

    // Ϊָ��id�û�֧��������Ʒ
    boolean payAllGoods(int uid) throws Exception;

    // ����ȷ���ʼ�
    boolean sendEmail(int uid, int gid, int number) throws Exception;

}
