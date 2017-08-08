/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetPackage;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Quchi
 */
public class GeneratedNewNeuralNet {
    
   public double[][][] ListaWagPoszególnychNeuronów;
   public double[][] ListaUkosówPoszególnychNeuronów;
    
  public  GeneratedNewNeuralNet(int numberOfInput,int NumberOfLayers,int[] numberOfNeuronPerLayer){
      
      ArrayList<ArrayList<ArrayList<Double>>> ListaWagPoszególnychNeuronów = new ArrayList<>();
      ArrayList<ArrayList<Double>> ListaUkosówPoszególnychNeuronów = new ArrayList<>();
      Random Random = new Random();
  
            ArrayList<ArrayList<Double>> NeuronsWeights = new ArrayList<>();
            ArrayList<Double> NeuronsBias = new ArrayList<>();
            
            for(int Neuron = 0 ; Neuron <numberOfNeuronPerLayer[0];Neuron++ ){
                NeuronsWeights.add(new ArrayList<>());
                for(int input = 0 ; input <numberOfInput;input++){
                 NeuronsWeights.get(Neuron).add(Random.nextGaussian());     
                }
                NeuronsBias.add(-1.0);
            }
            ListaUkosówPoszególnychNeuronów.add(NeuronsBias);
            ListaWagPoszególnychNeuronów.add(NeuronsWeights);
            
            for(int layer = 1 ; layer <NumberOfLayers;layer++ )
            {
            NeuronsWeights = new ArrayList<>();
            NeuronsBias = new ArrayList<>(); 
                for(int Neuron = 0 ; Neuron <numberOfNeuronPerLayer[layer];Neuron++ ){
                    NeuronsWeights.add(new ArrayList<>());
                    int numberOfInputsPrevLayer = ListaWagPoszególnychNeuronów.get(layer-1).size();
                    for(int input = 0 ; input <numberOfInputsPrevLayer;input++){
                     NeuronsWeights.get(Neuron).add(Random.nextGaussian());     
                    }
                    NeuronsBias.add(-1.0);
                }
            ListaUkosówPoszególnychNeuronów.add(NeuronsBias);
            ListaWagPoszególnychNeuronów.add(NeuronsWeights);
            }
            
            //transponowanie na tablice zwykłe
            this.ListaWagPoszególnychNeuronów = new double[ListaWagPoszególnychNeuronów.size()][][];
            for(int i = 0 ; i<this.ListaWagPoszególnychNeuronów.length;i++){
              this.ListaWagPoszególnychNeuronów[i] = new double[ListaWagPoszególnychNeuronów.get(i).size()][];
              for(int x = 0; x<this.ListaWagPoszególnychNeuronów[i].length;x++){
                 int ssize  = ListaWagPoszególnychNeuronów.get(i).get(x).size();
                 Double[] e = ListaWagPoszególnychNeuronów.get(i).get(x).toArray(((new Double[ssize])));
                 this.ListaWagPoszególnychNeuronów[i][x] = new double[ssize]; 
                 for(int s = 0 ;s<ssize;s++){
                      this.ListaWagPoszególnychNeuronów[i][x][s] = e[s];
                 }
              } 
            }
            
            this.ListaUkosówPoszególnychNeuronów = new double[ListaUkosówPoszególnychNeuronów.size()][];
            for(int i = 0; i<this.ListaUkosówPoszególnychNeuronów.length;i++){
                int ssize  = ListaUkosówPoszególnychNeuronów.get(i).size();
                this.ListaUkosówPoszególnychNeuronów[i] = new double[ssize];
                Double[] e = ListaUkosówPoszególnychNeuronów.get(i).toArray(((new Double[ssize])));
                for(int x = 0 ; x < ssize;x++){
                    this.ListaUkosówPoszególnychNeuronów[i][x]  = e[x];
                }
            }
  }
}
