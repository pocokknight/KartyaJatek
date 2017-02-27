package Jatek;

import static Jatek.Main.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.*;

public class JatekIranyito {
    
    Timer kartyamozgato;
    Timer kiirotimer;
    Vector<KartyaLap> pakli;
    int jatekLap,korLap,korJatekos;
    boolean leallit;
    boolean kartyaszamno;
    boolean egyszemelyes;
    
    public JatekIranyito(boolean e) {
        kartyaszamno = true;
        pakli = ujPakli();
        jatekLap = 1;
        korLap = 1;
        leallit = false;
        egyszemelyes = e;
        if(e){
            letszam = 4;
        }else{
            letszam = lobby.nevek.size();
            lobby.frame.dispose();
            lobby = null;
        }
        korJatekos = e ? 0 : Main.vel(0, letszam-1);
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
                    jatekvege(egyszemelyes);
                }
            }else{
                for (int i = 0; i < jatekLap; i++) {
                    try{
                        KartyaPanel kp = new KartyaPanel(pakli.get(0), 0, 2);
                        jatekter.jatekosKartyak.add(kp);
                        kp.addMouseListener(jatekter.listener);
                        pakli.remove(0);
                        jatekter.ellenfel1.kartyak.add(pakli.get(0));
                        sck.uzenetMindenkinek("ujkartya@"+jatekter.ellenfel1.nev.getText()+"@"+pakli.get(0).fajta+"@"+pakli.get(0).ertek);
                        pakli.remove(0);
                        jatekter.ellenfel2.kartyak.add(pakli.get(0));
                        sck.uzenetMindenkinek("ujkartya@"+jatekter.ellenfel2.nev.getText()+"@"+pakli.get(0).fajta+"@"+pakli.get(0).ertek);
                        pakli.remove(0);
                        jatekter.ellenfel3.kartyak.add(pakli.get(0));
                        sck.uzenetMindenkinek("ujkartya@"+jatekter.ellenfel3.nev.getText()+"@"+pakli.get(0).fajta+"@"+pakli.get(0).ertek);
                        pakli.remove(0);
                    }catch(Exception e){}
                }
                if(kartyaszamno){
                    jatekLap++;
                }else{
                    jatekLap--;
                }
                pakli = ujPakli();
                if(pakli.size()/letszam < jatekLap && kartyaszamno){
                    kartyaszamno = false;
                    jatekLap-=2;
                }
                if(jatekLap == 0){
                    jatekvege(egyszemelyes);
                }
            }

            jatekter.kartyakFrissit();
            if (egyszemelyes) {
                botTipp();
            } else {
                tobbjatekosTipp();
            }
        }else{
            jatekvege(egyszemelyes);
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
    
    void tobbjatekosKartyaRakas() {
        if (sorszam == letszam) {
            sorszam = 0;
            korPontSzamit();
        }else{
            try{
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
                sck.uzenetMindenkinek("kartyatfograkni@"+jatekter.ellenfel1.nev.getText());
            } else if (korJatekos == 2) {
                sorszam++;
                korJatekos++;
                if (korJatekos == letszam) {
                    korJatekos = 0;
                }
                sck.uzenetMindenkinek("kartyatfograkni@"+jatekter.ellenfel2.nev.getText());
            } else if (korJatekos == 3) {
                sorszam++;
                korJatekos++;
                if (korJatekos == letszam) {
                    korJatekos = 0;
                }
                sck.uzenetMindenkinek("kartyatfograkni@"+jatekter.ellenfel3.nev.getText());
            }
            }catch(Exception e){}
        }
    }
    
    void botKartyaRakas() {
        if (sorszam == letszam) {
            sorszam = 0;
            korPontSzamit();
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
        jatekter.nagyszovegkiir("Te következel!");
    }

    private void korPontSzamit() {
        System.out.println("korpontszamit");
        Kiiro k;
        korLap++;
        if(egyszemelyes){
            if(korLap == jatekLap){
                korLap = 1;
                String gyoztes = kartyakViszgal(jatekter.asztal);
                k = new Kiiro(gyoztes+" el a kört.",true);
                kiirotimer = new Timer(1500,k);
                kiirotimer.start();
            }else{
                String gyoztes = kartyakViszgal(jatekter.asztal);
                k = new Kiiro(gyoztes+" el a kört.",false);
                kiirotimer = new Timer(1500,k);
                kiirotimer.start();
            }
        }else{
            if(korLap == jatekLap){
                korLap = 1;
                String gyoztes = kartyakViszgal(null);
                k = new Kiiro(gyoztes+" el a kört.",true);
                kiirotimer = new Timer(1500,k);
                kiirotimer.start();
            }else{
                String gyoztes = kartyakViszgal(null);
                k = new Kiiro(gyoztes+" el a kört.",false);
                kiirotimer = new Timer(1500,k);
                kiirotimer.start();
            }
        }
    }

    private void botRak(JatekosPanel e) {
        System.out.println(e.nev.getText());
        int kartya = BotJatekos.getRakas(e);
        e.kivalasztottKartya = e.kartyak.get(kartya);
        e.kivalasztottKartya.rakta = e.nev.getText();
        jatekter.asztal.add(e.kivalasztottKartya);
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

    String kartyakViszgal(Vector<KartyaLap> a) {
        System.out.println("kartyakvizsgal");
        if(egyszemelyes){
            int poz = -1;
            int ertek = -1;
            System.out.println("itt van");
            for (int i = 0; i < a.size(); i++) {
                System.out.println(a.get(i).rakta);
                if(a.get(i).ertek >= ertek){
                    poz = i;
                    ertek = a.get(i).ertek;
                }
            }
            switch(a.get(poz).rakta){
                case "jatekos":
                    jatekter.jatekosKorPont++;
                    korJatekos = 0;
                    jatekter.labelfrissit();
                    return "Te vitted";
                case "Bot 1":
                    jatekter.ellenfel1.korPontErtek++;
                    korJatekos = 1;
                    jatekter.labelfrissit();
                    return "Bot 1 vitte";
                case "Bot 2":
                    jatekter.ellenfel2.korPontErtek++;
                    korJatekos = 2;
                    jatekter.labelfrissit();
                    return "Bot 2 vitte";
                case "Bot 3":
                    jatekter.ellenfel3.korPontErtek++;
                    korJatekos = 3;
                    jatekter.labelfrissit();
                    return "Bot 3 vitte";
            }
            return "";
        }else{
            int poz = -1;
            int ertek = -1;
            a = new Vector();
            jatekter.jatekoskijeloltkartya.lap.rakta = Main.nev;
            a.add(jatekter.jatekoskijeloltkartya.lap);
            try{
                a.add(jatekter.ellenfel1.kivalasztottKartya);
                jatekter.ellenfel1.kivalasztottKartya.rakta = jatekter.ellenfel1.nev.getText();
                a.add(jatekter.ellenfel2.kivalasztottKartya);
                jatekter.ellenfel2.kivalasztottKartya.rakta = jatekter.ellenfel2.nev.getText();
                a.add(jatekter.ellenfel3.kivalasztottKartya);
                jatekter.ellenfel3.kivalasztottKartya.rakta = jatekter.ellenfel3.nev.getText();
            }catch(Exception e){}
            for (int i = 0; i < a.size(); i++) {
                if(a.get(i).ertek >= ertek){
                    poz = i;
                    ertek = a.get(i).ertek;
                }
            }
            System.out.println(a.get(poz).rakta);
            if(a.get(poz).rakta.equals(Main.nev)){
                    jatekter.jatekosKorPont++;
                    korJatekos = 0;
                    jatekter.labelfrissit();
                    sck.vitte(Main.nev);
                    return "Te vitted";
            }else if(a.get(poz).rakta.equals(jatekter.ellenfel1.nev.getText())){
                    jatekter.ellenfel1.korPontErtek++;
                    korJatekos = 1;
                    jatekter.labelfrissit();
                    sck.vitte(jatekter.ellenfel1.nev.getText());
                    return jatekter.ellenfel1.nev.getText()+" vitte";
            }else if(a.get(poz).rakta.equals(jatekter.ellenfel2.nev.getText())){
                    jatekter.ellenfel2.korPontErtek++;
                    korJatekos = 2;
                    jatekter.labelfrissit();
                    sck.vitte(jatekter.ellenfel2.nev.getText());
                    return jatekter.ellenfel2.nev.getText()+" vitte";
            }else if(a.get(poz).rakta.equals(jatekter.ellenfel3.nev.getText())){
                    jatekter.ellenfel3.korPontErtek++;
                    korJatekos = 3;
                    jatekter.labelfrissit();
                    sck.vitte(jatekter.ellenfel3.nev.getText());
                    return jatekter.ellenfel3.nev.getText()+" vitte";
            }
            return "";
        }
    }
    
    private void osszesito() {
        System.out.println("osszesito");
        try{
        jatekter.jatekosOsszPont += jatekter.jatekosTipp == jatekter.jatekosKorPont ? 1 : (jatekter.jatekosTipp > jatekter.jatekosKorPont ? jatekter.jatekosKorPont-jatekter.jatekosTipp : jatekter.jatekosTipp-jatekter.jatekosKorPont);
        jatekter.jatekospont.setText("Pontod : "+jatekter.jatekosOsszPont);
        jatekter.jatekosKorPont = 0;
        
        jatekter.ellenfel1.osszPontErtek += jatekter.ellenfel1.tippertek == jatekter.ellenfel1.korPontErtek ? 1 : (jatekter.ellenfel1.tippertek > jatekter.ellenfel1.korPontErtek ? jatekter.ellenfel1.korPontErtek-jatekter.ellenfel1.tippertek : jatekter.ellenfel1.tippertek-jatekter.ellenfel1.korPontErtek);
        jatekter.ellenfel1.labelfrissit();
        jatekter.ellenfel1.tipp.setText("Tipp : -");
        jatekter.ellenfel1.korPontErtek = 0;
        
        jatekter.ellenfel2.osszPontErtek += jatekter.ellenfel2.tippertek == jatekter.ellenfel2.korPontErtek ? 1 : (jatekter.ellenfel2.tippertek > jatekter.ellenfel2.korPontErtek ? jatekter.ellenfel2.korPontErtek-jatekter.ellenfel2.tippertek : jatekter.ellenfel2.tippertek-jatekter.ellenfel2.korPontErtek);
        jatekter.ellenfel2.labelfrissit();
        jatekter.ellenfel2.tipp.setText("Tipp : -");
        jatekter.ellenfel2.korPontErtek = 0;
        
        jatekter.ellenfel3.osszPontErtek += jatekter.ellenfel3.tippertek == jatekter.ellenfel3.korPontErtek ? 1 : (jatekter.ellenfel3.tippertek > jatekter.ellenfel3.korPontErtek ? jatekter.ellenfel3.korPontErtek-jatekter.ellenfel3.tippertek : jatekter.ellenfel3.tippertek-jatekter.ellenfel3.korPontErtek);
        jatekter.ellenfel3.labelfrissit();
        jatekter.ellenfel3.tipp.setText("Tipp : -");
        jatekter.ellenfel3.korPontErtek = 0;
        
        }catch(Exception e){}
        sck.mindenfrissit();
        jatekter.labelfrissit();
    }

    private void jatekvege(boolean e) {
        vege = new KijelzoVege("A játék véget ért", FOABLAK_SZEL, FOABLAK_MAG, true ,e);
        vege.setVisible(true);
        jatekter.frame.dispose();
    }

    private void tobbjatekosTipp() {
        sck.tippeles();
    }

    class Kiiro implements ActionListener {

        String szoveg;
        int hanyadik = 0;
        boolean tobbkor;
        
        public Kiiro(String sz,boolean tobbkoros) {
            szoveg = sz;
            jatekter.kiiras.setText(szoveg);
            jatekter.kiiras.setVisible(true);
            tobbkor = tobbkoros;
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(egyszemelyes){
                if(!tobbkor){
                    kiirotimer.stop();
                    jatekter.kiiras.setVisible(false);
                    jatekter.asztal = new Vector<KartyaLap>();
                    jatekter.p.repaint();
                    jatekter.kartyakTakarit();
                    botKartyaRakas();
                }else{
                    kiirotimer.stop();
                    jatekter.kartyakTakarit();
                    osszesito();
                    jatekter.kiiras.setVisible(false);
                    jatekter.asztal = new Vector<KartyaLap>();
                    jatekter.p.repaint();
                    oszt();
                }
            }else{
                if(!tobbkor){
                    kiirotimer.stop();
                    jatekter.kiiras.setVisible(false);
                    jatekter.p.repaint();
                    jatekter.kartyakTakarit();
                    tobbjatekosKartyaRakas();
                }else{
                    kiirotimer.stop();
                    jatekter.kartyakTakarit();
                    osszesito();
                    jatekter.kiiras.setVisible(false);
                    jatekter.p.repaint();
                    oszt();
                }
            }
        }
        
    }
    
    class Mozgato implements ActionListener {

        JatekosPanel j;
        
        private Mozgato(JatekosPanel e) {
            j = e;
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (j.pozy != j.pozyig) {
                j.pozx += 8;
                if (j.pozx < j.pozxig) {
                    j.pozx = j.pozxig;
                }
                j.pozy += 6;
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
