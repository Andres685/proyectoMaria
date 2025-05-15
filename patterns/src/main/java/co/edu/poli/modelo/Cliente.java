package co.edu.poli.modelo;

public class Cliente{
    private String name;
    private int edad;
    private String correo;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public Cliente(String name, int edad, String correo) {
        this.name = name;
        this.edad = edad;
        this.correo = correo;
    }
    public Cliente(){}
    @Override
    public String toString() {
        return "Cliente [name=" + name + ", edad=" + edad + ", correo=" + correo + "]";
    }
    
}