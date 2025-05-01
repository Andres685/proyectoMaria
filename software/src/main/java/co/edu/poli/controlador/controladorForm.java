package co.edu.poli.controlador;

import java.util.List;
import java.util.ResourceBundle;

import co.edu.poli.modelo.Producto;
import co.edu.poli.modelo.Memento.Almacenador;
import co.edu.poli.modelo.Observer.Descuento;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class controladorForm {
    private Producto producto;
    private Almacenador historial;
    private Descuento descuento;

    @FXML
    private Button deshacerEstado, guardarEstado, mostrarEstados, disminuir, aumentar;

    @FXML
    private Label estadoActual, estadoAct;

    @FXML
    private TextField precioProducto, descuentoAplicar;

    @FXML private TableView<Producto.Memento> tablaEstados;
    @FXML private TableColumn<Producto.Memento, Number> colIndice;
    @FXML private TableColumn<Producto.Memento, Number> colPrecio;

    @FXML
     public void initialize() {
        producto = new Producto("iPhone", 1000.0);
        descuento = new Descuento();
        descuento.añadir(producto);

        historial = new Almacenador(producto);
        historial.guardar();
        estadoActual.setText(producto.toString());


        colIndice.setCellValueFactory(cell ->
            new ReadOnlyObjectWrapper<>(
                2025 + tablaEstados.getItems().indexOf(cell.getValue())
            )
        );
        colPrecio.setCellValueFactory(cell ->
            new ReadOnlyObjectWrapper<>(
                cell.getValue().getPrecio()
            )
        );
    }


    @FXML
    void clickDeshacer(ActionEvent event) {
        historial.deshacer();
        estadoActual.setText(producto.toString());
    }

    @FXML
    void clickEstado(ActionEvent event) {
        if(!precioProducto.getText().isEmpty()){
            double precio = Double.parseDouble(precioProducto.getText());
            producto.setPrecio(precio);
            historial.guardar();
            estadoActual.setText(producto.toString());
            precioProducto.clear();
        }
        else{
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setContentText("Inserta Un Valor A Actualizar");
            alerta.show();
        }
    }

    @FXML
    void clickMostrar(ActionEvent event) {
        tablaEstados.getItems().clear();
        List<Producto.Memento> estados = historial.getHistorial();
        tablaEstados.getItems().addAll(estados);
    }


    @FXML
    void clickAumentar(ActionEvent event) {
        aplicarDescuento("Aumentar");
    }

    @FXML
    void clickDisminuir(ActionEvent event) {
        aplicarDescuento("Disminuir");
    }


    private void aplicarDescuento(String accion){
        if(!descuentoAplicar.getText().isEmpty()){
            int descuentos = Integer.parseInt(descuentoAplicar.getText());
            descuento.setPorcentaje(descuentos, accion);
            historial.guardar();
            estadoActual.setText(producto.toString());
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
}