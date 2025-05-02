package co.edu.poli.modelo.Memento;

import java.util.HashMap;
import java.util.Map;

import co.edu.poli.modelo.Producto;
import co.edu.poli.modelo.Producto.Memento;

public class AlmacenadorDic {
    private Map<String, Memento> estados;
    private final Producto producto;

    public AlmacenadorDic(Producto producto){
        this.estados = new HashMap<>();
        this.producto = producto;
    }

    public void guardar(String año){
        estados.put(año, producto.crearRecuerdo());
    }
    public String desHacer(String año){
        if (estados.remove(año) != null) {
            return "Estado Eliminado" + año; 
        }
        else{
            return "No se Encontro el Estado";
        }
    }

    public String resturar(String año){
        Memento memento = estados.get(año);
        if(memento!=null){
            producto.resturarDesdeMemento(memento);
            return "Estado Resturado";
        }
        else{
            return "No se Encontro el Estado";
        }
    }

    public Map<String, Producto.Memento> getHistorial() {
        return new HashMap<>(estados);
    }


}
