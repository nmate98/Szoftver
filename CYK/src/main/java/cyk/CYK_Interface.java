package cyk;

import javax.swing.*;
import java.awt.*;

public class CYK_Interface {
    static JFrame frame = new JFrame();

    public static void main(String[] args) {
        CYK_LanguageCheckerPanel lc = new CYK_LanguageCheckerPanel();
        CYK_Classes.createDirectory();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 500));
    }
}
