package com.mycompany.proyectoaula;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario {

    private ConexionBD conexion;

    public Usuario() {
        conexion = new ConexionBD();
        conexion.conectar();
    }

    public ResultSet ejecutarSPUsuario(int operacion, String cedula, String nombre, String apellido, String telefono, String correoElectronico, String usuario, String clave) throws SQLException {
        String sql = "{CALL Usuarios(?, ?, ?, ?, ?, ?, ?, ?)}";
        PreparedStatement ps = conexion.conn.prepareStatement(sql);
        ps.setInt(1, operacion);
        ps.setString(2, cedula);
        ps.setString(3, nombre);
        ps.setString(4, apellido);
        ps.setString(5, telefono);
        ps.setString(6, correoElectronico);
        ps.setString(7, usuario);
        ps.setString(8, clave);

        if (operacion == 1) { 
            return ps.executeQuery();
        } else { 
            ps.executeUpdate();
            return null; 
        }
    }
}
