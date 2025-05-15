package co.edu.poli.controlador;

import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import co.edu.poli.modelo.Cliente;
import co.edu.poli.modelo.Pedido;
import co.edu.poli.modelo.Producto;
import co.edu.poli.modelo.Mediator.ConcreteMediator;
import co.edu.poli.modelo.Mediator.Mediator;
import co.edu.poli.modelo.State.EstadoNuevo;
import co.edu.poli.modelo.State.PedidoContexto;
import co.edu.poli.modelo.Visitor.VisitadorDescuento;
import co.edu.poli.modelo.Visitor.Visitante;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class controlador {
    private Cliente morgan = new Cliente("Morgan", 56, "morgan12@gmail.com");
    private Pedido pedio = new Pedido(morgan);
    private Producto p1= new Producto("Gafas", 2500);
    private Producto p2= new Producto("Televisor", 2000);
    private Producto p3= new Producto("Laptop", 1500);
    private Mediator mediador = new ConcreteMediator();
    private PedidoContexto contexto = new PedidoContexto(pedio);
    private Cliente clienteMediator;

    @FXML
    private void initialize(){
        pedio.agregarProductos(p1);
        pedio.agregarProductos(p2);
        pedio.agregarProductos(p3);
        estadoActual.setText(estadoActual.getText() + " " + contexto.getEstado());
    }
    @FXML
    private Button bttAplicar, bttAplicarProducto,  bttAgregarProducto,  bttRealizarPedido,  bttRegistrarCliente;
    @FXML
    private Button bttEntregar, bttReiniciar;

    @FXML
    private Button bttEnviar;

    @FXML
    private Button bttPagar;
    @FXML
    private Label estadoActual;

    @FXML
    private TextField descuentoProducto, descuentoPedido, descripcionProducto, correoCliente, descripcionNuevo, nombreCliente, precioNuevo;

    @FXML
    void clickAplicar(ActionEvent event) {
        String descuento = descuentoPedido.getText();
        Visitante visitante = new VisitadorDescuento(descuento);
        showAlert(pedio.accept(visitante), AlertType.INFORMATION, false);
        descuentoPedido.clear();

    }

    @FXML
    void clickProducto(ActionEvent event) {
        Producto buscar = pedio.getProductoName(descripcionProducto.getText());
        if(buscar!=null){
            Visitante visitante = new VisitadorDescuento(descuentoProducto.getText());
            showAlert(buscar.accept(visitante), AlertType.INFORMATION, false);
            descripcionProducto.clear();
            descuentoProducto.clear();
        }
        else{
            showAlert("No se Encuentra el Producto: " + descripcionProducto.getText(), AlertType.ERROR, false);
            descripcionProducto.clear();
        }
    }


    @FXML
    void clickRegistrar(ActionEvent event) {
        if(!nombreCliente.getText().isEmpty()){
            correoCliente.setPromptText(correoCliente.getPromptText() + nombreCliente.getText());
            if (!correoCliente.getText().isEmpty()) {
                Random random = new Random();
                int edad = random.nextInt(73) + 18;
                clienteMediator = new Cliente(nombreCliente.getText(), edad, correoCliente.getText());
                showAlert(mediador.registrarCliente(clienteMediator), AlertType.INFORMATION, false);
                nombreCliente.clear();
                correoCliente.clear();
            }
            else{
                showAlert("Ingresa un Correo para" + nombreCliente.getText(), AlertType.ERROR, false);
            }
        }
        else{
            showAlert("Ingresa un Nombre", AlertType.ERROR, false);
        }
    }

    @FXML
    void clickAgregarProducto(ActionEvent event) {
        if(!descripcionNuevo.getText().isEmpty()){
            precioNuevo.setPromptText(precioNuevo.getPromptText() + descripcionNuevo.getText());
            if (!precioNuevo.getText().isEmpty()) {
                double precio = Double.parseDouble(precioNuevo.getText());
                Producto nuevoP= new Producto(descripcionNuevo.getText(), precio);
                bttRealizarPedido.setDisable(false);
                showAlert(mediador.registrarProductos(nuevoP), AlertType.INFORMATION, false);
                descripcionNuevo.clear();
                precioNuevo.clear();
            }
            else{
                showAlert("Ingresa un Precio Para " + descripcionNuevo.getText(), AlertType.ERROR, false);
            }
        }
        else{
            showAlert("Ingresa una Descripcion al Producto", AlertType.ERROR, false);
        }
    }
    @FXML
    void clickRealizarPedido(ActionEvent event) {
        String mensaje = mediador.realizarPedido();
        if (mensaje.contains("No Registrado")){
            showAlert(mensaje, AlertType.ERROR, false);
            return;
        }
        if(mensaje.contains("No Hay Productos")){
            showAlert(mensaje, AlertType.ERROR, false);
            return;
        }
        else{
            showAlert(mensaje, AlertType.INFORMATION, true);
        }
    }




    @FXML
    void clickEEntregar(ActionEvent event) {
        showAlert(contexto.entregarPedido(), AlertType.INFORMATION, true);
        estadoActual.setText("Estado Actual: " + contexto.getEstado());
    }

    @FXML
    void clickEnviar(ActionEvent event) {
        showAlert(contexto.enviarPedido(), AlertType.INFORMATION, true);
        estadoActual.setText("Estado Actual: " + contexto.getEstado());
    }

    @FXML
    void clickPagar(ActionEvent event) {
        showAlert(contexto.realizarPago(), AlertType.INFORMATION, true);
        estadoActual.setText("Estado Actual: " + contexto.getEstado());
    }
    @FXML
    void clickReiniciar(ActionEvent event) {
        showAlert("Estado Del Pedido: " + pedio.toString()+"\n"+contexto.getEstado() + "\nReiniciado", AlertType.INFORMATION, true);
        contexto.setEstado(new EstadoNuevo());
        estadoActual.setText("Estado Actual: " + contexto.getEstado());
    }

    private void showAlert(String mensaje, AlertType alerta, boolean mostrarText){
        Alert alert = new Alert(alerta);
        if(mostrarText){
            TextArea area = new TextArea(mensaje);
            area.setMaxWidth(200);
            area.setMaxHeight(200);
            area.setWrapText(true);
            alert.getDialogPane().setContent(area);
            alert.show();
        }
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }

}
