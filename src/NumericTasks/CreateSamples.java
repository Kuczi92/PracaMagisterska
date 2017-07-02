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
    private  final String PathtoScript="Haar Training\\CreateSamples.bat";
    private  final String AppName="Haar Training\\Opencv bin\\opencv_createsamples.exe";
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
    
    
    
    
    public CreateSamples(
     Label info,Label vec,Label num, Label w,Label h,Label show,Label img,Label bg,Label bgcolor,Label bgthresh,
     Label inv,Label randinv,Label maxidev, Label maxxangle, Label maxyangle,Label maxzangle){
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
    }
    
    
    
    public void SaveScript(boolean list) {
        try (PrintWriter zapis = new PrintWriter(PathtoScript)) 
            {
              File File = new File(AppName);
               zapis.print("\""+File.getAbsolutePath()+"\""); 
               if(list){
                   if(!("Wybierz plik txt.".equals(info.getText())||null==info.getText())) 
                       {
                        zapis.print(" "+"-info "+"\""+info.getText()+"\"");
                       }
               }
               else{
                   if(!("Wybierz obraz.".equals(img.getText())||null==img.getText())) 
                       {
                        zapis.print(" "+"-img "+"\""+img.getText()+"\"");
                       }
               }
               
              if(!("Stwórz plik vec.".equals(vec.getText())||null==vec.getText())) 
                       {
                        zapis.print(" "+"-vec "+"\""+vec.getText()+"\"");
                       } 
               
              if(!("Wprowadź liczbę.".equals(num.getText())||null==num.getText())) 
                       {
                        zapis.print(" "+"-num "+num.getText());
                       } 
              
              if(!("Wprowadź liczbę.".equals(w.getText())||null==w.getText())) 
                       {
                        zapis.print(" "+"-w "+w.getText());
                       }  
              
              if(!("Wprowadź liczbę.".equals(h.getText())||null==h.getText())) 
                       {
                        zapis.print(" "+"-h "+h.getText());
                       }  
              
              if(!("Zaznacz/odznacz opcję.".equals(show.getText())||null==show.getText())) 
                       {
                        zapis.print(" "+"-show "+show.getText());
                       }
              
              if(!("Wprowadź liczbę.".equals(maxidev.getText())||null==maxidev.getText())) 
                       {
                        zapis.print(" "+"-maxidev "+maxidev.getText());
                       }
              
              
              if(!list){ 
                        if(!("Wybierz plik txt.".equals(bg.getText())||null==bg.getText())) 
                                 {
                                  zapis.print(" "+"-bg "+bg.getText());
                                 } 
                        if(!("Zaznacz/odznacz opcję.".equals(randinv.getText())||null==randinv.getText())) 
                                 {
                                  zapis.print(" "+"-randinv "+randinv.getText());
                                 } 
                        if(!("Wprowadź liczbę.".equals(bgcolor.getText())||null==bgcolor.getText())) 
                                 {
                                  zapis.print(" "+"-bgcolor "+bgcolor.getText());
                                 } 
                        if(!("Wprowadź liczbę.".equals(bgthresh.getText())||null==bgthresh.getText())) 
                                 {
                                  zapis.print(" "+"-bgthreshold "+bgthresh.getText());
                                 } 
                        if(!("Zaznacz/odznacz opcję.".equals(inv.getText())||null==inv.getText())) 
                                 {
                                  zapis.print(" "+"-inv "+inv.getText());
                                 }
                        if(!("Wprowadź liczbę.".equals(maxxangle.getText())||null==maxxangle.getText())) 
                                 {
                                  zapis.print(" "+"-maxxangle "+maxxangle.getText());
                                 }
                        if(!("Wprowadź liczbę.".equals(maxyangle.getText())||null==maxyangle.getText())) 
                                 {
                                  zapis.print(" "+"-maxyangle "+maxyangle.getText());
                                 }
                        if(!("Wprowadź liczbę.".equals(maxzangle.getText())||null==maxzangle.getText())) 
                                 {
                                  zapis.print(" "+"-maxzangle "+maxzangle.getText());
                                 }
                        
                        
              
              }
              zapis.println();
              zapis.println("PAUSE"); 
               
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
