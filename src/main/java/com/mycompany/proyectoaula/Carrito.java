package com.mycompany.proyectoaula;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Carrito extends javax.swing.JFrame {

    private MetodoDePago metodo;
    private boolean metodoConfirmado = false;
    private ConexionBD conexionBD;

    /**
     * Creates new form Carrito
     */
    public Carrito() {
        conexionBD = new ConexionBD();

        initComponents();
        this.setLocationRelativeTo(this);
        UtilidadesImagen.escalar(lblLogo, "C:/Users/USER/OneDrive/Escritorio/ProyectoAula/imgs/logo.jpg");
        TableUtils.centerText(tablaCarrito);

        DefaultTableModel modelo = (DefaultTableModel) tablaCarrito.getModel();
        // Limpiar las filas existentes (en caso de que haya alguna)
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        actualizarTotales();

        // Cargar los productos desde la clase global
        for (Producto producto : CarritoData.getProductos()) {
            modelo.addRow(new Object[]{producto.getNombre(), producto.getTalla(), producto.getCantidad(), producto.getPrecioUnitario(), producto.getSubtotal()});
        }

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        MetodoDePago.cargarMetodosDePago(model);
        comboMetodo.setModel(model);

        lblCedula.setText(Sesion.getCedula());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblCedulaLogin = new javax.swing.JLabel();
        lblCedula = new javax.swing.JLabel();
        lblIDPago = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCarrito = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnFinalizarCompra = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        fieldSubtotal = new javax.swing.JTextField();
        fieldIva = new javax.swing.JTextField();
        fieldTotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        comboMetodo = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        btnConfirmarMetodo = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnAñadirMas = new javax.swing.JLabel();
        btnDetalleFactura = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CARRITO DE COMPRAS");

        lblCedulaLogin.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblIDPago, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCedulaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCedulaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblIDPago, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 60));

        tablaCarrito.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        tablaCarrito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Talla", "Cantidad", "Precio Unitario", "Subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCarrito.setShowGrid(false);
        jScrollPane1.setViewportView(tablaCarrito);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 580, 410));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        btnFinalizarCompra.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btnFinalizarCompra.setForeground(new java.awt.Color(255, 255, 255));
        btnFinalizarCompra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnFinalizarCompra.setText("FINALIZAR COMPRA");
        btnFinalizarCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFinalizarCompraMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnFinalizarCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnFinalizarCompra, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 470, 200, 40));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("TOTALES DEL CARRITO");

        jLabel5.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("SUBTOTAL");

        jLabel6.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("IVA");

        jLabel7.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("TOTAL");

        fieldSubtotal.setEditable(false);
        fieldSubtotal.setBackground(new java.awt.Color(255, 255, 255));
        fieldSubtotal.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        fieldSubtotal.setBorder(null);

        fieldIva.setEditable(false);
        fieldIva.setBackground(new java.awt.Color(255, 255, 255));
        fieldIva.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        fieldIva.setBorder(null);

        fieldTotal.setEditable(false);
        fieldTotal.setBackground(new java.awt.Color(255, 255, 255));
        fieldTotal.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        fieldTotal.setBorder(null);

        jLabel8.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("SELECCIONAR MÉTODO DE PAGO:");

        comboMetodo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));

        btnConfirmarMetodo.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        btnConfirmarMetodo.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmarMetodo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnConfirmarMetodo.setText("Confirmar");
        btnConfirmarMetodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConfirmarMetodoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnConfirmarMetodo, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnConfirmarMetodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(43, 43, 43)
                                    .addComponent(fieldSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fieldIva, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(37, 37, 37))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(40, 40, 40)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(comboMetodo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fieldSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(fieldIva, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(fieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboMetodo, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, 380, 380));

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        btnAñadirMas.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btnAñadirMas.setForeground(new java.awt.Color(255, 255, 255));
        btnAñadirMas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAñadirMas.setText("AÑADIR MÁS PRODUCTOS");
        btnAñadirMas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAñadirMasMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAñadirMas, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAñadirMas, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 220, 40));

        btnDetalleFactura.setText("INSERTAR DETALLE");
        btnDetalleFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleFacturaActionPerformed(evt);
            }
        });
        jPanel1.add(btnDetalleFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 160, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAñadirMasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAñadirMasMouseClicked
        Productos pants = new Productos();
        pants.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnAñadirMasMouseClicked

    private void btnFinalizarCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizarCompraMouseClicked
        obtenerIDMetodoPago();
        if (!metodoConfirmado) {
            // Mostrar mensaje de advertencia
            JOptionPane.showMessageDialog(null, "Primero debes confirmar el método de pago.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            // Lógica para finalizar la compra
            finalizarCompra();

            JOptionPane.showMessageDialog(null, "Compra Finalizada");
        }

    }//GEN-LAST:event_btnFinalizarCompraMouseClicked

    private void finalizarCompra() {
        // Obtener los valores del contexto
        String cedula = lblCedula.getText();

        // Reemplazar la coma por un punto para los valores de tipo double
        double total = parseDoubleWithComma(fieldTotal.getText());
        double subtotal = parseDoubleWithComma(fieldSubtotal.getText());

        // Calcular el IVA (por ejemplo, el 21% del subtotal)
        double iva = parseDoubleWithComma(fieldIva.getText());

        int metodoPago = Integer.parseInt(lblIDPago.getText());
        java.sql.Date fecha = new java.sql.Date(System.currentTimeMillis());

        // Llamar al procedimiento almacenado
        String sql = "{call GestionarFactura(?, ?, ?, ?, ?, ?, ?, ?)}";

        try (CallableStatement stmt = conexionBD.conn.prepareCall(sql)) {
            // Configurar parámetros para el procedimiento almacenado
            stmt.setInt(1, 2); // Opción 2 para INSERT
            stmt.setNull(2, Types.INTEGER); // p_fac_id puede ser NULL si es autoincremental
            stmt.setString(3, cedula);
            stmt.setDouble(4, total);
            stmt.setDouble(5, iva); // IVA
            stmt.setDate(6, fecha);
            stmt.setInt(7, metodoPago);
            stmt.setDouble(8, subtotal);

            // Ejecutar el procedimiento almacenado
            stmt.execute();
            System.out.println("Compra finalizada y factura almacenada.");

        } catch (SQLException e) {
            System.out.println("Error al almacenar la factura: " + e.getMessage());
        } finally {
            conexionBD.desconectar();
        }
    }

    private double parseDoubleWithComma(String value) {
        if (value == null || value.isEmpty()) {
            return 0.0;
        }
        // Reemplazar la coma por punto
        value = value.replace(',', '.');
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir el número: " + e.getMessage());
            return 0.0;
        }
    }

    private void obtenerIDMetodoPago() {
        String metodoPagoSeleccionado = (String) comboMetodo.getSelectedItem();
        if (metodoPagoSeleccionado != null) {
            ConexionBD conexion = new ConexionBD();
            Statement st = null;
            ResultSet rs = null;

            try {
                st = conexion.conn.createStatement();
                String query = "SELECT metodo_id FROM metodos_pago WHERE metodo_nombre = '" + metodoPagoSeleccionado + "'";
                rs = st.executeQuery(query);

                if (rs.next()) {
                    int idPago = rs.getInt("metodo_id");
                    lblIDPago.setText("" + idPago);
                } else {
                    lblIDPago.setText("ID Pago: No encontrado");
                }
            } catch (SQLException ex) {
                lblIDPago.setText("Error al consultar: " + ex.getMessage());
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (st != null) {
                        st.close();
                    }
                } catch (SQLException ex) {
                    System.out.println("Error al cerrar recursos: " + ex.getMessage());
                }
                conexion.desconectar();
            }
        }
    }

    private void btnConfirmarMetodoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmarMetodoMouseClicked
        // Actualizar el estado de la variable
        metodoConfirmado = true;

        // Habilitar el botón Finalizar Compra
        btnFinalizarCompra.setEnabled(true);
        obtenerIDMetodoPago();
        String metodoSeleccionado = (String) comboMetodo.getSelectedItem();

        // Verificar si el método de pago seleccionado es "Transferencia"
        if ("Transferencia".equals(metodoSeleccionado)) {
            // Crear una instancia de la ventana de Transferencia
            Transferencia ventanaTransferencia = new Transferencia();
            ventanaTransferencia.setVisible(true);

            // Cerrar la ventana actual (Carrito)
        }
        if ("Tarjeta de Credito/Debito".equals(metodoSeleccionado)) {
            // Crear una instancia de la ventana de Transferencia
            Credito ventanaTransferencia = new Credito();
            ventanaTransferencia.setVisible(true);
        } else {
            // Aquí puedes manejar otros casos de métodos de pago si es necesario
            // Por ejemplo, mostrar un mensaje al usuario o redirigir a una ventana diferente
            // JOptionPane.showMessageDialog(this, "Método de pago no soportado.");
        }
    }//GEN-LAST:event_btnConfirmarMetodoMouseClicked

    private void btnDetalleFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleFacturaActionPerformed
        // Obtener el id de la factura (último id insertado)
        int idFactura = obtenerUltimoIdFactura();

        // Iterar sobre todas las filas seleccionadas en la tabla Carrito
        for (int i = 0; i < tablaCarrito.getRowCount(); i++) {
            // Obtener el id del producto desde la tabla Carrito
            String nombreProducto = (String) tablaCarrito.getValueAt(i, 0); // columna "Producto"
            int idProducto = obtenerIdProducto(nombreProducto);

            // Obtener la cantidad del producto desde la tabla Carrito
            int cantidadProducto = (int) tablaCarrito.getValueAt(i, 2); // columna "Cantidad"

            // Insertar datos en la tabla FacturaDetalle
            ConexionBD conexion = new ConexionBD();
            String sql = "INSERT INTO DetalleFactura (fk_fac_id, fk_pro_id, cantidad) VALUES (?,?,?)";
            try {
                PreparedStatement ps = conexion.conn.prepareStatement(sql);
                ps.setInt(1, idFactura);
                ps.setInt(2, idProducto);
                ps.setInt(3, cantidadProducto);
                ps.executeUpdate();
                System.out.println("Detalle de factura insertado correctamente");
            } catch (SQLException ex) {
                System.out.println("Error al insertar detalle de factura: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnDetalleFacturaActionPerformed

    private int obtenerIdProducto(String nombreProducto) {
        ConexionBD conexion = new ConexionBD();
        String sql = "SELECT pro_id FROM productos WHERE pro_nombrePro =?";
        try {
            PreparedStatement ps = conexion.conn.prepareStatement(sql);
            ps.setString(1, nombreProducto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("pro_id");
            } else {
                return -1; // no se encontró el producto
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener id de producto: " + ex.getMessage());
            return -1;
        }
    }

    private int obtenerUltimoIdFactura() {
        ConexionBD conexion = new ConexionBD();
        String sql = "SELECT MAX(fac_id) AS ultimo_id FROM factura";
        try {
            Statement st = conexion.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("ultimo_id");
            } else {
                return -1; // no se encontró la última factura
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener último id de factura: " + ex.getMessage());
            return -1;
        }
    }

    private int obtenerCantidadProducto(int filaSeleccionada) {
        // obtener la cantidad del producto desde la tabla Carrito
        return (int) tablaCarrito.getValueAt(filaSeleccionada, 2); // columna "Cantidad"
    }

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
            java.util.logging.Logger.getLogger(Carrito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Carrito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Carrito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Carrito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Carrito().setVisible(true);
            }
        });
    }

    public void agregarProductoAlCarrito(String nombreProducto, String tallaProducto, int cantidad, double precioUnitario) {
        DefaultTableModel modelo = (DefaultTableModel) tablaCarrito.getModel();
        double subtotal = cantidad * precioUnitario;
        // Agregar la fila al final de la tabla
        modelo.addRow(new Object[]{nombreProducto, tallaProducto, cantidad, precioUnitario, subtotal});

        // Guardar el producto en la clase global
        CarritoData.addProducto(new Producto(nombreProducto, tallaProducto, cantidad, precioUnitario));

        actualizarTotales();
    }

    private void actualizarTotales() {
        DefaultTableModel modelo = (DefaultTableModel) tablaCarrito.getModel();
        double subtotalTotal = 0.0;

        // Calcular el subtotal total sumando todos los valores de la columna "Subtotal"
        for (int i = 0; i < modelo.getRowCount(); i++) {
            double subtotal = (double) modelo.getValueAt(i, 4); // Columna de subtotal
            subtotalTotal += subtotal;
        }

        // Calcular el IVA (15% del subtotal total)
        double ivaTotal = subtotalTotal * 0.15;

        // Actualizar los campos de subtotal e IVA
        fieldSubtotal.setText(String.format("%.2f", subtotalTotal));
        fieldIva.setText(String.format("%.2f", ivaTotal));

        // Calcular el total (opcional, si deseas incluirlo también)
        double total = subtotalTotal + ivaTotal;
        fieldTotal.setText(String.format("%.2f", total));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAñadirMas;
    private javax.swing.JLabel btnConfirmarMetodo;
    private javax.swing.JButton btnDetalleFactura;
    private javax.swing.JLabel btnFinalizarCompra;
    private javax.swing.JComboBox<String> comboMetodo;
    private javax.swing.JTextField fieldIva;
    private javax.swing.JTextField fieldSubtotal;
    private javax.swing.JTextField fieldTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblCedulaLogin;
    private javax.swing.JLabel lblIDPago;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JTable tablaCarrito;
    // End of variables declaration//GEN-END:variables
}
