/**
 * Clasa Account va salva un account care contine:
 * ID-ul unic, Tipul contului (spending/saving), data inregistrarii,
 * si suma de bani.
 */
package Model;

import java.io.Serializable;
import java.util.Date;

/**
 * @version 27 aprilie 2013
 * @author Andrei
 */
public abstract class Account implements Serializable{
    
    private int id;
    //1 = SavingAccount, 2 = SpendingAccount
    private int tip; 
    private Date dateReg;
    private int balance;
    
    /**
     * Constructorul care poate fi apelat doar de catre subclasele
     * SavingAccount si SpendingAccount
     * @param id - id-ul accountului
     * @param tip -tip-ul: Saving/Spending
     */
    protected Account(int id, int tip){
        this.id = id;
        this.dateReg = new Date();
        this.tip = tip;
        balance = 0;
    }
    
    /**
     * @return id-ul accountului 
     */
    public int getId(){
        return id;
    }
    
    /**
     * @return suma de bani 
     */
    public int getBalance(){
        return balance;
    }
    /**
     * @return tipul accountului 1 sau 2 
     */
    public int getTip(){
        return tip;
    }
    /**
     * @return data inregistrarii accountului
     */
    public Date getDate(){
        return dateReg;
    }
    
    /**
     * seteaza suma de bani
     * @param i - cantitatea de bani care va fi resetata
     */
    public void setBalance(int i){
        balance = i;
    }
    
}
