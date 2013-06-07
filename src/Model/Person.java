/**
 * Clasa care salveaza o persoana cu urmatoarele date:
 * id-ul unic al persoanei, primul nume, al doilea nume, tara, parola,
 * nr de conturi pe care le detine, data crearii persoanei.
 */
package Model;

import java.io.Serializable;
import java.util.Date;

/**
 * @version 28 aprilie 2013
 * @author Andrei
 */
public class Person implements Serializable{
    private String id, firstName, lastName, country, password;
    private int nrAcc;
    private Date date;

    /**
     * Constructorul care creeaza o persoana
     * @param id id-ul unic de tip String
     * @param pass parola acelei persoane
     * @param nrAcc numarul de conturi initiale
     */
    public Person(String id, String pass, int nrAcc){
        this.id = id;
        this.nrAcc = nrAcc;
        firstName = "";
        lastName = "";
        country = "";
        date = new Date();
        password = pass;
    }
    
    /**
     * Constructorul 2 de creare a unei persoane
     * @param id id-ul unic al persoanei
     * @param pass parola.
     */
    public Person(String id, String pass){
        this(id, pass, 1);
    }

    public String getId(){
        return id;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getCountry(){
        return country;
    }
    public String getPassword(){
        return password;
    }
    public Date getDate(){
        return date;
    }
    public int getNrAcc(){
        return nrAcc;
    }
    
    public void setFirstName(String s){
        firstName = s;
    }
    public void setLastName(String s){
        lastName = s;
    }
    public void setCountry(String s){
        country = s;
    }
    public void setPassword(String s){
        password = s;
    }
    
    public void addNrAcc(){
        nrAcc++;
    }
    
    public void decNrAcc(){
        nrAcc--;
    }
    
    
}
