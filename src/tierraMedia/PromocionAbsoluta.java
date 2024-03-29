package tierraMedia;

public class PromocionAbsoluta extends Promocion {
    // Propiedades de la clase
    private Integer PrecioTotal;

    // Constructor
    public PromocionAbsoluta(String nombre, Atraccion atraccion1, Atraccion atraccion2, Integer precioTotal) {
        super(nombre, atraccion1, atraccion2);
        PrecioTotal = precioTotal;
    }

    // M�todos de la clase
    @Override
    public Integer getPrecioTotal() {
        return PrecioTotal;
    }
}
