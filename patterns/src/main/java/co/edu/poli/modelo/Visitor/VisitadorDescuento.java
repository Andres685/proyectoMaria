package co.edu.poli.modelo.Visitor;

import co.edu.poli.modelo.Pedido;
import co.edu.poli.modelo.Producto;

public class VisitadorDescuento implements Visitante {
    private double descuento;
    public VisitadorDescuento(String descuentos){
        this.descuento = Double.parseDouble(descuentos);
    }
    @Override
    public String visit(Pedido pedido) {
        double precioFinal = pedido.getTotal() - (pedido.getTotal() * (descuento/100));
        return String.format("El Precio de Tu Pedido Actual es: $%.2f \nSe Aplicara un Descuento del %.0f\nPrecio Final: $%s",pedido.getTotal(), descuento,precioFinal);
    }
    @Override
    public String visit(Producto producto) {
        double precioFinal = producto.getPrecio() - (producto.getPrecio() * (descuento/100));
        return String.format("El Precio de Tu Producto %s  es: $%.2f \nSe Aplicara un Descuento del %.0f\nPrecio Final: $%s",producto.getDescripcion(),producto.getPrecio(), descuento,precioFinal);

    }

}
