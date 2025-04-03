package co.edu.poli.modelo.Composite;

public class Empleado implements IEmpresa {
    private String nombre;
    private String cargo;

    public Empleado(String nombre, String cargo) {
        this.nombre = nombre;
        this.cargo = cargo;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public String mostrar(int nivel) {
        return "\t".repeat(nivel) + "Empleado{nombre='" + nombre + "': Cargo='" + cargo + "'}";
    }
}
