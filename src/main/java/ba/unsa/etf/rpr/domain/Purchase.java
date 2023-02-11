package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Holds all purchases made by buyers
 *
 * @author Selma Licina
 */

public class Purchase implements Idable {
    private int id;
    private Buyer buyer;
    private Phone phone;

    public Purchase() {
    }

    public Purchase(Buyer buyer, Phone phone) {
        this.buyer = buyer;
        this.phone = phone;
    }

    /**
     * Gets the purchase ID.
     * @return purchase ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the purchase ID.
     * @param id the purchase ID to be set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the purchase buyer.
     * @return buyer of this purchase
     */
    public Buyer getBuyer() {
        return buyer;
    }

    /**
     * Sets the purchase buyer
     * @param buyer the purchase buyer to be set
     */
    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    /**
     * Gets the purchase phone
     * @return phone of this purchase
     */
    public Phone getPhone() {
        return phone;
    }

    /**
     * Sets the purchase phone
     * @param phone the purchase phone to be set
     */
    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    /**
     * Returns a string representation of this purchase
     * @return this purchases id.
     */
    @Override
    public String toString(){
        return phone.getId()+"";
    }

    /**
     * Determines if this Purchase object is equal to another object.
     * @param o The object to compare to this Purchase object.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return id == purchase.id;
    }

    /**
     * Generates a hash code for the phone object based on its purchase ID, buyer and phone.
     * @return an integer value representing the hash code of the purchase object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, buyer, phone);
    }
}
