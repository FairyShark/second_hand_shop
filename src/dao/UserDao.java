package dao;

import java.util.List;

import bean.User;

public interface UserDao {

    // ����û�
    boolean addUser(User user) throws Exception;

    // �޸�����
    boolean editEmail(int uid, String email) throws Exception;

    // �޸�����
    boolean editPasswd(int uid, String passwd) throws Exception;

    // �޸�����¼ʱ��
    boolean editLoginTime(int uid) throws Exception;

    // ɾ���û�
    boolean deleteUser(int uid) throws Exception;

    // ͨ��id�����û���
    String queryUName(int uid) throws Exception;

    // ͨ���û��������û�
    User queryByName(String uname) throws Exception;

    // ͨ���û�id�����û�
    User queryByUid(int uid) throws Exception;

    // ͨ��email�����û�
    User queryByEmail(String email) throws Exception;

    // ���������û�
    List<User> selectAllUser() throws Exception;

    // �����û�
    List<User> selectUserList(int uid, String uname, String umail) throws Exception;

    // �޸Ľ�һ���µ�½����
    boolean editFrequency(int uid) throws Exception;

    // �޸�������Ϣ
    boolean editUser(int uid, String sex, String tel, String birth, String addr) throws Exception;

}
