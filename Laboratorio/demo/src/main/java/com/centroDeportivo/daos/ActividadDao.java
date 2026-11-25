package com.centroDeportivo.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.centroDeportivo.Interface.IDao;
import com.centroDeportivo.models.Actividad;
import com.centroDeportivo.utils.ConnectToDB;

public class ActividadDao implements IDao<Actividad, Integer> {
    private final Connection connection;

    private static final String INSERT = "INSERT INTO actividad(nombre, plazas_disponibles) VALUES (?,?)";
    private static final String SELECT = "SELECT id, nombre, plazas_disponibles FROM actividad WHERE id=?";
    private static final String SELECT_ALL = "SELECT id, nombre, plazas_disponibles FROM actividad";
    private static final String UPDATE = "SELECT actividad SET nombre=?, plazas_disponibles WHERE id=?";
    private static final String DELETE = "DELETE FROM actividad WHERE id=?"; 

    public ActividadDao() {
    this.connection = ConnectToDB.getInstance().getConnection();
    }

    public ActividadDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean createRecord(Actividad models) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setString(1, models.getNombre());
            ps.setInt(2, models.getPlazasDisponibles());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al crear: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Actividad readRecord(Integer idModel) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT)) {
            ps.setInt(1, idModel);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Actividad(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("plazas_disponibles"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
        return null; // no existe o hubo error
    }

    @Override
    public boolean updateRecord(Actividad models, Integer idModel) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, models.getNombre());
            ps.setInt(2, models.getPlazasDisponibles());
            ps.setInt(3, idModel);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteRecord(Integer idModel) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setInt(1, idModel);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al borrar: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Actividad> readRecords() {
        List<Actividad> lista = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Actividad(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("plazas_disponibles")));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }
}
