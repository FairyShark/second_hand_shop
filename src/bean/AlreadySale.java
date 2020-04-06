package bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    // ���۵���Ʒ����
    private int number;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(String saleTime) {
        this.saleTime = saleTime;
    }

    public int getMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(saleTime.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int smonth = c.get(Calendar.MONTH) + 1;
        return smonth;
    }

}
