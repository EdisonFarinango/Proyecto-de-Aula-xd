/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyectoaula;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class Pantalones extends javax.swing.JFrame {

    private ConexionBD conexion;
    private Carrito carrito;

    public Pantalones() {
        initComponents();
        this.setLocationRelativeTo(this);
        UtilidadesImagen.escalar(lblLogo, "C:/Users/USER/OneDrive/Escritorio/ProyectoAula/imgs/logo.jpg");
        conexion = new ConexionBD();

        ProductosCat.llenarComboProductos(comboProductos);

        // Añadir un listener al JComboBox
        comboProductos.addItemListener(evt -> {
            if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                try {
                    mostrarImagen();
                } catch (IOException ex) {
                    Logger.getLogger(Pantalones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        TableUtils.centerText(tablaPantalones);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comboProductos = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboTallas = new javax.swing.JComboBox<>();
        spinnerCantidad = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        btnMandarCarrito = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnAñadirTabla = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaPantalones = new javax.swing.JTable();
        lblImagen = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("SELECCIONE SU PRODUCTO");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jLabel5)
                .addContainerGap(152, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        comboProductos.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        comboProductos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        comboProductos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboProductosItemStateChanged(evt);
            }
        });
        comboProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProductosActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel1.setText("Seleccione Producto");

        jLabel2.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel2.setText("Eliga la cantidad:");

        jLabel3.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel3.setText("Seleccione la talla:");

        spinnerCantidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerCantidadStateChanged(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        btnMandarCarrito.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        btnMandarCarrito.setForeground(new java.awt.Color(255, 255, 255));
        btnMandarCarrito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnMandarCarrito.setText("MANDAR AL CARRITO");
        btnMandarCarrito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMandarCarritoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMandarCarrito, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMandarCarrito, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        btnAñadirTabla.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        btnAñadirTabla.setForeground(new java.awt.Color(255, 255, 255));
        btnAñadirTabla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAñadirTabla.setText("AÑADIR A LA LISTA");
        btnAñadirTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAñadirTablaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAñadirTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAñadirTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tablaPantalones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro.", "Producto", "Cantidad", "Talla", "Precio Unitario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
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
        tablaPantalones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPantalonesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaPantalones);

        lblImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel4.setText("Lista de productos");

        jLabel6.setForeground(new java.awt.Color(204, 0, 0));
        jLabel6.setText("Nota: Doble clic para eliminar de la lista*");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(spinnerCantidad))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboTallas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel6))
                                    .addComponent(jLabel4))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(comboTallas)
                    .addComponent(spinnerCantidad))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(111, 111, 111))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mostrarImagen() throws IOException {
        String productoSeleccionado = (String) comboProductos.getSelectedItem();
        if (productoSeleccionado != null) {
            // Transformar el nombre del producto para coincidir con el nombre del archivo de imagen
            String imageName = productoSeleccionado.toLowerCase().replace(" ", "_");
            String[] formatos = {".jpg", ".jpeg", ".png"};
            String imagePath = "C:/Users/USER/OneDrive/Escritorio/ProyectoAula/imgs/Pantalones/";
            File imageFile = null;

            // Buscar la imagen con diferentes extensiones
            for (String formato : formatos) {
                File tempFile = new File(imagePath + imageName + formato);
                if (tempFile.exists()) {
                    imageFile = tempFile;
                    break;
                }
            }

            if (imageFile != null && imageFile.exists()) {
                Image image = ImageIO.read(imageFile);
                // Redimensionar la imagen si es necesario
                Image scaledImage = image.getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH);
                lblImagen.setIcon(new ImageIcon(scaledImage));
            } else {
                lblImagen.setIcon(null); // No se encontró la imagen
                JOptionPane.showMessageDialog(this, "Imagen no encontrada para el producto seleccionado.");
            }
        }
    }


    private void btnAñadirTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAñadirTablaMouseClicked
        String productoSeleccionado = (String) comboProductos.getSelectedItem();
        String tallaSeleccionada = (String) comboTallas.getSelectedItem();
        int cantidadIngresada = (int) spinnerCantidad.getValue();

        if (productoSeleccionado != null && tallaSeleccionada != null) {
            if (cantidadIngresada > 0) { // Validar que la cantidad sea mayor que 0
                double precio = ProductosCat.obtenerPrecio(productoSeleccionado, tallaSeleccionada);
                int stockDisponible = ProductosCat.obtenerStockDisponible(productoSeleccionado, tallaSeleccionada);

                javax.swing.table.DefaultTableModel modeloTabla = (javax.swing.table.DefaultTableModel) tablaPantalones.getModel();
                boolean productoYaEnTabla = false;
                int cantidadTotalEnTabla = 0;
                int filaExistente = -1;

                // Buscar si el producto ya está en la tabla
                for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                    String productoEnTabla = (String) modeloTabla.getValueAt(i, 1);
                    String tallaEnTabla = (String) modeloTabla.getValueAt(i, 3);
                    if (productoSeleccionado.equals(productoEnTabla) && tallaSeleccionada.equals(tallaEnTabla)) {
                        cantidadTotalEnTabla += (int) modeloTabla.getValueAt(i, 2);
                        productoYaEnTabla = true;
                        filaExistente = i; // Guardar la fila existente para actualizar
                    }
                }

                // Verificar si la cantidad ingresada supera el stock disponible
                if (productoYaEnTabla) {
                    stockDisponible -= cantidadTotalEnTabla;
                }

                if (cantidadIngresada <= stockDisponible) {
                    if (productoYaEnTabla) {
                        // Actualizar la cantidad en la fila existente
                        int nuevaCantidad = cantidadTotalEnTabla + cantidadIngresada;
                        modeloTabla.setValueAt(nuevaCantidad, filaExistente, 2); // Actualizar cantidad
                    } else {
                        // Insertar una nueva fila al principio
                        modeloTabla.insertRow(0, new Object[]{
                            modeloTabla.getRowCount() + 1, // Nro.
                            productoSeleccionado,
                            cantidadIngresada,
                            tallaSeleccionada,
                            precio
                        });
                    }
                    actualizarNumerosFilas();
                    limpiarCampos();
                    ocultarImagen();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this,
                            "La cantidad ingresada excede el stock disponible. Stock disponible: " + stockDisponible,
                            "Error de stock",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "La cantidad debe ser mayor que 0.",
                        "Error de cantidad",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Por favor, seleccione un producto y una talla.",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAñadirTablaMouseClicked

    // Método para actualizar los números de las filas después de insertar o actualizar
    private void actualizarNumerosFilas() {
        javax.swing.table.DefaultTableModel modeloTabla = (javax.swing.table.DefaultTableModel) tablaPantalones.getModel();
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            modeloTabla.setValueAt(i + 1, i, 0); // Actualizar el número de la fila
        }
    }

    private void ocultarImagen() {
        lblImagen.setIcon(null); // Elimina la imagen del JLabel
    }

    private void comboProductosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboProductosItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            String productoSeleccionado = (String) comboProductos.getSelectedItem();

            if (productoSeleccionado != null) {
                ProductosCat.llenarComboTallas(comboTallas, productoSeleccionado);
            }
        }
    }//GEN-LAST:event_comboProductosItemStateChanged

    private void spinnerCantidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerCantidadStateChanged

        validarStock();
    }//GEN-LAST:event_spinnerCantidadStateChanged

    private void tablaPantalonesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPantalonesMouseClicked
        if (evt.getClickCount() == 2) { // Doble clic
            int row = tablaPantalones.rowAtPoint(evt.getPoint());
            if (row >= 0) {
                javax.swing.table.DefaultTableModel modeloTabla = (javax.swing.table.DefaultTableModel) tablaPantalones.getModel();
                String productoEliminado = (String) modeloTabla.getValueAt(row, 1);
                String tallaEliminada = (String) modeloTabla.getValueAt(row, 3);
                int cantidadEliminada = (int) modeloTabla.getValueAt(row, 2);

                // Incrementar stock
                ProductosCat.incrementarStock(productoEliminado, tallaEliminada, cantidadEliminada);

                modeloTabla.removeRow(row);
            }
        }
    }//GEN-LAST:event_tablaPantalonesMouseClicked

    private void btnMandarCarritoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMandarCarritoMouseClicked
        eliminarFilasVacias();

