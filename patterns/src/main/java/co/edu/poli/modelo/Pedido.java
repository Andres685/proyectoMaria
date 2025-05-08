package co.edu.poli.modelo;

import java.util.ArrayList;
import java.util.List;

import co.edu.poli.modelo.Chain.HandlerDescuento;
import co.edu.poli.modelo.Stragey.EstrategyPayMethod;

public class Pedido {
    private Cliente cliente;
    private List<Producto> products;
    private double descuento;
    private EstrategyPayMethod metodoPago;

    public Pedido(Cliente cliente, double descuento) {
        this.cliente = cliente;
        this.products = new ArrayList<>();
        this.descuento = descuento;
    }
    public EstrategyPayMethod getPayment(){
        return metodoPago;
    }
    public void setPaymentStrategy(EstrategyPayMethod strategy) {
        this.metodoPago = strategy;
    }

    public String procesarPago(String dato) {
        if (metodoPago == null) {
            return "Metodo de Pago no Especificado";
        }
        return metodoPago.pay(HandlerDescuento.montoActual, cliente,dato);
    }



    public double getDescuento() {
        return descuento;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public List<Producto> getProducts() {
        return products;
    }
    public void setProducts(List<Producto> products) {
        this.products = products;
    }
    public double getMonto(){
        return products.stream().mapToDouble(Producto::getPrecio).sum() ;
    }
    public void addProducts(Producto producto){
        products.add(producto);
    }
    public void setDescuento(double descuento){
        this.descuento =descuento;
    }



    @Override
    public String toString() {
        return "Pedido [cliente=" + cliente + ", products=" + products + "]";
    }
    
}
