package bean;

public class AlreadySale {

    // ����
    private int asid;
    // �û�id
    private int uid;
    // ���id
    private int buy_uid;
    // �������
    private String buy_name;
    // ��Ʒid
    private int gid;
    // ��Ʒ����
    private String gtype;
    // ���۵���Ʒ����
    private int number;
    // ����
    private float price;
    // �˷�
    private float carriage;
    // ����ʱ��
    private String saleTime;

    public int getAsid() {
        return asid;
    }

    public void setAsid(int asid) {
        this.asid = asid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getBUid() {
        return buy_uid;
    }

    public void setBUid(int buy_uid) {
        this.buy_uid = buy_uid;
    }

    public String getBName() {
        return buy_name;
    }

    public void setBName(String buy_name) {
        this.buy_name = buy_name;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGtype() {
        return gtype;
    }

    public void setGtype(String gtype) {
        this.gtype = gtype;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public String getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(String saleTime) {
        this.saleTime = saleTime;
    }

}
