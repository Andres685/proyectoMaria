package co.edu.poli.modelo.Chain;

import co.edu.poli.modelo.Pedido;

public class DescuentoMontos extends HandlerDescuento{

    @Override
    public String handle(Pedido pedido) {
        if(pedido.getMonto()>=1000000){
            String inicio = "Descuento por Monto 15%: Saldo Anterior $"+pedido.getMonto() +"\nNuevo Saldo: $";
            montoActual = (pedido.getMonto() - pedido.getMonto() * 0.15);
            return inicio+montoActual;
        } 
        if(descuentoHandler!=null){
            return "" +descuentoHandler.handle(pedido);
        }
        return "No se Aplica descuento por Nada\nValor a Pagar: $"+pedido.getMonto();
    }

}
