package dao;

import bean.Collection;

import java.util.List;

public interface CollectionDao {

    // ����ղ���Ʒ
    boolean addCollectionGoods(int uid, int gid) throws Exception;

    // ɾ���ղ���Ʒ
    boolean deleteCollectionGoods(int uid, int gid) throws Exception;

    // �ж��Ƿ����ղ�
    boolean judgeCollection(int uid, int gid) throws Exception;

    // ��ȡָ���û����ղ���Ʒ��¼
    List<Collection> getAllCollectionGoods(int uid) throws Exception;

    // ��ȡָ����Ʒ���ղ���Ŀ
    int getCount(int gid) throws Exception;

}
