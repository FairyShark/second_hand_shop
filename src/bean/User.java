package bean;

import java.util.Date; 
import java.util.Calendar; 
import java.text.ParseException; 
import java.text.SimpleDateFormat; 


public class User {

	// ����
	private int uid;
	// �û���
	private String uname;
	// ����
	private String passwd;
	// �����ַ
	private String email;
	// ����¼ʱ��
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd��HH:mm:ss");
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
