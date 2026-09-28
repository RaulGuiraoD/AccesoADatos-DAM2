package com.aleatorio;

import  java.io.File;
import java.io.RandomAccessFile;;

public class Main {
    public static void main(String[] args) {

        File fichero = new File("Fichero Aleatorio");
        RandomAccessFile raf = new RandomAccessFile(fichero, "rw");

        //set de datos

        String[] apellidos = {"palotes", "mentero", "jones"};
        int[] departamentos = {10, 20, 30};
        Double[] salarios = {2500.00, 3200.00, 1400.00};
        StringBuffer buffer = null;
        int tam = apellidos.length;

        for (int i=0; i < tam; i++){
            raf.writeInt(i+i);
            buffer = new StringBuffer(apellidos[i]);

            buffer.setLength(10);
            raf.writeChars(buffer.toString());
            raf.writeInt(departamentos[i]);
            raf.writeDouble(salarios[i]);

        }
        raf.close();

        //LECTURA DE FICHEROS

        int id, departamento, posicion;
        double salario;
        char apellido[] = new char[10], aux;
        RandomAccessFile rafread = new RandomAccessFile (fichero, "r");

        for (;;) {
            rafread.seek(posicion);
            id =rafread.readInt();
            for (int i = 0; i < apellido.length; i++) {
                aux = rafread.readChar();
                apellido[i] = aux;
            }

            departamento = rafread.readInt();
            salario =rafread.readDouble();
        }
        
    }
}