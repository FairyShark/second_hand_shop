package bean;

public class UserTag {

    //主键
    private int tid;
    //用户ID
    private int uid;
    //用户名
    private String uname;
    //行为类型
    private String acttype;
    //标签类型
    private String tagtype;
    //权重
    private int tagweight;
    //标签时间
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
