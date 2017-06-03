/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetPackage;

import NumericTasks.Picture;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.String.format;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
    String[] Number0;
    String[] Number1;
    String[] Number2;
    String[] Number3; 
    String[] Number4;
    String[] Number5;
    String[] Number6; 
    String[] Number7; 
    String[] Number8;
    String[] Number9;
    
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
    public void TrainingNeuralnet(int LearningCount,ProgressBar ProgressBar,Label JLabel,Label JLabel0,Label JLabel1,Label JLabel2,Label JLabel3,Label JLabel4,Label JLabel5,Label JLabel6,Label JLabel7,Label JLabel8,Label JLabel9,Label AktualnyProcent,LineChart<Number,Number> ChartOfErrors) throws FileNotFoundException, InterruptedException{
        
        
        int number0=Number0.length;
        int number1=Number1.length;
        int number2=Number2.length;
        int number3=Number3.length;
        int number4=Number4.length;
        int number5=Number5.length;
        int number6=Number6.length;
        int number7=Number7.length;
        int number8=Number8.length;
        int number9=Number9.length;
        
       
        for(int i = 0 ; i<LearningCount;i++){
           CurrentCoutLearn++;
           Random whichNumberToTrain = new Random();
           int Number = whichNumberToTrain.nextInt(10);
           Picture Picture;
          
           switch(Number){
                    case 0:     
                    Picture = new Picture(Number0[whichNumberToTrain.nextInt(Number0.length)]);
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    ErrorsNeuralNets[0]  =  NeuralNetRecognizeNumbers.TrenujSieć(Picture.convertTo2DWithoutUsingGetRGB(127), new ArrayList<>(Arrays.asList(1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0)), powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel0.setText(""+ErrorsNeuralNets[0]*100);
                    });
                    break;
                    
                    case 1:     
                    Picture = new Picture(Number1[whichNumberToTrain.nextInt(Number1.length)]);
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    ErrorsNeuralNets[1]  =  NeuralNetRecognizeNumbers.TrenujSieć(Picture.convertTo2DWithoutUsingGetRGB(127), new ArrayList<>(Arrays.asList(0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0)), powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel1.setText(""+ErrorsNeuralNets[1]*100);
                    });
                    break;
                    
                    case 2:     
                    Picture = new Picture(Number2[whichNumberToTrain.nextInt(Number2.length)]);
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    ErrorsNeuralNets[2]  =  NeuralNetRecognizeNumbers.TrenujSieć(Picture.convertTo2DWithoutUsingGetRGB(127), new ArrayList<>(Arrays.asList(0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0)), powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel2.setText(""+ErrorsNeuralNets[2]*100);
                    });
                    break;
                    
                    case 3:     
                    Picture = new Picture(Number3[whichNumberToTrain.nextInt(Number3.length)]);
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    ErrorsNeuralNets[3]  =  NeuralNetRecognizeNumbers.TrenujSieć(Picture.convertTo2DWithoutUsingGetRGB(127), new ArrayList<>(Arrays.asList(0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0)), powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel3.setText(""+ErrorsNeuralNets[3]*100);
                    });
                    break;
                    
                    case 4:     
                    Picture = new Picture(Number4[whichNumberToTrain.nextInt(Number4.length)]);
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    ErrorsNeuralNets[4]  =  NeuralNetRecognizeNumbers.TrenujSieć(Picture.convertTo2DWithoutUsingGetRGB(127), new ArrayList<>(Arrays.asList(0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0)), powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel4.setText(""+ErrorsNeuralNets[4]*100);
                    });
                    break;
                    
                    case 5:     
                    Picture = new Picture(Number5[whichNumberToTrain.nextInt(Number5.length)]);
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    ErrorsNeuralNets[5]  =  NeuralNetRecognizeNumbers.TrenujSieć(Picture.convertTo2DWithoutUsingGetRGB(127), new ArrayList<>(Arrays.asList(0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0)), powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel5.setText(""+ErrorsNeuralNets[5]*100);
                    });
                    break;
                    
                    case 6:     
                    Picture = new Picture(Number6[whichNumberToTrain.nextInt(Number6.length)]);
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    ErrorsNeuralNets[6]  =  NeuralNetRecognizeNumbers.TrenujSieć(Picture.convertTo2DWithoutUsingGetRGB(127), new ArrayList<>(Arrays.asList(0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0)), powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel6.setText(""+ErrorsNeuralNets[6]*100);
                    });
                    break;
                    
                    case 7:     
                    Picture = new Picture(Number7[whichNumberToTrain.nextInt(Number7.length)]);
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    ErrorsNeuralNets[7]  =  NeuralNetRecognizeNumbers.TrenujSieć(Picture.convertTo2DWithoutUsingGetRGB(127), new ArrayList<>(Arrays.asList(0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0)), powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel7.setText(""+ErrorsNeuralNets[7]*100);
                    });
                    break;
                    
                    case 8:     
                    Picture = new Picture(Number8[whichNumberToTrain.nextInt(Number8.length)]);
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    ErrorsNeuralNets[8]  =  NeuralNetRecognizeNumbers.TrenujSieć(Picture.convertTo2DWithoutUsingGetRGB(127), new ArrayList<>(Arrays.asList(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0)), powerOfLearn);
                    Platform.runLater(() -> {
                    JLabel8.setText(""+ErrorsNeuralNets[8]*100);
                    });
                    break;
                    
                    case 9:     
                    Picture = new Picture(Number9[whichNumberToTrain.nextInt(Number9.length)]);
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    ErrorsNeuralNets[9]  =  NeuralNetRecognizeNumbers.TrenujSieć(Picture.convertTo2DWithoutUsingGetRGB(127), new ArrayList<>(Arrays.asList(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0)), powerOfLearn);
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
            
                    ArrayList<String> Number0Array = new ArrayList<>();
                    ArrayList<String> Number1Array = new ArrayList<>();
                    ArrayList<String> Number2Array = new ArrayList<>();
                    ArrayList<String> Number3Array = new ArrayList<>();
                    ArrayList<String> Number4Array = new ArrayList<>();
                    ArrayList<String> Number5Array = new ArrayList<>();
                    ArrayList<String> Number6Array = new ArrayList<>();
                    ArrayList<String> Number7Array = new ArrayList<>();
                    ArrayList<String> Number8Array = new ArrayList<>();
                    ArrayList<String> Number9Array = new ArrayList<>(); 
                    
                    
            for(int i = 0 ; i < numberOfFiles;i++){
                
                       

                
                String FileName =ListOfNumbers.get(i).substring(ListOfNumbers.get(i).lastIndexOf("\\"));
                String[] split = FileName.split("_");
                int NumberOnPicture = Integer.valueOf(split[1]);
                
                switch (NumberOnPicture){
                    
                    case 0:   
                    Number0Array.add(ListOfNumbers.get(i));
                    break;
                    
                    case 1:    
                    Number1Array.add(ListOfNumbers.get(i));
                    break;
                    
                    case 2:
                    Number2Array.add(ListOfNumbers.get(i));
                    break;
                    
                    case 3:
                    Number3Array.add(ListOfNumbers.get(i));
                    break; 
                    
                    case 4:
                    Number4Array.add(ListOfNumbers.get(i));
                    break;
                    
                    case 5:
                    Number5Array.add(ListOfNumbers.get(i));
                    break;
                    
                    case 6:
                    Number6Array.add(ListOfNumbers.get(i));
                    break;
                    
                    case 7:
                    Number7Array.add(ListOfNumbers.get(i));
                    break;
                    
                    case 8:
                    Number8Array.add(ListOfNumbers.get(i));
                    break;
                    
                    case 9:
                    Number9Array.add(ListOfNumbers.get(i));
                    break;
                    
                }
   
            }
            this.Number0 = new String[Number0Array.size()];
            this.Number0 =  Number0Array.toArray(this.Number0);
            
            this.Number1 = new String[Number1Array.size()];
            this.Number1 = Number1Array.toArray(this.Number1);
            
            this.Number2 = new String[Number2Array.size()];
            this.Number2 = Number2Array.toArray(this.Number2);
            
            this.Number3 = new String[Number3Array.size()];
            this.Number3 = Number3Array.toArray(this.Number3);
            
            this.Number4 = new String[Number4Array.size()];
            this.Number4 = Number4Array.toArray(this.Number4);
            
            this.Number5 = new String[Number5Array.size()];
            this.Number5 = Number5Array.toArray(this.Number5);
            
            this.Number6 = new String[Number6Array.size()];
            this.Number6 = Number6Array.toArray(this.Number6);
            
            this.Number7 = new String[Number7Array.size()];
            this.Number7 = Number7Array.toArray(this.Number7);
            
            this.Number8 = new String[Number8Array.size()];
            this.Number8 = Number8Array.toArray(this.Number8);
            
            this.Number9 = new String[Number9Array.size()];
            this.Number9 = Number9Array.toArray(this.Number9);
     
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
