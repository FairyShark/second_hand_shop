package dao;

import bean.ConsumptionAbility;

import java.util.List;

public interface ConsumptionAbilityDao {

    // 添加用户的消费能力
    boolean addConsumptionAbility(int uid, String uname, int number, float price) throws Exception;

    // 获取所有用户的消费能力
    List<ConsumptionAbility> getAllConsumptionAbility() throws Exception;

    // 获取指定用户的消费能力
    ConsumptionAbility getConsumptionAbility(int uid) throws Exception;

}
