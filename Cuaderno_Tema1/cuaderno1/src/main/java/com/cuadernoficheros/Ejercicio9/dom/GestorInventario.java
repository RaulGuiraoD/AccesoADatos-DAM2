package dom;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import modelo.Producto;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GestorInventarioDOM {

    private static final String FICHERO = "inventario.xml";

    public void anadirProducto(Scanner sc) {
        try {
            Document doc = cargarDocumento();

            String codigo = pedirTexto(sc, "Código: ");
            if (buscarProducto(doc, codigo) != null) {
                System.out.println("Ya existe un producto con ese código");
                return;
            }

            String nombre = pedirTexto(sc, "Nombre: ");
            String categoria = pedirTexto(sc, "Categoría: ");
            String marca = pedirTexto(sc, "Marca: ");
            double precio = pedirPrecio(sc);
            int stock = pedirStock(sc, "Stock: ");

            Element producto = doc.createElement("producto");
            producto.setAttribute("codigo", codigo);
            producto.appendChild(crearElemento(doc, "nombre", nombre));
            producto.appendChild(crearElemento(doc, "categoria", categoria));
            producto.appendChild(crearElemento(doc, "marca", marca));
            producto.appendChild(crearElemento(doc, "precio", String.format(Locale.US, "%.2f", precio)));
            producto.appendChild(crearElemento(doc, "stock", String.valueOf(stock)));
            doc.getDocumentElement().appendChild(producto);

            guardarDocumento(doc);
            System.out.println("Producto añadido correctamente");

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            System.out.println("Error al añadir el producto: " + e.getMessage());
        }
    }

    public void mostrarProductos() {
        if (!new File(FICHERO).exists()) {
            System.out.println("El fichero inventario.xml no existe");
            return;
        }

        try {
            Document doc = cargarDocumento();
            NodeList lista = doc.getElementsByTagName("producto");

            if (lista.getLength() == 0) {
                System.out.println("El inventario está vacío");
                return;
            }

            for (int i = 0; i < lista.getLength(); i++) {
                Element elemento = (Element) lista.item(i);
                try {
                    System.out.println(leerProducto(elemento));
                    System.out.println();
                } catch (NumberFormatException e) {
                    System.out.println("Producto " + elemento.getAttribute("codigo")
                            + " ignorado: el precio o el stock no son números válidos");
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Error al leer el fichero XML: " + e.getMessage());
        }
    }

    public void actualizarStock(Scanner sc) {
        if (!new File(FICHERO).exists()) {
            System.out.println("El fichero inventario.xml no existe");
            return;
        }

        try {
            Document doc = cargarDocumento();

            String codigo = pedirTexto(sc, "Código del producto: ");
            Element producto = buscarProducto(doc, codigo);

            if (producto == null) {
                System.out.println("No existe ningún producto con ese código.");
                return;
            }

            int nuevoStock = pedirStock(sc, "Nuevo stock: ");

            NodeList stocks = producto.getElementsByTagName("stock");
            if (stocks.getLength() == 0) {
                producto.appendChild(crearElemento(doc, "stock", String.valueOf(nuevoStock)));
            } else {
                stocks.item(0).setTextContent(String.valueOf(nuevoStock));
            }

            guardarDocumento(doc);
            System.out.println("Stock actualizado correctamente.");

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            System.out.println("Error al actualizar el stock: " + e.getMessage());
        }
    }

    private DocumentBuilder crearBuilder() throws ParserConfigurationException {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }

    private Document cargarDocumento() throws ParserConfigurationException, SAXException, IOException {
        File fichero = new File(FICHERO);

        if (!fichero.exists()) {
            Document doc = crearBuilder().newDocument();
            doc.appendChild(doc.createElement("inventario")); 
            return doc;
        }

        Document doc = crearBuilder().parse(fichero);
        eliminarEspaciosVacios(doc.getDocumentElement());
        return doc;
    }

    private void guardarDocumento(Document doc) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");

        doc.setXmlStandalone(true); // evita que añada standalone="no" a la cabecera
        transformer.transform(new DOMSource(doc), new StreamResult(new File(FICHERO)));
    }

    private void eliminarEspaciosVacios(Node nodo) {
        NodeList hijos = nodo.getChildNodes();

        for (int i = hijos.getLength() - 1; i >= 0; i--) {
            Node hijo = hijos.item(i);

            if (hijo.getNodeType() == Node.TEXT_NODE && hijo.getTextContent().trim().isEmpty()) {
                nodo.removeChild(hijo);
            } else if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                eliminarEspaciosVacios(hijo);
            }
        }
    }

    private Element buscarProducto(Document doc, String codigo) {
        NodeList lista = doc.getElementsByTagName("producto");

        for (int i = 0; i < lista.getLength(); i++) {
            Element elemento = (Element) lista.item(i);
            if (elemento.getAttribute("codigo").equals(codigo)) {
                return elemento;
            }
        }
        return null;
    }

    private Element crearElemento(Document doc, String etiqueta, String valor) {
        Element elemento = doc.createElement(etiqueta);
        elemento.setTextContent(valor);
        return elemento;
    }

    private String texto(Element elemento, String etiqueta) {
        NodeList hijos = elemento.getElementsByTagName(etiqueta);
        if (hijos.getLength() == 0) {
            return "";
        }
        return hijos.item(0).getTextContent().trim();
    }

    // Convierte un <producto> del XML en un objeto Producto
    private Producto leerProducto(Element elemento) {
        return new Producto(
                elemento.getAttribute("codigo"),
                texto(elemento, "nombre"),
                texto(elemento, "categoria"),
                texto(elemento, "marca"),
                Double.parseDouble(texto(elemento, "precio")),
                Integer.parseInt(texto(elemento, "stock")));
    }

    private String pedirTexto(Scanner sc, String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = sc.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("Este campo no puede estar vacío.");
            }
        } while (texto.isEmpty());
        return texto;
    }

    private double pedirPrecio(Scanner sc) {
        while (true) {
            System.out.print("Precio en euros: ");
            try {
                double precio = Double.parseDouble(sc.nextLine().trim().replace(",", "."));
                if (precio >= 0) {
                    return precio;
                }
            } catch (NumberFormatException e) {

            }
            System.out.println("El precio debe ser un número igual o mayor que 0.");
        }
    }

    private int pedirStock(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int stock = Integer.parseInt(sc.nextLine().trim());
                if (stock >= 0) {
                    return stock;
                }
            } catch (NumberFormatException e) {

            }
            System.out.println("El stock debe ser un número entero igual o mayor que 0.");
        }
    }
}