/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praca.magisterska;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

/**
 *
 * @author Quchi
 */
public class FaceDetector {
    String SciezkaPliku;
    BufferedImage ObrazWyjsciowy;
    
    ArrayList<ArrayList<Integer>> ListaPunktów = new ArrayList();
    
    
    
   FaceDetector(String SciezkaPliku){
       this.SciezkaPliku =  SciezkaPliku;
   } 
   
  public static Mat bufferedImageToMat(BufferedImage bi) {
  Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
  byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
  mat.put(0, 0, data);
  return mat;
}
   
   public void DetectFaces(){
       CascadeClassifier faceDetector = new CascadeClassifier("lbpcascade_frontalface.xml");
       BufferedImage img = null;
      try 
      {
          img = ImageIO.read(new File(SciezkaPliku));
      } 
      catch (IOException e) 
      {   
   
        System.out.println("Bład podczas ładowania pliku");
      }
        Mat image = bufferedImageToMat(img);
 
        MatOfRect faceDetections = new MatOfRect();
       
    
        
        faceDetector.detectMultiScale(image, faceDetections, 1.1, 3, 
                   Objdetect.CASCADE_DO_CANNY_PRUNING , new Size(1, 1), new Size(1000, 1000)); 
        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
        
            for (Rect rect : faceDetections.toArray()) {
               
            
            Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),new Scalar(0, 255, 0),10);
            ArrayList<Integer> PunktyTwarzy = new ArrayList();
            PunktyTwarzy.add(rect.x);
            PunktyTwarzy.add(rect.y);
            
            PunktyTwarzy.add(rect.x + rect.width);
            PunktyTwarzy.add(rect.y + rect.height);
            
            ListaPunktów.add(PunktyTwarzy);
        
        }
      
    ObrazWyjsciowy = MatDoBufferedImage(image);
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



public BufferedImage GetObrazWynikowy(){
    return ObrazWyjsciowy;
}


}
