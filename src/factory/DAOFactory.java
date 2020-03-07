package factory;

import dao.AlreadyBuyDao;
import dao.AlreadySaleDao;
import dao.GoodsDao;
import dao.LandMessageDao;
import dao.OperationMesDao;
import dao.ShoppingCartDao;
import dao.UserDao;
import dao.VisitMessageDao;
import service.AlreadyBuyService;
import service.AlreadySaleService;
import service.GoodsService;
import service.LandMessageService;
import service.OperationMesService;
import service.ShoppingCartService;
import service.UserService;
import service.VisitMessageService;

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
	
	public static LandMessageDao getLandMessageServiceInstance() throws Exception {
		return new LandMessageService();
	}
	
	public static VisitMessageDao getVisitMessageServiceInstance() throws Exception {
		return new VisitMessageService();
	}
	
	public static OperationMesDao getOperationMesServiceInstance() throws Exception {
		return new OperationMesService();
	}
	
}
