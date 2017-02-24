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
        "Ebben a körbe 3 kártyát kaptál. Mint látod a kör elején megjelent egy ablak amelyben meg kell tippelned hány ütést fogsz elvinnni (egy ilyen kör ha minden játékos tett egy lapot most például ezt 0 és 3 közé kellene tippelned) jelen esetben rendelkezel egy 6 -ossal,10 -essel és egy dámával. Minnél nagyobb a kártyád annál nagyobb az eséllyed az ütésre. Akkor ütsz egyet ha te raktad a legnagyobb kártyát utoljára vagyis ha van az asztalon egy király azt te ütni tudod ha királyt teszel le, ugyan így más is ütheti királlyal a te királyodat. Mivel ez csak egy gyakorló kör a tippedet automatikusan 1 -re állítjuk.",
        "És ezzel elkezdődött a kör, az első kört mindig a játékos kezdi egyszemélyes módban, míg többszemélyes módban ez véletlen szerű. Kérlek kattints a [DÁMA] -ra amivel leteszed azt az asztalra.",
        "Most hogy a többiek is raktak láthatod, hogy Bot 1 egy királyt tett le így ő szerezte meg az ütést. Viszont neked még mindig vinned kell egy ütést",
        "Minden körben az kezd aki az előző körben elvitte az ütést. Jelen esetben ez Bot 1. Mivel még mindig szükséged van arra az egy ütésre így nem ártana ezt elütnöd... de ha jobban megnézed Bot 2 már lerakott egy [BUBI] -t így ezt már nem tudod elütni hiszen nincsen annál nagyobb lapod. Ilyen esetben ha még kell ütés de ezt már nem tudod megütni érdemes a legalacsonyabb értékű lapot rátenni. Ez most a 6 -os kártyád, kérlek rakd le azt.",
        "Mivel Bot 2 ütött az előző körben így most ő kezd. Mint látod egy 10-est rakott le amivel ha te kezdtél volna megütötte volna a te 10-esedet, de így te ütöd meg az övét. Márcsak abban kell reménykedned, hogy Bot 1 nem fog nálad nagyobbat tenni, viszont erre elég kevés az esély, hiszen a tippje 1 volt és azt már megütötte. Kérlek rakd le az utolsó lapodat.",
        "Szép munka volt! Teljesítetted a Gyakorló módot. Ezután nekiálhatsz csiszolni a tehetségedet a Bot játékosok ellen Egyjátékos módban vagy megmérkőzhetsz barátaiddal Többjátékos módban. Utolsó megjegyzésként lehet, hogy észrevetted a jobb alsó sarokban a kilépés gomb felirata megváltozott [Kilépés : nem] -ről [Kilépés : igen]-re. A játékban a kilépés csak a körök végén lehetséges. Amikor rákattintasz a kilépés gombra az vált igen/nem között ha igenen van akkor a következő kör végén szabályosan visszadob a menübe. További jó játékot! \nA játék készítője:\nHorváth Patrik"
    };
    
    String[] gombok = {
        "Osztás",
        "",
        "",
        "Rendben",
        "",
        "",
        "Vége"
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
        if(szovegSzamlalo != 6){
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
                case 4:
                    takarit();
                    korKetto();
                    break;
                case 5:
                    korHarom();
                    break;
                case 6:
                    korokVege();
                    break;
            }
        }else{
            gyakorlo.frame.dispose();
            gyakorlo = null;
            jatekter.frame.dispose();
            jatekter = null;
            valaszto = new KijelzoValaszto("Kártyajáték - készítette: Horváth Patrik", FOABLAK_SZEL, FOABLAK_MAG, true);
            valaszto.setVisible(true);
        }
    }

    void osztas() {
        for (int i = 0; i < 3; i++) {
            jatekter.ellenfel1.kartyak.add(new KartyaLap(0, 0));
            jatekter.ellenfel2.kartyak.add(new KartyaLap(0, 0));
            jatekter.ellenfel3.kartyak.add(new KartyaLap(0, 0));
            jatekter.kartyakFrissit();
        }
        
        jatekter.ellenfel1.tippertek = 1;
        jatekter.ellenfel2.tippertek = 1;
        jatekter.ellenfel3.tippertek = 0;
        
        jatekter.labelfrissit();
        
        jatekter.jatekosKartyak.add(new KartyaPanel(new KartyaLap(Main.vel(0, 3), 10), 0, 2));
        jatekter.kartyakFrissit();
        jatekter.jatekosKartyak.add(new KartyaPanel(new KartyaLap(Main.vel(0, 3), 4), 0, 2));
        jatekter.kartyakFrissit();
        jatekter.jatekosKartyak.add(new KartyaPanel(new KartyaLap(1, 8), 0, 2));
        jatekter.kartyakFrissit();
        tbk = new KijelzoTippBekero("Kérem adja meg a tippjét", FOABLAK_SZEL/3, FOABLAK_MAG/4, false , true);
        tbk.setVisible(true);
    }

    void korEgy() {
        jatekter.ellenfel1.kartyak.remove(0);
        jatekter.ellenfel2.kartyak.remove(0);
        jatekter.ellenfel3.kartyak.remove(0);
        
        jatekter.ellenfel1.kivalasztottKartya = new KartyaLap(Main.vel(0, 3), 11);
        jatekter.ellenfel2.kivalasztottKartya = new KartyaLap(Main.vel(0, 3), Main.vel(0, 3));
        jatekter.ellenfel3.kivalasztottKartya = new KartyaLap(Main.vel(0, 3), Main.vel(5, 7));
        lepes();
    }

    void korKetto() {
        jatekter.ellenfel1.kartyak.remove(0);
        jatekter.ellenfel2.kartyak.remove(0);
        jatekter.ellenfel3.kartyak.remove(0);
        
        jatekter.ellenfel1.kivalasztottKartya = new KartyaLap(Main.vel(0, 3), Main.vel(5, 7));
        jatekter.ellenfel2.kivalasztottKartya = new KartyaLap(Main.vel(0, 3), 9);
        jatekter.ellenfel3.kivalasztottKartya = new KartyaLap(Main.vel(0, 3), Main.vel(0, 3));
        jatekter.rakhat = true;
    }
    
    void korHarom() {
        jatekter.ellenfel2.kartyak.remove(0);
        jatekter.ellenfel3.kartyak.remove(0);
        
        jatekter.ellenfel2.kivalasztottKartya = new KartyaLap(2, 8);
        jatekter.ellenfel3.kivalasztottKartya = new KartyaLap(Main.vel(0, 3), Main.vel(0, 7));
        jatekter.rakhat = true;
    }
    
    void korokVege(){
        jatekter.ellenfel1.kartyak.remove(0);
        jatekter.ellenfel1.kivalasztottKartya = new KartyaLap(Main.vel(0, 3), Main.vel(0, 6));
        jatekter.kilepes.label.setText("Kilépés : igen");
    }
    
    void takarit() {
        jatekter.ellenfel1.kivalasztottKartya = null;
        jatekter.ellenfel2.kivalasztottKartya = null;
        jatekter.ellenfel3.kivalasztottKartya = null;
        jatekter.p.remove(jatekter.jatekoskijeloltkartya);
        jatekter.jatekoskijeloltkartya = null;
        jatekter.kartyakFrissit();
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
