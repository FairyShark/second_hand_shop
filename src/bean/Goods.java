package bean;

public class Goods {

    // ����
    private int gid;
    // �û�id
    private int uid;
    // �û�����
    private String uname;
    // ��Ʒ��
    private String gname;
    // ������Ʒ����
    private int number;
    // ��Ʒ��Ƭ
    private String photo;
    // ��Ʒ����
    private String type;
    // ʹ�����
    private String usage;
    // �۸�
    private float price;
    // �˷�
    private float carriage;
    // ��������
    private String pdate;
    // ������
    private String paddress;
    // ��Ʒ����
    private String described;

    public Goods() {

    }

    public Goods(int uid, String uname, String gname, int number, String photo, String type,
                 String usage, float price, float carriage,
                 String paddress, String described) {
        this.uid = uid;
        this.uname = uname;
        this.gname = gname;
        this.number = number;
        this.photo = photo;
        this.type = type;
        this.usage = usage;
        this.price = price;
        this.carriage = carriage;
        this.paddress = paddress;
        this.described = described;
    }

    public Goods(int uid, String uname, String gname, int number, String photo, String type,
                 String usage, float price, float carriage, String pdate,
                 String paddress, String described) {
        this.uid = uid;
        this.uname = uname;
        this.gname = gname;
        this.number = number;
        this.photo = photo;
        this.type = type;
        this.usage = usage;
        this.price = price;
        this.carriage = carriage;
        this.pdate = pdate;
        this.paddress = paddress;
        this.described = described;
    }

    public Goods(int gid, int uid, String uname, String gname, int number, String photo, String type,
                 String usage, float price, float carriage,
                 String paddress, String described) {
        this.gid = gid;
        this.uid = uid;
        this.uname = uname;
        this.gname = gname;
        this.number = number;
        this.photo = photo;
        this.type = type;
        this.usage = usage;
        this.price = price;
        this.carriage = carriage;
        this.paddress = paddress;
        this.described = described;
    }

    public Goods(int gid, int uid, String uname, String gname, int number, String type,
                 String usage, float price, float carriage,
                 String paddress, String described) {
        this.gid = gid;
        this.uid = uid;
        this.uname = uname;
        this.gname = gname;
        this.number = number;
        this.type = type;
        this.usage = usage;
        this.price = price;
        this.carriage = carriage;
        this.paddress = paddress;
        this.described = described;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

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

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getCarriage() {
        return carriage;
    }

    public void setCarriage(float carriage) {
        this.carriage = carriage;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public String getPaddress() {
        return paddress;
    }

    public void setPaddress(String paddress) {
        this.paddress = paddress;
    }

    public String getDescribed() {
        return described;
    }

    public void setDescribed(String described) {
        this.described = described;
    }

}
