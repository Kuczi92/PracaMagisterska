/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NumericTasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quchi
 */
public class LoadSettings extends Settings {
    
 private ArrayList<Double> ValuesFromSliders;
 private ArrayList<String> ValuesFromLabels;
 private ArrayList<Boolean> ValuesFromCheckBox;
 private ArrayList<String> ValuesFromComboBox;
  
   public LoadSettings(String Path){
       super(Path);
   }
   
   public ArrayList<Double> GetValuesFromSliders(){
    return ValuesFromSliders;   
   }
   public ArrayList<String> GetValuesFromLabels(){
    return ValuesFromLabels;   
   }
   public ArrayList<Boolean> GetValuesFromCheckBox(){
    return ValuesFromCheckBox;   
   }
   public ArrayList<String> GetValuesFromComboBox(){
    return ValuesFromComboBox;   
   }
   public void loadValues(){
      ValuesFromSliders = new ArrayList<>();
      ValuesFromLabels  = new ArrayList<>();
      ValuesFromCheckBox = new ArrayList<>();
      ValuesFromComboBox = new ArrayList<>();
       File file = new File(PathToFile);
       Scanner in = null;
       try {
           in = new Scanner(file);
           while(in.hasNext()){
           String zdanie = in.nextLine();
           String sentence[] = zdanie.split("_");
           
               switch (sentence[0]) {
                   case "S":
                       ValuesFromSliders.add(Double.valueOf(sentence[1]));
                       break;
                   case "L":
                       ValuesFromLabels.add(sentence[1]);
                       break;
                   case "C":
                       ValuesFromCheckBox.add(Boolean.valueOf(sentence[1]));
                       break;
                   case "CB":
                       ValuesFromComboBox.add((sentence[1]));
                       break;
                   default:
                       break;
               }
           
           
           }
           in.close();
       } catch (FileNotFoundException ex) {
           Logger.getLogger(LoadSettings.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   
   public void load(){
       
       File file = new File(PathToFile);
       Scanner in = null;
       try {
           in = new Scanner(file);
           
           int currentLineS = 0;
           int currentLineL = 0;
           int currentLineC = 0;
           int currentLineCB =0;
           while(in.hasNext()){
           String zdanie = in.nextLine();
           String sentence[] = zdanie.split("_");
           
               switch (sentence[0]) {
                   case "S":
                       SlidersToSave[currentLineS].setValue(Double.valueOf(sentence[1]));
                       currentLineS++;
                       break;
                   case "L":
                       LabelsTosave[currentLineL].setText(sentence[1]);
                       currentLineL++;
                       break;
                   case "C":
                       CheckBoxesToSave[currentLineC].setSelected(Boolean.valueOf(sentence[1]));
                       currentLineC++;
                       break;
                   case "CB":
                       ComboBoxesToSave[currentLineCB].getSelectionModel().select((sentence[1]));
                       currentLineCB++;
                       break;
                   default:
                       break;
               }
           
           
           }
           in.close();
       } catch (FileNotFoundException ex) {
           Logger.getLogger(LoadSettings.class.getName()).log(Level.SEVERE, null, ex);
       }
      
    }
}
