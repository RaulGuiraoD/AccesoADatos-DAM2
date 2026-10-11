import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class OperacionesPaquetes {

    private static final String FICHERO = "ofertas.json";

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void exportarJSON(List<PaqueteTuristico> paquetes) {
        String json = gson.toJson(paquetes);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHERO))) {
            bw.write(json);
            System.out.println("Se han exportado " + paquetes.size() + " paquetes a " + FICHERO);
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero: " + e.getMessage());
        }
    }

    public static List<PaqueteTuristico> importarJSON() {
        File fichero = new File(FICHERO);

        if (!fichero.exists()) {
            System.out.println("El fichero " + FICHERO + " no existe");
            return new ArrayList<>();
        }

        StringBuilder texto = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                texto.append(linea).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
            return new ArrayList<>();
        }

        Type tipoLista = new TypeToken<List<PaqueteTuristico>>() { }.getType();

        try {
            List<PaqueteTuristico> paquetes = gson.fromJson(texto.toString(), tipoLista);

            if (paquetes == null) { 
                return new ArrayList<>();
            }
            return paquetes;

        } catch (JsonSyntaxException e) {
            System.out.println("El fichero no tiene un JSON válido: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}