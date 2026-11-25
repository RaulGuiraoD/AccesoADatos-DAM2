package com.centroDeportivo;

import java.util.Scanner;

import com.centroDeportivo.Interface.IDao;
import com.centroDeportivo.factory.DaoFactory;
import com.centroDeportivo.models.Actividad;

public class Main {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  
        IDao<Actividad.Integer> actividadDao = DaoFactory.getDao(DaoFactory.DaoType.ACTIVIDAD);

        int opcion;

        do {
            System.out.println("\n===== CENTRO DEPORTIVO =====");
            System.out.println("1. Mostrar actividades");
            System.out.println("2. Realizar reserva");
            System.out.println("3. Consultar reservas de un usuario");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
               case 1 -> actividadDao.readRecord().forEach(System.out::println);
            }
        } while (opcion != 0);

        // TODO: Liberar recursos
    }


}