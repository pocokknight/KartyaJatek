package Jatek;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


public class Server implements Runnable{

    ServerSocket ss;
    Socket s;
    String szoveg = "";
    Vector<AlServer> alserverek= new Vector();
    int id = 0;
    boolean fut = true;
    
    Server(int port){
        try{
            ss = new ServerSocket(port);
        }catch(Exception e){System.out.println("server 1");}
    }
    
    @Override
    public void run() {
        while(fut){
            try{
                s = ss.accept();
            }catch(Exception e){System.out.println("server 2");}

            for (int i = 0; i < alserverek.size(); i++) {
                if (!alserverek.get(i).fut) {
                    alserverek.remove(i);
                }
            }

            try{
                if(s.isConnected()){
                    id++;
                    alserverek.add(new AlServer(s,id));
                    s=null;
                }
            }catch(Exception e){System.out.println("server 3");}
        }
    }
    
    public void kidob(String s){
        for (int i = 0; i < alserverek.size(); i++) {
            if(alserverek.get(i).servernev.equals(s)){
                alserverek.get(i).kuld("kick");
                alserverek.remove(i);
            }
        }
    }
    
    public int getIndex(String nev){
        for (int i = 0; i < alserverek.size(); i++) {
            if(alserverek.get(i).servernev.equals(nev)) return i;
        }
        return -1;
    }
    
    public void kuld(String nev,String uz){
        try{
            alserverek.get(getIndex(nev)).kuld(uz);
        }catch(Exception e){System.out.println("server 4");}
    }
    
    public void leall() {
        try{
            fut = false;
            for (int i = 0; i < alserverek.size(); i++) {
                alserverek.get(i).kuld("stop");
                alserverek.get(i).leall();
            }
        }catch(Exception e){System.out.println("server 5");}
    }
}
