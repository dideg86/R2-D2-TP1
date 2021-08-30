package com.DiegoDegracia.Modelos;

public class Atraccion {
    // Propiedades del objeto
    private String Nombre;
    private Integer Costo;
    private Double TiempoPromedio;
    private Integer Cupo;
    private TipoAventura Tipo;
    private Integer CantidadCompras;

    // Constructor del objeto
    public Atraccion(String nombre, Integer costo, Double tiempoPromedio, Integer cupo, TipoAventura tipo) {
        Nombre = nombre;
        Costo = costo;
        TiempoPromedio = tiempoPromedio;
        Cupo = cupo;
        Tipo = tipo;
        CantidadCompras = 0;
    }

    // Métodos del objeto.
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

    public void Comprar(){
        CantidadCompras += 1;
    }

    public Boolean EsDisponible(){
        return Cupo - CantidadCompras > 0;
    }
}
