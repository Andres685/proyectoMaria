package co.edu.poli.modelo;

import co.edu.poli.modelo.Clone.IProtoTypeClone;

public abstract class Producto implements IProtoTypeClone{
	private int id;
	private String descripcion;
	
	public Producto(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	@Override
	public String toString() {
		return "Producto [id=" + id + ", descripcion=" + descripcion + "]";
	}
	
}
