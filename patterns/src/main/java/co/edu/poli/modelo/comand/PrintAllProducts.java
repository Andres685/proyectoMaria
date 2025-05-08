package co.edu.poli.modelo.comand;

import co.edu.poli.modelo.Pedido;
import co.edu.poli.modelo.Producto;

public class PrintAllProducts implements Command{
    private final Pedido pedido;

    public PrintAllProducts(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public String execute() {
        String productos = "";
        if(!pedido.getProducts().isEmpty()){
            for (Producto producto : pedido.getProducts()) {
                productos += producto.toString() + "\n";
            }
            return productos;
        }
        return "No hay Productos para Listar";
    }
    
}
