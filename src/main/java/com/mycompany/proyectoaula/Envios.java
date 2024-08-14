/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyectoaula;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Envios extends javax.swing.JFrame {

    private ConexionBD conexion;

    public Envios() {
        initComponents();
        conexion = new ConexionBD(); // Inicializa la conexión a la base de datos
        UtilidadesImagen.escalar(lblLogo, "C:/Users/USER/OneDrive/Escritorio/ProyectoAula/imgs/logo.jpg");
        UtilidadesImagen.escalar(lblVolver, "C:/Users/USER/OneDrive/Escritorio/ProyectoAula/imgs/volver.png");
        tabla();
    }

    //Metodo para evitar mover las columnas y centrar los encabezados
    private void tabla() {
        // Centrar los encabezados de las columnas
        ((DefaultTableCellRenderer) tablaDetalles.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer) tablaPedidos.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);
        // Deshabilitar el movimiento de columnas
        tablaDetalles.getTableHeader().setReorderingAllowed(false);
        tablaPedidos.getTableHeader().setReorderingAllowed(false);

    }

    private void cargarDatosPedidos() {
        // Obtén el estado seleccionado en el JComboBox
        String estadoFiltro = (String) cmbFiltro.getSelectedItem();

        // Construye la consulta SQL basada en el filtro seleccionado
        String query;
        if ("Todos".equals(estadoFiltro)) {
            query = "SELECT f.fac_id AS 'ID Factura', u.usu_cedula AS 'C.I Cliente', u.direccion AS 'Dirección', e.estado AS 'Estado', m.metodo_nombre AS 'Método de Pago' "
                    + "FROM envios e "
                    + "JOIN factura f ON e.fk_fac_id = f.fac_id "
                    + "JOIN usuarios u ON f.fk_usu_cedula = u.usu_cedula "
                    + "JOIN metodos_pago m ON f.fk_metodo_id = m.metodo_id";
        } else {
            query = "SELECT f.fac_id AS 'ID Factura', u.usu_cedula AS 'C.I Cliente', u.direccion AS 'Dirección', e.estado AS 'Estado', m.metodo_nombre AS 'Método de Pago' "
                    + "FROM envios e "
                    + "JOIN factura f ON e.fk_fac_id = f.fac_id "
                    + "JOIN usuarios u ON f.fk_usu_cedula = u.usu_cedula "
                    + "JOIN metodos_pago m ON f.fk_metodo_id = m.metodo_id "
                    + "WHERE e.estado = ?";
        }

        ResultSet rs = null;
        PreparedStatement pst = null;
        Statement st = null;
        try {
            if ("Todos".equals(estadoFiltro)) {
                st = conexion.conn.createStatement();
                rs = st.executeQuery(query);
            } else {
                pst = conexion.conn.prepareStatement(query);
                pst.setString(1, estadoFiltro);
                rs = pst.executeQuery();
            }

            DefaultTableModel model = (DefaultTableModel) tablaPedidos.getModel();
            model.setRowCount(0); // Limpia la tabla antes de cargar nuevos datos

            while (rs.next()) {
                Object[] row = new Object[5]; // Cambié el tamaño del array a 5
                row[0] = rs.getInt("ID Factura");
                row[1] = rs.getString("C.I Cliente");
                row[2] = rs.getString("Dirección");
                row[3] = rs.getString("Estado");
                row[4] = rs.getString("Método de Pago"); // Añadí el método de pago
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cierra el ResultSet, Statement y PreparedStatement en el bloque finally
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (st != null && !st.isClosed()) {
                    st.close();
                }
                if (pst != null && !pst.isClosed()) {
                    pst.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void cargarDatosDetalles(int idFactura) {
        String query = "SELECT p.pro_nombrePro AS 'Producto', t.talla_nombre AS 'Talla', df.cantidad AS 'Cantidad' "
                + "FROM detallefactura df "
                + "JOIN productostallas pt ON df.productostallas_pro_talla_id = pt.pro_talla_id "
                + "JOIN productos p ON pt.fk_pro_id = p.pro_id "
                + "JOIN tallas t ON pt.fk_talla_id = t.talla_id "
                + "WHERE df.fk_fac_id = ?";

        ResultSet rs = null;
        PreparedStatement pst = null;
        try {
            pst = conexion.conn.prepareStatement(query);
            pst.setInt(1, idFactura);
            rs = pst.executeQuery();

            DefaultTableModel model = (DefaultTableModel) tablaDetalles.getModel();
            model.setRowCount(0); // Limpia la tabla antes de cargar nuevos datos

            while (rs.next()) {
                Object[] row = new Object[3];
                row[0] = rs.getString("Producto");
                row[1] = rs.getString("Talla");
                row[2] = rs.getInt("Cantidad");
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cierra el ResultSet y PreparedStatement en el bloque finally
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (pst != null && !pst.isClosed()) {
                    pst.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        jLabel1 = new javax.swing.JLabel();
        lblVolver = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDetalles = new javax.swing.JTable();
        cmbEstado = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        btnCambiarEstado = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        cmbFiltro = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("APROBAR ENVÍOS");

        lblVolver.setText("jLabel3");
        lblVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVolverMouseClicked(evt);
            }
        });

        lblLogo.setText("jLabel3");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 66));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Detalles de Envío", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Black", 0, 24))); // NOI18N

        tablaDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Talla", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDetalles.setEnabled(false);
        jScrollPane1.setViewportView(tablaDetalles);
        if (tablaDetalles.getColumnModel().getColumnCount() > 0) {
            tablaDetalles.getColumnModel().getColumn(0).setResizable(false);
            tablaDetalles.getColumnModel().getColumn(1).setResizable(false);
            tablaDetalles.getColumnModel().getColumn(2).setResizable(false);
        }

        cmbEstado.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enviado", "Pendiente", "Cancelado", "Entregado" }));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel2.setText("Estado de envío");

        btnCambiarEstado.setBackground(new java.awt.Color(0, 0, 0));
        btnCambiarEstado.setFont(new java.awt.Font("Roboto Black", 0, 16)); // NOI18N
        btnCambiarEstado.setForeground(new java.awt.Color(255, 255, 255));
        btnCambiarEstado.setText("Cambiar Estado");
        btnCambiarEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarEstadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCambiarEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCambiarEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(cmbEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, 420));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Listado de Pedidos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Black", 0, 24))); // NOI18N

        jScrollPane3.setBorder(null);

        tablaPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro. Factura", "C.I Cliente", "Dirección", "Estado", "Método de Pago"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPedidosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaPedidos);
        if (tablaPedidos.getColumnModel().getColumnCount() > 0) {
            tablaPedidos.getColumnModel().getColumn(0).setResizable(false);
            tablaPedidos.getColumnModel().getColumn(0).setPreferredWidth(5);
            tablaPedidos.getColumnModel().getColumn(1).setResizable(false);
            tablaPedidos.getColumnModel().getColumn(2).setResizable(false);
            tablaPedidos.getColumnModel().getColumn(3).setResizable(false);
            tablaPedidos.getColumnModel().getColumn(4).setResizable(false);
        }

        jScrollPane3.setViewportView(jScrollPane2);

        jLabel3.setText("Filtrar");

        cmbFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Enviado", "Pendiente", "Cancelado", "Entregado", " " }));
        cmbFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFiltroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(cmbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, 500, 420));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 530));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCambiarEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarEstadoActionPerformed
        // Obtén la fila seleccionada en tablaPedidos
        int filaSeleccionada = tablaPedidos.getSelectedRow();

        if (filaSeleccionada >= 0) {
            // Obtén el ID de la factura de la fila seleccionada
            int idFactura = (Integer) tablaPedidos.getValueAt(filaSeleccionada, 0); // Asegúrate de que el índice sea correcto

            // Obtén el nuevo estado del JComboBox
            String nuevoEstado = (String) cmbEstado.getSelectedItem();

            // Consulta SQL para actualizar el estado
            String query = "UPDATE envios SET estado = ? WHERE fk_fac_id = ?";

            PreparedStatement pst = null;
            try {
                pst = conexion.conn.prepareStatement(query);
                pst.setString(1, nuevoEstado);
                pst.setInt(2, idFactura);
                int filasActualizadas = pst.executeUpdate();

                if (filasActualizadas > 0) {
                    // Actualiza la tabla de pedidos después de la actualización
                    cargarDatosPedidos();
                    javax.swing.JOptionPane.showMessageDialog(this, "Estado actualizado correctamente.");
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "No se pudo actualizar el estado.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Cierra el PreparedStatement
                try {
                    if (pst != null && !pst.isClosed()) {
                        pst.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona un pedido para actualizar el estado.");
        }


    }//GEN-LAST:event_btnCambiarEstadoActionPerformed

    private void tablaPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPedidosMouseClicked
        int filaSeleccionada = tablaPedidos.rowAtPoint(evt.getPoint()); // Corrección aquí: usa 'evt' en lugar de 'e'
        if (filaSeleccionada >= 0) {
            // Obtener el ID de la factura desde la columna correspondiente
            int idFactura = (Integer) tablaPedidos.getValueAt(filaSeleccionada, 0); // Asegúrate de que el índice de columna sea el correcto
            cargarDatosDetalles(idFactura);
        }

    }//GEN-LAST:event_tablaPedidosMouseClicked

    private void lblVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolverMouseClicked
        MenuAdmin admin = new MenuAdmin();
        admin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblVolverMouseClicked

    private void cmbFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFiltroActionPerformed
        cargarDatosPedidos();
    }//GEN-LAST:event_cmbFiltroActionPerformed

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
            java.util.logging.Logger.getLogger(Envios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Envios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Envios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Envios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Envios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiarEstado;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<String> cmbFiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblVolver;
    private javax.swing.JTable tablaDetalles;
    private javax.swing.JTable tablaPedidos;
    // End of variables declaration//GEN-END:variables
}
