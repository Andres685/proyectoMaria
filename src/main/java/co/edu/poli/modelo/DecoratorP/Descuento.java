package co.edu.poli.modelo.DecoratorP;

public class Descuento extends CarritoDecorator{
    public Descuento(CarritoCompras carrito){
        super(carrito);
    }
    @Override
    public String getDescripcion() {
        return carritoAtributos.getDescripcion() + "\nAplicando 20% Descuento....";
    }

    @Override
    public double getTotal() {
        return carritoAtributos.getTotal() * 0.8;
    }


}
