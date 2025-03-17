package co.edu.poli.controlador;

import java.sql.SQLException;
import java.util.List;

import co.edu.poli.modelo.Cliente;
import co.edu.poli.modelo.Pedido;
import co.edu.poli.servicio.DaoPedido;

public class ControladorPedido {
    private DaoPedido metodosPedido;
    public ControladorPedido() throws ClassNotFoundException, SQLException{
        this.metodosPedido = new DaoPedido();
    }
    public List<Pedido> obtenerPed(){
		return metodosPedido.obtenerTodos();
	}
	public Pedido obtenerPedId(int id) {
		return metodosPedido.obtenerPorId(id);
	}
	public String insertarPed(Pedido pedido) {
		return metodosPedido.insertar(pedido);
	}
	public String actualizarPed(Pedido pedido) {
		return metodosPedido.actualizar(pedido);
	}
	public String eliminarPed(int id) {
		return metodosPedido.eliminar(id);
	}

}
