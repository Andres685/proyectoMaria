package co.edu.poli.modelo;

public class EnvioNacional extends Envio{
    public EnvioNacional(Mercancia mercancia){
        super(mercancia);
    }

    @Override
    public String enviar() {
        return "Envio Nacional\n" + mercancia.tipo();
    }
    
}
