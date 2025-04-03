package co.edu.poli.modelo;

abstract class CarritoDecorator implements CarritoCompras {
    protected CarritoCompras carritoAtributos;
    public CarritoDecorator(CarritoCompras carritoCompras){
        this.carritoAtributos = carritoCompras;
    }
    public String getDescription() {
        return carritoAtributos.getDescripcion();
    }

    public double getCost() {
        return carritoAtributos.getTotal();
    }

}
