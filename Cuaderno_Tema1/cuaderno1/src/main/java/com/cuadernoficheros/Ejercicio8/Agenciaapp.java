import java.util.ArrayList;
import java.util.List;

public class AgenciaApp {

    public static void main(String[] args) {

        List<PaqueteTuristico> catalogo = new ArrayList<>();
        catalogo.add(new PaqueteTuristico("Roma", 5, 649.90,
                new String[] { "Vuelo", "Hotel 4*", "Desayuno" }));
        catalogo.add(new PaqueteTuristico("Cancún", 10, 1899.00,
                new String[] { "Vuelo", "Hotel 5*", "Pensión Completa", "Excursión a Chichén Itzá" }));
        catalogo.add(new PaqueteTuristico("Tokio", 8, 2450.50,
                new String[] { "Vuelo", "Hotel 4*", "Pensión Completa" }));

        System.out.println("--- Exportando ---");
        OperacionesPaquetes.exportarJSON(catalogo);

        System.out.println("\n--- Importando ---");
        List<PaqueteTuristico> recuperados = OperacionesPaquetes.importarJSON();

        if (recuperados.isEmpty()) {
            System.out.println("No se ha recuperado ningún paquete.");
            return;
        }

        for (PaqueteTuristico p : recuperados) {
            System.out.println("Destino: " + p.getDestino() + " - Precio: " + String.format("%.2f", p.getPrecio()) + " €");
        }
    }
}