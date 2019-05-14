/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import objetosNegocio.Alarma;
import objetosNegocio.Sensor;

/**
 *
 * @author arand
 */
public class NewMain {

    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        
        Sensor s = new Sensor("M131,Temperatura,1000,50");
        
        Alarma a = new Alarma("Si", "Tambien", "Temperatura");
        
        s.addAlarma(a);
        
        s.start();
        while(true){
            
            System.out.println(s.ExportarLecturas());
            Thread.sleep(1000);
        }
        
    }
    
}
