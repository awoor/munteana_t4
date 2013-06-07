/**
 * clasa care contine un obiect de tip SavingAccount
 * extinde obiectul Account, are ca date o variabila totalInterests
 */
package Model;

import java.io.Serializable;

/**
 * @version 28 aprilie 2013
 * @author Andrei
 */
public class SavingAccount extends Account implements Serializable{
    
    private int totalInterests;
    
    /**
     * constructorul care creeaza acest obiect
     * @param id id-ul unic al persoanei
     */
    public SavingAccount(int id){
        super(id, 1);
        totalInterests = 0;
    }
    
    /**
     * se adauga numarul de bani castigati
     * @param i cantitatea de bani care va fi adaugata
     */
    public void addInterests(int i){
        totalInterests+=i;
    }
    
    /**
     * @return numarul de bani castigati din dobande.
     */
    public int getTotalInterests(){
        return totalInterests;
    }
    
}
