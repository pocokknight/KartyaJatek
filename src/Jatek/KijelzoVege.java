package Jatek;

import java.awt.event.*;
import static Jatek.Main.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class KijelzoVege extends Kijelzo {

    Gomb kilepes;
    JLabel elso,masodik,harmadik,negyedik;
    
    public KijelzoVege(String cim, int w, int h, boolean kilepos, boolean egy) {
        super(cim, w, h, kilepos);
        
        if(egy){
            Vector<VegeSzoveg> pontok = new Vector();
            pontok.add(new VegeSzoveg("J",jatekter.jatekosOsszPont));
            pontok.add(new VegeSzoveg("Bot 1",jatekter.ellenfel1.osszPontErtek));
            pontok.add(new VegeSzoveg("Bot 2",jatekter.ellenfel2.osszPontErtek));
            pontok.add(new VegeSzoveg("Bot 3",jatekter.ellenfel3.osszPontErtek));
            
            Collections.sort(pontok);
            
            elso = new JLabel("1. hely : "+(pontok.get(0).nev.equals("J") ? Main.nev : pontok.get(0).nev)+"  ..........  "+pontok.get(0).pont+" pont",JLabel.CENTER);
            masodik = new JLabel("2. hely : "+(pontok.get(1).nev.equals("J") ? Main.nev : pontok.get(1).nev)+"  ..........  "+pontok.get(1).pont+" pont",JLabel.CENTER);
            harmadik = new JLabel("3. hely : "+(pontok.get(2).nev.equals("J") ? Main.nev : pontok.get(2).nev)+"  ..........  "+pontok.get(2).pont+" pont",JLabel.CENTER);
            negyedik = new JLabel("4. hely : "+(pontok.get(3).nev.equals("J") ? Main.nev : pontok.get(3).nev)+"  ..........  "+pontok.get(3).pont+" pont",JLabel.CENTER);
            
            elso.setFont(new Font(elso.getFont().getName(),elso.getFont().getStyle(),30));
            masodik.setFont(new Font(masodik.getFont().getName(),masodik.getFont().getStyle(),25));
            harmadik.setFont(new Font(harmadik.getFont().getName(),harmadik.getFont().getStyle(),20));
            negyedik.setFont(new Font(negyedik.getFont().getName(),negyedik.getFont().getStyle(),20));
            
            elso.setForeground(new Color(225,175,50));
            masodik.setForeground(new Color(200,200,200));
            harmadik.setForeground(new Color(200,100,20));
            negyedik.setForeground(new Color(nr,ng,nb));
            
            p.add(elso);
            p.add(masodik);
            p.add(harmadik);
            p.add(negyedik);
            
            elso.setSize(FOABLAK_SZEL/2, FOABLAK_MAG/9);
            masodik.setSize(FOABLAK_SZEL/2, FOABLAK_MAG/10);
            harmadik.setSize(FOABLAK_SZEL/2, FOABLAK_MAG/11);
            negyedik.setSize(FOABLAK_SZEL/2, FOABLAK_MAG/12);
            
            elso.setLocation(FOABLAK_SZEL/4, FOABLAK_MAG/10*1);
            masodik.setLocation(FOABLAK_SZEL/4, FOABLAK_MAG/10*2);
            harmadik.setLocation(FOABLAK_SZEL/4, FOABLAK_MAG/10*3);
            negyedik.setLocation(FOABLAK_SZEL/4, FOABLAK_MAG/10*4);
            
            kilepes = new Gomb("Vissza",getSzel()/2,getMag()/10);
            kilepes.setLocation(getSzel()/2-kilepes.getWidth()/2, getMag()/10*8);
            p.add(kilepes);
            kilepes.addMouseListener(new Listener());
        }else{
            
        }
        
    }
    
    
    public class Listener implements MouseListener{

        @Override public void mouseClicked(MouseEvent me) {
            if(me.getSource() == kilepes){
                valaszto = new KijelzoValaszto("Kártyajáték - készítette: Horváth Patrik", FOABLAK_SZEL, FOABLAK_MAG, true);
                valaszto.setVisible(true);
                vege.frame.dispose();
            }
        }
        @Override public void mousePressed(MouseEvent me) {}
        @Override public void mouseReleased(MouseEvent me) {}
        @Override public void mouseEntered(MouseEvent me) {}
        @Override public void mouseExited(MouseEvent me) {}
        
    }


    
}
