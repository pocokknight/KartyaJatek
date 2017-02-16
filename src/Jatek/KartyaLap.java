package Jatek;

import Kepek.Kep;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Vector;

public class KartyaLap{

    String rakta;
    int ertek;
    int fajta;
    boolean kijelolt;
    BufferedImage kepasz;
    BufferedImage kepfajta;
    BufferedImage kepertek;
    static BufferedImage hatlap = Kep.getKep(Kep.class,"k_hatlap.png");
    static BufferedImage hatter = Kep.getKep(Kep.class,"k_hatter.png");
    
    
    public int getErtI(){ return ertek; }
    public String getErtS(){
        String s = "";
        if(ertek<9)     s=(ertek+2)+"";
        if(ertek==9)    s="J";
        if(ertek==10)   s="Q";
        if(ertek==11)   s="K";
        if(ertek==12)   s="A";
        return s;
    }
    
    KartyaLap(int f,int e){
        ertek = e;
        fajta = f;
        kijelolt = false;
        kepfajta = Kep.getKep(Kep.class,"s_"+fajta+".png");
        kepertek = Kep.getKep(Kep.class,"e_"+ertek+".png");
        if(ertek>8){
            if(ertek==9)    kepasz = Kep.getKep(Kep.class,"a_J.png");
            if(ertek==10)   kepasz = Kep.getKep(Kep.class,"a_Q.png");       
            if(ertek==11)   kepasz = Kep.getKep(Kep.class,"a_K.png");   
            if(ertek==12)   kepasz = Kep.getKep(Kep.class,"a_"+fajta+".png");
        }
        
        if(fajta<2){
            kepertek = Szinezo.szinez("37 37 37", kepertek);
            if(ertek>8 && ertek<12)
            kepasz = Szinezo.szinez("37 37 37", kepasz);
        }else{
            kepertek = Szinezo.szinez("164 6 32", kepertek);
            if(ertek>8 && ertek<12)
            kepasz = Szinezo.szinez("164 6 32", kepasz);
        }
    }
    
    public void grafikaHatlap(Graphics g, int x, int y, int szorzo){
        if(kijelolt){
            g.setColor(new Color(50,175,255));
            g.fillRoundRect(x-szorzo, y-szorzo, KartyaLap.hatter.getWidth()*szorzo+(2*szorzo),KartyaLap.hatter.getHeight()*szorzo+(2*szorzo),9*szorzo,9*szorzo);
        }
        g.drawImage(KartyaLap.hatlap, x, y, KartyaLap.hatlap.getWidth()*szorzo, KartyaLap.hatlap.getHeight()*szorzo, null);
    }
    
    public void grafikaTeljes(Graphics g, int x, int y, int szorzo){
        if(kijelolt){
            g.setColor(new Color(50,175,255));
            g.fillRoundRect(x-szorzo, y-szorzo, KartyaLap.hatter.getWidth()*szorzo+(2*szorzo),KartyaLap.hatter.getHeight()*szorzo+(2*szorzo),9*szorzo,9*szorzo);
        }
        g.drawImage(KartyaLap.hatter, x, y, KartyaLap.hatter.getWidth()*szorzo, KartyaLap.hatter.getHeight()*szorzo, null);
        g.drawImage(kepertek,x+ertekfent.x*szorzo,y+ertekfent.y*szorzo, kepertek.getWidth()*szorzo, kepertek.getHeight()*szorzo, null);
        x++;
        y++;
        if(ertek<9){
            for (int i = 0; i < elhelyez[ertek].length; i++) {
                g.drawImage(kepfajta,x+elhelyez[ertek][i].x*szorzo,y+elhelyez[ertek][i].y*szorzo, kepfajta.getWidth()*szorzo, kepfajta.getHeight()*szorzo, null);
            }
        }else{
            y--;
            x--;
            g.drawImage(kepasz, x+helyasz.x*szorzo, y+helyasz.y*szorzo, kepasz.getWidth()*szorzo, kepasz.getHeight()*szorzo, null);
            if(ertek<12){
                g.drawImage(kepfajta,x+fajtafent.x*szorzo,y+fajtafent.y*szorzo, kepfajta.getWidth()*szorzo, kepfajta.getHeight()*szorzo, null);
            }
        }
    }
    
