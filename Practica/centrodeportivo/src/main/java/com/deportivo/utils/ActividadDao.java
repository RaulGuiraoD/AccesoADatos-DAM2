package com.deportivo.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.deportivo.Interfaces.IDao;
import com.deportivo.models.Actividad;

public class ActividadDao implements IDao<Actividad, Integer>{
    private final Connection connection;

    private static final String INSERT = "INSERT INTO actividad(nombre, plazas_disponibles) VALUES (?,?)"; // añade una fila (2 huecos)
    private static final String SELECT = "SELECT id, nombre, plaza_disponibles FROM actividad WHERE id=?";  // busca una fila por id (1 hueco)
    private static final String SELECT_ALL = "SELECT id, nombre, plazas_disponibles FROM actividad";        // trae todas las filas (sin huecos)
    private static final String UPDATE = "UPDATE actividad SET nombre=?, plazas_disponibles=? WHERE id=?"; // modifica una fila (3 huecos: el id va el último)
    private static final String DELETE = "DELETE FROM actividad WHERE id=?";                                // borra una fila por id (1 hueco)

    // Constructor: se ejecuta al hacer "new ActividadDao()"
    public ActividadDao() {
        // Pide a ConnectToDB la conexión única (Singleton) y la guarda en el atributo
        this.connection = ConnectToDB.getInstance().getConnection();
    }

    // ---------- CREATE (INSERT) ----------
    @Override                                                       // indica que implementa un método de IDao
    public boolean createRecord(Actividad a) {                      // recibe la actividad a guardar; devuelve true/false
        try (PreparedStatement ps = connection.prepareStatement(INSERT)) {  // prepara el INSERT; el try lo cierra solo al acabar
            ps.setString(1, a.getNombre());                         // rellena el 1er "?" con el nombre (texto)
            ps.setInt(2, a.getPlazasDisponibles());                 // rellena el 2º "?" con las plazas (entero)
            return ps.executeUpdate() > 0;                          // ejecuta; devuelve nº de filas insertadas; >0 significa que funcionó
        } catch (SQLException e) {                                  // si la BD da error (por ejemplo, un UNIQUE repetido)...
            System.out.println("Error al crear: " + e.getMessage());// ...muestra el motivo
            return false;                                           // ...y avisa de que no se pudo
        }
    }

    // ---------- READ (un registro por id) ----------
    @Override
    public Actividad readRecord(Integer id) {                       // recibe el id; devuelve la Actividad o null
        try (PreparedStatement ps = connection.prepareStatement(SELECT)) {  // prepara el SELECT con WHERE id=?
            ps.setInt(1, id);                                       // rellena el "?" con el id buscado
            try (ResultSet rs = ps.executeQuery()) {                // ejecuta el SELECT y obtiene la tabla de resultados
                if (rs.next()) {                                    // mueve el "dedo" a la 1ª fila; true si existe (if: esperamos UNA fila)
                    return new Actividad(                           // construye el objeto con los datos de esa fila
                        rs.getInt("id"),                            // lee la columna id (entero)
                        rs.getString("nombre"),                     // lee la columna nombre (texto)
                        rs.getInt("plazas_disponibles"));           // lee la columna plazas_disponibles (entero)
                }
            }
        } catch (SQLException e) {                                  // si hay error de BD...
            System.out.println("Error al leer: " + e.getMessage()); // ...lo muestra
        }
        return null;                                                // si no hay fila o hubo error: devuelve null
    }

    // ---------- UPDATE ----------
    @Override
    public boolean updateRecord(Actividad a, Integer id) {          // a = datos nuevos; id = qué fila modificar
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {  // prepara el UPDATE
            ps.setString(1, a.getNombre());                         // 1er "?": nuevo nombre
            ps.setInt(2, a.getPlazasDisponibles());                 // 2º "?": nuevas plazas
            ps.setInt(3, id);                                       // 3er "?": el id del WHERE (último, igual que en el SQL)
            return ps.executeUpdate() > 0;                          // true si modificó alguna fila; false si el id no existe
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    // ---------- DELETE ----------
    @Override
    public boolean deleteRecord(Integer id) {                       // recibe el id de la fila a borrar
        try (PreparedStatement ps = connection.prepareStatement(DELETE)) {  // prepara el DELETE
            ps.setInt(1, id);                                       // rellena el "?" con el id
            return ps.executeUpdate() > 0;                          // true si borró alguna fila; false si no existía
        } catch (SQLException e) {
            System.out.println("Error al borrar: " + e.getMessage());
            return false;
        }
    }

    // ---------- READ (todos los registros) ----------
    @Override
    public List<Actividad> readRecords() {                          // devuelve una lista con todas las actividades
        List<Actividad> lista = new ArrayList<>();                  // lista vacía donde iremos metiendo cada actividad
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL);  // prepara el SELECT sin WHERE
             ResultSet rs = ps.executeQuery()) {                    // lo ejecuta; ambos se cierran solos al terminar
            while (rs.next()) {                                     // repite mientras queden filas (while: esperamos VARIAS)
                lista.add(new Actividad(                            // por cada fila crea una Actividad y la añade a la lista
                    rs.getInt("id"),                                // id de la fila actual
                    rs.getString("nombre"),                         // nombre de la fila actual
                    rs.getInt("plazas_disponibles")));              // plazas de la fila actual
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;                                               // devuelve la lista (vacía si no había filas o hubo error)
    }
}
