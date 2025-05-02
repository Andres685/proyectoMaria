package co.edu.poli.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import co.edu.poli.modelo.Producto;
import co.edu.poli.modelo.Memento.AlmacenadorDic;
import co.edu.poli.modelo.Observer.Descuento;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class controladorFormDic {
    private Producto producto;
    private AlmacenadorDic estados;
    private Descuento descuento;

    @FXML
    private Button aumentar;

    @FXML
    private TextField añoProducto;

    @FXML
    private TextField añoReDes, nombreProducto;

    @FXML private TableView<Map.Entry<String, Producto.Memento>> tablaEstados;
    @FXML private TableColumn<Map.Entry<String, Producto.Memento>, String> colIndice;
    @FXML private TableColumn<Map.Entry<String, Producto.Memento>, Double> colPrecio;

    @FXML
    private TextField descuentoAplicar;

    @FXML
    private Button deshacerEstado;

    @FXML
    private Button disminuir;

    @FXML
    private Label estadoAct;

    @FXML
    private Label estadoActual;

    @FXML
    private Button guardarEstado;

    @FXML
    private Button mostrarEstados;

    @FXML
    private TextField precioProducto;

    @FXML
    private Button recuperarEstado;

     @FXML
    public void initialize() {
        producto = new Producto("iPhone", 1000.0);
        descuento = new Descuento();
        descuento.añadir(producto);
        estados = new AlmacenadorDic(producto);
        estados.guardar("2025");

        estadoActual.setText(producto.toString());

        List<Map.Entry<String, Producto.Memento>> lista = 
            new ArrayList<>(estados.getHistorial().entrySet());
        tablaEstados.getItems().setAll(lista);

        colIndice.setCellValueFactory(cell -> 
            new ReadOnlyObjectWrapper<>(cell.getValue().getKey())
        );

        colPrecio.setCellValueFactory(cell -> 
            new ReadOnlyObjectWrapper<>(cell.getValue().getValue().getPrecio())
        );
    }   

     private void aplicarDescuento(String accion){
        if(!descuentoAplicar.getText().isEmpty() && !añoProducto.getText().isEmpty()){
            int descuentos = Integer.parseInt(descuentoAplicar.getText());
            descuento.setPorcentaje(descuentos, accion);
            estados.guardar(añoProducto.getText());
            estadoActual.setText(producto.toString());
            añoProducto.clear();
            estadoAct.setText("");
            estadoAct.setText("Estado de " + accion + "\nGuardado");
        }
        else{
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setContentText("Inserta Un valor para " + accion + " Descuento");
            alerta.show();
        }
    }



    @FXML
    void clickAumentar(ActionEvent event) {
        aplicarDescuento("Aumentar");
    }

    @FXML
    void clickDeshacer(ActionEvent event) {
        if(!añoReDes.getText().isEmpty()){
            estados.desHacer(añoReDes.getText());
        }
        else{
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setContentText("Inserta Un valor para Deshacer el Estado");
            alerta.show();
        }
    }

    @FXML
    void clickDisminuir(ActionEvent event) {
        aplicarDescuento("Disminuir");
    }

    @FXML
    void clickEstado(ActionEvent event) {
        if(!(añoProducto.getText().isEmpty() && precioProducto.getText().isEmpty())){
            double precio = Double.parseDouble(precioProducto.getText());
            //producto.setNombre(nombreProducto.getText());
            producto.setPrecio(precio);
            estados.guardar(añoProducto.getText());
            añoProducto.clear();
            precioProducto.clear();
            estadoActual.setText(producto.toString());
        }
        else{
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setContentText("Inserta Un valor de Año o Precio para guardar el Estado");
            alerta.show();
        }
    }

    @FXML
    void clickMostrar(ActionEvent event) {
        tablaEstados.getItems().clear();
        tablaEstados.getItems().addAll(estados.getHistorial().entrySet());
    }

    @FXML
    void clickRecuperar(ActionEvent event) {
        if(!añoReDes.getText().isEmpty()){
            estados.resturar(añoReDes.getText());
            estadoActual.setText(producto.toString());
            añoReDes.clear();
        }
        else{
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setContentText("Inserta Un valor de Año para Recuperar el Estado");
            alerta.show();
        }
    }

}

