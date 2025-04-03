package co.edu.poli.modelo.FactoryMethod;

import co.edu.poli.modelo.Producto;
import co.edu.poli.modelo.ProductoElectrico;

public class ProductoElectricoFactory implements FactoryProducto{

	@Override
	public Producto crearTipoProducto(int id, String descripcion, double voltaje) {
		return new ProductoElectrico(id, descripcion,voltaje);
	}

}
