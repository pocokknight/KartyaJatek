package Jatek;

import static Jatek.Main.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class AlServer extends Thread implements Comparable{

    public static int nevszamlalo = 0;
    public int id;
    private Socket s;
    private DataOutputStream out;
    private DataInputStream in; 
    public boolean fut = false;
    String servernev;
    boolean leakarallni;
    
    AlServer(Socket S,int ID){
        leakarallni = false;
        try{
            s = S;
            id = ID;
            out = new DataOutputStream(s.getOutputStream());
            in = new DataInputStream(s.getInputStream());
            fut = true;
            start();
        }catch(Exception e){System.out.println("alserver 1");}
    }
    
    public boolean az(String nev){
        return nev.equals(nev);
    }
    
    public void kuld(String sz){
        try{
            out.writeUTF(sz);
        }catch(Exception e){System.out.println("alserver 2");}
    }
    
    @Override
    public void run(){
        while(fut){
            try{
                String uz = in.readUTF();
                if(uz.startsWith("kapcs")){
                    String[] t = uz.split("@");
                    servernev = t[1];
                    if(servernev.equals(Main.nev)){
                        servernev += "("+nevszamlalo+")";
                        nevszamlalo++;
                    }
                    for (int i = 0; i < lobby.nevek.size(); i++) {
                        if(servernev.equals(lobby.nevek.get(i))){
                            servernev += "("+nevszamlalo+")";
                            nevszamlalo++;
                        }
                    }
                    lobby.nevek.add(new StringBoolean(servernev,false));
                    kuld("ujnev@"+servernev);
                    lobby.nevekfrissit();
                    sck.nevekfrissit();
                }else{
                    sck.ServernekUzenet(uz);
                }
            }catch(Exception e){
                vege = new KijelzoVege("A játék véget ért!", FOABLAK_SZEL, FOABLAK_MAG, true, false);
                vege.setVisible(true);
                if(tbk != null){
                    tbk.frame.dispose();
                    tbk = null;
                }
                sck.uzenetMindenkinek("jatekvege");
                sck.leallitas();
                sck = null;
            }
        }
    }
    
    public void leall(){
        try{
            fut = false;
            in.close();
            out.close();
            s.close();
        }catch(Exception e){System.out.println("alserver 4");}
    }

    @Override
    public int compareTo(Object t) {
        AlServer masik = (AlServer)t;
        return servernev.compareTo(masik.servernev);
    }
}
