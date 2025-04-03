package co.edu.poli.modelo.Bridge;

public class Documento implements Mercancia{

    @Override
    public String tipo() {
        return "Mercancia: Documento";
    }

}
