package co.edu.poli.modelo.DecoratorP;

abstract class CarritoDecorator implements CarritoCompras {
    protected CarritoCompras carritoAtributos;
    protected CarritoDecorator(CarritoCompras carritoCompras){
        this.carritoAtributos = carritoCompras;
    }
    public String getDescription() {
        return carritoAtributos.getDescripcion();
    }

    public double getCost() {
        return carritoAtributos.getTotal();
    }

}
