package servicios;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;
import modelos.*;

public class CSVHandler {

    public static void guardarSocios(Socio[] socios, int cantidad, String ruta) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {

            for (int i = 0; i < cantidad; i++) {
                Socio s = socios[i];
                String linea = String.format(Locale.US, "%s,%s,%s,%s,%.2f,%.2f,%s",
                        s.getTipo(), s.getPlan(), s.getId(), s.getNombre(),
                        s.getCuotaBase(), s.calcularCuota(), s.getExtra());
                bw.write(linea);
                bw.newLine();
            }

            System.out.println("Datos guardados en " + ruta);

        } catch (IOException e) {
            System.out.println("Error al guardar el CSV: " + e.getMessage());
        }
    }

    public static Socio[] cargarSocios(String ruta, int max) {
        File fichero = new File(ruta);

        if (!fichero.exists()) {
            System.out.println("El fichero " + ruta + " no existe");
            return null;
        }

        Socio[] temporal = new Socio[max];
        int contador = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;

            while ((linea = br.readLine()) != null && contador < max) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                try {
                    temporal[contador] = crearSocio(linea);
                    contador++;
                } catch (Exception e) {
                    System.out.println("Línea ignorada (formato incorrecto): " + linea);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el CSV: " + e.getMessage());
            return null;
        }
    
        return Arrays.copyOf(temporal, contador);
    }

    // Convierte una línea del CSV en el tipo de socio que corresponda
    private static Socio crearSocio(String linea) {
        String[] d = linea.split(",");

        String tipo = d[0];
        Plan plan = Plan.valueOf(d[1]);
        String id = d[2];
        String nombre = d[3];
        double cuotaBase = Double.parseDouble(d[4]);
        String[] extra = d[6].split(";");

        switch (tipo) {
            case "BASE":
                return new SocioBase(id, nombre, plan, cuotaBase, Integer.parseInt(extra[0]));
            case "TEMPORAL":
                return new SocioTemporal(id, nombre, plan, cuotaBase, LocalDate.parse(extra[0]));
            case "VIP":
                return new SocioVIP(id, nombre, plan, cuotaBase,
                        Integer.parseInt(extra[0]), Boolean.parseBoolean(extra[1]));
            default:
                throw new IllegalArgumentException("Tipo de socio desconocido: " + tipo);
        }
    }
}