    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Lucas Vieira
 * 
 */
public class DataHora {
    String dataAtual;
    String horas;
    String hora1;
    String mes;
    String hora2;
  
    
    Date data = new Date();
    

    public String Data() {
        
        SimpleDateFormat formaData= new SimpleDateFormat("dd/MM/yyyy");
        dataAtual = formaData.format(data);
        
      
        
        return dataAtual;
    }
    
    
     public String Mes() {
        
        SimpleDateFormat formaData= new SimpleDateFormat("MM");
        mes = formaData.format(data);
        
      
        
        return mes;
    }
    

    public String Hora() {
        
        SimpleDateFormat formaData = new SimpleDateFormat("HH:mm:ss");
        horas = formaData.format(data);

        
        return horas;
    }
    
       
      
    public String Hora1(){
        
         SimpleDateFormat formaData = new SimpleDateFormat("HH");
        hora1 = formaData.format(data);

        
        return hora1;
        
    }
    
    
    public String Hora2(){
        
         SimpleDateFormat formaData = new SimpleDateFormat("[H]:mm");
        hora2 = formaData.format(data);

        
        return hora2;
        
    }
    
}
