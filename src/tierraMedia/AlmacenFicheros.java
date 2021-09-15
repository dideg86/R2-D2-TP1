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
    
/*    public List<Atraccion> LeerAtraccionesDesdeFichero(String pathArchivo){
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
    } */
    
/*    //metodo sin terminar para leer las promociones desde txt    
    public List<Promocion> LeerPromocionesDesdeFichero(String pathArchivo){
        List<Promocion> listadoPromociones = new ArrayList<>();
        try{
            File archivo = new File(pathArchivo);
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            String linea = br.readLine();
            while(linea != null){
            	String[] valores = linea.split(",");
            	
            	//acá comienza lo distinto
            	if (!esNumerico(valores[valores.length - 1].trim())) {
            		String nombre = valores[0];
            		String atraccion1 = valores[1];
            		String atraccion2 = valores[1];
            		String atraccionDeRegalo = valores[1];
            		
            		Promocion nuevaPromocion = new 
            		
            		PromocionAxB nuevaPromocion = new PromocionAxB (valores[0]), 
            	}
                
                
                String nombre = valores[0];
                TipoAventura preferencia = TipoAventura.valueOf(valores[1]);
                Integer costo = Integer.valueOf(valores[1]);
                Double tiempo = Double.valueOf(valores[2]);
                Integer cupo = Integer.valueOf(valores[3]);
                
                Promocion nuevaPromocion = new Promocion(nombre, costo, tiempo, cupo, preferencia);
                listadoPromociones.add(nuevaPromocion);

                linea = br.readLine();
            }

            return listadoPromociones;
        }catch (Exception ex)
        {
            System.out.println(ex);
        }
        return listadoPromociones;
    }

    //verificar si es numerico
	public static boolean esNumerico(String trim) {
		// TODO Auto-generated method stub
		return trim.matches("-?\\d+(\\.\\d+)?");
	} */ 
	
}

