package Jatek;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static Jatek.Main.*;

public class KijelzoNevBekero extends Kijelzo {

    Gomb ok;
    JTextField mezo;
    KijelzoHiba hiba;
    
    public KijelzoNevBekero(String cim, int w, int h, boolean kilepos) {
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
            if(mezo.getText().equals("")){
                if(hiba != null) hiba.frame.dispose();;
                hiba = new KijelzoHiba("A mezőben nem szerepel megadott név.", FOABLAK_SZEL/3, FOABLAK_MAG/4);
            }
            else{
                Main.nev = mezo.getText();
                Main.nbk.frame.dispose();
                Main.valaszto = new KijelzoValaszto("Kártyajáték - készítette: Horváth Patrik", FOABLAK_SZEL, FOABLAK_MAG, true);
                Main.valaszto.setVisible(true);
            }
        }
        @Override public void mousePressed(MouseEvent me) {}
        @Override public void mouseReleased(MouseEvent me) {}
        @Override public void mouseEntered(MouseEvent me) {}
        @Override public void mouseExited(MouseEvent me) {}
        
    }


    
}
