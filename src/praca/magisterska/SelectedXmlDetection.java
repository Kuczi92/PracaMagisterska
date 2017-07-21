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
import static praca.magisterska.Detector.bufferedImageToMat;

/**
 *
 * @author Quchi
 */
public class SelectedXmlDetection extends Detector {
    private final String loadedXML;
    public SelectedXmlDetection(BufferedImage InputImage,String XML) {
        super(InputImage);
        this.loadedXML = XML;
    }
    public ArrayList<BufferedImage> Detect(int x , int y,int max_X, int max_Y, int min_X,int min_Y,double overlapFailedbox,
            Label MovedPointX,Label MovedPointY,Label MovedPointW,Label MovedPointH){
                CascadeClassifier HumanoidsDetector;
                HumanoidsDetector = new CascadeClassifier(loadedXML);
                MatOfRect faceDetections = new MatOfRect();
                //ObrazWejsciowy =  setPicture(ObrazWejsciowy,x,y);
                Mat image = bufferedImageToMat(ObrazWejsciowy);
                HumanoidsDetector.detectMultiScale(image, faceDetections, 1.1, 3,0, new Size(), new Size()); 
        
                faceDetections = deleteFailedBoxes(faceDetections,overlapFailedbox);
                System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
                ArrayList<BufferedImage> Out = new ArrayList<>();
                
              
                
                double MinimumX = min_X/100.0*ObrazWejsciowy.getWidth();
                double MinumumY = min_Y/100.0*ObrazWejsciowy.getHeight();
                double MaximumX = max_X/100.0*ObrazWejsciowy.getWidth();
                double MaximumY = max_Y/100.0*ObrazWejsciowy.getHeight();
                
             for (Rect rect : faceDetections.toArray()) 
              {
                  
               double moddedX = Double.valueOf(MovedPointX.getText().replace(',', '.'))/100*rect.width;
               double moddedY = Double.valueOf(MovedPointY.getText().replace(',', '.'))/100*rect.height;
               double moddedW = Double.valueOf(MovedPointW.getText().replace(',', '.'))/100*rect.width;
               double moddedH = Double.valueOf(MovedPointH.getText().replace(',', '.'))/100*rect.height;
               
                   if((MinimumX<rect.width&&MinumumY<rect.height)&&(MaximumX>rect.height&&MaximumY>rect.width))
                     {
                        Core.rectangle(image, new Point(rect.x+moddedX, rect.y+moddedY), new Point(rect.x +moddedX+ rect.width+moddedW, rect.y + moddedY + rect.height+moddedH),new Scalar(0, 255, 0),2);
                        Out.add(this.GetFragmentOfPicture((int) (rect.x+moddedX), (int) (rect.y+moddedY), (int) (rect.width+moddedW), (int) (rect.height+moddedH))); 
                        ListaPunktów.add(new ArrayList<>());
                        ListaPunktów.get(ListaPunktów.size()-1).add(rect.x);
                        ListaPunktów.get(ListaPunktów.size()-1).add(rect.y);
                        ListaPunktów.get(ListaPunktów.size()-1).add(rect.x+rect.width);
                        ListaPunktów.get(ListaPunktów.size()-1).add(rect.y+rect.height);
                     }
              }
              ObrazWejsciowy = MatDoBufferedImage(image);
              return Out;
   }
}
