package Jatek;

import static Jatek.Main.*;
import java.util.*;

public class JatekIranyito {
    
    Timer tipp = new Timer();
    Vector<KartyaLap> pakli;
    int jatekLap,korLap;
    boolean leallit;
    boolean kartyaszamno;
    boolean egyszemelyes;
    
    public JatekIranyito(boolean e) {
        kartyaszamno = true;
        pakli = ujPakli();
        jatekLap = 1;
        korLap = 0;
        leallit = false;
        egyszemelyes = e;
    }

    public void oszt() {
        if(!leallit){
            if(egyszemelyes){
                for (int i = 0; i < jatekLap; i++) {
                    jatekter.kartyak.add(new KartyaPanel(pakli.get(0), 0, 2));
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
    }

}
