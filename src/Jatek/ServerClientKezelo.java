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

    void ujServer(int i) {
        serverki();
        clientki();
        S = new Server(i);
        futtathato = S;
        szal = new Thread(futtathato);
        szal.start();
    }

    void ujClient(String s) {
        serverki();
        clientki();
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
        }
    }
    
    void ClientnekUzenet(String s){
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
        tippeksorszama++;
        if(tippeksorszama == iranyito.letszam){
            tippvege();
            tippeksorszama = 0;
        }
    }

    void tippvege(){
        
    }
    
    class TippListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            tippelestimer.stop();
            sck.uzenetMindenkinek("tippvege");
            tippvege();
        }

    }
}
