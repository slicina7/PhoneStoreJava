package ba.unsa.etf.rpr.domain;
/**
 * This inteface will insure us that all POJO beans will have ID field
 */
public interface Idable {
    void setId(int id);

    int getId();
}
