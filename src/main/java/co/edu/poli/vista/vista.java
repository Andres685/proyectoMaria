package co.edu.poli.vista;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import co.edu.poli.controlador.ControladorCliente;
import co.edu.poli.controlador.ControladorProductos;
import co.edu.poli.controlador.ControladorPedido;
import co.edu.poli.modelo.Cliente;
import co.edu.poli.modelo.Pedido;
import co.edu.poli.modelo.Producto;
import co.edu.poli.modelo.ProductoAlimenticio;
import co.edu.poli.modelo.ProductoElectrico;

public class vista {
    private Scanner scanner;
    private ControladorCliente controladorCliente;
    private ControladorProductos controladorProductos;
    private ControladorPedido controladorPedido;

    public vista() throws ClassNotFoundException, SQLException {
        scanner = new Scanner(System.in);
        controladorCliente = new ControladorCliente();
        controladorProductos = new ControladorProductos();
        controladorPedido = new ControladorPedido();
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Gestionar Clientes");
            System.out.println("2. Gestionar Productos Alimenticios");
            System.out.println("3. Gestionar Productos Eléctricos");
            System.out.println("4. Gestionar Pedidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    menuClientes();
                    break;
                case 2:
                    menuProductosAlimenticios();
                    break;
                case 3:
                    menuProductosElectricos();
                    break;
                case 4:
                    menuPedidos();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (opcion != 0);
    }


    private void menuClientes() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE CLIENTES ===");
            System.out.println("1. Ver todos los clientes");
            System.out.println("2. Buscar cliente por ID");
            System.out.println("3. Insertar nuevo cliente");
            System.out.println("4. Actualizar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    List<Cliente> clientes = controladorCliente.obtenerCl();
                    if (clientes.isEmpty()) {
                    	System.out.println("No se Reportan Registros de Clientes Aun");
                    	break;
                    }
                    clientes.forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Ingrese ID del cliente: ");
                    int idBuscar = scanner.nextInt();
                    Cliente cliente = controladorCliente.obtenerCl(idBuscar);
                    System.out.println(cliente);
                    break;
                case 3:
                    System.out.print("Ingrese nombre del cliente: ");
                    String nombre = scanner.nextLine();
                    Cliente nuevoCliente = new Cliente(0, nombre);
                    System.out.println(controladorCliente.insertarCl(nuevoCliente));
                    break;
                case 4:
                    System.out.print("Ingrese ID del cliente a actualizar: ");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Ingrese nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    Cliente clienteActualizado = new Cliente(idActualizar, nuevoNombre);
                    System.out.println(controladorCliente.actualizarCl(clienteActualizado));
                    break;
                case 5:
                    System.out.print("Ingrese ID del cliente a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    System.out.println(controladorCliente.eliminarCl(idEliminar));
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void menuProductosAlimenticios() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE PRODUCTOS ALIMENTICIOS ===");
            System.out.println("1. Ver todos los productos");
            System.out.println("2. Buscar producto por ID");
            System.out.println("3. Insertar nuevo producto");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                	List<Producto> productosA = controladorProductos.obtenerPa();
                	if(productosA.isEmpty()) {
                		System.out.println("No se Reportan Registros de Productos Alimenticios Aun");
                		break;
                	}
                	productosA.forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Ingrese ID del producto: ");
                    int idBuscar = scanner.nextInt();
                    System.out.println(controladorProductos.obtenerPaId(idBuscar));
                    break;
                case 3:
                    System.out.print("Ingrese descripción del producto: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Ingrese aporte calórico: ");
                    double aporte = scanner.nextDouble();
                    System.out.println(controladorProductos.insertarPa(0,descripcion,aporte));
                    break;
                case 4:
                    System.out.print("Ingrese ID del producto a actualizar: ");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese nueva descripción: ");
                    String nuevaDescripcion = scanner.nextLine();
                    System.out.print("Ingrese nuevo aporte calórico: ");
                    double nuevoAporte = scanner.nextDouble();
                    System.out.println(controladorProductos.actualizarPa(idActualizar,nuevaDescripcion,nuevoAporte));
                    break;
                case 5:
                    System.out.print("Ingrese ID del producto a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    System.out.println(controladorProductos.eliminarPa(idEliminar));
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void menuProductosElectricos() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE PRODUCTOS ELÉCTRICOS ===");
            System.out.println("1. Ver todos los productos");
            System.out.println("2. Buscar producto por ID");
            System.out.println("3. Insertar nuevo producto");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                	List<Producto> productosE = controladorProductos.obtenerPe();
                	if(productosE.isEmpty()) {
                		System.out.println("No se Reportan Registros de Productos Electricos Aun");
                		break;
                	}
                	productosE.forEach(System.out::println);
                    controladorProductos.obtenerPe().forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Ingrese ID del producto: ");
                    int idBuscar = scanner.nextInt();
                    System.out.println(controladorProductos.obtenerPeId(idBuscar));
                    break;
                case 3:
                    System.out.print("Ingrese descripción del producto: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Ingrese voltaje de entrada: ");
                    double voltaje = scanner.nextDouble();
                    System.out.println(controladorProductos.insertarPe(0,descripcion,voltaje));
                    break;
                case 4:
                    System.out.print("Ingrese ID del producto a actualizar: ");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese nueva descripción: ");
                    String nuevaDescripcion = scanner.nextLine();
                    System.out.print("Ingrese nuevo voltaje de entrada: ");
                    double nuevoVoltaje = scanner.nextDouble();
                    System.out.println(controladorProductos.actualizarPe(idActualizar,nuevaDescripcion,nuevoVoltaje));
                    break;
                case 5:
                    System.out.print("Ingrese ID del producto a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    System.out.println(controladorProductos.eliminarPe(idEliminar));
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

     private void menuPedidos() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE PEDIDOS ===");
            System.out.println("1. Ver todos los pedidos");
            System.out.println("2. Buscar pedido por ID");
            System.out.println("3. Insertar nuevo pedido");
            System.out.println("4. Actualizar pedido");
            System.out.println("5. Eliminar pedido");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    List<Pedido> pedidos = controladorPedido.obtenerPed();
                    if (pedidos.isEmpty()) {
                        System.out.println("No se reportan registros de pedidos aún");
                        break;
                    }
                    pedidos.forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Ingrese ID del pedido: ");
                    int idBuscar = scanner.nextInt();
                    System.out.println(controladorPedido.obtenerPedId(idBuscar));
                    break;
                
                case 3:
                    System.out.print("Ingrese ID del cliente ya Registrado: ");
                    int clienteId = scanner.nextInt();
                    Cliente cliente = controladorCliente.obtenerCl(clienteId);
                    System.out.print("Ingrese Los Productos Para el Pedido, separados por ',' ");
                    String productos = scanner.next();
                    List<String> productosLista = Arrays.asList(productos.split(","));
                    Pedido nuevoPedido = new Pedido(cliente, productosLista);
                    System.out.println(controladorPedido.insertarPed(nuevoPedido));
                    break;
                case 4:
                    System.out.print("Ingrese ID del pedido a actualizar: ");
                    int idActualizar = scanner.nextInt();
                    System.out.print("Ingrese nuevo ID del cliente: ");
                    int nuevoClienteId = scanner.nextInt();
                    Cliente clienteact = controladorCliente.obtenerCl(nuevoClienteId);
                    System.out.print("Ingrese nuevo Lista de Productos, Separadas por ',': ");
                    String nuevoProductos = scanner.next();
                    List<String>productosAct = Arrays.asList(nuevoProductos.split(","));
                    scanner.nextLine();
                    Pedido pedidoActualizado = new Pedido(idActualizar,clienteact,productosAct);
                    System.out.println(controladorPedido.actualizarPed(pedidoActualizado));
                    break;
                case 5:
                    System.out.print("Ingrese ID del pedido a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    System.out.println(controladorPedido.eliminarPed(idEliminar));
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    public static void main(String[] args) {
        try {
            new vista().mostrarMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

