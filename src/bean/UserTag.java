package bean;

public class UserTag {

    //����
    private int tid;
    //�û�ID
    private int uid;
    //�û���
    private String uname;
    //��Ϊ����
    private String acttype;
    //��ǩ����
    private String tagtype;
    //Ȩ��
    private int tagweight;
    //��ǩʱ��
    private String tagtime;

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getTid() {
        return tid;
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

    public void setActtype(String acttype) {
        this.acttype = acttype;
    }

    public String getActtype() {
        return acttype;
    }

    public void setTagtype(String tagtype) {
        this.tagtype = tagtype;
    }

    public String getTagtype() {
        return tagtype;
    }

    public void setTagweight(int tagweight) {
        this.tagweight = tagweight;
    }

    public int getTagweight() {
        return tagweight;
    }

    public void setTagtime(String tagtime) {
        this.tagtime = tagtime;
    }

    public String getTagtime() {
        return tagtime;
    }

}
