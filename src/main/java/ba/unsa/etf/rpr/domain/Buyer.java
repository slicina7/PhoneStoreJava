package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * List of buyers
 *
 * @author Selma Licina
 */

public class Buyer {
    private int id;
    private String name;
    private String surname;
    private String email;
    private int password;
    private int account_balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public int getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(int account_balance) {
        this.account_balance = account_balance;
    }
    @Override
    public String toString(){
        return "Buyer{" +
                "id=" + id +
                ", name=" +  name +
                ", surname=" +  surname +
                ", email=" + email +
                ", password=" + password +
                ", account_balance=" + account_balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buyer buyer = (Buyer) o;
        return id == buyer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, password, account_balance);
    }
}
