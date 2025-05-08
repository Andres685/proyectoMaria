package co.edu.poli.modelo.comand;

import co.edu.poli.modelo.Pedido;

public class ClearProductosCommand implements Command{
    private final Pedido pedido;
    

    public ClearProductosCommand(Pedido pedido) {
        this.pedido = pedido;
    }


    @Override
    public String execute() {
        if(!pedido.getProducts().isEmpty()){
            pedido.getProducts().clear();
            return "Todos Los Productos se Han eliminado";
        }
        else{
            return "No hay Productos para Eliminar";
        }
    }
    
}
