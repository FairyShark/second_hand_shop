package dao;

import java.util.List;

import bean.VisitMessage;

public interface VisitMessageDao {

    //添加打开时间
    boolean addLandTimeMes(int uid, int gid, String uname, String gname, String gtype) throws Exception;

    //添加退出时间
    boolean addCancelTimeMes(int uid, int gid, String landtime) throws Exception;

    //查询停留时间
    int queryLasttime(int uid, int gid, String landtime) throws Exception;

    //查询登陆时间
    String getVisitlandtime(int uid, int gid) throws Exception;

    //查询所有浏览信息
    List<VisitMessage> getAllVisitMessage() throws Exception;

    //查询浏览信息
    List<VisitMessage> getVisitMessage(int uid, int gid, String uname, String gname, String gtype, String landtime) throws Exception;

}
