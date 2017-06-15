/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetPackage;

import NumericTasks.Picture;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.String.format;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;



/**
 *
 * @author Quchi
 */
public class Numbers {

    private String PathToNeuralNet;
    
    public int CurrentCoutLearn = 0;
    public double powerOfLearn = 1.0;
    double[] ErrorsNeuralNets = new double[10];
    int sizeX;
    int sizeY;
    
    private ArrayList<Double> CalculatedValues;
    
    //List of Adress with numbers to training
    ArrayList<ArrayList<Double>> Number0;
    ArrayList<ArrayList<Double>> Number1;
    ArrayList<ArrayList<Double>> Number2;
    ArrayList<ArrayList<Double>> Number3; 
    ArrayList<ArrayList<Double>> Number4;
    ArrayList<ArrayList<Double>> Number5;
    ArrayList<ArrayList<Double>> Number6; 
    ArrayList<ArrayList<Double>> Number7; 
    ArrayList<ArrayList<Double>> Number8;
    ArrayList<ArrayList<Double>> Number9;
    
    SiećNeuronowa NeuralNetRecognizeNumbers;
   public Numbers(String Path,int sizeX,int sizeY) throws FileNotFoundException{
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.PathToNeuralNet = Path;
        
        NeuralNetRecognizeNumbers = new SiećNeuronowa(PathToNeuralNet);

    }
   
    public Numbers(String Path) throws FileNotFoundException{
        
        this.PathToNeuralNet = Path;
        
        NeuralNetRecognizeNumbers = new SiećNeuronowa(PathToNeuralNet);
        this.sizeX = NeuralNetRecognizeNumbers.x;
        this.sizeY = NeuralNetRecognizeNumbers.y;
    }
    
    
   public  Numbers(SiećNeuronowa NeuralNet,int sizeX,int sizeY){
      this.sizeX = sizeX;
      this.sizeY = sizeY;
      NeuralNetRecognizeNumbers = NeuralNet;
      NeuralNetRecognizeNumbers.x = sizeX;
      NeuralNetRecognizeNumbers.y = sizeY;
    }

    
    public ArrayList<Double> CalculatedValuesArray(){
       return CalculatedValues;
    }
    public int RecognizeNumber(Picture Picture,int threshold){
      Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
      CalculatedValues = NeuralNetRecognizeNumbers.ObliczWartości(Picture.convertTo2DWithoutUsingGetRGB(threshold));
      int size=CalculatedValues.size();
      int recognizedNumber=0;
      double max = 0;
      for(int i = 0 ; i< size;i++)
      {
          if(max<CalculatedValues.get(i))
          {
              max = CalculatedValues.get(i);
              recognizedNumber = i;
          }
          
      }
        return recognizedNumber;
    }
    
    public void SetPowerOfLearning(double PowerLearn){
        powerOfLearn = PowerLearn;
    }
    
