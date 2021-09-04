package com.DiegoDegracia.Modelos;

import java.util.List;

public class PromocionPorcentual extends Promocion {
    // Propiedades
    private Double Descuento;

    // Constructor
    public PromocionPorcentual(String nombre, Atraccion atraccion1, Atraccion atraccion2, Double descuento) {
        super(nombre, atraccion1, atraccion2);
        Descuento = descuento;
    }

    // MÃ©todos de la clase
    public Double getDescuento() {
        return Descuento;
    }

    public Integer getPrecioTotal() {
        return (int)Math.round(getPrecioTotal() * Descuento);
    }
}
