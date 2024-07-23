/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoaula;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class PreguntasSeguridad {

    private ConexionBD conexion;

    public PreguntasSeguridad() {
        conexion = new ConexionBD();
        conexion.conectar();
    }

    public ResultSet ejecutarSPPreguntas(int operacion, String pregunta_id, String usu_cedula, String pregunta, String respuesta) throws SQLException {
        String sql = "{CALL PreguntasSeguridad(?, ?, ?, ?, ?)}";
        PreparedStatement ps = conexion.conn.prepareStatement(sql);
        ps.setInt(1, operacion);
        ps.setString(2, pregunta_id);
        ps.setString(3, usu_cedula);
        ps.setString(4, pregunta);
        ps.setString(5, respuesta);

        if (operacion == 1) { // Si es una consulta
            return ps.executeQuery();
        } else { // Si es una inserción, actualización o eliminación
            ps.executeUpdate();
            return null; // No hay resultado para devolver en estas operaciones
        }
    }

    public ResultSet obtenerUsuarioYClave(String cedula, String pregunta, String respuesta) throws SQLException {
        String sql = "{CALL ObtenerUsuarioYClave(?, ?, ?)}";
        PreparedStatement ps = conexion.conn.prepareStatement(sql);
        ps.setString(1, cedula);
        ps.setString(2, pregunta);
        ps.setString(3, respuesta);
        return ps.executeQuery();
    }

}
