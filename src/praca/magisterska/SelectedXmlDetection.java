/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praca.magisterska;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
    public ArrayList<BufferedImage> Detect(int x , int y,int max_X, int max_Y, int min_X,int min_Y,double overlapFailedbox){
                CascadeClassifier HumanoidsDetector;
                HumanoidsDetector = new CascadeClassifier(loadedXML);
                MatOfRect faceDetections = new MatOfRect();
                //ObrazWejsciowy =  setPicture(ObrazWejsciowy,x,y);
                Mat image = bufferedImageToMat(ObrazWejsciowy);
                HumanoidsDetector.detectMultiScale(image, faceDetections, 1.1, 3,0, new Size(), new Size()); 
        
                faceDetections = deleteFailedBoxes(faceDetections,overlapFailedbox);
                System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
                ArrayList<BufferedImage> Out = new ArrayList<>();
                
                
             for (Rect rect : faceDetections.toArray()) 
              {
                    Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),new Scalar(0, 255, 0),10);
                    Out.add(this.GetFragmentOfPicture(rect.x, rect.y,rect.width, rect.height));
                    ListaPunktów.add(new ArrayList<>());
                    ListaPunktów.get(ListaPunktów.size()-1).add(rect.x);
                    ListaPunktów.get(ListaPunktów.size()-1).add(rect.y);
                    ListaPunktów.get(ListaPunktów.size()-1).add(rect.x+rect.width);
                    ListaPunktów.get(ListaPunktów.size()-1).add(rect.y+rect.height);
              }
              ObrazWejsciowy = MatDoBufferedImage(image);
              return Out;
   }
}
