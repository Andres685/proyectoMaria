package co.edu.poli.modelo.Stragey;

import co.edu.poli.modelo.Cliente;

public class Paypal implements EstrategyPayMethod {
    @Override
    public String pay(double monto, Cliente cliente, String email) {
        return String.format("Pagando %.2f Sin Contacto con Paypal \nCorreo de la Cuenta:  %s, \nDescontando al Usuario: %s", monto, email, cliente.getName());
    }
    
}
