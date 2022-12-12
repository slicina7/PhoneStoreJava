package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Buyer {
    private int id;
    private String name;
    private String surname;
    private String account_number;
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

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
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
                ", account_number=" + account_number +
                ", password=" + password +
                ", account_number=" + account_balance +
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
        return Objects.hash(id, name, surname, account_number, password, account_balance);
    }
}