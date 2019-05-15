package cyk;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class CYK_LCP_Controller implements ActionListener {

    JTextArea kimenet;
    JTextArea bemenet;
    JPanel panel;
    public CYK_LCP_Controller(JTextArea bemenet, JTextArea kimenet, JPanel panel){
        this.kimenet= kimenet;
        this.bemenet= bemenet;
        this.panel=panel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.toString().contains("Ellenőrzés")){
            try {
                kimenet.setText(Arrays.toString(CYK_Classes.CheckGrammar(bemenet.getText())).replace(", ","").replace("[","").replace("]",""));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        else{
            panel.setVisible(false);
            CYK_WordCheckerPanel wcp = new CYK_WordCheckerPanel();
            wcp.panel.setVisible(true);
        }
    }
}