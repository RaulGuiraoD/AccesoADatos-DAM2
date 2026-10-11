import java.io.Serializable;

public class Comic implements Serializable {

    private static final long serialVersionUID = 1L;

    private String titulo;
    private String editorial;
    private double precio;
    private transient String claveValoracion;

    public Comic(String titulo, String editorial, double precio, String claveValoracion) {
        this.titulo = titulo;
        this.editorial = editorial;
        this.precio = precio;
        this.claveValoracion = claveValoracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public double getPrecio() {
        return precio;
    }

    public String getClaveValoracion() {
        return claveValoracion;
    }

    @Override
    public String toString() {
        return "Comic [titulo=" + titulo
                + ", editorial=" + editorial
                + ", precio=" + String.format("%.2f", precio)
                + ", claveValoracion=" + claveValoracion + "]";
    }
}