/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praca.magisterska;

import NeuralNetPackage.FunkcjaAktywacji;
import NeuralNetPackage.GeneratedNewNeuralNet;
import NeuralNetPackage.Numbers;
import NeuralNetPackage.SiećNeuronowa;
import NewWidnows.Warning;
import NumericTasks.ArrayUtils;
import static NumericTasks.ArrayUtils.ChooseOneRadiobutton;
import NumericTasks.CreateSamples;
import NumericTasks.HaarCascadeTraining;
import NumericTasks.MarkedRect;
import NumericTasks.Picture;
import NumericTasks.MLTraining;
import NumericTasks.TestPictures;
import NumericTasks.TrainingPictures;
import NumericTasks.TypeOfThreshold;
import static NumericTasks.TypeOfThreshold.BRAK_PROGROWANIA;
import static NumericTasks.TypeOfThreshold.EFEKT_PRZYCIEMNAJACY;
import static NumericTasks.TypeOfThreshold.EFEKT_ROZJASNIAJACY;
import static NumericTasks.TypeOfThreshold.PROGOWANIE;
import NumericTasks.ViewPicture;
import NumericTasks.WriteToFile;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.String.format;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author Quchi
 */
public class FXMLDocumentController implements Initializable {
    Parent root;
    FXMLDetectedRunnersController FXMLDetectedRunnersController;
    TrainingNeuralNetController TrainingNeuralNetController;
    TypeOfDetect type;
    String pobranaścieszka;
    ArrayList<BufferedImage> DetectedRunners;
    public int widthOriginImage;
    public int heightOriginImage;
    Numbers NumbersNeuralNet;
    private final Image rootIcon = new Image("file:icontitle//neuralnet.jpg"); 
    private final Image LayerIcon = new Image("file:icontitle//layers.png");
    private final Image NeuronIcon = new Image("file:icontitle//neuron.png");
    
    
    //Ustawienia sieci neuronowej
    
    //Nowa siec
    @FXML
    public Button CreateNewFileNeural;
    @FXML 
    public Button ChoosefolderWithNumbers;
    @FXML 
    public Button Confirm1;
    @FXML 
    public TextField NormalizeWidthX;
    @FXML 
    public TextField NormalizeHeightY;
    @FXML
    public TextField NumbersOFLayers;
    @FXML 
    public TextField NumbersOfItarationCount;
    @FXML 
    public Button Confirm2;
    @FXML 
    public ComboBox ChoooseLayer;
    @FXML 
    public TextField NumbersOfNeuoronforLayer;
    @FXML 
    public Button Confirm3;
    @FXML 
    public Label PathToNeuralNet;
    @FXML 
    public Label PathToTrainingSet;
    @FXML 
    public Label NormalizeWidth;
    @FXML 
    public Label NormalizeHeight;
    @FXML
    public Label NumbersOfCount;
    @FXML
    public TreeView LayersAndNumbersOfNeuralNet;
    
    //Wczytanie sieci
    @FXML 
    public Button ReadFileWithNeuralNet;
    @FXML 
    public Button ReadFileWithPicture;
    @FXML 
    public Button RecognizeNumber;
    @FXML 
    public BarChart ChartNumbersDetect;
    @FXML
    public CategoryAxis Numbers;
    @FXML 
    public NumberAxis NumbersValues;
    @FXML      
    public ImageView ImageViewRecognizedNumber;
    @FXML
    public Label LabelRecognizedNumber;
    @FXML 
    public Label LabelFileWithNeuralNet;
    @FXML 
    public Label LabelPicWithNumber;
    @FXML 
    public Slider SliderThreszholdrecognize;
    @FXML
    public Label ValueSliderrecognize;
    final static String[] NUMBERS = {"0","1","2","3","4","5","6","7","8","9"};
   //Wykrywanie cyfr
    @FXML
    public Label FolderNewExamples;
    @FXML
    public Label ChoosePicture;
    BufferedImage PicWithNumbers;
    ArrayList <String> PathToNumbersFolder;
    
    
    @FXML
    CheckBox SetNegative;
    //Ustawianie klasyfikatora
    
    @FXML 
    Label LabelYCord;
    @FXML 
    Label LabelXCord;
    @FXML
    Canvas KlasifImageView;
    @FXML
    Label PathToCurrentImage;
    @FXML 
    Label KlasifChooseFolder;
    @FXML
    Label KlasifLabelPercent;
    @FXML 
    Label KlasifLabelLoadPic;
    @FXML
    ProgressBar KlasifProgressbarLoadImages;
    @FXML 
    TextField KlasifWidth;
    @FXML
    TextField KlasifHeight;        
    @FXML 
    CheckBox KlasifChakcboxOriginal;
    @FXML
    Label klassifFolderOutSave;
    
    
    ArrayList <String> PathToImages = new ArrayList<>();
    ArrayList <TrainingPictures> LoadedImage = new ArrayList<>();
    double MousePressX;
    double MousePressY;
    double MouseReleaseX;
    double MouseReleaseY;
    ArrayList <Rectangle> MarketExamples = new ArrayList<>();
    int CurrentKlasifImage = 0 ;
    GraphicsContext gc;
    
    
    //dla zakładki tworzenie klasyfikatora i pobranie próbek 
    
    @FXML
    Label KlasLabelFileXML;
    @FXML
    ProgressBar ProgressBarTrainingCassifier;
    @FXML
    Label KlassifTrainingPercent;
    @FXML 
    Label KlasssifTrainingTitle;
    @FXML
    Label KlassifPosPath;
    @FXML
    Label KlassifNegPath;
    @FXML
    Label KlassifChooseTestFile;
    @FXML
    TextField KlasifPosSizeX;
    @FXML
    TextField KlasifPosSizeY;
    @FXML
    TextField KlasifNegSizeX;
    @FXML
    TextField KlasifNegSizeY;
    
    @FXML 
    Label KlassifChooseFilesSelectedAreas;
    
    @FXML 
    CheckBox KlassifSaveChangedSizePic;
    
    @FXML
    public void KlassifLoadFileSelectedAreas(){
            Stage stage = new Stage();
            FileChooserFile FileChooserFile = new FileChooserFile(KlassifChooseFilesSelectedAreas.getText(),true,"txt","ListaObrazówiObiektów");
            FileChooserFile.start(stage);
            KlassifChooseFilesSelectedAreas.setText(FileChooserFile.Sciezka);
            
    }
    
