package com.DiegoDegracia;

import com.DiegoDegracia.Modelos.*;

public class Main {

    public static void main(String[] args) {
        // Primeramente a definir las atracciones - Levantar con un TXT
        Atraccion Moria = new Atraccion("Moria", 10, 2.0, 6, TipoAventura.AVENTURA);
        Atraccion MinasTirith = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoAventura.PAISAJE);
        Atraccion LaComarca = new Atraccion("La Comarca", 3, 6.5, 150, TipoAventura.DEGUSTACION);
        Atraccion Mordor = new Atraccion("Mordor", 25, 3.0, 4, TipoAventura.AVENTURA);
        Atraccion AbismoDeHellm = new Atraccion("Abismo de Hellm", 5, 2.0, 15, TipoAventura.PAISAJE);
        Atraccion Lothlorien = new Atraccion("Lothlórien", 35, 1.0, 30, TipoAventura.DEGUSTACION);
        Atraccion Erebor = new Atraccion("Erebor", 12, 3.0, 32, TipoAventura.PAISAJE);
        Atraccion BosqueNegro = new Atraccion("Bosque Negro", 3, 4.0,12,TipoAventura.AVENTURA);

        // Definir las promociones - Levantar con un TXT
        PromocionPorcentual PackAventura = new PromocionPorcentual("Pack Aventura", BosqueNegro, Mordor,0.2);
        PromocionAbsoluta PackDegustacion = new PromocionAbsoluta("Pack Degustacion", Lothlorien, LaComarca, 36);
        PromocionAxB PackPaisajes = new PromocionAxB("Pack Paisajes", MinasTirith, AbismoDeHellm,Erebor);

        // Definir los personajes - Levantar con un TXT
        Usuario Eowyn = new Usuario("Eowyn",TipoAventura.AVENTURA, 10, 8.0);
        Usuario Gandalf = new Usuario("Gandalf", TipoAventura.PAISAJE, 100, 5.0);
        Usuario Sam = new Usuario("Sam", TipoAventura.DEGUSTACION, 36, 8.0);
        Usuario Galadriel = new Usuario("Galadriel", TipoAventura.PAISAJE, 120, 8.0);

    }
}
