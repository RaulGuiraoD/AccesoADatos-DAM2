package sax;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import modelo.Producto;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LectorInventarioSAX {

    private static final String FICHERO = "inventario.xml";

    //  Buscar productos por categoría 
    public void buscarPorCategoria(Scanner sc) {
        if (!existeFichero()) {
            return;
        }

        String categoria;
        do {
            System.out.print("Introduzca la categoría: ");
            categoria = sc.nextLine().trim();
        } while (categoria.isEmpty());

        ManejadorProductos manejador = new ManejadorProductos(ManejadorProductos.POR_CATEGORIA, categoria, 0);
        List<Producto> encontrados = procesar(manejador);

        if (encontrados == null) {
            return; 
        }
        if (encontrados.isEmpty()) {
            System.out.println("No existen productos de esa categoría");
            return;
        }

        System.out.println("Productos encontrados:");
        for (Producto p : encontrados) {
            System.out.println(p.getCodigo() + " - " + p.getNombre() + " - " + p.getMarca()
                    + " - " + String.format(Locale.US, "%.2f", p.getPrecio()) + " €");
        }
    }

    // Mostrar productos con poco stock 
    public void mostrarPocoStock(Scanner sc) {
        if (!existeFichero()) {
            return;
        }

        int limite = -1;
        while (limite < 0) {
            System.out.print("Introduzca el stock máximo: ");
            try {
                limite = Integer.parseInt(sc.nextLine().trim());
                if (limite < 0) {
                    System.out.println("El stock máximo debe ser un número igual o mayor que 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número entero.");
            }
        }

        ManejadorProductos manejador = new ManejadorProductos(ManejadorProductos.POR_STOCK, "", limite);
        List<Producto> encontrados = procesar(manejador);

        if (encontrados == null) {
            return;
        }
        if (encontrados.isEmpty()) {
            System.out.println("No existen productos con ese nivel de stock");
            return;
        }

        System.out.println("Productos con stock insuficiente:");
        for (Producto p : encontrados) {
            System.out.println(p.getCodigo() + " - " + p.getNombre() + " - Stock: " + p.getStock());
        }
    }

    //  MÉTODOS AUXILIARES

    private boolean existeFichero() {
        if (!new File(FICHERO).exists()) {
            System.out.println("El fichero inventario.xml no existe");
            return false;
        }
        return true;
    }

    private List<Producto> procesar(ManejadorProductos manejador) {
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            parser.parse(new File(FICHERO), manejador);
            return manejador.getResultados();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Error al leer el fichero XML: " + e.getMessage());
            return null;
        } catch (NumberFormatException e) {
            System.out.println("El fichero contiene un precio o un stock que no es un número válido");
            return null;
        }
    }

    private static class ManejadorProductos extends DefaultHandler {

        static final int POR_CATEGORIA = 1;
        static final int POR_STOCK = 2;

        private final int modo;
        private final String categoriaBuscada;
        private final int stockMaximo;
        private final List<Producto> resultados = new ArrayList<>();

        private Producto actual;                         
        private final StringBuilder texto = new StringBuilder(); 

        ManejadorProductos(int modo, String categoriaBuscada, int stockMaximo) {
            this.modo = modo;
            this.categoriaBuscada = categoriaBuscada;
            this.stockMaximo = stockMaximo;
        }

        List<Producto> getResultados() {
            return resultados;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            texto.setLength(0); // empezamos a acumular texto desde cero

            if (qName.equals("producto")) {
                actual = new Producto();
                String codigo = attributes.getValue("codigo");
                actual.setCodigo(codigo == null ? "" : codigo);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            texto.append(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (actual == null) {
                return; 
            }

            String valor = texto.toString().trim();

            switch (qName) {
                case "nombre":
                    actual.setNombre(valor);
                    break;
                case "categoria":
                    actual.setCategoria(valor);
                    break;
                case "marca":
                    actual.setMarca(valor);
                    break;
                case "precio":
                    actual.setPrecio(Double.parseDouble(valor));
                    break;
                case "stock":
                    actual.setStock(Integer.parseInt(valor));
                    break;
                case "producto":
                    if (cumpleCondicion(actual)) {
                        resultados.add(actual);
                    }
                    actual = null;
                    break;
            }
        }

        private boolean cumpleCondicion(Producto p) {
            if (modo == POR_CATEGORIA) {
                return p.getCategoria().equalsIgnoreCase(categoriaBuscada);
            }
            return p.getStock() <= stockMaximo;
        }
    }
}