    @FXML 
    public void KlassifSaveSelectedAreasFiles(){
        try {
            new WriteToFile(KlassifChooseFilesSelectedAreas.getText(),LoadedImage).Wrtite();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //testowanie sieci neuronowej
    @FXML 
    Label FolderWithTestImages;
    @FXML
    Label FileWithNeuralNet;
    @FXML
    Label LabelProgressReadFileTest;
    @FXML
    ProgressBar ProgressFileRead;
    @FXML 
    Label TitleLoadTest;
    @FXML
    Label LabelAccuaracyRecognizeNumber;
    @FXML 
    Label ReadedNumberFromFile;
    @FXML
    Label RecognizedNumberFromImage;
    @FXML
    ImageView ViewCurrentNumber;
    @FXML
    Label CorrectNumber;
    @FXML
    Label RecognizeNumberTest;
    @FXML
    Label AccuracyOFRecognizing;
    
    @FXML
    Label CurrentValueRed;
    @FXML
    Slider SliderCurrentValueRed;
    @FXML
    public void ChangeValueRed(){
       CurrentValueRed.setText(format("%.2f",SliderCurrentValueRed.getValue()));
       if(Test==null){
       }
       else{
         changeValuesOfPicture();
       }
       
    }
    
    
    @FXML
    Label CurrentValueGreen;
    @FXML
    Slider SliderCurrentValueGreen;
    @FXML
    public void ChangeValueGreen(){
        CurrentValueGreen.setText(format("%.2f",SliderCurrentValueGreen.getValue())); 
        if(Test==null){
       }
       else{
         changeValuesOfPicture();
       }
    }
    
    
    @FXML
    Label CurrentValueBlue;
    @FXML
    Slider SliderCurrentValueBlue;
    @FXML
    public void ChangeValueBlue(){
        CurrentValueBlue.setText(format("%.2f",SliderCurrentValueBlue.getValue())); 
        if(Test==null){
       }
       else{
         changeValuesOfPicture();
       }
    }
    
    
    
    @FXML
    Label CurrentValueBrightness;
    @FXML
    Slider SliderCurrentValueBrightness;
    @FXML
    public void ChangeValueBrightness(){
         CurrentValueBrightness.setText(format("%.2f",SliderCurrentValueBrightness.getValue())); 
           if(Test==null){
       }
       else{
         changeValuesOfPicture();
       }
    }
    
    @FXML
    Label CurrentValueContrast;
    @FXML
    Slider SliderCurrentValueContrast;
    @FXML
    public void ChangeValueContrast(){
      CurrentValueContrast.setText(format("%.2f",SliderCurrentValueContrast.getValue()/1000)); 
         if(Test==null){
       }
       else{
         changeValuesOfPicture();
       }
    }
    
    
    
    @FXML
    Label CurrentValueThreshold;
    @FXML
    Slider SliderCurrentValueThreshold;
    @FXML
    public void ChangeValueThreshold(){
              CurrentValueThreshold.setText(format("%.2f",SliderCurrentValueThreshold.getValue())); 
       if(Test==null){
       }
       else{
         changeValuesOfPicture();
       }
    }
    
    
    
    @FXML
    Label CurrentValueBlurr;
    @FXML
    Slider SliderCurrentValueBlurr;
    @FXML
    public void ChangeValueBlurr(){
        CurrentValueBlurr.setText(format("%.2f",SliderCurrentValueBlurr.getValue()));
        if(Test==null){
       }
       else{
         changeValuesOfPicture();
       }
    }
    
    @FXML
    Label CurrentValueThresholdRecognize;
    @FXML
    Slider SliderCurrentThresholdRecognize;
    @FXML
    public void ChangeValueThresholdRecognize(){
        CurrentValueThresholdRecognize.setText(format("%.2f",SliderCurrentThresholdRecognize.getValue()));
        if(Test==null){
       }
       else{
         changeValuesOfPicture();
       }
    }
    
    
    @FXML
    CheckBox UseNegative;
    @FXML 
    ComboBox ThresholdType;
    @FXML
    public void ReloadTestImages(){
        if(Test==null){
            
        }
        else{
            Test.Reload();
        }
        
    }
    
    TestPictures Test; 
    @FXML
    public void ChooseFolderWithTestImages(){
        Stage stage = new Stage();
        FolderChooser FolderChooser = new FolderChooser(FolderWithTestImages.getText());
        FolderChooser.start(stage);
        FolderWithTestImages.setText(FolderChooser.getPobranaŚciezka());
        
    }
    
    @FXML
    public void ChooseFileWithNeuralNetTest(){
        try {
            Stage stage = new Stage();
            FileChooserFile FileChooserFile = new FileChooserFile(FileWithNeuralNet.getText(),false);
            FileChooserFile.start(stage);
            FileWithNeuralNet.setText(FileChooserFile.Sciezka);
            NumbersNeuralNet = new Numbers(FileWithNeuralNet.getText());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String Text = FileWithNeuralNet.getText();
        if(Text.length()>50){
        Text =  Text.substring(0,Text.length()/2)+"\n"+Text.substring(Text.length()/2,Text.length());
        }
        FileWithNeuralNet.setText(Text);
    }

    
    int CurrentImageTest = 0;
    
    @FXML
    public void NextTestImage(){
        if(CurrentImageTest+1<Test.NumberOfPictures()&&CurrentImageTest+1>=0){
            Image image = SwingFXUtils.toFXImage(Test.GetPicOfTestImage().get(++CurrentImageTest),null);
            ViewCurrentNumber.setImage(image);
            CorrectNumber.setText(String.valueOf(Test.GetCorrectNumbers().get(CurrentImageTest)));
            RecognizeNumberTest.setText(String.valueOf(Test.GetRecognizedNumbers().get(CurrentImageTest)));
            changeValuesOfPicture();
        }
        
        else 
        {   
            CurrentImageTest=0;
            Image image = SwingFXUtils.toFXImage(Test.GetPicOfTestImage().get(CurrentImageTest),null);
            ViewCurrentNumber.setImage(image);
            CorrectNumber.setText(String.valueOf(Test.GetCorrectNumbers().get(CurrentImageTest)));
            RecognizeNumberTest.setText(String.valueOf(Test.GetRecognizedNumbers().get(CurrentImageTest)));
            changeValuesOfPicture();       
        }
    }
    
    @FXML
    public void PreviousTestImage(){
        if(CurrentImageTest-1<Test.NumberOfPictures()&&CurrentImageTest-1>=0){
            Image image = SwingFXUtils.toFXImage(Test.GetPicOfTestImage().get(--CurrentImageTest),null);
            ViewCurrentNumber.setImage(image);
            CorrectNumber.setText(String.valueOf(Test.GetCorrectNumbers().get(CurrentImageTest)));
            RecognizeNumberTest.setText(String.valueOf(Test.GetRecognizedNumbers().get(CurrentImageTest)));
            changeValuesOfPicture();        
        }
        else 
        {   
            CurrentImageTest=Test.NumberOfPictures()-1;
            Image image = SwingFXUtils.toFXImage(Test.GetPicOfTestImage().get(CurrentImageTest),null);
            ViewCurrentNumber.setImage(image);
            CorrectNumber.setText(String.valueOf(Test.GetCorrectNumbers().get(CurrentImageTest)));
            RecognizeNumberTest.setText(String.valueOf(Test.GetRecognizedNumbers().get(CurrentImageTest)));
            changeValuesOfPicture();
        }
    
    }
    
    @FXML
    private void changeValuesOfPicture() {
      ViewPicture Pic = new ViewPicture(new Picture().setPicture(Test.GetPicOfTestImage().get(CurrentImageTest),Test.RecognizeTool.sizeX,Test.RecognizeTool.sizeY));
            int[] pixels = Pic.ModyfikujKoloryWKanaleRGB(
             SliderCurrentValueRed.getValue()+SliderCurrentValueBrightness.getValue(),
             SliderCurrentValueGreen.getValue()+SliderCurrentValueBrightness.getValue(),
             SliderCurrentValueBlue.getValue()+SliderCurrentValueBrightness.getValue(),
             SliderCurrentValueContrast.getValue()/1000,TypeOfThreshold,
             (int) SliderCurrentValueThreshold.getValue());
            Picture Out = new Picture(pixels,Pic.pobierzX(),Pic.pobierzY());
            
            if(UseNegative.isSelected()){
               Out.setImage(Out.Negative(Out.Image()));
            }
            
            if(SliderCurrentValueBlurr.getValue()>0.0){
                Out.setImage(Out.changeBlurr((int)SliderCurrentValueBlurr.getValue(), (int)SliderCurrentValueBlurr.getValue()));
            }
            
            
            ViewCurrentNumber.setImage(SwingFXUtils.toFXImage(Out.Image(), null));
            
            
    }
    
    public TypeOfThreshold TypeOfThreshold = BRAK_PROGROWANIA; 
    @FXML
    public void ChangeTypeOfThreshold(){
        switch(ThresholdType.getSelectionModel().getSelectedIndex())
        {
            case 0:
            TypeOfThreshold = TypeOfThreshold.BRAK_PROGROWANIA;
            changeValuesOfPicture();
            break;
            
            case 1:
            TypeOfThreshold = TypeOfThreshold.PROGOWANIE;
            changeValuesOfPicture();
            break;
            
            case 2:
            TypeOfThreshold = TypeOfThreshold.EFEKT_ROZJASNIAJACY;   
            changeValuesOfPicture();
            break;
            
            case 3:
            TypeOfThreshold = TypeOfThreshold.EFEKT_PRZYCIEMNAJACY;   
            changeValuesOfPicture();
            break;
        }
    }
    
    ArrayList<Integer> Correct;
    ArrayList<Integer> Recognized;
    
    @FXML
    public void BeginTest(){
        CurrentImageTest = 0;
        Test = new TestPictures(FolderWithTestImages.getText(),NumbersNeuralNet,ProgressFileRead
                ,LabelProgressReadFileTest,LabelProgressReadFileTest);
        Test.SetParametersPicture(
                SliderCurrentValueRed.getValue()+SliderCurrentValueBrightness.getValue(),
                SliderCurrentValueGreen.getValue()+SliderCurrentValueBrightness.getValue(), 
                SliderCurrentValueBlue.getValue()+SliderCurrentValueBrightness.getValue(), 
                SliderCurrentValueContrast.getValue(),TypeOfThreshold, (int) SliderCurrentValueThreshold.getValue(), UseNegative.isSelected(),
                SliderCurrentValueBlurr.getValue(), (int) SliderCurrentThresholdRecognize.getValue(),AccuracyOFRecognizing,
                ViewCurrentNumber,RecognizeNumberTest,CorrectNumber);
        new Thread(()->Test.run()).start();
        
    }
    
    @FXML
    public void KlasifChooseFileTestPath(){
        Stage stage = new Stage();
        FileChooserSample FileChooserFile = new FileChooserSample(KlassifChooseTestFile.getText());
        FileChooserFile.start(stage);
        KlassifChooseTestFile.setText(FileChooserFile.Sciezka);
    }
    
    @FXML
    public void KlasifChooseFolderPosPath(){
        Stage stage = new Stage();
        FolderChooser FolderChooser = new FolderChooser(KlassifPosPath.getText());
        FolderChooser.start(stage);
        KlassifPosPath.setText(FolderChooser.getPobranaŚciezka());
    }
    
    @FXML
    public void KlasifChooseFolderNegPath(){
        Stage stage = new Stage();
        FolderChooser FolderChooser = new FolderChooser(KlassifNegPath.getText());
        FolderChooser.start(stage);
        KlassifNegPath.setText(FolderChooser.getPobranaŚciezka());
    }
    
    public void KlasifSaveChoosenFragments() throws IOException{
        
       new Thread (()->{
            if(ArrayUtils.isNumeric(KlasifWidth.getText())&&ArrayUtils.isNumeric(KlasifHeight.getText())&&!KlasifChakcboxOriginal.isSelected())
                    {
                        Platform.runLater(() -> {  
                        KlasifLabelPercent.setVisible(true);
                        KlasifLabelLoadPic.setVisible(true);
                        KlasifProgressbarLoadImages.setVisible(true);
                        });
                        int size = LoadedImage.size();
                        
                        for(int i = 0 ;i<size;i++)
                            {   

                             
                              final double percent = (double)i/size;
                              
                              Platform.runLater(() -> {
                              KlasifProgressbarLoadImages.setProgress(percent);
                              KlasifLabelPercent.setText(format("%.2f",percent*100.0)+" %");
                              });
                             
                            }
                              Platform.runLater(() -> {
                              KlasifProgressbarLoadImages.setProgress(1);
                              KlasifLabelPercent.setText(format("%.2f",1*100.0)+" %");
                              }); 
                              
                               
                        
                             Platform.runLater(() -> {
                              KlasifProgressbarLoadImages.setProgress(0);
                              KlasifLabelPercent.setText(format("%.2f",0*100.0)+" %");
                              KlasifLabelLoadPic.setText("Zapis fragmentów do folderu:");
                              }); 
                             
                             
                             
                             
                             
                             int sizeSave = LoadedImage.size();
                                    for(int currentImage = 0 ; currentImage< sizeSave;currentImage++){
                                       
                                        if(KlassifSaveChangedSizePic.isSelected()){
                                            File outputfile = new File(klassifFolderOutSave.getText()+"\\"+LoadedImage.get(currentImage).PathToImage().substring(LoadedImage.get(currentImage).PathToImage().lastIndexOf("\\")+2));
                                            try {
                                                ImageIO.write(new Picture().setPicture(ImageIO.read(new File(LoadedImage.get(currentImage).PathToImage())), Integer.valueOf(KlasifWidth.getText()), Integer.valueOf(KlasifHeight.getText())),"jpg",outputfile);
                                            } catch (IOException ex) {
                                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                        else{
                                            int sizeExample = LoadedImage.get(currentImage).GetMarkedRect().size();
                                        for(int currentExample = 0 ; currentExample<sizeExample;currentExample++)
                                                       {
                                                        LoadedImage.get(currentImage).GetMarkedRect().get(currentExample);
                                                        File outputfile = new File(klassifFolderOutSave.getText()+"\\"+currentExample+LoadedImage.get(currentImage).PathToImage().substring(LoadedImage.get(currentImage).PathToImage().lastIndexOf("\\")+2));
                                                        try {
                                                           ImageIO.write(new Picture().PobierzWycinekObrazu(
                                                                   new Picture().setPicture(ImageIO.read(new File(LoadedImage.get(currentImage).PathToImage())), Integer.valueOf(KlasifWidth.getText()), Integer.valueOf(KlasifHeight.getText())),
                                                                   LoadedImage.get(currentImage).GetMarkedRect().get(currentExample).x,
                                                                   LoadedImage.get(currentImage).GetMarkedRect().get(currentExample).y,
                                                                   LoadedImage.get(currentImage).GetMarkedRect().get(currentExample).width,
                                                                   LoadedImage.get(currentImage).GetMarkedRect().get(currentExample).height
                                                           ), "jpg", outputfile);
                                                            } catch (IOException ex) {
                                                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                                                }
                                                        
                                                        
                                                            final double percent = (double)currentExample/sizeExample;
                              
                                                            Platform.runLater(() -> {
                                                            KlasifProgressbarLoadImages.setProgress(percent);
                                                            KlasifLabelPercent.setText(format("%.2f",percent*100.0)+" %");
                                                            });
                                                       }
                                        }
                                        
                                        
                                        
                                        
                                                    final double percent = (double)currentImage/sizeSave;
                              
                                                    Platform.runLater(() -> {
                                                    KlasifProgressbarLoadImages.setProgress(percent);
                                                    KlasifLabelPercent.setText(format("%.2f",percent*100.0)+" %");
                                                    });
                 
                                             }
                                    
                                    
                                    Platform.runLater(() -> {
                                                    KlasifProgressbarLoadImages.setProgress(1);
                                                    KlasifLabelPercent.setText(format("%.2f",1*100.0)+" %");
                                                    });
                                    
                        Platform.runLater(() -> {  
                                KlasifLabelPercent.setVisible(false);
                                KlasifLabelLoadPic.setVisible(false);
                                KlasifProgressbarLoadImages.setVisible(false);
                        });
                    
                    }
            
            else if(KlasifChakcboxOriginal.isSelected()){
                        
                        Platform.runLater(() -> {  
                                KlasifLabelPercent.setVisible(true);
                                KlasifLabelLoadPic.setVisible(true);
                                KlasifProgressbarLoadImages.setVisible(true);
                        });
                          
                          
                         
                          int size =  PathToImages.size();
                          for(int i = 0 ;i<size;i++)
                            {   

                             
                              final double percent = (double)i/size;
                              
                              Platform.runLater(() -> {
                              KlasifProgressbarLoadImages.setProgress(percent);
                              KlasifLabelPercent.setText(format("%.2f",percent*100.0)+" %");
                              });
                             
                              }
                         
                              Platform.runLater(() -> {
                              KlasifProgressbarLoadImages.setProgress(1);
                              KlasifLabelPercent.setText(format("%.2f",1*100.0)+" %");
                              });  
                              
                              
                             Platform.runLater(() -> {
                              KlasifProgressbarLoadImages.setProgress(0);
                              KlasifLabelPercent.setText(format("%.2f",0*100.0)+" %");
                              KlasifLabelLoadPic.setText("Zapis fragmentów do folderu:");
                              }); 
                             
                             
                             
                             int sizeSave = LoadedImage.size();
                                    for(int currentImage = 0 ; currentImage< sizeSave;currentImage++){
                                       if(KlassifSaveChangedSizePic.isSelected()){
                                            File outputfile = new File(klassifFolderOutSave.getText()+"\\"+LoadedImage.get(currentImage).PathToImage().substring(LoadedImage.get(currentImage).PathToImage().lastIndexOf("\\")+2));
                                            try {
                                                ImageIO.write(new Picture().setPicture(ImageIO.read(new File(LoadedImage.get(currentImage).PathToImage())), Integer.valueOf(KlasifWidth.getText()), Integer.valueOf(KlasifHeight.getText())),"jpg",outputfile);
                                            } catch (IOException ex) {
                                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                        else{
                                            int sizeExample = LoadedImage.get(currentImage).GetMarkedRect().size();
                                        for(int currentExample = 0 ; currentExample<sizeExample;currentExample++){
                                            
                                            File outputfile = new File(klassifFolderOutSave.getText()+"\\"+currentExample+LoadedImage.get(currentImage).PathToImage().substring(LoadedImage.get(currentImage).PathToImage().lastIndexOf("\\")+2));
                                                        try {
                                                            
                                                            
                                                            ImageIO.write(new Picture().PobierzWycinekObrazu(

                                                                    ImageIO.read(new File(LoadedImage.get(currentImage).PathToImage())),
                                                                    LoadedImage.get(currentImage).GetMarkedRect().get(currentExample).x,
                                                                    LoadedImage.get(currentImage).GetMarkedRect().get(currentExample).y,
                                                                    LoadedImage.get(currentImage).GetMarkedRect().get(currentExample).width,
                                                                    LoadedImage.get(currentImage).GetMarkedRect().get(currentExample).height
                                                            ), "jpg", outputfile);
                                                            } 
                                                        catch (IOException ex) 
                                                            {
                                                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                                            }
                                                        catch(IllegalArgumentException | NullPointerException ex){
                                                            continue;
                                                            }
                                                        
                                                        final double percent = (double)currentExample/sizeExample;
                              
                                                            Platform.runLater(() -> {
                                                            KlasifProgressbarLoadImages.setProgress(percent);
                                                            KlasifLabelPercent.setText(format("%.2f",percent*100.0)+" %");
                                                            });
                                                        
                                                        

                                                  }
                                        } 
                                        
                                        
                                        
                                        
                                        
                                                    final double percent = (double)currentImage/sizeSave;
                              
                                                    Platform.runLater(() -> {
                                                    KlasifProgressbarLoadImages.setProgress(percent);
                                                    KlasifLabelPercent.setText(format("%.2f",percent*100.0)+" %");
                                                    });
                                        
                                                 
                                             }
                                    Platform.runLater(() -> {
                                                    KlasifProgressbarLoadImages.setProgress(1);
                                                    KlasifLabelPercent.setText(format("%.2f",1*100.0)+" %");
                                                    });
                             
                             Platform.runLater(() -> {  
                                KlasifLabelPercent.setVisible(false);
                                KlasifLabelLoadPic.setVisible(false);
                                KlasifProgressbarLoadImages.setVisible(false);
                        });
                    }
            
            
            
            
            
                    else
                    {
                        new Warning(false,"Wprowadź liczbę całkowitą !").setVisible(true);
                    } 
       
       
       }).start();
       
       
       
       
       
    }
    
    @FXML
    public void KlasidChooseFolderOut(){
        Stage stage = new Stage();
        FolderChooser FolderChooser = new FolderChooser(klassifFolderOutSave.getText());
        FolderChooser.start(stage);
        klassifFolderOutSave.setText(FolderChooser.getPobranaŚciezka());
        
    }
    
    @FXML
    public void KlasifChooseFile(){
        Stage stage = new Stage();
        FileChooserFile FileChooserFile = new FileChooserFile(KlasLabelFileXML.getText(),true,"xml");
        FileChooserFile.start(stage);
        KlasLabelFileXML.setText(FileChooserFile.Sciezka);
    }
    
    @FXML
    public void KlasifDeleteLastRect(){
        LoadedImage.get(CurrentKlasifImage).deleteLastRect();
        gc.clearRect(0, 0,gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        gc.drawImage(SwingFXUtils.toFXImage(LoadedImage.get(CurrentKlasifImage).GetImage(), null), 0, 0);
        DrawSetOfMarkedRect(LoadedImage.get(CurrentKlasifImage));
    }
    
    @FXML 
    public void KlasifMouseClickStartRect(MouseEvent event){
     
     MouseReleaseX=0;    
     MouseReleaseY=0;    
     double H = LoadedImage.get(CurrentKlasifImage).getHeight();
     double W = LoadedImage.get(CurrentKlasifImage).getWidth();
     
     int HeightImg = LoadedImage.get(CurrentKlasifImage).GetImage().getHeight();
     int WidthImg = LoadedImage.get(CurrentKlasifImage).GetImage().getWidth();
     MousePressX = event.getX();
     MousePressY = event.getY();
     double x1 = MousePressX/WidthImg*W;
     double y1 = MousePressY/HeightImg*H;
     
     LabelXCord.setText(" "+format("%.2f",x1)+" "+format("%.2f",MouseReleaseX));
     LabelYCord.setText(" "+format("%.2f",y1)+" "+format("%.2f",MouseReleaseY));
     
    }
    
    @FXML 
    public void KlasifMouseClickEndRect(MouseEvent event){
     double H = LoadedImage.get(CurrentKlasifImage).getHeight();
     double W = LoadedImage.get(CurrentKlasifImage).getWidth();   
     MouseReleaseX = event.getX();
     MouseReleaseY = event.getY();
     int HeightImg = LoadedImage.get(CurrentKlasifImage).GetImage().getHeight();
     int WidthImg = LoadedImage.get(CurrentKlasifImage).GetImage().getWidth();
     
     double x1 = MousePressX/WidthImg*W;
     double y1 = MousePressY/HeightImg*H;
     double x2 = MouseReleaseX/WidthImg*W;
     double y2 = MouseReleaseY/HeightImg*H;
     
     
     LabelXCord.setText(" "+format("%.2f",x1)+" "+format("%.2f",x2));
     LabelYCord.setText(" "+format("%.2f",y1)+" "+format("%.2f",y2));
     
     LoadedImage.get(CurrentKlasifImage).AddNewRect(new MarkedRect((int)x1,(int)y1,(int)(y2-y1),(int)(x2-x1)));
    }
    
    public void DrawSetOfMarkedRect(TrainingPictures pic){
            double H = LoadedImage.get(CurrentKlasifImage).getHeight();
            double W = LoadedImage.get(CurrentKlasifImage).getWidth();
            double WindowW = LoadedImage.get(CurrentKlasifImage).GetImage().getWidth();
            double WindowH = LoadedImage.get(CurrentKlasifImage).GetImage().getHeight();
       int size = pic.GetMarkedRect().size();
        for(int i = 0 ; i<size;i++){
            gc.strokeRect(
            pic.GetMarkedRect().get(i).x*(WindowW/W),  
            pic.GetMarkedRect().get(i).y*(WindowH/H),
            pic.GetMarkedRect().get(i).width*(WindowW/W), 
            pic.GetMarkedRect().get(i).height*(WindowH/H));
        }
         
    }
    
    @FXML
    public void KlasifMauseCoordinates(MouseEvent event){
        double H = LoadedImage.get(CurrentKlasifImage).getHeight();
        double W = LoadedImage.get(CurrentKlasifImage).getWidth(); 
        
        int HeightImg = LoadedImage.get(CurrentKlasifImage).GetImage().getHeight();
        int WidthImg = LoadedImage.get(CurrentKlasifImage).GetImage().getWidth();
        
        
        double x1 = MousePressX/WidthImg*W;
        double y1 = MousePressY/HeightImg*H;
        double x2 = event.getX()/WidthImg*W;
        double y2 = event.getY()/HeightImg*H;
     
       LabelXCord.setText(" "+format("%.2f",x1)+" "+format("%.2f",x2));
       LabelYCord.setText(" "+format("%.2f",y1)+" "+format("%.2f",y2));
       gc.clearRect(0, 0,gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
       gc.drawImage(SwingFXUtils.toFXImage(LoadedImage.get(CurrentKlasifImage).GetImage(), null), 0, 0);
       DrawSetOfMarkedRect(LoadedImage.get(CurrentKlasifImage));
       gc.strokeRect(MousePressX,MousePressY, event.getX()-MousePressX, event.getY()-MousePressY);
       
    }
    
    
    @FXML 
    public void KlasifChooseFolder(){
        Stage stage = new Stage();
        FolderChooser FolderChooser = new FolderChooser(KlasifChooseFolder.getText());
        FolderChooser.start(stage);
        KlasifChooseFolder.setText(FolderChooser.getPobranaŚciezka());
        PathToImages.clear();
        try 
            { 
                PathToImages = new ArrayUtils().ListaPlikówWFolderze(KlasifChooseFolder.getText());
            } 
        catch (IOException ex) 
            {
                KlasifChooseFolder.setText("Błąd podczas ładowania pliku");
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }
    
    @FXML
    public void KlasifNextImage(){
        
        if(CurrentKlasifImage+1<LoadedImage.size()&&CurrentKlasifImage+1>=0){
            Image image = SwingFXUtils.toFXImage(LoadedImage.get(++CurrentKlasifImage).GetImage(),null);
            gc.clearRect(0, 0,gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
            gc.drawImage(image, 0, 0);
            DrawSetOfMarkedRect(LoadedImage.get(CurrentKlasifImage));
            PathToCurrentImage.setText("Obraz nr: "+(CurrentKlasifImage+1)+" spośród: "+LoadedImage.size()+" "+PathToImages.get(CurrentKlasifImage));
            
        }
        
        else 
        {   
            CurrentKlasifImage=0;
            Image image = SwingFXUtils.toFXImage(LoadedImage.get(CurrentKlasifImage).GetImage(),null);
            gc.clearRect(0, 0,gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
            gc.drawImage(image, 0, 0);
            DrawSetOfMarkedRect(LoadedImage.get(CurrentKlasifImage));
            PathToCurrentImage.setText("Obraz nr: "+(CurrentKlasifImage+1)+" spośród: "+LoadedImage.size()+" "+PathToImages.get(CurrentKlasifImage));
            
           
        }
    }
    
    @FXML
    @SuppressWarnings("empty-statement")
    public void KlasifPreviousImage(){
     if(CurrentKlasifImage-1<LoadedImage.size()&&CurrentKlasifImage-1>=0){
         
            Image image = SwingFXUtils.toFXImage(LoadedImage.get(--CurrentKlasifImage).GetImage(),null);
            gc.clearRect(0, 0,gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
            gc.drawImage(image, 0, 0);
            DrawSetOfMarkedRect(LoadedImage.get(CurrentKlasifImage));
            PathToCurrentImage.setText("Obraz nr: "+(CurrentKlasifImage+1)+" spośród: "+LoadedImage.size()+" "+PathToImages.get(CurrentKlasifImage));
            
        }
        else 
        {   
            CurrentKlasifImage=LoadedImage.size()-1;
            Image image = SwingFXUtils.toFXImage(LoadedImage.get(CurrentKlasifImage).GetImage(),null);
            gc.clearRect(0, 0,gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
            gc.drawImage(image, 0, 0);
            DrawSetOfMarkedRect(LoadedImage.get(CurrentKlasifImage));
            PathToCurrentImage.setText("Obraz nr: "+(CurrentKlasifImage+1)+" spośród: "+LoadedImage.size()+" "+PathToImages.get(CurrentKlasifImage));
        }
    }
    
    @FXML
    public void KlasifSaveCurrentSettings(){
     

    }
    
    @FXML
    public void KlasifLoadImages() {
        
             new Thread (()->{
              
              if(ArrayUtils.isNumeric(KlasifWidth.getText())&&ArrayUtils.isNumeric(KlasifHeight.getText())&&!KlasifChakcboxOriginal.isSelected())
                    {  
                        Platform.runLater(() -> {  
                        KlasifLabelPercent.setVisible(true);
                        KlasifLabelLoadPic.setVisible(true);
                        KlasifProgressbarLoadImages.setVisible(true);
                        });
                          CurrentKlasifImage=0;
                          LoadedImage.clear();
                          int size =  PathToImages.size();
                          for(int i = 0 ;i<size;i++)
                            {   
                              try 
                              {
                                BufferedImage Input = ImageIO.read(new File(PathToImages.get(i)));
                                
                                Picture pic = new Picture();
                                pic.setPicture(Input.getWidth(), Input.getHeight(), Integer.valueOf(KlasifWidth.getText()), Integer.valueOf(KlasifHeight.getText()));
                                LoadedImage.add(new TrainingPictures(
                                        new Picture().setPicture(Input, (int)KlasifImageView.getWidth(), (int) KlasifImageView.getHeight()),
                                        PathToImages.get(i), (int) pic.ProcentXWzględemOryginału, (int) pic.ProcentYWzględemOryginału));
                              } catch (IOException ex) {
                                 Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                              }
                             
                              final double percent = (double)i/size;
                              
                              Platform.runLater(() -> {
                              KlasifProgressbarLoadImages.setProgress(percent);
                              KlasifLabelPercent.setText(format("%.2f",percent*100.0)+" %");
                              });
                             
                            }
                          
                              Platform.runLater(() -> {
                              KlasifProgressbarLoadImages.setProgress(1);
                              KlasifLabelPercent.setText(format("%.2f",1*100.0)+" %");
                              });  
                          
                          
                            Platform.runLater(() -> {
                                
                           // KlasifImageView.setFitHeight(size);
                            Image image = SwingFXUtils.toFXImage(LoadedImage.get(CurrentKlasifImage).GetImage(),null);
                            gc.drawImage(image,0,0);
                            PathToCurrentImage.setText("Obraz nr: "+(CurrentKlasifImage+1)+" spośród: "+LoadedImage.size()+" "+PathToImages.get(CurrentKlasifImage));
                            });
                        
                            Platform.runLater(() -> {  
                            KlasifLabelPercent.setVisible(false);
                            KlasifLabelLoadPic.setVisible(false);
                            KlasifProgressbarLoadImages.setVisible(false);
                            });
                        
                             Platform.runLater(() -> {
                              KlasifProgressbarLoadImages.setProgress(0);
                              KlasifLabelPercent.setText(format("%.2f",0*100.0)+" %");
                              });  
                    }
              
                    else if(KlasifChakcboxOriginal.isSelected()){
                        
                        Platform.runLater(() -> {  
                                KlasifLabelPercent.setVisible(true);
                                KlasifLabelLoadPic.setVisible(true);
                                KlasifProgressbarLoadImages.setVisible(true);
                        });
                          CurrentKlasifImage=0;
                          
                          LoadedImage.clear();
                          int size =  PathToImages.size();
                          for(int i = 0 ;i<size;i++)
                            {   
                              try {
                                    BufferedImage Input = ImageIO.read(new File(PathToImages.get(i)));   
                                    LoadedImage.add(new TrainingPictures(new Picture().setPicture(Input, (int)KlasifImageView.getWidth(),(int)KlasifImageView.getHeight()),PathToImages.get(i),Input.getWidth(),Input.getHeight()));
                                   } 
                              catch (IOException ex) 
                              {
                                 Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                              }
                             
                              final double percent = (double)i/size;
                              
                              Platform.runLater(() -> {
                              KlasifProgressbarLoadImages.setProgress(percent);
                              KlasifLabelPercent.setText(format("%.2f",percent*100.0)+" %");
                              });
                             
                            }
                         
                              Platform.runLater(() -> {
                              KlasifProgressbarLoadImages.setProgress(1);
                              KlasifLabelPercent.setText(format("%.2f",1*100.0)+" %");
                              });  
                          
                          
                            Platform.runLater(() -> { 
                                    Image image = SwingFXUtils.toFXImage(LoadedImage.get(CurrentKlasifImage).GetImage(), null);
                                    gc.clearRect(0, 0,gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
                                    gc.drawImage(image, 0, 0);
                                    DrawSetOfMarkedRect(LoadedImage.get(CurrentKlasifImage));
                                    PathToCurrentImage.setText("Obraz nr: "+(CurrentKlasifImage+1)+" spośród: "+LoadedImage.size()+" "+PathToImages.get(CurrentKlasifImage));
                                    
                            });
                        
                            Platform.runLater(() -> {  
                            KlasifLabelPercent.setVisible(false);
                            KlasifLabelLoadPic.setVisible(false);
                            KlasifProgressbarLoadImages.setVisible(false);
                            });
                        
                             Platform.runLater(() -> {
                              KlasifProgressbarLoadImages.setProgress(0);
                              KlasifLabelPercent.setText(format("%.2f",0*100.0)+" %");
                              });  
                    }
                    else
                    {
                        new Warning(false,"Wprowadź liczbę całkowitą !").setVisible(true);
                    } 
              
                 
              }).start();
            
          }
    
    @FXML
    public void KlasifTrainingClassifier(){
        
        
        
      MLTraining train = new MLTraining(KlassifPosPath.getText(),KlassifNegPath.getText(),KlasLabelFileXML.getText(),
              KlassifChooseTestFile.getText(),KlassifTrainingPercent,KlasssifTrainingTitle,ProgressBarTrainingCassifier,
              Integer.valueOf(KlasifPosSizeX.getText()),Integer.valueOf(KlasifPosSizeY.getText()),Integer.valueOf(KlasifNegSizeX.getText()),
              Integer.valueOf(KlasifNegSizeY.getText()));
      
      new Thread(()->train.run()).start();
        
    }
    
    
    
    @FXML
    public void LoadFolderWithNumbers(){
        Stage stage = new Stage();
        FolderChooser FolderChooser = new FolderChooser(ChoosePicture.getText());
        FolderChooser.start(stage);
        ChoosePicture.setText(FolderChooser.getPobranaŚciezka());
    
        try {
          
            PathToNumbersFolder = new ArrayUtils().ListaPlikówWFolderze(ChoosePicture.getText());
         
        } catch (IOException ex) 
        {
            ChoosePicture.setText("Błąd podczas ładowania pliku");
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    @FXML
    public void detectNumbersformImage() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDetectedRunners.fxml"));
        Parent root = null;
        try 
        {
          root = (Parent) loader.load();
        }
        catch (IOException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image i =  new Image("file:title_icon.png");
        FXMLDetectedRunnersController = loader.getController();
        Scene scene = new Scene(root);
        Stage StagePic = new Stage();
        StagePic.setScene(scene);
        StagePic.getIcons().add(i);
        StagePic.setTitle("Ustawienia potrzebne do rozpoznania liczby");
        
        
        ArrayList<BufferedImage> one = new ArrayList<>();
        for(int current = 0 ; current < PathToNumbersFolder.size();current++){
            one.add(ImageIO.read(new File(PathToNumbersFolder.get(current))));
        }
        
        Image Pic = SwingFXUtils.toFXImage(one.get(0), null);
        FXMLDetectedRunnersController.ImageView.setImage(Pic);
        FXMLDetectedRunnersController.Images = one;
        FXMLDetectedRunnersController.Numbers = NumbersNeuralNet;
        FXMLDetectedRunnersController.PathToTrainingFolderExamples = FolderNewExamples.getText();
        StagePic.show();
        
        
//         Platform.runLater(() -> {
// 
//         });
    }    
    
    @FXML
    public void chooseFolderExamples(){
        Stage stage = new Stage();
        FolderChooser FolderChooser = new FolderChooser(FolderNewExamples.getText());
        FolderChooser.start(stage);
        FolderNewExamples.setText(FolderChooser.getPobranaŚciezka());
    }
    
    @FXML
    public void ChangeSliderValueSliderThreszholdrecognize(){
        ValueSliderrecognize.setText(format("%.2f",SliderThreszholdrecognize.getValue()));
    }
    @FXML
    public void ReadNeuralNetFromFile(){
            Stage stage = new Stage();
            FileChooserFile FileChooserFile = new FileChooserFile(LabelFileWithNeuralNet.getText(),false);
            FileChooserFile.start(stage); 
            LabelFileWithNeuralNet.setText(FileChooserFile.Sciezka);
        try {
                 if(LabelFileWithNeuralNet.getText()!=null){
                     NumbersNeuralNet = new Numbers(LabelFileWithNeuralNet.getText());
                 }
                 
            
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            LabelFileWithNeuralNet.setText("Błąd w odczytywaniu pliku,  bądź nie został poprawnie wybrany");
        }
    }
    @FXML
    public void  SetNegative() throws IOException{
        
        Image Image = ImageViewRecognizedNumber.getImage();
        BufferedImage image = SwingFXUtils.fromFXImage(Image, null);
        Picture pic;
        
         if(SetNegative.isSelected()){
            pic = new Picture(image);
            Image = SwingFXUtils.toFXImage(pic.Negative(pic.Image()), null);
        }
        
         
         ImageViewRecognizedNumber.setImage(Image);
    }
    
    
    
    @FXML 
    public void RecognizeNumberWithNeuralNet(){
        
  
            
       Image Image = ImageViewRecognizedNumber.getImage();
       int Rec = NumbersNeuralNet.RecognizeNumber(new Picture( SwingFXUtils.fromFXImage(Image, null)), (int) SliderThreszholdrecognize.getValue());
       LabelRecognizedNumber.setText(" "+Rec);
       
        XYChart.Series series1 = (XYChart.Series) ChartNumbersDetect.getData().get(0);
       int size =  series1.getData().size();
       for(int i = 0 ; i<size ; i++){
            XYChart.Data p =   new XYChart.Data();
           p = (XYChart.Data) series1.getData().get(i);
           p.setXValue(NUMBERS[i]);
           p.setYValue(NumbersNeuralNet.CalculatedValuesArray().get(i));
       }
    }
    
    @FXML
    public void ReadImageFromFile(){
        Stage stage = new Stage();
        FileChooserSample FileChooserSample = new FileChooserSample(LabelPicWithNumber.getText());
        FileChooserSample.start(stage);
        LabelPicWithNumber.setText(FileChooserSample.Sciezka);
        Image Pic = null;
        try {
         if(SetNegative.isSelected()){
            Picture pic = new Picture(LabelPicWithNumber.getText());
            Pic = SwingFXUtils.toFXImage(pic.Negative(pic.Image()), null);
        }
        else{
            Pic = SwingFXUtils.toFXImage(ImageIO.read(new File(LabelPicWithNumber.getText())), null);
        }   
         
        } catch (IOException ex) {
            LabelPicWithNumber.setText("Błąd podczas ładowania pliku");
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ImageViewRecognizedNumber.setImage(Pic);
    }
    
    @FXML 
    public void createFilewithNeuralNet(){
            Stage stage = new Stage();
            FileChooserFile FileChooserFile = new FileChooserFile(PathToNeuralNet.getText());
            FileChooserFile.start(stage); 
            PathToNeuralNet.setText(FileChooserFile.Sciezka);
    }
    
    
    @FXML
    public void chooseFolderWithTrainingNumbers(){
            Stage stage = new Stage();
            FolderChooser FolderChooser = new FolderChooser(PathToTrainingSet.getText());
            FolderChooser.start(stage); 
            PathToTrainingSet.setText(FolderChooser.getPobranaŚciezka());
    }
    
    
    @FXML 
    public  Label NumberOfIteration;
    
    @FXML 
    public void confirm2Values(){
        NormalizeWidth.setText(NormalizeWidthX.getText());
        NormalizeHeight.setText(NormalizeHeightY.getText());
        NumbersOfCount.setText(NumbersOFLayers.getText());
        NumberOfIteration.setText(NumbersOfItarationCount.getText());
        
        int NumbersOfLayers = Integer.valueOf(NumbersOFLayers.getText());
        if(ChoooseLayer.getItems().size()>0){
            ChoooseLayer.getItems().clear();
        }
        for(int i = 1 ; i<=NumbersOfLayers;i++)
                ChoooseLayer.getItems().add("Ustaw liczbę neuronów dla warstwy: "+i);
        
        LayersAndNumbersOfNeuralNet.setEditable(true);
        TreeItem<String> rootItem = new TreeItem<> ("Sieć Neuronowa", new ImageView(rootIcon));
        rootItem.setExpanded(true);
        for (int i = 1; i < NumbersOfLayers+1; i++) {
            TreeItem<String> item = new TreeItem<> ("Warstwa: " + i,new ImageView(LayerIcon));            
            rootItem.getChildren().add(item);
        }          
        LayersAndNumbersOfNeuralNet.setRoot(rootItem);
    }
    
    
    
    @FXML
    public void confirmNewNeuralNet(){
  
    //Tu se wpierdol walidacje danych bo jebnie, jak typy ktos zjebane wprowadzi.  
    int NumberOfInput = Integer.valueOf(NormalizeWidthX.getText())*Integer.valueOf(NormalizeHeightY.getText());
    int NumberOfLearningIteration = Integer.valueOf(NumbersOfItarationCount.getText());
    int NumberOfLayers  = Integer.valueOf(NumbersOFLayers.getText());
    int[] numberOfNeuronsforEachLayer = new int[NumberOfLayers];
    
    for(int layer = 0 ;layer<NumberOfLayers;layer++){
        TreeItem<String> item =  (TreeItem<String>) LayersAndNumbersOfNeuralNet.getRoot().getChildren().get(layer);
        String[] output = item.getChildren().get(0).getValue().split(" ");
        numberOfNeuronsforEachLayer[layer] = Integer.valueOf(output[2]);
    }
    
    
    String PathToTrainSet = PathToTrainingSet.getText();
    String PathToNewNeuralNet = PathToNeuralNet.getText();
    
    GeneratedNewNeuralNet GeneratedNewNeuralNet = new GeneratedNewNeuralNet(NumberOfInput,numberOfNeuronsforEachLayer.length,numberOfNeuronsforEachLayer); 
    NumbersNeuralNet = new Numbers(new SiećNeuronowa(GeneratedNewNeuralNet.ListaWagPoszególnychNeuronów,GeneratedNewNeuralNet.ListaUkosówPoszególnychNeuronów,FunkcjaAktywacji.SIGMOIDALNA_UNIPOLARNA)
            ,Integer.valueOf(NormalizeWidthX.getText())
            ,Integer.valueOf(NormalizeHeightY.getText()));
 
           Platform.runLater(() -> {
                   //Update UI here
                                FXMLLoader loader;
                                loader = new FXMLLoader(getClass().getResource("TrainingNeuralNet.fxml"));
                                Parent root = null;
                                
                                try {
                                  root = (Parent) loader.load();
                                } catch (IOException ex) {
                                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                }


                                TrainingNeuralNetController = loader.getController();
                                TrainingNeuralNetController.SetVariables(NumbersNeuralNet, PathToTrainSet, NumberOfLearningIteration, PathToNewNeuralNet, NumberOfLayers);
                                
                                Scene scene = new Scene(root);
                                Image i =  new Image("file:title_icon.png");
                                Stage StagePic = new Stage();
                                StagePic.getIcons().add(i);
                                StagePic.setTitle("Proces uczenia sieci neuronowej - "+PathToNewNeuralNet);
                                StagePic.setScene(scene);
                                StagePic.show();
                                
                                new Thread(TrainingNeuralNetController.Training::run).start();
                                
                                });
                                
    }
    
    @FXML
    public void confirm3values(){
         int NumberOfNeurons = Integer.valueOf(NumbersOfNeuoronforLayer.getText());
         String[] numbers = ChoooseLayer.getSelectionModel().getSelectedItem().toString().split(" ");
         int selectedLayer = Integer.valueOf(numbers[5]);
         TreeItem<String> item =  (TreeItem<String>) LayersAndNumbersOfNeuralNet.getRoot().getChildren().get(selectedLayer-1);
         TreeItem<String> Neuron = new TreeItem<> ("Liczba neuronów: " + NumberOfNeurons,new ImageView(NeuronIcon)); 
         if(item.isLeaf()){
             item.getChildren().add(Neuron);
         }
         else {
           item.getChildren().clear();
           item.getChildren().add(Neuron);
         }
           
    }

    //Pre processing
    @FXML
    private Label Sciezka;
    @FXML
    private ComboBox WybórWykrycia; 
    @FXML
    public TextField TextFieldRozdzielczonscMAX_X;
    @FXML
    public TextField TextFieldRozdzielczonscMAX_Y;
    @FXML 
    public Slider SliderRozmiarMinX;
    @FXML 
    public Slider SliderRozmiarMinY;
    @FXML 
    public Slider SliderRozmiarMaxX;
    @FXML 
    public Slider SliderRozmiarMaxY;
    @FXML
    public Slider SliderFailedBoxes;
    @FXML
    public Label LabelFailedBoxes;
    @FXML
    public Label LabelValueRozmiarMinX;
    @FXML
    public Label LabelValueRozmiarMinY;
    @FXML
    public Label LabelValueRozmiarMaxX;
    @FXML
    public Label LabelValueRozmiarMaxY;
    @FXML
    public CheckBox PreprocLoadChoosenclassif;
    @FXML
    public Label PreprocLoaedPathToClassifier;
    
    @FXML Slider PreProcRedSlider;
    @FXML Label PreprocRedValue;
    @FXML public void PreprocChangeSliderRedValue(){
      PreprocRedValue.setText(format("%.2f",PreProcRedSlider.getValue()));  
      PreProcchangeValuesOfPicture();
    }
    
    @FXML Slider PreProcGreenSlider;
    @FXML Label PreprocGreenValue;
    @FXML public void PreprocChangeSliderGreenValue(){
      PreprocGreenValue.setText(format("%.2f",PreProcGreenSlider.getValue()));  
      PreProcchangeValuesOfPicture();
    }
    
    @FXML Slider PreProcBlueSlider;
    @FXML Label PreprocBlueValue;
    @FXML public void PreprocChangeSliderBlueValue(){
      PreprocBlueValue.setText(format("%.2f",PreProcBlueSlider.getValue()));  
      PreProcchangeValuesOfPicture();
    }
    
    @FXML Slider PreProcBrightnessSlider;
    @FXML Label PreprocBrightnessValue;
    @FXML public void PreprocChangeSliderBrightnessValue(){
      PreprocBrightnessValue.setText(format("%.2f",PreProcBrightnessSlider.getValue()));
      PreProcchangeValuesOfPicture();
    }
    
    @FXML Slider PreProcContrastSlider;
    @FXML Label PreprocContrastValue;
    @FXML public void PreprocChangeSliderContrastValue(){
      PreprocContrastValue.setText(format("%.2f",PreProcContrastSlider.getValue()/1000));
      PreProcchangeValuesOfPicture();
    }
    
    @FXML Slider PreProcBlurrSlider;
    @FXML Label PreprocBlurrValue;
    @FXML public void PreprocChangeBlurrContrastValue(){
      PreprocBlurrValue.setText(format("%.2f",PreProcBlurrSlider.getValue())); 
      PreProcchangeValuesOfPicture();
    }
    
    @FXML Slider PreProcThresholdSlider;
    @FXML Label PreprocThresholdValue;
    @FXML public void PreprocChangeThresholdContrastValue(){
      PreprocThresholdValue.setText(format("%.2f",PreProcThresholdSlider.getValue()));
      PreProcchangeValuesOfPicture();
    }
    
    
    TypeOfThreshold PreprocTypeOfThreshold = BRAK_PROGROWANIA;
    @FXML ComboBox PreprocThresholdType;
    
    @FXML
    public void PreProcChangeTypeOfThreshold(){
        switch(PreprocThresholdType.getSelectionModel().getSelectedIndex())
        {
            case 0:
            PreprocTypeOfThreshold = BRAK_PROGROWANIA;
            PreProcchangeValuesOfPicture();
            break;
            
            case 1:
            PreprocTypeOfThreshold = PROGOWANIE;
            PreProcchangeValuesOfPicture();
            break;
            
            case 2:
            PreprocTypeOfThreshold = EFEKT_ROZJASNIAJACY;   
            PreProcchangeValuesOfPicture();
            break;
            
            case 3:
            PreprocTypeOfThreshold = EFEKT_PRZYCIEMNAJACY;   
            PreProcchangeValuesOfPicture();
            break;
        }
    }
    
    
    ViewPicture Pic;
    Picture PreprocOut = new Picture();
    @FXML ImageView PreprocImageLoad;
    @FXML
    private void PreProcchangeValuesOfPicture() {

            int[] pixels = Pic.ModyfikujKoloryWKanaleRGB(
             PreProcRedSlider.getValue()+PreProcBrightnessSlider.getValue(),
             PreProcGreenSlider.getValue()+PreProcBrightnessSlider.getValue(),
             PreProcBlueSlider.getValue()+PreProcBrightnessSlider.getValue(),
             PreProcContrastSlider.getValue()/1000,PreprocTypeOfThreshold,
            (int) PreProcThresholdSlider.getValue());
            PreprocOut.SetPicture(pixels,Pic.pobierzX(),Pic.pobierzY());
            
            if(PreprocUseNegative.isSelected()){
               PreprocOut.setImage(PreprocOut.Negative(PreprocOut.Image()));
            }
            
            if(PreProcBlurrSlider.getValue()>0.0){
                PreprocOut.setImage(PreprocOut.changeBlurr((int)PreProcBlurrSlider.getValue(), (int)PreProcBlurrSlider.getValue()));
            }
            
            PreprocImageLoad.setImage(SwingFXUtils.toFXImage(PreprocOut.Image(), null));
            
            
    }
    
    @FXML CheckBox PreprocUseResizeImage;
    @FXML public void PreprocReloadImage(){
        if(PreprocUseResizeImage.isSelected()){
           try {
                Pic = new ViewPicture(new Picture().setPicture(ImageIO.read(new File(Sciezka.getText())), Integer.valueOf(TextFieldRozdzielczonscMAX_X.getText()), Integer.valueOf(TextFieldRozdzielczonscMAX_Y.getText())));
                PreProcchangeValuesOfPicture();
                
               } 
           catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        }
        else{
            try 
            {  
                Pic = new ViewPicture(ImageIO.read(new File(Sciezka.getText())));
                PreProcchangeValuesOfPicture();
               
            } 
            catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    @FXML CheckBox PreprocUseNegative;
    
    
    //Fragment odpowiedzialny za ustawienia wycięć fragmentów zawierające interesujące nas obiekty
    //Dokładniej skalowanie i przesunięcie wyciętych obrazów
    @FXML Slider PreprocMovePointAX;
    @FXML Label PreprocCurrentMovePointAX;
    @FXML public void PreprocChangeCurrentMovePointAXtValue(){
      PreprocCurrentMovePointAX.setText(format("%.2f",PreprocMovePointAX.getValue()));
    }
    
    @FXML Slider PreprocMovePointAY;
    @FXML Label PreprocCurrentMovePointAY;
    @FXML public void PreprocChangeCurrentMovePointAYtValue(){
      PreprocCurrentMovePointAY.setText(format("%.2f",PreprocMovePointAY.getValue()));
    }
    
    @FXML Slider PreprocMovePointBX;
    @FXML Label PreprocCurrentMovePointBX;
    @FXML public void PreprocChangeCurrentMovePointBXtValue(){
      PreprocCurrentMovePointBX.setText(format("%.2f",PreprocMovePointBX.getValue()));
    }
    
    @FXML Slider PreprocMovePointBY;
    @FXML Label PreprocCurrentMovePointBY;
    @FXML public void PreprocChangeCurrentMovePointBYtValue(){
      PreprocCurrentMovePointBY.setText(format("%.2f",PreprocMovePointBY.getValue()));
    }
    
    @FXML Slider PreprocMovePointCX;
    @FXML Label PreprocCurrentMovePointCX;
    @FXML public void PreprocChangeCurrentMovePointCXtValue(){
      PreprocCurrentMovePointCX.setText(format("%.2f",PreprocMovePointCX.getValue()));
    }
    
    @FXML Slider PreprocMovePointCY;
    @FXML Label PreprocCurrentMovePointCY;
    @FXML public void PreprocChangeCurrentMovePointCYtValue(){
      PreprocCurrentMovePointCY.setText(format("%.2f",PreprocMovePointCY.getValue()));
    }
    
    @FXML Slider PreprocMovePointDX;
    @FXML Label PreprocCurrentMovePointDX;
    @FXML public void PreprocChangeCurrentMovePointDXtValue(){
      PreprocCurrentMovePointDX.setText(format("%.2f",PreprocMovePointDX.getValue()));
    }
    
    @FXML Slider PreprocMovePointDY;
    @FXML Label PreprocCurrentMovePointDY;
    @FXML public void PreprocChangeCurrentMovePointDYtValue(){
      PreprocCurrentMovePointDY.setText(format("%.2f",PreprocMovePointDY.getValue()));
    }
    //Fragment odpowiedzialny za zakładkę Haar klasyfikator
    
    //Fragment opdowiedzialny za zakładkę Create samples 
    
    //Fragment odpowiedzialny za zakładkę załadowanie odpowiednich fragmentów do pliku vec 
    @FXML public Tab CSloadfromImages;
    
    @FXML 
    public Label CSinfotxtfile;
     
    @FXML
    public void CSloadinfofile(){
        Stage stage = new Stage();
            FileChooserFile FileChooserFile = new FileChooserFile(CSinfotxtfile.getText(),false,"txt");
            FileChooserFile.start(stage);
            CSinfotxtfile.setText(FileChooserFile.Sciezka);
    }
    
    @FXML
    public Label CSvecfile;
     @FXML
    public Label CSvecfile1;
    @FXML
    public void CScreatevecFile(){
        Stage stage = new Stage();
        FileChooserFile FileChooserFile = new FileChooserFile(CSvecfile.getText(),true,"vec");
        FileChooserFile.start(stage);
        CSvecfile.setText(FileChooserFile.Sciezka);
        CSvecfile1.setText(FileChooserFile.Sciezka);
    }
    
    @FXML
    public Label CSnum;
    
    @FXML
    public TextField CSinputnum;
    
    public void CSEnterNumbers(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          CSnum.setText(CSinputnum.getText());
          CSinputnum.clear();
      }
      
    }
    
    @FXML
    public Label CSw;
    
    @FXML
    public TextField CSinputw;
    public void CSEnterW(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          CSw.setText(CSinputw.getText());
          SCIw.setText(CSinputw.getText());
          CSinputw.clear();
      }
      
    }
    
    @FXML
    public Label CSh;
    @FXML
    public TextField CSinputh;
    public void CSEnterH(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          CSh.setText(CSinputh.getText());
          SCIh.setText(CSinputh.getText());
          CSinputh.clear();
      }
      
    }
    
    @FXML
    public Label CSShow;
    @FXML
    public CheckBox CSShowOut;
    public void SCSetShowOption(){
        if(CSShowOut.isSelected()){
            SCIshowt.setSelected(true);
            CSShow.setText("TRUE");
            SCIshow.setText("TRUE");
        }
        else{
            SCIshowt.setSelected(true);
            CSShow.setText("FALSE");
            SCIshow.setText("FALSE");
        }
    }
    
    
   //Fragment odpowiedzialny za Załadowanie kontretnego zdjęcia zawierający model który należy szukać 
    @FXML public Tab CSloadfromImage;
    
    @FXML
    public Label SCIChoosenImage;
    @FXML
    public void CSILchooseImage(){
        Stage stage = new Stage();
            FileChooserSample FileChooserSample = new FileChooserSample(SCIChoosenImage.getText());
            FileChooserSample.start(stage); 
            SCIChoosenImage.setText(FileChooserSample.Sciezka);
    }
    
    @FXML Label SCIChoosenBGfile;
    @FXML
    public void CSILchooseBGfile(){
        Stage stage = new Stage();
            FileChooserFile FileChooserFile = new FileChooserFile(SCIChoosenBGfile.getText(),false,"txt");
            FileChooserFile.start(stage); 
            SCIChoosenBGfile.setText(FileChooserFile.Sciezka);
    }
    
    @FXML Label SCInum;
    @FXML TextField SCInumInput;
     public void SCIEnterNum(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          SCInum.setText(SCInumInput.getText());
          SCInumInput.clear();
          CSnum.setText(SCInumInput.getText());
          SCInumInput.clear();
      }
      
    }
    
    @FXML Label SCIbgcolor;
    @FXML TextField SCIbgcolorInput;
     public void SCIEnterbgcolor(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          SCIbgcolor.setText(SCIbgcolorInput.getText());
          SCIbgcolorInput.clear();
      }
      
    }
     
    @FXML Label SCIbgthresh;
    @FXML TextField SCIbgthreshInput;
     public void SCIEnterbgthresh(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          SCIbgthresh.setText(SCIbgthreshInput.getText());
          SCIbgthreshInput.clear();
      }
      
    } 
    
    @FXML Label SCImaxidev;
    @FXML TextField SCImaxidevInput;
     public void SCIEntermaxidev(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          SCImaxidev.setText(SCImaxidevInput.getText());
          SCImaxidevInput.clear();
      }
      
    }  
    
     
    @FXML Label SCImaxxangle;
    @FXML TextField SCImaxxangleInput;
     public void SCIEntermaxxangle(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          SCImaxxangle.setText(SCImaxxangleInput.getText());
          SCImaxxangleInput.clear();
      }
      
    } 
    
    @FXML Label SCImaxyangle;
    @FXML TextField SCImaxyangleInput;
     public void SCIEntermaxyangle(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          SCImaxyangle.setText(SCImaxyangleInput.getText());
          SCImaxyangleInput.clear();
      }
      
    } 
    
    @FXML Label SCImaxzangle;
    @FXML TextField SCImaxzangleInput;
     public void SCIEntermaxzangle(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          SCImaxzangle.setText(SCImaxzangleInput.getText());
          SCImaxzangleInput.clear();
      }
      
    }  
     
    @FXML Label SCIw;
    @FXML TextField SCIwInput;
     public void SCIEnterw(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          SCIw.setText(SCIwInput.getText());
          CSw.setText(SCIwInput.getText());
          SCIwInput.clear();
      }
      
    } 
    
    @FXML Label SCIh;
    @FXML TextField SCIhInput;
    public void SCIEnterh(KeyEvent e){
      if(e.getCode() == KeyCode.ENTER){
          SCIh.setText(SCIhInput.getText());
          CSh.setText(SCIhInput.getText());
          SCIhInput.clear();
      }
      
    } 
    
    @FXML
    public Label SCIinv;
    @FXML
    public CheckBox SCIinvSet;
    @FXML
    public void SCISetInv(){
        if(SCIinvSet.isSelected()){
            SCIinv.setText("TRUE");
        }
        else{
            SCIinv.setText("FALSE");
        }
    }  
    
    @FXML
    public Label SCIrandinv;
    @FXML
    public CheckBox SCIrandinvSet;
    @FXML
    public void SCIRandSetInv(){
        if(SCIrandinvSet.isSelected()){
            SCIrandinv.setText("TRUE");
        }
        else{
            SCIrandinv.setText("FALSE");
        }
    } 
    
    @FXML
    public Label SCIshow;
    @FXML
    public CheckBox SCIshowt;
    @FXML
    public void SCIRandSetShow(){
        if(SCIshowt.isSelected()){
            CSShowOut.setSelected(true);
            SCIshow.setText("TRUE");
            CSShow.setText("TRUE");
        }
        else{
            CSShowOut.setSelected(false);
            SCIshow.setText("FALSE");
            CSShow.setText("FALSE");
        }
    }
     
    //Fragment opdowiedzialny za zakładkę Haar Training  
    @FXML public Tab HTsetTraining;
    
    @FXML Label HTPathtoXml;
    public void HTChoosePathToXML(){
        Stage stage = new Stage();
            FolderChooser FolderChooser = new FolderChooser(HTPathtoXml.getText());
            FolderChooser.start(stage);
            HTPathtoXml.setText(FolderChooser.getPobranaŚciezka());
    }

    @FXML Label HTPathToVecFile;
    public void HTChoosePathToVec(){
        Stage stage = new Stage();
        FileChooserFile FileChooserFile = new FileChooserFile(HTPathToVecFile.getText(),false,"vec"," ");
            FileChooserFile.start(stage);
            HTPathToVecFile.setText(FileChooserFile.Sciezka);
    }
    
    @FXML Label HTPathtoBGtxt;
    public void HTChooseBGtxtFile(){
        Stage stage = new Stage();
        FileChooserFile FileChooserFile = new FileChooserFile(HTPathtoBGtxt.getText(),false,"txt"," ");
            FileChooserFile.start(stage);
            HTPathtoBGtxt.setText(FileChooserFile.Sciezka);
    }
    
    @FXML Label HTnpos;
    @FXML TextField HTnposInput;
     public void HTnposEnter(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          HTnpos.setText(HTnposInput.getText());
          HTnposInput.clear();
      }
      
    }
    
    @FXML Label HTnneg;
    @FXML TextField HTnnegInput;
     public void HTnnegEnter(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          HTnneg.setText(HTnnegInput.getText());
          HTnnegInput.clear();
      }
      
    } 
    
    @FXML Label HTnstages;
    @FXML TextField HTnstagesInput;
     public void HTnstagesEnter(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          HTnstages.setText(HTnstagesInput.getText());
          HTnstagesInput.clear();
      }
      
    }  
    
    @FXML Label HTprecalcValBufSize;
    @FXML TextField HTprecalcValBufSizeInput;
     public void HTprecalcValBufSizeEnter(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          HTprecalcValBufSize.setText(HTprecalcValBufSizeInput.getText());
          HTprecalcValBufSizeInput.clear();
      }
      
    }
     
    @FXML Label HTprecalcIdxBufSize;
    @FXML TextField HTprecalcIdxBufSizeInput;
     public void HTprecalcIdxBufSizeEnter(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          HTprecalcIdxBufSize.setText(HTprecalcIdxBufSizeInput.getText());
          HTprecalcIdxBufSizeInput.clear();
      }
      
    } 
    
    @FXML Label HTnumThreads;
    @FXML TextField HTnumThreadsInput;
     public void HTnumThreadsEnter(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          HTnumThreads.setText(HTnumThreadsInput.getText());
          HTnumThreadsInput.clear();
      }
      
    }  
   
    @FXML Label HTacceptanceRatioBreakValue ;
    @FXML TextField HTacceptanceRatioBreakValueInput;
     public void HTacceptanceRatioBreakValueEnter(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          HTacceptanceRatioBreakValue.setText(HTacceptanceRatioBreakValueInput.getText());
          HTacceptanceRatioBreakValueInput.clear();
      }
      
    }  
    
    @FXML
    public Label HTbaseFormatSave ;
    @FXML
    public CheckBox HTbaseFormatSaveCheck;
    @FXML
    public void HTbaseFormatSaveSet(){
        if(HTbaseFormatSaveCheck.isSelected()){
            HTbaseFormatSave.setText("TRUE");
        }
        else{
            HTbaseFormatSave.setText("FALSE");
        }
    } 
     
    @FXML Label HTw ;
    @FXML TextField HTwInput;
     public void HTwEnter(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          HTw.setText(HTwInput.getText());
          HTwInput.clear();
      }
    } 
    
    @FXML Label HTh ;
    @FXML TextField HThInput;
     public void HThEnter(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          HTh.setText(HThInput.getText());
          HThInput.clear();
      }
    }
     
     
    @FXML Label HTfeaturetype;
    @FXML RadioButton HTHaar;
    @FXML RadioButton HTLBP;
    @FXML public void HTChooseHaar(){
        ChooseOneRadiobutton(HTHaar,HTfeaturetype,HTLBP);
    }
    @FXML public void HTChooseLBP(){
        ChooseOneRadiobutton(HTLBP,HTfeaturetype,HTHaar);
    }
    
