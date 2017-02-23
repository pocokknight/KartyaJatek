package Jatek;

import static Jatek.Main.jatekter;
import java.util.*;

public class BotJatekos {
    
    public static int getTipp(Vector<KartyaLap> v){
        int tipp = 0;
        for (int i = 0; i < v.size(); i++) {
            int vel = Main.vel(0, 12);
            if(v.get(i).ertek >= vel){
                tipp++;
            }
        }
        return tipp;
    }

    public static int getRakas(JatekosPanel p) {
        Collections.sort(p.kartyak);
        
        int asztalmax = -1;
        int sajatmax = -1;
        int sajatmin = 20;
        int sorszam = -1;
        
        for (int i = 0; i < jatekter.asztal.size(); i++) {
            if(jatekter.asztal.get(i).ertek > asztalmax)
                asztalmax = jatekter.asztal.get(i).ertek;
        }
        
        if(p.korPontErtek < p.tippertek){//még kell lap
            for (int i = 0; i < p.kartyak.size(); i++) {
                if(p.kartyak.get(i).ertek < sajatmin && p.kartyak.get(i).ertek > asztalmax){
                    sorszam = i;
                    sajatmin = p.kartyak.get(i).ertek;
                }
            }
            if(sorszam == -1){
                for (int i = 0; i < p.kartyak.size(); i++) {
                    if(p.kartyak.get(i).ertek < sajatmin){
                        sorszam = i;
                        sajatmin = p.kartyak.get(i).ertek;
                    }
                }
            }
        }else{//már nem kell lap
            for (int i = 0; i < p.kartyak.size(); i++) {
                if(p.kartyak.get(i).ertek > sajatmax && p.kartyak.get(i).ertek < asztalmax){
                    sorszam = i;
                    sajatmax = p.kartyak.get(i).ertek;
                }
            }
            if(sorszam == -1){
                for (int i = 0; i < p.kartyak.size(); i++) {
                    if(p.kartyak.get(i).ertek > sajatmax){
                        sorszam = i;
                        sajatmax = p.kartyak.get(i).ertek;
                    }
                }
            }
        }
        System.out.println(sorszam);
        return sorszam;
    }
    
}
