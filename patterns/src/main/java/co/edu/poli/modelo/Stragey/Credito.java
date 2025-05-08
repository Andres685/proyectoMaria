package co.edu.poli.modelo.Stragey;

import co.edu.poli.modelo.Cliente;

public class Credito implements EstrategyPayMethod{
    @Override
    public String pay(double monto, Cliente cliente, String numeroTarjeta) {
        return String.format("Pagando $%.0f con Tarjeta Debito \nNumero de Tarjeta:  %s, \nDescontando al Propietario: %s", monto, numeroTarjeta, cliente.getName());
    }
    
}
