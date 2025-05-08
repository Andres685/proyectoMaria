package co.edu.poli.modelo.comand;

public class CommandInvoker {
    private Command comando;
    public void setCommand(Command accion){
        comando = accion;
    }
    public String execute() {
        if (comando == null) {
            return "No hay ningún comando asignado.";
        }
        return comando.execute();
    }
}
