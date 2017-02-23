package Jatek;

import java.awt.event.*;
import static Jatek.Main.*;
import java.awt.*;
import java.util.*;

public class KijelzoVege extends Kijelzo {

    Gomb kilepes;
    Label elso,masodik,harmadik,negyedik;
    
    public KijelzoVege(String cim, int w, int h, boolean kilepos, boolean egy) {
        super(cim, w, h, kilepos);
        
        if(egy){
            Vector<VegeSzoveg> pontok = new Vector();
            pontok.add(new VegeSzoveg("Játékos",jatekter.jatekosOsszPont));
            pontok.add(new VegeSzoveg("Bot 1",jatekter.ellenfel1.osszPontErtek));
            pontok.add(new VegeSzoveg("Bot 2",jatekter.ellenfel2.osszPontErtek));
            pontok.add(new VegeSzoveg("Bot 3",jatekter.ellenfel3.osszPontErtek));
            
            Collections.sort(pontok);
            
            
        }else{
            
        }
        
        kilepes = new Gomb("Kilépés",getSzel()/2,getMag()/13);
        kilepes.setLocation(getSzel()/2-kilepes.getWidth()/2, getMag()/10*9);
        p.add(kilepes);
        kilepes.addMouseListener(new Listener());
        
    }
    
    
    public class Listener implements MouseListener{

        @Override public void mouseClicked(MouseEvent me) {
            if(me.getSource() == kilepes){
                System.exit(0);
            }
        }
        @Override public void mousePressed(MouseEvent me) {}
        @Override public void mouseReleased(MouseEvent me) {}
        @Override public void mouseEntered(MouseEvent me) {}
        @Override public void mouseExited(MouseEvent me) {}
        
    }


    
}
