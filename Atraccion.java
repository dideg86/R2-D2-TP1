package com.DiegoDegracia.Modelos;

import java.util.Objects;

public class Atraccion {
    // Propiedades del objeto
    private String Nombre;
    private Integer Costo;
    private Double TiempoPromedio;
    private Integer Cupo;
    private TipoAventura Tipo;

    // Constructor del objeto
    public Atraccion(String nombre, Integer costo, Double tiempoPromedio, Integer cupo, TipoAventura tipo) {
        Nombre = nombre;
        Costo = costo;
        TiempoPromedio = tiempoPromedio;
        Cupo = cupo;
        Tipo = tipo;
    }

    // Metodos del objeto.
    public String getNombre() {
        return Nombre;
    }

    public Integer getCosto() {
        return Costo;
    }

    public Double getTiempoPromedio() {
        return TiempoPromedio;
    }

    public Integer getCupo() {
        return Cupo;
    }

    public TipoAventura getTipo() {
        return Tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atraccion atraccion = (Atraccion) o;
        return Nombre.equals(atraccion.Nombre) && Tipo == atraccion.Tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Nombre, Tipo);
    }
}
