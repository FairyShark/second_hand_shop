package factory;

import dao.AlreadyBuyDao;
import dao.AlreadySaleDao;
import dao.GoodsDao;
import dao.ShoppingCartDao;
import dao.UserDao;
import service.AlreadyBuyService;
import service.AlreadySaleService;
import service.GoodsService;
import service.ShoppingCartService;
import service.UserService;

public class DAOFactory {

	public static UserDao getUserServiceInstance() throws Exception {
		return new UserService();
	}

	public static GoodsDao getGoodsServiceInstance() throws Exception {
		return new GoodsService();
	}

	public static ShoppingCartDao getShoppingCartServiceInstance()
			throws Exception {
		return new ShoppingCartService();
	}

	public static AlreadyBuyDao getAlreadyBuyServiceInstance() throws Exception {
		return new AlreadyBuyService();
	}
	
	public static AlreadySaleDao getAlreadySaleServiceInstance() throws Exception {
		return new AlreadySaleService();
	}
}
