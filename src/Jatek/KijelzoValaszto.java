package Jatek;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static Jatek.Main.*;

public class KijelzoValaszto extends Kijelzo {

    Gomb egyjatekos,tobbjatekos,gyakorlas,kilepes;
    
    public KijelzoValaszto(String cim, int w, int h, boolean kilepos) {
        super(cim, w, h, kilepos);
        
        egyjatekos = new Gomb("Egyjátékos mód",getSzel()/2,getMag()/11);
        egyjatekos.setLocation(getSzel()/2-egyjatekos.getWidth()/2, getMag()/10*4);
        p.add(egyjatekos);
        egyjatekos.addMouseListener(new Listener());
        
        gyakorlas = new Gomb("Gyakorló mód",getSzel()/2,getMag()/11);
        gyakorlas.setLocation(getSzel()/2-gyakorlas.getWidth()/2, getMag()/10*5);
        p.add(gyakorlas);
        gyakorlas.addMouseListener(new Listener());
        
        tobbjatekos = new Gomb("Többjátékos mód",getSzel()/2,getMag()/11);
        tobbjatekos.setLocation(getSzel()/2-tobbjatekos.getWidth()/2, getMag()/10*6);
        p.add(tobbjatekos);
        tobbjatekos.addMouseListener(new Listener());
        
        kilepes = new Gomb("Kilépés",getSzel()/2,getMag()/11);
        kilepes.setLocation(getSzel()/2-kilepes.getWidth()/2, getMag()/10*8);
        p.add(kilepes);
        kilepes.addMouseListener(new Listener());
        
    }
    
    
    public class Listener implements MouseListener{

        @Override public void mouseClicked(MouseEvent me) {
            if(me.getSource() == egyjatekos){
                jatekter = new KijelzoJatekter("Riki-tiki by: Pocok", FOABLAK_SZEL, FOABLAK_MAG, true);
                jatekter.setVisible(true);
                iranyito = new JatekIranyito(true);
                gyakorlo = null;
                iranyito.oszt();
                valaszto.frame.dispose();
            }else if(me.getSource() == tobbjatekos){
                lobby = new KijelzoLobby("Riki-tiki by: Pocok - Többjátékos lobby", FOABLAK_SZEL, FOABLAK_MAG, true);
                lobby.setVisible(true);
                jatekter = null;
                iranyito = null;
                valaszto.frame.dispose();   
            }else if(me.getSource() == gyakorlas){
                jatekter = new KijelzoJatekter("Riki-tiki by: Pocok - Gyakorló mód", FOABLAK_SZEL, FOABLAK_MAG, true);
                jatekter.setVisible(true);
                gyakorlo = new KijelzoGyakorlo("Utasítás", FOABLAK_SZEL/6, FOABLAK_MAG);
                gyakorlo.setVisible(true);
                iranyito = null;
                valaszto.frame.dispose();
            }else if(me.getSource() == kilepes){
                System.exit(0);
            }
        }
        @Override public void mousePressed(MouseEvent me) {}
        @Override public void mouseReleased(MouseEvent me) {}
        @Override public void mouseEntered(MouseEvent me) {}
        @Override public void mouseExited(MouseEvent me) {}
        
    }


    
}
