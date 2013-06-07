/**
 * clasa de tip GUI (Graffic User Interface).
 */
package View;

import Model.Bank;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

/**
 * @version 28 aprilie 2013
 * @author Andrei
 */
public class BankGUI extends JFrame{
    
    
    private JPanel pnCentral;
    private JPanel pnSus;
    private JPanel pnJos;
    private JScrollPane scPnJos;
    
    private int nrAles;
    
    private ButtonGroup bgroup = new ButtonGroup();
    
    private JRadioButton radio1 = new JRadioButton("Show all Persons");
    private JRadioButton radio2 = new JRadioButton("Show all Accounts");
    private JRadioButton radio3 = new JRadioButton("Show all Tranzactions");
    
    private JButton btBack = new JButton("Back");
    private JButton btRemove1 = new JButton("Remove");
    private JButton btRemove2 = new JButton("Remove");
    private JButton btRemove3 = new JButton("Remove");
    private JButton btRemove3All = new JButton("Remove All");
    private JTable tablou;
    
    private DefaultTableModel x;
    
    public BankGUI() {
        setTitle("Bank GUI (Muntean Andrei)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 600));
        
        setVisible(false);
    }
    
    public void init(Bank b){
        pnCentral=new JPanel();
        pnSus = new JPanel();
        //pnJos = new JPanel();
        scPnJos = new JScrollPane();
        JPanel panel;
        setContentPane(pnCentral);
        pnCentral.setLayout(new GridLayout(2,1));
        pnCentral.add(pnSus);
        pnCentral.add(scPnJos);
        bgroup.add(radio1);
        bgroup.add(radio2);
        bgroup.add(radio3);
                
        pnSus.setLayout(new GridLayout(4,1));
        //pnJos.setLayout(new GridLayout(1,1));
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,3));
        panel.add(radio1);
        panel.add(btRemove1);
        panel.add(new JLabel());
        pnSus.add(panel);   
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,3));
        panel.add(radio2);
        panel.add(btRemove2);
        panel.add(new JLabel());
        pnSus.add(panel);   
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,3));
        panel.add(radio3);
        panel.add(btRemove3);
        panel.add(btRemove3All);
        pnSus.add(panel); 
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,3));
        panel.add(new JLabel());
        panel.add(btBack);
        panel.add(new JLabel());
        pnSus.add(panel);   
        
        
        btRemove1.setEnabled(false);
        btRemove2.setEnabled(false);
        btRemove3.setEnabled(false);
        btRemove3All.setEnabled(false);
        
        nrAles =0;
        
        
        pack();
        setLocationRelativeTo(null);  
        setVisible(true);
        showJos(b,false);
    }
    
    public void showJos(Bank b, boolean fortat){
        int nrVechiAles=nrAles;
        nrAles=0;
        if (radio1.isSelected()) nrAles=1;
        else if (radio2.isSelected()) nrAles=2;
        else if (radio3.isSelected()) nrAles=3;
        if (nrAles==nrVechiAles) 
            if (!fortat) return;
        btRemove1.setEnabled(false);
        btRemove2.setEnabled(false);
        btRemove3.setEnabled(false);
        btRemove3All.setEnabled(false);
        tablou = new JTable();
        if (nrAles==0) return;
        
        repaint();
        
        if (nrAles==1){
            btRemove1.setEnabled(true);
            
            ArrayList<Object[]> lista = b.reportPersons();
            x=new DefaultTableModel(
            new Object [][] {},    
                new String [] {"Person ID", "Date Created", "First Name", 
                    "Last Name", "Country", "Password", "Accounts"} ); 
            tablou.setModel(x);
            for (int i=0;i<lista.size();i++){
                x.addRow(lista.get(i));
            }
            
        } else if (nrAles==2){
            btRemove2.setEnabled(true);
            
            ArrayList<Object[]> lista = b.reportAccounts();
            x=new DefaultTableModel(
            new Object [][] {},    
                new String [] { "Account ID", "Date Created", "Balance", 
                    "Account Type", "Interests Gained", "Spended Money",
                    "Person ID", "Person Name"} ); 
            tablou.setModel(x);
            for (int i=0;i<lista.size();i++){
                x.addRow(lista.get(i));
            }
            
            
        }
        else{
            btRemove3.setEnabled(true);
            btRemove3All.setEnabled(true);
            
            
            ArrayList<Object[]> tranz = b.reportTranzactions();
            x=new DefaultTableModel(
            new Object [][] {},    
                new String [] {"Date", "Account 1", "Person 1", 
                    "Account 2", "Person 2", "Money"} ); 
            tablou.setModel(x);
            for (int i=0;i<tranz.size();i++){
                x.addRow(tranz.get(i));
            }
            
        }

            
               
        
        scPnJos.setViewportView(tablou);
        setPreferredSize(getSize()); 
        pack();
    }
    
    public void adaugaListenerBtnBack(ActionListener a){
        btBack.addActionListener(a);
    }
    public void adaugaListenerBtnRemove1(ActionListener a){
        btRemove1.addActionListener(a);
    }
    public void adaugaListenerBtnRemove2(ActionListener a){
        btRemove2.addActionListener(a);
    }
    public void adaugaListenerBtnRemove3(ActionListener a){
        btRemove3.addActionListener(a);
    }
    public void adaugaListenerBtnRemove3All(ActionListener a){
        btRemove3All.addActionListener(a);
    }
    
    public void adaugaWindowListener(WindowListener w){
        this.addWindowListener(w);
    }
    
    public void adaugaRadioListener(ChangeListener c){
        radio1.addChangeListener(c);
        radio2.addChangeListener(c);
        radio3.addChangeListener(c);
    }
    
    public void showError(int id){
        if (id==1){
            JOptionPane.showMessageDialog(this, 
                    "Invalid Row!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (id==0){
            JOptionPane.showMessageDialog(this, 
                    "Successfully deleted!",
                    "Succes", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public String getStringId(){
        return (String)x.getValueAt(tablou.getSelectedRow(), 0);
    }
    public int getIntId(){
        return (int)x.getValueAt(tablou.getSelectedRow(), 0);
    }
    
}
