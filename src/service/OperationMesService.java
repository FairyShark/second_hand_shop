package service;

import java.sql.SQLException;
import java.util.List;

import bean.OperationMes;
import dao.OperationMesDao;
import db.DBConnection;
import db.OperationMesDaoImpl;

public class OperationMesService implements OperationMesDao {

    private OperationMesDao dao;

    public OperationMesService() throws SQLException {
        DBConnection dbconn = new DBConnection();
        this.dao = new OperationMesDaoImpl(dbconn.getConnection());
    }

    //添加操作信息
    @Override
    public boolean addOperationMes(int uid, String uname, String userip, String otype, String opcontent) throws Exception {
        if (isInt(uid) && uname != null && userip != null && otype != null && opcontent != null) {
            return this.dao.addOperationMes(uid, uname, userip, otype, opcontent);
        }
        return false;
    }

    //查询操作日志
    @Override
    public List<OperationMes> getAllOperationMes() throws Exception {
        return this.dao.getAllOperationMes();
    }

    //查询浏览信息
    @Override
    public List<OperationMes> getOperationMes(int uid, String uname, String userip, String otype, String landtime) throws Exception {
        return this.dao.getOperationMes(uid, uname, userip, otype, landtime);
    }

    public boolean isInt(int index) {
        if (index == 0) {
            return false;
        }
        String str = String.valueOf(index);
        return str.matches("[0-9]+$");
    }

}
