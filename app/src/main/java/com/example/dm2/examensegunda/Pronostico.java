package com.example.dm2.examensegunda;

/**
 * Created by dm2 on 31/01/2017.
 */

public class Pronostico {

    private String hora;
    private String temperatura;
    private String estado;

    public String getHora() {
        return hora;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTemperatura(String temperatura) {

        this.temperatura = temperatura;
    }

    public void setHora(String hora) {

        this.hora = hora;
    }

    public String getEstado() {

        return estado;
    }

    public String getTemperatura() {

        return temperatura;
    }
}
