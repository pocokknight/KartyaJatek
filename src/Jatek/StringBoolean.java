package Jatek;
public class StringBoolean {

    String s;
    boolean b;
    
    StringBoolean(String st,boolean bo){
        s = st;
        b = bo;
    }
    
    public String getString(){
        return s +(b ? " [HOST]" : "");
    }
}