    @FXML Label HTmode;
    @FXML RadioButton HTBASIC;
    @FXML RadioButton HTCORE;
    @FXML RadioButton HTALL;
    @FXML public void HTChooseBASIC(){
        ChooseOneRadiobutton(HTBASIC,HTmode,HTCORE,HTALL);
    }
    @FXML public void HTChooseCORE(){
        ChooseOneRadiobutton(HTCORE,HTmode,HTBASIC,HTALL);
    }
    @FXML public void HTChooseALL(){
        ChooseOneRadiobutton(HTALL,HTmode,HTBASIC,HTCORE);
    }
    
    @FXML Label HTbt;
    @FXML RadioButton HTDAB;
    @FXML RadioButton HTRAB;
    @FXML RadioButton HTLB;
    @FXML RadioButton HTGAB;
    @FXML public void HTChooseDAB(){
        ChooseOneRadiobutton(HTDAB,HTbt,HTRAB,HTLB,HTGAB);
    }
    @FXML public void HTChooseRAB(){
        ChooseOneRadiobutton(HTRAB,HTbt,HTLB,HTDAB,HTGAB);
    }
    @FXML public void HTChooseLB(){
        ChooseOneRadiobutton(HTLB,HTbt,HTDAB,HTRAB,HTGAB);
    }
    @FXML public void HTChooseGAB(){
        ChooseOneRadiobutton(HTGAB,HTbt,HTDAB,HTRAB,HTLB);
    }
    
