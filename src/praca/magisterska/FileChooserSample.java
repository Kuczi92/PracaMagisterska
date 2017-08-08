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
    String InicialDirectory;
    FileChooserSample(String pobranaścieszka) {
        if(pobranaścieszka==null){
            this.pobranaścieszka = " ";
        }
        else{
            this.pobranaścieszka = pobranaścieszka;
        }
       
    }
    
    FileChooserSample(String pobranaścieszka, String InicialDirectory) {
        if(pobranaścieszka==null){
            this.pobranaścieszka = " ";
        }
        else{
            this.pobranaścieszka = pobranaścieszka;
        }
        
        this.InicialDirectory=InicialDirectory;
    }
    
    
    public String getPobranaŚciezka(){
        return pobranaścieszka;
    }
    
    @Override
    public void start(final Stage stage) {
        
        final FileChooser fileChooser = new FileChooser();
            configureFileChooser(fileChooser);
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) 
            {
                openFile(file);
            }
            stage.close();

    }
 
    private void configureFileChooser(final FileChooser fileChooser) 
    {      
             
            fileChooser.setTitle("Wybierz obraz");
            if(pobranaścieszka==null){
                pobranaścieszka = Sciezka.substring(0,Sciezka.lastIndexOf("\\"));
            }
            
            if(pobranaścieszka.endsWith(".jpg")||pobranaścieszka.endsWith(".png")||pobranaścieszka.endsWith(".bmp")){
                pobranaścieszka = pobranaścieszka.substring(0,pobranaścieszka.lastIndexOf("\\"));
                fileChooser.setInitialDirectory(new File(pobranaścieszka));   
            }
            
            else
            {
                if(null!=InicialDirectory){
                    fileChooser.setInitialDirectory(new File(InicialDirectory));
                }
                else{
                    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+"\\Pictures"));
                }
            }
            
                            
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Wszystkie obrazy", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
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
