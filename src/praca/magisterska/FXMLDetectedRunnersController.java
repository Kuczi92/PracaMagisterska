/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praca.magisterska;

import NeuralNetPackage.Numbers;
import NumericTasks.Picture;
import NumericTasks.TypeOfThreshold;
import NumericTasks.ViewPicture;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.String.format;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Quchi
 */
public class FXMLDetectedRunnersController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    static ArrayList<BufferedImage> Images;
    public Numbers Numbers;
    public String PathToTrainingFolderExamples;
    //komponenty znajdujące się w zakładce ustawienia obrazu w Tab text Obraz
    
    @FXML
    Slider SliderJasnosc;
    @FXML
    Slider SliderKontrast;
    @FXML
    Slider SliderWartoscProgowa;
    @FXML
    Slider SliderRozmycie;
    @FXML 
    Slider SliderMinimalnyX;
    @FXML 
    Slider SliderMinimalnyY;
    @FXML 
    Label LabelMinimalnyY;
    @FXML
    Label LabelMinimalnyX;
    
    @FXML 
    CheckBox DrawContur;
    
    
    @FXML 
    Slider SliderMaksymalnyX;
    @FXML 
    Slider SliderMaksymalnyY;
    @FXML 
    Label LabelMaksymalnyY;
    @FXML
    Label LabelMaksymalnyX;
    
    
    @FXML
    CheckBox CheckBoxKontury;
    @FXML
    Label LabelSliderValueKontrast;
    @FXML 
    Label LabelSlderValueJasnosc;
    @FXML 
    Label LabelSliderValueWartoscProgowa;
    @FXML 
    Label LabelValueSliderRozmycie;
    @FXML
    ComboBox ComboBoxWybierzRodzajProgowania;
    @FXML 
    Slider SliderKrawedz; 
    @FXML
    Label LabelValueSliderKrawedz;
    @FXML
    CheckBox CheckBoxUseKrawedz;
    @FXML
    CheckBox UseNegative;
    
    @FXML
    CheckBox LoadOriginPic;
    //Komponenty znajdujące się w zakładce Tab Kolory 
    @FXML
    Slider SliderCzerwony;
    @FXML
    Slider SliderZielony;
    @FXML
    Slider SliderNiebieski;
    @FXML
    Slider SliderNasycenie;
    @FXML
    Slider SliderWartosc;
    @FXML
    Slider SliderOdcien;
    @FXML
    Label LabelSliderValueCzerwony;
    @FXML
    Label LabelSliderValueZielony;
    @FXML
    Label LabelSliderValueNiebieski;
    @FXML
    Label LabelSliderValueNasycenie;
    @FXML
    Label LabelSliderValueWartosc;
    @FXML
    Label LabelSliderValueOdcien;
    @FXML 
    Slider SliderZoom;
    @FXML 
    Label LabelSliderValueZoom;
    //Komponenty znajdujące się w zakładce dane 
    @FXML
    TableColumn Point1FromPic;
    @FXML
    TableColumn Point2FromPic;
    @FXML
    TableColumn Point3FromPic;
    @FXML
    TableColumn Point4FromPic;
    
    // Komponenty znajdujące się w Wykrytych Liczbach
    
    @FXML
    BarChart ChartNumbers;
    @FXML
    Slider SliderDetectThreshold; 
    @FXML
    ImageView ImageViewNumber;
    @FXML
    Button ButtonPoprzedniNumber;
    @FXML
    Button ButtonNastepnyNumber;
    @FXML
    Button ButtonRecognizeNumber;
    @FXML
    Label LabelRecogizedNumer;
    final static String[] NUMBERS = {"0","1","2","3","4","5","6","7","8","9"};
    @FXML
    Label LabelCurrentValue;
    //Komponenty ogólne
    @FXML
    ImageView ImageView;
    @FXML
    Label Label;
    @FXML
    Button ButtonPrevios;
    @FXML 
    Button ButtonNext;
    
   
    
    TypeOfThreshold TypeOfThreshold;
    int currentImage= 0;
    
    int currentDetectedNumber = 0;
    ArrayList<BufferedImage> DetectedNumbers;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     TypeOfThreshold = TypeOfThreshold.BRAK_PROGROWANIA;
     ComboBoxWybierzRodzajProgowania.getSelectionModel().selectFirst();
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
       
       ChartNumbers.getData().add(series1);
       ChartNumbers.setAnimated(true);
    }    
    
    
    public void ChangeLabel(String w){
        Label.setText(w);
    }
    
    
    @FXML
    public void ChangeSliderValueSliderThreszholdrecognize(){
        LabelCurrentValue.setText(format("%.2f",SliderDetectThreshold.getValue()));
    }
    
    @FXML 
    public void RecognizeNumberWithNeuralNet(){
        
        int o = Numbers.RecognizeNumber(new Picture(DetectedNumbers.get(currentDetectedNumber)), (int) SliderDetectThreshold.getValue());
        LabelRecogizedNumer.setText(" "+o);
        XYChart.Series series1 = (XYChart.Series) ChartNumbers.getData().get(0);
        int size =  series1.getData().size();
        for(int i = 0 ; i<size ; i++){
            XYChart.Data p =   new XYChart.Data();
           p = (XYChart.Data) series1.getData().get(i);
           p.setXValue(NUMBERS[i]);
           p.setYValue(Numbers.CalculatedValuesArray().get(i));
       }
    }
   
    
    @FXML
    public void ChangeTypeOfThreshold(){
        switch(ComboBoxWybierzRodzajProgowania.getSelectionModel().getSelectedIndex())
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
    
    @FXML
    public void handleButtonNext(){
        if(currentImage+1<Images.size()&&currentImage+1>=0){
            Image image = SwingFXUtils.toFXImage(Images.get(++currentImage), null);
            ImageView.setImage(image);
            Label.setText("Obraz nr: "+(currentImage+1)+" spośród: "+Images.size());
            changeValuesOfPicture();
        }
        else 
        {   
            currentImage=0;
            Image image = SwingFXUtils.toFXImage(Images.get(currentImage), null);
            ImageView.setImage(image);
            Label.setText("Obraz nr: "+(currentImage+1)+" spośród: "+Images.size()+ " Brak innych obrazów!");
            changeValuesOfPicture();
           
        }
       
    }
    
    @FXML
    public void handleButtonPrevious(){
        if(currentImage-1<Images.size()&&currentImage-1>=0){
            Image image = SwingFXUtils.toFXImage(Images.get(--currentImage), null);
            ImageView.setImage(image);
            Label.setText("Obraz nr: "+(currentImage+1)+" spośród: "+Images.size());
            changeValuesOfPicture();
        }
        else 
        {
            currentImage=Images.size()-1;
            Image image = SwingFXUtils.toFXImage(Images.get(currentImage), null);
            ImageView.setImage(image);
            Label.setText("Obraz nr: "+(currentImage+1)+" spośród: "+Images.size()+ " Brak innych obrazów!");
            changeValuesOfPicture();
        }
       
    }
    @FXML
    CheckBox DrawContorous;
    
    @FXML
    ComboBox CorrectNumber;
    @FXML
    public void saveCorrectDetectNumber() throws IOException{
         long i  = (int) Files.list(Paths.get(PathToTrainingFolderExamples)).count();
         String FilePath = PathToTrainingFolderExamples+"\\Liczba_"+CorrectNumber.getSelectionModel().getSelectedItem().toString()+"_"+i+".png";
         File outputfile = new File(FilePath);
         ImageIO.write(DetectedNumbers.get(currentDetectedNumber), "png", outputfile);
        
    }
    
    
    
    @FXML
    public void changeValuesOfPicture(){
        ViewPicture Pic = new ViewPicture(Images.get(currentImage));
        //zmiana progowania mod jasnosci kontrastu kolorów
        int[] pixels = Pic.ModyfikujKoloryWKanaleRGB(SliderCzerwony.getValue()+SliderJasnosc.getValue(), SliderZielony.getValue()+SliderJasnosc.getValue(), SliderNiebieski.getValue()+SliderJasnosc.getValue(), SliderKontrast.getValue()/1000.0,TypeOfThreshold, (int) SliderWartoscProgowa.getValue());
        Picture Out = new Picture(pixels,Images.get(currentImage).getWidth(),Images.get(currentImage).getHeight());
        Out = new Picture( Out.Zoom(SliderZoom.getValue()/2));
        Image image = null;
        if(CheckBoxUseKrawedz.isSelected()){
            Out = new Picture (Out.changeBlurr((int)SliderRozmycie.getValue(), (int) SliderRozmycie.getValue()));
            image = SwingFXUtils.toFXImage(Out.DetectEdges(SliderKrawedz.getValue()), null); 
        }
        
        if(UseNegative.isSelected()){
            Out = new Picture (Out.Negative(Out.Image()));
            image = SwingFXUtils.toFXImage(Out.DetectEdges(SliderKrawedz.getValue()), null); 
        }
        if(CheckBoxKontury.isSelected()||LoadOriginPic.isSelected()){
            DetectedNumbers = Out.FindContorous((int)SliderRozmycie.getValue(), (int) SliderKrawedz.getValue(), (int)(SliderMinimalnyX.getValue()/100.0*Out.getImageWidth()),
                    (int)(SliderMinimalnyY.getValue()/100.0*Out.getImageHeight()), (int)(SliderMaksymalnyX.getValue()/100.0*Out.getImageWidth()),
                    (int)(SliderMaksymalnyY.getValue()/100.0*Out.getImageHeight()),
                    DrawContorous.isSelected(),new Picture(Images.get(currentImage)).Zoom(SliderZoom.getValue()/2),
                    LoadOriginPic.isSelected(), DrawContur.isSelected());
            image = SwingFXUtils.toFXImage(Out.Image(), null);
            if(DetectedNumbers.size()>0){
                ImageViewNumber.setImage(SwingFXUtils.toFXImage(DetectedNumbers.get(0), null));
            }
        }
        else
        {
            image = SwingFXUtils.toFXImage(Out.changeBlurr((int)SliderRozmycie.getValue(), (int) SliderRozmycie.getValue()), null);
        }
        ImageView.setImage(image);
    }
    
    @FXML
    public void changedetectedNumberPrevious(){
     if(currentDetectedNumber-1<DetectedNumbers.size()&&currentDetectedNumber-1>=0){
            Image image = SwingFXUtils.toFXImage(DetectedNumbers.get(--currentDetectedNumber), null);
            ImageViewNumber.setImage(image);
        }
        else 
        {   
            currentDetectedNumber=DetectedNumbers.size()-1;
            Image image = SwingFXUtils.toFXImage(DetectedNumbers.get(currentDetectedNumber), null);
            ImageViewNumber.setImage(image);
        }
    }
    
    @FXML
    public void changedetectedNumberNext(){
     if(currentDetectedNumber+1<DetectedNumbers.size()&&currentDetectedNumber+1>=0){
            Image image = SwingFXUtils.toFXImage(DetectedNumbers.get(++currentDetectedNumber), null);
            ImageViewNumber.setImage(image);
        }
        else 
        {   
            currentDetectedNumber=0;
            Image image = SwingFXUtils.toFXImage(DetectedNumbers.get(currentDetectedNumber), null);
            ImageViewNumber.setImage(image);
        }
    }
    
    @FXML
    public void changeValueSliderCzerwony(){
       LabelSliderValueCzerwony.setText(format("%.2f",SliderCzerwony.getValue()));
       changeValuesOfPicture();
    }
    
    @FXML
    public void changeValueSliderZielony(){
         LabelSliderValueZielony.setText(format("%.2f",SliderZielony.getValue()));
         changeValuesOfPicture();
    }
    
    @FXML
    public void changeValueSliderNiebieski(){
         LabelSliderValueNiebieski.setText(format("%.2f",SliderNiebieski.getValue()));
         changeValuesOfPicture();
    }
    
    @FXML
    public void changeValueSliderJasnosc(){
         LabelSlderValueJasnosc.setText(format("%.2f",SliderJasnosc.getValue()));
         changeValuesOfPicture();
    }
    
    @FXML
    public void changeValueSliderKontrast(){
         LabelSliderValueKontrast.setText(format("%.2f",SliderKontrast.getValue()/1000.0));
         changeValuesOfPicture();
    }
    
    @FXML
    public void changeValueSliderWartoscProgowa(){
         LabelSliderValueWartoscProgowa.setText(format("%.2f",SliderWartoscProgowa.getValue()));
         changeValuesOfPicture();
    }
    
    @FXML
    public void changeValueSliderRozmycie(){
         LabelValueSliderRozmycie.setText(format("%.2f",SliderRozmycie.getValue()));
         changeValuesOfPicture();
    }
    
    @FXML
    public void changeValueSliderKrawedz(){
         LabelValueSliderKrawedz.setText(format("%.2f",SliderKrawedz.getValue()));
         changeValuesOfPicture();
    }
    
    @FXML
    public void changeValueSliderOdcien(){
         LabelSliderValueOdcien.setText(format("%.2f",SliderOdcien.getValue()));
         changeValuesOfPicture();
    }
    
    @FXML
    public void changeValueSliderNasycenie(){
         LabelSliderValueNasycenie.setText(format("%.2f",SliderNasycenie.getValue()));
         changeValuesOfPicture();
    }
    
    @FXML
    public void changeValueSliderWartosc(){
         LabelSliderValueWartosc.setText(format("%.2f",SliderWartosc.getValue()));
         changeValuesOfPicture();
    }
    
    @FXML 
    public void changeValueSliderMinimalnyX(){
        LabelMinimalnyX.setText(format("%.2f",SliderMinimalnyX.getValue()));
        changeValuesOfPicture();
    }  
    
    @FXML
    public void changeValueSliderMinimalnyY(){
        LabelMinimalnyY.setText(format("%.2f",SliderMinimalnyY.getValue()));
        changeValuesOfPicture();
    } 
    
    @FXML
    public void changeValueSliderMaksymalnyX(){
        LabelMaksymalnyX.setText(format("%.2f",SliderMaksymalnyX.getValue()));
        changeValuesOfPicture();
    } 
    
    @FXML
    public void changeValueSliderMaksymalnyY(){
        LabelMaksymalnyY.setText(format("%.2f",SliderMaksymalnyY.getValue()));
        changeValuesOfPicture();
    }
    
    @FXML
    public void changeValueSliderZoom(){
        LabelSliderValueZoom.setText(format("%.2f",SliderZoom.getValue()));
        changeValuesOfPicture();
    }
    
    @FXML
    public void defaultValues(){
    SliderRozmycie.setValue(0.0);
    SliderJasnosc.setValue(0.0);
    SliderKontrast.setValue(1000.0);
    SliderWartoscProgowa.setValue(0.0);
    SliderCzerwony.setValue(0.0);
    SliderZielony.setValue(0.0);
    SliderNiebieski.setValue(0.0);
    TypeOfThreshold = TypeOfThreshold.BRAK_PROGROWANIA;
    changeValueSliderCzerwony();
    changeValueSliderZielony();
    changeValueSliderNiebieski();
    changeValueSliderJasnosc();
    changeValueSliderKontrast();
    changeValueSliderWartoscProgowa();
    changeValueSliderRozmycie();
    changeValuesOfPicture();
    ComboBoxWybierzRodzajProgowania.getSelectionModel().selectFirst();
    }
}
