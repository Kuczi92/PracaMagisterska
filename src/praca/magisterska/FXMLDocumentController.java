/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praca.magisterska;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Quchi
 */
public class FXMLDocumentController implements Initializable {
    FXMLLoader loader;
    Parent root;
    FXMLDetectedRunnersController FXMLDetectedRunnersController;
    TypeOfDetect type;
    String pobranaścieszka;
    
    ArrayList<BufferedImage> DetectedRunners;
    
    @FXML
    private Label Sciezka;
    
    @FXML
    private ComboBox WybórWykrycia; 
    
    
    @FXML
    public TextField TextFieldRozdzielczonscMAX_X;
     
    @FXML
    public TextField TextFieldRozdzielczonscMAX_Y;
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
    private void handleButtonWykrywanieAction(ActionEvent event) throws Exception {
        RunnersDetection RunnersDetection = new RunnersDetection(type,Sciezka.getText(),Integer.valueOf(TextFieldRozdzielczonscMAX_X.getCharacters().toString()),Integer.valueOf(TextFieldRozdzielczonscMAX_Y.getCharacters().toString()));
        DetectedRunners = RunnersDetection.DetectedRunners();
        DrawOnCanvas DrawOnCanvas = new DrawOnCanvas(RunnersDetection.PicWithRunners(),RunnersDetection.ListaPunktów.size());
        Stage stage = new Stage();
        DrawOnCanvas.start(stage);
        stage.show();
        

        
        
        loader = new FXMLLoader(getClass().getResource("FXMLDetectedRunners.fxml"));
        try {
          root = (Parent) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLDetectedRunnersController = loader.getController();
        Scene scene = new Scene(root);
        Stage StagePic = new Stage();
        StagePic.setScene(scene);
        Image image = SwingFXUtils.toFXImage(DetectedRunners.get(0), null);
        FXMLDetectedRunnersController.ImageView.setImage(image);
        FXMLDetectedRunnersController.Images = DetectedRunners;
        
        
        
        StagePic.show();
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       WybórWykrycia.getSelectionModel().selectFirst();
       type = TypeOfDetect.HUMANOID_DETECT;
    }    
    
}
