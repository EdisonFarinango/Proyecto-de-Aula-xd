package com.mycompany.proyectoaula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class ProductosEditarEliminar extends javax.swing.JFrame {

    private ConexionBD conexionBD;

    public ProductosEditarEliminar() {
        initComponents();
        conexionBD = new ConexionBD(); // Inicializamos la conexión

        UtilidadesImagen.escalar(lblLogo, "C:/Users/USER/OneDrive/Escritorio/ProyectoAula/imgs/logo.jpg");
        UtilidadesImagen.escalar(lblVolver, "C:/Users/USER/OneDrive/Escritorio/ProyectoAula/imgs/volver.png");
        cargarCategorias();

        // Agregar ActionListener al JComboBox de categorías
        cmbCategoria.addActionListener(evt -> cargarProductosPorCategoria());

        // Agregar ActionListener al JComboBox de productos
        comboProducto.addActionListener(evt -> cargarDetallesProducto());
    }

    private void cargarCategorias() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = conexionBD.conn; // Obtén la conexión desde ConexionBD
            stmt = connection.prepareStatement("SELECT cat_nombre FROM categorias");
            rs = stmt.executeQuery();

            while (rs.next()) {
                cmbCategoria.addItem(rs.getString("cat_nombre"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar categorías: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    private void cargarProductosPorCategoria() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String categoriaSeleccionada = (String) cmbCategoria.getSelectedItem();

        if (categoriaSeleccionada != null) {
            try {
                connection = conexionBD.conn; // Obtén la conexión desde ConexionBD
                stmt = connection.prepareStatement("SELECT pro_nombrePro FROM productos WHERE fk_cat_id = (SELECT cat_id FROM categorias WHERE cat_nombre = ?)");
                stmt.setString(1, categoriaSeleccionada);
                rs = stmt.executeQuery();

                // Limpiar el JComboBox de productos antes de cargar nuevos datos
                comboProducto.removeAllItems();

                while (rs.next()) {
                    comboProducto.addItem(rs.getString("pro_nombrePro"));
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al cargar productos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
    }

    private void cargarDetallesProducto() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String productoSeleccionado = (String) comboProducto.getSelectedItem();

        if (productoSeleccionado != null) {
            try {
                connection = conexionBD.conn; // Obtén la conexión desde ConexionBD
                stmt = connection.prepareStatement("SELECT t.talla_nombre AS Talla, pt.stock AS Stock, pt.precio AS Precio "
                        + "FROM productos p "
                        + "JOIN productostallas pt ON p.pro_id = pt.fk_pro_id "
                        + "JOIN tallas t ON pt.fk_talla_id = t.talla_id "
                        + "WHERE p.pro_nombrePro = ?");
                stmt.setString(1, productoSeleccionado);
                rs = stmt.executeQuery();

                // Limpiar la tabla antes de cargar nuevos datos
                DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
                model.setRowCount(0);

                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("Talla"),
                        rs.getDouble("Precio"),
                        rs.getInt("Stock")
                    });
                }

                // Actualizar el JLabel con el nombre del producto seleccionado
                lblNombreProducto.setText(productoSeleccionado);

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al cargar detalles del producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblVolver = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        comboProducto = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox<>();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtStockActual = new javax.swing.JLabel();
        txtTallaActual = new javax.swing.JLabel();
        txtPrecioActual = new javax.swing.JLabel();
        txtPrecioNuevo = new javax.swing.JFormattedTextField();
        jSeparator1 = new javax.swing.JSeparator();
        spnStockNuevo = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        lblNombreProducto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        lblVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVolverMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto Black", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("EDITAR/ELIMINAR PRODUCTOS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(lblVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 66));

        jPanel1.add(comboProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 170, 30));

        jLabel2.setText("Seleccionar Producto:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vista Productos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Black", 0, 24))); // NOI18N

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Talla", "Precio", "Stock"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProductos);
        if (tablaProductos.getColumnModel().getColumnCount() > 0) {
            tablaProductos.getColumnModel().getColumn(0).setResizable(false);
            tablaProductos.getColumnModel().getColumn(1).setResizable(false);
            tablaProductos.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, 500, 260));

        jLabel3.setText("Seleccionar Categoría:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jPanel1.add(cmbCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 170, 30));

        btnEliminar.setBackground(new java.awt.Color(0, 0, 0));
        btnEliminar.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(null);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 170, 40));

        btnActualizar.setBackground(new java.awt.Color(0, 0, 0));
        btnActualizar.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorder(null);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 170, 40));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalles del Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Black", 0, 24))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel4.setText("Stock Nuevo:");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, -1, -1));

        jLabel6.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel6.setText("Precio Nuevo:");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, -1, -1));

        jLabel7.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel7.setText("Nombre del Producto:");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, -1, -1));

        jLabel8.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel8.setText("Precio Actual:");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel9.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel9.setText("Stock Actual:");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        txtStockActual.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jPanel4.add(txtStockActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 110, 40));

        txtTallaActual.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jPanel4.add(txtTallaActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 110, 40));

        txtPrecioActual.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jPanel4.add(txtPrecioActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 110, 40));

        txtPrecioNuevo.setBorder(null);
        try {
            txtPrecioNuevo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel4.add(txtPrecioNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 110, 40));
        jPanel4.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 70, -1));

        spnStockNuevo.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jPanel4.add(spnStockNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 80, 40));

        jLabel10.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel10.setText("Talla del Producto:");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        lblNombreProducto.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jPanel4.add(lblNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 190, 40));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 370, 300));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 540));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolverMouseClicked
        MenuAdmin menu = new MenuAdmin();
        menu.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_lblVolverMouseClicked

    private void tablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMouseClicked
        int filaSeleccionada = tablaProductos.getSelectedRow(); // Obtén el índice de la fila seleccionada

        if (filaSeleccionada >= 0) { // Asegúrate de que se haya seleccionado una fila
            // Obtén los datos de la fila seleccionada
            String talla = (String) tablaProductos.getValueAt(filaSeleccionada, 0);
            double precio = (Double) tablaProductos.getValueAt(filaSeleccionada, 1);
            int stock = (Integer) tablaProductos.getValueAt(filaSeleccionada, 2);

            // Muestra los datos en los campos correspondientes
            txtTallaActual.setText(talla);
            txtPrecioActual.setText(String.valueOf(precio));
            txtStockActual.setText(String.valueOf(stock));
            spnStockNuevo.setValue(stock);
        }
    }//GEN-LAST:event_tablaProductosMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        Connection connection = null;
        PreparedStatement stmt = null;
        String talla = txtTallaActual.getText();
        String precioNuevoStr = txtPrecioNuevo.getText().replace(",", ".");
        double nuevoPrecio = 0;
        int nuevoStock = (Integer) spnStockNuevo.getValue();
        String productoSeleccionado = lblNombreProducto.getText();

        if (productoSeleccionado != null && talla != null && !talla.isEmpty()) {
            try {
                // Validar precio nuevo
                try {
                    nuevoPrecio = Double.parseDouble(precioNuevoStr);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "El precio ingresado no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                connection = conexionBD.conn; // Obtén la conexión desde ConexionBD

                // Llamar al procedimiento almacenado
                stmt = connection.prepareCall("{call ActualizarProducto(?, ?, ?, ?)}");
                stmt.setString(1, productoSeleccionado);
                stmt.setString(2, talla);
                stmt.setDouble(3, nuevoPrecio);
                stmt.setInt(4, nuevoStock);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Producto actualizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // Opcional: Actualiza la tabla después de la actualización
                cargarDetallesProducto();

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al actualizar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                // Cierra los recursos sin cerrar la conexión
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto y una talla para actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        Connection connection = null;
        PreparedStatement stmt = null;
        String talla = txtTallaActual.getText();
        String productoSeleccionado = lblNombreProducto.getText();

        if (productoSeleccionado != null && talla != null && !talla.isEmpty()) {
            try {
                connection = conexionBD.conn; // Obtén la conexión desde ConexionBD

                // Llamar al procedimiento almacenado
                stmt = connection.prepareCall("{call EliminarProducto(?, ?)}");
                stmt.setString(1, productoSeleccionado);
                stmt.setString(2, talla);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Producto eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // Opcional: Actualiza la tabla o vista después de la eliminación
                cargarDetallesProducto();

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al eliminar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                // Cierra los recursos sin cerrar la conexión
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto y una talla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductosEditarEliminar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox<String> cmbCategoria;
    private javax.swing.JComboBox<String> comboProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNombreProducto;
    private javax.swing.JLabel lblVolver;
    private javax.swing.JSpinner spnStockNuevo;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JLabel txtPrecioActual;
    private javax.swing.JFormattedTextField txtPrecioNuevo;
    private javax.swing.JLabel txtStockActual;
    private javax.swing.JLabel txtTallaActual;
    // End of variables declaration//GEN-END:variables
}
