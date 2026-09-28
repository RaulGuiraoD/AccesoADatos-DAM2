package com.example;

import java.io.Serializable;

public class Persona implements  Serializable{
        String nombre;
        int edad;

        public Persona(String nombre, int edad){
            this.nombre = nombre;
            this.edad = edad;
        }
    }
