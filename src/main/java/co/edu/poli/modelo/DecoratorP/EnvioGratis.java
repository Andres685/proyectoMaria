package co.edu.poli.modelo.DecoratorP;

public class EnvioGratis extends CarritoDecorator{
    public EnvioGratis(CarritoCompras carrito){
        super(carrito);
    }
    @Override
    public String getDescripcion() {
        return carritoAtributos.getDescripcion() + "\nTu Carrito Tiene Envio Gratis";
    }

    @Override
    public double getTotal() {
        return carritoAtributos.getTotal() - 70000;
    }

}
