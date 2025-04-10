package co.edu.poli.modelo.PatronFacade;

public class ClienteFacade {
    private Cliente cliente;
    private MetodoPago metodosCliente;
    private Pedidos pedidosCliente;

    public ClienteFacade(String nombre, String email) {
        this.cliente = new Cliente(nombre, email);
        this.metodosCliente = new MetodoPago();
        this.pedidosCliente = new Pedidos();
    }

    public String actualizarCliente(String nombre, String email){
        cliente.setNombre(nombre);
        cliente.setEmail(email);
        return "Cliente Actualizado: \n " + cliente.toString();
    }
    public String mostrarCliente(){
        return cliente.toString();
    }

    public String mostrarPedidos(){
        return pedidosCliente.mostrarPedido();
    }

    public String realizarPedido(String pedido){
        return pedidosCliente.agregar(pedido);
    }

    public String mostrarMetodos(){
        return metodosCliente.mostrar();
    }

    public String activarMetodoCliente(String metodo){
        return metodosCliente.activarMetodo(metodo);
    }
    public String bloquearMetodo(String Metodo){
        return metodosCliente.bloquearMetodo(Metodo);
    }
    
    
}
