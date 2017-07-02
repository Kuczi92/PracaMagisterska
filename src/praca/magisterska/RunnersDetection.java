/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praca.magisterska;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Quchi
 */
public class RunnersDetection  {
    final TypeOfDetect Type;
    final String Path;
    ArrayList<ArrayList<Integer>> ListaPunktów = new ArrayList();
    ArrayList<BufferedImage> ListaBiegaczy = new ArrayList();
    BufferedImage Image;
    RunnersDetection(TypeOfDetect Type,String path,int x, int y, int max_X, int max_Y, int min_X,int min_Y,double overlapFailedbox,boolean loadXML, String XML)
    {
        this.Type = Type;
        this.Path = path;
        
        if(loadXML){
        Type = TypeOfDetect.SELECTED_XML;
        }
        
        switch (Type)
                    {
                        case FACE_DETECT:
                            FaceDetector FaceDetector = new FaceDetector(Path);
                            FaceDetector.DetectFaces();
                            Image = FaceDetector.GetObrazWynikowy();
                            ListaPunktów = FaceDetector.ListaPunktów;
                            break;
                        case HUMANOID_DETECT:
                            HumanoidDetecion HumanoidDetecionFullBody = new HumanoidDetecion(Path,Type);
                            ListaBiegaczy =   HumanoidDetecionFullBody.DetectHumanoids(x,y,max_X,max_Y,min_X,min_Y,overlapFailedbox);
                            Image = HumanoidDetecionFullBody.GetObrazWynikowy();
                            ListaPunktów = HumanoidDetecionFullBody.ListaPunktów;
                            break;
                        case UPPER_BODY_DETECT:
                            HumanoidDetecion HumanoidDetecionUpper = new HumanoidDetecion(Path,Type);
                            ListaBiegaczy =  HumanoidDetecionUpper.DetectHumanoids(x,y,max_X,max_Y,min_X,min_Y,overlapFailedbox);
                            Image = HumanoidDetecionUpper.GetObrazWynikowy();
                            ListaPunktów = HumanoidDetecionUpper.ListaPunktów;
                            break;
                            
                        case SELECTED_XML:
                            SelectedXmlDetection DetectXML = new SelectedXmlDetection(Path,XML);
                            ListaBiegaczy =  DetectXML.Detect(x,y,max_X,max_Y,min_X,min_Y,overlapFailedbox);
                            Image = DetectXML.GetObrazWynikowy();
                            ListaPunktów = DetectXML.ListaPunktów;
                            break;
                        default:
                            throw new AssertionError(Type.name());    
                     }
        
    }
    public BufferedImage PicWithRunners(){
    return Image;
    }
    
    public ArrayList<BufferedImage> DetectedRunners(){
        return ListaBiegaczy;
    } 
             
}
