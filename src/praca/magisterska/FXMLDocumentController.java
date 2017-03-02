/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praca.magisterska;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Quchi
 */
public class FXMLDocumentController implements Initializable {
    
    
    String pobranaścieszka;
    
    @FXML
    private Label Sciezka;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
            Stage stage = new Stage();
            FileChooserSample FileChooserSample = new FileChooserSample(pobranaścieszka);
            FileChooserSample.start(stage); 
            pobranaścieszka = FileChooserSample.getPobranaŚciezka();
            Sciezka.setText(FileChooserSample.Sciezka);
            
    }
    
    
    @FXML
    private void handleButtonWykrywanieAction(ActionEvent event) throws Exception {
        FaceDetector FaceDetector = new FaceDetector(Sciezka.getText());
        FaceDetector.DetectFaces();
        DrawOnCanvas DrawOnCanvas = new DrawOnCanvas(FaceDetector.ObrazWyjsciowy,FaceDetector.ListaPunktów.size());
        Stage stage = new Stage();
        DrawOnCanvas.start(stage);
        
        stage.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
