package Inventario;

import Inventario.Lista_Producto;
import Inventario.Nodo_Producto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;


public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;

    static Lista_Producto listaProductos = new Lista_Producto();


    public static void main(String[] args) throws IOException {

        int opcion =0;

        do {
            opcion = mostrarMenu();
            procesarMenu(opcion);
        } while (opcion != 6);
    }

    //Rutina 1: Mostrar el menú
    public static int mostrarMenu() throws IOException{

        System.out.println("\n S&C Sistema de gestión de productos en línea");
        System.out.println("1. Registrar un producto al inicio");
        System.out.println("2. Registrar un producto al final");
        System.out.println("3. Modificar un producto existente");
        System.out.println("4. Mostrar todos los productos");
        System.out.println("5. Generar reporte de costos de los productos registrados");
        System.out.println("6. Salir del programa");
        System.out.print("Seleccione una opción: ");

        int opcion = Integer.parseInt(in.readLine());
        return opcion;
    }

    //Rutina 2: Procesar el menú
    public static void procesarMenu(int opcion) throws IOException {

        switch (opcion) {

            case 1:
                registrarProducto(true);
                break;

            case 2:
                registrarProducto(false);
                break;

            case 3:
                modificarProducto();
                break;

            case 4:
                mostrarProductos();
                break;

            case 5:
                generarReporte();
                break;

            case 6:
                out.println("Hasta luego!");
                break;

            default:
                out.println("Opción inválida. Debe ingresar un número del 1 al 6, por favor digite una opción: ");
        }
    }

    //Rutina 3: Registrar un producto en el inventario al inicio
    public static void registrarProducto(boolean insertarInicio) throws IOException{

        System.out.println("Por favor ingrese el nombre del producto: ");
        String nombreProducto = in.readLine();

        System.out.println("Por favor ingrese el precio del producto: ");
        double precioUnitario = Double.parseDouble(in.readLine());

        System.out.println("Por favor ingrese la categoría del producto: ");
        String categoriaProducto = in.readLine();

        System.out.println("Por favor ingrese la cantidad disponible del producto: ");
        int cantidadDisponible = Integer.parseInt(in.readLine());

        System.out.println("El producto ingresado tiene fecha de vencimiento? S / N: ");
        String fecha = in.readLine();


        LocalDate fechaVencimiento = null;

        if (fecha.equalsIgnoreCase("S")) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            out.print("Ingrese la fecha de vencimiento (DD/MM/YYYY): ");
            fechaVencimiento = LocalDate.parse(in.readLine(), formatter);

        }

        if (insertarInicio) {
            listaProductos.insertarNodoInicio(
                    nombreProducto,
                    precioUnitario,
                    categoriaProducto,
                    fechaVencimiento,
                    cantidadDisponible
            );
        } else {
            listaProductos.insertarNodoFinal(
                    nombreProducto,
                    precioUnitario,
                    categoriaProducto,
                    fechaVencimiento,
                    cantidadDisponible
            );
        }

        out.print("¿Cuántas imágenes desea agregar?: ");
        int cantidadImagenes;

        try {
            cantidadImagenes = Integer.parseInt(in.readLine());
        } catch (NumberFormatException e) {
            out.println("Debe ingresar un número válido.");
            return;
        }

        for (int indiceImagen = 0; indiceImagen < cantidadImagenes; indiceImagen++) {

            boolean imagenValida = false;

            while (!imagenValida) {

                out.print("Ingrese el nombre del archivo de la imagen: ");
                String nombreArchivoImagen = in.readLine().trim();

                // Agrega la extensión de la imagen
                if (!nombreArchivoImagen.contains(".")) {
                    nombreArchivoImagen += ".jpg";
                }

               String rutaImagenProducto = "img_productos/" + nombreArchivoImagen;
                File archivoImagen = new File(rutaImagenProducto);
                
                if (archivoImagen.exists()) {
                    System.out.println("Imagen encontrada");
                } else {
                    System.out.println("Imagen NO encontrada");
                }



                //valida si el archivo existe
                if (archivoImagen.exists()) {

                    listaProductos.agregarImagenAProducto(nombreProducto, rutaImagenProducto);
                    imagenValida = true; // Sale del while

                } else {

                    out.println("Error: El archivo '" + nombreArchivoImagen + "' no existe.");
                    out.println("Intente nuevamente.");
                }
            }
        }

        out.println("El producto fue registrado correctamente");
    }

    // Rutina 4: Modificar un producto
    public static void modificarProducto() throws IOException {

        out.print("Ingrese el nombre del producto: ");
        String nombreProducto = in.readLine();

        out.print("Ingrese el nuevo precio: ");
        double nuevoPrecio = Double.parseDouble(in.readLine());

        out.print("Ingrese la nueva categoría: ");
        String nuevaCategoria = in.readLine();

        out.print("Ingrese la nueva cantidad disponible: ");
        int nuevaCantidad = Integer.parseInt(in.readLine());

        out.print("¿Desea modificar la fecha? (S/N): ");
        String respuestaModificarFecha = in.readLine();

        LocalDate nuevaFecha = null;

        if (respuestaModificarFecha.equalsIgnoreCase("S")) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            out.print("Ingrese la nueva fecha de vencimiento (DD/MM/YYYY): ");
            nuevaFecha = LocalDate.parse(in.readLine(), formatter);
        }

        listaProductos.modificarProductos(
                nombreProducto,
                nuevoPrecio,
                nuevaCategoria,
                nuevaCantidad,
                nuevaFecha
        );

        out.print("¿Desea agregar nuevas imágenes al producto? (S/N): ");
        String respuestaAgregarImagen = in.readLine();

        if (respuestaAgregarImagen.equalsIgnoreCase("S")) {

            out.print("¿Cuántas imágenes desea agregar?: ");
            int cantidadImagenes;

            try {
                cantidadImagenes = Integer.parseInt(in.readLine());
            } catch (NumberFormatException e) {
                out.println("Debe ingresar un número válido.");
                return;
            }

            for (int indiceImagen = 0; indiceImagen < cantidadImagenes; indiceImagen++) {

                boolean imagenValida = false;

                while (!imagenValida) {

                    out.print("Ingrese el nombre del archivo de la imagen: ");
                    String nombreArchivoImagen = in.readLine().trim();

                    if (!nombreArchivoImagen.contains(".")) {
                        nombreArchivoImagen += ".jpg";
                    }

                    String rutaImagenProducto = "img_productos/" + nombreArchivoImagen;
                    File archivoImagen = new File(rutaImagenProducto);

                    if (archivoImagen.exists()) {

                        listaProductos.agregarImagenAProducto(nombreProducto, rutaImagenProducto);
                        out.println("Imagen agregada correctamente.");
                        imagenValida = true;

                    } else {

                        out.println("⚠ Error: El archivo '" + nombreArchivoImagen + "' no existe.");
                        out.println("Intente nuevamente.");
                    }
                }
            }
        }
    }

    //Rutina 5: Mostrar los productos
    public static void mostrarProductos() {
        listaProductos.mostrarLista();
    }

    //Rutina 6: Generar reporte de costos
    public static void generarReporte() {
        listaProductos.reporteCostos();
    }
}
