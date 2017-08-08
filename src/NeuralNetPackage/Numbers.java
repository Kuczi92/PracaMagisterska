/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetPackage;

import NumericTasks.Picture;
import static NumericTasks.TypeOfThreshold.PROGOWANIE_ZWYKŁE;
import NumericTasks.ViewPicture;
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
    public int sizeX;
    public int sizeY;
    
    double[] CalculatedValues;
    
    //List of Adress with numbers to training
    double[][] Number0;
    double[][] Number1;
    double[][] Number2;
    double[][] Number3; 
    double[][] Number4;
    double[][] Number5;
    double[][] Number6; 
    double[][] Number7; 
    double[][] Number8;
    double[][] Number9;
    
  public ArrayList<String> ErrorLoadsFiles = new ArrayList<>();
    
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

    
    public double[] CalculatedValuesArray(){
       return CalculatedValues;
    }
    public int RecognizeNumber(Picture Picture,int threshold){
      Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
      CalculatedValues = NeuralNetRecognizeNumbers.ObliczWartości(Picture.convertTo2DWithoutUsingGetRGB(threshold));
      int size=CalculatedValues.length;
      int recognizedNumber=0;
      double max = 0;
      for(int i = 0 ; i< size;i++)
      {
          if(max<CalculatedValues[i])
          {
              max = CalculatedValues[i];
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
        
        double[][] OutNumbers =   new double[10][10];
        for(int num = 0 ;num<10 ;num++){
            for(int currentnum = 0 ; currentnum <10;currentnum ++){
                if(currentnum==num){
                    OutNumbers[num][currentnum] = 1.0;
                }
                else{
                    OutNumbers[num][currentnum] = 0.0;
                }
            }  
        }
       
        for(int i = 0 ; i<LearningCount;i++){
           CurrentCoutLearn++;
           Random whichNumberToTrain = new Random();
           int Number = whichNumberToTrain.nextInt(10);
           //Picture Picture;
          
           switch(Number){
                    case 0:     
                    
                    ErrorsNeuralNets[0]  =  NeuralNetRecognizeNumbers.TrenujSieć(Number0[whichNumberToTrain.nextInt(Number0.length)], OutNumbers[Number], powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel0.setText(""+ErrorsNeuralNets[0]*100);
                    });
                    break;
                    
                    case 1:     
                    
                    ErrorsNeuralNets[1]  =  NeuralNetRecognizeNumbers.TrenujSieć(Number1[whichNumberToTrain.nextInt(Number1.length)],OutNumbers[Number], powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel1.setText(""+ErrorsNeuralNets[1]*100);
                    });
                    break;
                    
                    case 2:     
                    
                    ErrorsNeuralNets[2]  =  NeuralNetRecognizeNumbers.TrenujSieć(Number2[whichNumberToTrain.nextInt(Number2.length)], OutNumbers[Number], powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel2.setText(""+ErrorsNeuralNets[2]*100);
                    });
                    break;
                    
                    case 3:     
                    
                    ErrorsNeuralNets[3]  =  NeuralNetRecognizeNumbers.TrenujSieć(Number3[whichNumberToTrain.nextInt(Number3.length)], OutNumbers[Number], powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel3.setText(""+ErrorsNeuralNets[3]*100);
                    });
                    break;
                    
                    case 4:     
                    
                    ErrorsNeuralNets[4]  =  NeuralNetRecognizeNumbers.TrenujSieć(Number4[whichNumberToTrain.nextInt(Number4.length)],OutNumbers[Number], powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel4.setText(""+ErrorsNeuralNets[4]*100);
                    });
                    break;
                    
                    case 5:    
                    ErrorsNeuralNets[5]  =  NeuralNetRecognizeNumbers.TrenujSieć(Number5[whichNumberToTrain.nextInt(Number5.length)],OutNumbers[Number], powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel5.setText(""+ErrorsNeuralNets[5]*100);
                    });
                    break;
                    
                    case 6:    
                    ErrorsNeuralNets[6]  =  NeuralNetRecognizeNumbers.TrenujSieć(Number6[whichNumberToTrain.nextInt(Number6.length)], OutNumbers[Number], powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel6.setText(""+ErrorsNeuralNets[6]*100);
                    });
                    break;
                    
                    case 7:   
                    ErrorsNeuralNets[7]  =  NeuralNetRecognizeNumbers.TrenujSieć(Number7[whichNumberToTrain.nextInt(Number7.length)],OutNumbers[Number], powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel7.setText(""+ErrorsNeuralNets[7]*100);
                    });
                    break;
                    
                    case 8:
                    ErrorsNeuralNets[8]  =  NeuralNetRecognizeNumbers.TrenujSieć(Number8[whichNumberToTrain.nextInt(Number8.length)], OutNumbers[Number], powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel8.setText(""+ErrorsNeuralNets[8]*100);
                    });
                    break;
                    
                    case 9:  
                    ErrorsNeuralNets[9]  =  NeuralNetRecognizeNumbers.TrenujSieć(Number9[whichNumberToTrain.nextInt(Number9.length)], OutNumbers[Number], powerOfLearn);
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
    public void loadNumbersToTrainNeuralNet(String PathToTrainSet,int threshold){
        
        
                    
        try {
            ArrayList<String> ListOfNumbers  = ListaPlikówWFolderze(PathToTrainSet);
            int numberOfFiles =  ListOfNumbers.size();
            
                    ArrayList<double[]> Number0Array = new ArrayList<>();
                    ArrayList<double[]> Number1Array = new ArrayList<>();
                    ArrayList<double[]> Number2Array = new ArrayList<>();
                    ArrayList<double[]> Number3Array = new ArrayList<>();
                    ArrayList<double[]> Number4Array = new ArrayList<>();
                    ArrayList<double[]> Number5Array = new ArrayList<>();
                    ArrayList<double[]> Number6Array = new ArrayList<>();
                    ArrayList<double[]> Number7Array = new ArrayList<>();
                    ArrayList<double[]> Number8Array = new ArrayList<>();
                    ArrayList<double[]> Number9Array = new ArrayList<>(); 
                    
                    
            for(int i = 0 ; i < numberOfFiles;i++){
                
                       

                
                String FileName =ListOfNumbers.get(i).substring(ListOfNumbers.get(i).lastIndexOf("\\")+1);
                String[] split = FileName.split("_");
                boolean Zoom = false;
                
                if("NumerkiZoom".equals(split[1].substring(0, split[1].indexOf(".")))){
                    Zoom = true;
                }
                
                
                ViewPicture Pic = new ViewPicture(ListOfNumbers.get(i));
                //zmiana progowania mod jasnosci kontrastu kolorów
                int[] pixels = Pic.ModyfikujKoloryWKanaleRGB(-13,-13,-13,20,PROGOWANIE_ZWYKŁE,127);
                Picture Picture = new Picture(pixels,Pic.pobierzX(),Pic.pobierzY());
        
        
                //Picture Picture = new Picture(ListOfNumbers.get(i));
                Picture.setImage(Picture.Negative(Picture.Image()));
                ArrayList<BufferedImage> DetectedNumbers = Picture.FindContorous(0, 1, 0, 40/100*Picture.getImageHeight(), 100/100*Picture.getImageHeight(), 100/100*Picture.getImageWidth(), true, Picture.Negative(Picture.Image()), false,Zoom);
               
                
                
                for(int currentNum = 0 ; currentNum<DetectedNumbers.size(); currentNum++ )
                
                {
                  int NumberOnPicture;
               
                      NumberOnPicture = Integer.valueOf(String.valueOf(split[0].charAt(currentNum))); 
                      Picture.setImage(DetectedNumbers.get(currentNum));
                
               if(DetectedNumbers.size()==split[0].length()){
                   
                   switch (NumberOnPicture){
                    
                    case 0:
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    Number0Array.add(Picture.convertTo2DWithoutUsingGetRGB(threshold));
                    break;
                    
                    case 1:   
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    Number1Array.add(Picture.convertTo2DWithoutUsingGetRGB(threshold));
                    break;
                    
                    case 2:
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    Number2Array.add(Picture.convertTo2DWithoutUsingGetRGB(threshold));
                    break;
                    
                    case 3:
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    Number3Array.add(Picture.convertTo2DWithoutUsingGetRGB(threshold));
                    break; 
                    
                    case 4:
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    Number4Array.add(Picture.convertTo2DWithoutUsingGetRGB(threshold));
                    break;
                    
                    case 5:
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    Number5Array.add(Picture.convertTo2DWithoutUsingGetRGB(threshold));
                    break;
                    
                    case 6:
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    Number6Array.add(Picture.convertTo2DWithoutUsingGetRGB(threshold));
                    break;
                    
                    case 7:
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    Number7Array.add(Picture.convertTo2DWithoutUsingGetRGB(threshold));
                    break;
                    
                    case 8:
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    Number8Array.add(Picture.convertTo2DWithoutUsingGetRGB(threshold));
                    break;
                    
                    case 9:
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    Number9Array.add(Picture.convertTo2DWithoutUsingGetRGB(threshold));
                    break;
                    
                }
                   
                   
               }
                else{
                    ErrorLoadsFiles.add(FileName);
                    break;
                }
                    
                 }
            }
            
            
            
            this.Number0 = CastToArray(Number0Array);
            this.Number1 = CastToArray(Number1Array);
            this.Number2 = CastToArray(Number2Array);
            this.Number3 = CastToArray(Number3Array);
            this.Number4 = CastToArray(Number4Array);
            this.Number5 = CastToArray(Number5Array);
            this.Number6 = CastToArray(Number6Array);
            this.Number7 = CastToArray(Number7Array);
            this.Number8 = CastToArray(Number8Array);
            this.Number9 = CastToArray(Number9Array);
            
     
        }
        catch (IOException ex) 
        {
            Logger.getLogger(Numbers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
    }
    
    private double[][] CastToArray(ArrayList<double[]> input ){
       double[][] Output= new double[input.size()][sizeX*sizeY];
       int size = input.size();
       for(int i = 0 ; i < size;i++){
           Output[i] = input.get(i);
       }
       return Output;
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
