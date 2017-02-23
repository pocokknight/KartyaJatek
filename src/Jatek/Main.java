package Jatek;
public class Main {
    
    public static int lr = 225,lg = 255,lb = 175;
    public static int or = 175,og = 225,ob = 125;
    public static int nr = 150,ng = 200,nb = 100;
    public static int szinkul = 100;
    
    public static String nev;
    
    public static final int FOABLAK_SZEL = 1200;
    public static final int FOABLAK_MAG = 600;
    
    public static KijelzoNevBekero nbk;
    public static KijelzoValaszto valaszto;
    public static KijelzoJatekter jatekter;
    public static KijelzoTippBekero tbk;
    public static JatekIranyito iranyito;
    public static KijelzoLobby lobby;
    public static KijelzoVege vege;
    
    Main(){
        //new KijelzoTeszt(1000, 600);
        nbk = new KijelzoNevBekero("Kérem adja meg a játékos nevét!", FOABLAK_SZEL/3, FOABLAK_MAG/4, true);
        nbk.setVisible(true);
    }
    
    public static void main(String[] args) {
        new Main();
    }
    
    public static int vel(int a, int b){ return (int)(Math.random()*(b-a+1)+a);}

    /*
    public class Listener implements MouseListener{

        @Override public void mouseClicked(MouseEvent me) {}
        @Override public void mousePressed(MouseEvent me) {}
        @Override public void mouseReleased(MouseEvent me) {}
        @Override public void mouseEntered(MouseEvent me) {}
        @Override public void mouseExited(MouseEvent me) {}
        
    }
    */
}
