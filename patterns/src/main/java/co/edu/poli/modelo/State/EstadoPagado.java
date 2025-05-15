package co.edu.poli.modelo.State;

public class EstadoPagado implements EstadoPedido {

    @Override
    public String realizarPago(PedidoContexto contexto) {
        return String.format("[Estado Pagado]El pedido de %s Ya fue Pagado!!\nValor Pagado: $%.2f",contexto.getPedido().getCliente().getName(), contexto.getPedido().getTotal());
    }

    @Override
    public String enviar(PedidoContexto contexto) {
        String mensaje = String.format("[Estado Pagado] El pedido de %s sera enviado a su Destino", contexto.getPedido().getCliente().getName());
        contexto.setEstado(new EstadoEnviado());
        return mensaje;
    }

    @Override
    public String entregar(PedidoContexto contexto) {
        return String.format("[Estado Pagado]El pedido de %s No podra ser Entregado sin Enviar",contexto.getPedido().getCliente().getName());
    }
     @Override
    public String toString(){
        return "Estado Pagado";
    }
    
}
