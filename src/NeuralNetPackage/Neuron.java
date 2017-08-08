/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetPackage;

import static java.lang.Math.exp;
import static java.lang.Math.pow;


/**
 *
 * @author Quchi
 */
public class Neuron {
    
  private double WagaFunkcji[]; //weight
  private double UkosFunkcji; //bias
  private final  FunkcjaAktywacji Aktywacja; //funkcja aktywacji neuronu
  private double beta =1;
  private double WartośćWyjściaNeuronu;
  private double WartośćFunkcjiSumatora;
  private double StaraWagaFunkcji[];
  private double PochodnaBłęduNeuronuPoWyjściuNeuronu;
  
  public int LiczbaWag;
  public double PochodnaBłęduNeuronuPoWyjściuNeuronu(){
      return PochodnaBłęduNeuronuPoWyjściuNeuronu;
  }
  
   public double WartoścWyjścieNeuronu(){
       return WartośćWyjściaNeuronu;
   }
   
   public double WartośćFunkcjiSumatora(){
       return WartośćFunkcjiSumatora;
   }
   
   public Neuron(double[] WagaFunkcji,double UkosFunkcji,FunkcjaAktywacji FunkcjaAktywacji){
       this.LiczbaWag =  WagaFunkcji.length;
       this.UkosFunkcji=UkosFunkcji;
       this.WagaFunkcji=WagaFunkcji;
       StaraWagaFunkcji = new double[WagaFunkcji.length];
       this.Aktywacja=FunkcjaAktywacji;
   }
   
   public Neuron(int LiczbaWag,double WartośćUkosu,FunkcjaAktywacji FunkcjaAktywacji){
      this.LiczbaWag =  LiczbaWag;
      this.WagaFunkcji = new double[LiczbaWag];
      StaraWagaFunkcji = new double[WagaFunkcji.length];
      this.UkosFunkcji = WartośćUkosu;
      this.Aktywacja=FunkcjaAktywacji;
   }
   
   public Neuron(int LiczbaNeuronów,FunkcjaAktywacji FunkcjaAktywacji){
      this.WagaFunkcji = new double[LiczbaNeuronów];
      StaraWagaFunkcji = new double[WagaFunkcji.length];
      this.UkosFunkcji = -1;
      this.Aktywacja=FunkcjaAktywacji;
   }
   

  public void ustawBete(double beta){
      this.beta = beta;
  }
  
  public double pobierzBete(){
      return beta;
  }
  
  public String PobierzListęWag(){
      StringBuilder ListaWag = new StringBuilder();
       int rozmiar = WagaFunkcji.length;
      for(int waga = 0 ; waga<rozmiar;waga++){
        ListaWag.append(WagaFunkcji[waga]);
        
               if(waga==rozmiar-1)
               {
                   break;
               }
               ListaWag.append(" ");
      }
      String zwracanaWartośc = ListaWag.toString();
      return zwracanaWartośc;
  }
  
