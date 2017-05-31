/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NumericTasks;

import NeuralNetPackage.Numbers;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
/**
 *
 * @author Quchi
 */
public class TrainNeuralNet implements Runnable {
    
    Numbers Numbers;
    String PathToTrainSet;
    int CountLearn;
    String NewPathNeuralNet;
    int Treshold;
    ProgressBar ProgressBarCyclesLearn;
    Label JLabelCycle;
    Label LabelError0;
    Label LabelError1;
    Label LabelError2;
    Label LabelError3;
    Label LabelError4;
    Label LabelError5;
    Label LabelError6;
    Label LabelError7;
    Label LabelError8;
    Label LabelError9;
    Label AktualnyProcent;
     LineChart<Number,Number> ChartOfErrors;
    public TrainNeuralNet(Numbers Numbers,String PathToTrainSet,int CountLearn,String NewPathNeuralNet,int Treshold,
    ProgressBar ProgressBarCyclesLearn,Label JLabelCycle,Label LabelError0,Label LabelError1,Label LabelError2,Label LabelError3,
    Label LabelError4,Label LabelError5,Label LabelError6,Label LabelError7,Label LabelError8,Label LabelError9,Label AktualnyProcent,
    LineChart<Number,Number> ChartOfErrors) {
        this.ChartOfErrors = ChartOfErrors;
        this.ProgressBarCyclesLearn = ProgressBarCyclesLearn;
        this.JLabelCycle = JLabelCycle;
        this.Treshold = Treshold;
        this.NewPathNeuralNet = NewPathNeuralNet;
        this.CountLearn =  CountLearn;
        this.PathToTrainSet=PathToTrainSet;
        this.Numbers = Numbers;
        this.LabelError0 = LabelError0;
        this.LabelError1 = LabelError1;
        this.LabelError2 = LabelError2;
        this.LabelError3 = LabelError3;
        this.LabelError4 = LabelError4;
        this.LabelError5 = LabelError5;
        this.LabelError6 = LabelError6;
        this.LabelError7 = LabelError7;
        this.LabelError8 = LabelError8;
        this.LabelError9 = LabelError9;
        this.AktualnyProcent = AktualnyProcent;
    }
    

    @Override
    public void run()  {

        Numbers.loadNumbersToTrainNeuralNet(PathToTrainSet);
                    try {
                        Numbers.TrainingNeuralnet(CountLearn,ProgressBarCyclesLearn,JLabelCycle,LabelError0,LabelError1,LabelError2,LabelError3,LabelError4,LabelError5,LabelError6,LabelError7,LabelError8,LabelError9,AktualnyProcent,ChartOfErrors);
                    } catch (FileNotFoundException | InterruptedException ex) {
                        Logger.getLogger(TrainNeuralNet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        Numbers.SaveNeuralNet(NewPathNeuralNet);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(TrainNeuralNet.class.getName()).log(Level.SEVERE, null, ex);
                    }
   
        
       
    }
}
