package Jatek;

import static Jatek.Main.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

class ServerClientKezelo {

    static Runnable futtathato;
    static Thread szal;
    Server S = null;
    Client C = null;
    int tippek = 0;
    boolean leallit;

    void ujServer(int i) {
        serverki();
        clientki();
        leallit = false;
        S = new Server(i);
        futtathato = S;
        szal = new Thread(futtathato);
        szal.start();
    }

    void ujClient(String s) {
        serverki();
        clientki();
        leallit = false;
        String[] t = s.split(":");
        C = new Client(t[0], Integer.parseInt(t[1]));
        futtathato = C;
        szal = new Thread(futtathato);
        szal.start();
    }

    public void leallitas() {
        szal.stop();
        serverki();
        clientki();
    }
    
    private void serverki() {
        if (S != null) {
            S.leall();
            S = null;
        }
    }

    private void clientki() {
        if (C != null) {
            C.leall();
            C = null;
        }
    }

    void ServernekUzenet(String s){
        System.out.println("server kapott üzenet "+s);
        String[] t = s.split("@");
        switch(t[0]){
            case "uzenet":
                serverKapottuzenet(t[1]);
                break;
            case "jatekostipp":
                uzenetMindenkinek("tippelt@"+t[1]+"@"+t[2]);
                try{
                    if(jatekter.ellenfel1.nev.getText().equals(t[1])) jatekter.ellenfel1.tippertek = Integer.parseInt(t[2]);
                    else if(jatekter.ellenfel2.nev.getText().equals(t[1])) jatekter.ellenfel2.tippertek = Integer.parseInt(t[2]);
                    else if(jatekter.ellenfel3.nev.getText().equals(t[1])) jatekter.ellenfel3.tippertek = Integer.parseInt(t[2]);
                }catch(Exception e){}
                jatekter.labelfrissit();
                tippszamlalo();
                break;
            case "kartyatrakott":
                uzenetMindenkinek(s);
                try{
                    if(jatekter.ellenfel1.nev.getText().equals(t[1])){
                        jatekter.ellenfel1.kivalasztottKartya = new KartyaLap(Integer.parseInt(t[2]), Integer.parseInt(t[3]));
                        jatekter.ellenfel1.kartyak.remove(0);
                    }
                    else if(jatekter.ellenfel2.nev.getText().equals(t[1])){
                        jatekter.ellenfel2.kivalasztottKartya = new KartyaLap(Integer.parseInt(t[2]), Integer.parseInt(t[3]));
                        jatekter.ellenfel2.kartyak.remove(0);
                    }
                    else if(jatekter.ellenfel3.nev.getText().equals(t[1])){
                        jatekter.ellenfel3.kivalasztottKartya = new KartyaLap(Integer.parseInt(t[2]), Integer.parseInt(t[3]));
                        jatekter.ellenfel3.kartyak.remove(0);
                    }
                }catch(Exception e){}
                jatekter.kartyakFrissit();
                iranyito.tobbjatekosKartyaRakas();
                break;
            case "kilepek":
                uzenetMindenkinek("jatekvege");
                vege = new KijelzoVege("A játék véget ért!", FOABLAK_SZEL, FOABLAK_MAG, true, false);
                vege.setVisible(true);
                jatekter.frame.dispose();
                jatekter = null;
                sck.leallitas();
                sck = null;
                break;
        }
    }
    
