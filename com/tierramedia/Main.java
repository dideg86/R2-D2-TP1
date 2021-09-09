package com.tierramedia;

import com.tierramedia.modelos.Atraccion;
import com.tierramedia.modelos.Promocion;
import com.tierramedia.modelos.PromocionAbsoluta;
import com.tierramedia.modelos.PromocionAxB;
import com.tierramedia.modelos.PromocionPorcentual;
import com.tierramedia.modelos.TipoAventura;
import com.tierramedia.modelos.Usuario;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {

	public static List<Atraccion> atracciones = new ArrayList<>();
	public static List<Promocion> promociones = new ArrayList<>();
	public static List<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args) {
    	cargarDatos();
    	
        // Primeramente a definir las atracciones - Levantar con un TXT
        Atraccion Moria = new Atraccion("Moria", 10, 2.0, 6, TipoAventura.AVENTURA);
        Atraccion MinasTirith = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoAventura.PAISAJE);
        Atraccion LaComarca = new Atraccion("La Comarca", 3, 6.5, 150, TipoAventura.DEGUSTACION);
        Atraccion Mordor = new Atraccion("Mordor", 25, 3.0, 4, TipoAventura.AVENTURA);
        Atraccion AbismoDeHellm = new Atraccion("Abismo de Hellm", 5, 2.0, 15, TipoAventura.PAISAJE);
        Atraccion Lothlorien = new Atraccion("Lothlarien", 35, 1.0, 30, TipoAventura.DEGUSTACION);
        Atraccion Erebor = new Atraccion("Erebor", 12, 3.0, 32, TipoAventura.PAISAJE);
        Atraccion BosqueNegro = new Atraccion("Bosque Negro", 3, 4.0,12,TipoAventura.AVENTURA);

        // Definir las promociones - Levantar con un TXT
        PromocionPorcentual PackAventura = new PromocionPorcentual("Pack Aventura", BosqueNegro, Mordor,0.2);
        PromocionAbsoluta PackDegustacion = new PromocionAbsoluta("Pack Degustacion", Lothlorien, LaComarca, 36);
        PromocionAxB PackPaisajes = new PromocionAxB("Pack Paisajes", MinasTirith, AbismoDeHellm,Erebor);
        
        int idUsuario = 0;
        System.out.println("Usuarios" + "\n");
        // foreach forma 1
        for (Usuario usuario: usuarios) {
        	System.out.println("[" + idUsuario++ + "] " + usuario.getNombre());
        }
        
        idUsuario = 0;
        Usuario usuarioElegido = usuarios.get(idUsuario);
        
        // crear lista con atracciones que el usuario puede pagar y hacer
        List<Atraccion> atraccionesDelUsuario = new ArrayList<>();
        for (Atraccion atraccion : atracciones)  {
        	if (atraccion.getCosto().intValue() <= usuarioElegido.getPresupuesto().intValue()
        			&& atraccion.getTiempoPromedio().doubleValue() <= usuarioElegido.getTiempoDisponible().doubleValue()) {
        		atraccionesDelUsuario.add(atraccion);
        	}
        }

        // extraer promociones de la preferencia del usuario
        List<Promocion> promocionesSugeridas = new ArrayList<>();
        for (Promocion promocion : promociones)  {
        	Atraccion at1 = promocion.getAtraccion1();
        	Atraccion at2 = promocion.getAtraccion2();
        	if (atraccionesDelUsuario.contains(at1)
        			&& at1.getTipo().equals(usuarioElegido.getPreferencia())
        			&& atraccionesDelUsuario.contains(at2)
        			&& at2.getTipo().equals(usuarioElegido.getPreferencia())) {
        		promocionesSugeridas.add(promocion);
        	}
        }
        
        // ordenar la lista
        Collections.sort(atraccionesDelUsuario, new AtraccionesPorCostoYTiempo(promociones));
        

    }
    
    private static void cargarDatos() {
    	atracciones.add(new Atraccion("Moria", 10, 2.0, 6, TipoAventura.AVENTURA));
    	atracciones.add(new Atraccion("Minas Tirith", 5, 2.5, 25, TipoAventura.PAISAJE));
    	atracciones.add(new Atraccion("La Comarca", 3, 6.5, 150, TipoAventura.DEGUSTACION));
    	atracciones.add(new Atraccion("Abismo de Hellm", 5, 2.0, 15, TipoAventura.PAISAJE));
    	atracciones.add(new Atraccion("Lothlórien", 35, 1.0, 30, TipoAventura.DEGUSTACION));

    	usuarios.add(new Usuario("Eowyn",TipoAventura.AVENTURA, 10, 8.0));
    	usuarios.add(new Usuario("Gandalf", TipoAventura.PAISAJE, 100, 5.0));
    	usuarios.add(new Usuario("Sam", TipoAventura.DEGUSTACION, 36, 8.0));
    	usuarios.add(new Usuario("Galadriel", TipoAventura.PAISAJE, 120, 8.0));
    }
    
}
