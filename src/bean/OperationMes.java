package bean;

public class OperationMes {

    //主键
    private int omid;
    //用户ID
    private int uid;
    //用户名
    private String uname;
    //IP地址
    private String userip;
    //操作类型
    private String otype;
    //操作内容
    private String opcontent;
    //操作时间
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
