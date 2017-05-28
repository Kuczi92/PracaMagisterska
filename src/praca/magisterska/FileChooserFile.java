/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praca.magisterska;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Quchi
 */
public class FileChooserFile extends Application{
    private String pobranaścieszka;
    private final Desktop desktop = Desktop.getDesktop();
    String Sciezka;
    
    FileChooserFile(String pobranaścieszka) {
       this.pobranaścieszka = pobranaścieszka;
    }
    
    public String getPobranaŚciezka(){
        return pobranaścieszka;
    }
    
    @Override
    public void start(final Stage stage) {
        
        final FileChooser fileChooser = new FileChooser();
            configureFileChooser(fileChooser);
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                openFile(file);
            }

    }
 
    private void configureFileChooser(final FileChooser fileChooser) 
    {      
            
            fileChooser.setTitle("Stwórz plik txt z siecia neuronową ");
            if(pobranaścieszka.endsWith(".txt")){
                fileChooser.setInitialDirectory(new File(pobranaścieszka));   
            }
            
            else
            {   fileChooser.setInitialFileName("Nowa sieć neuronowa.txt");
                fileChooser.setInitialDirectory(                
                        new File(System.getProperty("user.home"))
                );
            }
            
                            
            fileChooser.getExtensionFilters().addAll(
               
                new FileChooser.ExtensionFilter("TXT", "*.txt")
         
            );
    }
 
    private void openFile(File file) {
        try {
            file.createNewFile();
            try {
                
                desktop.open(file);
                Sciezka = file.getAbsolutePath();
                pobranaścieszka = Sciezka.substring(0,Sciezka.lastIndexOf("\\"));
            } catch (IOException ex) {
                Logger.getLogger(FileChooserSample.class.getName()).log(
                        Level.SEVERE, null, ex
                );
            }
        } catch (IOException ex) {
            Logger.getLogger(FileChooserFile.class.getName()).log(
                Level.SEVERE, null, ex
            );
        }
    }
}
