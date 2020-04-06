package dao;

import bean.Collection;

import java.util.List;

public interface CollectionDao {

    // ����ղ���Ʒ
    public boolean addCollectionGoods(int uid, int gid) throws Exception;

    // ɾ���ղ���Ʒ
    public boolean deleteCollectionGoods(int uid, int gid) throws Exception;

    // �ж��Ƿ����ղ�
    public boolean judgeCollection(int uid, int gid) throws Exception;

    // ��ȡָ���û����ղ���Ʒ��¼
    public List<Collection> getAllCollectionGoods(int uid) throws Exception;

}
