package cyk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CYK_Word_Writer {
    JPanel panel = new JPanel();

    JLabel bemenettext = new JLabel("Szó:");
    JLabel kimenettext = new JLabel("Eredmény:");
    JTextField szóbemenet = new JTextField();
    JTextField kimenet = new JTextField();
    JButton kezd = new JButton("Írás");
    JButton valto = new JButton("Mód váltása");
    public CYK_Word_Writer(){
        panel.setPreferredSize(new Dimension(800,800));
        panel.setBackground(new Color(135,206,235));
        panel.setVisible(true);
        bemenettext.setSize(100,50);
        kimenettext.setSize(100,50);
        szóbemenet.setPreferredSize(new Dimension(100,50));
        kimenet.setPreferredSize(new Dimension(100,50));
        kimenet.setEditable(false);
        panel.add(bemenettext);
        panel.add(szóbemenet);
        panel.add(kimenettext);
        panel.add(kimenet);
        panel.add(kezd);
        panel.add(valto);
        CYK_Interface.frame.add(panel);
        ActionListener al = new CYK_WWP_Controller(szóbemenet,kimenet,panel);
        kezd.addActionListener(al);
        ActionListener vl = new CYK_WWP_Controller(szóbemenet,kimenet,panel);
        valto.addActionListener(vl);

    }
}
