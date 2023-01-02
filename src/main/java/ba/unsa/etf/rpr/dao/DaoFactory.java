package ba.unsa.etf.rpr.dao;
/**
 * Factory method for singleton implementation of DAOs
 */
public class DaoFactory {
    private static final BrandDao brandDao=new BrandDaoSQLImpl();
    private static final BuyerDao buyerDao=new BuyerDaoSQLImpl();
    private static final PhoneDao phoneDao=new PhoneDaoSQLImpl();
    private static final PurchaseDao purchaseDao=new PurchaseDaoSQLImpl();

    private DaoFactory(){
    }
    public static BrandDao brandDao(){ return brandDao;}
    public static BuyerDao buyerDao(){return buyerDao;}
    public static PhoneDao phoneDao(){return phoneDao;}
    public static PurchaseDao purchaseDao(){return purchaseDao;}
}
