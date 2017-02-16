package Kepek;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Kep{

    public static BufferedImage getKep(Class<?> osztaly,String nev){
        BufferedImage img = null;
        try{
            img = ImageIO.read(osztaly.getResource(nev));
        }catch(Exception e){}
        return img;
    }
    
}
