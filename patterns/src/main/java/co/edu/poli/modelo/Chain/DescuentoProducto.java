package co.edu.poli.modelo.Chain;

import co.edu.poli.modelo.Pedido;

public class DescuentoProducto extends HandlerDescuento{
    @Override
    public String handle(Pedido pedido) {
        if(pedido.getDescuento()>0){
            String inicio = "Descuento por Monto " + pedido.getDescuento()+"%\nTotal Anterior: $"+pedido.getMonto()+"\nAhora: $";
            montoActual = (pedido.getMonto() - pedido.getMonto() * (pedido.getDescuento()/100));
            return inicio + montoActual;
        }
        if(descuentoHandler!=null){
           return "" + descuentoHandler.handle(pedido);
        }
        return "No se Aplica Descuento por Pedido";
    }
    
}
