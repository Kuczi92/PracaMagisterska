/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praca.magisterska;

import NumericTasks.ArrayUtils;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;

/**
 *
 * @author Quchi
 */
abstract class Detector {
    
    
   final String SciezkaPliku;
   BufferedImage  ObrazWejsciowy;
   ArrayList<ArrayList<Integer>> ListaPunktów = new ArrayList();
   
   
   Detector(String SciezkaPliku){
       this.SciezkaPliku =  SciezkaPliku;
        try 
      {
          ObrazWejsciowy = ImageIO.read(new File(SciezkaPliku));
      } 
      catch (IOException e) 
      {   
   
        System.out.println("Bład podczas ładowania pliku");
      }
   }
   
   public static Mat bufferedImageToMat(BufferedImage bi) {
    Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
    byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
    mat.put(0, 0, data);
    return mat;
   }
      
public static BufferedImage MatDoBufferedImage(Mat matrix)
{
    
    BufferedImage bimg;
    if ( matrix != null ) { 
        int cols = matrix.cols();  
        int rows = matrix.rows();  
        int elemSize = (int)matrix.elemSize();  
        byte[] data = new byte[cols * rows * elemSize];  
        int type;  
        matrix.get(0, 0, data);  
        switch (matrix.channels()) {  
        case 1:  
            type = BufferedImage.TYPE_BYTE_GRAY;  
            break;  
        case 3:  
            type = BufferedImage.TYPE_3BYTE_BGR;  
            // bgr to rgb  
            byte b;  
            for(int i=0; i<data.length; i=i+3) {  
                b = data[i];  
                data[i] = data[i+2];  
                data[i+2] = b;  
            }  
            break;  
        default:  
            return null;  
        }  

        // Reuse existing BufferedImage if possible
        
            bimg = new BufferedImage(cols, rows, type);
               
        bimg.getRaster().setDataElements(0, 0, cols, rows, data);
    } else { // mat was null
        bimg = null;
    }
    return bimg;  
}

public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_3BYTE_BGR);

    Graphics2D g2d = dimg.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();

    return dimg;
} 

public BufferedImage GetObrazWynikowy(){
    return ObrazWejsciowy;
}





public BufferedImage GetFragmentOfPicture(int startX, int startY, int endX,int  endY){
    
       int  w = ObrazWejsciowy.getWidth();
       int  h = ObrazWejsciowy.getHeight();
    
       if(startX+endX>w){
           endX = w-startX;
       }
       if(startY+endY>h){
           endY = h-startY;
       }
       if(startX<0){
           startX=0;
       }
       if(startY<0){
           startY=0;
       }
       BufferedImage img = ObrazWejsciowy.getSubimage(startX, startY, endX, endY); //fill in the corners of the desired crop location here
       BufferedImage copyOfImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
       Graphics g = copyOfImage.createGraphics();
       g.drawImage(img, 0, 0, null);
       return copyOfImage;
   }







public BufferedImage  setPicture(BufferedImage img,int width ,int height){
    BufferedImage Out = null;
         double stosunekDługościOryginału = 1.0;
         double ProcentXWzględemOryginału = 1.0;
         double ProcentYWzględemOryginału =1.0;
         if(img.getWidth()<=width&&img.getHeight()<=height){
            
            stosunekDługościOryginału = 1.0;
            ProcentXWzględemOryginału = 1.0;
            ProcentYWzględemOryginału =1.0;
        }
        else if(img.getWidth()>=img.getHeight())
                {
                    stosunekDługościOryginału =(double) img.getWidth()/img.getHeight();
                    int NowyY = (int) (width*stosunekDługościOryginału);

                    if(NowyY>height){

                        int NowyX =  (int) (height*stosunekDługościOryginału);   
                        Out = resize(img,NowyX,height);
                        ProcentXWzględemOryginału =(double) NowyX/img.getWidth();
                        ProcentYWzględemOryginału =(double) height/img.getHeight();
                        stosunekDługościOryginału= ProcentYWzględemOryginału;
                        }
                    else{
                        Out = resize(img,width,NowyY);
                        ProcentXWzględemOryginału =(double) width/img.getWidth();
                        ProcentYWzględemOryginału =(double) NowyY/img.getHeight();
                    }
           
        }
        
        else if(img.getHeight()>=img.getWidth())
                {
                    stosunekDługościOryginału =(double) img.getHeight()/img.getWidth();
                    int NowyX =  (int) (height*stosunekDługościOryginału);
                        if(NowyX>width){
                            int NowyY = (int) (width*stosunekDługościOryginału);
                            Out = resize(img,width,NowyY);
                            ProcentXWzględemOryginału =(double) width/img.getWidth();
                            ProcentYWzględemOryginału =(double) NowyY/img.getHeight();
                        }
                        else{
                             Out = resize(img,NowyX,height);
                             ProcentXWzględemOryginału =(double) NowyX/img.getWidth();
                             ProcentYWzględemOryginału =(double) height/img.getHeight();
                        }
           
        }
         return Out;
     }

            public MatOfRect deleteFailedBoxes(MatOfRect DetectedRectangles ,double overlaptresh){
                ArrayUtils np = new ArrayUtils();
                ArrayList <Rect> FailedBoxes  = new ArrayList<>();
                Rect[] Boxes = DetectedRectangles.toArray();
                double[] areas = np.calculate_area(DetectedRectangles);
                ArrayList<Integer> idxs = np.ToArrayList(np.argsort(areas, true));
                 
                while(idxs.size()>0){
                    int size = idxs.size();
                    
                    for(int box = 0; box<size;box++){
                        if(idxs.get(0)==box){
                            
                        }
                       else if(np.IsInclude(Boxes[idxs.get(0)], Boxes[box]))
                        {
                            int xx1 = Math.max(Boxes[idxs.get(0)].x,Boxes[box].x);
                            int yy1 = Math.max(Boxes[idxs.get(0)].y,Boxes[box].y);
                            int xx2 = Math.min(Boxes[idxs.get(0)].x+Boxes[idxs.get(0)].width, Boxes[box].x+Boxes[box].width);
                            int yy2 = Math.min(Boxes[idxs.get(0)].y+Boxes[idxs.get(0)].height, Boxes[box].y+Boxes[box].height);
                            
                            int w = Math.abs(xx2 - xx1);
                            int h = Math.abs(yy2 - yy1);
                            
                            float overlap = (float) ((w*h)/areas[idxs.get(0)]);
                            if(overlap>overlaptresh)
                            {
                                        FailedBoxes.add(Boxes[idxs.get(0)]);
                                        break;
                            }
                            
                            
                        }
                    }
                    if(idxs.size()>0){
                       idxs.remove(idxs.get(0)); 
                    }
                    
                }
                
            
             
             ArrayList<Rect> DefaultBox = new ArrayList<>(Arrays.asList(Boxes));
             
                 DefaultBox.removeAll(FailedBoxes);
              
              
            Rect[] Out = DefaultBox.toArray(new Rect[0]);
            DetectedRectangles = new MatOfRect(Out);    
            return DetectedRectangles;
            }
   

          
}
