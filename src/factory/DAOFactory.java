package factory;

import dao.*;
import service.*;

public class DAOFactory {

    public static UserDao getUserServiceInstance() throws Exception {
        return new UserService();
    }

    public static GoodsDao getGoodsServiceInstance() throws Exception {
        return new GoodsService();
    }

    public static ShoppingCartDao getShoppingCartServiceInstance() throws Exception {
        return new ShoppingCartService();
    }

    public static CollectionDao getCollectionServiceInstance() throws Exception {
        return new CollectionService();
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

    public static UserTagDao getUserTagServiceInstance() throws Exception {
        return new UserTagService();
    }

    public static ConsumptionAbilityDao getConsumptionAbilityServiceInstance() throws Exception {
        return new ConsumptionAbilityService();
    }

}
