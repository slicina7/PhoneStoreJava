package ba.unsa.etf.rpr.dao;
/**
 * Factory method for singleton implementation of DAOs
 * @author Selma Licina
 */
public class DaoFactory {
    private static final BrandDao brandDao=BrandDaoSQLImpl.getInstance();
    private static final BuyerDao buyerDao=BuyerDaoSQLImpl.getInstance();
    private static final PhoneDao phoneDao=PhoneDaoSQLImpl.getInstance();
    private static final PurchaseDao purchaseDao=PurchaseDaoSQLImpl.getInstance();

    private DaoFactory(){
    }
    public static BrandDao brandDao(){ return brandDao;}
    public static BuyerDao buyerDao(){return buyerDao;}
    public static PhoneDao phoneDao(){return phoneDao;}
    public static PurchaseDao purchaseDao(){return purchaseDao;}
}
