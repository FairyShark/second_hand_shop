package bean;

public class AlreadyBuy {

    // ����
    private int aid;
    // �û�id
    private int uid;
    // �������
    private String sale_name;
    // ��Ʒid
    private int gid;
    // ��Ʒ����
    private String gtype;
    // �������Ʒ����
    private int number;
    // ����
    private float price;
    // �˷�
    private float carriage;
    // ����ʱ��
    private String buyTime;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getSName() {
        return sale_name;
    }

    public void setSName(String sale_name) {
        this.sale_name = sale_name;
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

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

}
