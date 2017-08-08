/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetPackage;

import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import static NeuralNetPackage.FunkcjaAktywacji.OBCIETA_LINIOWA;

/**
 *
 * @author Quchi
 */
public class SiećNeuronowa 
{
    public WartstwaNeuronów[] WartstwaNeuronów;
    private final int LiczbaWarstw;
    private final FunkcjaAktywacji Aktywacja;
    public int x;
    public int y;
  
    
    public SiećNeuronowa(String lokalizacjaPliku) throws FileNotFoundException{
       ArrayList<WartstwaNeuronów> WartstwaNeuronów = new ArrayList<>();
        
        int liczbaWykrytychWarstwSieci =0;
        int LiczbaWykrytychNeuronówWWarstwie =0;
        this.Aktywacja = OBCIETA_LINIOWA;
        
        File file = new File(lokalizacjaPliku);
        Scanner in = new Scanner(file);
        while(in.hasNext()){
        String zdanie = in.nextLine();
        
                                        if(zdanie.startsWith("Size")){
                                            String[] linia = zdanie.split(" ");
                                            this.x = Integer.valueOf(linia[1]);
                                            this.y = Integer.valueOf(linia[2]);
                                        }
        
                                        if(zdanie.startsWith("War")){
                                            String[] Linia = zdanie.split(" ");
                                            WartstwaNeuronów.add(new WartstwaNeuronów(Integer.valueOf(Linia[1]),FunkcjaAktywacji.valueOf(Linia[2])));

                                            liczbaWykrytychWarstwSieci++;
                                            LiczbaWykrytychNeuronówWWarstwie =0;
                                        }
                                        
                                        
                                        else if(zdanie.startsWith("N"))
                                        {    
                                            String[] Linia = zdanie.split(":");
                                            String WagiNeuronu = Linia[1].substring(Linia[1].indexOf("[")+1, Linia[1].indexOf("]"));
                                            String UkosNeuronu = Linia[2].substring(Linia[2].indexOf("[")+1, Linia[2].indexOf("]"));
                                            String[] WagaNeuronu = (WagiNeuronu.split(" "));

                                            
                                            
                                            WartstwaNeuronów.get(liczbaWykrytychWarstwSieci-1).UstawWagi(LiczbaWykrytychNeuronówWWarstwie, StringToDoubleTablica(WagaNeuronu));
                                            WartstwaNeuronów.get(liczbaWykrytychWarstwSieci-1).Neuron[LiczbaWykrytychNeuronówWWarstwie].UaktualnijUkos(Double.valueOf(UkosNeuronu));
                                            
                                            LiczbaWykrytychNeuronówWWarstwie++;
                                        }
        
        
        }
        
        this.LiczbaWarstw = liczbaWykrytychWarstwSieci;
        
        
        this.WartstwaNeuronów = WartstwaNeuronów.toArray(new WartstwaNeuronów[WartstwaNeuronów.size()]);
        
        
    }
    
    /** 
    @param ListaWagPoszególnychNeuronów jest to lista list Wag dla każdego neuronu
    @param ListaUkosówPoszególnychNeuronów jest to lista ukosu dla każdego neuronu
     * @param FunkcjaAktywacji
    */
    public SiećNeuronowa(double[][][] ListaWagPoszególnychNeuronów,double[][] ListaUkosówPoszególnychNeuronów,FunkcjaAktywacji FunkcjaAktywacji)
    {
        this.WartstwaNeuronów = new WartstwaNeuronów[ListaWagPoszególnychNeuronów.length];
        this.LiczbaWarstw = ListaWagPoszególnychNeuronów.length;
        this.Aktywacja = FunkcjaAktywacji;
        for(int warstwa = 0 ; warstwa<LiczbaWarstw;warstwa++)
        { 
            this.WartstwaNeuronów[warstwa] = new WartstwaNeuronów(ListaWagPoszególnychNeuronów[warstwa].length,Aktywacja);
                int liczbaNeuronów = ListaWagPoszególnychNeuronów[warstwa].length;
                for(int neuron = 0 ; neuron<liczbaNeuronów;neuron++ )
                {
                 WartstwaNeuronów[warstwa].UstawUkosy(ListaUkosówPoszególnychNeuronów[warstwa]);
                 WartstwaNeuronów[warstwa].UstawWagi(neuron, ListaWagPoszególnychNeuronów[warstwa][neuron]);   
                }
        }
    }
    
    
    public SiećNeuronowa(int[] tab,FunkcjaAktywacji FunkcjaAktywacji)
    {
        Random Generator = new Random();
        this.WartstwaNeuronów = new WartstwaNeuronów[tab.length];
        this.LiczbaWarstw= tab.length;
        this.Aktywacja =FunkcjaAktywacji;
        int liczbaNeuronówWWarstwie;
        for(int warstwa = 0 ; warstwa<LiczbaWarstw;warstwa++)
        {
            
            this.WartstwaNeuronów[warstwa] = new WartstwaNeuronów(tab[warstwa],Aktywacja);
            
                int liczbaNeuronów = tab[warstwa];
                
                
                for(int neuron = 0 ; neuron<liczbaNeuronów;neuron++ ){
                    
                 WartstwaNeuronów[warstwa].Neuron[neuron].UaktualnijUkos(Generator.nextGaussian());
                   
                   liczbaNeuronówWWarstwie = WartstwaNeuronów[warstwa].PobierzLiczbeNeuronów();
                   
                        if(warstwa>0){
                            liczbaNeuronówWWarstwie = WartstwaNeuronów[warstwa-1].PobierzLiczbeNeuronów();
                        }
                        else{
                            liczbaNeuronówWWarstwie = WartstwaNeuronów[warstwa].PobierzLiczbeNeuronów();
                        }
                   
                            for( int Waga = 0 ;Waga <liczbaNeuronówWWarstwie;Waga++){
                                WartstwaNeuronów[warstwa].Neuron[neuron].DodajWage(Waga,Generator.nextGaussian());
                            }
                    
                    
                }
           }
           
    }

   

    
    
