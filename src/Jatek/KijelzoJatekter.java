package Jatek;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static Jatek.Main.*;
import java.util.Vector;
import static Jatek.Main.*;

public class KijelzoJatekter extends Kijelzo {

    Vector<KartyaPanel> jateksoKartyak;
    Vector<KartyaLap> asztal;
    boolean rakhat;
    int tipp;
    JatekosPanel ellenfel1,ellenfel2,ellenfel3;
    
    public KijelzoJatekter(String cim, int w, int h,boolean egyjatekos) {
        super(cim, w, h, true);
        rakhat = false;
        jateksoKartyak = new Vector();
        tipp = 0;
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
    }

    private void tobbjatekos() {
        System.out.println("Még nem támogatott.");
    }
    
    
    public void addKartya(KartyaLap k){
        jateksoKartyak.add(new KartyaPanel(k, 0, 2));
    }
    
    public void kartyakFrissit(){
        for (int i = 0; i < jateksoKartyak.size(); i++) {
            p.remove(jateksoKartyak.get(i));
        }
        int kezd = Main.FOABLAK_SZEL/2-jateksoKartyak.size()*10;
        for (int i = jateksoKartyak.size()-1; i >= 0; i--) {
            if(i != jateksoKartyak.size()-1){
                jateksoKartyak.get(i).setAllapot(1);
            }else{
                jateksoKartyak.get(i).setAllapot(0);
            }
            jateksoKartyak.get(i).setLocation(kezd+i*20,Main.FOABLAK_MAG/10*7);
            jateksoKartyak.get(i).setSize(35*jateksoKartyak.get(i).szorzo, 59*jateksoKartyak.get(i).szorzo);
            p.add(jateksoKartyak.get(i));
        }
        p.repaint();
        
        ellenfel1.tipp.setText("Tipp: "+ellenfel1.tippertek);
        ellenfel2.tipp.setText("Tipp: "+ellenfel2.tippertek);
        ellenfel3.tipp.setText("Tipp: "+ellenfel3.tippertek);
                        
        ellenfel1.pont.setText("Pont: "+ellenfel1.osszPontErtek);               
        ellenfel2.pont.setText("Pont: "+ellenfel2.osszPontErtek);               
        ellenfel3.pont.setText("Pont: "+ellenfel3.osszPontErtek);               
    }
    
    public class Listener implements MouseListener{

        @Override public void mouseClicked(MouseEvent me) {}
        @Override public void mousePressed(MouseEvent me) {}
        @Override public void mouseReleased(MouseEvent me) {}
        @Override public void mouseEntered(MouseEvent me) {}
        @Override public void mouseExited(MouseEvent me) {}
        
    }


    
}
