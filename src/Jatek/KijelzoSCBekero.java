package Jatek;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static Jatek.Main.*;
import java.util.Vector;

public class KijelzoSCBekero extends Kijelzo {

    Gomb ok;
    boolean server;
    JTextField mezo;
    KijelzoHiba hiba;

    public KijelzoSCBekero(String cim, int w, int h, boolean kilepos, boolean server) {
        super(cim, w, h, kilepos);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.server = server;
        ok = new Gomb("Rendben", getSzel() / 2, getMag() / 5);
        ok.setLocation(getSzel() / 2 - ok.getWidth() / 2, getMag() / 2);
        p.add(ok);
        mezo = new JTextField();
        mezo.setText(server ? "7777" : "localhost:7777");
        mezo.setForeground(new Color(nr - szinkul, ng - szinkul, nb - szinkul));
        mezo.setSize(getSzel() / 4 * 3, getMag() / 5);
        mezo.setLocation(getSzel() / 2 - mezo.getWidth() / 2, getMag() / 5);
        p.add(mezo);
        ok.addMouseListener(new Listener());
        frame.setVisible(true);
    }

    public class Listener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent me) {
            if (hiba != null) {
                hiba.frame.dispose();
                hiba = null;
            }
            if (me.getSource() == ok) {
                if (server) {
                    try {
                        int i = Integer.parseInt(mezo.getText());
                        if (i < 1024 || i > 65535) {
                            hiba = new KijelzoHiba("Kérlek a port szám 1024 és 65535 között legyen.", FOABLAK_SZEL / 3, FOABLAK_MAG / 4);
                        } else {
                            if(sck.S == null){
                                sck.ujServer(i);
                                lobby.nevek = new Vector();
                                lobby.nevek.add(new StringBoolean(Main.nev,true));
                                lobby.nevekfrissit();
                                lobby.bekero.hiba = null;
                                lobby.bekero.frame.dispose();
                                lobby.bekero = null;
                            }
                        }
                    } catch (Exception e) {
                        hiba = new KijelzoHiba("A mezőben nem szám szerepel.", FOABLAK_SZEL / 3, FOABLAK_MAG / 4);
                    }
                } else {
                    try {
                        String[] t = mezo.getText().split(":");
                        if (!t[0].equals("localhost")) {
                            String[] ip = t[0].split(".");
                            for (int i = 0; i < ip.length; i++) {
                                int x = Integer.parseInt(ip[i]);
                            }
                            int y;
                            if (ip.length != 4) {
                                y = 0 / 0;
                            }
                        }
                        int p = Integer.parseInt(t[1]);
                        if (p < 1024 || p > 65535) {
                            hiba = new KijelzoHiba("Kérlek a port szám 1024 és 65535 között legyen.", FOABLAK_SZEL / 3, FOABLAK_MAG / 4);
                        } else {
                            if(sck.C == null){
                                sck.ujClient(mezo.getText());
                                sck.C.kuld("kapcs@"+Main.nev);
                                hiba = null;
                                lobby.bekero.frame.dispose();
                                lobby.bekero = null;
                            }
                        }
                    } catch (Exception e) {
                        hiba = new KijelzoHiba("A mezőben nem megfelelő ip cím szerepel. A megfelelő forma pl: 192.168.0.0:7777", FOABLAK_SZEL / 4 * 3, FOABLAK_MAG / 4);
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent me) {
        }

        @Override
        public void mouseReleased(MouseEvent me) {
        }

        @Override
        public void mouseEntered(MouseEvent me) {
        }

        @Override
        public void mouseExited(MouseEvent me) {
        }

    }

}