    void ClientnekUzenet(String s){
        System.out.println("client kapott uzenet "+s);
        String[] t = s.split("@");
        switch(t[0]){
            case "lobbynevek":
                if(lobby != null){
                    lobby.nevek.clear();
                    for (int i = 1; i < t.length; i++) {
                        String[] tomb = t[i].split("#");
                        lobby.nevek.add(new StringBoolean(tomb[0],tomb[1].equals("true")));
                    }
                }
                lobby.nevekfrissit();
                break;
            case "ujnev":
                Main.nev = t[1];
                break;
            case "uzenet":
                lobby.chat.setText(lobby.chat.getText()+"\n"+t[1]);
                break;
            case "kezdes":
                jatekter = new KijelzoJatekter("Riki-tiki by: Pocok", FOABLAK_SZEL, FOABLAK_MAG, false);
                jatekter.setVisible(true);
                lobby.frame.dispose();
                lobby = null;
                break;
            case "ujkartya":
                if(t[1].equals(Main.nev)){
                    jatekter.jatekosKartyak.add(new KartyaPanel(new KartyaLap(Integer.parseInt(t[2]), Integer.parseInt(t[3])), 0, 2));
                    try{
                        jatekter.ellenfel1.kartyak.add(new KartyaLap(0, 0));
                        jatekter.ellenfel2.kartyak.add(new KartyaLap(0, 0));
                        jatekter.ellenfel3.kartyak.add(new KartyaLap(0, 0));
                    }catch(Exception e){}
                    jatekter.kartyakFrissit();
                }
                break;
            case "tippetkerek":
                tbk = new KijelzoTippBekero("Kérem adja meg a tippjét 30 mp alatt.", FOABLAK_SZEL/3, FOABLAK_MAG/4, false , false);
                tbk.setVisible(true);
                try{
                    jatekter.ellenfel1.tippertek = 0;
                    jatekter.ellenfel2.tippertek = 0;
                    jatekter.ellenfel3.tippertek = 0;
                }catch(Exception e){}
                break;
            case "tippelt":
                try{
                    if(jatekter.ellenfel1.nev.getText().equals(t[1])) jatekter.ellenfel1.tippertek = Integer.parseInt(t[2]);
                    else if(jatekter.ellenfel2.nev.getText().equals(t[1])) jatekter.ellenfel2.tippertek = Integer.parseInt(t[2]);
                    else if(jatekter.ellenfel3.nev.getText().equals(t[1])) jatekter.ellenfel3.tippertek = Integer.parseInt(t[2]);
                }catch(Exception e){}
                jatekter.labelfrissit();
                break;
            case "tippvege":
                if(tbk != null){
                    tbk.frame.dispose();
                    tbk = null;
                    jatekter.jatekosTipp = 0;
                    jatekter.labelfrissit();
                }
                break;
            case "kartyatrakott":
                try{
                    if(jatekter.ellenfel1.nev.getText().equals(t[1])){
                        jatekter.ellenfel1.kivalasztottKartya = new KartyaLap(Integer.parseInt(t[2]), Integer.parseInt(t[3]));
                        jatekter.ellenfel1.kartyak.remove(0);
                    }
                    else if(jatekter.ellenfel2.nev.getText().equals(t[1])){
                        jatekter.ellenfel2.kivalasztottKartya = new KartyaLap(Integer.parseInt(t[2]), Integer.parseInt(t[3]));
                        jatekter.ellenfel2.kartyak.remove(0);
                    }
                    else if(jatekter.ellenfel3.nev.getText().equals(t[1])){
                        jatekter.ellenfel3.kivalasztottKartya = new KartyaLap(Integer.parseInt(t[2]), Integer.parseInt(t[3]));
                        jatekter.ellenfel3.kartyak.remove(0);
                    }
                }catch(Exception e){}
                jatekter.kartyakFrissit();
                break;
            case "kartyatfograkni":
                if(t[1].equals(Main.nev)){
                    jatekter.rakhat = true;
                    jatekter.nagyszovegkiir("Te következel!");
                }
                break;
            case "utottegyet":
                if(t[1].equals(Main.nev)){
                    jatekter.nagyszovegkiir("Te vitted.");
                    jatekter.jatekosKorPont++;
                }else{
                    jatekter.nagyszovegkiir(t[1]+" vitte.");
                    try{
                    if(jatekter.ellenfel1.nev.getText().equals(t[1])){
                        jatekter.ellenfel1.korPontErtek++;
                    }
                    else if(jatekter.ellenfel2.nev.getText().equals(t[1])){
                        jatekter.ellenfel2.korPontErtek++;
                    }
                    else if(jatekter.ellenfel3.nev.getText().equals(t[1])){
                        jatekter.ellenfel3.korPontErtek++;
                    }
                }catch(Exception e){}
                }
                jatekter.labelfrissit();
                jatekter.kartyakTakarit();
                break;
            case "mindentfrissit":
                for (int i = 1; i < t.length; i++) {
                    String[] t2 = t[i].split("#");
                    try{
                    if(Main.nev.equals(t2[0])){
                        jatekter.jatekosTipp = 0;
                        jatekter.jatekosKorPont = 0;
                        jatekter.jatekosOsszPont = Integer.parseInt(t2[1]);
                    }else if(jatekter.ellenfel1.nev.getText().equals(t2[0])){
                        jatekter.ellenfel1.tippertek = 0;
                        jatekter.ellenfel1.korPontErtek = 0;
                        jatekter.ellenfel1.osszPontErtek = Integer.parseInt(t2[1]);
                    }
                    else if(jatekter.ellenfel2.nev.getText().equals(t2[0])){
                        jatekter.ellenfel2.tippertek = 0;
                        jatekter.ellenfel2.korPontErtek = 0;
                        jatekter.ellenfel2.osszPontErtek = Integer.parseInt(t2[1]);
                    }
                    else if(jatekter.ellenfel3.nev.getText().equals(t2[0])){
                        jatekter.ellenfel3.tippertek = 0;
                        jatekter.ellenfel3.korPontErtek = 0;
                        jatekter.ellenfel3.osszPontErtek = Integer.parseInt(t2[1]);
                    }
                    }catch(Exception e){}
                }
                break;
            case "jatekvege":
                vege = new KijelzoVege("A játék véget ért!", FOABLAK_SZEL, FOABLAK_MAG, true, false);
                vege.setVisible(true);
                jatekter.frame.dispose();
                jatekter = null;
                sck.leallitas();
                sck = null;
                break;
        }
    }

