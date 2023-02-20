package ba.unsa.etf.rpr;
import ba.unsa.etf.rpr.business.BrandManager;
import ba.unsa.etf.rpr.business.BuyerManager;
import ba.unsa.etf.rpr.business.PhoneManager;
import ba.unsa.etf.rpr.domain.Brand;
import ba.unsa.etf.rpr.domain.Buyer;
import ba.unsa.etf.rpr.domain.Phone;
import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

public class App {
    private static final Option addBrand=new Option("addBr","add-brand",false,"Adding new brand in phone-store database");
    private static final Option addPhone=new Option("addPh","add-phone",false,"Adding new phone in phone-store database");
    private static final Option addBuyer=new Option("addBu","add-buyer",false,"Adding new buyer in phone-store database");
    private static final Option addPurchase=new Option("addPu","add-purchase",false,"Adding new purchase in phone-store database");
    private static final Option getBrand=new Option("getBr","get-brand",false,"Printing all brands from phone-store database");
    private static final Option getPhone=new Option("getPh","get-phone",false,"Printing all phones from phone-store database");
    private static final Option getBuyer=new Option("getBu","get-buyer",false,"Printing all buyers from phone-store database");
    private static final Option getPurchase=new Option("getPu","get-purchase",false,"Printing purchases brands from phone-store database");

    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar projekat.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addBrand);
        options.addOption(addPhone);
        options.addOption(addBuyer);
        options.addOption(addPurchase);
        options.addOption(getBrand);
        options.addOption(getPhone);
        options.addOption(getBuyer);
        options.addOption(getPurchase);
        return options;
    }
    public static void main(String[] args) throws Exception {
        Options options = addOptions();

        CommandLineParser commandLineParser = new DefaultParser();

        CommandLine cl = commandLineParser.parse(options, args);

        if((cl.hasOption(addPhone.getOpt()) || cl.hasOption(addPhone.getLongOpt()))){
            PhoneManager phoneManager=new PhoneManager();
            BrandManager brandManager=new BrandManager();
            try {
                Brand brand = brandManager.searchByName(cl.getArgList().get(0));
                Phone phone=new Phone();
                phone.setBrand(brand);
                phone.setVersion(cl.getArgList().get(1));
                phone.setPrice(Integer.parseInt(cl.getArgList().get(2)));
                phone.setIn_stock(Integer.parseInt(cl.getArgList().get(3)));
                phone.setRelease_date(Date.valueOf(cl.getArgList().get(4)));
                phoneManager.insert(phone);
                System.out.println("You successfully added phone to database!");
            }catch (Exception e){
                System.out.println("Problem with adding to db");
            }
        } else if(cl.hasOption(getPhone.getOpt()) || cl.hasOption(getPhone.getLongOpt())){
            PhoneManager phoneManager=new PhoneManager();
            phoneManager.getAll().forEach(q -> System.out.println(q.getBrand().getName()+" "+q.getVersion()));

        } else if(cl.hasOption(addBuyer.getOpt()) || cl.hasOption(addBuyer.getLongOpt())){
            try {
                BuyerManager buyerManager=new BuyerManager();
                Buyer buyer=new Buyer(cl.getArgList().get(0),cl.getArgList().get(1),cl.getArgList().get(2),cl.getArgList().get(3),cl.getArgList().get(4),Integer.parseInt(cl.getArgList().get(5)));
                buyerManager.insert(buyer);
                System.out.println("You successfully added buyer to database!");

            }catch(Exception e) {
                System.out.println("Problem with adding to db");
            }

        } else if(cl.hasOption(getBrand.getOpt()) || cl.hasOption(getBrand.getLongOpt())){
            BrandManager brandManager=new BrandManager();
            brandManager.getAll().forEach(q -> System.out.println(q.getName()));

        } else {
            printFormattedOptions(options);
            System.exit(-1);

        }
    }
}