  public double ObliczWyjście(double[] zmienne)
  {
      
      double wartośćfunkcji = 0;
      int rozmiar = zmienne.length;
      for(int i = 0 ; i<rozmiar;i++)
        {
          wartośćfunkcji+=zmienne[i]*WagaFunkcji[i];
        }
      wartośćfunkcji+=UkosFunkcji;
       
      WartośćFunkcjiSumatora= wartośćfunkcji;
      
      double wartośc_zwracana = 0;
      
      switch (Aktywacja){
         case LINIOWA:
           wartośc_zwracana=wartośćfunkcji;
         break;
          
         case OBCIETA_LINIOWA:
                if(wartośćfunkcji>1)
                     {
                         wartośc_zwracana = 1;
                     }
                else if(wartośćfunkcji<-1)
                     {
                         wartośc_zwracana = -1;
                     }
                else
                     {
                         wartośc_zwracana=wartośćfunkcji;
                     }
         break;
          
         
         case PROGOWA_UNIPOLARNA:
                if(wartośćfunkcji>=0)
                     {
                         wartośc_zwracana = 1;
                     }
                else if(wartośćfunkcji<0)
                     {
                         wartośc_zwracana = 0;
                     }
               
         break;    
              
         case PROGOWA_BIPOLARNA:
                 if(wartośćfunkcji>=0)
                     {
                         wartośc_zwracana = 1;
                     }
                else if(wartośćfunkcji<0)
                     {
                         wartośc_zwracana = -1;
                     }
         break;
         
         case SIGMOIDALNA_UNIPOLARNA:
           wartośc_zwracana = 1/(1+exp(-beta*wartośćfunkcji));  
         break;
         
         case SIGMOIDALNA_BIPOLARNA:
            wartośc_zwracana = 2/(1+exp(-beta*wartośćfunkcji))-1;
         break;
         
         
         case GAUSSA:
             wartośc_zwracana = exp(-(beta/pow(wartośćfunkcji,2)));
         break;    
          
      }
      WartośćWyjściaNeuronu = wartośc_zwracana;
      return wartośc_zwracana;
  } 
  
  
  public void DouczWagi(double PochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu,double[] PochodnaSumyWażonejNeuronówPoWadzeNeuronu,double tempoUczenia){
    
    double PochodnaWyjściaAktywującegoPoSumieWażonejWartościNeuronu = ObliczPochodnąWyjściaAktywującegoPoSumieWażonejNeuronu(WartośćWyjściaNeuronu);
    int rozmiar = WagaFunkcji.length;
    PochodnaBłęduNeuronuPoWyjściuNeuronu = PochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu*PochodnaWyjściaAktywującegoPoSumieWażonejWartościNeuronu;
    for(int Wagi = 0 ;Wagi <rozmiar;Wagi++ )
    {
       //double PochodnaSumyWażonejNeuronówPoWadzeNeuronu=WagaFunkcji.get(Wagi); 
       double Gradient = PochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu
                         *PochodnaWyjściaAktywującegoPoSumieWażonejWartościNeuronu
                         *PochodnaSumyWażonejNeuronówPoWadzeNeuronu[Wagi];
       UaktualnijWage(Wagi,WagaFunkcji[Wagi]-tempoUczenia*Gradient);
    }
      
      
  }
  
  
  
  public double ObliczPochodnąWyjściaAktywującegoPoSumieWażonejNeuronu(double wartośc){
      double WartośćPochodnej = 0 ;
      
      try{
                switch (Aktywacja)
                {
                case LINIOWA:
                 WartośćPochodnej = wartośc;
                break;

                case OBCIETA_LINIOWA:
                  if(wartośc>1)
                            {
                                WartośćPochodnej = 1;
                            }
                       else if(wartośc<-1)
                            {
                                WartośćPochodnej = -1;
                            }
                       else
                            {
                                WartośćPochodnej=wartośc;
                            }     
                break;

                case SIGMOIDALNA_UNIPOLARNA:
                WartośćPochodnej= beta*wartośc*(1-wartośc);
                break;

                case SIGMOIDALNA_BIPOLARNA:
                WartośćPochodnej =  beta*(2/(exp(wartośc)+1) - 2/(pow((exp(wartośc)+1),2))); 
                break;

                case GAUSSA:
                WartośćPochodnej = beta * 2*exp(beta/pow(wartośc,2))/pow(wartośc,3);  
                break;
                
                default:
                
               }
      
          
        }
      
      
       catch(IllegalArgumentException e){
           System.out.println("Nie da się policzyć pochodnej z tej funkcji");
       }
      
      return WartośćPochodnej;
  }
  
  public void WstawWagi(double[] a){
      StaraWagaFunkcji = new double[a.length];
      LiczbaWag = a.length;
      WagaFunkcji=a;
      
  }
  
  public void DodajWage(int index ,double wartosc){
      WagaFunkcji[index]=wartosc;
  }
  
  
  public int PodajDługośćStarychWag(){
      return StaraWagaFunkcji.length;
  }
  
  public double PodajWartośćWagi(int waga){
      return StaraWagaFunkcji[waga];
  }
  public double PobierzUkos(){
      return UkosFunkcji;
  }
       
   public void UaktualnijWage(int i, double wartosc){
       StaraWagaFunkcji[i]=WagaFunkcji[i];
       WagaFunkcji[i] = wartosc;
   }
   
   public void UaktualnijUkos(double wartosc){
       UkosFunkcji=wartosc;
   }
}
