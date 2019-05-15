package cyk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class CYK_WordCheckerPanel {
    JPanel panel = new JPanel();
    JLabel bemenettext1 = new JLabel("Nyelv:");
    JLabel bemenettext2 = new JLabel("Szó:");
    JLabel kimenettext = new JLabel("Eredmény:");
    JTextArea nyelvbemenet = new JTextArea();
    JTextField szóbemenet = new JTextField();
    JTextArea kimenet = new JTextArea();
    JButton kezd = new JButton("Ellenőrzés");
    JButton valto = new JButton("Mód váltása");
    public CYK_WordCheckerPanel(){
        panel.setPreferredSize(new Dimension(800,800));
        panel.setBackground(new Color(135,206,235));
        panel.setVisible(true);
        bemenettext1.setSize(100,50);
        bemenettext2.setSize(100,50);
        kimenettext.setSize(100,50);
        nyelvbemenet.setPreferredSize(new Dimension(200,400));
        szóbemenet.setPreferredSize(new Dimension(100,50));
        kimenet.setPreferredSize(new Dimension(200,400));
        kimenet.setEditable(false);
        panel.add(bemenettext1);
        panel.add(nyelvbemenet);
        panel.add(bemenettext2);
        panel.add(szóbemenet);
        panel.add(kimenettext);
        panel.add(kimenet);
        panel.add(kezd);
        panel.add(valto);
        CYK_Interface.frame.add(panel);
        ActionListener al = new CYK_WCP_Controller(nyelvbemenet,szóbemenet,kimenet,panel);
        kezd.addActionListener(al);
        ActionListener vl = new CYK_WCP_Controller(nyelvbemenet,szóbemenet,kimenet,panel);
        valto.addActionListener(vl);
    }
}
