package bean;

public class VisitMessage {

    //����
    private int vmid;
    //�û�ID
    private int uid;
    //��ƷID
    private int gid;
    //�û���
    private String uname;
    //��Ʒ��
    private String gname;
    //��Ʒ����
    private String types;
    //��ʱ��
    private String landtime;
    //�˳�ʱ��
    private String canceltime;
    //���ʱ��
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

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUname() {
        return uname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGname() {
        return gname;
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
