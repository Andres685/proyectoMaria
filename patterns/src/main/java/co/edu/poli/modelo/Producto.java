package co.edu.poli.modelo;

import co.edu.poli.modelo.Visitor.Elemento;
import co.edu.poli.modelo.Visitor.Visitante;

public class Producto implements Elemento{
    private String descripcion;
    private double precio;
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public Producto(String descripcion, double precio) {
        this.descripcion = descripcion;
        this.precio = precio;
    }
    @Override
    public String toString() {
        return "Producto [descripcion=" + descripcion + ", precio=" + precio + "]";
    }
    @Override
    public String accept(Visitante visitante) {
        return visitante.visit(this);
    }

}
