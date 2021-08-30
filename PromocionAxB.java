package com.DiegoDegracia.Modelos;

import java.util.List;

public class PromocionAxB extends Promocion {
    // Propiedades de la clase
    private Atraccion AtraccionDeRegalo;

    // Constructor
    public PromocionAxB(String nombre, Atraccion atraccion1, Atraccion atraccion2, Atraccion atraccionDeRegalo) {
        super(nombre, atraccion1, atraccion2);
        AtraccionDeRegalo = atraccionDeRegalo;
    }

    // Métodos de la clase
    @Override
    public List<Atraccion> getAtracciones() {
        List<Atraccion> atracciones = super.getAtracciones();
        atracciones.add(AtraccionDeRegalo);

        return atracciones;
    }

    @Override
    public Boolean EsDisponible() {
        return super.EsDisponible() && AtraccionDeRegalo.EsDisponible();
    }

    @Override
    public Double TiempoPromedioTotal() {
        return super.TiempoPromedioTotal() + AtraccionDeRegalo.getTiempoPromedio();
    }

    @Override
    public void Comprar() {
        List<Atraccion> atracciones = super.getAtracciones();
        atracciones.add(getAtraccionDeRegalo());

        // Verifico que estén todos disponibles
        for (Atraccion atraccion: atracciones) {
            if(!atraccion.EsDisponible())
                return; // Manejar la excepcion
        }

        // Compro las atracciones
        for (Atraccion atraccion: atracciones) {
            atraccion.Comprar();
        }
    }

    public Atraccion getAtraccionDeRegalo() {
        return AtraccionDeRegalo;
    }

    public Integer getPrecioTotal(){
        return  super.getPrecioTotal();
    }

}
