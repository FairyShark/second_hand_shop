package bean;

public class ConsumptionAbility {

    // 主键
    private int bid;
    // 用户id
    private int uid;
    // 用户名字
    private String user_name;
    // 付款次数
    private int pay_number;
    // 付款总额
    private float total_pay;
    // 消费分层
    private String layered;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getPay_number() {
        return pay_number;
    }

    public void setPay_number(int pay_number) {
        this.pay_number = pay_number;
    }

    public float getTotal_pay() {
        return total_pay;
    }

    public void setTotal_pay(float total_pay) {
        this.total_pay = total_pay;
    }

    public String getLayered() {
        return layered;
    }

    public void setLayered(String layered) {
        this.layered = layered;
    }

}
