import java.util.ArrayList;
import java.util.List;

public class CuidadorMascota implements Entrenador {

    private static int contadorId = 1;          
    private static final int MAX_MASCOTAS = 4;

    private int id;
    private String nombre;
    private List<Mascota> mascotas;

    public CuidadorMascota(String nombre) {
        this.id = contadorId++;                 
        this.nombre = nombre;
        this.mascotas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public boolean tieneEspacio() {
        return mascotas.size() < MAX_MASCOTAS;
    }

    public boolean asignarMascota(Mascota mascota) {
        if (!tieneEspacio()) {
            System.out.println("Límite alcanzado: el cuidador no puede recibir más mascotas");
            return false;
        }
        mascotas.add(mascota);
        return true;
    }

    @Override
    public void entrenar(Mascota mascota) {
        System.out.println("El cuidador " + nombre + " entrena a " + mascota.getNombre() + ":");
        mascota.emitirSonido();
        mascota.hacerTruco();
    }
}