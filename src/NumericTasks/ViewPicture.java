/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NumericTasks;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;



/**
 *
 * @author Quchi
 */
public class ViewPicture{
    private final Picture RGB;

   public ViewPicture(BufferedImage Obraz) {
       RGB = new Picture(Obraz);
    }

   public ViewPicture(Picture ObrazEdytowalny) {
       this.RGB=ObrazEdytowalny;
    }
   public ViewPicture(String Sciezka) {
      RGB = new Picture(Sciezka);
   }
   
    public void zapiszObraz(String Sciezka){
       RGB.writeImage(Sciezka);
   }
   public int[] pobierzTabliceRGB(){
       return RGB.getPixelArray();
   }
   public int pobierzX(){
        return RGB.getImageWidth();
   }
   public int pobierzY()
   {
       return RGB.getImageHeight();
   }
   
   public void UstawTablicePikseli(int tablica[]){
       RGB.setPixelArray(tablica);
   }
   
   public BufferedImage PobierzObraz(){
       return RGB.Image();
   }



    void zapiszObraz(String absolutePath, int x, int y) {
       BufferedImage Obraz = ZmieńRozmiarObrazu(RGB.Image(),(int)(RGB.Image().getWidth()*x/100.0), (int) (RGB.Image().getHeight()*y/100.0));
       writeImage( absolutePath,Obraz);
    }
    
   public void writeImage(String filePath,BufferedImage image){
        try{
            File f = new File(filePath);
            String fileType = filePath.substring(filePath.lastIndexOf('.')+1);
            ImageIO.write(image, fileType, f);
        }catch(IOException e){
            
        }
    } 
   
   public static BufferedImage ZmieńRozmiarObrazu(BufferedImage img, int newW, int newH) { 
            java.awt.Image tmp = img.getScaledInstance(newW, newH, java.awt.Image.SCALE_SMOOTH);
            BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D g2d = dimg.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();

    return dimg;
 }
   
 
   
