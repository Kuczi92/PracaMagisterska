/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NumericTasks;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;

/**
 *
 * @author Quchi
 */
public class CreateSamples {
    private  final String PathtoScript="Haar_Training\\CreateSamples.bat";
    private  final String AppName="Haar_Training\\Opencv bin\\opencv_createsamples.exe";
    private final Label info;
    private final Label vec;
    private final Label num;
    private final Label w;
    private final Label h;
    private final Label show;
    private final Label img;
    private final Label bgcolor;
    private final Label bgthresh;
    private final Label inv;
    private final Label randinv;
    private final Label maxxangle;
    private final Label maxyangle;
    private final Label maxzangle;
    private final Label bg;
    private final Label maxidev;
    private final Label PngOutput;
    
    
    
    public CreateSamples(
     Label info,Label vec,Label num, Label w,Label h,Label show,Label img,Label bg,Label bgcolor,Label bgthresh,
     Label inv,Label randinv,Label maxidev, Label maxxangle, Label maxyangle,Label maxzangle,Label Pngoutput){
     this. info = info;
     this. vec = vec;
     this. num = num;
     this. w = w;
     this. h = h;
     this. show = show;
     this. img = img;
     this. bgcolor = bgcolor;
     this. bgthresh = bgthresh;
     this. inv = inv;
     this. randinv = randinv;
     this. maxxangle = maxxangle;
     this. maxyangle = maxyangle;
     this. maxzangle = maxzangle;
     this. bg = bg;
     this. maxidev = maxidev;
     this.PngOutput = Pngoutput;
    }
    
    
    
    public void SaveScript(boolean list) {
        try (PrintWriter zapis = new PrintWriter(PathtoScript)) 
            {
              File File = new File(AppName);
               zapis.print("\""+File.getAbsolutePath()+"\""); 
     
               if(!("Wybierz plik txt.".equals(info.getText())||"".equals(info.getText()))) 
                       {
                        String tmp = info.getText().substring(info.getText().indexOf("Haar_Training"));
                        zapis.print(" "+"-info "+tmp);
                       }
                   if(!("Wybierz obraz.".equals(img.getText())||"".equals(img.getText()))) 
                       {
                        zapis.print(" "+"-img "+"\""+img.getText()+"\"");
                       }
                   if(!("Wybierz plik txt.".equals(bg.getText())||"".equals(bg.getText()))) 
                       {
                        zapis.print(" "+"-bg "+bg.getText().substring(bg.getText().indexOf("Haar_Training")));
                       } 
              if(!("Stwórz plik vec.".equals(vec.getText())||"".equals(vec.getText()))) 
                       {
                        zapis.print(" "+"-vec "+"\""+vec.getText()+"\"");
                       } 
              if(!("Wprowadź liczbę.".equals(num.getText())||"".equals(num.getText()))) 
                       {
                        zapis.print(" "+"-num "+num.getText());
                       } 
              if(!("Wprowadź liczbę.".equals(w.getText())||"".equals(w.getText()))) 
                       {
                        zapis.print(" "+"-w "+w.getText());
                       }  
              if(!("Wprowadź liczbę.".equals(h.getText())||"".equals(h.getText()))) 
                       {
                        zapis.print(" "+"-h "+h.getText());
                       }  
              if(!("Zaznacz/odznacz opcję.".equals(show.getText())||"".equals(show.getText()))) 
                       {
                           if(show.getText().equals("TRUE")){
                              zapis.print(" "+"-show ");  
                           }
                       
                       }
              if(!("Wprowadź liczbę.".equals(maxidev.getText())||"".equals(maxidev.getText()))) 
                       {
                        zapis.print(" "+"-maxidev "+maxidev.getText());
                       }
              
                        if(!("Zaznacz/odznacz opcję.".equals(PngOutput.getText())||"".equals(PngOutput.getText()))) 
                                 {
                                    if(PngOutput.getText().equals("TRUE")) 
                                        zapis.print(" "+"-pngoutput");
                                 }
              
                        if(!("Wprowadź liczbę.".equals(bgcolor.getText())||"".equals(bgcolor.getText()))) 
                                 {
                                  zapis.print(" "+"-bgcolor "+bgcolor.getText());
                                 } 
                        if(!("Wprowadź liczbę.".equals(bgthresh.getText())||"".equals(bgthresh.getText()))) 
                                 {
                                  zapis.print(" "+"-bgthresh "+bgthresh.getText());
                                 } 
                        if(!("Zaznacz/odznacz opcję.".equals(randinv.getText())||"".equals(randinv.getText()))) 
                                 {
                                     if(randinv.getText().equals("TRUE")){
                                       zapis.print(" "+"-randinv ");
                                      }
                                  
                                 } 
                        
                        if(!("Zaznacz/odznacz opcję.".equals(inv.getText())||"".equals(inv.getText()))) 
                                 {
                                     if(inv.getText().equals("TRUE")){
                                         zapis.print(" "+"-inv ");
                                     }
                                  
                                 }
                        if(!("Wprowadź liczbę.".equals(maxxangle.getText())||"".equals(maxxangle.getText()))) 
                                 {
                                  zapis.print(" "+"-maxxangle "+maxxangle.getText());
                                 }
                        if(!("Wprowadź liczbę.".equals(maxyangle.getText())||"".equals(maxyangle.getText()))) 
                                 {
                                  zapis.print(" "+"-maxyangle "+maxyangle.getText());
                                 }
                        if(!("Wprowadź liczbę.".equals(maxzangle.getText())||"".equals(maxzangle.getText()))) 
                                 {
                                  zapis.print(" "+"-maxzangle "+maxzangle.getText());
                                 }
                        
              zapis.println();
              zapis.println("PAUSE");
              
             zapis.close();
            } catch (FileNotFoundException ex) { 
            Logger.getLogger(CreateSamples.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    public void RunScript(){
      Desktop desktop = Desktop.getDesktop();
       try {
           File File = new File(PathtoScript);
           desktop.open(new File(File.getAbsolutePath()));
       } catch (IOException ex) {
           Logger.getLogger(HaarCascadeTraining.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
}
