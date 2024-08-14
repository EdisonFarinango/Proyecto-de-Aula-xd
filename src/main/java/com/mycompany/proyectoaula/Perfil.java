/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyectoaula;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class Perfil extends javax.swing.JFrame {

    /**
     * Creates new form Perfil
     */
    private ConexionBD conexionBD;

    public Perfil() {
        initComponents();
        conexionBD = new ConexionBD(); // Inicializamos la conexión
        UtilidadesImagen.escalar(lblLogo, "C:/Users/USER/OneDrive/Escritorio/ProyectoAula/imgs/logo.jpg");
        UtilidadesImagen.escalar(lblVolver, "C:/Users/USER/OneDrive/Escritorio/ProyectoAula/imgs/volver.png");
        lblCedula.setText(Sesion.getCedula());
        cargarDatosUsuario();  // Cargar los datos del usuario
        cargarDatosFacturaEnTablaPorCedula(Sesion.getCedula());
    }

    private void cargarDatosFacturaEnTablaPorCedula(String cedulaUsuario) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = conexionBD.conn; // Obtén la conexión desde ConexionBD

            // Preparar la llamada al Stored Procedure
            String sp = "{CALL sp_obtenerFacturasPorCedula(?)}";
            stmt = connection.prepareCall(sp);
            stmt.setString(1, cedulaUsuario); // Establecer el valor de la cédula en la llamada al SP

            // Ejecutar el SP y obtener los resultados
            rs = stmt.executeQuery();

            // Obtener el modelo de la tabla
            DefaultTableModel model = (DefaultTableModel) tablaHistorial.getModel();
            model.setRowCount(0); // Limpiar la tabla antes de insertar nuevos datos

            // Recorrer los resultados y añadirlos a la tabla
            while (rs.next()) {
                int idFactura = rs.getInt("Factura");
                Date fechaFactura = rs.getDate("FechaFactura");
                String estadoEnvio = rs.getString("EstadoEnvio");
                double total = rs.getDouble("Total");

                // Agregar la fila a la tabla
                model.addRow(new Object[]{idFactura, fechaFactura, estadoEnvio, total});
            }

        } catch (SQLException e) {
            System.out.println("Error al cargar datos en la tabla: " + e.getMessage());
        } finally {
            // Cierra los recursos sin cerrar la conexión
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
        }
    }

    private void cargarDatosUsuario() {
        try {
            // Asume que `lblCedula` tiene el valor correcto de la cédula del usuario
            String cedula = lblCedula.getText();

            // Ejecuta la consulta usando el procedimiento almacenado
            ConexionBD conexion = new ConexionBD();
            String sql = "{CALL ManejarUsuario(1, ? , NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)}";

            try (PreparedStatement ps = conexion.conn.prepareStatement(sql)) {
                ps.setString(1, cedula);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        fieldNombre.setText(rs.getString("usu_nombre"));
                        fieldApellido.setText(rs.getString("usu_apellido"));
                        fieldCedula.setText(rs.getString("usu_cedula"));
                        fieldTelefono.setText(rs.getString("usu_telefono"));
                        fieldCorreoElectronico.setText(rs.getString("usu_correoElectronico"));
                        fieldUsuario.setText(rs.getString("usu_usuario"));
                        fieldContraseña.setText(rs.getString("usu_clave")); // Asegúrate de manejar la contraseña de manera segura
                        fieldDirección.setText(rs.getString("direccion"));
                        ComboPreguntasSeguridad.setSelectedItem(rs.getString("pregunta"));
                        fieldRespuestaPreguntas.setText(rs.getString("respuesta"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar los datos del usuario: " + e.getMessage());
        }
    }

    private void actualizarDatosUsuario() {
        try {
            // Obtener los datos del formulario
            String cedula = fieldCedula.getText();
            String nombre = fieldNombre.getText();
            String apellido = fieldApellido.getText();
            String telefono = fieldTelefono.getText();
            String correo = fieldCorreoElectronico.getText();
            String usuario = fieldUsuario.getText();
            String clave = new String(fieldContraseña.getPassword()); // Convertir a String
            String direccion = fieldDirección.getText();
            String preguntaSeguridad = (String) ComboPreguntasSeguridad.getSelectedItem();
            String respuestaSeguridad = fieldRespuestaPreguntas.getText();

            // Ejecutar la consulta usando el procedimiento almacenado
            ConexionBD conexion = new ConexionBD();
            String sql = "{CALL ManejarUsuario(3, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

            try (PreparedStatement ps = conexion.conn.prepareStatement(sql)) {
                ps.setString(1, cedula);
                ps.setString(2, nombre);
                ps.setString(3, apellido);
                ps.setString(4, telefono);
                ps.setString(5, correo);
                ps.setString(6, usuario);
                ps.setString(7, clave);
                ps.setString(8, direccion);
                ps.setString(9, preguntaSeguridad);
                ps.setString(10, respuestaSeguridad);

                int rowsUpdated = ps.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Datos actualizados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No se actualizó ningún dato.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar los datos del usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblVolver = new javax.swing.JLabel();
        lblCedula = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fieldApellido = new javax.swing.JTextField();
        fieldUsuario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        fieldRespuestaPreguntas = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fieldNombre = new javax.swing.JTextField();
        fieldDirección = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        ComboPreguntasSeguridad = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        fieldCorreoElectronico = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        fieldCedula = new javax.swing.JTextField();
        fieldContraseña = new javax.swing.JPasswordField();
        checkMostrarContraseña = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        fieldTelefono = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaHistorial = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(496, 6, 58, 58));

        jLabel1.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PERFIL");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 250, 50));

        lblVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVolverMouseClicked(evt);
            }
        });
        jPanel2.add(lblVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 7, 55, 57));
        jPanel2.add(lblCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 50, 10));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 70));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Detalles del Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Black", 0, 24))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel2.setText("Correo Electrónico:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel3.setText("Apellido:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, -1, -1));

        fieldApellido.setBorder(null);
        jPanel3.add(fieldApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 150, -1));

        fieldUsuario.setBorder(null);
        jPanel3.add(fieldUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 150, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel4.setText("Contraseña:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, -1, -1));

        fieldRespuestaPreguntas.setBorder(null);
        jPanel3.add(fieldRespuestaPreguntas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 310, -1));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel5.setText("Nombre:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel6.setText("Dirección:   ");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        fieldNombre.setBorder(null);
        jPanel3.add(fieldNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 150, -1));

        fieldDirección.setBorder(null);
        jPanel3.add(fieldDirección, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 310, -1));

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel7.setText("Telefono:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        ComboPreguntasSeguridad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "¿Cuál es el nombre de tu primer mascota?", "¿En qué ciudad naciste?", "¿Cuál es el nombre de tu primer escuela?", "¿Cuál es el nombre de tu abuela materna?", "¿Cuál es tu comida favorita?", "¿Qué nombre recibiste en tu primer trabajo?", "¿Cuál es el nombre del primer amigo que tuviste?", "¿En qué calle vivias cuando eras niño?" }));
        jPanel3.add(ComboPreguntasSeguridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 310, -1));

        jLabel8.setText("Respuesta:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        btnActualizar.setBackground(new java.awt.Color(0, 0, 0));
        btnActualizar.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar Datos");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel3.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 170, 40));

        fieldCorreoElectronico.setBorder(null);
        jPanel3.add(fieldCorreoElectronico, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 310, -1));

        jLabel9.setText("Pregunta de Seguridad:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel10.setText("Nro.Cedula");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 51, 51));
        jLabel11.setText("(Cantón, Dirección exacta)*");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, -1));

        fieldCedula.setEditable(false);
        fieldCedula.setBorder(null);
        jPanel3.add(fieldCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 150, -1));

        fieldContraseña.setBorder(null);
        fieldContraseña.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldContraseñaFocusLost(evt);
            }
        });
        fieldContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldContraseñaActionPerformed(evt);
            }
        });
        jPanel3.add(fieldContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 150, -1));

        checkMostrarContraseña.setText("Mostrar Contraseña");
        checkMostrarContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkMostrarContraseñaActionPerformed(evt);
            }
        });
        jPanel3.add(checkMostrarContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, -1, -1));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 310, 10));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 150, 10));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 150, 10));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 150, 10));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 150, 10));
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 150, 10));
        jPanel3.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, 150, 10));
        jPanel3.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 310, 10));
        jPanel3.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 310, 10));

        fieldTelefono.setBorder(null);
        try {
            fieldTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("09########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel3.add(fieldTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 150, -1));

        jLabel13.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel13.setText("Usuario:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, -1, -1));

        jTabbedPane1.addTab("Editar Perfil", jPanel3);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Historial", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Black", 0, 18))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tablaHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Factura", "Fecha Factura", "Estado Envío", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaHistorial);
        if (tablaHistorial.getColumnModel().getColumnCount() > 0) {
            tablaHistorial.getColumnModel().getColumn(0).setResizable(false);
            tablaHistorial.getColumnModel().getColumn(1).setResizable(false);
            tablaHistorial.getColumnModel().getColumn(2).setResizable(false);
            tablaHistorial.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 500, 350));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 540, 400));

        jTabbedPane1.addTab("Historial de Compras", jPanel4);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 560, 460));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 580));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void checkMostrarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkMostrarContraseñaActionPerformed
        if (checkMostrarContraseña.isSelected()) {
            fieldContraseña.setEchoChar((char) 0); // Muestra la contraseña
        } else {
            fieldContraseña.setEchoChar('*'); // Oculta la contraseña
        }
    }//GEN-LAST:event_checkMostrarContraseñaActionPerformed

    private void lblVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolverMouseClicked
        Productos pro = new Productos();
        pro.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_lblVolverMouseClicked

    private void fieldContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldContraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldContraseñaActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizarDatosUsuario();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void fieldContraseñaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldContraseñaFocusLost
        String clave = fieldContraseña.getText();
        if (clave.length() < 8) {
            JOptionPane.showMessageDialog(null, "La clave debe tener al menos 8 caracteres");
            fieldContraseña.requestFocus(); // Devolver el foco al campo clave
        }
    }//GEN-LAST:event_fieldContraseñaFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Perfil().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboPreguntasSeguridad;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JCheckBox checkMostrarContraseña;
    private javax.swing.JTextField fieldApellido;
    private javax.swing.JTextField fieldCedula;
    private javax.swing.JPasswordField fieldContraseña;
    private javax.swing.JTextField fieldCorreoElectronico;
    private javax.swing.JTextField fieldDirección;
    private javax.swing.JTextField fieldNombre;
    private javax.swing.JTextField fieldRespuestaPreguntas;
    private javax.swing.JFormattedTextField fieldTelefono;
    private javax.swing.JTextField fieldUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblVolver;
    private javax.swing.JTable tablaHistorial;
    // End of variables declaration//GEN-END:variables
}
