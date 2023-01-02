package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Buyer;
import ba.unsa.etf.rpr.exception.BuyerException;
import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class BuyerDaoSQLImpl extends AbstractDao<Buyer> implements BuyerDao{

    public BuyerDaoSQLImpl(){super("buyers");}

    @Override
    public Buyer rowToObject(ResultSet rs) throws BuyerException {
        try{
            Buyer buyer=new Buyer();
            buyer.setId(rs.getInt("id"));
            buyer.setName(rs.getString("name"));
            buyer.setSurname(rs.getString("surname"));
            buyer.setEmail(rs.getString("email"));
            buyer.setAccount_number(rs.getString("account_number"));
            buyer.setPassword(rs.getString("password"));
            buyer.setAccount_balance(rs.getInt("account_balance"));
            return buyer;
        }catch(SQLException e){
            throw new BuyerException(e.getMessage(),e);
        }
    }

    @Override
    public Map<String, Object> objectToRow(Buyer object) {
        Map<String,Object> item=new TreeMap<>();
        item.put("id",object.getId());
        item.put("name",object.getName());
        item.put("surname",object.getSurname());
        item.put("email",object.getEmail());
        item.put("account_number",object.getAccount_number());
        item.put("password",object.getPassword());
        item.put("account_balance",object.getAccount_balance());
        return item;
    }

    @Override
    public Buyer searchByEmailAndPassword(String email,String password) throws BuyerException {
        return executeQueryUnique("\"SELECT * FROM buyers WHERE email = ? AND password = ?",new Object[]{email,password});
    }
}
