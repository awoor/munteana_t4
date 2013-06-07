/**
 * Clasa AccountListener primeste ca argument 2 obiecte de tip GUI,
 * si 1 obiect de tip Bank. In aceasta metoda vor fi toate Listenerurile
 * ferestrei AcccountGUI.
 */
package Controller;

import Model.*;
import View.AccountGUI;
import View.LogGUI;
import java.awt.event.*;

/**
 * @version 28 aprilie 2013s
 * @author Andrei
 */
public class AccountListener {
    private LogGUI gLog;
    private AccountGUI g;
    private Bank b;  
    
    /**
     * Constructorul
     * @param gLog obiect de tip LogGUI
     * @param g obiect de tip AccounGUI
     * @param b obiect de tip Bank
     */
    public AccountListener(LogGUI gLog, AccountGUI g, Bank b){
        this.gLog = gLog;
        this.g = g;
        this.b = b;
        g.adaugaWindowListener(windLsn);
        g.adaugaListenerBtnBack(acLisButExit);
        g.adaugaListenerBtnSet(acLisButSet);
        g.adaugaListenerBtnNewAcc1(acLisButNewAcc1);
        g.adaugaListenerBtnNewAcc2(acLisButNewAcc2);
        g.addCoBox1ItLis(addItemListener1);
        g.adaugaListenerBtnOKAdauga(acLisButOKAdauga);
        g.adaugaListenerBtnOKDepune(acLisButOKDepune);
        g.adaugaListenerBtnOKExtrag(acLisButOKExtrage);
        g.adaugaListenerBtnOKPlata(acLisButOKPlateste);
        g.adaugaListenerBtnGetInterest(acLisButAddInterest);
        g.addCoBoxDepune(addItemListenerDepune);
        
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
    
    private ActionListener acLisButNewAcc1 = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            b.createAccount(Var.idLogin, null, 1);
            g.loadCoBox1(b);
            g.showCont(b);
            g.showError(0, "You have created a new Saving Account!");
        }
        
    }; 
    private ActionListener acLisButNewAcc2 = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            b.createAccount(Var.idLogin, null, 2);
            g.loadCoBox1(b);
            g.showCont(b);
            g.showError(0, "You have created a new Spending Account!");
        }
        
    }; 
    
    private ActionListener acLisButExit = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            gLog.setVisible(true);
            g.setVisible(false);
        }
        
    }; 
    
    private ActionListener acLisButSet = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            String data[] = g.getPersonData();
            boolean bun[] = {true,true,true,true};
            String sch[] = {"","",""};
            Person p = b.readPerson(Var.idLogin);
            try{
                assert (data[0].length()>3);
            }catch(AssertionError ex) {
                g.showError(1, "First Name is too short!");
                bun[0]=false;
            }            
            try{
                assert (data[1].length()>3);
            }catch(AssertionError ex) {
                g.showError(1, "Last Name is too short!");
                bun[1]=false;
            }            
            try{
                assert (data[2].length()>3);
            }catch(AssertionError ex) {
                g.showError(1, "Country Name is too short!");
                bun[2]=false;
            }                  
            try{
                assert (data[0].length()<16);
            }catch(AssertionError ex) {
                g.showError(1, "First Name is too long!");
                bun[0]=false;
            }                      
            try{
                assert (data[1].length()<16);
            }catch(AssertionError ex) {
                g.showError(1, "Last Name is too long!");
                bun[1]=false;
            }                   
            try{
                assert (data[2].length()<16);
            }catch(AssertionError ex) {
                g.showError(1, "Country Name is too long!");
                bun[2]=false;
            }      
            if ((data[3].length())==0) bun[3]=false;
            if ((data[3].length())>0)
            if ((data[3].length())<4) {g.showError(1, "Password is too short!");bun[3]=false;}
            if ((data[3].length())>15) {g.showError(1, "Password is too long!");bun[3]=false;}
            if ((data[3].compareTo(data[4]))!=0) {g.showError(1, "Passwords doesn't match!");bun[3]=false;}
            if (bun[0]) if (data[0].compareTo(p.getFirstName())!=0){
                g.showError(0, "First Name has been changed!"); sch[0]=data[0];
                p.setFirstName(data[0]);
            }            
            if (bun[1]) if (data[1].compareTo(p.getLastName())!=0){
                g.showError(0, "Last Name has been changed!"); sch[1]=data[1];
                p.setLastName(data[1]);
            }            
            if (bun[2]) if (data[2].compareTo(p.getCountry())!=0){
                g.showError(0, "Country Name has been changed!"); sch[2]=data[2];
                p.setCountry(data[2]);
            }         
            if (bun[3]) if (data[3].compareTo(p.getPassword())!=0){
                g.showError(0, "Password has been changed!");
                p.setPassword(data[3]);
            }
            

            
            g.setPersonData(sch);
        }
        
    }; 
    
    private ItemListener addItemListener1 = new ItemListener(){
        public void itemStateChanged(ItemEvent e) {
            if ( e.getStateChange() == 1 ){
                g.showCont(b);
            }
        }
    };    
    
    private ItemListener addItemListenerDepune = new ItemListener(){
        public void itemStateChanged(ItemEvent e) {
            if ( e.getStateChange() == 1 ){
                g.scrieContDepuneri();
            }
        }
    };  
    
    private int[] verificaTranzactie(String s[], int tip1, int tip2){
        int data[] = {-1,0,0,0};
        boolean er=false;
        int nr, nr2;
        int id=0, id2=0;
        
        try{
            nr = Integer.parseInt(s[0]);
            Account a = b.readAccount(nr);
            id = a.getId();
            if ( (tip1==1) && (a.getTip()!=1) ) er=true;
            if ( (tip1==2) && (a.getTip()!=2) ) er=true;
            
            //destinatar
            nr2 = Integer.parseInt(s[2]);
            Account a2 = b.readAccount(nr2);
            id2 = a2.getId();
            if ( (tip2==1) && (a2.getTip()!=1) ) er=true;
            if ( (tip2==2) && (a2.getTip()!=2) ) er=true;
        }
        catch(Exception ex){
            er = true;
        }
        if (er){
            g.showError(1, "Invalid Account!");
            return data;
        }
        data[1] = id;
        data[3] = id2;
        
        nr=0;
        try{
            nr = Integer.parseInt(s[1]);
        }
        catch(Exception ex){
            er = true;
        }
        if ( (nr<1) || (nr>1000000) ) er=true;
        if (er){
            g.showError(1, "Introduceti o suma de bani valida!");
            return data;
        }
        data[2] = nr;
        data[0] = 0;
        return data;
    }
    
    private ActionListener acLisButAddInterest = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                int id = g.getAccount(b);
                Account a = b.readAccount(id);
                SavingAccount sa = (SavingAccount)a;
                b.addInterests(sa);
                g.showBalances(a);
                g.showError(0, "Interests has been rised with "+Var.procPerClick+
                        " %");
            }
            catch (Exception ex){
                g.showError(1, "Invalid Account!");
            }
        }
        
    }; 
    
    private ActionListener acLisButOKAdauga = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            int nr[] = verificaTranzactie(g.getBaniAdauga(),2,0);
            if (nr[0]==-1)return;
            Account a = b.readAccount(nr[1]);
            a.setBalance(a.getBalance()+nr[2]);
            g.showBalances(a);
            b.makeTranzaction(b.readAccount(nr[1]), b.readAccount(nr[3]), nr[2]);
        }
        
    }; 
    private ActionListener acLisButOKPlateste = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            int nr[] = verificaTranzactie(g.getBaniPlata(),2,0);
            if (nr[0]==-1)return;
            Account a = b.readAccount(nr[1]);
            int bani = a.getBalance();
            if (nr[2]>bani){
                g.showError(1, "Bani insuficienti!");
                return;
            }
            
            a.setBalance(a.getBalance()-nr[2]);
            SpendingAccount sa = (SpendingAccount)a;
            sa.addBalanceSpended(nr[2]);
            g.showBalances(a);
            b.makeTranzaction(b.readAccount(nr[1]), b.readAccount(nr[3]), -nr[2]);
        }
        
    }; 
    private ActionListener acLisButOKDepune = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            int nr[] = verificaTranzactie(g.getBaniDepuneri(),2,0);
            if (nr[0]==-1)return;
            Account a = b.readAccount(nr[1]);
            int bani = a.getBalance();
            if (nr[2]>bani){
                g.showError(1, "Bani insuficienti!");
                return;
            }
            
            Account a2 = b.readAccount(nr[3]);
            a2.setBalance(a2.getBalance()+nr[2]);
            
            a.setBalance(a.getBalance()-nr[2]);
            g.showBalances(a);
            b.makeTranzaction(b.readAccount(nr[1]), b.readAccount(nr[3]), nr[2]);
        }
        
    }; 
    private ActionListener acLisButOKExtrage = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            int nr[] = verificaTranzactie(g.getBaniExtrageri(),1,2);
            if (nr[0]==-1)return;
            Account a = b.readAccount(nr[1]);
            int bani = a.getBalance();
            if (nr[2]>bani){
                g.showError(1, "Bani insuficienti!");
                return;
            }
            
            Account a2 = b.readAccount(nr[3]);
            a2.setBalance(a2.getBalance()+nr[2]);

            
            a.setBalance(a.getBalance()-nr[2]);
            g.showBalances(a);
            b.makeTranzaction(b.readAccount(nr[1]), b.readAccount(nr[3]), nr[2]);
        }
        
    }; 
    
    
}
