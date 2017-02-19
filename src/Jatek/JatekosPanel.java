package Jatek;

import static Jatek.Main.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class JatekosPanel extends JPanel {

    JLabel nev,pont,tipp;
    int osszPontErtek = 0;
    int korPontErtek = 0;
    int tippertek = 0;
    Vector<KartyaLap> kartyak;
    KartyaLap kivalasztottKartya;
    int szel,mag;
    
    JatekosPanel(String n,int w,int h){
        kartyak = new Vector<KartyaLap>();
        nev = new JLabel(n);
        pont = new JLabel("Pont: "+osszPontErtek);
        tipp = new JLabel("Tipp: "+tippertek);
        setLayout(null);
        szel = w;
        mag = h;
        nev.setForeground(new Color(nr,ng,nb));
        pont.setForeground(new Color(nr,ng,nb));
        tipp.setForeground(new Color(nr,ng,nb));
        nev.setSize(w, h/9);
        pont.setSize(w, h/9);
        tipp.setSize(w, h/9);
        nev.setLocation(w/10*1, h/9*1);
        pont.setLocation(w/10*1, h/9*2);
        tipp.setLocation(w/10*1, h/9*3);
        add(nev);
        add(pont);
        add(tipp);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(new Color(nr,ng,nb));
        g.drawRoundRect(0, 0, szel-1, mag-1,10,10);
        KartyaLap lap = new KartyaLap(0, 0);
        int tav = (int)((szel-35)/(kartyak.size()+1));
        if(tav > 20) tav = 20;
        for (int i = 0; i < kartyak.size(); i++) {
            lap.grafikaHatlap(g, tav+i*tav, (int)(mag/10*4.5f), 1);
        }
        if(kivalasztottKartya != null){
            kivalasztottKartya.grafikaTeljes(g, szel/2-35, 125, 2);
        }else{
            g.drawRoundRect(szel/2-35, 125, 70, 118, 10, 10);
        }
    }

}
