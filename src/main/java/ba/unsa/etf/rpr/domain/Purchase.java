package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Holds all purchases made by buyers
 *
 * @author Selma Licina
 */

public class Purchase implements IdField{
    private int id;
    private Buyer buyer;
    private Phone phone;

    public Purchase() {
    }

    public Purchase(Buyer buyer, Phone phone) {
        this.buyer = buyer;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }
    @Override
    public String toString(){
        return phone.getId()+"";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return id == purchase.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, buyer, phone);
    }
}
