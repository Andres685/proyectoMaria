package co.edu.poli.modelo.FactoryMethod;

public interface FactoryProducto <Producto>{
	Producto crearTipoProducto(int id, String descripcion, double medida);
}
