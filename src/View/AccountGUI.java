/**
 * clasa de tip GUI (Graffic User Interface).
 */
package View;

import Model.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.WindowListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.*;

/**
 * @version 28 aprilie 2013
 * @author Andrei
 */
public class AccountGUI extends JFrame{
    
    private JPanel pnCentral;
    private JPanel pnStanga;
    private JPanel pnDreapta;
    private JPanel[] pnCont = new JPanel[10];
    
    private JTextField txtID = new JTextField();
    private JTextField txtDate = new JTextField();
    private JTextField txtNrAcc = new JTextField();
    private JTextField txtFName = new JTextField();
    private JTextField txtLName = new JTextField();
    private JTextField txtCountry = new JTextField();
    private JTextField txtExtrageri = new JTextField();
    private JTextField txtDepuneri = new JTextField();
    private JTextField txtPlati = new JTextField();
    private JTextField txtAdauga = new JTextField();
    private JTextField txtAltCont = new JTextField();
    private JTextField txtT1I1 = new JTextField();
    private JTextField txtT1I2 = new JTextField();
    private JTextField txtT1I3 = new JTextField();
    private JTextField txtT2I1 = new JTextField();
    private JTextField txtT2I2 = new JTextField();
    private JPasswordField pass1 = new JPasswordField();
    private JPasswordField pass2 = new JPasswordField();
    
    private JButton btBack = new JButton("Back");
    private JButton btSet = new JButton("Set");
    private JButton btNewAcc1 = new JButton("Create New Saving Account");
    private JButton btNewAcc2 = new JButton("Create New Spending Account");
    private JButton btOKExtrag = new JButton("OK");
    private JButton btOKDepune = new JButton("OK");
    private JButton btOKPlata = new JButton("OK");
    private JButton btOKAdauga = new JButton("OK");
    private JButton btGetInterest = new JButton("Get Interest");
    
    private JComboBox coBox1 = new JComboBox();
    private JComboBox coBoxExtrageri = new JComboBox();
    private JComboBox coBoxDepuneri = new JComboBox();
    
