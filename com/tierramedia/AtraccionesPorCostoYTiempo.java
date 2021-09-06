package com.tierramedia;

import java.util.Comparator;

import com.tierramedia.modelos.Atraccion;
import com.tierramedia.modelos.Promocion;
import java.util.List;


public class AtraccionesPorCostoYTiempo implements Comparator<Atraccion> {
	
	private List<Promocion> promociones;
	
	public AtraccionesPorCostoYTiempo(List<Promocion> promociones) {
		this.promociones = promociones;
	}
	
    @Override
    public int compare(Atraccion a1, Atraccion a2) {
        int costoComp = a1.getCosto().intValue() == a2.getCosto().intValue() ? 0 :
        	(a1.getCosto().intValue() > a2.getCosto().intValue() ? 1 : -1);
        if (costoComp != 0) {
           return costoComp;
        }

        return a1.getTiempoPromedio().doubleValue() == a2.getTiempoPromedio().doubleValue() ? 0 :
        	(a1.getTiempoPromedio().doubleValue() > a2.getTiempoPromedio().doubleValue() ? 1 : -1);
    }
}