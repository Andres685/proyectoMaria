package co.edu.poli.modelo.Memento;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import co.edu.poli.modelo.Producto;

public class Almacenador {
    private final Stack<Producto.Memento> estados = new Stack<>();
    private final Producto producto;

    public Almacenador(Producto producto){
        this.producto = producto;
    }

    public void guardar(){
        estados.push(producto.crearRecuerdo());
    }
    public String  deshacer(){
        if(!estados.isEmpty()){
            Producto.Memento memento = estados.pop();
            producto.resturarDesdeMemento(memento);
            return "Ahora tienes " + estados.size() + " Estados de Producto";
        }
        else{
            return "No hay Historial de estados";
        }
    }

    public List<Producto.Memento> getHistorial(){
        return new ArrayList<>(estados);
    }
}
