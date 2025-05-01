package co.edu.poli.modelo.Observer;

import java.util.ArrayList;
import java.util.List;

public class Descuento implements DescuentoObservable{
    private int descuento;
    private List<Observer> suscriptores = new ArrayList<>();


    public Descuento(){}

    public void setPorcentaje(int nuevo, String accion){
        descuento = nuevo;
        notificar(accion);
    }

    public int getDescuento(){
        return descuento;
    }
    @Override
    public void añadir(Observer observer) {
        suscriptores.add(observer);
    }
    @Override
    public void eliminar(Observer observer) {
        suscriptores.remove(observer);
    }
    @Override
    public void notificar(String accion) {
        for (Observer observer : suscriptores) {
            observer.actualizar(this, accion);
        }
    }

    
}
