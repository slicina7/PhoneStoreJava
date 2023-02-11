package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Holds different types of phone brands
 *
 * @author Selma Licina
 */

public class Brand implements Idable {
    private int id;
    private String name;

    public Brand(String name) {
        this.name = name;
    }

    /**
     * Gets the brand ID.
     * @return brand ID
     */
    public int getId(){
        return id;
    }

    /**
     * Sets the brand ID.
     * @param id the brand ID to be set
     */
    public void setId(int id){
        this.id=id;
    }

    /**
     * Gets the brand name.
     * @return brand name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the brnd name.
     * @param name the brand name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of this brand.
     * @return this brands name.
     */
    @Override
    public String toString(){
        return name;
    }

    /**
     * Determines if this Brand object is equal to another object.
     * @param o The object to compare to this Brand object.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass()!=o.getClass()) return false;
        Brand brand=(Brand) o;
        return id==brand.id;
    }

    /**
     * Generates a hash code for the brand object based on its brand ID and name.
     * @return an integer value representing the hash code of the brand object.
     */
    @Override
    public int hashCode(){
        return Objects.hash(id,name);
    }
}
