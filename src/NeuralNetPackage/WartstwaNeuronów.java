/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetPackage;

import java.util.ArrayList;

/**
 *
 * @author Quchi
 */

public class WartstwaNeuronów {
    ArrayList<Neuron> Neuron;
    private final int LiczbaNeuronów;
    private final FunkcjaAktywacji Aktywacja;
    private ArrayList<Double> WartościWyjść;
    
    public ArrayList<Double> ZwróćWartościWyjść(){
        return WartościWyjść;
    }
    
    public ArrayList<Double> ObliczWartościWyjść(ArrayList<Double> WartościDziedziny){
       ArrayList<Double> Tablica = new ArrayList<>();
       WartościWyjść = new ArrayList<>();
       for(int i = 0 ; i <LiczbaNeuronów;i++){
         WartościWyjść.add(Neuron.get(i).ObliczWyjście(WartościDziedziny));
         Tablica.add(Neuron.get(i).ObliczWyjście(WartościDziedziny));
         }
       return Tablica;
   }
    
    public String PodajTypFunkcjiAktywacji(){
        return String.valueOf(Aktywacja);
    }
    public WartstwaNeuronów (int liczbaNeuronów,FunkcjaAktywacji FunkcjaAktywacji){
        this.LiczbaNeuronów=liczbaNeuronów;
        this.Neuron = new ArrayList <>();
        this.Aktywacja =FunkcjaAktywacji;
        
        for(int i =0 ; i<LiczbaNeuronów ;i++){
            Neuron.add(new Neuron(LiczbaNeuronów,Aktywacja));
        }
    }

    public void trenujNeurony(ArrayList<Double> PochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu,ArrayList<Double> PochodnaSumyWażonejNeuronówPoWadzeNeuronu,double tempoUczenia){

        for (int n = 0 ; n <LiczbaNeuronów ;n++){ 
           Neuron.get(n).DouczWagi(PochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu.get(n),PochodnaSumyWażonejNeuronówPoWadzeNeuronu, tempoUczenia);
        }
    }
    
   public void UstawUkosy(ArrayList<Double> Ukosy){
       for (int i = 0 ; i <LiczbaNeuronów ; i++){
           Neuron.get(i).UaktualnijUkos(Ukosy.get(i));
       }
   }
   
   public void UstawWagi(int DanyNeuron,ArrayList<Double> Wagi){
       for (int i = 0 ; i <LiczbaNeuronów ; i++){
           Neuron.get(DanyNeuron).UaktualnijWage(i, Wagi.get(i));
       }
   }
   
   public void DodajWagi(int DanyNeuron,ArrayList<Double> Wagi){
       int rozmiar = Wagi.size();
       for (int i = 0 ; i <rozmiar ; i++){
           Neuron.get(DanyNeuron).DodajWage(Wagi.get(i));
       }
   }
   
   public ArrayList<Double> ObliczBłędyCałkowiteNeuronów(){
       ArrayList<Double> Błędy = new ArrayList<>();
       for (int n =0 ;n<LiczbaNeuronów;n++){
           Błędy.add(ObliczBłądCałkowityDlaNeuronu(n));
       }
       return Błędy;
   }

   public ArrayList<Double> ObliczBłędyCałkowiteNeuronów(ArrayList<Double> Błęd){
       ArrayList<Double> Błędy = new ArrayList<>();
       for (int n =0 ;n<LiczbaNeuronów;n++){
           Błędy.add(ObliczBłądCałkowityDlaNeuronu(n));
       }
       return Błędy;
   }
   
   public  double ObliczBłądCałkowityDlaNeuronu(int DanyNeuron){
      ArrayList<Double> TablicaWag = ZwróćWagiŁącząceSięZDanymNeuronem( DanyNeuron);
      ArrayList<Double> TablicaPochodnych = ZwróćPochodneBłęduNeuronuPoWyjściuNeuronu();
      double BłądDlaNeuronu = 0;
       for (int n =0 ;n<LiczbaNeuronów;n++)
       {
           
           BłądDlaNeuronu +=TablicaWag.get(n)*TablicaPochodnych.get(n);
       }
       return BłądDlaNeuronu;
   }
   
   
   
   
   public ArrayList<Double> ZwróćWagiŁącząceSięZDanymNeuronem(int DanyNeuron){
       ArrayList<Double>Tablica = new ArrayList<>();
       for(int i = 0;i<LiczbaNeuronów;i++){
           
          Tablica.add(Neuron.get(i).PodajWartośćWagi(DanyNeuron));
       }
       return Tablica;
   }
   
   public ArrayList<Double> ZwróćWagiDanegoNeuronu(int DanyNeuron){
      ArrayList<Double>Tablica = new ArrayList<>();
     int rozmiar = Neuron.get(DanyNeuron).PodajDługośćStarychWag();
      for(int waga = 0;waga<rozmiar;waga++){
       Tablica.add(Neuron.get(DanyNeuron).PodajWartośćWagi(waga));
      }
      
      return Tablica;
   }
   
   
   public ArrayList<Double> ZwróćPochodneBłęduNeuronuPoWyjściuNeuronu(){
     ArrayList<Double> Tablica = new ArrayList<>();
     for(int i =0 ;i<LiczbaNeuronów;i++){
         Tablica.add(Neuron.get(i).PochodnaBłęduNeuronuPoWyjściuNeuronu());
     }
    return Tablica;
   }
   
  
   @SuppressWarnings("null")
   public ArrayList<Double> ObliczWartości(ArrayList<Double> WartościDziedziny){
       ArrayList<Double> Tablica = new ArrayList<>();
       for(int i = 0 ; i <LiczbaNeuronów;i++){
         Tablica.add(Neuron.get(i).ObliczWyjście(WartościDziedziny));
         }
       return Tablica;
   }
  public int PobierzLiczbeNeuronów(){
      return this.LiczbaNeuronów;
  }
}


