/**
 * clasa de tip GUI (Graffic User Interface).
 */
package View;

import Model.Var;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import javax.swing.*;

/**
 * @version 28 aprilie 2013
 * @author Andrei
 */
public class LogGUI extends JFrame {
    
    private JButton btAccountL = new JButton("Account Login");
    private JButton btAccountC = new JButton("Account Create");
    private JButton btBank = new JButton("Bank Administrator");
    private JButton btExit = new JButton("Exit");
    private JPanel pnCentral = new JPanel();
    
    /** Creates new form LogGUI */
    public LogGUI() {
        setTitle("Log GUI (Muntean Andrei)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        
        setContentPane(pnCentral);
        pnCentral.setLayout(new GridLayout(7,1));
        pnCentral.add(new JLabel("Alegeti modul de folosire:",JLabel.CENTER));
        JPanel panel;
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,3));
        panel.add(new JLabel());
        panel.add(btAccountC);
        panel.add(new JLabel());
        pnCentral.add(panel);
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,3));
        panel.add(new JLabel());
        panel.add(btAccountL);
        panel.add(new JLabel());
        pnCentral.add(panel);
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,3));
        panel.add(new JLabel());
        panel.add(btBank);
        panel.add(new JLabel());
        pnCentral.add(panel);

        pnCentral.add(new JPanel());

        panel = new JPanel();
        panel.setLayout(new GridLayout(1,3));
        panel.add(new JLabel());
        panel.add(btExit);
        panel.add(new JLabel());
        pnCentral.add(panel);
        
 
        pnCentral.add(new JLabel());
        
        pack();
        setLocationRelativeTo(null);  
        setVisible(true);
    }
    
    public void addBtBankAcList(ActionListener a){
        btBank.addActionListener(a);
    }
    
    public void addBtAccountLoginAcList(ActionListener a){
        btAccountL.addActionListener(a);
    }
    
    public void addBtAccountCreateAcList(ActionListener a){
        btAccountC.addActionListener(a);
    }

    public void addBtExitAcList(ActionListener a){
        btExit.addActionListener(a);
    }
    
    public String[] getAccountLog(int id){
        String[] log = new String[3];
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JTextField jtf1 = new JTextField("");
        JPasswordField jpf1 = new JPasswordField("");
        JPasswordField jpf2 = new JPasswordField("");
        jtf1.setForeground(Color.blue);
        jpf1.setForeground(Color.red);
        jpf2.setForeground(Color.red);
        String sAct;
        Object[] obj;
        if (id==1){
            label1.setText("Choose an unique account name:");
            label2.setText("Choose a password:");
            label3.setText("Repeat your password:");
            sAct="Register a new Account";
            obj=new Object[]{label1,jtf1,label2,jpf1,label3,jpf2};
        }
        else if (id==2){
            label1.setText("Your unique account name:");
            label2.setText("Your password:");
            sAct="Account Login";
            obj=new Object[]{label1,jtf1,label2,jpf1};
        }
        else {
            label1.setText("Administrator Login:");
            label2.setText("Administrator Password:");
            sAct="Administrator Login";
            obj=new Object[]{label1,jtf1,label2,jpf1};
            if (Var.autoAdminLogin){
                jtf1.setText(Var.adminLogin);
                jpf1.setText(Var.adminPass);
            }
        }
        int status; 
        status = JOptionPane.showConfirmDialog(this, obj, 
                sAct, JOptionPane.OK_CANCEL_OPTION);
        log[1]=String.valueOf(jtf1.getText());
        log[2]=String.valueOf(jpf1.getPassword());
        log[0]="0";
        if (id==1)
            if (String.valueOf(jpf1.getPassword()).compareTo(String.valueOf(jpf2.getPassword()))!=0)
                log[0]="2";
        if (jpf1.getPassword().length<4) log[0]="4";
        if (jtf1.getText().length()<4) log[0]="3";
        if (status!=0) log[0]="-1";
        return log;
    }
    
    public void setLoginMessage(int id){
        if (id==0)
            JOptionPane.showMessageDialog(this, 
                    "Logare cu succes!", "Succes",
                    JOptionPane.INFORMATION_MESSAGE);
        else if (id==2)
            JOptionPane.showMessageDialog(this,
                    "Parolele nu coincid!","Eroare", 
                    JOptionPane.ERROR_MESSAGE );
        else if (id==3)
            JOptionPane.showMessageDialog(this,
                    "Login prea scurt!","Eroare", 
                    JOptionPane.ERROR_MESSAGE );
        else if (id==4)
            JOptionPane.showMessageDialog(this,
                    "Parola prea scurta!","Eroare", 
                    JOptionPane.ERROR_MESSAGE );
        else if (id==10)
            JOptionPane.showMessageDialog(this,
                    "Autentificare invalida!","Eroare", 
                    JOptionPane.ERROR_MESSAGE );
        else if (id==11)
            JOptionPane.showMessageDialog(this,
                    "Account Inexistent!","Eroare", 
                    JOptionPane.ERROR_MESSAGE );
        else if (id==12)
            JOptionPane.showMessageDialog(this,
                    "Parola Invalida!","Eroare", 
                    JOptionPane.ERROR_MESSAGE );
        else if (id==15)
            JOptionPane.showMessageDialog(this,
                    "Account ocupat!","Eroare", 
                    JOptionPane.ERROR_MESSAGE );
    }

    public void adaugaWindowListener(WindowListener w){
        this.addWindowListener(w);
    }
    
}
