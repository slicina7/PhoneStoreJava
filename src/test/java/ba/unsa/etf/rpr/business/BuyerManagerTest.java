package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.BuyerDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Buyer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuyerManagerTest {
    private BuyerManager buyerManager;
    private Buyer buyer;
    private BuyerDaoSQLImpl buyerDaoSQLMock;
    private List<Buyer> buyers;
    @Test
    void getAll() {
    }
}