package Jatek;

import static Jatek.Main.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.*;

public class JatekIranyito {
    
    Timer kartyamozgato;
    Vector<KartyaLap> pakli;
    int jatekLap,korLap,korJatekos;
    boolean leallit;
    boolean kartyaszamno;
    boolean egyszemelyes;
    
    public JatekIranyito(boolean e) {
        kartyaszamno = true;
        pakli = ujPakli();
        jatekLap = 1;
        korLap = 0;
        korJatekos = 0;
        leallit = false;
        egyszemelyes = e;
        if(e){
            letszam = 4;
        }else{
            
        }
    }

    public void oszt() {
        jatekter.asztal = new Vector<KartyaLap>();
        if(!leallit){
            if(egyszemelyes){
                for (int i = 0; i < jatekLap; i++) {
                    KartyaPanel kp = new KartyaPanel(pakli.get(0), 0, 2);
                    jatekter.jatekosKartyak.add(kp);
                    kp.addMouseListener(jatekter.listener);
                    pakli.remove(0);
                    jatekter.ellenfel1.kartyak.add(pakli.get(0));
                    pakli.remove(0);
                    jatekter.ellenfel2.kartyak.add(pakli.get(0));
                    pakli.remove(0);
                    jatekter.ellenfel3.kartyak.add(pakli.get(0));
                    pakli.remove(0);
                }
                if(kartyaszamno){
                    jatekLap++;
                }else{
                    jatekLap--;
                }
                pakli = ujPakli();
                if(pakli.size()/4 < jatekLap && kartyaszamno){
                    kartyaszamno = false;
                    jatekLap-=2;
                }
                if(jatekLap == 0){
                    System.out.println("Játék vége");
                }
            }else{
                
            }
        }else{
            System.out.println("Játék vége");
        }
        jatekter.kartyakFrissit();
        if(egyszemelyes){
            botTipp();
        }else{
            //jatekos tipp
        }
    }
    
    private Vector<KartyaLap> ujPakli() {
        Vector<KartyaLap> uj = new Vector();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                uj.add(new KartyaLap(i, j));
            }
        }
        Collections.shuffle(uj);
        return uj;
    }

    private void botTipp() {
        jatekter.ellenfel1.tippertek = BotJatekos.getTipp(jatekter.ellenfel1.kartyak);
        jatekter.ellenfel2.tippertek = BotJatekos.getTipp(jatekter.ellenfel2.kartyak);
        jatekter.ellenfel3.tippertek = BotJatekos.getTipp(jatekter.ellenfel3.kartyak);
        
        jatekter.kartyakFrissit();
        jatekosTipp();
    }

    private void jatekosTipp() {
        tbk = new KijelzoTippBekero("Kérem adja meg a tippjét", FOABLAK_SZEL/3, FOABLAK_MAG/4, false , egyszemelyes);
        tbk.setVisible(true);
    }

    int letszam;
    int sorszam = 0;
    
    void botKartyaRakas() {
        //System.out.println(sorszam+" "+letszam+" "+korJatekos);
        if (sorszam == letszam) {
            sorszam = 0;
            korPontSzamit();
            System.out.println("korvege");
        }else{
            if (korJatekos == 0) {
                sorszam++;
                korJatekos++;
                if (korJatekos == letszam) {
                    korJatekos = 0;
                }
                jatekosRak();
            } else if (korJatekos == 1) {
                sorszam++;
                korJatekos++;
                if (korJatekos == letszam) {
                    korJatekos = 0;
                }
                botRak(jatekter.ellenfel1);
            } else if (korJatekos == 2) {
                sorszam++;
                korJatekos++;
                if (korJatekos == letszam) {
                    korJatekos = 0;
                }
                botRak(jatekter.ellenfel2);
            } else if (korJatekos == 3) {
                sorszam++;
                korJatekos++;
                if (korJatekos == letszam) {
                    korJatekos = 0;
                }
                botRak(jatekter.ellenfel3);
            }
        }
    }

    private void jatekosRak() {
        jatekter.rakhat = true;
        System.out.println("Te raksz");
    }

    private void korPontSzamit() {
        korLap++;
        if(korLap == jatekLap){
            korLap = 0;
            //kartyavizsgalat
            jatekter.asztal = new Vector<KartyaLap>();
            oszt();
        }else{
            //kartyavizsgalat
            jatekter.asztal = new Vector<KartyaLap>();
            botKartyaRakas();
        }
    }

    private void botRak(JatekosPanel e) {
        System.out.println(e.nev.getText());
        int kartya = BotJatekos.getRakas(e);
        e.kivalasztottKartya = e.kartyak.get(kartya);
        e.kivalasztottKartya.rakta = e.nev.getText();
        e.kartyak.remove(kartya);
        mozgat(e);
    }

    private void mozgat(JatekosPanel e) {
        e.pozx = 20;
        e.pozy = e.pozys;
        e.mozgat = true;
        kartyamozgato = new Timer(1,new Mozgato(e));
        kartyamozgato.start();
        //e.mozgat = false;
    }

    class Mozgato implements ActionListener {

        JatekosPanel j;
        
        private Mozgato(JatekosPanel e) {
            j = e;
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (j.pozy != j.pozyig) {
                System.out.println(j.pozx);
                j.pozx += 3;
                if (j.pozx < j.pozxig) {
                    j.pozx = j.pozxig;
                }
                j.pozy += 4;
                if (j.pozy > j.pozyig) {
                    j.pozy = j.pozyig;
                }
                jatekter.p.repaint();
            }else{
                j.mozgat = false;
                jatekter.p.repaint();
                kartyamozgato.stop();
                botKartyaRakas();
            }
        }
    }
}
