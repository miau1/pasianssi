/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.pasianssi.gui;

import static java.awt.Image.SCALE_SMOOTH;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author mikko
 */
public class ImageHandler {
    
    private ImageIcon[] kuvat;
    
    public ImageHandler() throws IOException{
        kuvat = new ImageIcon[106];
        BufferedImage myPicture1 = ImageIO.read(new File("src/main/resources/images/kortit/Backs/Card-Back-01.png"));
        kuvat[0] = new ImageIcon(myPicture1.getScaledInstance(70, 95, SCALE_SMOOTH));
        for (int i = 1; i < 53; i++) {
            String osoite = "src/main/resources/images/kortit/Cards/Classic/";
            if (i < 14) {
                osoite = osoite + "h" + rakennaLuku(i);
            } else if (i > 13 && i < 27) {
                osoite = osoite + "s" + rakennaLuku(i - 13);
            } else if (i > 26 && i < 40) {
                osoite = osoite + "d" + rakennaLuku(i - 26);
            } else {
                osoite = osoite + "c" + rakennaLuku(i - 39);
            }
            osoite = osoite + ".png";
            myPicture1 = ImageIO.read(new File(osoite));
            kuvat[i] = new ImageIcon(myPicture1.getScaledInstance(70, 95, SCALE_SMOOTH));
            kuvat[i + 52] = new ImageIcon(myPicture1.getSubimage(0, 0, 768, 250).getScaledInstance(70, 25, SCALE_SMOOTH));
        }
        myPicture1 = ImageIO.read(new File("src/main/resources/images/kortit/Backs/pohja.png"));
        kuvat[105] = new ImageIcon(myPicture1.getScaledInstance(70, 95, SCALE_SMOOTH));
    }
    
    public ImageIcon kuva(int sijainti){
        return this.kuvat[sijainti];
    }
    
    private String rakennaLuku(int luku) {
        String ret = "";
        if (luku < 10) {
            ret = ret + "0";
        }
        ret = ret + Integer.toString(luku);
        return ret;
    }
}
