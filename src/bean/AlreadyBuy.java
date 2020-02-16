package bean;

/*
 * 已经购买的商品
 */
public class AlreadyBuy {

	// 主键
	private int aid;
	// 用户id
	private int uid;
	// 用户名字
	private String sale_name;
	// 商品id
	private int gid;
	// 购买的商品数量
	private int number;
	// 购买时间
	private String buyTime;

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String getSName() {
		return sale_name;
	}
	
	public void setSName(String sale_name) {
		this.sale_name = sale_name;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}

}
