/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoaula;



public class ProductoEnCarrito {
    private String nombre;
    private String talla;
    private int cantidad;
    private double precio;

    public ProductoEnCarrito(String nombre, String talla, int cantidad, double precio) {
        this.nombre = nombre;
        this.talla = talla;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getTalla() {
        return talla;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }
}
