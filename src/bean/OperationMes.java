package bean;

public class OperationMes {

    //����
    private int omid;
    //�û�ID
    private int uid;
    //�û���
    private String uname;
    //IP��ַ
    private String userip;
    //��������
    private String otype;
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

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUname() {
        return uname;
    }

    public void setUserip(String userip) {
        this.userip = userip;
    }

    public String getUserip() {
        return userip;
    }

    public void setOtype(String otype) {
        this.otype = otype;
    }

    public String getOtype() {
        return otype;
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
