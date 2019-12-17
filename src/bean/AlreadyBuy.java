package bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * 已经购买的商品
 */
public class AlreadyBuy {

	// 主键
	private int aid;
	// 用户id
	private int uid;
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(buyTime.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();  
        c.setTime(date);
        c.add(Calendar.HOUR, 16);
        date = c.getTime(); 
        buyTime = sdf.format(date);
		return buyTime;
	}

	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}

}
