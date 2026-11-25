package com.centroDeportivo.daos;

import com.centroDeportivo.models.Reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.centroDeportivo.Interface.IDao;
import com.centroDeportivo.utils.ConnectToDB;

public class ReservadaDao implements IDao<Reserva, Integer>  {
    private final Connection connection;

    private static final String RESERVAR_PLAZA = "UPDATE actividad SET plazas_disponibles=plazas_desponibles=1";


    public ReservadaDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean createRecord(Reserva models) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createRecord'");
    }

    @Override
    public Reserva readRecord(Integer idModel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readRecord'");
    }

    @Override
    public boolean updateRecord(Reserva models, Integer idModel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRecord'");
    }

    @Override
    public boolean deleteRecord(Integer idModel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRecord'");
    }

    @Override
    public List<Reserva> readRecords() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readRecords'");
    }

}
