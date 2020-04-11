package dao;

import java.util.List;

import bean.OperationMes;

public interface OperationMesDao {

    //添加操作信息
    boolean addOperationMes(int uid, String uname, String userip, String otype, String opcontent) throws Exception;

    //查询所有操作日志
    List<OperationMes> getAllOperationMes() throws Exception;

    //查询操作日志
    List<OperationMes> getOperationMes(int uid, String uname, String userip, String otype, String landtime) throws Exception;

}
