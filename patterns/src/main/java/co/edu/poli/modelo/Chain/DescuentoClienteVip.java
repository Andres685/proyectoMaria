package co.edu.poli.modelo.Chain;

import co.edu.poli.modelo.Pedido;

public class DescuentoClienteVip extends HandlerDescuento{
    @Override
    public String handle(Pedido pedido) {
        if(pedido.getCliente().getEstatus().equals("VIP")){
            String inicio = "Descuento por Cliente VIP 30%\nTotal Anterior: $"+pedido.getMonto() + "\nAhora: $";
            montoActual = (pedido.getMonto() - (pedido.getMonto() * 0.30));
            return inicio + montoActual;
        }
        if(pedido.getProducts().isEmpty()){
            return "No se puede Aplicar Descuento, no hay productos en el Pedido";
        }
        if(descuentoHandler!=null){
           return "" + descuentoHandler.handle(pedido);
        }
        return "No se Aplica Descuento Por Cliente";
    }
}
