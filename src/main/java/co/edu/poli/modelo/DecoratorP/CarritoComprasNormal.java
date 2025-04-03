package co.edu.poli.modelo;

public class CarritoComprasNormal implements CarritoCompras{

    @Override
    public String getDescripcion() {
        return "Manzana: $1000\nTelevisor: $1'200.000\nBateria: $75.000 $\nPapa: $24.000\nEnvio: $70.000";
    }

    @Override
    public double getTotal() {
        return 1370000;
    }

}
