/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praca.magisterska;

import java.io.File;
import javafx.application.Application;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author Quchi
 */
public class FolderChooser extends Application{
    private String pobranaścieszka;
 
    
    FolderChooser(String pobranaścieszka) {
       this.pobranaścieszka = pobranaścieszka;
    }
    
    public String getPobranaŚciezka(){
        return pobranaścieszka;
    }
    
    @Override
    public void start(final Stage stage) {
        
        final DirectoryChooser DirectoryChooser = new DirectoryChooser();
            configureFileChooser(DirectoryChooser);
           File File = DirectoryChooser.showDialog(stage);
            
       pobranaścieszka =  File.toString();
       stage.close();

    }
 
    private void configureFileChooser(final DirectoryChooser fileChooser) 
    {      

                fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
              
    }
 
   
    
}
