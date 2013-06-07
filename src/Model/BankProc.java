/**
 * Interfata BankProc care contine toate operatiile pe care
 * trebuie sa le implementeze clasa Bank.
 */
package Model;

import java.util.ArrayList;

/**
 * @version 28 aprilie 2013
 * @author Andrei
 */
public interface BankProc {
    public void createAccount(String idPerson, String pass, int tip);
    public void removeAccount(Account a);
    public Account readAccount(int id);
    public Person readPerson(String id);
    public ArrayList<Object[]> reportTranzactions();
    public ArrayList<Object[]> reportAccounts();
    public ArrayList<Object[]> reportPersons();
    public void addInterests(SavingAccount sa);
    public void saveBank();
    public void loadBank();
    public ArrayList<Account> getAllAccounts(String idPerson);
    public void makeTranzaction(Account a1, Account a2, int bani);
    public void removePerson(Person p);
    public void removeTranzaction(String date);
    public void removeAllTranzaction();
}
