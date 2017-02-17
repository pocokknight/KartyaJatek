package Jatek;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static Jatek.Main.*;

public class KijelzoTippBekero extends Kijelzo {

    Gomb ok;
    JTextField mezo;
    KijelzoHiba hiba;
    
    public KijelzoTippBekero(String cim, int w, int h, boolean kilepos) {
        super(cim, w, h, kilepos);
        ok = new Gomb("Rendben",getSzel()/2,getMag()/5);
        ok.setLocation(getSzel()/2-ok.getWidth()/2, getMag()/2);
        p.add(ok);
        mezo = new JTextField();
        mezo.setForeground(new Color(nr-szinkul,ng-szinkul,nb-szinkul));
        mezo.setSize(getSzel()/4*3,getMag()/5);
        mezo.setLocation(getSzel()/2-mezo.getWidth()/2, getMag()/5);
        p.add(mezo);
        ok.addMouseListener(new Listener());
    }
    
    
    public class Listener implements MouseListener{

        @Override public void mouseClicked(MouseEvent me) {
            int t;
            if(hiba != null)hiba.frame.dispose();
            if(mezo.getText().equals("")){
                hiba = new KijelzoHiba("A mezőben nem szerepel tipp!", FOABLAK_SZEL/3, FOABLAK_MAG/4);
            }else{
                try{
                    t = Integer.parseInt(mezo.getText());
                    jatekter.tipp = t;
                    frame.dispose();
                }catch(Exception e){
                    hiba = new KijelzoHiba("A mezőben nem szám szerepel!.", FOABLAK_SZEL/3, FOABLAK_MAG/4);
                }
            }
        }
        @Override public void mousePressed(MouseEvent me) {}
        @Override public void mouseReleased(MouseEvent me) {}
        @Override public void mouseEntered(MouseEvent me) {}
        @Override public void mouseExited(MouseEvent me) {}
        
    }


    
}
