package bean;

public class AlreadySale {

    // 主键
    private int asid;
    // 用户id
    private int uid;
    // 买家id
    private int buy_uid;
    // 买家名字
    private String buy_name;
    // 商品id
    private int gid;
    // 商品类型
    private String gtype;
    // 销售的商品数量
    private int number;
    // 单价
    private float price;
    // 运费
    private float carriage;
    // 销售时间
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
