package co.edu.poli.modelo.State;

public class EstadoNuevo implements EstadoPedido{

    @Override
    public String realizarPago(PedidoContexto contexto) {
        String mensaje = String.format("[Estado Nuevo] El pedido fue Pagado de %s con exito \n Valor:$%.2f", contexto.getPedido().getCliente().getName(), contexto.getPedido().getTotal());
        contexto.setEstado(new EstadoPagado());
        return mensaje;
    }

    @Override
    public String enviar(PedidoContexto contexto) {
        return String.format("[Estado Nuevo] El pedido de %s no puede ser Enviado sin Pagar\nCancelar: $%.2f", contexto.getPedido().getCliente().getName(),contexto.getPedido().getTotal());
    }

    @Override
    public String entregar(PedidoContexto contexto) {
        return String.format("[Estado Nuevo] El pedido de %s no puede ser Entregado sin Enviarse y sin Pagarse\nValor: $%.2f\nDireccion: %s", contexto.getPedido().getCliente().getName(),contexto.getPedido().getTotal(),contexto.getPedido().getCliente().getCorreo());
    }
     @Override
    public String toString(){
        return "Estado Nuevo";
    }
    
}
