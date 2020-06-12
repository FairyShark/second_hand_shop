package service;

import bean.ConsumptionAbility;
import dao.ConsumptionAbilityDao;
import db.ConsumptionAbilityDaoImpl;
import db.DBConnection;

import java.sql.SQLException;
import java.util.List;

public class ConsumptionAbilityService implements ConsumptionAbilityDao {

    private ConsumptionAbilityDao dao;

    public ConsumptionAbilityService() throws SQLException {
        DBConnection dbconn = new DBConnection();
        this.dao = new ConsumptionAbilityDaoImpl(dbconn.getConnection());
    }

    @Override
    public  boolean addConsumptionAbility(int uid, String uname, int number, float price) throws Exception{
        if(isInt(uid) && uname != null && isInt(number)){
            return this.dao.addConsumptionAbility(uid, uname, number, price);
        }
        return false;
    }

    @Override
    public List<ConsumptionAbility> getAllConsumptionAbility() throws Exception{
        return this.dao.getAllConsumptionAbility();
    }

    @Override
    public ConsumptionAbility getConsumptionAbility(int uid) throws Exception{
        if(isInt(uid)){
            return this.dao.getConsumptionAbility(uid);
        }
        return null;
    }

    public boolean isInt(int index) {
        if (index == 0) {
            return false;
        }
        String str = String.valueOf(index);
        return str.matches("[0-9]+$");
    }

}
