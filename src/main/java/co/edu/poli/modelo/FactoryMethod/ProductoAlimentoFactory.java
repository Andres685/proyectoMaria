package co.edu.poli.modelo.FactoryMethod;

import co.edu.poli.modelo.Producto;
import co.edu.poli.modelo.ProductoAlimenticio;

public class ProductoAlimentoFactory implements FactoryProducto <Producto>{
	@Override
	public Producto crearTipoProducto(int id, String descripcion, double calorias) {
		return new ProductoAlimenticio(id, descripcion, calorias);
	}

}
