/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosNegocio;

/**
 *
 * @author arand
 */
public interface ISensor {
    
    public String getLectura();
    
    public int addAlarma(Alarma alarma);
    
    public int start();
    
    public int stop();
    
}
