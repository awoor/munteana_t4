/**
 * Clasa Bank va stoca un bank intreg care contine:
 * un tablou Hashtable cu toate accounturile create, precum si 
 * tranzactiile care au avut loc.
 */
package Model;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.JOptionPane;

/**
 * @version 28 aprilie 2013
 * @author Andrei
 */
public class Bank implements Serializable, BankProc{
    private Hashtable table;
    private ArrayList<Object[]> tranz;
    
    /**
     * constructorul va instantia o variabila de tip Hashtable
     * si va pune ceva date initiale in ea. Va mai instantia
     * de asemenea, un obiect tranz de tip ArrayList<Object[]>
     */
    public Bank(){
        table = new Hashtable();
        Person p;
        Account a1,a2;
        p = new Person("Awoor","Andaluz",3);
        p.setFirstName("Andrei");
        p.setLastName("Muntean");
        p.setCountry("Romania");
        a1 = new SavingAccount(100001);
        table.put(a1, p); 
        a1 = new SavingAccount(100002);
        table.put(a1, p); 
        a1 = new SpendingAccount(200001);
        table.put(a1, p); 
        a1.setBalance(700);
        p = new Person("Western","digital",1);
        p.setFirstName("Ioan");
        p.setLastName("Dragutanu");
        p.setCountry("Moldova");
        a2 = new SavingAccount(100010);
        a2.setBalance(300);
        table.put(a2, p); 
        tranz = new ArrayList<Object[]>();
        makeTranzaction(a1,a2,300);
    }
    
    /**
     * metoda care creeaza un nou cont
     * @param idPerson Id-ul persoanei unice
     * @param pass - parola persoanei respective
     * @param tip - tipul de cont Saving/Spending
     */
    @Override
    public void createAccount(String idPerson, String pass, int tip) {
        Person p = readPerson(idPerson);
        if (p==null) p = new Person(idPerson, pass);
        else p.addNrAcc();
        Account a;
        int id;
        boolean liber;
        
        do {
            id = (int)(Math.random()*100000)+100000;
            if (tip==2) id+=100000;
            liber = true;
            Enumeration conturi; 
            conturi = table.keys(); 
            while(conturi.hasMoreElements()) { 
                a = (Account) conturi.nextElement(); 
                if (id==a.getId()) liber = false;
            }
        }
        while (!liber);
            
        if (tip==1){
            a = new SavingAccount(id);
        }else{
            a = new SpendingAccount(id);
        }
        table.put(a, p);
    }

    /**
     * metoda care sterge un cont
     * @param a contul care trebuie sters
     */
    @Override
    public void removeAccount(Account a) {
        //Avem  conturi in tabela de dispersie
        try{
            assert (isWellFormed() == true);
        }
        catch (AssertionError ex){
                        JOptionPane.showMessageDialog(null,
                    "Nu exista conturi","Error!", 
                    JOptionPane.ERROR_MESSAGE );
        }
        try{
            assert (a != null);
        }
        catch (AssertionError ex){
                        JOptionPane.showMessageDialog(null,
                    "Nu exista un cont valida","Error!", 
                    JOptionPane.ERROR_MESSAGE );
        }
        Person p = (Person) table.get(a);
        p.decNrAcc();
        table.remove(a);
    }

    /**
     * metoda care citeste un cont
     * @param id id-ul contului care trebuie citit
     * @return un obiect de tip Account care are acel id
     */
    @Override
    public Account readAccount(int id) {
        //Avem  conturi in tabela de dispersie
        try{
            assert (isWellFormed() == true);
        }
        catch (AssertionError ex){
                        JOptionPane.showMessageDialog(null,
                    "Nu exista conturi","Error!", 
                    JOptionPane.ERROR_MESSAGE );
        }
        try{
            assert ((id >= 100000)&&(id<=999999));
        }
        catch (AssertionError ex){
                        JOptionPane.showMessageDialog(null,
                    "id invalid","Error!", 
                    JOptionPane.ERROR_MESSAGE );
        }
        Enumeration conturi; 
        Account a;
        conturi = table.keys(); 
        while(conturi.hasMoreElements()) { 
            a = (Account) conturi.nextElement(); 
            if (id==a.getId()) return a;
        }
        return null;
    }

