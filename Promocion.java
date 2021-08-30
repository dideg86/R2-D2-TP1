package com.DiegoDegracia.Modelos;

import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.List;

public class Promocion {
    // Propiedades de la clase
    private String Nombre;
    private Atraccion Atraccion1;
    private Atraccion Atraccion2;

    // Constructor
    public Promocion(String nombre, Atraccion atraccion1, Atraccion atraccion2) {
        Nombre = nombre;
        Atraccion1 = atraccion1;
        Atraccion2 = atraccion2;
    }

    // Métodos de la clase
    public String getNombre() {
        return Nombre;
    }

    public List<Atraccion> getAtracciones() {
        List<Atraccion> atracciones = new ArrayList<Atraccion>();
        atracciones.add(Atraccion1);
        atracciones.add(Atraccion2);

        return atracciones;
    }

    public Integer getPrecioTotal() {
        return Atraccion1.getCosto() + Atraccion2.getCosto();
    }

    public Boolean EsDisponible(){
        return Atraccion1.EsDisponible() && Atraccion2.EsDisponible();
    }

    public Double TiempoPromedioTotal(){
        return Atraccion2.getTiempoPromedio() + Atraccion1.getTiempoPromedio();
    }

    public void Comprar(){
        if(!Atraccion1.EsDisponible() || !Atraccion2.EsDisponible())
            return; // Manejar excepcion

        Atraccion2.Comprar();
        Atraccion1.Comprar();
    }
}
