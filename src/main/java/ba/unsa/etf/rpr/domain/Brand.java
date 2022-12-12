package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Holds different types of phone brands
 *
 * @author Selma Licina
 */

public class Brand {
    private int id;
    private String name;
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "Brand" +
                "id=" + id +
                ", name=" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass()!=o.getClass()) return false;
        Brand brand=(Brand) o;
        return id==brand.id;
    }
    @Override
    public int hashCode(){
        return Objects.hash(id,name);
    }
}
