package co.edu.poli.controlador;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import co.edu.poli.modelo.Cliente;
import co.edu.poli.modelo.Producto;
import co.edu.poli.modelo.ProductoAlimenticio;
import co.edu.poli.modelo.ProductoElectrico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControladorCRUD {

    private ControladorCliente metodosCliente;
    private ControladorProductos metodosProduct;

    public ControladorCRUD() throws ClassNotFoundException, SQLException{
        this.metodosCliente = new ControladorCliente();
        this.metodosProduct = new ControladorProductos();
    }

    @FXML
    private Button btt1;

    @FXML
    private Button btt2;

    @FXML
    private Button btt3;

    @FXML
    private Button btt4;

    @FXML
    private Button btt5;

    @FXML
    private Button btt6;

    @FXML
    private TextField consultarId;

    @FXML
    private TextField eliminarId;

    @FXML
    private TextField insertarNombre;

    @FXML
    private TextArea textAreaClientes;

    @FXML
    void clonarAlimento(ActionEvent event) {
        Producto mango = new ProductoAlimenticio(1, "Mango", 567);
        Producto mangoClonado = mango.cloneProducts();
        metodosProduct.insertarPa(mangoClonado.getId(), mangoClonado.getDescripcion(), ((ProductoAlimenticio) mangoClonado).getAporteCalorico());
        JOptionPane.showMessageDialog(null, "Producto Mango, Clonado!!" + mangoClonado.toString());
    }

    @FXML
    void clonarElectrico(ActionEvent event) {
        Producto bateria = new ProductoElectrico(1, "Bateria", 15);
        Producto bateriaClonada = bateria.cloneProducts();
        metodosProduct.insertarPe(bateriaClonada.getId(), bateriaClonada.getDescripcion(), ((ProductoElectrico) bateriaClonada).getVoltajeEntrada());
        JOptionPane.showMessageDialog(null, "Producto Bateria, Clonado!!" + bateriaClonada.toString());
    }

    @FXML
    void consulta(ActionEvent event) {
        try {
        List<Cliente> clientes = metodosCliente.obtenerCl();
        textAreaClientes.clear();
        if (clientes.isEmpty()) {
            textAreaClientes.appendText("No se encontraron clientes.");
        } else {
            for (Cliente cliente : clientes) {
                textAreaClientes.appendText(cliente.toString() + "\n");
            }
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Ocurrió un error al consultar los clientes.");
    }
    }

    @FXML
    void eliminar(ActionEvent event) {
        String id= eliminarId.getText();
        if(id == ""){
            JOptionPane.showMessageDialog(null, "No puedes Eliminar un Cliente sin su ID");
        }
        else{
            int id_numero = Integer.parseInt(id);
            try {
                JOptionPane.showMessageDialog(null, "Cliente Eliminado: "  + metodosCliente.eliminarCl(id_numero));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error en la Eliminacion");
            }
        }
    }

    @FXML
    void ingresar(ActionEvent event) {
        String nombre= insertarNombre.getText();
        if(nombre == ""){
            JOptionPane.showMessageDialog(null, "No puedes Ingresar un Cliente sin Nombre");
        }
        else{
            try {
                Cliente cliente = new Cliente(0,insertarNombre.getText());
                JOptionPane.showMessageDialog(null, "Cliente Ingresado: "  + metodosCliente.insertarCl(cliente));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error en la Insercion");
            }
        }

    }

    @FXML
    void unaConsulta(ActionEvent event) {
        try {
            int id = Integer.parseInt(consultarId.getText());
            Cliente cliente = metodosCliente.obtenerCl(id);
            JOptionPane.showMessageDialog(null, "Cliente encontrado con ID: " + id + " " + cliente.toString());
        } catch (NumberFormatException e) {
            // En caso de que el texto ingresado no sea un número válido
            JOptionPane.showMessageDialog(null, "El ID debe ser un número");
        }
    }

}
