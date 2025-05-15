package co.edu.poli.modelo.State;

public class EstadoEnviado implements EstadoPedido{

    @Override
    public String realizarPago(PedidoContexto contexto) {
        return String.format("[Estado Enviado]El pedido de %s Ya fue Pagado!!\nValor Pagado: $%.2f",contexto.getPedido().getCliente().getName(), contexto.getPedido().getTotal());
    }

    @Override
    public String enviar(PedidoContexto contexto) {
        return String.format("[Estado Enviado]El pedido de %s Ya fue Enviado\nDireccion: %s",contexto.getPedido().getCliente().getName(),contexto.getPedido().getCliente().getCorreo());
    }

    @Override
    public String entregar(PedidoContexto contexto) {
        String mensaje = String.format("[Estado Enviado] El pedido de %s ya se encuentra en el Destino\nDireccion: %s", contexto.getPedido().getCliente().getName(), contexto.getPedido().getCliente().getCorreo());
        contexto.setEstado(new EstadoEntregado());
        return mensaje;
    }
     @Override
    public String toString(){
        return "Estado Enviado";
    }

}
