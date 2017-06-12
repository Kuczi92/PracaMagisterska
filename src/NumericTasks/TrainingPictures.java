/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NumericTasks;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Quchi
 */

public class TrainingPictures {
    
   private  BufferedImage Picture;
   private final  ArrayList<MarkedRect> MarkedRect;
   private final int width;
   private final int height;
   private final String PathToFile;
   
   public TrainingPictures (BufferedImage In,String Path){
        this.PathToFile = Path;
        this.height = In.getHeight();
        this.width=In.getWidth();
        this.Picture = In;
        MarkedRect = new ArrayList<>();
    }
   
   public TrainingPictures (BufferedImage In,String Path,int x,int y){
        this.PathToFile = Path;
        this.height=y;
        this.width=x;
        this.Picture = In;
        MarkedRect = new ArrayList<>();
    }
   
   public String PathToImage(){
       return this.PathToFile;
   }

   public int getWidth(){
       return width;
   }
   
   public int getHeight(){
       return height;
   }
   
   public void SetImage(BufferedImage in){
     Picture = in;
   }
   
   public BufferedImage GetImage(){
       return Picture;
   }
   
   public ArrayList<MarkedRect> GetMarkedRect(){
       return MarkedRect;
   }
   
   public void AddNewRect(MarkedRect MarkedRect){
      this.MarkedRect.add(MarkedRect);
   }
}