    /**
     * metoda care returneaza Tranzactiile
     * @return obiectul tranz de tip ArrayList<Object[]>
     */
    @Override
    public ArrayList<Object[]> reportTranzactions() {
        return tranz;
    }

    /**
     * metoda care adauga dobanzile in cont
     * @param sa un obiect de tip SavingAccount in care se va adauga dobanda
     */
    @Override
    public void addInterests(SavingAccount sa) {
        int balance = sa.getBalance();
        int castig = (balance*Var.procPerClick/100);
        sa.setBalance(balance+castig);
        sa.addInterests(castig);
    }

    /**
     * metoda care citeste datele unei persoane
     * @param id unicul al persoanei
     * @return un obiect de tip Persoana cu id-ul unic
     */
    @Override
    public Person readPerson(String id) {
        //Avem  conturi in tabela de dispersie
        try{
            assert (isWellFormed() == true);
        }
        catch (AssertionError ex){
                        JOptionPane.showMessageDialog(null,
                    "Nu exista conturi","Error!", 
                    JOptionPane.ERROR_MESSAGE );
        }
        try{
            assert (id.length()>3);
        }
        catch (AssertionError ex){
                        JOptionPane.showMessageDialog(null,
                    "id invalid","Error!", 
                    JOptionPane.ERROR_MESSAGE );
        }
        Enumeration conturi; 
        Person p;
        conturi = table.elements(); 
        while(conturi.hasMoreElements()) { 
            p = (Person) conturi.nextElement(); 
            if (p.getId().compareTo(id)==0) return p;
        }
        return null;
    }

    /**
     * metoda care salveaza acest obiect
     * SERIALIZARE
     */
    @Override
    public void saveBank() {
        Object obj[] = new Object[1];
        obj[0] = this;
        FileOutputStream fout;
        ObjectOutputStream sout;
        try {
            fout = new FileOutputStream(Var.fileDateName);
            sout = new ObjectOutputStream(fout);
            sout.writeObject(obj);
            sout.flush();
            sout.close();
            fout.close();
        } catch (Exception ex) {
            System.out.println("saveBank exception: \n"+ex.toString());
        }
    }

    /**
     * metoda care citeste obiectul Bank
     * DESERIALIZARE
     */
    @Override
    public void loadBank() {
        Object obj[];
        FileInputStream fin;
	ObjectInputStream sin;
	try {
            fin = new FileInputStream(Var.fileDateName);
            sin = new ObjectInputStream(fin);
            obj = (Object[]) sin.readObject();
            sin.close();
            fin.close();
            Bank b = (Bank) obj[0];
            table = b.table;
            tranz = b.tranz;
	} catch (Exception ex) {
            //date implicite in bank:
            System.out.println("getBank exception: \n"+ex.toString());
        }
    }

    /**
     * metoda care returneaza toate conturile unei persoane
     * @param idPerson id-ul persoanei care detine aceste conturi
     * @return un obiect ArrayList<Account>
     */
    @Override
    public ArrayList<Account> getAllAccounts(String idPerson) {
        ArrayList<Account> lista = new ArrayList<Account>();
        Enumeration conturi; 
        Person p;
        Account a;
        conturi = table.keys(); 
        while(conturi.hasMoreElements()) { 
            a = (Account) conturi.nextElement();
            p = (Person) table.get(a); 
            if (p.getId().compareTo(idPerson)==0) 
                lista.add(a);
        }
        return lista;
    }

    /**
     * metoda care face o tranzactie
     * @param a1 primul cont
     * @param a2 al doilea cont
     * @param bani banii care sunt transmisi
     */
    @Override
    public void makeTranzaction(Account a1, Account a2, int bani) {
        Person p;
        Object obj[] = new Object[6];
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        obj[0] = dateFormat.format(date);
        obj[1] = a1.getId();
        p = (Person) table.get(a1);
        obj[2] = String.valueOf(p.getId())+"->"+p.getFirstName()+" "+p.getLastName();
        obj[3] = a2.getId();
        p = (Person) table.get(a2);
        obj[4] = String.valueOf(p.getId())+"->"+p.getFirstName()+" "+p.getLastName();
        obj[5] = bani;
        tranz.add(obj);
    }

