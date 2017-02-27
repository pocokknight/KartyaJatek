package Jatek;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static Jatek.Main.*;
import java.util.Vector;
import static Jatek.Main.*;

public class KijelzoJatekter extends Kijelzo {

    Gomb kilepes;
    Vector<KartyaPanel> jatekosKartyak;
    KartyaPanel jatekoskijeloltkartya;
    Vector<KartyaLap> asztal;
    boolean rakhat,egyjatekos;
    int jatekosTipp,jatekosKorPont,jatekosOsszPont;
    JatekosPanel ellenfel1,ellenfel2,ellenfel3;
    Listener listener = new Listener();
    JLabel jatekospont,jatekostipp,kiiras,sajatutes;
    
    public KijelzoJatekter(String cim, int w, int h,boolean egyjatekos) {
        super(cim, w, h, true);
        this.egyjatekos = egyjatekos;
        rakhat = false;
        kilepes = new Gomb("Kilépés : nem",getSzel()/10,getMag()/20);
        kilepes.setLocation(getSzel()/10*8, getMag()/10*9);
        p.add(kilepes);
        kilepes.addMouseListener(new Listener());
        jatekospont = new JLabel("Pontod : "+jatekosOsszPont);
        jatekostipp = new JLabel("Utolsó tipped : -");
        kiiras = new JLabel("SZÖVEG AMIT MEG KELL NÉZNED!",JLabel.CENTER);
        sajatutes = new JLabel("Ütéseid : -");
        jatekospont.setSize(FOABLAK_SZEL/10*2,FOABLAK_MAG/30);
        jatekospont.setLocation(FOABLAK_SZEL/10*3,FOABLAK_MAG/20*18);
        jatekospont.setForeground(new Color(nr,ng,nb));
        p.add(jatekospont);
        jatekostipp.setSize(FOABLAK_SZEL/10*2,FOABLAK_MAG/30);
        jatekostipp.setLocation(FOABLAK_SZEL/10*5,FOABLAK_MAG/20*18);
        jatekostipp.setForeground(new Color(nr,ng,nb));
        p.add(jatekostipp);
        sajatutes.setSize(FOABLAK_SZEL/10*2,FOABLAK_MAG/30);
        sajatutes.setLocation(FOABLAK_SZEL/10*7,FOABLAK_MAG/20*18);
        sajatutes.setForeground(new Color(nr,ng,nb));
        p.add(sajatutes);
        kiiras.setSize(FOABLAK_SZEL,FOABLAK_MAG/10);
        kiiras.setLocation(FOABLAK_SZEL/2-kiiras.getWidth()/2,FOABLAK_MAG/2-kiiras.getHeight()/2);
        kiiras.setFont(new Font(kiiras.getFont().getName(),kiiras.getFont().getStyle(),FOABLAK_MAG/10));
        kiiras.setForeground(new Color(nr,ng,nb));
        p.add(kiiras);
        kiiras.setVisible(false);
        jatekosKartyak = new Vector();
        jatekosTipp = 0;
        jatekosKorPont = 0;
        jatekosOsszPont = 0;
        if(egyjatekos) egyjatekos();
        else tobbjatekos();
    }

    void labelfrissit(){
        jatekospont.setText("Pontod : "+jatekosOsszPont);
        sajatutes.setText("Ütéseid : "+jatekosKorPont);
        jatekostipp.setText("Utolsó tipped : "+jatekosTipp);
        try{
        ellenfel1.labelfrissit();
        ellenfel2.labelfrissit();
        ellenfel3.labelfrissit();
        }catch(Exception e){}
        jatekter.p.repaint();
    }
    
    private void egyjatekos() {
        
        ellenfel1 = new JatekosPanel("Bot 1",FOABLAK_SZEL/10*2,FOABLAK_MAG/5);
        ellenfel2 = new JatekosPanel("Bot 2",FOABLAK_SZEL/10*2,FOABLAK_MAG/5);
        ellenfel3 = new JatekosPanel("Bot 3",FOABLAK_SZEL/10*2,FOABLAK_MAG/5);
        
        ellenfel1.setSize(FOABLAK_SZEL/10*2,FOABLAK_MAG/2);
        ellenfel2.setSize(FOABLAK_SZEL/10*2,FOABLAK_MAG/2);
        ellenfel3.setSize(FOABLAK_SZEL/10*2,FOABLAK_MAG/2);
        
        ellenfel1.setLocation(FOABLAK_SZEL/10*1,FOABLAK_MAG/30);
        ellenfel2.setLocation(FOABLAK_SZEL/10*4,FOABLAK_MAG/30);
        ellenfel3.setLocation(FOABLAK_SZEL/10*7,FOABLAK_MAG/30);
        
        p.add(ellenfel1);
        p.add(ellenfel2);
        p.add(ellenfel3);
        
        kartyakFrissit();
    }

