package co.edu.poli.modelo.Bridge;

public class EnvioInternacional extends Envio{
    public EnvioInternacional(Mercancia mercancia){
        super(mercancia);
    }

    @Override
    public String enviar() {
        return "Envio Internacional\n" + mercancia.tipo();
    }


}