    //Invariantul:
    private boolean isWellFormed() {
        boolean stare;
        if (table.isEmpty()) {
            stare = false;
        } else {
            stare = true;
        }
        return stare;
    }
    
    /**
     * metoda care returneaza toate conturile unice
     * @return un obiect ArryList<Object[]> care contine toate conturile
     */
    @Override
    public ArrayList<Object[]> reportAccounts() {
        Object obj[];
        ArrayList<Object[]> lista = new ArrayList<Object[]>();
        Enumeration conturi; 
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Account a;
        conturi = table.keys(); 
        while(conturi.hasMoreElements()) { 
            obj = new Object[8];
            a = (Account) conturi.nextElement(); 
            obj[0]=a.getId();
            obj[1]=dateFormat.format(a.getDate());
            obj[2]=a.getBalance();
            obj[3]=(a.getTip()==1)?"Saving":"Spending";
            if (a.getTip()==1){
                SavingAccount sa=(SavingAccount)a;
                obj[4]=sa.getTotalInterests();
                obj[5]="";
            }
            else{
                SpendingAccount sa=(SpendingAccount)a;
                obj[5]=sa.getBalanceSpended();
                obj[4]="";
            }
            Person p = (Person)table.get(a);
            obj[6]=p.getId();
            obj[7]=p.getFirstName()+" "+p.getLastName();
            lista.add(obj);
        }
        return lista;
    }

    /**
     * Metoda care returneaza toate persoanele unice.
     * @return un obiect ArrayList<Object[]>
     */
    @Override
    public ArrayList<Object[]> reportPersons() {
        Object obj[];
        ArrayList<Object[]> lista = new ArrayList<Object[]>();
        Enumeration conturi; 
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Person p;
        conturi = table.elements(); 
        while(conturi.hasMoreElements()) {
            obj = new Object[7];
            p = (Person) conturi.nextElement(); 
            obj[0]=p.getId();
            obj[1]=dateFormat.format(p.getDate());
            obj[2]=p.getFirstName();
            obj[3]=p.getLastName();
            obj[4]=p.getCountry();
            obj[5]=p.getPassword();
            obj[6]=p.getNrAcc();
            boolean exista=false;
            String s;
            for (int i=0;i<lista.size();i++){
                s=(String)lista.get(i)[0];
                if (s.compareTo(p.getId()) == 0){
                    exista=true;
                    break;
                }
            }
            if (!exista) lista.add(obj);
        }
        return lista;
    }

    /**
     * metoda care sterge o persoana, impreuna cu toate conturile sale
     * @param p obiectul Person 
     */
    @Override
    public void removePerson(Person p) {
        //Avem  conturi in tabela de dispersie
        try{
            assert (isWellFormed() == true);
        }
        catch (AssertionError ex){
                        JOptionPane.showMessageDialog(null,
                    "Nu exista conturi","Error!", 
                    JOptionPane.ERROR_MESSAGE );
        }
        try{
            assert (p != null);
        }
        catch (AssertionError ex){
                        JOptionPane.showMessageDialog(null,
                    "Nu exista o persoana valida","Error!", 
                    JOptionPane.ERROR_MESSAGE );
        }
        Enumeration conturi; 
        Account a;
        Person p2;
        conturi = table.keys(); 
        while(conturi.hasMoreElements()) { 
            a = (Account) conturi.nextElement(); 
            p2 = (Person) table.get(a);
            if (p2.getId().compareTo(p.getId())==0){
                table.remove(a);
            }
        }
    }

    /**
     * metoda care sterge o tranzactie
     * @param date data cand a avut loc acea tranzactie
     */
    @Override
    public void removeTranzaction(String date) {
        for (int i=0;i<tranz.size();i++){
            String s = (String) tranz.get(i)[0];
            if (date.compareTo(s)==0)
                tranz.remove(i);
        }
    }

    /**
     * metoda care sterge toate tranzactiile
     */
    @Override
    public void removeAllTranzaction() {
        tranz.removeAll(tranz);
    }

    
}