    private void tobbjatekos() {
        int lobbyszam = 0;
        if(lobby.nevek.size()>1){
            if(lobby.nevek.get(lobbyszam).s.equals(Main.nev)) lobbyszam++;
            ellenfel1 = new JatekosPanel(lobby.nevek.get(lobbyszam).s,FOABLAK_SZEL/10*2,FOABLAK_MAG/5);
            ellenfel1.setSize(FOABLAK_SZEL/10*2,FOABLAK_MAG/2);
            ellenfel1.setLocation(FOABLAK_SZEL/10*1,FOABLAK_MAG/30);
            p.add(ellenfel1);
            lobbyszam++;
        }
        if(lobby.nevek.size()>2){
            if(lobby.nevek.get(lobbyszam).s.equals(Main.nev)) lobbyszam++;
            ellenfel2 = new JatekosPanel(lobby.nevek.get(lobbyszam).s,FOABLAK_SZEL/10*2,FOABLAK_MAG/5);
            ellenfel2.setSize(FOABLAK_SZEL/10*2,FOABLAK_MAG/2);
            ellenfel2.setLocation(FOABLAK_SZEL/10*4,FOABLAK_MAG/30);
            p.add(ellenfel2);
            lobbyszam++;
        }
        if(lobby.nevek.size()>3){
            if(lobby.nevek.get(lobbyszam).s.equals(Main.nev)) lobbyszam++;
            ellenfel3 = new JatekosPanel(lobby.nevek.get(lobbyszam).s,FOABLAK_SZEL/10*2,FOABLAK_MAG/5);
            ellenfel3.setSize(FOABLAK_SZEL/10*2,FOABLAK_MAG/2);
            ellenfel3.setLocation(FOABLAK_SZEL/10*7,FOABLAK_MAG/30);
            p.add(ellenfel3);
        }
        
        kartyakFrissit();
    }
    
    
    public void addKartya(KartyaLap k){
        jatekosKartyak.add(new KartyaPanel(k, 0, 2));
    }

    void kartyakTakarit(){
        try{
        p.remove(jatekter.jatekoskijeloltkartya);
        ellenfel1.kivalasztottKartya = null;
        ellenfel2.kivalasztottKartya = null;
        ellenfel3.kivalasztottKartya = null;
        }catch(Exception e){}
    }
    
    public void kartyakFrissit(){
        for (int i = 0; i < jatekosKartyak.size(); i++) {
            p.remove(jatekosKartyak.get(i));
        }
        int kezd = Main.FOABLAK_SZEL/2-jatekosKartyak.size()*10;
        for (int i = jatekosKartyak.size()-1; i >= 0; i--) {
            if(i != jatekosKartyak.size()-1){
                jatekosKartyak.get(i).setAllapot(1);
            }else{
                jatekosKartyak.get(i).setAllapot(0);
            }
            jatekosKartyak.get(i).setSize(35*jatekosKartyak.get(i).szorzo, 59*jatekosKartyak.get(i).szorzo);
            jatekosKartyak.get(i).setLocation((kezd+i*20)-jatekosKartyak.get(i).getWidth()/2,Main.FOABLAK_MAG/10*7);
            p.add(jatekosKartyak.get(i));
        }
        p.repaint();
        
        if(egyjatekos){
            ellenfel1.tipp.setText("Tipp: "+ellenfel1.tippertek);
            ellenfel2.tipp.setText("Tipp: "+ellenfel2.tippertek);
            ellenfel3.tipp.setText("Tipp: "+ellenfel3.tippertek);

            ellenfel1.pont.setText("Pont: "+ellenfel1.osszPontErtek);               
            ellenfel2.pont.setText("Pont: "+ellenfel2.osszPontErtek);               
            ellenfel3.pont.setText("Pont: "+ellenfel3.osszPontErtek);  
        }else{
            try{
                
            ellenfel1.tipp.setText("Tipp: "+ellenfel1.tippertek);
            ellenfel1.pont.setText("Pont: "+ellenfel1.osszPontErtek); 
            ellenfel2.tipp.setText("Tipp: "+ellenfel2.tippertek);
            ellenfel2.pont.setText("Pont: "+ellenfel2.osszPontErtek); 
            ellenfel3.tipp.setText("Tipp: "+ellenfel3.tippertek);
            ellenfel3.pont.setText("Pont: "+ellenfel3.osszPontErtek); 
                            
            }catch(Exception e){}
        }
    }
    
    Timer szovegtimer;
    
    void nagyszovegkiir(String s){
        szovegtimer = new Timer(1500, new SzovegTimer());
        szovegtimer.start();
        kiiras.setText(s);
        kiiras.setVisible(true);
    }

     class SzovegTimer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            szovegtimer.stop();
            kiiras.setVisible(false);
        }

    }
    
    public class Listener implements MouseListener{

        @Override public void mouseClicked(MouseEvent me) {
            if(me.getSource() == kilepes){
                if (iranyito != null) {
                    iranyito.leallit = !iranyito.leallit;
                    kilepes.label.setText("Kilepes : " + (iranyito.leallit ? "igen" : "nem"));
                }
                if (gyakorlo != null) {
                    kilepes.label.setText("Kilepes : nem");
                }
            }
        }
        @Override public void mousePressed(MouseEvent me) {}
        @Override public void mouseReleased(MouseEvent me) {}
        @Override public void mouseEntered(MouseEvent me) {}
        @Override public void mouseExited(MouseEvent me) {}
        
    }


    
}
