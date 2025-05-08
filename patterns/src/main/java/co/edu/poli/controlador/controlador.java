package co.edu.poli.controlador;
import java.lang.reflect.Array;
import java.util.Arrays;

import co.edu.poli.modelo.Cliente;
import co.edu.poli.modelo.Pedido;
import co.edu.poli.modelo.Producto;
import co.edu.poli.modelo.Chain.DescuentoClienteVip;
import co.edu.poli.modelo.Chain.DescuentoProducto;
import co.edu.poli.modelo.Chain.DescuentoMontos;
import co.edu.poli.modelo.Chain.HandlerDescuento;
import co.edu.poli.modelo.Stragey.Credito;
import co.edu.poli.modelo.Stragey.EstrategyPayMethod;
import co.edu.poli.modelo.Stragey.Nequi;
import co.edu.poli.modelo.Stragey.Paypal;
import co.edu.poli.modelo.comand.ClearProductosCommand;
import co.edu.poli.modelo.comand.Command;
import co.edu.poli.modelo.comand.CommandInvoker;
import co.edu.poli.modelo.comand.PrintAllProducts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert.AlertType;

public class controlador {
    private Cliente morgan = new Cliente("morgan", null);
    private Producto producto1 = new Producto("Audifonos", 1000);
    private Producto producto2 = new Producto("Celular", 1500);
    private Producto producto3 = new Producto("Gafas", 1200);
    private Pedido pedido = new Pedido(morgan, 0);
    private boolean procesado = false;
    private CommandInvoker invocador = new CommandInvoker();


    @FXML
    private void initialize(){
        pedido.addProducts(producto1);
        pedido.addProducts(producto2);
        pedido.addProducts(producto3);
    }
    @FXML
    private Button bttDesc10, bttDesc20, bttDesc25, bttNormal, bttProcesar, bttProductos, bttVIP,  bttPaypal, bttNequi, bttTarjeta, bttListar,bttEliminar;
    @FXML
    private TextField produ,  infoPago;

    //Patrons CHAIN
    @FXML
    void click10Descuento(ActionEvent event) {
        pedido.setDescuento(10);
        showAlert("Descuento Agregado del 10% al Pedido\n Pedido + 10%", AlertType.INFORMATION, false);
    }

    @FXML
    void click20Descuento(ActionEvent event) {
        pedido.setDescuento(20);
        showAlert("Descuento Agregado del 20% al Pedido\n Pedido + 20%", AlertType.INFORMATION, false);
    }

    @FXML
    void click25Descuento(ActionEvent event) {
        pedido.setDescuento(25);
        showAlert("Descuento Agregado del 25% al Pedido\n Pedido + 25%", AlertType.INFORMATION, false);
    }

    @FXML
    void clickNormal(ActionEvent event) {
        morgan.setEstatus("NORMAL");
        showAlert("Estatus del Cliente Modificado\n"+morgan.toString(), AlertType.INFORMATION, false);
    }

    @FXML
    void clickProcesar(ActionEvent event) {
        if(morgan.getEstatus()!=null){
            HandlerDescuento descuento = new DescuentoClienteVip();
            descuento.setSiguiente(new DescuentoProducto()).setSiguiente(new DescuentoMontos());
            showAlert("Tipo de Descuento:\n " + descuento.handle(pedido), AlertType.INFORMATION, false);
        }
        else{
            mostrarError("Establece un Estatus para " + morgan.getName());
            return;
        }
        procesado = true;
    }

    @FXML
    void clickProductos(ActionEvent event) {
        showAlert(null, AlertType.INFORMATION, true);
    }

    @FXML
    void clickVIP(ActionEvent event) {
        morgan.setEstatus("VIP");
        showAlert("Estatus del Cliente Modificado\n"+morgan.toString(), AlertType.INFORMATION, false);
    }



    //patron Strategy

    @FXML
    void clickNequi(ActionEvent event) {
        infoPago.setPromptText("Ingresa Numero de Telefono");
        pedido.setPaymentStrategy(new Nequi());
    }
    @FXML
    void clickPaypal(ActionEvent event) {
        infoPago.setPromptText("Ingresa Correo");
        pedido.setPaymentStrategy(new Paypal());
    }
    @FXML
    void clickTarjeta(ActionEvent event) {
        infoPago.setPromptText("Ingresa Numero de Tarjeta");
        pedido.setPaymentStrategy(new Credito());
    }
    @FXML
    void clickPagar(ActionEvent event) {
        if(!infoPago.getText().isEmpty() && procesado ==true){
            showAlert("Metodo de Pago Procesado\n" + pedido.procesarPago(infoPago.getText()), AlertType.INFORMATION, false);
        }
        else if(!infoPago.getText().isEmpty()){
            mostrarError("Debes Procesar El Pedido Primero");
        }
        else{
            mostrarError("Ingresa datos De pago antes de continuar");
        }
        infoPago.clear();
    }


    //patron Command
    
    @FXML
    void clickEliminar(ActionEvent event) {
        invocador.setCommand(new ClearProductosCommand(pedido));
        showAlert("Ejecutando Comando de Listar:\n" + invocador.execute(), AlertType.INFORMATION, false);
        produ.setPromptText("");
    }

    @FXML
    void clickListar(ActionEvent event) {
        invocador.setCommand(new PrintAllProducts(pedido));
        showAlert("Ejecutando Comando de Listar:\n" + invocador.execute(), AlertType.INFORMATION, false);
    }


    private void showAlert(String mensaje, AlertType alert, boolean editable) {
        Alert alerta = new Alert(alert);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
    
        if (editable) {
            TextField descripcion = new TextField();
            TextField precio = new TextField();
            descripcion.setPromptText("Descripción del nuevo producto");
            precio.setPromptText("Precio del producto");
    
            VBox content = new VBox(10); 
            content.getChildren().addAll(
                new Label("Descripción:"), descripcion,
                new Label("Precio:"), precio
            );
    
            alerta.getDialogPane().setContent(content);
            alerta.showAndWait().ifPresent(response -> {
                try {
                    String desc = descripcion.getText();
                    double precioVal = Double.parseDouble(precio.getText());
                    Producto nuevo = new Producto(desc, precioVal);
                    pedido.addProducts(nuevo);
                    produ.setPromptText(produ.getPromptText()+"-"+ nuevo.getNombre());
                    showAlert("Producto Agregado Con Exito\n" + nuevo.toString(), AlertType.INFORMATION, false);
                } catch (NumberFormatException e) {
                    mostrarError("El precio debe ser un número válido.");
                }
            });
        } else {
            alerta.show();
        }
    }
    
    private void mostrarError(String mensaje) {
        Alert error = new Alert(AlertType.ERROR);
        error.setHeaderText(null);
        error.setContentText(mensaje);
        error.showAndWait();
    }
    

}
