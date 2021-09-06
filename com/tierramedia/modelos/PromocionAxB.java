package com.tierramedia.modelos;

import java.util.List;

public class PromocionAxB extends Promocion {
    // Propiedades de la clase
    private Atraccion AtraccionDeRegalo;

    // Constructor
    public PromocionAxB(String nombre, Atraccion atraccion1, Atraccion atraccion2, Atraccion atraccionDeRegalo) {
        super(nombre, atraccion1, atraccion2);
        AtraccionDeRegalo = atraccionDeRegalo;
    }

    // MÃ©todos de la clase
    @Override
    public List<Atraccion> getAtracciones() {
        List<Atraccion> atracciones = super.getAtracciones();
        atracciones.add(AtraccionDeRegalo);

        return atracciones;
    }
    @Override
    public Double TiempoPromedioTotal() {
        return super.TiempoPromedioTotal() + AtraccionDeRegalo.getTiempoPromedio();
    }

    public Atraccion getAtraccionDeRegalo() {
        return AtraccionDeRegalo;
    }

    public Integer getPrecioTotal(){
        return  super.getPrecioTotal();
    }

}
