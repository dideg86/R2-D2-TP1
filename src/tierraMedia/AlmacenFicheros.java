package tierraMedia;

import java.util.List;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class AlmacenFicheros {
    // Constructores

    public AlmacenFicheros() {
    }

    public static List<Usuario> LeerUsuariosDesdeFichero(String pathArchivo){
        List<Usuario> listadoUsuario = new ArrayList<>();
        try{
            File archivo = new File(pathArchivo);
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            String linea = br.readLine();
            while(linea != null){
                String[] valores = linea.split(",");
                String nombre = valores[0];
                TipoAventura preferencia = TipoAventura.valueOf(valores[1]);
                Integer tiempo = Integer.valueOf(valores[2]);
                Double presupuesto = Double.valueOf(valores[3]);

                Usuario nuevoUsuario = new Usuario(nombre, preferencia, tiempo, presupuesto);
                listadoUsuario.add(nuevoUsuario);

                linea = br.readLine();
            }

            return listadoUsuario;
        }catch (Exception ex)
        {
            System.out.println(ex);
        }
        return listadoUsuario;
    }
    
       //Metodo para leer las atracciones desde txt
    public List<Atraccion> LeerAtraccionesDesdeFichero(String pathArchivo){
        List<Atraccion> listadoAtracciones = new ArrayList<>();
        try{
            File archivo = new File(pathArchivo);
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            String linea = br.readLine();
            while(linea != null){
                String[] valores = linea.split(",");
                String nombre = valores[0];
                Integer costo = Integer.valueOf(valores[1]);
                Double tiempo = Double.valueOf(valores[2]);
                Integer cupo = Integer.valueOf(valores[3]);
                TipoAventura preferencia = TipoAventura.valueOf(valores[4]);
                
                Atraccion nuevaAtraccion = new Atraccion(nombre, costo, tiempo, cupo, preferencia);
                listadoAtracciones.add(nuevaAtraccion);

                linea = br.readLine();
            }

            return listadoAtracciones;
        }catch (Exception ex)
        {
            System.out.println(ex);
        }
        return listadoAtracciones;
    } 
    
      //Metodo para leer las promociones desde txt - SIN TERMINAR    
    public List<Promocion> LeerPromocionesDesdeFichero(String pathArchivo, List<Atraccion> Atracciones){
        try{
            File archivo = new File(pathArchivo);
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            List<Promocion> promociones = new ArrayList();

            String linea = br.readLine();
            while(linea != null){
            	String[] valores = linea.split(",");
            	
            	String nombre = valores[0].trim();
            	TipoPromocion promocion = TipoPromocion.valueOf(valores[1].trim());
            	
            	
            	if (promocion == TipoPromocion.AXB) {
            		List<Atraccion> listado = new ArrayList();
            		for (Atraccion atraccion : Atracciones) {
            			if (atraccion.getNombre().contains(valores[2].trim())) {
            				listado.add(atraccion);
            			}          			
            			if (atraccion.getNombre().contains(valores[3].trim())) {
            				listado.add(atraccion);
            			}  
            			if (atraccion.getNombre().contains(valores[4].trim())) {
            				listado.add(atraccion);
            			}  
					}
            		PromocionAxB promocionAxB = new PromocionAxB(nombre, listado.get(0), listado.get(1), listado.get(2));
            		promociones.add(promocionAxB);
            	}
            	
            	if (promocion == TipoPromocion.ABSOLUTA) {
            		List<Atraccion> listado = new ArrayList();
            		for (Atraccion atraccion : Atracciones) {
            			if (atraccion.getNombre().contains(valores[2].trim())) {
            				listado.add(atraccion);
            			}          			
            			if (atraccion.getNombre().contains(valores[3].trim())) {
            				listado.add(atraccion);
            			}  
					}
            		Integer valorPromocion = Integer.valueOf(valores[4]);
            		PromocionAbsoluta promocionAbsoluta = new PromocionAbsoluta(nombre, listado.get(0), listado.get(1), valorPromocion);
            		promociones.add(promocionAbsoluta);
            	}
            	
            	if (promocion == TipoPromocion.PORCENTUAL) {
            		List<Atraccion> listado = new ArrayList();
            		for (Atraccion atraccion : Atracciones) {
            			if (atraccion.getNombre().contains(valores[2].trim())) {
            				listado.add(atraccion);
            			}          			
            			if (atraccion.getNombre().contains(valores[3].trim())) {
            				listado.add(atraccion);
            			}  
					}
            		Double valorDescuento = Double.valueOf(valores[4]);
            		PromocionPorcentual promocionPorcentual = new PromocionPorcentual(nombre, listado.get(0), listado.get(1), valorDescuento);
            		promociones.add(promocionPorcentual);
            	}
            		
                linea = br.readLine();
            }
            return promociones;
        }catch (Exception ex)
        {
            System.out.println(ex);
        }
		return null;
    }

    //verificar si es numerico
	public static boolean esNumerico(String trim) {
		// TODO Auto-generated method stub
		return trim.matches("-?\\d+(\\.\\d+)?"); // foolprof
	} 
	
}

