package bean;

public class VisitMessage {
	
	//主键
	private int vmid;
	//用户ID
	private int uid;
	//商品ID
	private int gid;
	//商品类型
	private String types;
	//打开时间
	private String landtime;
	//退出时间
	private String canceltime;
	//浏览时间
	private int lasttime;
	
	public void setVmid(int vmid) {
		this.vmid = vmid;
	}
	public int getVmid() {
		return vmid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getUid() {
		return uid;
	}
	
	public void setGid(int gid) {
		this.gid = gid;
	}
	public int getGid() {
		return gid;
	}
	
	public void setTypes(String types) {
		this.types = types;
	}
	public String getTypes() {
		return types;
	}
	
	public void setLandtime(String landtime) {
		this.landtime = landtime;
	}
	public String getLandtime() {
		return landtime;
	}
	
	public void setCanceltime(String canceltime) {
		this.canceltime = canceltime;
	}
	public String getCanceltime() {
		return canceltime;
	}
	
	public void setLasttime(int lasttime) {
		this.lasttime = lasttime;
	}
	public int getLasttime() {
		return lasttime;
	}

}
