package bean;

public class OperationMes {
	
	//����
	private int omid;
	//�û�ID
	private int uid;
	//IP��ַ
	private String userip;
	//��������
	private String opcontent;
	//����ʱ��
	private String operationtime;
	
	public void setOmid(int omid) {
		this.omid = omid;
	}
	public int getOmid() {
		return omid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getUid() {
		return uid;
	}
	
	public void setUserip(String userip) {
		this.userip = userip;
	}
	public String getUserip() {
		return userip;
	}
	
	public void setOpcontent(String opcontent) {
		this.opcontent = opcontent;
	}
	public String getOpcontent() {
		return opcontent;
	}
	
	public void setOperationtime(String operationtime) {
		this.operationtime = operationtime;
	}
	public String getOperationtime() {
		return operationtime;
	}

}
