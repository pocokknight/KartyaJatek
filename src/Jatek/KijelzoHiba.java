package Jatek;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static Jatek.Main.*;

public class KijelzoHiba extends Kijelzo {

    Gomb ok;
    JLabel mezo;
    
    public KijelzoHiba(String uzenet, int w, int h) {
        super("Hiba!", w, h, false);
        frame.setLocation(frame.getX(), frame.getY()+10+frame.getHeight());
        ok = new Gomb("Rendben",getSzel()/2,getMag()/5);
        ok.setLocation(getSzel()/2-ok.getWidth()/2, getMag()/2);
        p.add(ok);
        mezo = new JLabel(uzenet);
        mezo.setForeground(new Color(nr,ng,nb));
        mezo.setSize(getSzel()/4*3,getMag()/5);
        mezo.setLocation(getSzel()/2-mezo.getWidth()/2, getMag()/5);
        p.add(mezo);
        ok.addMouseListener(new Listener());
        frame.setVisible(true);
    }
    
    
    public class Listener implements MouseListener{

        @Override public void mouseClicked(MouseEvent me) {
            frame.dispose();
        }
        @Override public void mousePressed(MouseEvent me) {}
        @Override public void mouseReleased(MouseEvent me) {}
        @Override public void mouseEntered(MouseEvent me) {}
        @Override public void mouseExited(MouseEvent me) {}
        
    }


    
}
