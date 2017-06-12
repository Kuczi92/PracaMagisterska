/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NewWidnows;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import static java.awt.Image.SCALE_SMOOTH;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Quchi
 */
public class Display extends JPanel {
    BufferedImage Image;

    Graphics2D g2d;
    
    Display(BufferedImage Image){
        super();
        this.Image = Image;
      
    }
    Display() {
     super(); //
     Image = new BufferedImage(700,422,BufferedImage.TYPE_3BYTE_BGR);
    }
    Display(String Path){
        try {
               Image = ImageIO.read(new File(Path));
            } catch (IOException e) {
                e.getMessage();
            }
    }
 
    
    
    public void SetNewPicture(BufferedImage Obraz){
        this.Image = Obraz;
    }
    public BufferedImage GetPicture(){
        return Image;
    }
    
    @Override
    public void paintComponent(Graphics g) {
	        g2d = (Graphics2D) g;
		g2d.drawImage(Image, 0, 0, this);
             
                
    }
    
    
    private static BufferedImage resize(BufferedImage img, int newW, int newH) { 
    Image tmp = img.getScaledInstance(newW, newH, SCALE_SMOOTH);
    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = dimg.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();

    return dimg;
   }
}
