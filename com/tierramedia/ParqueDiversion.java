package com.tierramedia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tierramedia.modelos.Atraccion;
import com.tierramedia.modelos.Promocion;
import com.tierramedia.modelos.Usuario;

public class ParqueDiversion {
    private List<Atraccion> Atracciones;
    private List<Promocion> Promociones;
    private Map<Atraccion, Integer> ComprasAtraccion; // Cantidad de compras
    private Map<Usuario, List<Atraccion>> CompraAtraccionesUsuario;
    private Map<Usuario, List<Promocion>> CompraPromocionesUsuario;

    // Constructor
    public ParqueDiversion(List<Atraccion> atracciones, List<Promocion> promociones) {
        Atracciones = atracciones;
        Promociones = promociones;

        // Inicialización de los maps
        ComprasAtraccion = new HashMap<Atraccion, Integer>();
        CompraAtraccionesUsuario = new HashMap<Usuario, List<Atraccion>>();
        CompraPromocionesUsuario = new HashMap<Usuario, List<Promocion>>();

        for (Atraccion atraccion: Atracciones) {
            ComprasAtraccion.put(atraccion, atraccion.getCupo());
        }
    }

    public boolean EsAtraccionDisponible(Atraccion atraccion){
        return ComprasAtraccion.get(atraccion) - atraccion.getCupo() > 0;
    }

    // Métodos públicos
    public List<Atraccion> getAtraccionesComprables(Usuario usuario){
        List<Atraccion> listadoAtraccionesDisponibles = new ArrayList<Atraccion>();

        for (Atraccion atraccion:Atracciones) {
            if(!this.AtraccionComprada(usuario, atraccion))
                listadoAtraccionesDisponibles.add(atraccion);
        }

        return listadoAtraccionesDisponibles;
    }
    public List<Promocion> getPromocionesComprables(Usuario usuario){
        List<Promocion> listadoPromocionesDisponibles = new ArrayList<Promocion>();

        for (Promocion promocion:Promociones) {
            if(!this.PromocionComprada(usuario, promocion))
                listadoPromocionesDisponibles.add(promocion);
        }

        return listadoPromocionesDisponibles;
    }
    public List<Atraccion> getAtraccionesCompradasUsuario(Usuario usuario){
        return CompraAtraccionesUsuario.get(usuario);
    }
    public List<Promocion> getPromocionesCompradasUsuario(Usuario usuario){
        return CompraPromocionesUsuario.get(usuario);
    }

    public boolean ComprarAtraccion(Atraccion atraccion, Usuario usuario){
        // Si la atracción tiene cupo
        if(!this.EsAtraccionDisponible(atraccion))
            return false;

        // Descontar la compra al cliente
        if(usuario.DescontarCompra(atraccion.getCosto(), atraccion.getTiempoPromedio())) {
            this.AgregarAtraccionCompradaUsuario(atraccion, usuario);
            return true;
        }
        // Si no tiene con qué pagar
        return false;
    }
    public boolean ComprarPromocion(Usuario usuario, Promocion promocion){
        // Buscar si al menos UNA atraccion no está disponible
        for (Atraccion atraccion: promocion.getAtracciones()) {
            if(!EsAtraccionDisponible(atraccion))
                return false;
        }

        if(usuario.DescontarCompra(promocion.getPrecioTotal(), promocion.TiempoPromedioTotal())){
            // Si compró la atracción
            for (Atraccion atraccion: promocion.getAtracciones()) {
                usuario.DevolucionCompra(atraccion.getCosto(), atraccion.getTiempoPromedio());
            }

            // Compra de la promoción
            this.AgregarPromocionCompradaUsuario(promocion, usuario);

            return true;
        }
        // Si no tiene el dinero
        return false;
    }

    // Métodos privados
    private void AgregarAtraccionCompradaUsuario(Atraccion atraccion, Usuario usuario){
        Integer cupoActual = ComprasAtraccion.get(atraccion);
        ComprasAtraccion.replace(atraccion, cupoActual - 1);

        // Buscar si el usuario existe en el listado
        if(CompraAtraccionesUsuario.containsKey(usuario)){
            List<Atraccion> atraccionesCompradasUsuario = CompraAtraccionesUsuario.get(usuario); // Listado de compras
            atraccionesCompradasUsuario.add(atraccion); // Agrego la nueva atracción comprada
            CompraAtraccionesUsuario.replace(usuario, atraccionesCompradasUsuario); // Reemplazo la lista anterior
        }else{
            List<Atraccion> atraccionesCompradasUsuario = new ArrayList<Atraccion>(); // Creo el listado de compras
            atraccionesCompradasUsuario.add(atraccion); // Agrego la nueva atracción comprada
            CompraAtraccionesUsuario.put(usuario, atraccionesCompradasUsuario); // Creo el usuario con las compras
        }
    }
    private void AgregarPromocionCompradaUsuario(Promocion promocion, Usuario usuario){
        // Si no compró la atracción
        for (Atraccion atraccion: promocion.getAtracciones()) {
            if(!this.AtraccionComprada(usuario, atraccion)){
                this.AgregarAtraccionCompradaUsuario(atraccion, usuario);
            }
        }

        // Buscar si el usuario existe en el listado
        if(CompraPromocionesUsuario.containsKey(usuario)){
            List<Promocion> promocionesCompradasUsuario = CompraPromocionesUsuario.get(usuario); // Listado de promociones
            promocionesCompradasUsuario.add(promocion); // Agrego la nueva promocion comprada
            CompraPromocionesUsuario.replace(usuario, promocionesCompradasUsuario); // Reemplazo la lista anterior
        }else{
            List<Promocion> promocionesCompradasUsuario = new ArrayList<Promocion>(); // Creo el listado de promociones
            promocionesCompradasUsuario.add(promocion); // Agrego la nueva promocion comprada
            CompraPromocionesUsuario.put(usuario, promocionesCompradasUsuario); // Creo el usuario con las promociones
        }
    }
    private boolean AtraccionComprada(Usuario usuario, Atraccion atraccion){
        List<Atraccion> atraccionesCompradas = CompraAtraccionesUsuario.get(usuario);
        return  atraccionesCompradas.contains(atraccion);
    }
    private boolean PromocionComprada(Usuario usuario, Promocion promocion){
        List<Promocion> promocionesCompradas = CompraPromocionesUsuario.get(usuario);
        return promocionesCompradas.contains(promocion);
    }
}
