package com.aleatorio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class corregido {
    public static void main(String[] args) throws IOException {

        File fichero = new File("Fichero Aleatorio");
        RandomAccessFile raf = new RandomAccessFile(fichero, "rw");

        // set de datos
        String[] apellidos = {"palotes", "mentero", "jones"};
        int[] departamentos = {10, 20, 30};
        Double[] salarios = {2500.00, 3200.00, 1400.00};
        StringBuffer buffer = null;
        int tam = apellidos.length;

        for (int i = 0; i < tam; i++) {
            raf.writeInt(i);                                      // Escribe el ID
            buffer = new StringBuffer(apellidos[i]);              // Crea el apellido

            buffer.setLength(10);                                // El apellido ocupa 10 caracteres
            raf.writeChars(buffer.toString());                   // Escribe el apellido

            raf.writeInt(departamentos[i]);                      // Escribe el departamento
            raf.writeDouble(salarios[i]);                         // Escribe el salario
        }

        raf.close();                                              // Cierra el fichero

        // LECTURA DE FICHEROS

        int id, departamento, posicion = 0;                      // posicion empieza en 0
        double salario;
        char apellido[] = new char[10], aux;

        RandomAccessFile rafread = new RandomAccessFile(fichero, "r");

        int tamRegistro = 36;                                    // 4 + 20 + 4 + 8 = 36 bytes

        for (;;) {
            if (posicion >= rafread.length()) {                  // Comprueba si hemos llegado al final
                break;
            }

            rafread.seek(posicion);                              // Se coloca en la posición del registro

            id = rafread.readInt();                              // Lee el ID

            for (int i = 0; i < apellido.length; i++) {
                aux = rafread.readChar();                        // Lee un carácter
                apellido[i] = aux;                               // Guarda el carácter
            }

            departamento = rafread.readInt();                    // Lee el departamento
            salario = rafread.readDouble();                      // Lee el salario

            System.out.println("ID: " + id +
                    " Apellido: " + new String(apellido).trim() +
                    " Departamento: " + departamento +
                    " Salario: " + salario);                     // Muestra los datos

            posicion = posicion + tamRegistro;                  // Pasa al siguiente registro
        }

        rafread.close();                                         // Cierra el fichero
    }
}
