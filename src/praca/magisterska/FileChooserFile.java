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
    boolean createFile = true;
    String extension;
    String FileName;
    String InicialDirectory;
    FileChooserFile(String pobranaścieszka) {
        if(pobranaścieszka==null){
            this.pobranaścieszka = "Brak";
        }else{
            this.pobranaścieszka = pobranaścieszka;
        }
    
    }

    FileChooserFile(String text, boolean b) {
      this.pobranaścieszka = text;
      this.createFile = b;
    }

    FileChooserFile(String text, boolean b, String xml) {
        this.pobranaścieszka = text;
        this.createFile = b;
        this.extension = xml;
    }

    FileChooserFile(String text, boolean b, String txt, String listaObrazówiObiektów) {
     this.pobranaścieszka = text;
        this.createFile = b;
        this.extension = txt;
        this.FileName = listaObrazówiObiektów;
    }
    
    FileChooserFile(String text, boolean b, String txt, String listaObrazówiObiektów,String InicialDirectory) {
     this.pobranaścieszka = text;
        this.createFile = b;
        this.extension = txt;
        this.FileName = listaObrazówiObiektów;
        this.InicialDirectory = InicialDirectory;
    }
    
    public String getPobranaŚciezka(){
        return pobranaścieszka;
    }
    
    @Override
    public void start(final Stage stage) {
        
        final FileChooser fileChooser = new FileChooser();
            configureFileChooser(fileChooser);
            File file;
            if(createFile){
                 file = fileChooser.showSaveDialog(stage);
            }
            else{
                 file = fileChooser.showOpenDialog(stage);
            }
            
            if (file != null) {
                openFile(file);
                
            }
            stage.close();
    }
 
    private void configureFileChooser(final FileChooser fileChooser) 
    {      
   
            fileChooser.setTitle("Wybierz plik txt z siecia neuronową ");
            if(pobranaścieszka==null){
                pobranaścieszka = "wybierzplik";
                fileChooser.setInitialDirectory(                
                        new File(System.getProperty("user.home"))
                );
            }
            if(pobranaścieszka.endsWith(".txt")){
                pobranaścieszka = pobranaścieszka.substring(0,pobranaścieszka.lastIndexOf("\\"));
                fileChooser.setInitialDirectory(new File(pobranaścieszka));   
            }
            
            else
            {   
                if(createFile){
                   
                    
                    if(extension==null&&FileName==null){
                        fileChooser.setTitle("Stwórz plik txt z siecia neuronową ");
                        fileChooser.setInitialFileName("Nowa sieć neuronowa.txt");
                    }
                    else if(FileName!=null&&extension!=null){
                        fileChooser.setTitle("Stwórz plik "+extension);
                        fileChooser.setInitialFileName(FileName+"."+extension);
                    }
                    else{
                        fileChooser.setTitle("Stwórz plik " + extension);
                        fileChooser.setInitialFileName("Nowy klasyfikator."+extension);
                        
                    }
                    
                }
                else{
                     if(extension==null&&FileName==null){
                        fileChooser.setTitle("Wybierz plik z siecią neuronową ");
                        fileChooser.setInitialFileName("Nowa sieć neuronowa.txt");
                    }
                    else if(FileName!=null&&extension!=null){
                        fileChooser.setTitle("Wybierz plik "+extension);
                        fileChooser.setInitialFileName(FileName+"."+extension);
                    }
                    else{
                        fileChooser.setTitle("Wybierz plik " + extension);
                        fileChooser.setInitialFileName("Nowy klasyfikator."+extension);
                        
                    }
                }
                if(InicialDirectory!=null){
                     fileChooser.setInitialDirectory(                
                        new File(InicialDirectory)
                );
                }
                else{
                     fileChooser.setInitialDirectory(                
                        new File(System.getProperty("user.home"))
                );
                }
               
            }
            
            if(extension==null){                
             fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("TXT", "*.txt"));
            }
            else{
                  fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter(extension.toUpperCase(), "*."+extension));
            }
    }
 
    private void openFile(File file) {
        try {
            if(createFile){
                 file.createNewFile();
            }
           
            try {
                
                desktop.open(file);
                Sciezka = file.getAbsolutePath();
                pobranaścieszka = Sciezka.substring(0,Sciezka.lastIndexOf("\\"));
            } 
            catch (IOException ex) {
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
