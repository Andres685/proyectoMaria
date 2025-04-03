package co.edu.poli.modelo.DecoratorP;

public class Puntos extends CarritoDecorator{
    public Puntos(CarritoCompras carrito){
        super(carrito);
    }

    @Override
    public String getDescripcion() {
        return carritoAtributos.getDescripcion() + "\nSe te Descontaran tus 20 Puntos = $30.000 cop";
    }

    @Override
    public double getTotal() {
        return carritoAtributos.getTotal() - 30000;
    }
    

}
