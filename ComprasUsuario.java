package com.DiegoDegracia.Modelos;

import java.util.ArrayList;
import java.util.List;

public class ComprasUsuario {
    private Usuario Usuario;
    private List<Promocion> PromocionesCompradas;
    private List<Atraccion> AtraccionesCompradas; // Atracciones individualmente compradas

    // Constructores
    public ComprasUsuario(Usuario usuario) {
        Usuario = usuario;
        PromocionesCompradas = new ArrayList<Promocion>();
        AtraccionesCompradas = new ArrayList<Atraccion>();
    }

    public ComprasUsuario(Usuario usuario, Promocion promocionComprada){
        Usuario = usuario;
        PromocionesCompradas = new ArrayList<Promocion>();
        AtraccionesCompradas = new ArrayList<Atraccion>();

        PromocionesCompradas.add(promocionComprada);
    }

    public ComprasUsuario(Usuario usuario, Atraccion atraccionComprada){
        Usuario = usuario;
        PromocionesCompradas = new ArrayList<Promocion>();
        AtraccionesCompradas = new ArrayList<Atraccion>();

        AtraccionesCompradas.add(atraccionComprada);
    }

    // Métodos de la clase
    // Devuelvo las atracciones compradas en promociones
    public List<Atraccion> AtraccionesCompradasConPromociones() {
        List<Atraccion> atraccionesDePromociones = new ArrayList<Atraccion>();

        for (Promocion promocionComprada: PromocionesCompradas) {
            for (Atraccion atraccionComprada:promocionComprada.getAtracciones()) {
                atraccionesDePromociones.add(atraccionComprada);
            }
        }

        return atraccionesDePromociones;
    }

    public List<Atraccion> AtraccionesCompradasSinPromociones(){
        return AtraccionesCompradas;
    }

    public List<Atraccion> TodasLasAtraccionesCompradas(){
        List<Atraccion> todasLasAtracciones = AtraccionesCompradasConPromociones();
        todasLasAtracciones.addAll(AtraccionesCompradas);

        return  todasLasAtracciones;
    }

    public void ComprarAtraccion(Atraccion atraccionAComprar){
        if(!atraccionAComprar.EsDisponible())
            return; // No dejo que haga nada -- Comportamiento que hay que manejar

        Usuario.DescontarCompra(atraccionAComprar.getCosto(), atraccionAComprar.getTiempoPromedio());
        atraccionAComprar.Comprar(); // PELIGRO!! A pulir después
    }

    public void ComprarPromocion(Promocion promocionAComprar){
        if(!promocionAComprar.EsDisponible())
            return; // -- Comportamiento que hay que manejar

        Usuario.DescontarCompra(promocionAComprar.getPrecioTotal(), promocionAComprar.TiempoPromedioTotal());
    }
}
