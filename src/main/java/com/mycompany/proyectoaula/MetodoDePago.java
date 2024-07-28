/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoaula;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author USER
 */
public class MetodoDePago {

    public static void cargarMetodosDePago(DefaultComboBoxModel<String> model) {
        ConexionBD conexion = new ConexionBD();
        ResultSet rs = null;

        try {
            CallableStatement stmt = conexion.conn.prepareCall("{call GestionarMetodosPago(?, ?, ?)}");
            stmt.setInt(1, 1); // Opción para SELECT
            stmt.setInt(2, 0); // ID (no se usa en SELECT)
            stmt.setString(3, null); // Nombre (no se usa en SELECT)

            // Ejecutar el procedimiento
            rs = stmt.executeQuery();

            model.removeAllElements();
            
            while (rs.next()) {
                String nombreMetodo = rs.getString("metodo_nombre");
                model.addElement(nombreMetodo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar los recursos
            try {
                if (rs != null) rs.close();                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    

}
