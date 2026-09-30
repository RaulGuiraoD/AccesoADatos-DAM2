// ============================================================
// LIBROFISICO.JAVA
// ============================================================

package com.SimulacroExamen;


public class LibroFisico extends Libro {

    private int numeroPaginas;

    public LibroFisico(int id, String titulo, String autor, String categoria, double precio, int numeroPaginas) {
        super(id, titulo, autor, categoria, precio);
        this.numeroPaginas = numeroPaginas;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public String toString() {
        return "LibroFisico [numeroPaginas=" + numeroPaginas + ", id=" + getId() + ", titulo=" + getTitulo()
                + ", autor=" + getAutor() + ", categoria=" + getCategoria() + ", precio=" + getPrecio()
                + "]";
    }
    
}
