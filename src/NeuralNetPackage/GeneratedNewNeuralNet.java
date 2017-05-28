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
    
    ArrayList<ArrayList<ArrayList<Double>>> ListaWagPoszególnychNeuronów = new ArrayList<>();
    ArrayList<ArrayList<Double>> ListaUkosówPoszególnychNeuronów = new ArrayList<>();
    
    GeneratedNewNeuralNet(int numberOfInput,int NumberOfLayers,int[] numberOfNeuronPerLayer){
      Random Random = new Random();
  
            ArrayList<ArrayList<Double>> NeuronsWeights = new ArrayList<>();
            ArrayList<Double> NeuronsBias = new ArrayList<>();
            
            for(int Neuron = 0 ; Neuron <numberOfNeuronPerLayer[0];Neuron++ ){
                NeuronsWeights.add(new ArrayList<>());
                for(int input = 0 ; input <numberOfInput;input++){
                 NeuronsWeights.get(Neuron).add(Random.nextGaussian());     
                }
                NeuronsBias.add(Random.nextGaussian());
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
                    NeuronsBias.add(Random.nextGaussian());
                }
            ListaUkosówPoszególnychNeuronów.add(NeuronsBias);
            ListaWagPoszególnychNeuronów.add(NeuronsWeights);
            }
    }
}
