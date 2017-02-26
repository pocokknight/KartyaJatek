package Jatek;

import static Jatek.Main.lobby;
import static Jatek.Main.sck;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client implements Runnable{

    Socket s;
    DataInputStream in;
    DataOutputStream out;
    int id;
    String szoveg = "";
    boolean fut = true;
    
    Client(String ip,int port){
        try{
            s = new Socket(ip,port);
            if(s.isConnected()){
                open();
            }
            lobby.server.label.setText("Szerver hosztolása");
        }catch(Exception e){System.out.println("client 1");}
    }
    
    @Override
    public void run() {
        while(fut){
            try{
                String uz = in.readUTF();
                szoveg = uz;
                sck.ClientnekUzenet(uz);
            }catch(Exception e){System.out.println("client 2");}
        }
    }

    private void open() {
        try{
            out = new DataOutputStream(s.getOutputStream());
            in = new DataInputStream(s.getInputStream());
        }catch(Exception e){System.out.println("client 3");}
    }

    public void kuld(String sz){
        try{
            //out = new DataOutputStream(s.getOutputStream());
            out.writeUTF(sz);
        }catch(Exception e){System.out.println("client 4");}
    }
    
    public void leall(){
        try{
            fut = false;
            in.close();
            out.close();
            s.close();
        }catch(Exception e){System.out.println("client 5");}
    }
    
}
