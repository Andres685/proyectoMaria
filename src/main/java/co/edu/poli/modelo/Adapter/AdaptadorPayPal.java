package co.edu.poli.modelo.Adapter;

public class AdaptadorPayPal implements MetodoPago {
    private PayPal payPal;
    public AdaptadorPayPal() {
        this.payPal = new PayPal();
    }
    @Override
    public String realizarPago(int valor) {
        return payPal.realizarEnvio(valor);
    }
}