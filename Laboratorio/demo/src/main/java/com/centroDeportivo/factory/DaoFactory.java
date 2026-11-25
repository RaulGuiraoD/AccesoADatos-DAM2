package com.centroDeportivo.factory;

import com.centroDeportivo.Interface.IDao;
import com.centroDeportivo.daos.ActividadDao;

public class DaoFactory {

    public enum DaoType {
        ACTIVIDAD 
    }

    public static <T, v> IDao<T, v> getDao(DaoType daoType) {
        return switch (daoType){
            case ACTIVIDAD -> (IDao<T,v>) new ActividadDao();
        };
    }
}
