/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyectoaula;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class RegistrarUsuario extends javax.swing.JFrame {

    private Usuario user;
    private PreguntasSeguridad seg;
    VentanaLogin log = new VentanaLogin();

    public RegistrarUsuario() {
        initComponents();
        this.setLocationRelativeTo(this);
        UtilidadesImagen.escalar(lblLogo, "C:/Users/USER/OneDrive/Escritorio/ProyectoAula/imgs/logo.jpg");
        user = new Usuario();
        seg = new PreguntasSeguridad();

    }

    private void ejecutarSP(int operacion) {
        String cedula = fieldCedula.getText().trim();
        String nombre = fieldNombre.getText().trim();
        String apellido = fieldApellido.getText().trim();
        String telefono = fieldTelefono.getText().trim();
        String correoElectronico = fieldCorreo.getText().trim();
        String usuario = fieldUsuario.getText().trim();
        String clave = new String(fieldContrasenia.getPassword()).trim();

        try {
            ResultSet rs = user.ejecutarSPUsuario(operacion, cedula, nombre, apellido, telefono, correoElectronico, usuario, clave);
            if (operacion == 1) {
                System.out.println("select");
            } else {
                switch (operacion) {
                    case 2 ->
                        JOptionPane.showMessageDialog(this, "Cliente añadido correctamente.");
                    case 3 ->
                        JOptionPane.showMessageDialog(this, "Cliente añadido correctamente.");
                    case 4 ->
                        JOptionPane.showMessageDialog(this, "Cliente añadido correctamente.");
                    default -> {
                    }
                }

            }
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la operación: " + ex.getMessage());
        }
    }

    private void ejecutarSPPreguntas(int operacion) {
        String cedula = fieldCedula.getText().trim();
        String pregunta = (String) ComboPreguntasUno.getSelectedItem();
        String respuesta = fieldRespuestaUno.getText().trim();

        try {
            ResultSet rs = seg.ejecutarSPPreguntas(operacion, null, cedula, pregunta, respuesta);
            if (operacion == 1) {
                while (rs.next()) {
                    System.out.println("Pregunta: " + rs.getString("pregunta"));
                    System.out.println("Respuesta: " + rs.getString("respuesta"));
                }
            } else {
                switch (operacion) {
                    case 2:
                        System.out.println("Pregunta insertada correctamente.");
                        break;
                    case 3:
                        System.out.println("Pregunta actualizada correctamente.");
                        break;
                    case 4:
                        System.out.println("Pregunta eliminada correctamente.");
                        break;
                    default:
                        System.out.println("Operación no válida.");
                        break;
                }
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al ejecutar la operación: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblLogin = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        fieldRespuestaUno = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        fieldUsuario = new javax.swing.JTextField();
        fieldContrasenia = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        btnRegistrarse = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        CheckMostrarPass = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        fieldCedula = new javax.swing.JFormattedTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        fieldNombre = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        fieldApellido = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        fieldTelefono = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        fieldCorreo = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        ComboPreguntasUno = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        lblLogin.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        lblLogin.setForeground(new java.awt.Color(255, 255, 255));
        lblLogin.setText("REGISTRO DE USUARIO");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(148, Short.MAX_VALUE)
                .addComponent(lblLogin)
                .addGap(53, 53, 53)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 80));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fieldRespuestaUno.setBorder(null);
        jPanel1.add(fieldRespuestaUno, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 468, 283, 30));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 270, 283, 10));

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        btnCancelar.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCancelar.setText("CANCELAR");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 560, -1, -1));

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel9.setText("Ingrese su usuario:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(418, 155, -1, -1));

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel10.setText("Ingrese su contraseña:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(418, 214, -1, -1));

        fieldUsuario.setBorder(null);
        jPanel1.add(fieldUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(418, 178, 283, 30));

        fieldContrasenia.setBorder(null);
        fieldContrasenia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldContraseniaFocusLost(evt);
            }
        });
        jPanel1.add(fieldContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(418, 237, 283, 30));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, 283, 10));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, 283, 10));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, 283, 10));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        btnRegistrarse.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        btnRegistrarse.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnRegistrarse.setText("REGISTRARSE");
        btnRegistrarse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegistrarseMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRegistrarse, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 560, -1, -1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de Inicio de Sesión", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Black", 0, 24))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CheckMostrarPass.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        CheckMostrarPass.setText("Mostrar contraseña");
        CheckMostrarPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckMostrarPassActionPerformed(evt);
            }
        });
        jPanel6.add(CheckMostrarPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, 370, 190));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Personales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Black", 0, 24))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setText("Ingrese su cedula:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        fieldCedula.setBorder(null);
        try {
            fieldCedula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel3.add(fieldCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 283, 30));
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 280, 10));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setText("Ingrese su nombre:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        fieldNombre.setBorder(null);
        jPanel3.add(fieldNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 283, 30));
        jPanel3.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 280, 10));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setText("Ingrese su apellido:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        fieldApellido.setBorder(null);
        jPanel3.add(fieldApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 283, 30));

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel7.setText("Ingrese su telefono:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        fieldTelefono.setBorder(null);
        try {
            fieldTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("09########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel3.add(fieldTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 283, 30));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel8.setText("Ingrese su correo electronico:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        fieldCorreo.setBorder(null);
        jPanel3.add(fieldCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 283, 30));
        jPanel3.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 280, 10));
        jPanel3.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 280, 10));
        jPanel3.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 280, 10));
        jPanel3.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 280, 10));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 370, 420));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pregunta de Seguridad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Black", 0, 24))); // NOI18N
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel7.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 283, 10));

        jLabel14.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel14.setText("Pregunta ");
        jPanel7.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        ComboPreguntasUno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "¿Cuál es el nombre de tu primer mascota?", "¿En qué ciudad naciste?", "¿Cuál es el nombre de tu primer escuela?", "¿Cuál es el nombre de tu abuela materna?", "¿Cuál es tu comida favorita?", "¿Qué nombre recibiste en tu primer trabajo?", "¿Cuál es el nombre del primer amigo que tuviste?", "¿En qué calle vivias cuando eras niño?", " " }));
        ComboPreguntasUno.setBorder(null);
        ComboPreguntasUno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboPreguntasUnoActionPerformed(evt);
            }
        });
        jPanel7.add(ComboPreguntasUno, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 283, 30));

        jLabel15.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel15.setText("Respuesta:");
        jPanel7.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 330, 370, 210));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboPreguntasUnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboPreguntasUnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboPreguntasUnoActionPerformed

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        log.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelarMouseClicked


    private void CheckMostrarPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckMostrarPassActionPerformed
        if (CheckMostrarPass.isSelected()) {
            fieldContrasenia.setEchoChar((char) 0); // Muestra la contraseña
        } else {
            fieldContrasenia.setEchoChar('*'); // Oculta la contraseña
        }
    }//GEN-LAST:event_CheckMostrarPassActionPerformed

    private void btnRegistrarseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarseMouseClicked
        if (camposEstanLlenos()) {
            ejecutarSP(2);
            ejecutarSPPreguntas(2);
            log.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos antes de continuar.");
        }
    }//GEN-LAST:event_btnRegistrarseMouseClicked

    private void fieldContraseniaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldContraseniaFocusLost
        String clave = fieldContrasenia.getText();
        if (clave.length() < 8) {
            JOptionPane.showMessageDialog(null, "La clave debe tener al menos 8 caracteres");
            fieldContrasenia.requestFocus(); // Devolver el foco al campo clave
        }
    }//GEN-LAST:event_fieldContraseniaFocusLost

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
            java.util.logging.Logger.getLogger(RegistrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarUsuario().setVisible(true);
            }
        });
    }

    private boolean camposEstanLlenos() {
        String clave = new String(fieldContrasenia.getPassword()).trim();
        return !fieldNombre.getText().trim().isEmpty()
                && !fieldApellido.getText().trim().isEmpty()
                && !fieldCedula.getText().trim().isEmpty()
                && !fieldTelefono.getText().trim().isEmpty()
                && !fieldCorreo.getText().trim().isEmpty()
                && !fieldUsuario.getText().trim().isEmpty()
                && !clave.isEmpty();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CheckMostrarPass;
    private javax.swing.JComboBox<String> ComboPreguntasUno;
    private javax.swing.JLabel btnCancelar;
    private javax.swing.JLabel btnRegistrarse;
    private javax.swing.JTextField fieldApellido;
    private javax.swing.JFormattedTextField fieldCedula;
    private javax.swing.JPasswordField fieldContrasenia;
    private javax.swing.JTextField fieldCorreo;
    private javax.swing.JTextField fieldNombre;
    private javax.swing.JTextField fieldRespuestaUno;
    private javax.swing.JFormattedTextField fieldTelefono;
    private javax.swing.JTextField fieldUsuario;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblLogo;
    // End of variables declaration//GEN-END:variables
}
