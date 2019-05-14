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
public class Alarma {
    
    Sensor sensor;
    String correo;
    String telefono;
    String tipoNotificacion;
    Thread hilonTesla;
    
    public Alarma(){
        
    }

    public Alarma(String correo, String telefono, String tipoNotificacion) {
        this.correo = correo;
        this.telefono = telefono;
        this.tipoNotificacion = tipoNotificacion;
    }
    
    public Alarma(String atributos){
        if (atributos != null) {
            if (atributos.contains(",")) {

                String[] data = atributos.split(",");

                if (data.length == 3) {
                    correo = data[0];
                    telefono = (data[1]);
                    tipoNotificacion = (data[2]);
                }
            }
        }
    }
    
    public int notificar(String fecha){
        if(Double.valueOf(sensor.getLecturaMapa(fecha)) > sensor.getLimite()){
            sendCorreo();
            sendNotificacionTelefono();
        }
        
        return 1;
    }
    
    public int sendCorreo(){
        if(correo != null){
            System.out.println("Soy un correo! Estoy alarmandote!");
            return 1;
        }
        return 0;
    }
    
    public int sendNotificacionTelefono(){
        if(telefono != null){
            System.out.println("Soy un telefono! Estoy alarmandote!");
            return 1;
        }
        return 0;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(String tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }
    
    
    
}
