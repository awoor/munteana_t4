/**
 * clasa care contine un obiect de tip SpendingAccount
 * extinde obiectul Account, are ca date o variabila balanceSpended
 */
package Model;

import java.io.Serializable;

/**
 * @version 28 aprilie 2013
 * @author Andrei
 */
public class SpendingAccount extends Account implements Serializable{
    
    private int balanceSpended;
    
    /**
     * constructorul
     * @param id id-ul unic al contului
     */
    public SpendingAccount(int id){
        super(id, 2);
        balanceSpended = 0;
    }
    
    /**
     * @return numarul de bani care au fost cheltuiti 
     */
    public int getBalanceSpended(){
        return balanceSpended;
    }
    
    /**
     * metoda care adauga numarul total de bani care au fost cheltuiti
     * @param i cantitatea de bani care va fi adaugata.
     */
    public void addBalanceSpended(int i){
        balanceSpended+=i;
    }
    
}
