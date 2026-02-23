package Inventario;

import java.time.LocalDate;
import java.util.ArrayList;

public class Nodo_Producto {

    // Atributos
    private String nombreProducto;
    private double precioUnitario;
    private String categoriaProducto;
    private LocalDate fechaVencimiento;
    private int cantidadDisponible;

    // ArrayList para las imágenes
    private ArrayList<String> listaImagenes;

    private Nodo_Producto siguiente;

    //Métodos
    // Constructor
    public Nodo_Producto(String nombreProducto,
                         double precioUnitario,
                         String categoriaProducto,
                         LocalDate fechaVencimiento,
                         int cantidadDisponible) {

        this.nombreProducto = nombreProducto;
        this.precioUnitario = precioUnitario;
        this.categoriaProducto = categoriaProducto;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidadDisponible = cantidadDisponible;
        this.listaImagenes = new ArrayList<>();
        this.siguiente = null;
    }

    // Getters
    public String getNombreProducto() {
        return nombreProducto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public String getCategoriaProducto() {
        return categoriaProducto;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public ArrayList<String> getListaImagenes() {
        return listaImagenes;
    }

    public Nodo_Producto getSiguiente() {
        return siguiente;
    }

    // Setters
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setCategoriaProducto(String categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public void setListaImagenes(ArrayList<String> listaImagenes) {
        this.listaImagenes = listaImagenes;
    }

    public void setSiguiente(Nodo_Producto siguiente) {
        this.siguiente = siguiente;
    }

    // Se agregan las imágenes
    public void agregarImagen(String rutaImagen) {
        listaImagenes.add(rutaImagen);
    }

    // Cálculo del costo por producto y el total
    public double calcularCostoTotal() {
        return precioUnitario * cantidadDisponible;
    }

    //to String
    @Override
    public String toString() {
        return "Producto: " + nombreProducto +
                " | Precio: ¢" + precioUnitario +
                " | Cantidad: " + cantidadDisponible +
                " | Categoria: " + categoriaProducto +
                " | Vence: " + fechaVencimiento +
                " | Imágenes: " + listaImagenes;
    }
}