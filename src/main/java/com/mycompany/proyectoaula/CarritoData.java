/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoaula;


import java.util.ArrayList;
import java.util.List;

public class CarritoData {
    private static List<Producto> productos = new ArrayList<>();

    public static List<Producto> getProductos() {
        return productos;
    }

    public static void addProducto(Producto producto) {
        productos.add(producto);
    }

    public static void clear() {
        productos.clear();
    }
}

class Producto {
    private String nombre;
    private String talla;
    private int cantidad;
    private double precioUnitario;

    public Producto(String nombre, String talla, int cantidad, double precioUnitario) {
        this.nombre = nombre;
        this.talla = talla;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTalla() {
        return talla;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getSubtotal() {
        return cantidad * precioUnitario;
    }
}
