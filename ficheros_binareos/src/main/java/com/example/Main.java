package com.example;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        String ruta = "datos2.bien";
        int[] t = {0,1,2,3,4,5,6};
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream(ruta));
            for (int n : t) {
            dos.writeInt(n);
            }
            dos.writeInt(42); 
            dos.writeDouble(3.14); 
            dos.writeUTF("Hola, binario!"); 
            dos.close(); 
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    private static void EscribirObjetoBinario (){
        try {
            ObjectOutputStream oos = null;
            oos = new ObjectOutputStream(new FileOutputStream("personas.dat",true));
            Persona p1 = new Persona("Juan",20);
            oos.writeUTF("\n");
            oos.writeObject(p1); 
            oos.close();
            System.out.println("Se ha escrito el objeto correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}