    @SuppressWarnings("SleepWhileInLoop")
    public void TrainingNeuralnet(int LearningCount,ProgressBar ProgressBar,
            Label JLabel,Label JLabel0,Label JLabel1,Label JLabel2,Label JLabel3,
            Label JLabel4,Label JLabel5,Label JLabel6,Label JLabel7,Label JLabel8,
            Label JLabel9,Label AktualnyProcent,LineChart<Number,Number> ChartOfErrors) throws FileNotFoundException, InterruptedException{
        
        ArrayList<ArrayList<Double>> OutNumbers=   new ArrayList<>();
        
        for(int num = 0 ;num<10 ;num++){
            OutNumbers.add(new ArrayList<>());
            for(int currentnum = 0 ; currentnum <10;currentnum ++){
                if(currentnum==num){
                    OutNumbers.get(num).add(1.0);
                }
                else{
                    OutNumbers.get(num).add(0.0);
                }
                
            }
            
        }
       
        for(int i = 0 ; i<LearningCount;i++){
           CurrentCoutLearn++;
           Random whichNumberToTrain = new Random();
           int Number = whichNumberToTrain.nextInt(10);
           Picture Picture;
          
           switch(Number){
                    case 0:     
                    
                    ErrorsNeuralNets[0]  =  NeuralNetRecognizeNumbers.TrenujSieć(Number0.get(whichNumberToTrain.nextInt(Number0.size())), OutNumbers.get(Number), powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel0.setText(""+ErrorsNeuralNets[0]*100);
                    });
                    break;
                    
                    case 1:     
                    
                    ErrorsNeuralNets[1]  =  NeuralNetRecognizeNumbers.TrenujSieć(Number1.get(whichNumberToTrain.nextInt(Number1.size())),OutNumbers.get(Number), powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel1.setText(""+ErrorsNeuralNets[1]*100);
                    });
                    break;
                    
                    case 2:     
                    
                    ErrorsNeuralNets[2]  =  NeuralNetRecognizeNumbers.TrenujSieć(Number2.get(whichNumberToTrain.nextInt(Number2.size())), OutNumbers.get(Number), powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel2.setText(""+ErrorsNeuralNets[2]*100);
                    });
                    break;
                    
                    case 3:     
                    
