package Jatek;

import static Jatek.Main.lobby;

class ServerClientKezelo {

    static Runnable futtathato;
    static Thread szal;
    Server S = null;
    Client C = null;
    int tippek = 0;

    void ujServer(int i) {
        serverki();
        clientki();
        S = new Server(i);
        futtathato = S;
        szal = new Thread(futtathato);
        szal.start();
    }

    void ujClient(String s) {
        serverki();
        clientki();
        String[] t = s.split(":");
        C = new Client(t[0], Integer.parseInt(t[1]));
        futtathato = C;
        szal = new Thread(futtathato);
        szal.start();
    }

    public void leallitas() {
        szal.stop();
        serverki();
        clientki();
    }
    
    private void serverki() {
        if (S != null) {
            S.leall();
            S = null;
        }
    }

    private void clientki() {
        if (C != null) {
            C.leall();
            C = null;
        }
    }

    void ServernekUzenet(String s){
        String[] t = s.split("@");
        switch(t[0]){
            case "uzenet":
                serverKapottuzenet(t[1]);
                break;
        }
    }
    
    void ClientnekUzenet(String s){
        String[] t = s.split("@");
        switch(t[0]){
            case "lobbynevek":
                if(lobby != null){
                    lobby.nevek.clear();
                    for (int i = 1; i < t.length; i++) {
                        String[] tomb = t[i].split("#");
                        lobby.nevek.add(new StringBoolean(tomb[0],tomb[1].equals("true")));
                    }
                }
                lobby.nevekfrissit();
                break;
            case "ujnev":
                Main.nev = t[1];
                break;
            case "uzenet":
                lobby.chat.setText(lobby.chat.getText()+"\n"+t[1]);
                break;
        }
    }

    void nevekfrissit() {
        String s = "lobbynevek";
        if(S != null){
            for (int i = 0; i < lobby.nevek.size(); i++) {
                s+="@"+lobby.nevek.get(i).s+"#"+lobby.nevek.get(i).b;
            }
            for (int i = 0; i < S.alserverek.size(); i++) {
                S.alserverek.get(i).kuld(s);
            }
        }
    }

    void serverKapottuzenet(String s){
        for (int i = 0; i < S.alserverek.size(); i++) {
            S.alserverek.get(i).kuld("uzenet@"+s);
        }
        lobby.chat.setText(lobby.chat.getText()+"\n"+s);
    }
    
    void uzenet(String s) {
        if(S != null){
            serverKapottuzenet(Main.nev+" : "+s);
        }else if(C != null){
            C.kuld("uzenet@"+Main.nev+" : "+s);
        }
    }
}
