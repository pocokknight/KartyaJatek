package Jatek;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KijelzoTeszt extends Kijelzo {
    
    MyPanel mp = new MyPanel();
    
    public KijelzoTeszt(int w, int h) {
        super("Teszt", w, h, false);
        frame.add(mp);
        frame.setVisible(true);
    }

    class MyPanel extends JPanel{

        @Override
        public void paintComponent(Graphics g) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 13; j++) {
                    new KartyaLap(j,i).grafikaTeljes(g, j*64, i*128, 2);
                }
            }
        }
        
    }
    
}
