package com.tierramedia.modelos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import com.tierramedia.*;

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

	// Metodos de la clase
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

	// atracciones que el usuario puede pagar y tiene tiempo de hacer
	public Atraccion puedoComprarYtengoTiempo(Atraccion atraccion) {

		int presupuesto = this.getPresupuesto().intValue();
		int costo = atraccion.getCosto().intValue();
		double tiempoDisponible = this.getTiempoDisponible().doubleValue();
		double tiempoPromedio = atraccion.getTiempoPromedio().doubleValue();

		if (presupuesto >= costo && tiempoDisponible >= tiempoPromedio) {
			return atraccion;
		}
		return atraccion;
	}
	
	public void atraccionesDelUsuario() {
		List<Atraccion> atraccionesDelUsuario = new ArrayList<>();
		}

	/* extraer promociones de la preferencia del usuario
	List<Promocion> promocionesSugeridas = new ArrayList<>();
	Promocion promocion:promociones) {
		Atraccion at1 = promocion.getAtraccion1();
		Atraccion at2 = promocion.getAtraccion2();
		if (atraccionesDelUsuario.contains(at1) && at1.getTipo().equals(usuarioElegido.getPreferencia())
				&& atraccionesDelUsuario.contains(at2) && at2.getTipo().equals(usuarioElegido.getPreferencia())) {
			promocionesSugeridas.add(promocion);
		}
	}*/

	/* ordenar la lista
	Collections.sort(atraccionesDelUsuario,new AtraccionesPorCostoYTiempo(promociones));*/

	public boolean DescontarCompra(Integer monedasDeOro, Double tiempo){
		if(this.getPresupuesto() - monedasDeOro >= 0 && this.getTiempoDisponible() - tiempo >= 0){
            this.Presupuesto -= monedasDeOro;
            this.TiempoDisponible -= tiempo;
            return true;
        }

        return false; // No se hizo la transaccion
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
