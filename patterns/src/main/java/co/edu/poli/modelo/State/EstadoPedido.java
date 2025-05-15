package co.edu.poli.modelo.State;

public interface EstadoPedido{
    String realizarPago(PedidoContexto contexto);
    String enviar(PedidoContexto contexto);
    String entregar(PedidoContexto contexto);
}
