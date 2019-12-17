package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.User;
import bean.Goods;
import bean.ShoppingCart;
import dao.UserDao;
import dao.AlreadyBuyDao;
import dao.AlreadySaleDao;
import dao.GoodsDao;
import dao.ShoppingCartDao;
import factory.DAOFactory;

import com.sun.mail.util.MailSSLSocketFactory;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import java.security.GeneralSecurityException;
import java.util.Properties;

public class ShoppingCartDaoImpl implements ShoppingCartDao {

	private Connection conn = null;

	private PreparedStatement pstmt = null;

	public ShoppingCartDaoImpl(Connection conn) {
		this.conn = conn;
	}
	
	//���ﳵ�����Ʒ
	@Override
	public boolean addGoods(int uid, int gid, int number) throws Exception {
		pstmt = null;
		int result = 0;
		String message = this.getDesignateGoodsMs(uid, gid);
		if (!"".equals(message)) {
			int sid = Integer.valueOf(message.split("&")[0]);
			System.out.println("SID:" + sid);
			int goodsCount = Integer.valueOf(message.split("&")[1]);
			String sql = "update shoppingcart set number=?,sdate=now() where sid=?";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, goodsCount + number);
			pstmt.setInt(2, sid);
		} else {
			String sql = "insert into shoppingcart(uid,gid,number,sdate)value(?,?,?,now());";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setInt(2, gid);
			pstmt.setInt(3, number);
		}
		result = pstmt.executeUpdate();
		pstmt.close();
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	//ɾ�����ﳵ�е���Ʒ
	@Override
	public boolean deleteGoods(int uid, int gid, int number) throws Exception {
		String message = this.getDesignateGoodsMs(uid, gid);
		int result = 0;
		if (!"".equals(message)) {
			int sid = Integer.valueOf(message.split("&")[0]);
			int goodsCount = Integer.valueOf(message.split("&")[1]);
			if (goodsCount < number) {
				return false;
			} else if (goodsCount == number) {
				String sql = "delete from shoppingcart where sid=?";
				pstmt = this.conn.prepareStatement(sql);
				pstmt.setInt(1, sid);
			} else {
				String sql = "update shoppingcart set number=? where sid=?";
				pstmt = this.conn.prepareStatement(sql);
				pstmt.setInt(1, goodsCount - number);
				pstmt.setInt(2, sid);
			}
			result = pstmt.executeUpdate();
			pstmt.close();
		}
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	//��ȡ���ﳵ�е�������Ʒ
	@Override
	public List<ShoppingCart> getAllGoods(int uid) throws Exception {
		pstmt = null;
		ResultSet rs = null;
		List<ShoppingCart> scList = null;
		String sql = "select * from shoppingcart where uid=?";
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		rs = pstmt.executeQuery();
		ShoppingCart sc;
		scList = new ArrayList<ShoppingCart>();
		while (rs.next()) {
			sc = new ShoppingCart();
			sc.setSid(rs.getInt("sid"));
			sc.setUid(rs.getInt("uid"));
			sc.setGid(rs.getInt("gid"));
			sc.setNumber(rs.getInt("number"));
			String date = rs.getDate("sdate").toString();
			String time = rs.getTime("sdate").toString();
			sc.setSdate(date + " " + time);
			scList.add(sc);
		}
		return scList;
	}
	
	//���ָ���û����ﳵ���Ƿ���ָ����Ʒ��������򷵻ع��ﳵid����Ʒ���������򷵻ؿ�
	@Override
	public String getDesignateGoodsMs(int uid, int gid) throws Exception {
		ResultSet rs = null;
		String sql = "select * from shoppingcart where uid =? and gid=?";
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		pstmt.setInt(2, gid);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getString("sid") + "&" + rs.getInt("number");
		}
		return "";
	}

	//֧��ָ����Ʒ
	@Override
	public boolean payGoods(int uid, int gid, int number) throws Exception {
		boolean flag = false;
		GoodsDao dao = DAOFactory.getGoodsServiceInstance();
		AlreadySaleDao saleDao = DAOFactory.getAlreadySaleServiceInstance();
		int extantGoods = dao.queryNumberById(gid);
		int sale_uid = dao.querySaleUid(gid);
		conn.setAutoCommit(false);
		if (extantGoods >= number) {
			if (this.deleteGoods(uid, gid, number)) {
				Goods goods = dao.queryById(gid);
				goods.setNumber(extantGoods - number);
				AlreadyBuyDao ab = DAOFactory.getAlreadyBuyServiceInstance();
				flag = (ab.addBuyGoods(uid, gid, number) & dao.editInfo(goods));
				saleDao.addSaleGoods(sale_uid, uid, gid, number);
				this.sendEmail(uid, gid, number);
			}
		}else {
			if (this.deleteGoods(uid, gid, number)) {
			JOptionPane.showMessageDialog(null, "�������Ʒ��治�㣬������ѡ��������", "��Ǹ", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		conn.commit();
		conn.setAutoCommit(true);
		return flag;
	}

	//֧��������Ʒ
	@Override
	public boolean payAllGoods(int uid) throws Exception {
		List<ShoppingCart> scList = this.getAllGoods(uid);
		if (scList != null & scList.size() > 0) {
			int gid;
			int number;
			ShoppingCart sc;
			for (int i = 0; i < scList.size(); i++) {
				sc = scList.get(i);
				gid = sc.getGid();
				number = sc.getNumber();
				this.payGoods(uid, gid, number);
			}
			return true;
		}
		return false;
	}
	
	//����ȷ���ʼ�
	@Override
	public boolean sendEmail(int uid, int gid, int number) throws MessagingException, GeneralSecurityException, Exception {
        
		UserDao user = DAOFactory.getUserServiceInstance();
		GoodsDao goods = DAOFactory.getGoodsServiceInstance();
		User buypeople = user.queryByUid(uid);
		Goods buygoods = goods.queryById(gid);
		
		String messages = "�װ���" + buypeople.getUname() + ":�������" + number + "��  ��" + buygoods.getGname() + "�� ֧���ɹ�����ȷ���ջ���";
		String buyemail = buypeople.getEmail();
		
		//����һ�������ļ�������
		
        Properties properties = new Properties();

        properties.setProperty("mail.host","smtp.qq.com");

        properties.setProperty("mail.transport.protocol","smtp");

        properties.setProperty("mail.smtp.auth","true");

        //QQ����һ����������SSL����
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        //����һ��session����
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("530734760@qq.com","qxcwjwzhkbgjbhjc");
            }
        });

        //����debugģʽ
        session.setDebug(true);

        //��ȡ���Ӷ���
        Transport transport = session.getTransport();

        //���ӷ�����
        transport.connect("smtp.qq.com","530734760@qq.com","qxcwjwzhkbgjbhjc");

        //�����ʼ�����
        MimeMessage mimeMessage = new MimeMessage(session);

        //�ʼ�������
        mimeMessage.setFrom(new InternetAddress("530734760@qq.com"));

        //�ʼ�������
        mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(buyemail));

        //�ʼ�����
        mimeMessage.setSubject("ȷ���ջ�");

        //�ʼ�����
        mimeMessage.setContent(messages, "text/html;charset=UTF-8");

        //�����ʼ�
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());

        //�ر�����
        transport.close();
       
        return true;
    }

}
