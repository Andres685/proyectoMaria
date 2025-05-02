package co.edu.poli.modelo;


import co.edu.poli.modelo.Observer.Descuento;
import co.edu.poli.modelo.Observer.Observer;

public class Producto implements Observer{
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio){
        this.precio= precio;
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public Memento crearRecuerdo(){
        return new Memento(nombre, precio);
    }
    public void resturarDesdeMemento(Memento memento){
        this.nombre = memento.getNombre();
        this.precio = memento.getPrecio();
    }

    public static class Memento {
        private final double precio;
        private final String nombre;
    
        private Memento (String nombre, double precio){
            this.nombre= nombre;
            this.precio = precio;
        }
    
        private String getNombre(){
            return this.nombre;
        }
        public double getPrecio(){
            return this.precio;
        }
    
        
    }

    @Override
    public String toString(){
        return "Producto \nNombre: "+nombre+" \nPrecio: $"+ precio;
    }

    
    @Override
    public void actualizar(Descuento descuento, String accion) {
        if(accion.equals("Aumentar")){
            precio += (precio * descuento.getDescuento())/100;
        }
        else if(accion.equals("Disminuir")){
            precio -= (precio*descuento.getDescuento())/100;
        }
    }
}
