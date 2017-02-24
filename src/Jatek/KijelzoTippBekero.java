package Jatek;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static Jatek.Main.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class KijelzoTippBekero extends Kijelzo {

    Gomb ok;
    JSpinner mezo;
    KijelzoHiba hiba;
    boolean egyszemelyes;
    
    public KijelzoTippBekero(String cim, int w, int h, boolean kilepos, boolean e) {
        super(cim, w, h, kilepos);
        egyszemelyes = e;
        ok = new Gomb("Rendben",getSzel()/2,getMag()/5);
        ok.setLocation(getSzel()/2-ok.getWidth()/2, getMag()/2);
        p.add(ok);
        mezo = new JSpinner();
        mezo.setForeground(new Color(nr-szinkul,ng-szinkul,nb-szinkul));
        mezo.setSize(getSzel()/4*3,getMag()/5);
        mezo.setLocation(getSzel()/2-mezo.getWidth()/2, getMag()/5);
        SpinnerNumberModel model = new SpinnerNumberModel();
        model.setValue(0);
        model.setMaximum(15);
        model.setMinimum(0);
        mezo.setModel(model);
        JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor)mezo.getEditor();
        spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
        p.add(mezo);
        ok.addMouseListener(new Listener());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        
    }
    
    
    public class Listener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent me) {
            if (me.getSource() == ok) {
                int t;
                if (hiba != null) {
                    hiba.frame.dispose();
                }
                if (mezo.getValue() == null) {
                    hiba = new KijelzoHiba("A mezőben nem szerepel tipp!", FOABLAK_SZEL / 3, FOABLAK_MAG / 4);
                } else {
                    try {

                        mezo.commitEdit();
                        jatekter.jatekosTipp = (int) mezo.getValue();
                        jatekter.jatekostipp.setText("Utolsó tipped : " + jatekter.jatekosTipp);
                        frame.dispose();
                        if (iranyito == null) {
                            gyakorlo.lepes();
                            jatekter.jatekosTipp = 1;
                            jatekter.jatekostipp.setText("Utolsó tipped : 1");
                        } else if (egyszemelyes) {
                            iranyito.botKartyaRakas();
                        } else {
                            //tobbszemelyes
                        }
                    } catch (Exception e) {
                        hiba = new KijelzoHiba("A mezőben nem megfelelő szám szerepel!.", FOABLAK_SZEL / 3, FOABLAK_MAG / 4);
                    }
                }
            }
        }
        @Override public void mousePressed(MouseEvent me) {}
        @Override public void mouseReleased(MouseEvent me) {}
        @Override public void mouseEntered(MouseEvent me) {}
        @Override public void mouseExited(MouseEvent me) {}
        
    }


    
}
