package co.edu.poli.modelo.Mediator;

import java.util.List;

import co.edu.poli.modelo.Cliente;
import co.edu.poli.modelo.Pedido;
import co.edu.poli.modelo.Producto;

public interface Mediator {
    String registrarCliente(Cliente cliente);
    String registrarProductos(Producto producto);
    String realizarPedido();
    String notificarCliente(Cliente cliente);
}
