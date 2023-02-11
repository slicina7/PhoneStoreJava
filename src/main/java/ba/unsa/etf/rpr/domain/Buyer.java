package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * List of buyers
 *
 * @author Selma Licina
 */

public class Buyer implements Idable {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String account_number;
    private String password;
    private int account_balance;

    public Buyer() {
    }

    public Buyer(String name, String surname, String email, String account_number, String password, int account_balance) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.account_number = account_number;
        this.password = password;
        this.account_balance = account_balance;
    }

    /**
     * Gets the buyer ID.
     * @return buyer ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the buyer ID.
     * @param id the buyer ID to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the buyer name.
     * @return buyer name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the buyer name.
     * @param name the buyer name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the buyer last name.
     * @return buyer last name.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the buyer last name.
     * @param surname the buyer surname to be set.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the buyer email.
     * @return buyer email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the buyer email.
     * @param email the buyer email to be set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the buyer account number.
     * @return buyer account number.
     */
    public String getAccount_number () {
        return account_number;
    }

    /**
     * Sets the buyer account number.
     * @param account_number the buyer account number to be set.
     */
    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    /**
     * Gets the buyer password.
     * @return buyer password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the buyer password.
     * @param password the buyer password to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the buyer account balance.
     * @return buyer account balance.
     */
    public int getAccount_balance() {
        return account_balance;
    }

    /**
     * Sets the buyer account balance.
     * @param account_balance the buyer account balance to be set.
     */
    public void setAccount_balance(int account_balance) {
        this.account_balance = account_balance;
    }

    /**
     * Returns a string representation of this buyer object.
     * @return A string representation of this buyer object.
     */
    @Override
    public String toString(){
        return  "First name :       " + name + "\n"+
                "Last name :        " + surname +"\n"+
                "Email :            " + email +"\n"+
                "Account number :   " + account_number+"\n"+
                "Password :         " + password +"\n"+
                "Account balance :  " + account_balance ;
    }

    /**
     * Determines if this Buyer object is equal to another object.
     * @param o The object to compare to this Buyer object.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buyer buyer = (Buyer) o;
        return id == buyer.id;
    }

    /**
     * Generates a hash code for the Buyer object based on its ID, source, name ,surname ,email , account number,
     * password and account balance.
     * @return an integer value representing the hash code of the buyer object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, password,account_number, account_balance);
    }
}
