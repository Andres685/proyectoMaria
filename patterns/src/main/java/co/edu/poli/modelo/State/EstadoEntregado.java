package co.edu.poli.modelo.State;

public class EstadoEntregado implements EstadoPedido{

    @Override
    public String realizarPago(PedidoContexto contexto) {
        return String.format("[Estado Entrgado]El pedido de %s Ya fue Pagado!!\nValor Pagado: $%.2f",contexto.getPedido().getCliente().getName(), contexto.getPedido().getTotal());
    }

    @Override
    public String enviar(PedidoContexto contexto) {
        return String.format("[Estado Entregado]El pedido de %s Ya fue Enviado\nDireccion: %s",contexto.getPedido().getCliente().getName(),contexto.getPedido().getCliente().getCorreo());
    }

    @Override
    public String entregar(PedidoContexto contexto) {
        return String.format("[Estado Entregado]El pedido de %s Ya ha sido Entregado\nDireccion: %s",contexto.getPedido().getCliente().getName(),contexto.getPedido().getCliente().getCorreo());
    }
    @Override
    public String toString(){
        return "Estado Entregado";
    }
    
}
