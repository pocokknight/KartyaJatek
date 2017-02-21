package Jatek;

import static Jatek.Main.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KartyaPanel extends JPanel {

    KartyaLap lap;
    int allapot = 0;
    int szorzo = 1;
    
    KartyaPanel(KartyaLap l,int all,int sz){
        addMouseListener(new Listener());
        lap = l;
        allapot = all;
        szorzo = sz;
        repaint();
    }
    
    public void setLap(KartyaLap l,int all){
        lap = l;
        allapot = all;
        repaint();
    }
    
    public void setAllapot(int all){
        allapot = all;
        repaint();
    }
    
    public void setSzorzo(int sz){
        szorzo = sz;
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        switch(allapot){
            case 0:
                lap.grafikaTeljes(g, 0, 0, szorzo);
                break;
            case 1:
                lap.grafikaMinimal(g, 0, 0, szorzo);
                break;
            case 2:
                lap.grafikaHatlap(g, 0, 0, szorzo);
                break;
            default:
                break;
        }
    }
    
    public class Listener implements MouseListener{

        @Override public void mouseClicked(MouseEvent me) {
            if(jatekter.rakhat)
            for (int i = 0; i < jatekter.jatekosKartyak.size(); i++) {
                if(jatekter.jatekosKartyak.get(i).equals(me.getSource())){
                    jatekter.jatekosKartyak.get(i).lap.kijelolt = true;
                    jatekter.rakhat = false;
                    iranyito.botKartyaRakas();
                }
            }
        }
        @Override public void mousePressed(MouseEvent me) {}
        @Override public void mouseReleased(MouseEvent me) {}
        @Override public void mouseEntered(MouseEvent me) {}
        @Override public void mouseExited(MouseEvent me) {}
        
    }

}
