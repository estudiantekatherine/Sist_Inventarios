package Inventario;

import java.time.LocalDate;

public class Lista_Producto {

    //Atributos
    private Nodo_Producto primero;

    //Métodos
    //Constructor
    public Lista_Producto() {
        primero = null;
    }

    private boolean estaVacia() {
        return primero == null;
    }

    // Insertar un producto al inicio de la lista
    public void insertarNodoInicio(String nombre, double precio,
                                   String categoria, LocalDate fecha,
                                   int cantidad) {

        Nodo_Producto nuevo = new Nodo_Producto(nombre, precio, categoria, fecha, cantidad);
        nuevo.setSiguiente(primero);
        primero = nuevo;
    }

    // Insertar un producto al final de la lista
    public void insertarNodoFinal(String nombre, double precio,
                                  String categoria, LocalDate fecha,
                                  int cantidad) {

        Nodo_Producto nuevo = new Nodo_Producto(nombre, precio, categoria, fecha, cantidad);

        if (estaVacia()) {
            primero = nuevo;
            return;
        }

        Nodo_Producto temp = primero;
        while (temp.getSiguiente() != null) {
            temp = temp.getSiguiente();
        }

        temp.setSiguiente(nuevo);
    }

    // Buscar un producto recibiendo el atributo nombre
    public Nodo_Producto buscar(String nombre) {

        Nodo_Producto temp = primero;

        while (temp != null &&
                !temp.getNombreProducto().equalsIgnoreCase(nombre)) {

            temp = temp.getSiguiente();
        }

        return temp;
    }

    // Mostrar la lista de productos registrados
    public void mostrarLista() {

        if (estaVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }

        Nodo_Producto temp = primero;

        while (temp != null) {
            System.out.println(temp);
            temp = temp.getSiguiente();
        }
    }

    // Modificar la información de un producto
    public void modificarProductos(String nombre, double nuevoPrecio,
                                   String nuevaCategoria, int nuevaCantidad, LocalDate nuevaFechaVencimiento) {

        //búsqueda del producto por su nombre
        Nodo_Producto producto = buscar(nombre);

        if (producto != null) {
            producto.setPrecioUnitario(nuevoPrecio);
            producto.setCategoriaProducto(nuevaCategoria);
            producto.setCantidadDisponible(nuevaCantidad);
            producto.setFechaVencimiento(nuevaFechaVencimiento);
            System.out.println("El producto fue modificado correctamente");
        } else {
            System.out.println("El producto no fue encontrado, por favor ingrese un nombre válido: ");
        }
    }

    // Generar un reporte de costos de todos los productos registrados
    public void reporteCostos() {

        if (estaVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }

        Nodo_Producto temp = primero;
        double totalGeneral = 0;

        while (temp != null) {

            double costoProducto = temp.calcularCostoTotal();

            System.out.println("Producto: " + temp.getNombreProducto() +
                    " | Costo total: ¢" + costoProducto);

            totalGeneral += costoProducto;
            temp = temp.getSiguiente();
        }

        System.out.println("Costo total acumulado de la lista: ¢" + totalGeneral);
    }

    //Agregar imágenes a un producto
    public void agregarImagenAProducto(String nombre, String rutaImagen) {

        Nodo_Producto producto = buscar(nombre);

        if (producto != null) {
            producto.agregarImagen(rutaImagen);
            System.out.println("La imagen fue agregada correctamente");
        } else {
            System.out.println("El producto no fue encontrado");
        }
    }
}

