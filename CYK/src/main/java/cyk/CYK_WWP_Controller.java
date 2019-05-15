package cyk;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class CYK_WWP_Controller implements ActionListener {

    JTextField kimenet;
    JTextField bemenet;
    JPanel panel;
    public CYK_WWP_Controller(JTextField bemenet, JTextField kimenet, JPanel panel){
        this.kimenet= kimenet;
        this.bemenet= bemenet;
        this.panel=panel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.toString().contains("Írás")) {
            try {
                kimenet.setText(CYK_Classes.WriteToJson(bemenet.getText()));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        else{
            panel.setVisible(false);
            CYK_LanguageCheckerPanel lcp = new CYK_LanguageCheckerPanel();
            lcp.panel.setVisible(true);
        }
    }
}