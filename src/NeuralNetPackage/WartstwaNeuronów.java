/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetPackage;

/**
 *
 * @author Quchi
 */

public class WartstwaNeuronów {
    public Neuron[] Neuron;
    private final int LiczbaNeuronów;
    private final FunkcjaAktywacji Aktywacja;
    private double[] WartościWyjść;
    
    public double[] ZwróćWartościWyjść(){
        return WartościWyjść;
    }
    
    public double[] ObliczWartościWyjść(double[] WartościDziedziny){
       double[] Tablica = new double[LiczbaNeuronów];
       WartościWyjść = new double[LiczbaNeuronów];
       for(int i = 0 ; i <LiczbaNeuronów;i++){
         WartościWyjść[i] = Neuron[i].ObliczWyjście(WartościDziedziny);
         Tablica[i] = Neuron[i].ObliczWyjście(WartościDziedziny);
         }
       return Tablica;
   }
    
    public String PodajTypFunkcjiAktywacji(){
        return String.valueOf(Aktywacja);
    }
    public WartstwaNeuronów (int liczbaNeuronów,FunkcjaAktywacji FunkcjaAktywacji){
        this.LiczbaNeuronów=liczbaNeuronów;
        this.Neuron = new Neuron[liczbaNeuronów];
        this.Aktywacja =FunkcjaAktywacji;
        
        for(int i =0 ; i<LiczbaNeuronów ;i++){
            Neuron[i]=new Neuron(LiczbaNeuronów,Aktywacja);
        }
    }

    public void trenujNeurony(double[] PochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu,double[] PochodnaSumyWażonejNeuronówPoWadzeNeuronu,double tempoUczenia){
     //tu gdzieś strzel se ustawienie biasu neuronów 
        for (int n = 0 ; n <LiczbaNeuronów ;n++){ 
           Neuron[n].DouczWagi(PochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu[n],PochodnaSumyWażonejNeuronówPoWadzeNeuronu, tempoUczenia);
           //Neuron[n].UaktualnijUkos(Neuron[n].PobierzUkos() - PochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu[n]);
        }
    }
    
   public void UstawUkosy(double[] Ukosy){
       for (int i = 0 ; i <LiczbaNeuronów ; i++){
           Neuron[i].UaktualnijUkos(Ukosy[i]);
       }
   }
   
   public void UstawWagi(int DanyNeuron,double[] Wagi){
           Neuron[DanyNeuron].WstawWagi(Wagi);
   }
   
   public void DodajWagi(int DanyNeuron,double[] Wagi){
       int rozmiar = Wagi.length;
       for (int i = 0 ; i <rozmiar ; i++){
           Neuron[DanyNeuron].DodajWage(i,Wagi[i]);
       }
   }
   
   public double[] ObliczBłędyCałkowiteNeuronów(){
       double[] Błędy = new double[LiczbaNeuronów];
       for (int n =0 ;n<LiczbaNeuronów;n++){
           Błędy[n] = ObliczBłądCałkowityDlaNeuronu(n);
       }
       return Błędy;
   }

   public  double ObliczBłądCałkowityDlaNeuronu(int DanyNeuron){
      double[] TablicaWag = ZwróćWagiŁącząceSięZDanymNeuronem(DanyNeuron);
      double[] TablicaPochodnych = ZwróćPochodneBłęduNeuronuPoWyjściuNeuronu();
      double BłądDlaNeuronu = 0;
       for (int n =0 ;n<LiczbaNeuronów;n++)
       {
           BłądDlaNeuronu +=TablicaWag[n]*TablicaPochodnych[n];
       }
       return BłądDlaNeuronu;
   }
   
   
   
   
   public double[] ZwróćWagiŁącząceSięZDanymNeuronem(int DanyNeuron){
       //liczbe wag nie neuronów
       double[] Tablica = new double[Neuron[DanyNeuron].LiczbaWag];
       for(int i = 0;i<Tablica.length;i++){
          Tablica[i]=Neuron[i].PodajWartośćWagi(DanyNeuron);
       }
       return Tablica;
   }
   
   public double[] ZwróćWagiDanegoNeuronu(int DanyNeuron){
       //liczbe wag nie neuronów !
     double[] Tablica = new double[Neuron[DanyNeuron].LiczbaWag];
     int rozmiar = Neuron[DanyNeuron].PodajDługośćStarychWag();
      for(int waga = 0;waga<rozmiar;waga++)
      {
       Tablica[waga]= Neuron[DanyNeuron].PodajWartośćWagi(waga);
      }
      return Tablica;
   }
   
   
   public double[] ZwróćPochodneBłęduNeuronuPoWyjściuNeuronu(){
     double[] Tablica = new double[LiczbaNeuronów];
     for(int i =0 ;i<LiczbaNeuronów;i++){
         Tablica[i]=Neuron[i].PochodnaBłęduNeuronuPoWyjściuNeuronu();
     }
    return Tablica;
   }
   
  

   public double[] ObliczWartości(double[] WartościDziedziny){
       double[] Tablica = new double[LiczbaNeuronów];
       for(int i = 0 ; i <LiczbaNeuronów;i++){
         Tablica[i] = Neuron[i].ObliczWyjście(WartościDziedziny);
         }
       return Tablica;
   }
  public int PobierzLiczbeNeuronów(){
      return this.LiczbaNeuronów;
  }
}


