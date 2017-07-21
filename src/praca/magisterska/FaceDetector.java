/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praca.magisterska;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javafx.scene.control.Label;
import org.opencv.core.Core;
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
public class FaceDetector extends Detector {

    public FaceDetector(BufferedImage InputImage) {
        super(InputImage);
    }

    
    
   public ArrayList<BufferedImage> DetectFaces(int max_X, int max_Y, int min_X,int min_Y,double overlapFailedbox,Label MovedPointX,Label MovedPointY,Label MovedPointW,Label MovedPointH){
       CascadeClassifier faceDetector = new CascadeClassifier("BuildInXML\\lbpcascade_frontalface.xml");
       CascadeClassifier faceProfileDetector = new  CascadeClassifier("BuildInXML\\haarcascade_profileface.xml");
       
      //  ObrazWejsciowy =  setPicture(ObrazWejsciowy,500,500);
     
        Mat image = bufferedImageToMat(ObrazWejsciowy);
 
        MatOfRect faceDetections = new MatOfRect();
        MatOfRect faceProfileDetections = new MatOfRect();
        
        
        
        
        faceProfileDetector.detectMultiScale(image, faceProfileDetections, 1.1, 3,Objdetect.CASCADE_DO_CANNY_PRUNING , new Size(1, 1), new Size(1000, 1000));
        
        faceDetector.detectMultiScale(image, faceDetections, 1.1, 3,Objdetect.CASCADE_DO_CANNY_PRUNING , new Size(1, 1), new Size(1000, 1000));
        
        faceDetections = deleteFailedBoxes(faceDetections,overlapFailedbox);
        faceProfileDetections = deleteFailedBoxes(faceProfileDetections,overlapFailedbox);
        
        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
        
        double MinimumX = min_X/100.0*ObrazWejsciowy.getWidth();
        double MinumumY = min_Y/100.0*ObrazWejsciowy.getHeight();
        double MaximumX = max_X/100.0*ObrazWejsciowy.getWidth();
        double MaximumY = max_Y/100.0*ObrazWejsciowy.getHeight();
        
        
        
         
         ArrayList<BufferedImage> Out = new ArrayList<>();
            for (Rect rect : faceDetections.toArray()) {
                
               double moddedX = Double.valueOf(MovedPointX.getText().replace(',', '.'))/100*rect.width;
               double moddedY = Double.valueOf(MovedPointY.getText().replace(',', '.'))/100*rect.height;
               double moddedW = Double.valueOf(MovedPointW.getText().replace(',', '.'))/100*rect.width;
               double moddedH = Double.valueOf(MovedPointH.getText().replace(',', '.'))/100*rect.height;
               
                if((MinimumX<rect.width&&MinumumY<rect.height)&&(MaximumX>rect.height&&MaximumY>rect.width))
                    {
                     Core.rectangle(image, new Point(rect.x+moddedX, rect.y+moddedY), new Point(rect.x +moddedX+ rect.width+moddedW, rect.y + moddedY + rect.height+moddedH),new Scalar(0, 255, 0),2);
                     Out.add(this.GetFragmentOfPicture((int) (rect.x+moddedX), (int) (rect.y+moddedY), (int) (rect.width+moddedW), (int) (rect.height+moddedH))); 
                     ArrayList<Integer> PunktyTwarzy = new ArrayList();
                     PunktyTwarzy.add(rect.x);
                     PunktyTwarzy.add(rect.y);
                     PunktyTwarzy.add(rect.x + rect.width);
                     PunktyTwarzy.add(rect.y + rect.height);
                     ListaPunktów.add(PunktyTwarzy);
                     Core.putText(image,"X: "+rect.x+" Y:"+rect.y+" W:"+rect.width+" H:"+rect.height, new Point(rect.x, rect.y), Core.FONT_HERSHEY_PLAIN, 0.8, new Scalar(255, 255, 255));
                    }
            
                   }
         for (Rect rect : faceProfileDetections.toArray()) {
             
               double moddedX = Double.valueOf(MovedPointX.getText().replace(',', '.'))/100*rect.x;
               double moddedY = Double.valueOf(MovedPointY.getText().replace(',', '.'))/100*rect.y;
               double moddedW = Double.valueOf(MovedPointW.getText().replace(',', '.'))/100*rect.width;
               double moddedH = Double.valueOf(MovedPointH.getText().replace(',', '.'))/100*rect.height;
               
                if((MinimumX<rect.width&&MinumumY<rect.height)&&(MaximumX>rect.height&&MaximumY>rect.width))
                    {
                    Core.rectangle(image, new Point(rect.x+moddedX, rect.y+moddedY), new Point(rect.x +moddedX+ rect.width+moddedW, rect.y + moddedY + rect.height+moddedH),new Scalar(255, 0, 0),2);
                    Out.add(this.GetFragmentOfPicture((int) (rect.x+moddedX), (int) (rect.y+moddedY), (int) (rect.width+moddedW), (int) (rect.height+moddedH))); 
                    ArrayList<Integer> PunktyTwarzy = new ArrayList();
                    PunktyTwarzy.add(rect.x);
                    PunktyTwarzy.add(rect.y);
                    PunktyTwarzy.add(rect.x + rect.width);
                    PunktyTwarzy.add(rect.y + rect.height);
                     Core.putText(image,"X: "+rect.x+" Y:"+rect.y+" W:"+rect.width+" H:"+rect.height, new Point(rect.x, rect.y), Core.FONT_HERSHEY_PLAIN, 0.8, new Scalar(255, 255, 255));
                    ListaPunktów.add(PunktyTwarzy);
                   }
        }
    ObrazWejsciowy = MatDoBufferedImage(image);
        return Out;
   }

   
   
}