    public void grafikaMinimal(Graphics g, int x, int y, int szorzo){
        if(kijelolt){
            g.setColor(new Color(50,175,255));
            g.fillRoundRect(x-szorzo, y-szorzo, KartyaLap.hatter.getWidth()*szorzo+(2*szorzo),KartyaLap.hatter.getHeight()*szorzo+(2*szorzo),9*szorzo,9*szorzo);
        }
        g.drawImage(KartyaLap.hatter, x, y, KartyaLap.hatter.getWidth()*szorzo, KartyaLap.hatter.getHeight()*szorzo, null);
        g.drawImage(kepertek,x+ertekfent.x*szorzo,y+ertekfent.y*szorzo, kepertek.getWidth()*szorzo, kepertek.getHeight()*szorzo, null);
        g.drawImage(kepfajta,x+fajtafent.x*szorzo,y+fajtafent.y*szorzo, kepfajta.getWidth()*szorzo, kepfajta.getHeight()*szorzo, null);
    }
    
    public static Vector<KartyaLap> pakli(){
        Vector<KartyaLap> v = new Vector();
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                v.add(new KartyaLap(i,j));
            }
        }
        Collections.shuffle(v);
        return v;
    }
    
    //ertek elhelyezkedési lista
    
    static Point ertekfent = new Point(2,2);
    static Point fajtafent = new Point(2,10);
    static Point helyasz = new Point(7,19);
    
    static int elso_oszl = 10;
    static int masodik_oszl = 15;
    static int harmadik_oszl = 19;
    
    static Point[][] elhelyez = {
        /*2*/{new Point(elso_oszl,12),new Point(harmadik_oszl,40)},
        /*3*/{new Point(elso_oszl,12),new Point(masodik_oszl,26),new Point(harmadik_oszl,40)},
        /*4*/{new Point(elso_oszl,12),new Point(elso_oszl,40),new Point(harmadik_oszl,12),new Point(harmadik_oszl,40)},
        /*5*/{new Point(elso_oszl,12),new Point(elso_oszl,40),new Point(masodik_oszl,26),new Point(harmadik_oszl,12),new Point(harmadik_oszl,40)},
        /*6*/{new Point(elso_oszl,12),new Point(elso_oszl,40),new Point(elso_oszl,26),new Point(harmadik_oszl,26),new Point(harmadik_oszl,12),new Point(harmadik_oszl,40)},
        /*7*/{new Point(elso_oszl,7),new Point(harmadik_oszl,7),new Point(masodik_oszl,17),new Point(elso_oszl,37),new Point(harmadik_oszl,37),new Point(elso_oszl,47),new Point(harmadik_oszl,47)},
        /*8*/{new Point(elso_oszl,7),new Point(harmadik_oszl,7),new Point(elso_oszl,17),new Point(harmadik_oszl,17),new Point(elso_oszl,37),new Point(harmadik_oszl,37),new Point(elso_oszl,47),new Point(harmadik_oszl,47)},
        /*9*/{new Point(elso_oszl,7),new Point(harmadik_oszl,7),new Point(elso_oszl,17),new Point(harmadik_oszl,17),new Point(masodik_oszl,27),new Point(elso_oszl,37),new Point(harmadik_oszl,37),new Point(elso_oszl,47),new Point(harmadik_oszl,47)},
        /*10*/{new Point(elso_oszl,7),new Point(harmadik_oszl,7),new Point(elso_oszl,17),new Point(harmadik_oszl,17),new Point(elso_oszl,27),new Point(harmadik_oszl,27),new Point(elso_oszl,37),new Point(harmadik_oszl,37),new Point(elso_oszl,47),new Point(harmadik_oszl,47)}
    };

    void setRakta(String j) {
        rakta = j;
    }
    
}
