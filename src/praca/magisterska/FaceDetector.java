/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praca.magisterska;

import java.util.ArrayList;
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

    public FaceDetector(String SciezkaPliku) {
        super(SciezkaPliku);
    }

    
    
   public void DetectFaces(){
       CascadeClassifier faceDetector = new CascadeClassifier("lbpcascade_frontalface.xml");
       CascadeClassifier faceProfileDetector = new  CascadeClassifier("haarcascade_profileface.xml");
       
        ObrazWejsciowy =  setPicture(ObrazWejsciowy,500,500);
     
        Mat image = bufferedImageToMat(ObrazWejsciowy);
 
        MatOfRect faceDetections = new MatOfRect();
        MatOfRect faceProfileDetections = new MatOfRect();
        
        
        
        
        faceProfileDetector.detectMultiScale(image, faceProfileDetections, 1.1, 3,Objdetect.CASCADE_DO_CANNY_PRUNING , new Size(1, 1), new Size(1000, 1000));
        
        faceDetector.detectMultiScale(image, faceDetections, 1.1, 3,Objdetect.CASCADE_DO_CANNY_PRUNING , new Size(1, 1), new Size(1000, 1000)); 
        
        
        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
        
            for (Rect rect : faceDetections.toArray()) {
               
            
            Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),new Scalar(0, 255, 0),2);
            ArrayList<Integer> PunktyTwarzy = new ArrayList();
            PunktyTwarzy.add(rect.x);
            PunktyTwarzy.add(rect.y);
            PunktyTwarzy.add(rect.x + rect.width);
            PunktyTwarzy.add(rect.y + rect.height);
            ListaPunktów.add(PunktyTwarzy);
        
        }
         for (Rect rect : faceProfileDetections.toArray()) {
               
            
            Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),new Scalar(255,0, 0),2);
            ArrayList<Integer> PunktyTwarzy = new ArrayList();
            PunktyTwarzy.add(rect.x);
            PunktyTwarzy.add(rect.y);
            
            PunktyTwarzy.add(rect.x + rect.width);
            PunktyTwarzy.add(rect.y + rect.height);
            
            ListaPunktów.add(PunktyTwarzy);
        
        }
    ObrazWejsciowy = MatDoBufferedImage(image);
   }

   
   
}
