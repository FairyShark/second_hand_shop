package bean;

public class LandMessage {
	
	//主键
	private int lmid;
	//用户ID
	private int uid;
	//IP地址
	private String userip;
	//登陆时间
	private String landtime;
	//退出时间
	private String canceltime;
	
	public void setLmid(int lmid) {
		this.lmid = lmid;
	}
	public int getLmid() {
		return this.lmid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getUid() {
		return this.uid;
	}
	
	public void setUserip(String userip) {
		this.userip = userip;
	}
	public String getUserip() {
		return this.userip;
	}
	
	public void setLandtime(String landtime) {
		this.landtime = landtime;
	}
	public String getLandtime() {
		return this.landtime;
	}
	
	public void setCanceltime(String canceltime) {
		this.canceltime = canceltime;
	}
	public String getCanceltime() {
		return this.canceltime;
	}
	

}
