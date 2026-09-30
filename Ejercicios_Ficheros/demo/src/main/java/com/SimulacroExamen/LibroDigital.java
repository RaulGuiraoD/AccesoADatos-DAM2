// ============================================================
// LIBRODIGITAL.JAVA
// ============================================================

package com.SimulacroExamen;

public class LibroDigital extends Libro {

    private double tamanioMB;

    public LibroDigital(int id, String titulo, String autor, String categoria, double precio, double tamanioMB) {
        super(id, titulo, autor, categoria, precio);
        this.tamanioMB = tamanioMB;
    }


    public void setTamanioMB(double tamanioMB) {
        this.tamanioMB = tamanioMB;
    }

    public double getTamanioMB() {
        return tamanioMB;
    }


    @Override
    public String toString() {
        return "LibroDigital [tamanioMB=" + tamanioMB + ", id=" + getId() + ", titulo=" + getTitulo()
                + ", autor=" + getAutor() + ", categoria=" + getCategoria() + ", precio=" + getPrecio()
                + "]";
    }
}
