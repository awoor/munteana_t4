/**
 * Clasa AccountListener primeste ca argument 2 obiecte de tip GUI,
 * si 1 obiect de tip Bank. In aceasta metoda vor fi toate Listenerurile
 * ferestrei BankGUI.
 */
package Controller;

import Model.Account;
import Model.Bank;
import Model.Person;
import View.BankGUI;
import View.LogGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @version 28 aprilie 2013
 * @author Andrei
 */
public class BankListener {

    private LogGUI gLog;
    private BankGUI g;
    private Bank b;

    /**
     * Constructorul
     * @param gLog Obiect de tip LogGUI
     * @param g Obiect de tip BankGUI
     * @param b Obiect de tip Bank
     */
    public BankListener(LogGUI gLog, BankGUI g, Bank b) {
        this.gLog = gLog;
        this.g = g;
        this.b = b;
        g.adaugaWindowListener(windLsn);
        g.adaugaListenerBtnBack(acLisButExit);
        g.adaugaRadioListener(adaugaChangeListener);
        g.adaugaListenerBtnRemove1(acLisButRemove1);
        g.adaugaListenerBtnRemove2(acLisButRemove2);
        g.adaugaListenerBtnRemove3(acLisButRemove3);
        g.adaugaListenerBtnRemove3All(acLisButRemove3All);
    }
    /**
     * atunci cand inchidem fereastra.
     */
    private WindowListener windLsn = new WindowListener(){
        public void windowClosing(WindowEvent arg0) {
            b.saveBank();
        }
        public void windowOpened(WindowEvent arg0) {}
        public void windowClosed(WindowEvent arg0) {}
        public void windowIconified(WindowEvent arg0) {}
        public void windowDeiconified(WindowEvent arg0) {}
        public void windowActivated(WindowEvent arg0) {}
        public void windowDeactivated(WindowEvent arg0) {}
    };
    
    private ActionListener acLisButExit = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            gLog.setVisible(true);
            g.setVisible(false);
        }
    };
    private ChangeListener adaugaChangeListener = new ChangeListener() {

        @Override
        public void stateChanged(ChangeEvent e) {
            g.showJos(b, false);
        }
    };
    private ActionListener acLisButRemove1 = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String id = g.getStringId();
                Person p = b.readPerson(id);
                b.removePerson(p);
            } catch (Exception ex) {
                g.showError(1);
            }
            g.showJos(b, true);
        }
    };
    private ActionListener acLisButRemove2 = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = g.getIntId();
                Account a = b.readAccount(id);
                b.removeAccount(a);
            } catch (Exception ex) {
                g.showError(1);
            }
            g.showJos(b, true);
        }
    };
    private ActionListener acLisButRemove3 = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String date = g.getStringId();
                b.removeTranzaction(date);
            } catch (Exception ex) {
                g.showError(1);
            }
            g.showJos(b, true);
        }
    };
    private ActionListener acLisButRemove3All = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                b.removeAllTranzaction();
            } catch (Exception ex) {
                g.showError(1);
            }
            g.showJos(b, true);
        }
    };
}
