/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NumericTasks;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/**
 *
 * @author Quchi
 */
public class SaveSettings extends Settings{
   
   public SaveSettings(String PathToFile){
        super(PathToFile);
    }
    public void save(){
        PrintWriter zapis;
        try {
                zapis = new PrintWriter(new File (PathToFile));
                
                    for (Label a1 : this.LabelsTosave) {
                        zapis.println("L_"+a1.getText());
                    }
                    for(Slider a1 :SlidersToSave){
                        zapis.println("S_"+a1.getValue());
                    }
                    for(CheckBox a1 : this.CheckBoxesToSave){
                        zapis.println("C_"+a1.isSelected());
                    }
                    for(ComboBox a1 : this.ComboBoxesToSave){
                        zapis.println("CB_"+a1.getValue().toString());
                    }
                    
                zapis.close();
            } 
        catch (IOException ex) {
            Logger.getLogger(SaveSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
}
