package co.edu.poli.modelo.Mediator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.poli.modelo.Cliente;
import co.edu.poli.modelo.Pedido;
import co.edu.poli.modelo.Producto;

public class ConcreteMediator implements Mediator {
    private Cliente clienteActual;
    private List<Producto>productos = new ArrayList<>();
    private Pedido pedido;


    @Override
    public String registrarCliente(Cliente cliente){
        this.clienteActual = cliente;
        return clienteActual.toString() + "\n Registrado Con Exito";
    }
    @Override
    public String registrarProductos(Producto producto){
        this.productos.add(producto);
        return producto.toString() + "\nAñadido al Pedido";
    }
    @Override
    public String realizarPedido() {
        if(clienteActual == null){
            return "Cliente Aun No Registrado";
        }
        if(productos.isEmpty()){
            return "No Hay Productos En el Pedido";
        }
        pedido = new Pedido(clienteActual);
        for (Producto producto : productos) {
            pedido.agregarProductos(producto);
        }
        String mensaje = "Procesando pedido de " + clienteActual.getName() + " para Los Productos:\n" + productos.stream().map(Producto::getDescripcion).collect(Collectors.joining("\n"));
        double precio = pedido.getTotal();
        mensaje +="\nEl precio del pedido: $" + precio;
        return notificarCliente(clienteActual) + mensaje;
    }

    @Override
    public String notificarCliente(Cliente cliente) {
        return"Enviando notificación a " + cliente.getName() + ":\nCorreo Electronico: " + cliente.getCorreo() + "\nNotificacion: ";
    }
}
