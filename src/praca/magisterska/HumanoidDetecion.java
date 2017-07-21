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
   
   
   public HumanoidDetecion(BufferedImage InputImage,TypeOfDetect Type){
        super(InputImage);
        this.Type = Type;
   }
   public ArrayList<BufferedImage> DetectHumanoids(int x , int y,int max_X, int max_Y, int min_X,int min_Y,double overlapFailedbox,
           Label MovedPointX,Label MovedPointY,Label MovedPointW,Label MovedPointH) {
       ArrayList<BufferedImage> ListaBiegaczy = new ArrayList();
       
       if(null == Type){
       ListaBiegaczy = DetectUpperBody(x,y,max_X,max_Y,min_X,min_Y,overlapFailedbox,MovedPointX,MovedPointY,MovedPointW,MovedPointH);
       }
       else switch (Type) {
           case HUMANOID_DETECT:
              ListaBiegaczy = DetectFullBody(x,y,max_X,max_Y,min_X,min_Y,overlapFailedbox,MovedPointX,MovedPointY,MovedPointW,MovedPointH);   
               break;
           case UPPER_BODY_DETECT:
              ListaBiegaczy = DetectUpperBody(x,y,max_X,max_Y,min_X,min_Y,overlapFailedbox,MovedPointX,MovedPointY,MovedPointW,MovedPointH);
               break;
           default:
              ListaBiegaczy = DetectUpperBody(x,y,max_X,max_Y,min_X,min_Y,overlapFailedbox,MovedPointX,MovedPointY,MovedPointW,MovedPointH);
               break;
       }

       return ListaBiegaczy;
   }
   
   public ArrayList<BufferedImage> DetectUpperBody(int x , int y,int max_X, int max_Y, int min_X,int min_Y,double overlapFailedbox,
           Label MovedPointX,Label MovedPointY,Label MovedPointW,Label MovedPointH){
                CascadeClassifier HumanoidsDetector;
                HumanoidsDetector = new CascadeClassifier("BuildInXML\\haarcascade_upperbody.xml");
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
                                                    Core.putText(image,"X: "+rect.x+" Y:"+rect.y+" W:"+rect.width+" H:"+rect.height, new Point(rect.x, rect.y), Core.FONT_HERSHEY_PLAIN, 0.8, new Scalar(255, 255, 255));

                                                }
              }
              ObrazWejsciowy = MatDoBufferedImage(image);
              return Out;
   }
   
   
   public ArrayList<BufferedImage> DetectFullBody(int x , int y,int max_X, int max_Y, int min_X,int min_Y,double overlapFailedbox,
           Label MovedPointX,Label MovedPointY,Label MovedPointW,Label MovedPointH){
       final HOGDescriptor hog = new HOGDescriptor();
                                     final MatOfFloat descriptors = HOGDescriptor.getDefaultPeopleDetector();
                                     hog.setSVMDetector(descriptors);

                                    // ObrazWejsciowy =  setPicture(ObrazWejsciowy,x,y);
                                     
                                     Mat image = bufferedImageToMat(ObrazWejsciowy);

                                     MatOfRect HumanoidsDetections = new MatOfRect();
                                     final MatOfDouble foundWeights = new MatOfDouble();
                                     final Size winStride = new Size(4, 4);
                                     final Size padding = new Size(8, 8);
                                     // Pobaw się parametrami
                                      hog.detectMultiScale(image, HumanoidsDetections, foundWeights, 0, winStride, padding, 1.05, 0, true);
                                    // tu zaimplementuj non maxima suspression by nie powtarzały sie dotekcje w określonym obszarze
                                    
                                    // HumanoidsDetections  = non_max_suppression_slow(HumanoidsDetections,0.65);
                                      HumanoidsDetections = deleteFailedBoxes(HumanoidsDetections,overlapFailedbox);
                                     System.out.println(String.format("Detected %s Humanoids", HumanoidsDetections.toArray().length));
                                     ArrayList<BufferedImage> Out = new ArrayList<>();
                                     
                                     
                                     double moddedX = Double.valueOf(MovedPointX.getText().replace(',', '.'))/100*ObrazWejsciowy.getWidth();
                                     double moddedY = Double.valueOf(MovedPointY.getText().replace(',', '.'))/100*ObrazWejsciowy.getHeight();
                                     double moddedW = Double.valueOf(MovedPointW.getText().replace(',', '.'))/100*ObrazWejsciowy.getWidth();
                                     double moddedH = Double.valueOf(MovedPointH.getText().replace(',', '.'))/100*ObrazWejsciowy.getHeight();
                                     
                                     double MinimumX = min_X/100.0*ObrazWejsciowy.getWidth();
                                     double MinumumY = min_Y/100.0*ObrazWejsciowy.getHeight();
                                     double MaximumX = max_X/100.0*ObrazWejsciowy.getWidth();
                                     double MaximumY = max_Y/100.0*ObrazWejsciowy.getHeight();
                                     
                                         for (Rect rect : HumanoidsDetections.toArray()) {
                                             
                                                
                                                
                                                if((MinimumX<rect.width&&MinumumY<rect.height)&&(MaximumX>rect.height&&MaximumY>rect.width))
                                                {
                                                         
                                                          
                                                          ListaPunktów.add(new ArrayList<>());
                                                          
                                                          
                                                          Core.rectangle(image, new Point(rect.x+moddedX, rect.y+moddedY), new Point(rect.x +moddedX+ rect.width+moddedW, rect.y + moddedY + rect.height+moddedH),new Scalar(0, 255, 0),2);
                                                          Out.add(this.GetFragmentOfPicture((int) (rect.x+moddedX), (int) (rect.y+moddedY), (int) (rect.width+moddedW), (int) (rect.height+moddedH))); 
                                                          
                                                          
                                                          
                                                          ListaPunktów.get(ListaPunktów.size()-1).add(rect.x);
                                                          ListaPunktów.get(ListaPunktów.size()-1).add(rect.y);
                                                          ListaPunktów.get(ListaPunktów.size()-1).add(rect.x+rect.width);
                                                          ListaPunktów.get(ListaPunktów.size()-1).add(rect.y+rect.height);
                                                          Core.putText(image,"X: "+rect.x+" Y:"+rect.y+" W:"+rect.width+" H:"+rect.height, new Point(rect.x, rect.y), Core.FONT_HERSHEY_PLAIN, 0.8, new Scalar(255, 255, 255));
                                                          
                                                }
                                                else{
                                                    
                                                }
                                          }  
                                          
                                         

                                 ObrazWejsciowy = MatDoBufferedImage(image);
                                 
                                 return Out;
   }
}
   
   
   
   
   
   
   
   

