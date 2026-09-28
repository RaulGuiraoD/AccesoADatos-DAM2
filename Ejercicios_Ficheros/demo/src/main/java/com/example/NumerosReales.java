package com.example;
import java.io.*;
import java.util.Random;

public class NumerosReales {
    public static void main(String[] args) throws IOException {
        String ruta = System.getProperty("user.home") + "/Desktop/NumerosReales.txt";
        Random random = new Random();
 
        BufferedWriter bw = new BufferedWriter(new FileWriter(ruta));
        for (int i = 0; i < 10; i++) {
            int numero = random.nextInt(10000); 
            bw.write(numero + "");
            bw.newLine();
        }
        bw.close();
        System.out.println("Fichero creado en: " + ruta);
 
        BufferedReader br = new BufferedReader(new FileReader(ruta));
        int suma = 0;
        int contador = 0;
        String linea = br.readLine();
 
        while (linea != null) {
            suma = suma + Integer.parseInt(linea);
            contador++;
            linea = br.readLine();
        }
        br.close();
 
        double media = (double) suma / contador;
        System.out.println("Media: " + media);
    }
}