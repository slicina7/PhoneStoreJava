package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

/**
 * Holds different types of phone versions
 *
 * @author Selma Licina
 */

public class Phone {
    private int id;
    private Brand brand;
    private String version;
    private int price;
    private int in_stock;
    private java.sql.Date release_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIn_stock() {
        return in_stock;
    }

    public void setIn_stock(int in_stock) {
        this.in_stock = in_stock;
    }

    public java.sql.Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(java.sql.Date release_date) {
        this.release_date = release_date;
    }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return id == phone.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, version, price, in_stock, release_date);
    }
}
