package modelo;

import java.util.Locale;

public class Producto {

    private String codigo;
    private String nombre;
    private String categoria;
    private String marca;
    private double precio;
    private int stock;

    public Producto() {
        this("", "", "", "", 0, 0);
    }

    public Producto(String codigo, String nombre, String categoria, String marca, double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "-- PRODUCTO --\n"
                + "Código: " + codigo + "\n"
                + "Nombre: " + nombre + "\n"
                + "Categoría: " + categoria + "\n"
                + "Marca: " + marca + "\n"
                + "Precio: " + String.format(Locale.US, "%.2f", precio) + " €\n"
                + "Stock: " + stock;
    }
}