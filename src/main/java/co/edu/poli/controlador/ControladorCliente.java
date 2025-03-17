package co.edu.poli.controlador;

import java.sql.SQLException;
import java.util.List;

import co.edu.poli.modelo.Cliente;
import co.edu.poli.servicio.DaoCliente;

public class ControladorCliente {
	private DaoCliente metodoCliente;
	public ControladorCliente() throws ClassNotFoundException, SQLException {
		this.metodoCliente = new DaoCliente();
	}
	public List<Cliente> obtenerCl(){
		return metodoCliente.obtenerTodos();
	}
	public Cliente obtenerCl(int id) {
		return metodoCliente.obtenerPorId(id);
	}
	public String insertarCl(Cliente cliente) {
		return metodoCliente.insertar(cliente);
	}
	public String actualizarCl(Cliente cliente) {
		return metodoCliente.actualizar(cliente);
	}
	public String eliminarCl(int id) {
		return metodoCliente.eliminar(id);
	}
}
