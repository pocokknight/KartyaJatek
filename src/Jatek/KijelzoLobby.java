package Jatek;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static Jatek.Main.*;
import java.util.Vector;

public class KijelzoLobby extends Kijelzo {

    Gomb kilepes,server,client;
    JTextArea chat,jatekosok;
    JScrollPane chatscroll,jatekosokscroll;
    JTextField chatbe;
    KijelzoSCBekero bekero;
    Vector<StringBoolean> nevek;

    public KijelzoLobby(String cim, int w, int h, boolean kilepos) {
        super(cim, w, h, kilepos);
        
        chat = new JTextArea("Hozz létre egy szervert vagy csatlakozz egyhez.");
        chatscroll = new JScrollPane(chat);
        chatscroll.setSize(FOABLAK_SZEL/10*6, FOABLAK_MAG/10*6);
        chatscroll.setLocation(FOABLAK_SZEL/10*1, FOABLAK_MAG/10*1);
        chat.setLineWrap(true);
        chat.setWrapStyleWord(true);
        chat.setEditable(false);
        chat.setFocusable(false);
        chat.setForeground(new Color(nr-100,ng-100,nb-100));
        p.add(chatscroll);
        
        nevek = new Vector();
        jatekosok = new JTextArea(Main.nev);
        jatekosokscroll = new JScrollPane(jatekosok);
        jatekosokscroll.setSize(FOABLAK_SZEL/20*3, FOABLAK_MAG/10*6);
        jatekosokscroll.setLocation(FOABLAK_SZEL/20*15, FOABLAK_MAG/10*1);
        jatekosok.setLineWrap(true);
        jatekosok.setWrapStyleWord(true);
        jatekosok.setEditable(false);
        jatekosok.setFocusable(false);
        jatekosok.setForeground(new Color(nr-100,ng-100,nb-100));
        p.add(jatekosokscroll);
        
        chatbe = new JTextField();
        chatbe.setSize(FOABLAK_SZEL/10*8, FOABLAK_MAG/20);
        chatbe.setLocation(FOABLAK_SZEL/10*1, FOABLAK_MAG/20*15);
        chatbe.setForeground(new Color(nr-100,ng-100,nb-100));
        chatbe.addKeyListener(new KeyL());
        p.add(chatbe);
        
        server = new Gomb("Szerver hosztolása",FOABLAK_SZEL/7, FOABLAK_MAG/20);
        server.setLocation(FOABLAK_SZEL/7*1, FOABLAK_MAG/20*17);
        p.add(server);
        server.addMouseListener(new Listener());
        
        client = new Gomb("Csatlakozás szerverhez",FOABLAK_SZEL/7, FOABLAK_MAG/20);
        client.setLocation(FOABLAK_SZEL/7*3, FOABLAK_MAG/20*17);
        p.add(client);
        client.addMouseListener(new Listener());
        
        kilepes = new Gomb("Vissza",FOABLAK_SZEL/7, FOABLAK_MAG/20);
        kilepes.setLocation(FOABLAK_SZEL/7*5, FOABLAK_MAG/20*17);
        p.add(kilepes);
        kilepes.addMouseListener(new Listener());
    }
    
    void nevekfrissit(){
        String s = "";
        for (int i = 0; i < nevek.size(); i++) {
            s+=nevek.get(i).getString()+"\n";
        }
        jatekosok.setText(s);
    }

    class KeyL implements KeyListener {

        @Override public void keyTyped(KeyEvent ke) {
            if(ke.getKeyChar() == '\n'){
                try{
                sck.uzenet(chatbe.getText());
                chatbe.setText("");
                }catch(Exception e){}
            }
        }
        @Override public void keyPressed(KeyEvent ke) {}
        @Override public void keyReleased(KeyEvent ke) {}

    }
    
    public class Listener implements MouseListener{

        @Override public void mouseClicked(MouseEvent me) {
            if(me.getSource() == kilepes){
                lobby.frame.dispose();
                lobby = null;
                sck.leallitas();
                valaszto = new KijelzoValaszto("Kártyajáték - készítette: Horváth Patrik", FOABLAK_SZEL, FOABLAK_MAG, true);
                valaszto.setVisible(true);
            }else if(me.getSource() == server){
                sck = new ServerClientKezelo();
                bekero = new KijelzoSCBekero("Kérlek add meg a szerver portját:", FOABLAK_SZEL/2, FOABLAK_MAG/4, false, true);
            }else if(me.getSource() == client){
                sck = new ServerClientKezelo();
                bekero = new KijelzoSCBekero("Kérlek add meg a szerver IP címét és portját : -al elválasztva", FOABLAK_SZEL/2, FOABLAK_MAG/4, false, false);
            }
        }
        @Override public void mousePressed(MouseEvent me) {}
        @Override public void mouseReleased(MouseEvent me) {}
        @Override public void mouseEntered(MouseEvent me) {}
        @Override public void mouseExited(MouseEvent me) {}
        
    }


    
}
