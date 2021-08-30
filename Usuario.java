package com.DiegoDegracia.Modelos;

public class Usuario {
    // Propiedades
    private String Nombre;
    private TipoAventura Preferencia;
    private Integer Presupuesto;
    private Double TiempoDisponible;

    // Constructor
    public Usuario(String nombre, TipoAventura preferencia, Integer presupuesto, Double tiempoDisponible) {
        Nombre = nombre;
        Preferencia = preferencia;
        Presupuesto = presupuesto;
        TiempoDisponible = tiempoDisponible;
    }

    // Métodos de la clase
    public String getNombre() {
        return Nombre;
    }

    public TipoAventura getPreferencia() {
        return Preferencia;
    }

    public Integer getPresupuesto() {
        return Presupuesto;
    }

    public Double getTiempoDisponible() {
        return TiempoDisponible;
    }

    public void DescontarCompra(Integer monedasDeOro, Double tiempo){
        Presupuesto -= monedasDeOro;
        TiempoDisponible -= tiempo;
    }
}
