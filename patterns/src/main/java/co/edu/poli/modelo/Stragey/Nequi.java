package co.edu.poli.modelo.Stragey;

import co.edu.poli.modelo.Cliente;

public class Nequi implements EstrategyPayMethod{
    @Override
    public String pay(double monto, Cliente cliente, String numeroCelular) {
        return String.format("Cuenta Pagada con QR nequi:\n Numero de Celular: %s\nCliente: %s\nMonto: $%s", numeroCelular, cliente.getName(), monto);
    }
    

}
