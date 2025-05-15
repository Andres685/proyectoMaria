package co.edu.poli.modelo.State;

import co.edu.poli.modelo.Pedido;

public class PedidoContexto {
    private EstadoPedido estado;
    private final Pedido pedido;
    public String getEstado() {
        return estado.toString();
    }
    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public PedidoContexto(Pedido pedido) {
        this.estado = new EstadoNuevo();
        this.pedido = pedido;
    }
    public String realizarPago(){
        return estado.realizarPago(this);
    }
    public String enviarPedido(){
        return estado.enviar(this);
    }
    public String entregarPedido(){
        return estado.entregar(this);
    }
    
}
