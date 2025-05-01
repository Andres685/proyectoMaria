package co.edu.poli.modelo.Observer;

public interface DescuentoObservable {
    void añadir(Observer observer);
    void eliminar(Observer observer);
    void notificar(String accion);
}
