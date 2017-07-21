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
public class HaarCascadeTraining {
   private static String PathtoScript="Haar_Training\\HaarTraining.bat";
   private final String AppName="Haar_Training\\Opencv bin\\opencv_haartraining.exe"; 
   private final String SecondAppName = "Haar_Training\\Opencv bin\\opencv_traincascade.exe";
   private final Label data;
   private final Label vec;
   private final Label bg;
   private final Label npos;
   private final Label nneg;
   private final Label nstages;
   private final Label precalcValBufSize;
   private final Label precalcIdxBufSize;
   private final Label baseFormatSave;
   private final Label numThreads;
   private final Label acceptanceRatioBreakValue;
   private final Label w;
   private final Label h;
   private final Label featureType;
   private final Label mode;
   private final Label bt;
   private final Label minHitRate;
   private final Label maxFalseAlarmRate;
   private final Label weightTrimRate;
   private final Label maxDepth;
   private final Label maxWeakCount;
   private final Label nsplits;
   
   
   private static final String PATH_TO_DATA2CASCADE_SCRIPT = "Haar_Training\\ConvertToXML.bat";
   private static final String APP_NAME_DATA2CASCADE_SCRIPT = "Haar_Training\\Opencv bin\\haarconv.exe";
   
   public static void ChangeStriptPath(String a){
       PathtoScript = a;
   }
   
   
   public  void DataToCascadeXmlSave(Label PathToFolder, Label PathToXMLfile){
       try (PrintWriter zapis = new PrintWriter(PATH_TO_DATA2CASCADE_SCRIPT)) 
            {
                File File = new File(APP_NAME_DATA2CASCADE_SCRIPT);
                zapis.print("\""+File.getAbsolutePath()+"\""); 
                if(!("Wybierz folder.".equals(PathToFolder.getText())||"".equals(PathToFolder.getText()))) 
                    {
                        zapis.print(" "+PathToFolder.getText().substring(PathToFolder.getText().indexOf("Haar_Training")));
                    }
                
                if(!("Wybierz plik xml.".equals(PathToXMLfile.getText())||"".equals(PathToXMLfile.getText()))) 
                    {
                        zapis.print(" "+"\""+PathToXMLfile.getText()+"\"");
                    }
                if(!("Wprowadź liczbę.".equals(w.getText())||"".equals(w.getText()))) 
                    {
                        zapis.print(" "+w.getText());
                    }
                if(!("Wprowadź liczbę.".equals(h.getText())||"".equals(h.getText()))) 
                    {
                        zapis.print(" "+h.getText());
                    }
              zapis.println();
              zapis.println("PAUSE");
              
             zapis.close();
            } catch (FileNotFoundException ex) {
           Logger.getLogger(HaarCascadeTraining.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   
   public static void DataToCascadeXmlOpen(){
       Desktop desktop = Desktop.getDesktop();
       try {
           File File = new File(PATH_TO_DATA2CASCADE_SCRIPT);
           desktop.open(new File(File.getAbsolutePath()));
       } catch (IOException ex) {
           Logger.getLogger(HaarCascadeTraining.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   public HaarCascadeTraining(
    Label data, Label vec, Label bg, Label npos, Label nneg,Label nstages,Label precalcValBufSize,
    Label precalcIdxBufSize,Label baseFormatSave,Label numThreads, Label acceptanceRatioBreakValue,
    Label w,Label h,Label featureType, Label mode, Label bt, Label minHitRate, Label maxFalseAlarmRatel,
    Label weightTrimRate,Label maxDepth,Label maxWeakCount,Label nsplits
   )
   {
    this.data = data;
    this.vec = vec;
    this.bg=bg;
    this.npos=npos;
    this.nneg =nneg;
    this.nstages = nstages;
    this.precalcValBufSize = precalcValBufSize;
    this.precalcIdxBufSize = precalcIdxBufSize;
    this.baseFormatSave = baseFormatSave;
    this.numThreads = numThreads;
    this.acceptanceRatioBreakValue = acceptanceRatioBreakValue;
    this.w = w;
    this.h = h;
    this.featureType = featureType;
    this.mode = mode;
    this.bt = bt;
    this.minHitRate = minHitRate;
    this.maxFalseAlarmRate = maxFalseAlarmRatel;
    this.weightTrimRate = weightTrimRate;
    this.maxDepth = maxDepth;
    this.maxWeakCount  = maxWeakCount;
    this.nsplits = nsplits;
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
   
   public void SaveScript(boolean useNewer){
     try (PrintWriter zapis = new PrintWriter(PathtoScript)) 
            {  
               File File;
               if(!useNewer){
                File = new File(AppName);
               }
               else{
                File = new File(SecondAppName);  
               }
               
               
               zapis.print("\""+File.getAbsolutePath()+"\""); 
            if(!("Wybierz folder.".equals(data.getText())||"".equals(data.getText()))) 
            {
                zapis.print(" "+"-data "+"\""+data.getText()+"\"");
            }
            
            if(!("Wybierz plik vec.".equals(vec.getText())||"".equals(vec.getText()))) 
            {
                zapis.print(" "+"-vec "+"\""+vec.getText()+"\"");
            }
            
            if(!("Wybierz plik txt.".equals(bg.getText())||"".equals(bg.getText())))
            {
                zapis.print(" "+"-bg "+bg.getText().substring(bg.getText().indexOf("Haar_Training")));
            }
            
            if(!("Wprowadź liczbę.".equals(npos.getText())||"".equals(npos.getText()))) 
            {
                zapis.print(" "+"-npos "+npos.getText());
            }
            
            if(!("Wprowadź liczbę.".equals(nneg.getText())||"".equals(nneg.getText()))) 
            {
                zapis.print(" "+"-nneg "+nneg.getText());
            }
            
            if(!("Wprowadź liczbę.".equals(nstages.getText())||"".equals(nstages.getText()))) 
            {
                zapis.print(" "+"-nstages "+nstages.getText());
            }
            
            if(!("Wprowadź liczbę.".equals(nsplits.getText())||"".equals(nsplits.getText()))) 
            {
                zapis.print(" "+"-nsplits "+nsplits.getText());
            }
            
            if(!("Wprowadź liczbę.".equals(precalcValBufSize.getText())||"".equals(precalcValBufSize.getText())))
            {
                zapis.print(" "+"-precalcValBufSize "+precalcValBufSize.getText());
            }
            
            if(!("Wprowadź liczbę.".equals(precalcIdxBufSize.getText())||"".equals(precalcIdxBufSize.getText()))) 
            {
                zapis.print(" "+"-precalcIdxBufSize "+precalcIdxBufSize.getText());
            }
            
            if(!("Zaznacz/odznacz opcję.".equals(baseFormatSave.getText())||"".equals(baseFormatSave.getText())))
            {
                zapis.print(" "+"-baseFormatSave "+baseFormatSave.getText());
            }
            
            if(!("Wprowadź liczbę.".equals(numThreads.getText())||"".equals(numThreads.getText()))) 
            {
                zapis.print(" "+"-numThreads "+numThreads.getText());
            }
            
            if(!("Wprowadź liczbę.".equals(acceptanceRatioBreakValue.getText())||"".equals(acceptanceRatioBreakValue.getText())) )
            {
                zapis.print(" "+"-acceptanceRatioBreakValue "+acceptanceRatioBreakValue.getText());
            }
            
            if(!("Wprowadź liczbę.".equals(w.getText())||"".equals(w.getText()))) 
            {
                zapis.print(" "+"-w "+w.getText());
            }
            
            if(!("Wprowadź liczbę.".equals(h.getText())||"".equals(h.getText())))
            {
                zapis.print(" "+"-h "+h.getText());
            }
            
            if(!("Wybierz jedno.".equals(featureType.getText())||"".equals(featureType.getText()))) 
            {
                zapis.print(" "+"-featureType "+featureType.getText());
            }
            
            if(!("Wybierz jedno.".equals(mode.getText())||"".equals(mode.getText()))) 
            {
                zapis.print(" "+"-mode "+mode.getText());
            }
            
            if(!("Wybierz jedno.".equals(bt.getText())||"".equals(bt.getText()))) 
            {
                zapis.print(" "+"-bt "+bt.getText());
            }
            
            if(!("Wprowadź liczbę.".equals(minHitRate.getText())||"".equals(minHitRate.getText()))) 
            {
                zapis.print(" "+"-minHitRate "+minHitRate.getText());
            }
            
            if(!("Wprowadź liczbę.".equals(maxFalseAlarmRate.getText())||"".equals(maxFalseAlarmRate.getText()))) 
            {
                zapis.print(" "+"-maxFalseAlarmRate "+maxFalseAlarmRate.getText());
            } 
            
            if(!("Wprowadź liczbę.".equals(weightTrimRate.getText())||"".equals(weightTrimRate.getText()))) 
            {
                zapis.print(" "+"-weightTrimRate "+weightTrimRate.getText());
            } 
            
            if(!("Wprowadź liczbę.".equals(maxDepth.getText())||"".equals(maxDepth.getText()))) 
            {
                zapis.print(" "+"-maxDepth "+maxDepth.getText());
            } 
            
            if(!("Wprowadź liczbę.".equals(maxWeakCount.getText())||"".equals(maxWeakCount.getText()))) 
            {
                zapis.print(" "+"-maxWeakCount "+maxWeakCount.getText());
            } 
            
              zapis.println();
              zapis.println("PAUSE");
              
             zapis.close();
        } catch (FileNotFoundException ex) {  
           Logger.getLogger(HaarCascadeTraining.class.getName()).log(Level.SEVERE, null, ex);
       }  
   }
   
}
