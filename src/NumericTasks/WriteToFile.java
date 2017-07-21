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
   ArrayList< TrainingPictures> Pictures;
    
   public WriteToFile(String path, ArrayList< TrainingPictures> Pic){
        Path = path;
        Pictures = Pic;
    }
    public void Wrtite() throws FileNotFoundException{
        
        try (PrintWriter zapis = new PrintWriter(Path)) 
        {   
            Pictures.forEach((CurentItem) -> {
                zapis.print(CurentItem.PathToImage());
                
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
        
    }
}
