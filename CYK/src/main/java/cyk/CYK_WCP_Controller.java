package cyk;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class CYK_WCP_Controller implements ActionListener {

    JTextArea nyelv;
    JTextField szo;
    JTextArea kimenet;
    JPanel panel;
    public CYK_WCP_Controller(JTextArea nyelv,JTextField szo, JTextArea kimenet, JPanel panel){
        this.kimenet= kimenet;
        this.nyelv= nyelv;
        this.panel=panel;
        this.szo=szo;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.toString().contains("Ellenőrzés")){
            kimenet.setText("");
            String[][] x = CYK_Classes.CheckWord(nyelv.getText(),szo.getText());
            for(int i = 0 ;i<x.length; i++){
                kimenet.append(Arrays.toString(x[x.length-i-1]).replace("[[","[")
                        .replace(", ","")
                        .replace("][","] [")
                        .replace("]]","]")+"\n");

            }
        }
        else{
            panel.setVisible(false);
            CYK_Word_Writer wwp = new CYK_Word_Writer();
            wwp.panel.setVisible(true);
        }
    }
}