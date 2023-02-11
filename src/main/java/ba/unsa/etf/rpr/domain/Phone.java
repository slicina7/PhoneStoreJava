package ba.unsa.etf.rpr.domain;

import java.sql.Date;
import java.util.Objects;

/**
 * Holds different types of phone versions
 *
 * @author Selma Licina
 */

public class Phone implements Idable {
    private int id;
    private Brand brand;
    private String version;
    private int price;
    private int in_stock;
    private java.sql.Date release_date;

    public Phone(){}
    public Phone(Brand brand, String version, int price, int in_stock, Date release_date) {
        this.brand = brand;
        this.version = version;
        this.price = price;
        this.in_stock = in_stock;
        this.release_date = release_date;
    }

    /**
     * Gets the phone ID.
     * @return phone ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the phone ID.
     * @param id the phone ID to be set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the phone brand.
     * @return phone brand
     */
    public Brand getBrand() {
        return brand;
    }

    /**
     * Sets the phone brand
     * @param brand -the phone brand to be set
     */
    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    /**
     * Gets the phone version.
     * @return phone version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the phone version
     * @param version -the phone version to be set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Gets the phone price.
     * @return phone price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the phone price
     * @param price -the phone price to be set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Gets the number of phones in stock.
     * @return number of phones in stock
     */
    public int getIn_stock() {
        return in_stock;
    }

    /**
     * Sets the number of phones in stock
     * @param in_stock -number of phones in stock to be set
     */
    public void setIn_stock(int in_stock) {
        this.in_stock = in_stock;
    }

    /**
     * Gets the phone release date.
     * @return phone release date
     */
    public java.sql.Date getRelease_date() {
        return release_date;
    }

    /**
     * Sets the phone release date
     * @param release_date -phone release date to be set
     */
    public void setRelease_date(java.sql.Date release_date) {
        this.release_date = release_date;
    }

    /**
     * Returns a string representation of this phone.
     * @return this phones id,brand,version,in_stock,release_date.
     */
    @Override
    public String toString(){
        return "Phone{" +
        "id=" + id +
        ", brand=" +  brand +
        ", version=" +  version +
        ", price=" + price +
        ", in_stock=" + in_stock +
        ", release_date=" + release_date +
        '}';
    }

    /**
     * Determines if this Phone object is equal to another object.
     * @param o The object to compare to this Phone object.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return id == phone.id;
    }

    /**
     * Generates a hash code for the phone object based on its phone ID, brand,version,price,in stock and release date.
     * @return an integer value representing the hash code of the phone object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, brand, version, price, in_stock, release_date);
    }
}
