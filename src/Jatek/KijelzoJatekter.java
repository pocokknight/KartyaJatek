package Jatek;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static Jatek.Main.*;

public class KijelzoJatekter extends Kijelzo {

    JatekosPanel ellenfel1,ellenfel2,ellenfel3;
    
    public KijelzoJatekter(String cim, int w, int h,boolean egyjatekos) {
        super(cim, w, h, true);
        if(egyjatekos) egyjatekos();
        else tobbjatekos();
    }

    private void egyjatekos() {
        ellenfel1 = new JatekosPanel("Bot 1",FOABLAK_SZEL/10*2,FOABLAK_MAG/5);
        ellenfel2 = new JatekosPanel("Bot 2",FOABLAK_SZEL/10*2,FOABLAK_MAG/5);
        ellenfel3 = new JatekosPanel("Bot 3",FOABLAK_SZEL/10*2,FOABLAK_MAG/5);
        
        ellenfel1.setSize(FOABLAK_SZEL/10*2,FOABLAK_MAG/5);
        ellenfel2.setSize(FOABLAK_SZEL/10*2,FOABLAK_MAG/5);
        ellenfel3.setSize(FOABLAK_SZEL/10*2,FOABLAK_MAG/5);
        
        ellenfel1.setLocation(FOABLAK_SZEL/10*1,FOABLAK_MAG/30);
        ellenfel2.setLocation(FOABLAK_SZEL/10*4,FOABLAK_MAG/30);
        ellenfel3.setLocation(FOABLAK_SZEL/10*7,FOABLAK_MAG/30);
        
        p.add(ellenfel1);
        p.add(ellenfel2);
        p.add(ellenfel3);
    }

    private void tobbjatekos() {}
    
    public class Listener implements MouseListener{

        @Override public void mouseClicked(MouseEvent me) {}
        @Override public void mousePressed(MouseEvent me) {}
        @Override public void mouseReleased(MouseEvent me) {}
        @Override public void mouseEntered(MouseEvent me) {}
        @Override public void mouseExited(MouseEvent me) {}
        
    }


    
}
