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
public enum FunkcjaAktywacji {

   
    LINIOWA(true),
    OBCIETA_LINIOWA(true),
    PROGOWA_UNIPOLARNA(false),
    PROGOWA_BIPOLARNA(false),
    SIGMOIDALNA_BIPOLARNA(true),
    SIGMOIDALNA_UNIPOLARNA(true),
    GAUSSA(true);
    
    boolean CzyFunkcjaciągła = false;
    
   private FunkcjaAktywacji ( boolean CzyFunkcjaCiągła)
   {
      this.CzyFunkcjaciągła= CzyFunkcjaCiągła;
   }
   
  
}
