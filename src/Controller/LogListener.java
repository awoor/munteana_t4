/**
 * Clasa LogListener primeste ca argument 3 obiecte de tip GUI,
 * si 1 obiect de tip Bank. In aceasta metoda vor fi toate Listenerurile
 * ferestrei LogGUI.
 */
package Controller;

import Model.Bank;
import Model.Person;
import Model.Var;
import View.AccountGUI;
import View.BankGUI;
import View.LogGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * @version 28 aprilie 2013
 * @author Andrei
 */
public class LogListener {
    
    private LogGUI g;
    private BankGUI gBank;
    private AccountGUI gAcc;
    
    private Bank b;
    
    /**
     * Constructorul
     * @param g Obiect de tip LogGUI
     * @param gBank Obiect de tip BankGUI
     * @param gAcc obiect de tip AccountGUI
     * @param b obiect de tip Bank
     */
    public LogListener(LogGUI g, BankGUI gBank, AccountGUI gAcc, Bank b){
        this.g = g;
        this.b = b;
        this.gAcc = gAcc;
        this.gBank = gBank;
        g.addBtBankAcList(acLisButAdmin);
        g.addBtAccountLoginAcList(acLisButAccountL);
        g.addBtAccountCreateAcList(acLisButAccountC);
        g.addBtExitAcList(acLisButExit);
        g.adaugaWindowListener(windLsn);
    }

    /**
     * atunci cand se inchide fereastra
     */
    private WindowListener windLsn = new WindowListener(){
        public void windowOpened(WindowEvent e) {}
        public void windowClosing(WindowEvent e) {
            b.saveBank();
        }
        public void windowClosed(WindowEvent e) {}
        public void windowIconified(WindowEvent e) {}
        public void windowDeiconified(WindowEvent e) {}
        public void windowActivated(WindowEvent e) {}
        public void windowDeactivated(WindowEvent e) {}
    };
    
    private ActionListener acLisButAccountL = new ActionListener(){
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String log[] = g.getAccountLog(2);
            if (!"0".equals(log[0])) {
                g.setLoginMessage(Integer.parseInt(log[0]));
                return;
            }
            Person p = b.readPerson(log[1]);
            if (p==null){
                g.setLoginMessage(11);
                return;
            }
            String pass = p.getPassword();
            if (pass.compareTo(log[2])!=0){
                g.setLoginMessage(12);
                return;
            }
            Var.idLogin=log[1];
            g.setVisible(false);
            gAcc.init(b);
        }
        
    };
    
    
    private ActionListener acLisButAccountC = new ActionListener(){
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String log[] = g.getAccountLog(1);
            if (!"0".equals(log[0])) {
                g.setLoginMessage(Integer.parseInt(log[0]));
                return;
            }
            Person p = b.readPerson(log[1]);
            if (p!=null){
                g.setLoginMessage(15);
                return;
            }
            b.createAccount(log[1],log[2], 2);
            Var.idLogin=log[1];
            g.setVisible(false);
            gAcc.init(b);
        }
        
    };
    
    private ActionListener acLisButExit = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            b.saveBank();
            System.exit(0);
        }

    };
    
    private ActionListener acLisButAdmin = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            String log[] = g.getAccountLog(3);
            if (!"0".equals(log[0])) {
                g.setLoginMessage(Integer.parseInt(log[0]));
                return;
            }
            if ( (log[1].compareTo(Var.adminLogin)!=0) || 
                    (log[2].compareTo(Var.adminPass)!=0) ){
                g.setLoginMessage(10);
                return;
            }
            g.setLoginMessage(0);
            g.setVisible(false);
            gBank.init(b);
        }
        
    };    
    
    
}