                    ErrorsNeuralNets[3]  =  NeuralNetRecognizeNumbers.TrenujSieć(Number3.get(whichNumberToTrain.nextInt(Number3.size())), OutNumbers.get(Number), powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel3.setText(""+ErrorsNeuralNets[3]*100);
                    });
                    break;
                    
                    case 4:     
                    
                    ErrorsNeuralNets[4]  =  NeuralNetRecognizeNumbers.TrenujSieć(Number4.get(whichNumberToTrain.nextInt(Number4.size())),OutNumbers.get(Number), powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel4.setText(""+ErrorsNeuralNets[4]*100);
                    });
                    break;
                    
                    case 5:     
                    
                    ErrorsNeuralNets[5]  =  NeuralNetRecognizeNumbers.TrenujSieć(Number5.get(whichNumberToTrain.nextInt(Number5.size())),OutNumbers.get(Number), powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel5.setText(""+ErrorsNeuralNets[5]*100);
                    });
                    break;
                    
                    case 6:     
                    
                    ErrorsNeuralNets[6]  =  NeuralNetRecognizeNumbers.TrenujSieć(Number6.get(whichNumberToTrain.nextInt(Number6.size())), OutNumbers.get(Number), powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel6.setText(""+ErrorsNeuralNets[6]*100);
                    });
                    break;
                    
                    case 7:     
                    
                    ErrorsNeuralNets[7]  =  NeuralNetRecognizeNumbers.TrenujSieć(Number7.get(whichNumberToTrain.nextInt(Number7.size())),OutNumbers.get(Number), powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel7.setText(""+ErrorsNeuralNets[7]*100);
                    });
                    break;
                    
                    case 8:     
                    
                    ErrorsNeuralNets[8]  =  NeuralNetRecognizeNumbers.TrenujSieć(Number8.get(whichNumberToTrain.nextInt(Number8.size())), OutNumbers.get(Number), powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel8.setText(""+ErrorsNeuralNets[8]*100);
                    });
                    break;
                    
                    case 9:     
                    
                    ErrorsNeuralNets[9]  =  NeuralNetRecognizeNumbers.TrenujSieć(Number9.get(whichNumberToTrain.nextInt(Number9.size())), OutNumbers.get(Number), powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel9.setText(""+ErrorsNeuralNets[9]*100);
                    });
                    break;
           }
            
           
                int div; 
                 if(LearningCount/1000<1){
                    div = 1; 
                 }
                 else {
                   div =  LearningCount/1000;
                 }
                 if(i%(div)==0){
                        final int cyklu;
                        cyklu = i;
                        Platform.runLater(()->{
                        ChartOfErrors.getData().get(9).getData().add(new XYChart.Data(cyklu,ErrorsNeuralNets[9]*100));
                        });
                        Platform.runLater(()->{
                        ChartOfErrors.getData().get(8).getData().add(new XYChart.Data(cyklu,ErrorsNeuralNets[8]*100));
                        });
                        Platform.runLater(()->{
                        ChartOfErrors.getData().get(7).getData().add(new XYChart.Data(cyklu,ErrorsNeuralNets[7]*100));
                        });
                        Platform.runLater(()->{
                        ChartOfErrors.getData().get(6).getData().add(new XYChart.Data(cyklu,ErrorsNeuralNets[6]*100));
                        });
                        Platform.runLater(()->{
                        ChartOfErrors.getData().get(5).getData().add(new XYChart.Data(cyklu,ErrorsNeuralNets[5]*100));
                        });
                        Platform.runLater(()->{
                        ChartOfErrors.getData().get(4).getData().add(new XYChart.Data(cyklu,ErrorsNeuralNets[4]*100));
                        });
                        Platform.runLater(()->{
                        ChartOfErrors.getData().get(3).getData().add(new XYChart.Data(cyklu,ErrorsNeuralNets[3]*100));
                        });
                        Platform.runLater(()->{
                        ChartOfErrors.getData().get(2).getData().add(new XYChart.Data(cyklu,ErrorsNeuralNets[2]*100));
                        });
                        Platform.runLater(()->{
                        ChartOfErrors.getData().get(1).getData().add(new XYChart.Data(cyklu,ErrorsNeuralNets[1]*100));
                        });
                        Platform.runLater(()->{
                        ChartOfErrors.getData().get(0).getData().add(new XYChart.Data(cyklu,ErrorsNeuralNets[0]*100));
                        });
                 }
                        
          
                 
                                                      
                Platform.runLater(() -> {
                 ProgressBar.setProgress(((double)CurrentCoutLearn/LearningCount));
                  });
                
                Platform.runLater(() -> {
                AktualnyProcent.setText(format("%.2f",((double)CurrentCoutLearn/LearningCount*100)));
                 }); 
                
                
                Platform.runLater(() -> {
                 JLabel.setText(CurrentCoutLearn+" wykonano na: "+LearningCount);
                  });
            
            }      

        Platform.runLater(() -> {
         ProgressBar.setProgress(1);
         });
    }
    public void loadNumbersToTrainNeuralNet(String PathToTrainSet){
        
        
                    
        try {
            ArrayList<String> ListOfNumbers  = ListaPlikówWFolderze(PathToTrainSet);
            int numberOfFiles =  ListOfNumbers.size();
            
                    ArrayList<ArrayList<Double>> Number0Array = new ArrayList<>();
                    ArrayList<ArrayList<Double>> Number1Array = new ArrayList<>();
                    ArrayList<ArrayList<Double>> Number2Array = new ArrayList<>();
                    ArrayList<ArrayList<Double>> Number3Array = new ArrayList<>();
                    ArrayList<ArrayList<Double>> Number4Array = new ArrayList<>();
                    ArrayList<ArrayList<Double>> Number5Array = new ArrayList<>();
                    ArrayList<ArrayList<Double>> Number6Array = new ArrayList<>();
                    ArrayList<ArrayList<Double>> Number7Array = new ArrayList<>();
                    ArrayList<ArrayList<Double>> Number8Array = new ArrayList<>();
                    ArrayList<ArrayList<Double>> Number9Array = new ArrayList<>(); 
                    
                    
            for(int i = 0 ; i < numberOfFiles;i++){
                
                       

                
                String FileName =ListOfNumbers.get(i).substring(ListOfNumbers.get(i).lastIndexOf("\\")+1);
                String[] split = FileName.split("_");
                
                Picture Picture = new Picture(ListOfNumbers.get(i));
                
                Picture.setImage(Picture.Negative(Picture.Image()));
                ArrayList<BufferedImage> DetectedNumbers = Picture.FindContorous(0, 1, 0, 40/100*Picture.getImageHeight(), 100/100*Picture.getImageHeight(), 100/100*Picture.getImageWidth(), true, Picture.Negative(Picture.Image()), false);
               
                
                
                for(int currentNum = 0 ; currentNum<DetectedNumbers.size(); currentNum++ ){
                    
                    
                int NumberOnPicture = Integer.valueOf(String.valueOf(split[0].charAt(currentNum))); 
                Picture.setImage(DetectedNumbers.get(currentNum));
                
                
                switch (NumberOnPicture){
                    
                    case 0:
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    Picture.convertTo2DWithoutUsingGetRGB(127);
                    Number0Array.add(Picture.convertTo2DWithoutUsingGetRGB(127));
                    break;
                    
                    case 1:   
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    Picture.convertTo2DWithoutUsingGetRGB(127);
                    Number1Array.add(Picture.convertTo2DWithoutUsingGetRGB(127));
                    break;
                    
                    case 2:
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    Picture.convertTo2DWithoutUsingGetRGB(127);
                    Number2Array.add(Picture.convertTo2DWithoutUsingGetRGB(127));
                    break;
                    
                    case 3:
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    Picture.convertTo2DWithoutUsingGetRGB(127);
                    Number3Array.add(Picture.convertTo2DWithoutUsingGetRGB(127));
                    break; 
                    
                    case 4:
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    Picture.convertTo2DWithoutUsingGetRGB(127);
                    Number4Array.add(Picture.convertTo2DWithoutUsingGetRGB(127));
                    break;
                    
                    case 5:
                 
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    Picture.convertTo2DWithoutUsingGetRGB(127);
                    Number5Array.add(Picture.convertTo2DWithoutUsingGetRGB(127));
                    break;
                    
                    case 6:
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    Picture.convertTo2DWithoutUsingGetRGB(127);
                    Number6Array.add(Picture.convertTo2DWithoutUsingGetRGB(127));
                    break;
                    
                    case 7:
                    
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    Picture.convertTo2DWithoutUsingGetRGB(127);
                    Number7Array.add(Picture.convertTo2DWithoutUsingGetRGB(127));
                    break;
                    
                    case 8:
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    Picture.convertTo2DWithoutUsingGetRGB(127);
                    Number8Array.add(Picture.convertTo2DWithoutUsingGetRGB(127));
                    break;
                    
                    case 9:
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    Picture.convertTo2DWithoutUsingGetRGB(127);
                    Number9Array.add(Picture.convertTo2DWithoutUsingGetRGB(127));
                    break;
                    
                }
   
            }
            }
            this.Number0 = Number0Array;
            this.Number1 = Number1Array;
            this.Number2 = Number2Array;
            this.Number3 = Number3Array;
            this.Number4 = Number4Array;
            this.Number5 = Number5Array;
            this.Number6 = Number6Array;
            this.Number7 = Number7Array;
            this.Number8 = Number8Array;
            this.Number9 = Number9Array;
            
     
        }
        catch (IOException ex) 
        {
            Logger.getLogger(Numbers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
    }
    
     private ArrayList<String> ListaPlikówWFolderze(String Ściezka) throws IOException{
        ArrayList<String> ListaPlików = new ArrayList<>();
        try(Stream<Path> paths = Files.walk(Paths.get(Ściezka))) {
          paths.forEach((Path filePath) -> {
        if (Files.isRegularFile(filePath)) {
            
            String temp = filePath.toString();
            if((temp.endsWith("jpg")||temp.endsWith("png")||temp.endsWith("jpeg")||temp.endsWith("JPEG")||temp.endsWith("JPG"))||temp.endsWith("bmp"))
            {
                ListaPlików.add(filePath.toString());
            }

           }
          });
         }
        return ListaPlików;
    
    }

   public void SaveNeuralNet(String NewPathNeuralNet) throws FileNotFoundException {
       NeuralNetRecognizeNumbers.ZapiszSiećDoPliku(NewPathNeuralNet);
    }
}
