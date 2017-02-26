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

        @Override
        public void mouseClicked(MouseEvent me) {
            if (jatekter.rakhat) {
                if (iranyito != null) {
                    for (int i = 0; i < jatekter.jatekosKartyak.size(); i++) {
                        if (jatekter.jatekosKartyak.get(i).equals(me.getSource())) {
                            jatekter.jatekosKartyak.get(i).lap.rakta = "jatekos";
                            jatekter.asztal.add(jatekter.jatekosKartyak.get(i).lap);
                            jatekter.jatekoskijeloltkartya = jatekter.jatekosKartyak.get(i);
                            jatekter.jatekoskijeloltkartya.setAllapot(0);
                            jatekter.jatekoskijeloltkartya.setLocation(FOABLAK_SZEL / 2 - jatekter.jatekoskijeloltkartya.getWidth() / 2, FOABLAK_MAG / 2);
                            jatekter.jatekosKartyak.remove(i);
                            jatekter.rakhat = false;
                            jatekter.kartyakFrissit();
                            iranyito.botKartyaRakas();
                        }
                    }
                } else {
                    KartyaPanel p;
                    switch (gyakorlo.szovegSzamlalo) {
                        case 2:
                            p = (KartyaPanel) me.getSource();
                            if (p.lap.ertek == 10) {
                                jatekter.jatekoskijeloltkartya = jatekter.jatekosKartyak.get(0);
                                jatekter.jatekoskijeloltkartya.setAllapot(0);
                                jatekter.jatekoskijeloltkartya.setLocation(FOABLAK_SZEL / 2 - jatekter.jatekoskijeloltkartya.getWidth() / 2, FOABLAK_MAG / 2);
                                jatekter.jatekosKartyak.remove(0);
                                jatekter.rakhat = false;
                                jatekter.ellenfel1.korPontErtek = 1;
                                jatekter.labelfrissit();
                                jatekter.kartyakFrissit();
                                gyakorlo.korEgy();
                            }
                            break;
                        case 4:
                            p = (KartyaPanel) me.getSource();
                            if (p.lap.ertek == 4) {
                                jatekter.jatekoskijeloltkartya = jatekter.jatekosKartyak.get(0);
                                jatekter.jatekoskijeloltkartya.setAllapot(0);
                                jatekter.jatekoskijeloltkartya.setLocation(FOABLAK_SZEL / 2 - jatekter.jatekoskijeloltkartya.getWidth() / 2, FOABLAK_MAG / 2);
                                jatekter.jatekosKartyak.remove(0);
                                jatekter.rakhat = false;
                                jatekter.kartyakFrissit();
                                jatekter.ellenfel2.korPontErtek = 1;
                                jatekter.labelfrissit();
                                gyakorlo.takarit();
                                gyakorlo.lepes();
                            }
                            break;
                        case 5:
                            p = (KartyaPanel) me.getSource();
                            if (p.lap.ertek == 8) {
                                jatekter.jatekoskijeloltkartya = jatekter.jatekosKartyak.get(0);
                                jatekter.jatekoskijeloltkartya.setAllapot(0);
                                jatekter.jatekoskijeloltkartya.setLocation(FOABLAK_SZEL / 2 - jatekter.jatekoskijeloltkartya.getWidth() / 2, FOABLAK_MAG / 2);
                                jatekter.jatekosKartyak.remove(0);
                                jatekter.rakhat = false;
                                jatekter.kartyakFrissit();
                                jatekter.jatekosKorPont = 1;
                                jatekter.labelfrissit();
                                gyakorlo.lepes();
                            }
                            break;
                    }
                }
            }
        }
        @Override public void mousePressed(MouseEvent me) {}
        @Override public void mouseReleased(MouseEvent me) {}
        @Override public void mouseEntered(MouseEvent me) {}
        @Override public void mouseExited(MouseEvent me) {}
        
    }

}
