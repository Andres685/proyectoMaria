package co.edu.poli.controlador;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import co.edu.poli.modelo.Cliente;
import co.edu.poli.modelo.Producto;
import co.edu.poli.modelo.ProductoAlimenticio;
import co.edu.poli.modelo.ProductoElectrico;
import co.edu.poli.modelo.Adapter.AdaptadorNequi;
import co.edu.poli.modelo.Adapter.AdaptadorPayPal;
import co.edu.poli.modelo.Adapter.MetodoPago;
import co.edu.poli.modelo.Bridge.Documento;
import co.edu.poli.modelo.Bridge.Envio;
import co.edu.poli.modelo.Bridge.EnvioInternacional;
import co.edu.poli.modelo.Bridge.EnvioNacional;
import co.edu.poli.modelo.Bridge.Fragil;
import co.edu.poli.modelo.Bridge.Mercancia;
import co.edu.poli.modelo.Bridge.Normal;
import co.edu.poli.modelo.Builder.Certificacion;
import co.edu.poli.modelo.Builder.Evaluacion;
import co.edu.poli.modelo.Builder.PoliticaEntrega;
import co.edu.poli.modelo.Builder.Proveedor;
import co.edu.poli.modelo.Composite.Departamento;
import co.edu.poli.modelo.Composite.Empleado;
import co.edu.poli.modelo.DecoratorP.CarritoCompras;
import co.edu.poli.modelo.DecoratorP.CarritoComprasNormal;
import co.edu.poli.modelo.DecoratorP.Descuento;
import co.edu.poli.modelo.DecoratorP.EnvioGratis;
import co.edu.poli.modelo.DecoratorP.Puntos;
import co.edu.poli.servicio.DaoCliente;
import co.edu.poli.servicio.DaoProductoAlimenticio;
import co.edu.poli.servicio.DaoProductoElectrico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControladorCRUD {

    private DaoCliente metodosCliente;
    private DaoProductoAlimenticio metodosProductalimento;
    private DaoProductoElectrico metodosProductelectrico;

    public ControladorCRUD() throws ClassNotFoundException, SQLException {
        this.metodosCliente = new DaoCliente();
        this.metodosProductalimento = new DaoProductoAlimenticio();
        this.metodosProductelectrico = new DaoProductoElectrico();
    }

    @FXML
    private Button btt1, btt2, btt3, btt4, btt5, btt6, bttActualizar, bttAdapatar,  bttBuilder,bttComposite, bttEnvio, bttCarrito,bttAplicar;

    @FXML
    private ComboBox<String> bttMedio, bttOpcionEnvio, bttMercancia;
    @FXML
    private CheckBox certificacion, polientrega,evaluacion,envioGratis,devolucion,puntos,envioNacional, envioInternacional,mercanciaFragil, mercanciaDocumento,mercanciaNormal, descuento;

    @FXML
    private TextField consultarId, eliminarId, insertarNombre, productoAlimento, productoElectrico, valorPago, nombreProveedor, actualizarid, actualizarNombre;


    @FXML
    private TextArea textAreaClientes, mostrarJerarquia, mostrarCarrito;

    @FXML
    public void initialize() {
        bttMedio.getItems().addAll("Nequi", "PayPal");
        bttOpcionEnvio.getItems().addAll("Nacional", "Internacional");
        bttMercancia.getItems().addAll("Documento", "Fragil", "Normal");
        System.out.println("hola  mundo");
    }
    
    @FXML
    void Adaptar(ActionEvent event) {
        MetodoPago metodoPago;
        String opcion = bttMedio.getValue();
        if(opcion != null && !valorPago.getText().isEmpty()) {
            switch (opcion) {
                case "Nequi":
                    metodoPago = new AdaptadorNequi();
                    break;
                case "PayPal":
                    metodoPago = new AdaptadorPayPal();
                    break;
                default:
                    metodoPago = null;
                    break;
            }
            int valor = Integer.parseInt(valorPago.getText());
            valorPago.clear();
            String mensaje = metodoPago.realizarPago(valor);
            bttMedio.setValue(null);
            JOptionPane.showMessageDialog(null, mensaje);
        }
    }

    @FXML
    void clickMedio(ActionEvent event) {
        String opcion = bttMedio.getValue();
        if(opcion != null) {
            switch (opcion) {
                case "Nequi":
                    valorPago.setPromptText("Valor a pagar con Nequi");
                    break;
                case "PayPal":
                    break;
                default:
                    valorPago.setPromptText("Valor a pagar");
                    break;
            }
        }
    }


    @FXML
    void clonarAlimento(ActionEvent event) {
        Producto mango = new ProductoAlimenticio(1, "Mango", 567);
        Producto mangoClonado = mango.cloneProducts();
        metodosProductalimento.insertar(mangoClonado);
        JOptionPane.showMessageDialog(null, "Producto Mango, Clonado!! " + mangoClonado.toString());
    }


    @FXML
    void clonarElectrico(ActionEvent event) {
        Producto bateria = new ProductoElectrico(1, "Bateria", 15);
        Producto bateriaClonada = bateria.cloneProducts();
        metodosProductelectrico.insertar(bateriaClonada);
        JOptionPane.showMessageDialog(null, "Producto Bateria, Clonado!! " + bateriaClonada.toString());
    }

    @FXML
    void consulta(ActionEvent event) {
        try {
            List<Cliente> clientes = metodosCliente.obtenerTodos();
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
        String id = eliminarId.getText();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No puedes eliminar un Cliente sin su ID");
        } else {
            try {
                int id_numero = Integer.parseInt(id);
                JOptionPane.showMessageDialog(null, "Cliente Eliminado: " + metodosCliente.eliminar(id_numero));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error en la eliminación");
            }
        }
    }

    @FXML
    void ingresar(ActionEvent event) {
        String nombre = insertarNombre.getText();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No puedes ingresar un Cliente sin Nombre");
        } else {
            try {
                Cliente cliente = new Cliente(0, nombre);
                JOptionPane.showMessageDialog(null, "Cliente Ingresado: " + metodosCliente.insertar(cliente));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error en la inserción");
            }
        }
    }

    @FXML
    void unaConsulta(ActionEvent event) {
        try {
            int id = Integer.parseInt(consultarId.getText());
            Cliente cliente = metodosCliente.obtenerPorId(id);
            JOptionPane.showMessageDialog(null, "Cliente encontrado con ID: " + id + " " + cliente.toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número");
        }
    }

    @FXML
    void actulizarCliente(ActionEvent event) {
        try {
            int id = Integer.parseInt(actualizarid.getText());
            String nombre = actualizarNombre.getText();
            Cliente cliente = new Cliente(id, nombre);
            JOptionPane.showMessageDialog(null, "Cliente Actualizado: " + metodosCliente.actualizar(cliente));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al actualizar el cliente.");
        }
    }
    
    @FXML
    void clickBuildProveedor(ActionEvent event) {
        Proveedor.Builder proveedor1 = new Proveedor.Builder();

        if(certificacion.isSelected()) {
            proveedor1.certificacion(new Certificacion());
        }
        if(evaluacion.isSelected()){
            proveedor1.evaluacion(new Evaluacion());
        }
        if (polientrega.isSelected()) {
            proveedor1.politicaEntrega(new PoliticaEntrega());
        }
        Proveedor proveedor = proveedor1.build();
        JOptionPane.showMessageDialog(null, proveedor.mostrarInfo());
    }

    @FXML
    void ClickComposite(ActionEvent event) {
        Empleado emp1 = new Empleado("Juan Pérez", "Desarrollador");
        Empleado emp2 = new Empleado("María López", "Diseñadora");
        Empleado emp3 = new Empleado("Carlos Gómez", "Gerente");
        Empleado emp4 = new Empleado("Ana Martínez", "Directora");
        Departamento deptDesarrollo = new Departamento("Desarrollo");
        Departamento deptDiseno = new Departamento("Diseño");
        Departamento deptTecnologia = new Departamento("Tecnología");
        Departamento deptEmpresa = new Departamento("Empresa");
        
        deptDesarrollo.agregarMiembro(emp1);
        deptDiseno.agregarMiembro(emp2);
        
        deptTecnologia.agregarMiembro(deptDesarrollo);
        deptTecnologia.agregarMiembro(deptDiseno);
        deptTecnologia.agregarMiembro(emp3);
        
        deptEmpresa.agregarMiembro(deptTecnologia);
        deptEmpresa.agregarMiembro(emp4);
        
        mostrarJerarquia.setText(deptEmpresa.mostrar(0));
    }

    @FXML
    void clickEnvio(ActionEvent event){
        String tipoEnvio = bttOpcionEnvio.getValue();
        String tipoMercancia = bttMercancia.getValue();
        if(tipoEnvio!= null && tipoMercancia != null){
            Mercancia mercancia = null;
            switch (tipoMercancia) {
                case "Documento":
                    mercancia = new Documento();
                    break;
                case "Fragil":
                    mercancia = new Fragil();
                    break;
                case "Normal":
                    mercancia = new Normal();
                    break;
            }
            Envio envio;
            if(tipoEnvio.equals("Internacional")){
                envio = new EnvioInternacional(mercancia);
            }
            else{
                envio = new EnvioNacional(mercancia);
            }
            JOptionPane.showMessageDialog(null, envio.enviar());
        }
        else{
            JOptionPane.showMessageDialog(null, "Debes Especificar Tipo de Envio y de Mercancia", "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }
    }
    @FXML
    void clickAplicar(ActionEvent event){
        CarritoCompras carritoBae = new CarritoComprasNormal();
        if(descuento.isSelected()){
            carritoBae = new Descuento(carritoBae);
        }
        if(puntos.isSelected()){
            carritoBae = new Puntos(carritoBae);
        }
        if(envioGratis.isSelected()){
            carritoBae = new EnvioGratis(carritoBae);
        }
        mostrarCarrito.setText(carritoBae.getDescripcion()+ "\nCosto Total: $"+carritoBae.getTotal());
    }

    

}