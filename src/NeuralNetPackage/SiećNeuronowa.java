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
    public ArrayList<WartstwaNeuronów> WartstwaNeuronów;
    private final int LiczbaWarstw;
    private final FunkcjaAktywacji Aktywacja;
    public int x;
    public int y;
  
    
    public SiećNeuronowa(String lokalizacjaPliku) throws FileNotFoundException{
        WartstwaNeuronów = new ArrayList<>();
        
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

                                            
                                            
                                            WartstwaNeuronów.get(liczbaWykrytychWarstwSieci-1).DodajWagi(LiczbaWykrytychNeuronówWWarstwie, StringToDoubleTablica(WagaNeuronu));
                                            WartstwaNeuronów.get(liczbaWykrytychWarstwSieci-1).Neuron.get(LiczbaWykrytychNeuronówWWarstwie).UaktualnijUkos(Double.valueOf(UkosNeuronu));
                                            
                                            LiczbaWykrytychNeuronówWWarstwie++;
                                        }
        
        
        }
        
        this.LiczbaWarstw = liczbaWykrytychWarstwSieci;
    }
    
    /** 
    @param ListaWagPoszególnychNeuronów jest to lista list Wag dla każdego neuronu
    @param ListaUkosówPoszególnychNeuronów jest to lista ukosu dla każdego neuronu
     * @param FunkcjaAktywacji
    */
    public SiećNeuronowa(ArrayList<ArrayList<ArrayList<Double>>> ListaWagPoszególnychNeuronów,ArrayList<ArrayList<Double>> ListaUkosówPoszególnychNeuronów,FunkcjaAktywacji FunkcjaAktywacji)
    {
        this.WartstwaNeuronów = new ArrayList<>();
        this.LiczbaWarstw = ListaWagPoszególnychNeuronów.size();
        this.Aktywacja = FunkcjaAktywacji;
        for(int warstwa = 0 ; warstwa<LiczbaWarstw;warstwa++)
        { 
            this.WartstwaNeuronów.add(new WartstwaNeuronów(ListaWagPoszególnychNeuronów.get(warstwa).size(),Aktywacja));
                int liczbaNeuronów = ListaWagPoszególnychNeuronów.get(warstwa).size();
                for(int neuron = 0 ; neuron<liczbaNeuronów;neuron++ )
                {
                 WartstwaNeuronów.get(warstwa).UstawUkosy(ListaUkosówPoszególnychNeuronów.get(warstwa));
                 WartstwaNeuronów.get(warstwa).DodajWagi(neuron,ListaWagPoszególnychNeuronów.get(warstwa).get(neuron));   
                }
        }
    }
    
    
    public SiećNeuronowa(int[] tab,FunkcjaAktywacji FunkcjaAktywacji)
    {
        Random Generator = new Random();
        this.WartstwaNeuronów = new ArrayList<>();
        this.LiczbaWarstw= tab.length;
        this.Aktywacja =FunkcjaAktywacji;
        int liczbaNeuronówWWarstwie;
        for(int warstwa = 0 ; warstwa<LiczbaWarstw;warstwa++)
        {
            
            this.WartstwaNeuronów.add(new WartstwaNeuronów(tab[warstwa],Aktywacja));
            
                int liczbaNeuronów = tab[warstwa];
                
                
                for(int neuron = 0 ; neuron<liczbaNeuronów;neuron++ ){
                    
                 WartstwaNeuronów.get(warstwa).Neuron.get(neuron).UaktualnijUkos(Generator.nextGaussian());
                   
                   liczbaNeuronówWWarstwie = WartstwaNeuronów.get(warstwa).PobierzLiczbeNeuronów();
                   
                        if(warstwa>0){
                            liczbaNeuronówWWarstwie = WartstwaNeuronów.get(warstwa-1).PobierzLiczbeNeuronów();
                        }
                        else{
                            liczbaNeuronówWWarstwie = WartstwaNeuronów.get(warstwa).PobierzLiczbeNeuronów();
                        }
                   
                            for( int Waga = 0 ;Waga <liczbaNeuronówWWarstwie;Waga++){
                                WartstwaNeuronów.get(warstwa).Neuron.get(neuron).DodajWage(Generator.nextGaussian());
                            }
                    
                    
                }
           }
           
    }

   

    
    
    public ArrayList<Double> ObliczWartościPotrzebneDotrenowaniaSieci(ArrayList<Double> WartościDziedziny){
       ArrayList<Double> Tablica = WartstwaNeuronów.get(0).ObliczWartościWyjść(WartościDziedziny);
       for(int i = 1; i<LiczbaWarstw;i++)
       {
          Tablica = WartstwaNeuronów.get(i).ObliczWartościWyjść(Tablica);
          
       }
       
       return Tablica;
   }
    
    
    
    
    public ArrayList<Double> ObliczWartości(ArrayList<Double> WartościDziedziny){
       ArrayList<Double> Tablica = WartstwaNeuronów.get(0).ObliczWartości(WartościDziedziny);
       for(int i = 1; i<LiczbaWarstw;i++)
       {
          Tablica = WartstwaNeuronów.get(i).ObliczWartości(Tablica);
       }
       
       return Tablica;
   }
    
    public double TrenujSieć(ArrayList<Double> Wejscie,ArrayList<Double> Wyjście,double TempoUczenia)
    {
        
        //operujemy głównie na warstwach
        ArrayList<Double> Wynik = ObliczWartościPotrzebneDotrenowaniaSieci(Wejscie);
        double BłądSieciNeuronowej = ObliczBłądSieciNeuronowej(Wyjście,Wynik); // czyli nasze E total we wzorze
        ArrayList<Double> BłądWyjścia_PochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu = ObliczPochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu(Wyjście,Wynik);// czyli błędy z poszczególnych wyjść
        
       
        for(int warstwaNeuronu = LiczbaWarstw; warstwaNeuronu>0;warstwaNeuronu--)
        {
            
            if(warstwaNeuronu==LiczbaWarstw-2){
              BłądWyjścia_PochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu = ObliczBłędyNeuronówDoNastępnejWartstwy(WartstwaNeuronów.get(warstwaNeuronu));
              WartstwaNeuronów.get(warstwaNeuronu-1).trenujNeurony(BłądWyjścia_PochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu,Wejscie, TempoUczenia);   
            }
           else  if(!(warstwaNeuronu == LiczbaWarstw))
            {
                BłądWyjścia_PochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu = ObliczBłędyNeuronówDoNastępnejWartstwy(WartstwaNeuronów.get(warstwaNeuronu));
                WartstwaNeuronów.get(warstwaNeuronu-1).trenujNeurony(BłądWyjścia_PochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu,Wejscie, TempoUczenia);
            }
            else{
               WartstwaNeuronów.get(warstwaNeuronu-1).trenujNeurony(BłądWyjścia_PochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu,WartstwaNeuronów.get(warstwaNeuronu-2).ZwróćWartościWyjść(), TempoUczenia);

            }

        }
       return BłądSieciNeuronowej; 
    }
    
    
    public ArrayList<Double> ObliczBłędyNeuronówDoNastępnejWartstwy(WartstwaNeuronów WartstwaNeuronów){
        ArrayList<Double> PochodneBłędu =  WartstwaNeuronów.ZwróćPochodneBłęduNeuronuPoWyjściuNeuronu();
        int LiczbaWag = WartstwaNeuronów.Neuron.get(0).PodajDługośćStarychWag();
        ArrayList<Double> Lista = new ArrayList<>();
      
           for(int DanyNeuron = 0 ;DanyNeuron <WartstwaNeuronów.PobierzLiczbeNeuronów();DanyNeuron++)
           {
              ArrayList<Double> TablicaWag = WartstwaNeuronów.ZwróćWagiDanegoNeuronu(DanyNeuron);
                for (int waga = 0 ; waga<LiczbaWag ; waga ++)
                { 
                 if(DanyNeuron>0)
                 {
                  Lista.set(waga,PochodneBłędu.get(DanyNeuron)*TablicaWag.get(waga)+Lista.get(waga));
                 }
                 else
                 {   
                  Lista.add(PochodneBłędu.get(DanyNeuron)*TablicaWag.get(waga));    
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
                zapis.println("War "+WartstwaNeuronów.get(Warstwa).PobierzLiczbeNeuronów()+" "+WartstwaNeuronów.get(Warstwa).PodajTypFunkcjiAktywacji());
                int LiczbaNeuronów = WartstwaNeuronów.get(Warstwa).PobierzLiczbeNeuronów();
                
                for(int DanyNeuron = 0;DanyNeuron<LiczbaNeuronów;DanyNeuron++)
                    {
                        zapis.println("N:W ["+WartstwaNeuronów.get(Warstwa).Neuron.get(DanyNeuron).PobierzListęWag()+"]:"+"B ["+WartstwaNeuronów.get(Warstwa).Neuron.get(DanyNeuron).PobierzUkos()+"]");
                        
                    }
                    
                
            }
           
            
            
            zapis.close();
        }
        
   } 
    
    public ArrayList <Double> ObliczPochodnaBłęduTotalnegoPoWyjściuAktywacjiNeuronu(ArrayList<Double> Cel,ArrayList<Double> Wynik){
     ArrayList<Double> WartościPochodnych = new ArrayList<>();
     int rozmiarCel =  Cel.size();
     int rozmiarWynik = Wynik.size();
     
     
      try{
          if(!(rozmiarCel==rozmiarWynik)){
              throw new ArrayIndexOutOfBoundsException("Tablice: Cele i wyniki nie maja takiego samego rozmiaru w funkcji ObliczBłądSieciNeuronowej");
          }
                for(int x = 0 ;x<rozmiarCel;x++)
                {
                    
                        WartościPochodnych.add(Wynik.get(x)-Cel.get(x));
                    
                }  
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Niepoprawny parametr, rozmiar tablicy to: "+e.getMessage());
        }
     return WartościPochodnych;
    }
    
    public double ObliczBłądSieciNeuronowej(ArrayList<Double> Cel,ArrayList<Double> Wynik){
        int rozmiarCel =  Cel.size();
        int rozmiarWynik = Wynik.size();
        double sumaBłędówSieciNeuronowej = 0 ;
        try{
          if(!(rozmiarCel==rozmiarWynik)){
              throw new ArrayIndexOutOfBoundsException("Tablice: Cele i wyniki nie maja takiego samego rozmiaru w funkcji ObliczBłądSieciNeuronowej");
          }
                for(int x = 0 ;x<rozmiarCel;x++)
                {
                    
                        sumaBłędówSieciNeuronowej+= ObliczBłąd(Cel.get(x),Wynik.get(x));
                   
                }  
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Niepoprawny parametr, rozmiar tablicy to: "+e.getMessage());
        }

        return sumaBłędówSieciNeuronowej;
    }
    
    
    public ArrayList<Double> ObliczBłądCeluZWynikiem(ArrayList<Double> Cel,ArrayList<Double> Wynik){
        int rozmiarCel =  Cel.size();
        int rozmiarWynik = Wynik.size();
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
                        BłądWyjścia.add(ObliczBłąd(Cel.get(x),Wynik.get(y))) ;
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
    
     private ArrayList<Double> StringToDoubleTablica(String[] Tablica){
     ArrayList<Double> Liczby = new ArrayList<>();
            for(int indeks =0 ; indeks < Tablica.length ;indeks ++){
                Liczby.add(Double.valueOf(Tablica[indeks]));
            }
     return Liczby;
    }
}
