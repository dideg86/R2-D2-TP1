package tierraMedia;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Terminal {
    private ParqueDiversion Parque;
    private List<Usuario> Usuarios;
    private List<Atraccion> Atraccion;
    private Scanner scan;

    // Constructor
    public Terminal(ParqueDiversion parque, List<Usuario> usuarios, List<Atraccion> Atraccion) {
        Parque = parque;
        Usuarios = usuarios;

        scan = new Scanner(System.in);
    }

    public void Ejecutar() throws IOException{
        // Me paro sobre cada usuario en particular
        for (Usuario usuario:Usuarios) {
            while(true){
                List<Promocion> promociones = PromocionesPriorizadasUsuario(usuario);
                List<Atraccion> atracciones = AtraccionesPriorizadasUsuario(usuario);

                if(!(usuario.getTiempoDisponible() >= 0
                        && usuario.getPresupuesto() >= 0)
                        || (promociones.isEmpty()
                        && atracciones.isEmpty()))
                {
                    break;
                }

       		
        		salidaPorUsuario(); //prueba si funciona la escritura del txt
                PantallaInicio();
                PantallaPromocionesPriorizadas(promociones);
                PantallaAtraccionesPriorizadas(atracciones);
                PantallaFooter();
                PantallaCompra(usuario);
                
                
            }

            // Imprimo por pantalla el resultado de la compra
            PantallaFinalizacionCompra(usuario);
        }
    }

    // Pantallas
    private void PantallaInicio(){
    	System.out.println("-------------------------------------------------------");
    	System.out.println("-------------------------------------------------------");
        System.out.println("Bienvenido al menú de compras del parque de diversiones");
        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.println("A continuación, se muestran las opciones que puede comprar");
        System.out.println();
    }
    private void PantallaPromocionesPriorizadas(List<Promocion> promociones){
        String alias = "p";
        int count = 1;

        System.out.println("PROMOCIONES DISPONIBLES:");
        for (Promocion promocion:promociones) {
            System.out.println();
            System.out.println(alias + count + " - " + "Promocion: "+promocion.getNombre());
            System.out.println("Precio: " + promocion.getPrecioTotal());
            System.out.println("Tiempo aproximado de duración: " + promocion.TiempoPromedioTotal());
            System.out.println("Atracciones incluidas en la promoción");
            for (Atraccion atraccion:promocion.getAtracciones()) {
                System.out.println(" - "+ atraccion.getNombre());
            }

            count++;
        }
        System.out.println();
        System.out.println("-------------------------------------------------------");
    }
    private void PantallaAtraccionesPriorizadas(List<Atraccion> atracciones){
        String alias = "a";
        int count = 1;

        System.out.println();
        System.out.println("ATRACCIONES DISPONIBLES:");
        for (Atraccion atraccion:atracciones) {
            System.out.println();
            System.out.println(alias + count + " - " + "Atraccion: "+atraccion.getNombre());
            System.out.println("Precio: " + atraccion.getCosto());
            System.out.println("Tiempo aproximado de duración: " + atraccion.getTiempoPromedio());

            count++;
        }
        System.out.println();
        System.out.println("-------------------------------------------------------");
    }
    private void PantallaFooter(){
    	System.out.println();
        System.out.println("Ingrese:");
        System.out.println(" - 'p' + número: promoción");
        System.out.println(" - 'a' + número: atracción");
        System.out.println("la promoción o atracción que desea comprar.");
    }
    private void PantallaCompra(Usuario usuario){
    	System.out.println();
        System.out.print("Ingrese la opción a comprar: ");
        String opcion = scan.nextLine();

        // Si no contiene para comprar promoción o comprar atracción
        if(!opcion.toUpperCase(Locale.ROOT).contains("P") && !opcion.toUpperCase(Locale.ROOT).contains("A")){
            System.out.println("OPCIÓN INCORRECTA!!! Debe ingresar sólo p (promoción) o a (atracción) seguido");
            System.out.println("del número de opción disponible.");
            scan.nextLine(); // Esto es para que frene la ejecución en pantalla
            return;
        }

        if(opcion.toUpperCase(Locale.ROOT).contains("P")){
            // Numero de opción elegida
            Integer nroOpcion = Integer.valueOf(opcion.toUpperCase(Locale.ROOT).split("P")[1]);

            List<Promocion> promociones = PromocionesPriorizadasUsuario(usuario);

            // Verifica si la opción ingresada es válida
            if(promociones.size() - Math.abs(nroOpcion) >= 0)
            {
                Promocion promocionSeleccionada = promociones.get(nroOpcion - 1); // Porque arranca en cero la lista
                Boolean resultado = Parque.ComprarPromocion(usuario, promocionSeleccionada);
                // Si está todo ok
                if(resultado){
                	System.out.println();
                	System.out.println("Promoción comprada correctamente.");
                }else {
                	System.out.println();
                    System.out.println("Usted no disponde de los fondos necesarios para comprar la atracción.");
                }
            }
            else
            { // Si la opción ingresada no es válida
            	System.out.println();
                System.out.println("La opción ingresada no es válida para promociones.");
                System.out.println("Por favor, vuelva a ingresar la opción");
            }
        }
        else
        {
            // Numero de opción elegida
            Integer nroOpcion = Integer.valueOf(opcion.toUpperCase(Locale.ROOT).split("A")[1]);

            List<Atraccion> atracciones = AtraccionesPriorizadasUsuario(usuario);

            // Verifica si la opción ingresada es válida
            if(atracciones.size() - Math.abs(nroOpcion) >= 0)
            {
                Atraccion atraccionSeleccionada = atracciones.get(nroOpcion - 1); // Porque arranca en cero la lista
                Boolean resultado = Parque.ComprarAtraccion(atraccionSeleccionada, usuario);

                // Si está todo ok
                if(resultado){
                	System.out.println();
                	System.out.println("Atracción comprada correctamente.");
                }else {
                    System.out.println("Usted no disponde de los fondos necesarios para comprar la atracción.");
                }
            }
            else
            { // Si la opción ingresada no es válida
                System.out.println("La opción ingresada no es válida para promociones.");
                System.out.println(" Por favor, vuelva a ingresar la opción");
            }
        }

        // Para frenar la pantalla
        scan.nextLine();
    }
    private void PantallaFinalizacionCompra(Usuario usuario){
        List<Promocion> promocionesCompradas = Parque.getPromocionesCompradasUsuario(usuario);
        List<Atraccion> atraccionesCompradas = Parque.getAtraccionesCompradasUsuario(usuario);

        Integer costoTotal = 0;
        Double tiempoPromedioTotal = 0.0;

        System.out.println("----------------------------------------");
        System.out.println("Resumen de compra del usuario "+ usuario.getNombre().toUpperCase());
        System.out.println("----------------------------------------");

        // Resumen de promociones compradas
        System.out.println();
        if (promocionesCompradas == null){
            System.out.println("Promociones: No compró alguna promoción");
        }else{
            System.out.println("Listado de promociones compradas: ");
            System.out.println();
            for (Promocion promocion:promocionesCompradas) {
                System.out.println("Promoción: " + promocion.getNombre());
                System.out.println("Costo total de la promoción: " + promocion.getPrecioTotal());
                System.out.println("Tiempo necesario para la promocion: " + promocion.TiempoPromedioTotal());
                System.out.println("Atracciones incluidas en la promoción:");
                for (Atraccion atraccion:promocion.getAtracciones()) {
                    // Elimino las atracciones compradas del listado de atracciones
                    atraccionesCompradas.remove(atraccion);

                    System.out.println("- Atracción: "+atraccion.getNombre());
                    System.out.println(" - Costo total de la atracción: " + atraccion.getCosto());
                    System.out.println(" - Tiempo necesario para la atracción: " + atraccion.getTiempoPromedio());
                }
                System.out.println();

                costoTotal += promocion.getPrecioTotal();
                tiempoPromedioTotal += promocion.TiempoPromedioTotal();
            }
        }

        // Resumen de atracciones compradas
        System.out.println();
        if (atraccionesCompradas == null || atraccionesCompradas.isEmpty()){
            System.out.println("Atracciones: No compró alguna atracción");
        }else{
            System.out.println("Listado de atracciones compradas: ");
            System.out.println();
            for (Atraccion atraccion:atraccionesCompradas) {
                System.out.println("Atracción: " + atraccion.getNombre());
                System.out.println("Costo total de la atracción: " + atraccion.getCosto());
                System.out.println("Tiempo necesario para la atracción: " + atraccion.getTiempoPromedio());
                System.out.println();

                costoTotal += atraccion.getCosto();
                tiempoPromedioTotal += atraccion.getTiempoPromedio();
            }
        }

        // Resumen de costos
        System.out.println();
        System.out.println("Costo total en monedas: " + costoTotal);
        System.out.println("Tiempo requerido: " + tiempoPromedioTotal);

        // Para frenar la pantalla
        scan.nextLine();
        
    }

    // Métodos propios de la clase
    private List<Promocion> PromocionesPriorizadasUsuario(Usuario usuario) {
        List<Promocion> promocionesComprables = Parque.getPromocionesComprables(usuario);

        Comparator<Promocion> comp = Comparator.comparing((Promocion p) -> p.getPrecioTotal()).thenComparing(p -> p.TiempoPromedioTotal()).reversed();
        Collections.sort(promocionesComprables,comp);

        List<Promocion> ret = new ArrayList<>();

        // Filtro por lo que puedo pagar
        for (Promocion promocion:promocionesComprables) {
            if(promocion.TiempoPromedioTotal() <= usuario.getTiempoDisponible()
                    && promocion.getPrecioTotal() <= usuario.getPresupuesto())
            {
                ret.add(promocion);
            }
        }

        return ret;
    }
    private List<Atraccion> AtraccionesPriorizadasUsuario(Usuario usuario){
        List<Atraccion> atraccionesComprables = Parque.getAtraccionesComprables(usuario);

        Comparator<Atraccion> comp = Comparator.comparing((Atraccion a) -> a.getCosto()).thenComparing(a -> a.getTiempoPromedio()).reversed();
        Collections.sort(atraccionesComprables,comp);

        List<Atraccion> ret = new ArrayList<>();

        // Filtro por lo que puedo pagar
        for (Atraccion atraccion:atraccionesComprables) {
            if(atraccion.getTiempoPromedio() <= usuario.getTiempoDisponible()
                    && atraccion.getCosto() <= usuario.getPresupuesto())
            {
                ret.add(atraccion);
            }
        }

        return ret;
    }
    
    //prueba de escritura de txt
    public void salidaPorUsuario() throws IOException {
    
    	PrintWriter salida = new PrintWriter(new FileWriter("ArchivoSalidaDePrueba.txt"));
		
		int nroCualquiera = 12345678;
		String s = "Escribir un nro de prueba:";
		salida.print(s);    
		salida.println(" " + nroCualquiera);
		

		salida.close();
    }
    	
   	
    
}
