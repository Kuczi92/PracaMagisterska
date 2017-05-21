/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praca.magisterska;

import NumericTasks.Picture;
import NumericTasks.TypeOfThreshold;
import NumericTasks.ViewPicture;
import java.awt.image.BufferedImage;
import static java.lang.String.format;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    Label LabelSliderValueKontrast;
    @FXML 
    Label LabelSlderValueJasnosc;
    @FXML 
    Label LabelSliderValueWartoscProgowa;
    @FXML 
    Label LabelValueSliderRozmycie;
    @FXML
    ComboBox ComboBoxWybierzRodzajProgowania;
    
    //Komponenty znajdujące się w zakładce Tab Kolory 
    @FXML
    Slider SliderCzerwony;
    
    @FXML
    Slider SliderZielony;
    
    @FXML
    Slider SliderNiebieski;
    
    @FXML
    Label LabelSliderValueCzerwony;
    
    @FXML
    Label LabelSliderValueZielony;
    
    @FXML
    Label LabelSliderValueNiebieski;
    
    //Komponenty znajdujące się w zakładce dane 
    @FXML
    TableColumn Point1FromPic;
    @FXML
    TableColumn Point2FromPic;
    @FXML
    TableColumn Point3FromPic;
    @FXML
    TableColumn Point4FromPic;
    
    
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     TypeOfThreshold = TypeOfThreshold.BRAK_PROGROWANIA;
     ComboBoxWybierzRodzajProgowania.getSelectionModel().selectFirst();
    }    
    
    
    public void ChangeLabel(String w){
        Label.setText(w);
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
            Label.setText("Obraz nr: "+currentImage+" spośród: "+Images.size());
            changeValuesOfPicture();
        }
        else 
        {   
            currentImage=0;
            Image image = SwingFXUtils.toFXImage(Images.get(currentImage), null);
            ImageView.setImage(image);
            Label.setText("Obraz nr: "+currentImage+" spośród: "+Images.size()+ " Brak innych obrazów!");
            changeValuesOfPicture();
           
        }
       
    }
    
    @FXML
    public void handleButtonPrevious(){
        if(currentImage-1<Images.size()&&currentImage-1>=0){
            Image image = SwingFXUtils.toFXImage(Images.get(--currentImage), null);
            ImageView.setImage(image);
            Label.setText("Obraz nr: "+currentImage+" spośród: "+Images.size());
            changeValuesOfPicture();
        }
        else 
        {
            currentImage=Images.size()-1;
            Image image = SwingFXUtils.toFXImage(Images.get(currentImage), null);
            ImageView.setImage(image);
            Label.setText("Obraz nr: "+currentImage+" spośród: "+Images.size()+ " Brak innych obrazów!");
            changeValuesOfPicture();
        }
       
    }
    
    @FXML
    public void changeValuesOfPicture(){
        ViewPicture Pic = new ViewPicture(Images.get(currentImage));
        int[] pixels = Pic.ModyfikujKoloryWKanaleRGB(SliderCzerwony.getValue()+SliderJasnosc.getValue(), SliderZielony.getValue()+SliderJasnosc.getValue(), SliderNiebieski.getValue()+SliderJasnosc.getValue(), SliderKontrast.getValue()/1000.0,TypeOfThreshold, (int) SliderWartoscProgowa.getValue());
        Picture Out = new Picture(pixels,Images.get(currentImage).getWidth(),Images.get(currentImage).getHeight());
        Image image = SwingFXUtils.toFXImage(Out.changeBlurr((int)SliderRozmycie.getValue(), (int) SliderRozmycie.getValue()), null);
        ImageView.setImage(image);
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
