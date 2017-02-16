package Jatek;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static Jatek.Main.*;

public class Gomb extends JPanel{
    
    boolean aktiv = false;
    boolean lenyomott = false;
    JLabel label;
    
    Gomb(String felirat,int w,int h){
        setSize(w, h);
        label = new JLabel(felirat,JLabel.CENTER);
        label.setFont(new Font(Font.SERIF,label.getFont().getStyle(),h/2));
        add(label);
        addMouseListener(new Listener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        
        if(lenyomott) label.setForeground(new Color(lr-szinkul,lg-szinkul,lb-szinkul));
        else if(aktiv) label.setForeground(new Color(or-szinkul,og-szinkul,ob-szinkul));
        else label.setForeground(new Color(nr-szinkul,ng-szinkul,nb-szinkul));
        
        if(lenyomott) g.setColor(new Color(lr-szinkul,lg-szinkul,lb-szinkul));
        else if(aktiv) g.setColor(new Color(or-szinkul,og-szinkul,ob-szinkul));
        else g.setColor(new Color(nr-szinkul,ng-szinkul,nb-szinkul));
        g.fillRect(0, 0, getWidth(), getHeight());
        
        if(lenyomott) g.setColor(new Color(lr,lg,lb));
        else if(aktiv) g.setColor(new Color(or,og,ob));
        else g.setColor(new Color(nr,ng,nb));
        g.fillRect(getHeight()/10, getHeight()/10, getWidth()-getHeight()/5, getHeight()-getHeight()/5);
        
    }

    void aktival(boolean b) {
        aktiv = b;
        repaint();
    }
    
    
    void lenyom(boolean b) {
        lenyomott = b;
        repaint();
    }
    
    public class Listener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent me) {}

        @Override
        public void mousePressed(MouseEvent me) {
            lenyom(true);
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            lenyom(false);
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            aktival(true);
        }

        @Override
        public void mouseExited(MouseEvent me) {
            aktival(false);
        }
        
    }

}