    void nevekfrissit() {
        String s = "lobbynevek";
        if(S != null){
            for (int i = 0; i < lobby.nevek.size(); i++) {
                s+="@"+lobby.nevek.get(i).s+"#"+lobby.nevek.get(i).b;
            }
            for (int i = 0; i < S.alserverek.size(); i++) {
                S.alserverek.get(i).kuld(s);
            }
        }
    }

    void serverKapottuzenet(String s){
        for (int i = 0; i < S.alserverek.size(); i++) {
            S.alserverek.get(i).kuld("uzenet@"+s);
        }
        lobby.chat.setText(lobby.chat.getText()+"\n"+s);
    }
    
    void uzenet(String s) {
        if(S != null){
            serverKapottuzenet(Main.nev+" : "+s);
        }else if(C != null){
            C.kuld("uzenet@"+Main.nev+" : "+s);
        }
    }

    void uzenetMindenkinek(String s) {
        if(S != null){
            for (int i = 0; i < S.alserverek.size(); i++) {
                S.alserverek.get(i).kuld(s);
            }
        }
    }
    
    Timer tippelestimer;

    void tippeles() {
        if(S != null){
            tippelestimer = new Timer(30000,new TippListener());
            tippelestimer.start();
            try{
                jatekter.ellenfel1.tippertek = 0;
                jatekter.ellenfel2.tippertek = 0;
                jatekter.ellenfel3.tippertek = 0;
            }catch(Exception e){}
            uzenetMindenkinek("tippetkerek");
            tbk = new KijelzoTippBekero("Kérem adja meg a tippjét 30 mp alatt.", FOABLAK_SZEL/3, FOABLAK_MAG/4, false , false);
            tbk.setVisible(true);
        }
    }

    void tippelek() {
        if(C != null){
            C.kuld("jatekostipp@"+Main.nev+"@"+jatekter.jatekosTipp);
        }else{
            uzenetMindenkinek("tippelt@"+Main.nev+"@"+jatekter.jatekosTipp);
            tippszamlalo();
        }
    }
    
    int tippeksorszama = 0;
    
    void tippszamlalo(){
        System.out.println(tippeksorszama);
        if(S != null){
            tippeksorszama++;
            if(tippeksorszama == iranyito.letszam){
                tippvege();
                tippeksorszama = 0;
            }
        }
    }

    void tippvege(){
        if(S != null){
            tippelestimer.stop();
            tippelestimer = null;
            iranyito.tobbjatekosKartyaRakas();
        }
    }

    void kartyarakas(int fajta, int ertek) {
        if(S != null){
            uzenetMindenkinek("kartyatrakott@"+Main.nev+"@"+fajta+"@"+ertek);
            iranyito.tobbjatekosKartyaRakas();
        }else{
            C.kuld("kartyatrakott@"+Main.nev+"@"+fajta+"@"+ertek);
        }
    }

    void vitte(String s) {
        if(S != null){
            uzenetMindenkinek("utottegyet@"+s);
        }
    }

    void mindenfrissit() {
        if(S != null){
            String s = "mindentfrissit";
            try{
                s+= "@"+Main.nev+"#"+jatekter.jatekosOsszPont;
                s+= "@"+jatekter.ellenfel1.nev.getText()+"#"+jatekter.ellenfel1.osszPontErtek;
                s+= "@"+jatekter.ellenfel2.nev.getText()+"#"+jatekter.ellenfel2.osszPontErtek;
                s+= "@"+jatekter.ellenfel3.nev.getText()+"#"+jatekter.ellenfel3.osszPontErtek;
            }catch(Exception e){}
            uzenetMindenkinek(s);
        }
    }

    void leakarallitani() {
        if(C != null){
            C.kuld("leallvalt");
        }
    }
    
    class TippListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            tippelestimer.stop();
            sck.uzenetMindenkinek("tippvege");
                if(tbk != null){
                    tbk.frame.dispose();
                    tbk = null;
                    jatekter.jatekosTipp = 0;
                    jatekter.labelfrissit();
                }
            tippvege();
        }

    }
}
