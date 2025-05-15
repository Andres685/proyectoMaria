package co.edu.poli.modelo;

import java.util.ArrayList;
import java.util.List;

import co.edu.poli.modelo.Visitor.Elemento;
import co.edu.poli.modelo.Visitor.Visitante;

public class Pedido implements Elemento{
    private Cliente cliente;
    private List<Producto> products;
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
    public List<Producto> getProductos(){
        return products;
    }
    public void agregarProductos(Producto producto){
            products.add(producto);
    }

    public double getTotal(){
        return products.stream().mapToDouble(Producto::getPrecio).sum();
    }

    public Producto getProductoName(String descripcion){
        Producto productoEncontrada = null;
        for (Producto producto : products) {
            if(producto.getDescripcion().toLowerCase().equals(descripcion.toLowerCase())){
                productoEncontrada = producto;
                break;
            }
        }
        return productoEncontrada;
    }


    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.products = new ArrayList<>();
    }
    @Override
    public String toString() {
        return "Pedido [cliente=" + cliente + ", products=" + products + "]";
    }
    @Override
    public String accept(Visitante visitante) {
        return visitante.visit(this);
    }
    
    
}
