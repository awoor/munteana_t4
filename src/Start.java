
/**
 * Clasa Start porneste programul.
 * Creeaza 1 obiect model de tip bank,
 * 3 obicte de tip GUI si alte 3 obiecte de tip Listener.
 * Listenerurile primesc ca argumente obiectele de tip GUI ca sa poata interactiona
 * intre ele si acel obiect de tip Bank.
 */

import Controller.AccountListener;
import Controller.BankListener;
import Controller.LogListener;
import Model.Bank;
import View.AccountGUI;
import View.BankGUI;
import View.LogGUI;

/**
 * @version 28 aprilie 2013
 * @author Andrei
 */
public class Start {
    
    
    /**
     * metoda main care este apelata la deschiderea programului java
     * @param args argumentele care in cazul nostru nu sunt folosite
     */
    public static void main(String[] args){
        newStyle();
        Bank b = new Bank();
        b.loadBank();
        LogGUI g1 = new LogGUI();
        BankGUI g2 = new BankGUI();
        AccountGUI g3 = new AccountGUI();
        LogListener l1 = new LogListener(g1,g2,g3,b);
        AccountListener l2 = new AccountListener(g1,g3,b);
        BankListener l3 = new BankListener(g1,g2,b);
    }
    
    /**
     * metoda care schimba stilul ferestrelor grafice
     * in cazul in care stilul Nimbus nu a fost gasit se va afisa stilul default
     */
    private static void newStyle(){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                }
            }
        } catch (Exception ex) {
            System.out.println("getStyle exception: \n"+ex.toString());
        }
    }
    
    
}
