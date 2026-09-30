// ============================================================
// LIBRO.JAVA
// ============================================================

package  com.SimulacroExamen;
import java.io.Serializable;

public class Libro implements Serializable {

    private int id;
    private String titulo;
    private String autor;
    private String categoria;
    private double precio;

    

    public Libro(int id, String titulo, String autor, String categoria, double precio) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Libro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", categoria=" + categoria + ", precio="
                + precio + "]";
    }
}
