/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NumericTasks;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Quchi
 */
public class WriteToFile {
   String Path;
   ArrayList<TrainingPictures> Pictures;
   ArrayList<String>PathFiles;
   public WriteToFile(String path, ArrayList<?> Pic){
        Path = path;
        Pictures = new ArrayList<>();
        PathFiles = new ArrayList<>();
        Pic.forEach((o) -> {
            if (o instanceof TrainingPictures) {
               Pictures.add((TrainingPictures) o);
            }
            else if (o instanceof String) {
                PathFiles.add( (String) o );
            }
       });
        
    }
    public void Wrtite() throws FileNotFoundException{
        
        try (PrintWriter zapis = new PrintWriter(Path)) 
        {   
            
           if(!Pictures.isEmpty()){
                    Pictures.forEach((CurentItem) -> {
                        
                      if(CurentItem.PathToImage().contains("Haar_Training\\Negative")){
                      zapis.print(CurentItem.PathToImage().substring(CurentItem.PathToImage().indexOf("Haar_Training\\Negative")).replace("Haar_Training\\Negative\\", ""));    
                      }
                      else{
                      zapis.print(CurentItem.PathToImage());   
                      }
                      
                      if(CurentItem.GetMarkedRect().isEmpty()){

                      }
                      else{
                          zapis.print(" ");
                          zapis.print(CurentItem.GetMarkedRect().size());
                          zapis.print(" ");

                          CurentItem.GetMarkedRect().forEach((CurrenRect) -> {
                              zapis.print(CurrenRect.x+" "+CurrenRect.y+" "+CurrenRect.width+" "+CurrenRect.height+" ");
                          });
                      }
                      zapis.println();
                  }); 
           }
           
           else if(!PathFiles.isEmpty()){
                 PathFiles.forEach((CurentItem) -> {
                     
                      if(CurentItem.contains("Haar_Training\\Negative")){
                      zapis.print(CurentItem.substring(CurentItem.indexOf("Haar_Training\\Negative")).replace("Haar_Training\\Negative\\", ""));    
                      }
                      else{
                      zapis.print(CurentItem);   
                      }
                      
                      zapis.println();
                  }); 
           }
            
        }
        
    }
}
