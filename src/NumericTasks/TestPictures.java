/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NumericTasks;

import NeuralNetPackage.Numbers;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.String.format;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

/**
 *
 * @author Quchi
 */
public class TestPictures implements Runnable{
 private final ArrayList <BufferedImage> Number;
 private final ArrayList <Integer> CorrectNumber;
 private final ArrayList <Integer> RecognizedNumber;
 
 private final String PathToFolder;
 public Numbers RecognizeTool;
 
 private final ProgressBar Progress;
 private final Label ProgresPercent;
 private final Label Title;
 
 private TypeOfThreshold TypeOfThreshold;
 private double Red;
 private double Green;
 private double Blue;
 private double ThresholdValue;
 private double Contrast;
 private boolean Negative;
 private double Blurr;
 private int recognitionThreshold;
 
 int sizeOfFiles;
 private boolean Reloading = true;
 
 private Label AccuracyOFRecognizing;
 private ImageView ViewCurrentNumber;
 private Label LabelRecognizedtNumber, LabelCorrectNumber;
 
    public TestPictures(String PathFolder,Numbers Num,ProgressBar Progress,Label ProgresPercent,Label Title){
     this.PathToFolder = PathFolder;
     this.RecognizeTool = Num;
     this.ProgresPercent = ProgresPercent;
     this.Progress = Progress;
     this.Title = Title;
     CorrectNumber = new ArrayList<>();
     RecognizedNumber = new ArrayList<>();
     Number = new ArrayList<>();
    }

    public void SetParametersPicture(double czerwien,double zielen,double niebieski,
        double kontrast,TypeOfThreshold progowanie,int wartoscprogujaca,boolean Negative,
        double blurr,int recognitionThreshold,Label AccuracyOFRecognizing,ImageView ViewCurrentNumber,
        Label RecognizedtNumber, Label CorrectNumber){
        this.Red = czerwien;
        this.Green = zielen;
        this.Blue = niebieski;
        this.TypeOfThreshold = progowanie;
        this.ThresholdValue = wartoscprogujaca;
        this.Contrast = kontrast;
        this.Negative = Negative;
        this.Blurr = blurr;
        this.recognitionThreshold = recognitionThreshold;
        this.AccuracyOFRecognizing = AccuracyOFRecognizing;
        this.ViewCurrentNumber = ViewCurrentNumber;
        this.LabelCorrectNumber = CorrectNumber;
        this.LabelRecognizedtNumber  = RecognizedtNumber;
    }
    
    public void Reload(){
        Reloading = true;
    }
    
    public ArrayList <BufferedImage> GetPicOfTestImage(){
       return this.Number;
    }
    
    public ArrayList <Integer> GetCorrectNumbers(){
       return this.CorrectNumber;
    }
    
    public ArrayList<Integer> GetRecognizedNumbers(){
       return this.RecognizedNumber;
    }
    
    public int NumberOfPictures(){
        return sizeOfFiles;
    }
    
    @Override
    public void run() {
        if(Reloading){
            Platform.runLater(() -> {
            Title.setText("Ładowanie Obrazów: ");
            });
            ArrayList<String> PathToImages = null;
                try {
                    PathToImages = new ArrayUtils().ListaPlikówWFolderze(PathToFolder);
                    sizeOfFiles = PathToImages.size();
                    final int size = sizeOfFiles;
                    int i = 0;
                        for (String PathToImage : PathToImages) {
                             String FileName =PathToImage.substring(PathToImage.lastIndexOf("\\")+1);
                             String[] words = FileName.split("_");
                             CorrectNumber.add(Integer.valueOf(words[0]));
                             Number.add(ImageIO.read(new File(PathToImage)));

                             i++;
                             final double procent = (double)i/(double)size;
                             Platform.runLater(() -> {
                                ProgresPercent.setText(format("%.2f",procent*100)+" %");
                                Progress.setProgress(procent);
                             });
                        }
                        Platform.runLater(() -> {
                                    ProgresPercent.setText(format("%.2f",100.00)+" %");
                                    Progress.setProgress(1);
                                   });
                    //RecognizeTool.RecognizeNumber(Picture, 0);
                    } 
                catch (IOException ex) 
                    {
                    Logger.getLogger(TestPictures.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
            Reloading = false;
        }
        int size = Number.size();
        
        Platform.runLater(() -> {
            Title.setText("Rozpoznawanie liczb: ");
            });
        
        for(int currentImg = 0 ; currentImg<size;currentImg++){
            
                ViewPicture Pic = new ViewPicture(new Picture().setPicture(Number.get(currentImg), RecognizeTool.sizeX, RecognizeTool.sizeY));
                int[] pixels = Pic.ModyfikujKoloryWKanaleRGB(Red,Green,Blue,Contrast,TypeOfThreshold, (int) ThresholdValue);
                Picture Out = new Picture(pixels,Pic.pobierzX(),Pic.pobierzY());
                if(Negative){
                   Out.setImage(Out.Negative(Out.Image()));
                }

                if(Blurr>0.0){
                    Out.setImage(Out.changeBlurr((int)Blurr, (int)Blurr));
                }

                    RecognizedNumber.add(RecognizeTool.RecognizeNumber(Out, recognitionThreshold));


                    final double procent = (double)currentImg/(double)size;
                                 Platform.runLater(() -> {
                                    ProgresPercent.setText(format("%.2f",procent*100)+" %");
                                    Progress.setProgress(procent);
                                   });
            }
             Platform.runLater(() -> {
                                    ProgresPercent.setText(format("%.2f",100.00)+" %");
                                    Progress.setProgress(1);
                                   });
        
        ViewPicture Pic = new ViewPicture(new Picture().setPicture(GetPicOfTestImage().get(0), RecognizeTool.sizeX, RecognizeTool.sizeY));
                int[] pixels = Pic.ModyfikujKoloryWKanaleRGB(Red,Green,Blue,Contrast,TypeOfThreshold, (int) ThresholdValue);
                Picture Out = new Picture(pixels,Pic.pobierzX(),Pic.pobierzY());
                if(Negative){
                   Out.setImage(Out.Negative(Out.Image()));
                }

                if(Blurr>0.0){
                    Out.setImage(Out.changeBlurr((int)Blurr, (int)Blurr));
                }
        Image Img = SwingFXUtils.toFXImage(Out.Image(),null);
        int sizeg= NumberOfPictures();
         
        int correctRecognized = 0;
        for(int currentDetect = 0;currentDetect< sizeg;currentDetect++){
            if(Objects.equals(RecognizedNumber.get(currentDetect), CorrectNumber.get(currentDetect))){
                correctRecognized++;
            }
        }
        
        
        final double Correct = (double)correctRecognized/size;
        Platform.runLater(() -> {
        AccuracyOFRecognizing.setText(format("%.2f",Correct*100)+" %");
        ViewCurrentNumber.setImage(Img);
        });
        
        Platform.runLater(() -> {
        LabelRecognizedtNumber.setText(String.valueOf(RecognizedNumber.get(0)));
        LabelCorrectNumber.setText(String.valueOf(CorrectNumber.get(0)));
        });
         
        
        
    }
 
    
    
}