    @FXML Label HTminHitRate ;
    @FXML TextField HTminHitRateInput;
     public void HTminHitRateEnter(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          HTminHitRate.setText(HTminHitRateInput.getText());
          HTminHitRateInput.clear();
      }
      
    }
    
    @FXML Label HTmaxFalseAlarmRate ;
    @FXML TextField HTmaxFalseAlarmRateInput;
     public void HTmaxFalseAlarmRateEnter(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          HTmaxFalseAlarmRate.setText(HTmaxFalseAlarmRateInput.getText());
          HTmaxFalseAlarmRateInput.clear();
      }
      
    } 
    
    @FXML Label HTweightTrimRate;
    @FXML TextField HTweightTrimRateInput;
     public void HTweightTrimRateEnter(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          HTweightTrimRate.setText(HTweightTrimRateInput.getText());
          HTweightTrimRateInput.clear();
      }
      
    } 
     
    @FXML Label HTmaxDepth;
    @FXML TextField HTmaxDepthInput;
     public void HTmaxDepthEnter(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          HTmaxDepth.setText(HTmaxDepthInput.getText());
          HTmaxDepthInput.clear();
      }
    } 
    
    @FXML Label HTmaxWeakCount ;
    @FXML TextField HTmaxWeakCountInput;
     public void HTmaxWeakCountEnter(KeyEvent e){
      if(e.getCode()== KeyCode.ENTER){
          HTmaxWeakCount.setText(HTmaxWeakCountInput.getText());
          HTmaxWeakCountInput.clear();
      }
    } 
     @FXML Label HTChoosenXMLFile;
     
     @FXML public void HTLoadChoosenXMLFile(){
         Stage Stage = new Stage();
         FileChooserFile FileChooserFile = new FileChooserFile(HTChoosenXMLFile.getText(),true,"xml");
         FileChooserFile.start(Stage);
         HTChoosenXMLFile.setText(FileChooserFile.Sciezka);
     }
     
     @FXML public void HTSaveDataFromCascadeFolder(){
        Cascade.DataToCascadeXmlSave(HTPathtoXml, HTChoosenXMLFile);
        HaarCascadeTraining.DataToCascadeXmlOpen();
     }
     
    // koniec fragmentów odpowiedzialnych za kontrolę parametrów skrytpu
    // fragment odpowiedzialny za tworzenie oraz uruchomienie skryptu
    //referencja do tworzenia / odtworzenia skryptu CreateSample 
    CreateSamples CreateSamples;
    
    //Referencja do tworzenia/odtworzenia skrytpu HaarCascade
    HaarCascadeTraining Cascade;
     @FXML public void SaveScript(){
         if(CSloadfromImages.isSelected()){
             CreateSamples.SaveScript(true);
         }
         else if(CSloadfromImage.isSelected()){
             CreateSamples.SaveScript(false);
         }
         else if(HTsetTraining.isSelected()){
             Cascade.SaveScript();
         }
     }

     @FXML public void OpenScript(){
         if(CSloadfromImages.isSelected()){
            CreateSamples.RunScript();
         }
         else if(CSloadfromImage.isSelected()){
            CreateSamples.RunScript(); 
         }
         else if(HTsetTraining.isSelected()){
            Cascade.RunScript();
         }
     }
     
     
    @FXML
    public void preprocSlider(){
        LabelFailedBoxes.setText(format("%.2f",SliderFailedBoxes.getValue())); 
    }
    
    @FXML 
    public void preprocLoadClassifier(){
        Stage stage = new Stage();
            FileChooserFile FileChooserFile = new FileChooserFile(PreprocLoaedPathToClassifier.getText(),false,"xml"," ");
            FileChooserFile.start(stage);
            PreprocLoaedPathToClassifier.setText(FileChooserFile.Sciezka);
    }
    
    @FXML
    private void handleButtonAction() throws Exception {
            Stage stage = new Stage();
            FileChooserSample FileChooserSample = new FileChooserSample(pobranaścieszka);
            FileChooserSample.start(stage); 
            pobranaścieszka = FileChooserSample.getPobranaŚciezka();
            Sciezka.setText(FileChooserSample.Sciezka);
            PreprocReloadImage(); 
    }
    
    @FXML
    private void handleComboBoxWybórWykryciaAction(ActionEvent event){
            switch(WybórWykrycia.getValue().toString()){
                case "Wykryj po Twarzy":
                type = TypeOfDetect.FACE_DETECT;
                break;
                case "Wykryj po Sylwetce":
                type = TypeOfDetect.HUMANOID_DETECT;
                break;
                case "Wykryj po Torsie":
                type = TypeOfDetect.UPPER_BODY_DETECT;
                break;
            }
         }
    
    
    @FXML
    public void changeValueMinX(){
        LabelValueRozmiarMinX.setText(format("%.2f",SliderRozmiarMinX.getValue()));
    }
    @FXML
    public void changeValueMinY(){
        LabelValueRozmiarMinY.setText(format("%.2f",SliderRozmiarMinY.getValue())); 
    }
    @FXML
    public void changeValueMaxX(){
         LabelValueRozmiarMaxX.setText(format("%.2f",SliderRozmiarMaxX.getValue()));
    }
    @FXML
    public void changeValueMaxY(){
         LabelValueRozmiarMaxY.setText(format("%.2f",SliderRozmiarMaxY.getValue()));
    }
    
    @FXML
    private void handleButtonWykrywanieAction() throws Exception {
        RunnersDetection RunnersDetection = new RunnersDetection(type,SwingFXUtils.fromFXImage(PreprocImageLoad.getImage(),null),Integer.valueOf(TextFieldRozdzielczonscMAX_X.getCharacters().toString()),
        Integer.valueOf(TextFieldRozdzielczonscMAX_Y.getCharacters().toString()),(int) (SliderRozmiarMaxX.getValue()),(int)(SliderRozmiarMaxY.getValue()),
        (int)(SliderRozmiarMinX.getValue()),(int)(SliderRozmiarMinY.getValue()),SliderFailedBoxes.getValue()/100.0,
        PreprocLoadChoosenclassif.isSelected(),PreprocLoaedPathToClassifier.getText());
        
        DetectedRunners = RunnersDetection.DetectedRunners();
        DrawOnCanvas DrawOnCanvas = new DrawOnCanvas(RunnersDetection.PicWithRunners(),RunnersDetection.ListaPunktów.size());
        Stage stage = new Stage();
        DrawOnCanvas.start(stage);
        stage.show();
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("FXMLDetectedRunners.fxml"));
        Parent root = null;
        try {
          root = (Parent) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image i =  new Image("file:title_icon.png");
        stage.getIcons().add(i);
        stage.setTitle("Ustawienia parametrów na wykrytych biegaczach");
        
        FXMLDetectedRunnersController = loader.getController();
        Scene scene = new Scene(root);
        Stage StagePic = new Stage();
        StagePic.setScene(scene);
        StagePic.getIcons().add(i);
        StagePic.setTitle("Ustawienia potrzebne do rozpoznania biegaczy");
        Image image = SwingFXUtils.toFXImage(DetectedRunners.get(0), null);
        FXMLDetectedRunnersController.ImageView.setImage(image);
        FXMLDetectedRunnersController.Images = DetectedRunners;
        FXMLDetectedRunnersController.Numbers = NumbersNeuralNet;
        FXMLDetectedRunnersController.PathToTrainingFolderExamples = FolderNewExamples.getText();
        StagePic.show();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
       KlasifLabelPercent.setVisible(false);
       KlasifLabelLoadPic.setVisible(false);
       KlasifProgressbarLoadImages.setVisible(false);
       WybórWykrycia.getSelectionModel().selectFirst();
       type = TypeOfDetect.HUMANOID_DETECT;
       @SuppressWarnings("LocalVariableHidesMemberVariable")
       TreeItem<String> root = new TreeItem<>("Sieć Neuronowa");
       LayersAndNumbersOfNeuralNet.setRoot(root);
       ThresholdType.getSelectionModel().selectFirst();
       XYChart.Series series1 = new XYChart.Series();
        series1.setName("Stopień podobieństwa");
        series1.getData().add(new XYChart.Data(NUMBERS[0], 0.0));
        series1.getData().add(new XYChart.Data(NUMBERS[1], 0.0));
        series1.getData().add(new XYChart.Data(NUMBERS[2], 0.0));
        series1.getData().add(new XYChart.Data(NUMBERS[3], 0.0));
        series1.getData().add(new XYChart.Data(NUMBERS[4], 0.0));
        series1.getData().add(new XYChart.Data(NUMBERS[5], 0.0));
        series1.getData().add(new XYChart.Data(NUMBERS[6], 0.0));
        series1.getData().add(new XYChart.Data(NUMBERS[7], 0.0));
        series1.getData().add(new XYChart.Data(NUMBERS[8], 0.0));
        series1.getData().add(new XYChart.Data(NUMBERS[9], 0.0));
       
       ChartNumbersDetect.getData().add(series1);
       ChartNumbersDetect.setAnimated(true);
       
        gc = KlasifImageView.getGraphicsContext2D();
        
        CreateSamples = new CreateSamples(CSinfotxtfile,CSvecfile,CSnum,CSw,CSh,CSShow,
        SCIChoosenImage,SCIChoosenBGfile,SCIbgcolor,SCIbgthresh,SCIinv,SCIrandinv,SCImaxidev,
        SCImaxxangle,SCImaxyangle,SCImaxzangle);
        
        Cascade = new HaarCascadeTraining(HTPathtoXml,HTPathToVecFile,HTPathtoBGtxt,HTnpos,HTnneg,HTnstages,
        HTprecalcValBufSize,HTprecalcIdxBufSize,HTbaseFormatSave,HTnumThreads,HTacceptanceRatioBreakValue,
        HTw,HTh,HTfeaturetype,HTmode,HTbt,HTminHitRate,HTmaxFalseAlarmRate,HTweightTrimRate,
        HTmaxDepth,HTmaxWeakCount);
    }    

   
    
}
