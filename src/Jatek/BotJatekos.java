package Jatek;

import java.util.*;

public class BotJatekos {
    
    public static int getTipp(Vector<KartyaLap> v){
        int tipp = 0;
        for (int i = 0; i < v.size(); i++) {
            int vel = Main.vel(0, 12);
            System.out.println(v.get(i).ertek+" "+vel);
            if(v.get(i).ertek >= vel){
                tipp++;
            }
        }
        return tipp;
    }
    
}
