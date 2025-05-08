package co.edu.poli.modelo;

public class Cliente {
    private String name;
    private String estatus;
    public Cliente(String name, String estatus) {
        this.name = name;
        this.estatus = estatus;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEstatus() {
        return estatus;
    }
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    @Override
    public String toString() {
        return "Cliente [name=" + name + ", estatus=" + estatus + "]";
    }
    
}
