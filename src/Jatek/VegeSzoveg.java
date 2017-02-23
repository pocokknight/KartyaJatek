package Jatek;
public class VegeSzoveg implements Comparable{
    
    String nev;
    int pont;
    
    VegeSzoveg(String nev,int pont){
        this.nev = nev;
        this.pont = pont;
    }

    @Override
    public int compareTo(Object o) {
        VegeSzoveg masik = (VegeSzoveg)o;
        return masik.pont-this.pont;
    }
}
