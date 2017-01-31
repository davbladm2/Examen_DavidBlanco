package com.example.dm2.examen_davidblanco;

/**
 * Created by dm2 on 31/01/2017.
 */

public class Tiempo {

    String hora,estado;
    int temperatura;

    public String getHora() {
        return hora;
    }
    public int getTemperatura() {
        return temperatura;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }
}
