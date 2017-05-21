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
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.HOGDescriptor;

import static praca.magisterska.Detector.bufferedImageToMat;

/**
 *
 * @author Quchi
 */
public class HumanoidDetecion extends Detector {
   TypeOfDetect Type;
   
   
   public HumanoidDetecion(String SciezkaPliku,TypeOfDetect Type){
        super(SciezkaPliku);
        this.Type = Type;
   }
   public ArrayList<BufferedImage> DetectHumanoids() {
       ArrayList<BufferedImage> ListaBiegaczy = new ArrayList();
       
       if(null == Type){
       ListaBiegaczy = DetectUpperBody();
       }
       else switch (Type) {
           case HUMANOID_DETECT:
              ListaBiegaczy = DetectFullBody();   
               break;
           case UPPER_BODY_DETECT:
              ListaBiegaczy = DetectUpperBody();
               break;
           default:
              ListaBiegaczy = DetectUpperBody();
               break;
       }

       return ListaBiegaczy;
   }
   
   public ArrayList<BufferedImage> DetectUpperBody(){
                CascadeClassifier HumanoidsDetector;
                HumanoidsDetector = new CascadeClassifier("haarcascade_upperbody.xml");
                MatOfRect faceDetections = new MatOfRect();
                ObrazWejsciowy =  setPicture(ObrazWejsciowy,400,400);
                Mat image = bufferedImageToMat(ObrazWejsciowy);
                HumanoidsDetector.detectMultiScale(image, faceDetections, 1.1, 3,0, new Size(), new Size()); 
        
        
                System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
                ArrayList<BufferedImage> Out = new ArrayList<>();
                
             for (Rect rect : faceDetections.toArray()) 
              {
                    Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),new Scalar(0, 255, 0),10);
                    Out.add(this.GetFragmentOfPicture(rect.x, rect.y,rect.width, rect.height));
                    ListaPunktów.get(ListaPunktów.size()-1).add(rect.x);
                    ListaPunktów.get(ListaPunktów.size()-1).add(rect.y);
                    ListaPunktów.get(ListaPunktów.size()-1).add(rect.x+rect.width);
                    ListaPunktów.get(ListaPunktów.size()-1).add(rect.y+rect.height);
              }
              ObrazWejsciowy = MatDoBufferedImage(image);
              return Out;
   }
   
   
   public ArrayList<BufferedImage> DetectFullBody(){
       final HOGDescriptor hog = new HOGDescriptor();
                                     final MatOfFloat descriptors = HOGDescriptor.getDefaultPeopleDetector();
                                     hog.setSVMDetector(descriptors);

                                     ObrazWejsciowy =  setPicture(ObrazWejsciowy,400,400);
                                     
                                     Mat image = bufferedImageToMat(ObrazWejsciowy);

                                     MatOfRect HumanoidsDetections = new MatOfRect();
                                     final MatOfDouble foundWeights = new MatOfDouble();
                                     final Size winStride = new Size(4, 4);
                                     final Size padding = new Size(8, 8);
                                     // Pobaw się parametrami
                                     hog.detectMultiScale(image, HumanoidsDetections, foundWeights, 0, winStride, padding, 1.05, 0, true);
                                    // tu zaimplementuj non maxima suspression by nie powtarzały sie dotekcje w określonym obszarze
                                    
                                    // HumanoidsDetections  = non_max_suppression_slow(HumanoidsDetections,0.65);
                                      HumanoidsDetections = deleteFailedBoxes(HumanoidsDetections,0.65);
                                     System.out.println(String.format("Detected %s Humanoids", HumanoidsDetections.toArray().length));
                                     ArrayList<BufferedImage> Out = new ArrayList<>();
                                         for (Rect rect : HumanoidsDetections.toArray()) {
                                          ListaPunktów.add(new ArrayList<>());
                                          Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),new Scalar(0, 255, 0),1);

                                          Out.add(this.GetFragmentOfPicture(rect.x, rect.y,rect.width,rect.height)); 
                                          ListaPunktów.get(ListaPunktów.size()-1).add(rect.x);
                                          ListaPunktów.get(ListaPunktów.size()-1).add(rect.y);
                                          ListaPunktów.get(ListaPunktów.size()-1).add(rect.x+rect.width);
                                          ListaPunktów.get(ListaPunktów.size()-1).add(rect.y+rect.height);
                                         }

                                 ObrazWejsciowy = MatDoBufferedImage(image);
                                 
                                 return Out;
   }
   
   
   
   
   
   
   
}
