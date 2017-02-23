package Jatek;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static Jatek.Main.*;

public class KijelzoLobby extends Kijelzo {

    Gomb kilepes;
    
    public KijelzoLobby(String cim, int w, int h, boolean kilepos) {
        super(cim, w, h, kilepos);
        
    }
    
    
    public class Listener implements MouseListener{

        @Override public void mouseClicked(MouseEvent me) {}
        @Override public void mousePressed(MouseEvent me) {}
        @Override public void mouseReleased(MouseEvent me) {}
        @Override public void mouseEntered(MouseEvent me) {}
        @Override public void mouseExited(MouseEvent me) {}
        
    }


    
}