   public int[] ModyfikujKoloryWKanaleRGB(double czerwien,double zielen,double niebieski,double kontrast,TypeOfThreshold progowanie,int wartoscprogujaca){
       
       int height = pobierzX();
       int width = pobierzY();
       @SuppressWarnings("MismatchedReadAndWriteOfArray")
       int KanalAlpha [] = RGB.getAlphaTable();
       @SuppressWarnings("MismatchedReadAndWriteOfArray")
       int KolorCzerwony[] = RGB.getRedTable();
       @SuppressWarnings("MismatchedReadAndWriteOfArray")
       int KolorZielony[] = RGB.getGreenTable();
       @SuppressWarnings("MismatchedReadAndWriteOfArray")
       int KolorNiebieski[] = RGB.getBlueTable();
       
       
       @SuppressWarnings("MismatchedReadAndWriteOfArray")
       int WyjsciowyRGB[] = new int[RGB.getImageTotalPixels()];
        
       for(int y = 0; y < height; y++)
       
       {
            for(int x = 0; x < width; x++)
            {
                
             ///Blok Dla koloru czerwonego   
                            //blok kodu odpowiedzialny za zabezpieczenia które mają za zadanie zapobiec przepełnienia liczby poza skale 8 bitow
                            
                                      //obliczenie wartosci pikseli oraz operacje progowania  
                                      if(null != progowanie)  
                                        switch (progowanie) {
                                            case BRAK_PROGOWANIA:
                                                
                                                
                                                                    if((KolorCzerwony[x+width*y]+czerwien)*kontrast>255)
                                                                            {
                                                                               KolorCzerwony[x+width*y]=255;
                                                                            }
                                                                    else if((KolorCzerwony[x+width*y]+czerwien)*kontrast<0)
                                                                            { 
                                                                              KolorCzerwony[x+width*y]=0;
                                                                            }
                                                                    else
                                                                            {
                                                                               KolorCzerwony[x+width*y]=(int) ((KolorCzerwony[x+width*y]+czerwien)*kontrast);
                                                                            }
                                                
                                                
                                                                    if((KolorZielony[x+width*y]+zielen)*kontrast>255)
                                                                            {
                                                                                KolorZielony[x+width*y]=255;
                                                                            }
                                                                    else if((KolorZielony[x+width*y]+zielen)*kontrast<0)
                                                                            {
                                                                                KolorZielony[x+width*y]=0;
                                                                            }
                                                                    else
                                                                            {
                                                                                KolorZielony[x+width*y]=(int) ((KolorZielony[x+width*y]+zielen)*kontrast);
                                                                            } 
                                                                    
                                                                    
                                                                    
                                                                    if((KolorNiebieski[x+width*y]+niebieski)*kontrast>255)
                                                                            {
                                                                               KolorNiebieski[x+width*y]=255;
                                                                            }
                                                                    else if((KolorNiebieski[x+width*y]+niebieski)*kontrast<0)
                                                                            { 
                                                                               KolorNiebieski[x+width*y]=0;
                                                                            }
                                                                    else
                                                                            {
                                                                               KolorNiebieski[x+width*y]=(int) ((KolorNiebieski[x+width*y]+niebieski)*kontrast);
                                                                            }
                                                break;
                                                
                                               
                                               
                                            case PROGOWANIE_ZWYKŁE:
                                              
                                                if((KolorCzerwony[x+width*y]+KolorZielony[x+width*y]+KolorNiebieski[x+width*y])/3 > wartoscprogujaca)
                                                {   
                                                                    if(((255+czerwien)*kontrast)>255)
                                                                            {
                                                                               KolorCzerwony[x+width*y]=255;
                                                                            }
                                                                    else if(((255+czerwien)*kontrast)<0)
                                                                            { 
                                                                              KolorCzerwony[x+width*y]=0;
                                                                            }
                                                                    else
                                                                            {
                                                                               KolorCzerwony[x+width*y]=(int) ((255+czerwien)*kontrast);
                                                                            }
                                                                    
                                                                    
                                                                    
                                                                    
                                                                    if(((255+zielen)*kontrast)>255)
                                                                            {
                                                                                KolorZielony[x+width*y]=255;
                                                                            }
                                                                    else if(((255+zielen)*kontrast)<0)
                                                                            {
                                                                                KolorZielony[x+width*y]=0;
                                                                            }
                                                                    else
                                                                            {
                                                                                KolorZielony[x+width*y]=(int) ((255+zielen)*kontrast);
                                                                            }
                                                                    
                                                                    
                                                                    if(((255+niebieski)*kontrast)>255)
                                                                            {
                                                                               KolorNiebieski[x+width*y]=255;
                                                                            }
                                                                    else if(((255+niebieski)*kontrast)<0)
                                                                            { 
                                                                               KolorNiebieski[x+width*y]=0;
                                                                            }
                                                                    else
                                                                            {
                                                                               KolorNiebieski[x+width*y]=(int) ((255+niebieski)*kontrast);
                                                                            }
                                                                    
                                                    
                                                }
                                                
                                                
                                                
                                                else
                                                {
                                                                    if(((0+czerwien)*kontrast)>255)
                                                                            {
                                                                               KolorCzerwony[x+width*y]=255;
                                                                            }
                                                                    else if(((0+czerwien)*kontrast)<0)
                                                                            { 
                                                                              KolorCzerwony[x+width*y]=0;
                                                                            }
                                                                    else
                                                                            {
                                                                               KolorCzerwony[x+width*y]=(int) ((0+czerwien)*kontrast);
                                                                            }
                                                                    
                                                                    
                                                                    
                                                                    
                                                                    if(((0+zielen)*kontrast)>255)
                                                                            {
                                                                                KolorZielony[x+width*y]=255;
                                                                            }
                                                                    else if(((0+zielen)*kontrast)<0)
                                                                            {
                                                                                KolorZielony[x+width*y]=0;
                                                                            }
                                                                    else
                                                                            {
                                                                                KolorZielony[x+width*y]=(int) ((0+zielen)*kontrast);
                                                                            }
                                                                    
                                                                    
                                                                    if(((0+niebieski)*kontrast)>255)
                                                                            {
                                                                               KolorNiebieski[x+width*y]=255;
                                                                            }
                                                                    else if(((0+niebieski)*kontrast)<0)
                                                                            { 
                                                                               KolorNiebieski[x+width*y]=0;
                                                                            }
                                                                    else
                                                                            {
                                                                               KolorNiebieski[x+width*y]=(int) ((0+niebieski)*kontrast);
                                                                            }
                                                }     
                                                
                                                
                                                break;
                                            case EFEKT_ROZJAŚNIAJĄCY:
                                                if((KolorCzerwony[x+width*y]+KolorZielony[x+width*y]+KolorNiebieski[x+width*y])/3 > wartoscprogujaca)
                                                {
                                                    KolorCzerwony[x+width*y]= 255;
                                                    KolorZielony[x+width*y]=255;
                                                    KolorNiebieski[x+width*y]=255;
                                                }
                                                else
                                                {
                                                                    if(((KolorCzerwony[x+width*y]+czerwien)*kontrast)>255)
                                                                            {
                                                                               KolorCzerwony[x+width*y]=255;
                                                                            }
                                                                    else if(((KolorCzerwony[x+width*y]+czerwien)*kontrast)<0)
                                                                            { 
                                                                              KolorCzerwony[x+width*y]=0;
                                                                            }
                                                                    else
                                                                            {
                                                                               KolorCzerwony[x+width*y]=(int) ((KolorCzerwony[x+width*y]+czerwien)*kontrast);
                                                                            }
                                                                    
                                                                    
                                                                    
                                                                    
                                                                    if(((KolorZielony[x+width*y]+zielen)*kontrast)>255)
                                                                            {
                                                                                KolorZielony[x+width*y]=255;
                                                                            }
                                                                    else if(((KolorZielony[x+width*y]+zielen)*kontrast)<0)
                                                                            {
                                                                                KolorZielony[x+width*y]=0;
                                                                            }
                                                                    else
                                                                            {
                                                                                KolorZielony[x+width*y]=(int) ((KolorZielony[x+width*y]+zielen)*kontrast);
                                                                            }
                                                                    
                                                                    
                                                                    if(((KolorNiebieski[x+width*y]+niebieski)*kontrast)>255)
                                                                            {
                                                                               KolorNiebieski[x+width*y]=255;
                                                                            }
                                                                    else if(((KolorNiebieski[x+width*y]+niebieski)*kontrast)<0)
                                                                            { 
                                                                               KolorNiebieski[x+width*y]=0;
                                                                            }
                                                                    else
                                                                            {
                                                                               KolorNiebieski[x+width*y]=(int) ((KolorNiebieski[x+width*y]+niebieski)*kontrast);
                                                                            }
                                                                    
                                                }    
                                                break;
                                            case EFEKT_PRZYCIEMNAJĄCY:
                                                if((KolorCzerwony[x+width*y]+KolorZielony[x+width*y]+KolorNiebieski[x+width*y])/3 > wartoscprogujaca)
                                                {
                                                    if(((KolorCzerwony[x+width*y]+czerwien)*kontrast)>255)
                                                                            {
                                                                               KolorCzerwony[x+width*y]=255;
                                                                            }
                                                                    else if(((KolorCzerwony[x+width*y]+czerwien)*kontrast)<0)
                                                                            { 
                                                                              KolorCzerwony[x+width*y]=0;
                                                                            }
                                                                    else
                                                                            {
                                                                               KolorCzerwony[x+width*y]=(int) ((KolorCzerwony[x+width*y]+czerwien)*kontrast);
                                                                            }
                                                                    
                                                                    
                                                                    
                                                                    
                                                                    if(((KolorZielony[x+width*y]+zielen)*kontrast)>255)
                                                                            {
                                                                                KolorZielony[x+width*y]=255;
                                                                            }
                                                                    else if(((KolorZielony[x+width*y]+zielen)*kontrast)<0)
                                                                            {
                                                                                KolorZielony[x+width*y]=0;
                                                                            }
                                                                    else
                                                                            {
                                                                                KolorZielony[x+width*y]=(int) ((KolorZielony[x+width*y]+zielen)*kontrast);
                                                                            }
                                                                    
                                                                    
                                                                    if(((KolorNiebieski[x+width*y]+niebieski)*kontrast)>255)
                                                                            {
                                                                               KolorNiebieski[x+width*y]=255;
                                                                            }
                                                                    else if(((KolorNiebieski[x+width*y]+niebieski)*kontrast)<0)
                                                                            { 
                                                                               KolorNiebieski[x+width*y]=0;
                                                                            }
                                                                    else
                                                                            {
                                                                               KolorNiebieski[x+width*y]=(int) ((KolorNiebieski[x+width*y]+niebieski)*kontrast);
                                                                            }
                                                                    
                                                                    
                                                                    
                                                }
                                                else
                                                {
                                                    KolorCzerwony[x+width*y]= 0;
                                                    KolorZielony[x+width*y]= 0;
                                                    KolorNiebieski[x+width*y]= 0;
                                                }     break;
                                            default:
                                                break;  
                                        }
                  
                             
                WyjsciowyRGB[x+width*y]= (KanalAlpha [x+width*y]<<24) | (KolorCzerwony[x+width*y]<<16) | (KolorZielony[x+width*y]<<8) | KolorNiebieski[x+width*y];
            }
            
            
            
         }
       
       
       
          return WyjsciowyRGB;
        } 

   public void UstawObraz(BufferedImage Obraz){
       RGB.setImage(Obraz);
   }
}