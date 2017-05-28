/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetPackage;

import NumericTasks.Picture;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

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
    Numbers(String Path,int sizeX,int sizeY) throws FileNotFoundException{
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.PathToNeuralNet = Path;
        
        NeuralNetRecognizeNumbers = new SiećNeuronowa(PathToNeuralNet);

    }
    
    Numbers(SiećNeuronowa NeuralNet,int sizeX,int sizeY){
      this.sizeX = sizeX;
      this.sizeY = sizeY;
      NeuralNetRecognizeNumbers = NeuralNet;
    }

    Numbers(String SourcePathLoadedNeuralNet) throws FileNotFoundException {
        this.PathToNeuralNet = SourcePathLoadedNeuralNet;
        NeuralNetRecognizeNumbers = new SiećNeuronowa(PathToNeuralNet);
        this.sizeX = NeuralNetRecognizeNumbers.x;
        this.sizeY = NeuralNetRecognizeNumbers.y;
    }
    
    public int RecognizeNumber(Picture Picture,int threshold){
      
      ArrayList<Double> CalculatedValues = NeuralNetRecognizeNumbers.ObliczWartości(Picture.convertTo2DWithoutUsingGetRGB(threshold));
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
    
    public void TrainingNeuralnet(int LearningCount,JProgressBar JProgressBar,JLabel JLabel,JLabel JLabel0,JLabel JLabel1,JLabel JLabel2,JLabel JLabel3,JLabel JLabel4,JLabel JLabel5,JLabel JLabel6,JLabel JLabel7,JLabel JLabel8,JLabel JLabel9) throws FileNotFoundException{
        
        
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
                    break;
                    
                    case 1:     
                    Picture = new Picture(Number1[whichNumberToTrain.nextInt(Number1.length)]);
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    ErrorsNeuralNets[1]  =  NeuralNetRecognizeNumbers.TrenujSieć(Picture.convertTo2DWithoutUsingGetRGB(127), new ArrayList<>(Arrays.asList(0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0)), powerOfLearn);
                    break;
                    
                    case 2:     
                    Picture = new Picture(Number2[whichNumberToTrain.nextInt(Number2.length)]);
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    ErrorsNeuralNets[2]  =  NeuralNetRecognizeNumbers.TrenujSieć(Picture.convertTo2DWithoutUsingGetRGB(127), new ArrayList<>(Arrays.asList(0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0)), powerOfLearn);
                    break;
                    
                    case 3:     
                    Picture = new Picture(Number3[whichNumberToTrain.nextInt(Number3.length)]);
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    ErrorsNeuralNets[3]  =  NeuralNetRecognizeNumbers.TrenujSieć(Picture.convertTo2DWithoutUsingGetRGB(127), new ArrayList<>(Arrays.asList(0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0)), powerOfLearn);
                    break;
                    
                    case 4:     
                    Picture = new Picture(Number4[whichNumberToTrain.nextInt(Number4.length)]);
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    ErrorsNeuralNets[4]  =  NeuralNetRecognizeNumbers.TrenujSieć(Picture.convertTo2DWithoutUsingGetRGB(127), new ArrayList<>(Arrays.asList(0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0)), powerOfLearn);
                    break;
                    
                    case 5:     
                    Picture = new Picture(Number5[whichNumberToTrain.nextInt(Number5.length)]);
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    ErrorsNeuralNets[5]  =  NeuralNetRecognizeNumbers.TrenujSieć(Picture.convertTo2DWithoutUsingGetRGB(127), new ArrayList<>(Arrays.asList(0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0)), powerOfLearn);
                    break;
                    
                    case 6:     
                    Picture = new Picture(Number6[whichNumberToTrain.nextInt(Number6.length)]);
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    ErrorsNeuralNets[6]  =  NeuralNetRecognizeNumbers.TrenujSieć(Picture.convertTo2DWithoutUsingGetRGB(127), new ArrayList<>(Arrays.asList(0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0)), powerOfLearn);
                    break;
                    
                    case 7:     
                    Picture = new Picture(Number7[whichNumberToTrain.nextInt(Number7.length)]);
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    ErrorsNeuralNets[7]  =  NeuralNetRecognizeNumbers.TrenujSieć(Picture.convertTo2DWithoutUsingGetRGB(127), new ArrayList<>(Arrays.asList(0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0)), powerOfLearn);
                    break;
                    
                    case 8:     
                    Picture = new Picture(Number8[whichNumberToTrain.nextInt(Number8.length)]);
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    ErrorsNeuralNets[8]  =  NeuralNetRecognizeNumbers.TrenujSieć(Picture.convertTo2DWithoutUsingGetRGB(127), new ArrayList<>(Arrays.asList(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0)), powerOfLearn);
                    break;
                    
                    case 9:     
                    Picture = new Picture(Number8[whichNumberToTrain.nextInt(Number9.length)]);
                    Picture.ZmieńRozmiarObrazu(sizeX, sizeY);
                    ErrorsNeuralNets[9]  =  NeuralNetRecognizeNumbers.TrenujSieć(Picture.convertTo2DWithoutUsingGetRGB(127), new ArrayList<>(Arrays.asList(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0)), powerOfLearn);
                    break;
           }
          
           
           
           JProgressBar.setValue((int)(100*CurrentCoutLearn/LearningCount));
           JLabel.setText(CurrentCoutLearn+" wykonano na: "+LearningCount);
           JLabel0.setText(""+ErrorsNeuralNets[0]*100);
           JLabel1.setText(""+ErrorsNeuralNets[1]*100);
           JLabel2.setText(""+ErrorsNeuralNets[2]*100);
           JLabel3.setText(""+ErrorsNeuralNets[3]*100);
           JLabel4.setText(""+ErrorsNeuralNets[4]*100);
           JLabel5.setText(""+ErrorsNeuralNets[5]*100);
           JLabel6.setText(""+ErrorsNeuralNets[6]*100);
           JLabel7.setText(""+ErrorsNeuralNets[7]*100);
           JLabel8.setText(""+ErrorsNeuralNets[8]*100);
           JLabel9.setText(""+ErrorsNeuralNets[9]*100);
           
        }
         JProgressBar.setValue(100);
        
        
        
        
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
