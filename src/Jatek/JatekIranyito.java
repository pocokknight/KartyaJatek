package Jatek;

import static Jatek.Main.*;
import java.util.*;

public class JatekIranyito {
    
    Timer tipp = new Timer();
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
    }

    public void oszt() {
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
        tbk = new KijelzoTippBekero("Kérem adja meg a tippjét", FOABLAK_SZEL/3, FOABLAK_MAG/4, false);
        tbk.setVisible(true);
    }

    void botKartyaRakas() {
        jatekosRak();
        jatekter.asztal = new Vector<KartyaLap>();
        int letszam = 0;
        int a = 0;
            jatekter.p.repaint();
            switch (korJatekos){
                case 0:
                    if(!jatekter.rakhat)
                    letszam++;
                    break;
                case 1:
                    a = BotJatekos.getRakas(jatekter.ellenfel1);
                    jatekter.ellenfel1.kivalasztottKartya = jatekter.ellenfel1.kartyak.get(a);
                    jatekter.asztal.add(jatekter.ellenfel1.kartyak.get(a));
                    jatekter.ellenfel1.kartyak.get(a).rakta = jatekter.ellenfel1.nev.getText();
                    jatekter.ellenfel1.kartyak.remove(a);
                    kovJatekos();
                    letszam++;
                    break;
                case 2:
                    a = BotJatekos.getRakas(jatekter.ellenfel2);
                    jatekter.ellenfel2.kivalasztottKartya = jatekter.ellenfel2.kartyak.get(a);
                    jatekter.asztal.add(jatekter.ellenfel2.kartyak.get(a));
                    jatekter.ellenfel2.kartyak.get(a).rakta = jatekter.ellenfel2.nev.getText();
                    jatekter.ellenfel2.kartyak.remove(a);
                    kovJatekos();
                    letszam++;
                    break;
                case 3:
                    a = BotJatekos.getRakas(jatekter.ellenfel3);
                    jatekter.ellenfel3.kivalasztottKartya = jatekter.ellenfel3.kartyak.get(a);
                    jatekter.asztal.add(jatekter.ellenfel3.kartyak.get(a));
                    jatekter.ellenfel3.kartyak.get(a).rakta = jatekter.ellenfel3.nev.getText();
                    jatekter.ellenfel3.kartyak.remove(a);
                    kovJatekos();
                    letszam++;
                    break;
            }
        System.out.println("mindenki rakott");
    }
    
    void kovJatekos(){
        korJatekos++;
        if(egyszemelyes){
            if(korJatekos>4) korJatekos %= 4;
        }else{
            
        }
    }

    private void jatekosRak() {
        jatekter.rakhat = true;
    }
}