// Crear una nueva instancia de Carrito
        Carrito carrito = new Carrito();
        carrito.setVisible(true);

        // Obtener el modelo de la tabla Pantalones
        DefaultTableModel modeloPantalones = (DefaultTableModel) tablaPantalones.getModel();

        // Iterar sobre todas las filas de tablaPantalones
        for (int i = 0; i < modeloPantalones.getRowCount(); i++) {
            // Obtener los datos de la fila actual
            String nombreProducto = (String) modeloPantalones.getValueAt(i, 1); // Nombre del producto
            String tallaProducto = (String) modeloPantalones.getValueAt(i, 3);  // Talla

            // Obtener la cantidad, asegurándose de que no sea nulo
            Object cantidadObj = modeloPantalones.getValueAt(i, 2);
            int cantidad = (cantidadObj != null) ? ((Integer) cantidadObj).intValue() : 0;

            // Obtener el precio unitario, asegurándose de que no sea nulo
            Object precioObj = modeloPantalones.getValueAt(i, 4);
            double precioUnitario = (precioObj != null) ? ((Double) precioObj).doubleValue() : 0.0;

            // Llamar al método en la instancia de Carrito
            carrito.agregarProductoAlCarrito(nombreProducto, tallaProducto, cantidad, precioUnitario);
        }
    }//GEN-LAST:event_btnMandarCarritoMouseClicked

    private void comboProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboProductosActionPerformed

    private void eliminarFila(int fila) {
        javax.swing.table.DefaultTableModel modeloTabla = (javax.swing.table.DefaultTableModel) tablaPantalones.getModel();
        if (fila >= 0 && fila < modeloTabla.getRowCount()) {
            modeloTabla.removeRow(fila);
        }
    }

    private void validarStock() {
        String productoSeleccionado = (String) comboProductos.getSelectedItem();
        String tallaSeleccionada = (String) comboTallas.getSelectedItem();
        int cantidadIngresada = (int) spinnerCantidad.getValue();

        if (productoSeleccionado != null && tallaSeleccionada != null) {
            int stockDisponible = ProductosCat.obtenerStockDisponible(productoSeleccionado, tallaSeleccionada);

            if (cantidadIngresada > stockDisponible) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "La cantidad ingresada excede el stock disponible. Stock disponible: " + stockDisponible,
                        "Error de stock",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

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
            java.util.logging.Logger.getLogger(Pantalones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pantalones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pantalones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pantalones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pantalones().setVisible(true);
            }
        });
    }

    private void limpiarCampos() {
        comboProductos.setSelectedIndex(-1);
        comboTallas.setSelectedIndex(-1);
        spinnerCantidad.setValue(0);
    }

    private void eliminarFilasVacias() {
        DefaultTableModel modeloPantalones = (DefaultTableModel) tablaPantalones.getModel();

        // Iterar sobre las filas de la tabla
        for (int i = modeloPantalones.getRowCount() - 1; i >= 0; i--) {
            // Verificar si la fila está vacía
            boolean filaVacia = true;
            for (int j = 0; j < modeloPantalones.getColumnCount(); j++) {
                if (modeloPantalones.getValueAt(i, j) != null && !modeloPantalones.getValueAt(i, j).toString().trim().isEmpty()) {
                    filaVacia = false;
                    break;
                }
            }
            // Eliminar la fila si está vacía
            if (filaVacia) {
                modeloPantalones.removeRow(i);
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAñadirTabla;
    private javax.swing.JLabel btnMandarCarrito;
    private javax.swing.JComboBox<String> comboProductos;
    private javax.swing.JComboBox<String> comboTallas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JSpinner spinnerCantidad;
    private javax.swing.JTable tablaPantalones;
    // End of variables declaration//GEN-END:variables
}
