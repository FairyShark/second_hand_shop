package dao;

import java.util.List;

import bean.LandMessage;

public interface LandMessageDao {

    //添加登陆时间
    boolean addLandTimeMes(int uid, String uname, String userip) throws Exception;

    //添加退出时间
    boolean addCancelTimeMes(int uid, String userip, String landtime) throws Exception;

    //查询登陆时间
    String getLandtime(int uid, String userip) throws Exception;

    //查询所有登陆信息
    List<LandMessage> getAllLandMessage() throws Exception;

    //查询登陆信息
    List<LandMessage> getLandMessage(int uid, String uname, String userip, String landtime) throws Exception;

}
