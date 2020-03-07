package dao;

import java.util.List;

import bean.LandMessage;

public interface LandMessageDao {
	
	//添加登陆时间
	public boolean addLandTimeMes(int uid, String userip) throws Exception;

	//添加退出时间
	public boolean addCancelTimeMes(int uid, String userip, String landtime) throws Exception;
	
	//查询登陆信息
	public List<LandMessage> getLandMessage(int uid, String userip) throws Exception;

}
