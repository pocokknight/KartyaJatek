package Jatek;

import Kepek.Kep;
import java.awt.*;
import javax.swing.*;

public class Kijelzo {

    JFrame frame;
    MyMainPanel p;

    Kijelzo(String cim, int w, int h, boolean kilepos) {
        frame = new JFrame(cim);
        frame.setSize(w, h);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(kilepos ? JFrame.EXIT_ON_CLOSE : JFrame.DISPOSE_ON_CLOSE);
        p = new MyMainPanel();
        frame.add(p);
        frame.setResizable(false);
        p.setLayout(null);
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }

    public int getSzel() {
        return frame.getWidth();
    }

    public int getMag() {
        return frame.getHeight();
    }

    public class MyMainPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(Kep.getKep(Kep.class, "paletta.png"), 0, 0, Main.FOABLAK_SZEL, Main.FOABLAK_MAG, null);
        }

    }

}
