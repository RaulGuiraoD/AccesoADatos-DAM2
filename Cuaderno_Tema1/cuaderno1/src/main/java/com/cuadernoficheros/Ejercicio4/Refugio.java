import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Refugio {

    private List<Mascota> mascotas = new ArrayList<>();
    private List<CuidadorMascota> cuidadores = new ArrayList<>();

    public void agregarMascota(Mascota mascota) {
        mascotas.add(mascota);
    }

    public void agregarCuidador(CuidadorMascota cuidador) {
        cuidadores.add(cuidador);
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    // Comprueba si la mascota ya la tiene algún cuidador
    private boolean estaAsignada(Mascota mascota) {
        for (CuidadorMascota c : cuidadores) {
            if (c.getMascotas().contains(mascota)) {
                return true;
            }
        }
        return false;
    }

    public void asignarMascotasAutomaticamente() {
        for (Mascota m : mascotas) {
            if (estaAsignada(m)) {
                continue;
            }

            boolean asignada = false;
            for (CuidadorMascota c : cuidadores) {
                if (c.tieneEspacio()) {
                    c.asignarMascota(m);
                    asignada = true;
                    break;
                }
            }

            if (!asignada) {
                System.out.println("No hay cuidadores con espacio para " + m.getNombre());
            }
        }
    }

    public void entrenarTodas() {
        for (CuidadorMascota c : cuidadores) {
            for (Mascota m : c.getMascotas()) {
                c.entrenar(m);
            }
        }
    }

    public void mostrarMascotasPorCuidador() {
        StringBuilder sb = new StringBuilder();
        sb.append("== ESTADO DEL REFUGIO ==\n");

        for (CuidadorMascota c : cuidadores) {
            sb.append("Cuidador ").append(c.getId()).append(" - ").append(c.getNombre())
              .append(" (").append(c.getMascotas().size()).append(" mascotas)\n");

            if (c.getMascotas().isEmpty()) {
                sb.append("   (sin mascotas asignadas)\n");
            }
            for (Mascota m : c.getMascotas()) {
                sb.append("   - ").append(m).append("\n");
            }
        }

        System.out.print(sb);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("estado_refugio.txt"))) {
            bw.write(sb.toString());
            System.out.println("Resumen guardado en estado_refugio.txt");
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero: " + e.getMessage());
        }
    }
}