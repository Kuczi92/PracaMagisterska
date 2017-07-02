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
   private final String PathtoScript="Haar Training\\HaarTraining.bat";
   private final String AppName="Haar Training\\Opencv bin\\opencv_haartraining.exe"; 
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
   
   public HaarCascadeTraining(
    Label data, Label vec, Label bg, Label npos, Label nneg,Label nstages,Label precalcValBufSize,
    Label precalcIdxBufSize,Label baseFormatSave,Label numThreads, Label acceptanceRatioBreakValue,
    Label w,Label h,Label featureType, Label mode, Label bt, Label minHitRate, Label maxFalseAlarmRatel,
    Label weightTrimRate,Label maxDepth,Label maxWeakCount
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
   
   public void SaveScript(){
     try (PrintWriter zapis = new PrintWriter(PathtoScript)) 
            {   
            File File = new File(AppName);
               zapis.print("\""+File.getAbsolutePath()+"\""); 
            if(!("Wybierz plik xml.".equals(data.getText())||null==data.getText())) 
            {
                zapis.print(" "+"-data "+data.getText());
            }
            
            if(!("Wybierz plik vec.".equals(vec.getText())||null==vec.getText())) 
            {
                zapis.print(" "+"-vec "+"\""+vec.getText()+"\"");
            }
            
            if(!("Wybierz plik txt.".equals(bg.getText())||null==bg.getText())) 
            {
                zapis.print(" "+"-bg "+"\""+bg.getText()+"\"");
            }
            
            if(!("Wprowadź liczbę.".equals(npos.getText())||null==npos.getText())) 
            {
                zapis.print(" "+"-npos "+npos.getText());
            }
            
            if(!("Wprowadź liczbę.".equals(nneg.getText())||null==nneg.getText())) 
            {
                zapis.print(" "+"-nneg "+nneg.getText());
            }
            
            if(!("Wprowadź liczbę.".equals(nstages.getText())||null==nstages.getText())) 
            {
                zapis.print(" "+"-nstages "+nstages.getText());
            }
            
            if(!("Wprowadź liczbę.".equals(precalcValBufSize.getText())||null==precalcValBufSize.getText())) 
            {
                zapis.print(" "+"-precalcValBufSize "+precalcValBufSize.getText());
            }
            
            if(!("Wprowadź liczbę.".equals(precalcIdxBufSize.getText())||null==precalcIdxBufSize.getText())) 
            {
                zapis.print(" "+"-precalcIdxBufSize "+precalcIdxBufSize.getText());
            }
            
            if(!("Zaznacz/odznacz opcję.".equals(baseFormatSave.getText())||null==baseFormatSave.getText())) 
            {
                zapis.print(" "+"-baseFormatSave "+baseFormatSave.getText());
            }
            
            if(!("Wprowadź liczbę.".equals(numThreads.getText())||null==numThreads.getText())) 
            {
                zapis.print(" "+"-numThreads "+numThreads.getText());
            }
            
            if(!("Wprowadź liczbę.".equals(acceptanceRatioBreakValue.getText())||null==acceptanceRatioBreakValue.getText())) 
            {
                zapis.print(" "+"-acceptanceRatioBreakValue "+acceptanceRatioBreakValue.getText());
            }
            
            if(!("Wprowadź liczbę.".equals(w.getText())||null==w.getText())) 
            {
                zapis.print(" "+"-w "+w.getText());
            }
            
            if(!("Wprowadź liczbę.".equals(h.getText())||null==h.getText())) 
            {
                zapis.print(" "+"-h "+h.getText());
            }
            
            if(!("Wybierz jedno.".equals(featureType.getText())||null==featureType.getText())) 
            {
                zapis.print(" "+"-featureType "+featureType.getText());
            }
            
            if(!("Wybierz jedno.".equals(mode.getText())||null==mode.getText())) 
            {
                zapis.print(" "+"-mode "+mode.getText());
            }
            
            if(!("Wybierz jedno.".equals(bt.getText())||null==bt.getText())) 
            {
                zapis.print(" "+"-bt "+bt.getText());
            }
            
            if(!("Wprowadź liczbę.".equals(minHitRate.getText())||null==minHitRate.getText())) 
            {
                zapis.print(" "+"-minHitRate "+minHitRate.getText());
            }
            
            if(!("Wprowadź liczbę.".equals(maxFalseAlarmRate.getText())||null==maxFalseAlarmRate.getText())) 
            {
                zapis.print(" "+"-maxFalseAlarmRate "+maxFalseAlarmRate.getText());
            } 
            
            if(!("Wprowadź liczbę.".equals(weightTrimRate.getText())||null==weightTrimRate.getText())) 
            {
                zapis.print(" "+"-weightTrimRate "+weightTrimRate.getText());
            } 
            
            if(!("Wprowadź liczbę.".equals(maxDepth.getText())||null==maxDepth.getText())) 
            {
                zapis.print(" "+"-maxDepth "+maxDepth.getText());
            } 
            
            if(!("Wprowadź liczbę.".equals(maxWeakCount.getText())||null==maxWeakCount.getText())) 
            {
                zapis.print(" "+"-maxWeakCount "+maxWeakCount.getText());
            } 
            
            zapis.close();
        } catch (FileNotFoundException ex) {  
           Logger.getLogger(HaarCascadeTraining.class.getName()).log(Level.SEVERE, null, ex);
       }  
   }
   
}
