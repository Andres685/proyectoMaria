package co.edu.poli.controlador;

import java.sql.SQLException;
import java.util.List;

import co.edu.poli.modelo.FactoryProducto;
import co.edu.poli.modelo.Producto;
import co.edu.poli.modelo.ProductoAlimenticio;
import co.edu.poli.modelo.ProductoAlimentoFactory;
import co.edu.poli.modelo.ProductoElectrico;
import co.edu.poli.modelo.ProductoElectricoFactory;
import co.edu.poli.servicio.DaoProductoAlimenticio;
import co.edu.poli.servicio.DaoProductoElectrico;
import co.edu.poli.servicio.IDaoProducto;

public class ControladorProductos {
    private IDaoProducto<Producto> metodoProductoA;
    private IDaoProducto<Producto> metodoProductoE;
    private FactoryProducto factoryAlimento;
    private FactoryProducto factoryElectrico;
    
    public ControladorProductos() throws ClassNotFoundException, SQLException {
        this.metodoProductoA = new DaoProductoAlimenticio();
        this.metodoProductoE = new DaoProductoElectrico();
        this.factoryAlimento = new ProductoAlimentoFactory();
        this.factoryElectrico = new ProductoElectricoFactory();
    }
    
    public ProductoAlimenticio crearProductoAlimenticio(int id, String descripcion, double aporteCalorico) {
        return (ProductoAlimenticio) factoryAlimento.crearTipoProducto(id, descripcion, aporteCalorico);
    }
    
    public ProductoElectrico crearProductoElectrico(int id, String descripcion, double voltajeEntrada) {
        return (ProductoElectrico) factoryElectrico.crearTipoProducto(id, descripcion, voltajeEntrada);
    }
    
    public List<Producto> obtenerPa() {
        return metodoProductoA.obtenerTodos();
    }
    
    public Producto obtenerPaId(int id) {
        return metodoProductoA.obtenerPorId(id);
    }
    
    public String insertarPa(int id, String descripcion, double aporteCalorico) {
        Producto productoAlimento = crearProductoAlimenticio(id, descripcion, aporteCalorico);
        return metodoProductoA.insertar(productoAlimento);
    }
    
    public String actualizarPa(int id, String descripcion, double aporteCalorico) {
        Producto productoAlimento = crearProductoAlimenticio(id, descripcion, aporteCalorico);
        return metodoProductoA.actualizar(productoAlimento);
    }
    
    public String eliminarPa(int id) {
        return metodoProductoA.eliminar(id);
    }
    
    
    public List<Producto> obtenerPe() {
        return metodoProductoE.obtenerTodos();
    }
    
    public Producto obtenerPeId(int id) {
        return metodoProductoE.obtenerPorId(id);
    }
    
    public String insertarPe(int id, String descripcion, double voltajeEntrada) {
        Producto productoElectrico = crearProductoElectrico(id, descripcion, voltajeEntrada);
        return metodoProductoE.insertar(productoElectrico);
    }
    
    public String actualizarPe(int id, String descripcion, double voltajeEntrada) {
        Producto productoElectrico = crearProductoElectrico(id, descripcion, voltajeEntrada);
        return metodoProductoE.actualizar(productoElectrico);
    }
    
    public String eliminarPe(int id) {
        return metodoProductoE.eliminar(id);
    }
    
}