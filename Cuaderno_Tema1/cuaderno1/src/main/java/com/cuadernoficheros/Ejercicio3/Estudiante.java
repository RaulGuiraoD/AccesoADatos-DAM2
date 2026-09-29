package com.cuadernoficheros.Ejercicio3;
import java.util.Locale;

public class Estudiante {
    private int matricula;
    private String nombre;
    private String fechaInscripcion;
    private String curso;
    private double notaMedia;

    public Estudiante (int matricula, String nombre, String fechaInscripcion, String curso, double notaMedia) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.fechaInscripcion = fechaInscripcion;
        this.curso = curso;
        this.notaMedia = notaMedia;
    }

    public Estudiante(String linea) {
        String [] datos = linea.split(";");
        this.matricula = Integer.parseInt(datos[0].trim());
        this.nombre = datos[1].trim();
        this.fechaInscripcion = datos[2].trim();
        this.curso = datos[3].trim();
        this.notaMedia = Double.parseDouble(datos[4].trim());
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getCurso() {
        return curso;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    
    public String getNombre(){
        return nombre;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setNotaMedia(double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public String toString() {
        return "Estudiantes [matricula = " + matricula + ", nombre = " + nombre + ", fechaIncripcion = " + fechaInscripcion + ", curso = " + curso + ", notaMedia = " + String.format("%.2f", notaMedia) + "]";
    }

    public String toStringWithSeparators() {
    return matricula + ";" + nombre + ";" + fechaInscripcion + ";" + curso + ";" + String.format(Locale.US, "%.2f", notaMedia);
    }
}
