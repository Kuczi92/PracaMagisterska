/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praca.magisterska;

/**
 *
 * @author Quchi
 */
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
 
public final class FileChooserSample extends Application {
   private String pobranaścieszka;
    private final Desktop desktop = Desktop.getDesktop();
    String Sciezka;

    FileChooserSample(String pobranaścieszka) {
       this.pobranaścieszka = pobranaścieszka;
    }
    
    public String getPobranaŚciezka(){
        return pobranaścieszka;
    }
    
    @Override
    public void start(final Stage stage) {
        
        final FileChooser fileChooser = new FileChooser();
            configureFileChooser(fileChooser);
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                openFile(file);
            }

    }
 
    private void configureFileChooser(final FileChooser fileChooser) 
    {      
        
            fileChooser.setTitle("View Pictures");
            if(pobranaścieszka!=null){
                fileChooser.setInitialDirectory(new File(pobranaścieszka));   
            }
            
            else
            {
                fileChooser.setInitialDirectory(                
                        new File(System.getProperty("user.home"))
                );
            }
            
                            
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
    }
 
    private void openFile(File file) {
        try {
            desktop.open(file);
            Sciezka = file.getAbsolutePath();
            pobranaścieszka = Sciezka.substring(0,Sciezka.lastIndexOf("\\"));
        } catch (IOException ex) {
            Logger.getLogger(FileChooserSample.class.getName()).log(
                Level.SEVERE, null, ex
            );
        }
    }
}