    public double[] ObliczWartościPotrzebneDotrenowaniaSieci(double[] WartościDziedziny){
       double[] Tablica = WartstwaNeuronów[0].ObliczWartościWyjść(WartościDziedziny);
       for(int i = 1; i<LiczbaWarstw;i++)
       {
          Tablica = WartstwaNeuronów[i].ObliczWartościWyjść(Tablica);
       }
       
       return Tablica;
   }
    
    
    
    
    public double[] ObliczWartości(double[] WartościDziedziny){
       double[] Tablica = WartstwaNeuronów[0].ObliczWartości(WartościDziedziny);
       for(int i = 1; i<LiczbaWarstw;i++)
       {
          Tablica = WartstwaNeuronów[i].ObliczWartości(Tablica);
       }
       
       return Tablica;
   }
    
    public double TrenujSieć(double[] Wejscie,double[] Wyjście,double TempoUczenia)
    {
        
        //operujemy głównie na warstwach
        double[] Wynik = ObliczWartościPotrzebneDotrenowaniaSieci(Wejscie);
        double BłądSieciNeuronowej = ObliczBłądSieciNeuronowej(Wyjście,Wynik); // czyli nasze E total we wzorze
        double[] BłądWyjścia_PochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu = ObliczPochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu(Wyjście,Wynik);// czyli błędy z poszczególnych wyjść
        
       
        for(int warstwaNeuronu = LiczbaWarstw; warstwaNeuronu>0;warstwaNeuronu--)
        {
            
            if(warstwaNeuronu==LiczbaWarstw-2){
              BłądWyjścia_PochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu = ObliczBłędyNeuronówDoNastępnejWartstwy(WartstwaNeuronów[warstwaNeuronu]);
              WartstwaNeuronów[warstwaNeuronu-1].trenujNeurony(BłądWyjścia_PochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu,Wejscie, TempoUczenia);   
            }
           else  if(!(warstwaNeuronu == LiczbaWarstw))
            {
                BłądWyjścia_PochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu = ObliczBłędyNeuronówDoNastępnejWartstwy(WartstwaNeuronów[warstwaNeuronu]);
                WartstwaNeuronów[warstwaNeuronu-1].trenujNeurony(BłądWyjścia_PochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu,Wejscie, TempoUczenia);
            }
            else{
               WartstwaNeuronów[warstwaNeuronu-1].trenujNeurony(BłądWyjścia_PochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu,WartstwaNeuronów[warstwaNeuronu-2].ZwróćWartościWyjść(), TempoUczenia);

            }

        }
       return BłądSieciNeuronowej; 
    }
    
    
    public double[] ObliczBłędyNeuronówDoNastępnejWartstwy(WartstwaNeuronów WartstwaNeuronów){
        double[] PochodneBłędu =  WartstwaNeuronów.ZwróćPochodneBłęduNeuronuPoWyjściuNeuronu();
        int LiczbaWag = WartstwaNeuronów.Neuron[0].PodajDługośćStarychWag();
        double[] Lista = new double[LiczbaWag];
        
           for(int DanyNeuron = 0 ;DanyNeuron <WartstwaNeuronów.PobierzLiczbeNeuronów();DanyNeuron++)
           {
              double[] TablicaWag = WartstwaNeuronów.ZwróćWagiDanegoNeuronu(DanyNeuron);
                for (int waga = 0 ; waga<LiczbaWag ; waga ++)
                { 
                 if(DanyNeuron>0)
                 {
                  Lista[waga] = PochodneBłędu[DanyNeuron]*TablicaWag[waga]+Lista[waga];
                 }
                 else
                 {   
                  Lista[waga] = PochodneBłędu[DanyNeuron]*TablicaWag[waga];    
                 }
                  
               }
       }
        
        
        return Lista;
    }
    
