/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praca.magisterska;

import NeuralNetPackage.Numbers;
import NumericTasks.TrainNeuralNet;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 * FXML Controller class
 *
 * @author Quchi
 */
public class TrainingNeuralNetController implements Initializable {
    //Ilosc cykli trenujących
    public int CountLearn;
    //Sciezka do folderu trenujacego
    public String PathToTrainSet;
    //Sciezka do pliku z siecią neuronową
    public String NewPathNeuralNet;
    //referencja do klasy wątku wykonawczej
    public TrainNeuralNet Training;
    //Referencja do obiektu rozpoznawającego cyfry oraz mogących się uczyć rozpoznawać
    public Numbers Numbers;
    //wartość progowa
    public int Treshold;
     //Referencje do labeli oraz ProgressBaru
    @FXML 
    Label NumbersOfCycle;
    @FXML
    Label LabelError0;
    @FXML
    Label LabelError1;
    @FXML
    Label LabelError2;
    @FXML
    Label LabelError3;
    @FXML
    Label LabelError4;
    @FXML
    Label LabelError5;
    @FXML
    Label LabelError6;
    @FXML
    Label LabelError7;
    @FXML
    Label LabelError8;
    @FXML
    Label LabelError9;
    @FXML
    ProgressBar ProgressBar;
    @FXML 
    Label AktualnyProcent;
    //Referencja do Wykresu
    @FXML
    LineChart<Number,Number> ChartOfErrors;
    @FXML
    private NumberAxis xAxis ;

    @FXML
    private NumberAxis yAxis ;

    //Inicjacja zmiennych
    public void SetVariables(Numbers Numbers,String PathToTrainSet,int CountLearn,String NewPathNeuralNet,int Treshold)
    {
       this.Treshold = Treshold;
       this.NewPathNeuralNet = NewPathNeuralNet;
       this.CountLearn =  CountLearn;
       this.PathToTrainSet=PathToTrainSet;
       this.Numbers = Numbers; 
       Training = new TrainNeuralNet(Numbers,PathToTrainSet,CountLearn,NewPathNeuralNet,Treshold,ProgressBar,NumbersOfCycle,
       LabelError0,LabelError1,LabelError2,LabelError3,LabelError4,LabelError5,LabelError6,LabelError7,LabelError8,LabelError9,AktualnyProcent,ChartOfErrors);
    }
    

    //uruchomienie procesu uczącego 
  
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    yAxis = new NumberAxis();
    xAxis = new NumberAxis();
    xAxis.setLabel("Cykl");
    yAxis.setLabel("Wartość błędu:");
  
    ChartOfErrors.setTitle("Wykres postępów");
     XYChart.Series series0 = new XYChart.Series();
        series0.setName("0");
        series0.getData().add(new XYChart.Data(0, 100));
        
     XYChart.Series series1 = new XYChart.Series();
        series1.setName("1");
        series1.getData().add(new XYChart.Data(0, 100));
        
     XYChart.Series series2 = new XYChart.Series();
        series2.setName("2");
        series2.getData().add(new XYChart.Data(0, 100));
        
     XYChart.Series series3 = new XYChart.Series();
        series3.setName("3");
        series3.getData().add(new XYChart.Data(0, 100));   
     XYChart.Series series4 = new XYChart.Series();
        series4.setName("4");
        series4.getData().add(new XYChart.Data(0, 100));  
        
     XYChart.Series series5 = new XYChart.Series();
        series5.setName("5");
        series5.getData().add(new XYChart.Data(0, 100));
        
     XYChart.Series series6 = new XYChart.Series();
        series6.setName("6");
        series6.getData().add(new XYChart.Data(0, 100));   
    
     XYChart.Series series7 = new XYChart.Series();
        series7.setName("7");
        series7.getData().add(new XYChart.Data(0, 100));
          
        
    XYChart.Series series8 = new XYChart.Series();
        series8.setName("8");
        series8.getData().add(new XYChart.Data(0, 100));
          
        
     XYChart.Series series9 = new XYChart.Series();
        series9.setName("9");
        series9.getData().add(new XYChart.Data(0, 100));
          
     ChartOfErrors.getData().addAll(series0, series1, series2,series3,series4,series5,series6,series7,series8,series9);
     ChartOfErrors.setCreateSymbols(false);
    
    }    
    
}
