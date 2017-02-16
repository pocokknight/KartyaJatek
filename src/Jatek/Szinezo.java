package Jatek;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Szinezo {

    static BufferedImage szinez(String szin,BufferedImage kep){
        String[] s = szin.split(" ");
        int a;
        Color c;
        BufferedImage ujkep = new BufferedImage(kep.getWidth(),kep.getHeight(),BufferedImage.TYPE_INT_ARGB);
        
        for (int i = 0; i < kep.getWidth(); i++) {
            for (int j = 0; j < kep.getHeight(); j++) {
                a = kep.getRGB(i, j);
                c = new Color(Integer.parseInt(s[0]),
                        Integer.parseInt(s[1]),
                        Integer.parseInt(s[2]),
                        new Color(a,true).getAlpha());
                ujkep.setRGB(i, j, c.getRGB());
            }
        }
        return ujkep;
    }
    
}
