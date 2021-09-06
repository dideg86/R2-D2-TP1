package com.tierramedia.modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    
    private static List [] atracciones = new CoincidenciasUsuario() {
    	
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

    public boolean DescontarCompra(Integer monedasDeOro, Double tiempo){
        if(this.getPresupuesto() - monedasDeOro >= 0 && this.getTiempoDisponible() - tiempo >= 0){
            this.Presupuesto -= monedasDeOro;
            this.TiempoDisponible -= tiempo;
            return true;
        }

        return false; // No se hizo la transacción
    }

    public void DevolucionCompra(Integer monedasDeOro, Double tiempo){
        this.Presupuesto += monedasDeOro;
        this.TiempoDisponible += tiempo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Nombre.equals(usuario.Nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Nombre);
    }
}
