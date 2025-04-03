package co.edu.poli.modelo.Bridge;

public class EnvioNacional extends Envio{
    public EnvioNacional(Mercancia mercancia){
        super(mercancia);
    }

    @Override
    public String enviar() {
        return "Envio Nacional\n" + mercancia.tipo();
    }
    
}