    public AccountGUI() {
        setTitle("Account GUI (Andrei Muntean)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 600));
        
        setVisible(false);
    }
    
    public void init(Bank b){
        pnCentral=new JPanel();
        pnStanga =new JPanel();
        pnDreapta=new JPanel();
        JPanel panel;
        setContentPane(pnCentral);
        pnCentral.setLayout(new GridLayout(1,2));
        pnCentral.add(pnStanga);
        pnCentral.add(pnDreapta);
        pnStanga.setLayout(new GridLayout(9,1));
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.add(new JLabel(" ID:"));
        panel.add(txtID);
        pnStanga.add(panel);
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.add(new JLabel(" Account Created Date:"));
        panel.add(txtDate);
        pnStanga.add(panel);
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.add(new JLabel(" First Name:"));
        panel.add(txtFName);
        pnStanga.add(panel);
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.add(new JLabel(" Last Name:"));
        panel.add(txtLName);
        pnStanga.add(panel);
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.add(new JLabel(" Country:"));
        panel.add(txtCountry);
        pnStanga.add(panel);
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.add(new JLabel(" Password:"));
        panel.add(pass1);
        pnStanga.add(panel);
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.add(new JLabel(" Repeat Password:"));
        panel.add(pass2);
        pnStanga.add(panel);
        
        pnStanga.add(new JSeparator());
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.add(btBack);
        panel.add(btSet);
        pnStanga.add(panel);
        
        Person p = b.readPerson(Var.idLogin);
        txtID.setText(String.valueOf(p.getId()));
        txtCountry.setText(p.getCountry());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        txtDate.setText(dateFormat.format(p.getDate()));
        txtFName.setText(p.getFirstName());
        txtLName.setText(p.getLastName());
        txtID.setEditable(false);
        txtDate.setEditable(false);
        txtNrAcc.setEditable(false);
        loadCoBox1(b);
        
        ////////////////dreapta
        
        pnDreapta.setLayout(new GridLayout(12,1));
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.add(new JLabel(" Total Bank Accounts:"));
        panel.add(txtNrAcc);
        pnDreapta.add(panel);   
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.add(btNewAcc1);
        panel.add(btNewAcc2);
        pnDreapta.add(panel);   
        
        pnDreapta.add(new JSeparator());
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.add(new JLabel(" Select an Account:"));
        panel.add(coBox1);
        pnDreapta.add(panel);   
        
        pnDreapta.add(new JSeparator());
        
        
        
        for (int i=0;i<7;i++) {
            pnCont[i] = new JPanel();
            pnDreapta.add(pnCont[i]);
        }
        

        
        pack();
        
        setLocationRelativeTo(null);  
        setVisible(true);
        
        showCont(b);
    }
    
    public void showCont(Bank b){
        
        if (coBox1.getItemCount()<2) return;
        

            for (int i=0;i<7;i++){
                pnCont[i].removeAll();
                pnDreapta.remove(pnCont[i]);
            }

        
        repaint();
        
        String s=(String)coBox1.getSelectedItem();
        while (true){
            if (s.length()<3) break;
            s = s.substring(0, 6);
            int id;
            try{
                id = Integer.parseInt(s);
            }
            catch (Exception ex){
                System.out.println("Exception parseInt:\n"+ex.toString());
                break;
            }
            Account a = b.readAccount(id);
            if (a==null) break;
            JPanel panel;
            JTextField txt;
            showBalances(a);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                panel = new JPanel();
                panel.setLayout(new GridLayout(1,2));
                panel.add(new JLabel(" Created date:"));
                txt = new JTextField(dateFormat.format(a.getDate()));
                txt.setEditable(false);
                panel.add(txt);
                pnCont[0] = panel;        
            if (a.getTip()==1){       
                SavingAccount sa = (SavingAccount) a;
                int w=0;
                
                coBoxExtrageri.removeAllItems();
                ArrayList<Account> lista = b.getAllAccounts(Var.idLogin);
                coBoxExtrageri.addItem("-");
                for (int i=0;i<lista.size();i++){
                    String z = String.valueOf(lista.get(i).getId());
                    if (lista.get(i).getTip()==1) z+=" (Saving)";
                        else z+=" (Spending)";
                    if (lista.get(i).getId()!=id)
                        if (lista.get(i).getTip()==2)
                            coBoxExtrageri.addItem((String)z);
                }
                
                panel = new JPanel();
                panel.setLayout(new GridLayout(1,2));
                panel.add(new JLabel("Bani Depozitati:"));
                txtT1I1.setEditable(false);
                panel.add(txtT1I1);
                pnCont[++w] = panel;
                
                panel = new JPanel();
                panel.setLayout(new GridLayout(1,3));
                panel.add(new JLabel("Bani Castigati:"));
                txtT1I2.setEditable(false);
                panel.add(txtT1I2);
                panel.add(btGetInterest);
                pnCont[++w] = panel;
                
                panel = new JPanel();
                panel.setLayout(new GridLayout(1,2));
                panel.add(new JLabel("Bani Actuali:"));
                txtT1I3.setEditable(false);
                panel.add(txtT1I3);
                pnCont[++w] = panel;
                
                panel = new JPanel();
                panel.setLayout(new GridLayout(1,2));
                panel.add(new JLabel("Fa o Extragere in contul:"));
                panel.add(coBoxExtrageri);
                pnCont[++w] = panel;              
                
                panel = new JPanel();
                panel.setLayout(new GridLayout(1,3));
                panel.add(new JLabel("Suma spre extragere:"));
                panel.add(txtExtrageri);
                panel.add(btOKExtrag);
                pnCont[++w] = panel;
                
                
            }      
            else{       
                SpendingAccount sa = (SpendingAccount) a;
                int w=0;
                
                coBoxDepuneri.removeAllItems();
                ArrayList<Account> lista = b.getAllAccounts(Var.idLogin);
                coBoxDepuneri.addItem("-");
                for (int i=0;i<lista.size();i++){
                    String z = String.valueOf(lista.get(i).getId());
                    if (lista.get(i).getTip()==1) z+=" (Saving)";
                        else z+=" (Spending)";
                    if (lista.get(i).getId()!=id)
                            coBoxDepuneri.addItem((String)z);
                }
                
                panel = new JPanel();
                panel.setLayout(new GridLayout(1,2));
                panel.add(new JLabel("Bani de cheltuiala:"));
                txtT2I1.setEditable(false);
                panel.add(txtT2I1);
                pnCont[++w] = panel;
                
                panel = new JPanel();
                panel.setLayout(new GridLayout(1,2));
                panel.add(new JLabel("Total cheltuit:"));
                txtT2I2.setEditable(false);
                panel.add(txtT2I2);
                pnCont[++w] = panel;
                
                panel = new JPanel();
                panel.setLayout(new GridLayout(1,3));
                panel.add(new JLabel("Adauga in cont:"));
                panel.add(txtAdauga);
                panel.add(btOKAdauga);
                pnCont[++w] = panel;
                
                panel = new JPanel();
                panel.setLayout(new GridLayout(1,3));
                panel.add(new JLabel("Fa o plata:"));
                panel.add(txtPlati);
                panel.add(btOKPlata);
                pnCont[++w] = panel;
                
                panel = new JPanel();
                panel.setLayout(new GridLayout(1,3));
                panel.add(new JLabel("Depune in cont:"));
                panel.add(coBoxDepuneri);
                panel.add(txtAltCont);
                pnCont[++w] = panel;               
                
                panel = new JPanel();
                panel.setLayout(new GridLayout(1,3));
                panel.add(new JLabel("Suma spre depunere:"));
                panel.add(txtDepuneri);
                panel.add(btOKDepune);
                pnCont[++w] = panel;

            }
            
            break;
        }
        
        for (int i=0;i<7;i++) {
            pnDreapta.add(pnCont[i]);
        }    
        
        setPreferredSize(getSize()); 
        pack();
    }
    
    public String[] getPersonData(){
        String s[] = new String[5];
        s[0] = txtFName.getText();
        s[1] = txtLName.getText();
        s[2] = txtCountry.getText();
        s[3] = String.valueOf(pass1.getPassword());
        s[4] = String.valueOf(pass2.getPassword());
        return s;
    }
    
    public void showError(int id, String s){
        if (id==0)
            JOptionPane.showMessageDialog(this, 
                    s, "Succes!",
                    JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(this,
                    s,"Error!", 
                    JOptionPane.ERROR_MESSAGE );
    }
    
    
    public void loadCoBox1(Bank b){

        coBox1.removeAllItems();
        ArrayList<Account> lista = b.getAllAccounts(Var.idLogin);
        coBox1.addItem("-");
        for (int i=0;i<lista.size();i++){
            String s = String.valueOf(lista.get(i).getId());
            if (lista.get(i).getTip()==1) s+=" (Saving)";
            else s+=" (Spending)";
            coBox1.addItem((String)s);
        }
        Person p = b.readPerson(Var.idLogin);
        txtNrAcc.setText(String.valueOf(p.getNrAcc()));
    }
    
    public void setPersonData(String s[]){
        if (s[0].compareTo("")!=0) txtFName.setText(s[0]);
        if (s[1].compareTo("")!=0) txtLName.setText(s[1]);
        if (s[2].compareTo("")!=0) txtCountry.setText(s[2]);
        pass1.setText("");
        pass2.setText("");
    }
    
    public void adaugaListenerBtnBack(ActionListener a){
        btBack.addActionListener(a);
    }
    public void adaugaListenerBtnSet(ActionListener a){
        btSet.addActionListener(a);
    }
    public void adaugaListenerBtnNewAcc1(ActionListener a){
        btNewAcc1.addActionListener(a);
    }
    public void adaugaListenerBtnNewAcc2(ActionListener a){
        btNewAcc2.addActionListener(a);
    }
    public void adaugaListenerBtnOKAdauga(ActionListener a){
        btOKAdauga.addActionListener(a);
    }
    public void adaugaListenerBtnOKDepune(ActionListener a){
        btOKDepune.addActionListener(a);
    }
    public void adaugaListenerBtnOKExtrag(ActionListener a){
        btOKExtrag.addActionListener(a);
    }
    public void adaugaListenerBtnOKPlata(ActionListener a){
        btOKPlata.addActionListener(a);
    }
    public void adaugaListenerBtnGetInterest(ActionListener a){
        btGetInterest.addActionListener(a);
    }
    public void addCoBox1ItLis(ItemListener it){
        coBox1.addItemListener(it);
    }
    public void addCoBoxDepune(ItemListener it){
        coBoxDepuneri.addItemListener(it);
    }
 
    
    public void adaugaWindowListener(WindowListener w){
        this.addWindowListener(w);
    }
    
    public void scrieContDepuneri(){
        String z="";
        String s = (String)coBoxDepuneri.getSelectedItem();
        if (s.length()>5) z=s.substring(0, 6);
        txtAltCont.setText(z);
    }
    
    public int getAccount(Bank b){
        try{
            String s=(String)coBox1.getSelectedItem();
            s = s.substring(0, 6);
            int nr = Integer.parseInt(s);
            Account a = b.readAccount(nr);
            return a.getId();
        }
        catch(Exception ex){
            return -1;
        }
    }
    
    public String[] getBaniAdauga(){
        String[] s = new String[3];
        s[0] = (String)coBox1.getSelectedItem();
        if (s[0].length()>6) s[0]=s[0].substring(0, 6);
        s[1] = txtAdauga.getText();
        s[2] = s[0];
        return s;
    }
    public String[] getBaniPlata(){
        String[] s = new String[3];
        s[0] = (String)coBox1.getSelectedItem();
        if (s[0].length()>6) s[0]=s[0].substring(0, 6);
        s[1] = txtPlati.getText();
        s[2] = s[0];
        return s;
    }
    public String[] getBaniExtrageri(){
        String[] s = new String[3];
        s[0] = (String)coBox1.getSelectedItem();
        if (s[0].length()>6) s[0]=s[0].substring(0, 6);
        s[1] = txtExtrageri.getText();
        s[2] = (String)coBoxExtrageri.getSelectedItem();
        if (s[2].length()>6) s[2]=s[2].substring(0, 6);
        return s;
    }
    public String[] getBaniDepuneri(){
        String[] s = new String[3];
        s[0] = (String)coBox1.getSelectedItem();
        if (s[0].length()>6) s[0]=s[0].substring(0, 6);
        s[1] = txtDepuneri.getText();
        s[2] = txtAltCont.getText();
        return s;
    }
    
    public void showBalances(Account a){
        if (a.getTip()==1){
            SavingAccount sa = (SavingAccount)a;
            txtT1I1.setText(String.valueOf(a.getBalance()-sa.getTotalInterests()));
            txtT1I2.setText(String.valueOf(sa.getTotalInterests()));
            txtT1I3.setText(String.valueOf(a.getBalance()));
            txtExtrageri.setText("");
        }else{
            SpendingAccount sa = (SpendingAccount)a;
            txtT2I1.setText(String.valueOf(a.getBalance()));
            txtT2I2.setText(String.valueOf(sa.getBalanceSpended()));
            txtDepuneri.setText("");
            txtPlati.setText("");
            txtAdauga.setText("");
        }
    }
    
}
