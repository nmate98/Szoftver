package cyk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CYK_LanguageCheckerPanel {
    JPanel panel = new JPanel();
    JLabel bemenettext = new JLabel("Ellenőrzendő nyelv:");
    JLabel kimenettext = new JLabel("Eredmény:");
    JTextArea bemenet = new JTextArea();
    JTextArea kimenet = new JTextArea();
    JButton kezd = new JButton("Ellenőrzés");
    JButton valto = new JButton("Mód váltása");
    public CYK_LanguageCheckerPanel(){
        panel.setPreferredSize(new Dimension(800,800));
        panel.setBackground(new Color(135,206,235));
        panel.setVisible(true);
        CYK_Interface.frame.add(panel);
        panel.add(bemenettext);
        bemenettext.setSize(100,50);
        kimenettext.setSize(100,50);
        bemenet.setPreferredSize(new Dimension(200,400));
        panel.add(bemenet);
        panel.add(kimenettext);
        kimenet.setPreferredSize(new Dimension(200,400));
        panel.add(kimenet);
        kimenet.setEditable(false);
        panel.add(kezd);
        panel.add(valto);
        ActionListener al = new CYK_LCP_Controller(bemenet,kimenet,panel);
        kezd.addActionListener(al);
        ActionListener vl = new CYK_LCP_Controller(bemenet,kimenet,panel);
        valto.addActionListener(vl);
    }
}
