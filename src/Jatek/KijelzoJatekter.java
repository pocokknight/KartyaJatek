package Jatek;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static Jatek.Main.*;
import java.util.Vector;
import static Jatek.Main.*;

public class KijelzoJatekter extends Kijelzo {

    Vector<KartyaPanel> kartyak;
    JatekosPanel ellenfel1,ellenfel2,ellenfel3;
    
    public KijelzoJatekter(String cim, int w, int h,boolean egyjatekos) {
        super(cim, w, h, true);
        kartyak = new Vector();
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
        
        kartyakFrissit();
        
        iranyito = new JatekIranyito(true);
        iranyito.oszt();
    }

    private void tobbjatekos() {
        System.out.println("Még nem támogatott.");
    }
    
    
    public void addKartya(KartyaLap k){
        kartyak.add(new KartyaPanel(k, 0, 2));
    }
    
    public void kartyakFrissit(){
        for (int i = 0; i < kartyak.size(); i++) {
            p.remove(kartyak.get(i));
        }
        int kezd = Main.FOABLAK_SZEL/2-kartyak.size()*10;
        for (int i = kartyak.size()-1; i >= 0; i--) {
            if(i != kartyak.size()-1){
                kartyak.get(i).setAllapot(1);
            }else{
                kartyak.get(i).setAllapot(0);
            }
            kartyak.get(i).setLocation(kezd+i*20,Main.FOABLAK_MAG/10*7);
            kartyak.get(i).setSize(35*kartyak.get(i).szorzo, 59*kartyak.get(i).szorzo);
            p.add(kartyak.get(i));
        }
    }
    
    public class Listener implements MouseListener{

        @Override public void mouseClicked(MouseEvent me) {}
        @Override public void mousePressed(MouseEvent me) {}
        @Override public void mouseReleased(MouseEvent me) {}
        @Override public void mouseEntered(MouseEvent me) {}
        @Override public void mouseExited(MouseEvent me) {}
        
    }


    
}
