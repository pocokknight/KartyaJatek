package Jatek;

import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static Jatek.Main.*;

public class KijelzoGyakorlo extends Kijelzo {
    
    Timer t;
    boolean leallit;
    Gomb kovetkezo;
    JTextArea szoveg;
    int szovegSzamlalo = 0;
    KijelzoTippBekero tippelo;
    
    String[] szovegek = {
        "Minden kör kezdetén a játékos folyamatosan növekvő számú kártyát kap első körben egyet, majd innen növekedik addig, amíg a játékosoknak az 52 kártya egyenlően elosztható (4 játékos esetén ez 52/4 = 12 darab kártya) majd visszafelé amíg a kártyák szám 0 -ra nem csökken. Az eredeti szabályoktól eltérően az első kört a játék túlbonyolításának elkerülése végett NEM fordított kártyákkal játszuk.",
        "Ebben a körbe 3 kártyát kaptál. Mint látod a kör elején megjelent egy ablak amelyben meg kell tippelned hány környi kártyát fogsz elütni (egy ilyen kör ha minden játékos tett egy lapot most például ezt 0 és 3 közé kellene állítanod) jelen esetben rendelkezel egy 6 -ossal,10 -essel és egy dámával. Minnél nagyobb a kártyád annál nagyobb az eséllyed az ütésre. Akkor ütsz eggyet ha te raktad a legnagyobb kártyát utoljára vagyis ha van az asztalon egy király azt te ütni tudod ha királyt teszel le, ugyan így más is ütheti királlyal a te királyodat. Mivel ez csak egy gyakorló kör a tippedet automatikusan 1 re állítjuk.",
        "És ezzel elkezdődött a kör, az első kört mindig a játékos kezdi egyszemélyes módban, míg többszemélyes módban ez véletlen szerű. Kérlek kattints a [DÁMA] ra amivel leteszed azt az asztalra.",
        "Most hogy a többiek is raktak láthatod, hogy Bot 1 egy királyt tett le így ő szerezte meg az ütést. Viszont neked még mindig vinned kell egy kártyát",
        ""
    };
    
    String[] gombok = {
        "Osztás",
        "",
        "",
        "Rendben",
        ""
    };
    
    public KijelzoGyakorlo(String cim, int w, int h) {
        super(cim, w, h, false);
        t = new Timer(1,new A());
        t.start();
        leallit = false;
        frame.setLocation(0, frame.getY());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        kovetkezo = new Gomb(gombok[0],w/10*8,h/12);
        kovetkezo.setLocation(w/10, h/12*10);
        kovetkezo.addMouseListener(new Listener());
        p.add(kovetkezo);
        szoveg = new JTextArea(szovegek[0]);
        szoveg.setLineWrap(true);
        szoveg.setWrapStyleWord(true);
        szoveg.setEditable(false);
        szoveg.setForeground(new Color(nr,ng,nb));
        szoveg.setFocusable(false);
        szoveg.setBackground(new Color(0,0,0,0));
        szoveg.setHighlighter(null);
        szoveg.setSize(w/10*8,h/12*9);
        szoveg.setLocation(w/10, h/12);
        p.add(szoveg);
    }

    void lepes(){
        szovegSzamlalo++;
        if(gombok[szovegSzamlalo].equals("")){
            kovetkezo.setVisible(false);
        }else{
            kovetkezo.label.setText(gombok[szovegSzamlalo]);
            kovetkezo.setVisible(true);
        }
        szoveg.setText(szovegek[szovegSzamlalo]);
        switch(szovegSzamlalo){
            case 1:
                osztas();
                break;
            case 2:
                jatekter.rakhat = true;
                break;
            case 3:
                System.out.println("asd");
                break;
        }
    }

    void osztas() {
        for (int i = 0; i < 3; i++) {
            jatekter.ellenfel1.kartyak.add(new KartyaLap(0, 0));
            jatekter.ellenfel2.kartyak.add(new KartyaLap(0, 0));
            jatekter.ellenfel3.kartyak.add(new KartyaLap(0, 0));
            jatekter.kartyakFrissit();
        }
        jatekter.jatekosKartyak.add(new KartyaPanel(new KartyaLap(Main.vel(0, 3), 10), 0, 2));
        jatekter.kartyakFrissit();
        jatekter.jatekosKartyak.add(new KartyaPanel(new KartyaLap(Main.vel(0, 3), 4), 0, 2));
        jatekter.kartyakFrissit();
        jatekter.jatekosKartyak.add(new KartyaPanel(new KartyaLap(Main.vel(0, 3), 8), 0, 2));
        jatekter.kartyakFrissit();
        tbk = new KijelzoTippBekero("Kérem adja meg a tippjét", FOABLAK_SZEL/3, FOABLAK_MAG/4, false , true);
        tbk.setVisible(true);
    }

    void korEgy() {
        jatekter.ellenfel1.kartyak.remove(0);
        jatekter.ellenfel2.kartyak.remove(0);
        jatekter.ellenfel3.kartyak.remove(0);
        
        jatekter.ellenfel1.kivalasztottKartya = new KartyaLap(Main.vel(0, 3), 11);
        jatekter.ellenfel2.kivalasztottKartya = new KartyaLap(Main.vel(0, 3), Main.vel(0, 9));
        jatekter.ellenfel3.kivalasztottKartya = new KartyaLap(Main.vel(0, 3), Main.vel(0, 9));
        lepes();
    }
    
    class A implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            p.repaint();
        }

    }
    
    public class Listener implements MouseListener{

        @Override public void mouseClicked(MouseEvent me) {
            if(me.getSource() == kovetkezo) lepes();
        }
        @Override public void mousePressed(MouseEvent me) {}
        @Override public void mouseReleased(MouseEvent me) {}
        @Override public void mouseEntered(MouseEvent me) {}
        @Override public void mouseExited(MouseEvent me) {}
        
    }


    
}
