package bean;

import java.util.Date; 
import java.util.Calendar; 
import java.text.ParseException; 
import java.text.SimpleDateFormat; 


public class User {

	// 主键
	private int uid;
	// 用户名
	private String uname;
	// 密码
	private String passwd;
	// 邮箱地址
	private String email;
	// 最后登录时间
	private String lastLogin;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastLogin() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd　HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(lastLogin.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();  
        c.setTime(date);
        c.add(Calendar.HOUR, 16);
        date = c.getTime(); 
        lastLogin = sdf.format(date);
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

}
