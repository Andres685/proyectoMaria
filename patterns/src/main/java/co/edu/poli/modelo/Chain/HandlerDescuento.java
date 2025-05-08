package co.edu.poli.modelo.Chain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import co.edu.poli.modelo.Cliente;
import co.edu.poli.modelo.Pedido;
import co.edu.poli.modelo.Producto;

public abstract class HandlerDescuento{
    protected HandlerDescuento descuentoHandler;
    public static double montoActual;

    public HandlerDescuento setSiguiente(HandlerDescuento descuentoHandler){
        this.descuentoHandler = descuentoHandler;
        return descuentoHandler;
    }
    public abstract String handle(Pedido pedido);
}
