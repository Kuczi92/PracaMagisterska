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
import NumericTasks.ArrayUtils;
import NumericTasks.Picture;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public void LoadFolderWithNumbers(){
        Stage stage = new Stage();
        FolderChooser FolderChooser = new FolderChooser(ChoosePicture.getText());
        FolderChooser.start(stage);
        ChoosePicture.setText(FolderChooser.getPobranaŚciezka());
    
        try {
             
            PathToNumbersFolder = new ArrayUtils().ListaPlikówWFolderze(ChoosePicture.getText());
         
        } catch (IOException ex) {
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
    public void RecognizeNumberWithNeuralNet(){
        
        try {
            int i = NumbersNeuralNet.RecognizeNumber(new Picture(ImageIO.read(new File(LabelPicWithNumber.getText()))), (int) SliderThreszholdrecognize.getValue());
            LabelRecognizedNumber.setText(" "+i);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            LabelRecognizedNumber.setText("Błąd podczas ładowania pliku!");
        }
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
         Pic = SwingFXUtils.toFXImage(ImageIO.read(new File(LabelPicWithNumber.getText())), null);
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
                                
                                new Thread(()->{
                                    TrainingNeuralNetController.Training.run();
                                }).start();
                                
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
    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
            Stage stage = new Stage();
            FileChooserSample FileChooserSample = new FileChooserSample(pobranaścieszka);
            FileChooserSample.start(stage); 
            pobranaścieszka = FileChooserSample.getPobranaŚciezka();
            Sciezka.setText(FileChooserSample.Sciezka);
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
    private void handleButtonWykrywanieAction(ActionEvent event) throws Exception {
        RunnersDetection RunnersDetection = new RunnersDetection(type,Sciezka.getText(),Integer.valueOf(TextFieldRozdzielczonscMAX_X.getCharacters().toString()),Integer.valueOf(TextFieldRozdzielczonscMAX_Y.getCharacters().toString()),
        (int) (SliderRozmiarMaxX.getValue()),(int)(SliderRozmiarMaxY.getValue()),(int)(SliderRozmiarMinX.getValue()),(int)(SliderRozmiarMinY.getValue()),SliderFailedBoxes.getValue()/100.0);
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
       WybórWykrycia.getSelectionModel().selectFirst();
       type = TypeOfDetect.HUMANOID_DETECT;
       @SuppressWarnings("LocalVariableHidesMemberVariable")
       TreeItem<String> root = new TreeItem<>("Sieć Neuronowa");
       LayersAndNumbersOfNeuralNet.setRoot(root);
       
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
       
    }    
    
}
