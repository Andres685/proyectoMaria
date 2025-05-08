package co.edu.poli.modelo.Stragey;

import co.edu.poli.modelo.Cliente;
import co.edu.poli.modelo.Pedido;

public interface EstrategyPayMethod {
    String pay(double monto, Cliente cliente, String dato);
}
