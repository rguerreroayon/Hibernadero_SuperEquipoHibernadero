/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosNegocio;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author arand
 */
public class Sensor implements ISensor {

    private String modelo;
    private String tipo;

    private int intervalo;
    private float limite;
    private Thread hilonMusk;
    private Map<String, String> lecturas = new HashMap<>();
    private List<Alarma> alarmas = new ArrayList<>();

    public Sensor() {

    }

    public Sensor(String modelo, String tipo, int intervalo, float limite) {
        this.modelo = modelo;
        this.tipo = tipo;
        this.intervalo = intervalo;
        this.limite = limite;
    }

    public Sensor(String atributos) {
        if (atributos != null) {
            if (atributos.contains(",")) {

                String[] data = atributos.split(",");

                if (data.length == 4) {
                    modelo = data[0];
                    tipo = data[1];
                    intervalo = Integer.valueOf(data[2]);
                    limite = Float.valueOf(data[3]);
                }
            }
        }
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    public String getLecturaMapa(String fecha) {
        return lecturas.get(fecha);
    }

    @Override
    public String getLectura() {
        // Esta lectura ha sido construida cuidadosamente y de manera artesanal
        // para poder recibir una lectura siempre, cuidadosamente medida, para
        // que sea aleatoria.

        Random dadito = new Random();
        int lectura = dadito.nextInt(69);

        return String.valueOf(lectura);
    }

    @Override
    public int addAlarma(Alarma alarma) {
        alarmas.add(alarma);
        alarma.setSensor(this);

        return 1;
    }

    @Override
    public int start() {
        hilonMusk = new Thread() {

            long tiempoPasado = 0;

            @Override
            public void run() {
                while (true) {
                    long tiempoActual = System.currentTimeMillis();

                    if (tiempoActual - tiempoPasado >= intervalo) {

                        LocalDateTime ld = LocalDateTime.now(ZoneId.systemDefault());
                        String ldf = ld.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                        
                        lecturas.put(ldf, getLectura());
                        
                        alarmas.forEach((alarma) -> {
                            alarma.notificar(ldf);
                        });
                        
                        tiempoPasado = tiempoActual;
                    }

                }
            }

        };

        hilonMusk.start();

        return 1;
    }

    @Override
    public int stop() {
        hilonMusk.stop();
        return 1;
    }

    public Map<String, String> ExportarLecturas() {
        return lecturas;
    }

}
