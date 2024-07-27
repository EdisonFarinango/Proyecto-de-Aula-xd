/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoaula;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;

public class ProductosCat {

    public static void llenarComboProductos(JComboBox<String> comboBox) {
        ConexionBD conexion = new ConexionBD();
        ResultSet rs = null;

        try {
            // Llamar al procedimiento almacenado
            CallableStatement stmt = conexion.conn.prepareCall("{call ObtenerPantalones()}");
            rs = stmt.executeQuery();

            while (rs.next()) {
                // Suponiendo que 'pro_nombrePro' es una columna en la vista 'VistaPantalones'
                String productoNombre = rs.getString("pro_nombrePro");
                comboBox.addItem(productoNombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
            // Cerrar ResultSet si es necesario
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void llenarComboTallas(JComboBox<String> comboBox, String nombreProducto) {
        ConexionBD conexion = new ConexionBD();
        ResultSet rs = null;

        try {
            CallableStatement stmt = conexion.conn.prepareCall("{call ObtenerTallasPorProducto(?)}");
            stmt.setString(1, nombreProducto);
            rs = stmt.executeQuery();

            comboBox.removeAllItems();

            while (rs.next()) {
                String tallaNombre = rs.getString("talla");
                comboBox.addItem(tallaNombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static int obtenerStockDisponible(String producto, String talla) {
        int stock = 0;
        ConexionBD conexion = new ConexionBD();
        ResultSet rs = null;

        try {
            CallableStatement stmt = conexion.conn.prepareCall("{call ObtenerStockDisponible(?, ?)}");
            stmt.setString(1, producto);
            stmt.setString(2, talla);
            rs = stmt.executeQuery();

            if (rs.next()) {
                stock = rs.getInt("stock");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.desconectar();
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return stock;
    }

    public static double obtenerPrecio(String producto, String talla) {
        double precio = 0.0;
        ConexionBD conexion = new ConexionBD();
        ResultSet rs = null;
        CallableStatement stmt = null;

        try {
            stmt = conexion.conn.prepareCall("{call ObtenerPrecio(?, ?)}");
            stmt.setString(1, producto);
            stmt.setString(2, talla);
            rs = stmt.executeQuery();

            if (rs.next()) {
                precio = rs.getDouble("precio");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conexion.desconectar();
        }

        return precio;
    }
    
    
    
    public static void incrementarStock(String producto, String talla, int cantidad) {
        ConexionBD conexion = new ConexionBD();
        CallableStatement stmt = null;

        try {
            // Preparar llamada al procedimiento almacenado
            stmt = conexion.conn.prepareCall("{call IncrementarStock(?, ?, ?)}");
            stmt.setString(1, producto);
            stmt.setString(2, talla);
            stmt.setInt(3, cantidad);

            // Ejecutar el procedimiento
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conexion.desconectar();
        }
    }

}
