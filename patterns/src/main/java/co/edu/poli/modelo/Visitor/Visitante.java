package co.edu.poli.modelo.Visitor;

import co.edu.poli.modelo.Pedido;
import co.edu.poli.modelo.Producto;

public interface Visitante {
    String visit(Pedido pedido);
    String visit(Producto producto);
}
