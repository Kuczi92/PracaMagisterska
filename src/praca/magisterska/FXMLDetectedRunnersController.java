/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praca.magisterska;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    
    ArrayList<BufferedImage> Images;
    
    @FXML
    ImageView ImageView;
    
    @FXML
    Label Label;
    
    @FXML
    Button ButtonPrevios;
    
    @FXML 
    Button ButtonNext;
    
    
    int currentImage= 0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
    
    
    public void ChangeLabel(String w){
        Label.setText(w);
    }
    
    @FXML
    public void handleButtonNext(){
        if(currentImage+1<Images.size()&&currentImage+1>=0){
            Image image = SwingFXUtils.toFXImage(Images.get(++currentImage), null);
            ImageView.setImage(image);
        }
        else 
        {
            Label.setText("Brak innych obrazów");
        }
       
    }
    
    public void handleButtonPrevious(){
        if(currentImage-1<Images.size()&&currentImage-1>=0){
            Image image = SwingFXUtils.toFXImage(Images.get(--currentImage), null);
            ImageView.setImage(image);
        }
        else 
        {
            Label.setText("Brak innych obrazów");
        }
       
    }
}
