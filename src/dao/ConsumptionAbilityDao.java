package dao;

import bean.ConsumptionAbility;

import java.util.List;

public interface ConsumptionAbilityDao {

    // ����ղ���Ʒ
    boolean addConsumptionAbility(int uid, String uname, int number, float price) throws Exception;

    // ��ȡ�����û�����������
    List<ConsumptionAbility> getAllConsumptionAbility() throws Exception;

    // ��ȡָ���û�����������
    ConsumptionAbility getConsumptionAbility(int uid) throws Exception;

}
