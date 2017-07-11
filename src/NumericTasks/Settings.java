/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NumericTasks;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/**
 *
 * @author Quchi
 */
public class Settings {
   final String PathToFile;
   Label LabelsTosave[];
   Slider SlidersToSave[];
   CheckBox CheckBoxesToSave[];
   ComboBox ComboBoxesToSave[];
    public Settings(String PathToFile){
        this.PathToFile = PathToFile;
    }
    public void loadCheckBoxes(CheckBox ... a){
        this.CheckBoxesToSave = a;
    }
    public void loadComboBoxes(ComboBox ... a){
        this.ComboBoxesToSave = a;
    }
    public void loadLabels(Label ... a){
        this.LabelsTosave = a;
    }
    public void loadSliders(Slider ... b){
       this.SlidersToSave = b;     
    }
}
