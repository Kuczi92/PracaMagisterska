/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praca.magisterska;

import javafx.scene.image.Image ;
import java.lang.reflect.Field;
import java.util.Arrays;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.opencv.core.Core;

/**
 *
 * @author Quchi
 */
public class PracaMagisterska extends Application {
    
   public static void addLibraryPath(String pathToAdd) throws Exception {
   final Field usrPathsField = ClassLoader.class.getDeclaredField("usr_paths");
    usrPathsField.setAccessible(true);
 
    //get array of paths
    final String[] paths = (String[])usrPathsField.get(null);
    
    //check if the path to add is already present
    for(String path : paths) {
        if(path.equals(pathToAdd)) {
            return;
        }
    }
 
    //add the new path
    final String[] newPaths = Arrays.copyOf(paths, paths.length + 1);
    newPaths[newPaths.length-1] = pathToAdd;
    usrPathsField.set(null, newPaths);
}    
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Image i =  new Image("file:settings\\icontitle\\icon_title.png");
        stage.getIcons().add(i);
        stage.setTitle("Wykrywanie biegaczy");
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        if("amd64".equals(System.getProperty("os.arch"))){
            addLibraryPath("lib\\x64");
        }
        else{
            addLibraryPath("lib\\x86");
        }
        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        launch(args);
    }
    
}