   public void ZapiszSiećDoPliku(String NazwaPliku) throws FileNotFoundException{
        try (PrintWriter zapis = new PrintWriter(NazwaPliku)) 
        {   
            
            zapis.println("Size "+this.x+" "+this.y);
                                           
            for(int Warstwa = 0 ; Warstwa<LiczbaWarstw;Warstwa++){
                zapis.println("War "+WartstwaNeuronów[Warstwa].PobierzLiczbeNeuronów()+" "+WartstwaNeuronów[Warstwa].PodajTypFunkcjiAktywacji());
                int LiczbaNeuronów = WartstwaNeuronów[Warstwa].PobierzLiczbeNeuronów();
                
                for(int DanyNeuron = 0;DanyNeuron<LiczbaNeuronów;DanyNeuron++)
                    {
                        zapis.println("N:W ["+WartstwaNeuronów[Warstwa].Neuron[DanyNeuron].PobierzListęWag()+"]:"+"B ["+WartstwaNeuronów[Warstwa].Neuron[DanyNeuron].PobierzUkos()+"]");
                        
                    }
                    
                
            }
           
            
            
            zapis.close();
        }
        
   } 
    
    public double[] ObliczPochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu(double[] Cel,double[] Wynik){
     double[] WartościPochodnych = new double[Cel.length];
     int rozmiarCel =  Cel.length;
     int rozmiarWynik = Wynik.length;
     
     
      try{
          if(!(rozmiarCel==rozmiarWynik)){
              throw new ArrayIndexOutOfBoundsException("Tablice: Cele i wyniki nie maja takiego samego rozmiaru w funkcji ObliczBłądSieciNeuronowej");
          }
                for(int x = 0 ;x<rozmiarCel;x++)
                {
                    
                        WartościPochodnych[x]=(Wynik[x]-Cel[x]);
                    
                }  
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Niepoprawny parametr, rozmiar tablicy to: "+e.getMessage());
        }
     return WartościPochodnych;
    }
    
    public double ObliczBłądSieciNeuronowej(double[] Cel,double[] Wynik){
        int rozmiarCel =  Cel.length;
        int rozmiarWynik = Wynik.length;
        double sumaBłędówSieciNeuronowej = 0 ;
        try{
          if(!(rozmiarCel==rozmiarWynik)){
              throw new ArrayIndexOutOfBoundsException("Tablice: Cele i wyniki nie maja takiego samego rozmiaru w funkcji ObliczBłądSieciNeuronowej");
          }
                for(int x = 0 ;x<rozmiarCel;x++)
                {
                    
                        sumaBłędówSieciNeuronowej+= ObliczBłąd(Cel[x],Wynik[x]);
                   
                }  
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Niepoprawny parametr, rozmiar tablicy to: "+e.getMessage());
        }

        return sumaBłędówSieciNeuronowej;
    }
    
    
    public ArrayList<Double> ObliczBłądCeluZWynikiem(double[] Cel,double[] Wynik){
        int rozmiarCel =  Cel.length;
        int rozmiarWynik = Wynik.length;
        ArrayList<Double> BłądWyjścia = new ArrayList<>();
         try
         {
          if(!(rozmiarCel==rozmiarWynik))
          {
              throw new ArrayIndexOutOfBoundsException("Tablice: Cele i wyniki nie maja takiego samego rozmiaru w Funkcji ObliczBłądCeluZWynikiem");
          }
                for(int x = 0 ;x<rozmiarCel;x++)
                {
                    for(int y = 0 ;y<rozmiarWynik;y++)
                    {
                        BłądWyjścia.add(ObliczBłąd(Cel[x],Wynik[y])) ;
                    }
                }  
        } 
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Niepoprawny parametr, rozmiar tablicy to: "+e.getMessage());
        }
         
         return BłądWyjścia;
    }
    
    
    public double ObliczBłąd(double cel,double wyjscie){
      return 0.5*Math.pow((cel - wyjscie), 2);
    }
    
     private double[] StringToDoubleTablica(String[] Tablica){
     double[] Liczby = new double[Tablica.length];
        for (int i =0; i< Tablica.length ; i++) {
            Liczby[i] = Double.valueOf(Tablica[i]);
        }
     return Liczby;
    }
}
