package com.mycompany.proyectoaula;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Carrito extends javax.swing.JFrame {

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

        verificarDireccionUsuario();

    }

    // ------------------------
    //---------------------
    private void verificarDireccionUsuario() {
        String cedulaUsuario = lblCedula.getText(); // Obtener la cédula del usuario desde el JLabel

        // Conectar a la base de datos
        ConexionBD conexion = new ConexionBD();
        String sql = "SELECT direccion FROM usuarios WHERE usu_cedula = ?";
        try {
            PreparedStatement ps = conexion.conn.prepareStatement(sql);
            ps.setString(1, cedulaUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Obtener la dirección completa
                String direccionCompleta = rs.getString("direccion");

                if (direccionCompleta != null && !direccionCompleta.trim().isEmpty()) {
                    // Dividir la cadena en canton y direccion
                    String[] partes = direccionCompleta.split(",", 2);

                    if (partes.length == 2) {
                        // Parte 1: Canton
                        String canton = partes[0].trim();
                        // Parte 2: Direccion
                        String direccion = partes[1].trim();

                        // Establecer valores en los componentes de la interfaz
                        comboCanton.setSelectedItem(canton);
                        fieldDireccion.setText(direccion);

                        // Deshabilitar el botón para añadir dirección
                        btnAñadirDireccion.setEnabled(true);
                    } else {
                        // Si la cadena no se puede dividir correctamente, restaurar valores predeterminados
                        comboCanton.setSelectedIndex(0); // Restaurar al primer valor del JComboBox
                        fieldDireccion.setText(""); // Limpiar el JTextField

                        // Habilitar el botón para añadir dirección
                        btnAñadirDireccion.setEnabled(true);
                    }
                } else {
                    // Si direccionCompleta es null o está vacío, restaurar valores predeterminados
                    comboCanton.setSelectedIndex(0); // Restaurar al primer valor del JComboBox
                    fieldDireccion.setText(""); // Limpiar el JTextField

                    // Habilitar el botón para añadir dirección
                    btnAñadirDireccion.setEnabled(true);
                }
            } else {
                // Si no se encuentra dirección, restaurar valores predeterminados
                comboCanton.setSelectedIndex(0); // Restaurar al primer valor del JComboBox
                fieldDireccion.setText(""); // Limpiar el JTextField

                // Habilitar el botón para añadir dirección
                btnAñadirDireccion.setEnabled(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al verificar la dirección: " + ex.getMessage());
        } finally {
            conexion.desconectar();
        }
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        fieldSubtotal = new javax.swing.JTextField();
        fieldIva = new javax.swing.JTextField();
        fieldTotal = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        fieldEnvio = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        lblPrecioFijo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fieldDireccion = new javax.swing.JTextField();
        comboCanton = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        btnConfirmarMetodo = new javax.swing.JLabel();
        comboMetodo = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btnAñadirDireccion = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        btnAñadirMas = new javax.swing.JLabel();

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
                .addComponent(lblIDPago, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblIDPago)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCedulaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 580, 350));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        btnFinalizarCompra.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btnFinalizarCompra.setForeground(new java.awt.Color(255, 255, 255));
        btnFinalizarCompra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnFinalizarCompra.setText("FINALIZAR COMPRA");
        btnFinalizarCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
            .addComponent(btnFinalizarCompra, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 230, 50));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Totales del Carrito", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Black", 0, 24))); // NOI18N

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

        fieldEnvio.setEditable(false);
        fieldEnvio.setBackground(new java.awt.Color(255, 255, 255));
        fieldEnvio.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        fieldEnvio.setText("Precio Fijo: $");
        fieldEnvio.setBorder(null);

        jLabel9.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("ENVÍO");

        lblPrecioFijo.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        lblPrecioFijo.setText("2.99");

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Envios solo Imbabura*");

        jLabel3.setText("Seleccionar Cantón");

        jLabel4.setText("Ingrese dirección de domicilio");

        comboCanton.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ibarra", "Otavalo", "Cotacachi", "Urcuquí", "Antón de la Guardia", "Angochagua", " " }));

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));

        btnConfirmarMetodo.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        btnConfirmarMetodo.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmarMetodo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnConfirmarMetodo.setText("Confirmar");
        btnConfirmarMetodo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
            .addComponent(btnConfirmarMetodo, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        comboMetodo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("SELECCIONAR MÉTODO DE PAGO:");

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));

        btnAñadirDireccion.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btnAñadirDireccion.setForeground(new java.awt.Color(255, 255, 255));
        btnAñadirDireccion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAñadirDireccion.setText("Guardar Dirección");
        btnAñadirDireccion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAñadirDireccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAñadirDireccionMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAñadirDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAñadirDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(fieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(60, 60, 60)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(fieldEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblPrecioFijo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(fieldDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                    .addComponent(comboCanton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(comboMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(237, 237, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel3)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(43, 43, 43)
                                    .addComponent(fieldSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fieldIva, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(37, 37, 37))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fieldSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(fieldIva, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(fieldEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecioFijo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(fieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboCanton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, 380, 500));

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
            .addComponent(btnAñadirMas, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAñadirMas, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 230, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
        // Obtener el id de la factura (último id insertado)
        if (!metodoConfirmado) {
            // Mostrar mensaje de advertencia
            JOptionPane.showMessageDialog(null, "Primero debes confirmar el método de pago.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            // Lógica para finalizar la compra
            finalizarCompra();
            int idFactura = obtenerUltimoIdFactura();

            // Llamar al método para insertar los detalles de la factura
            insertarDetallesFactura(idFactura);

            // Obtener la cédula del usuario desde el JLabel
            String cedulaUsuario = lblCedula.getText();

            // Llamar al método para insertar el envío
            insertarEnvio(cedulaUsuario);

            JOptionPane.showMessageDialog(null, "Compra Finalizada");
            // Obtener los valores de subtotal, IVA y total
            double subtotal = parseDoubleWithComma(fieldSubtotal.getText());
            double iva = parseDoubleWithComma(fieldIva.getText());
            double total = parseDoubleWithComma(fieldTotal.getText());
            String metodoPagoSeleccionado = (String) comboMetodo.getSelectedItem();
            String direccion = fieldDireccion.getText();
            String cedula = lblCedula.getText();
            String envio = lblPrecioFijo.getText();
            // Crear una instancia de la ventana de factura con los valores obtenidos
            Factura fac = new Factura(idFactura, subtotal, iva, total, metodoPagoSeleccionado, direccion, cedula,envio);

            // Obtener el modelo de la tabla carrito
            DefaultTableModel modeloCarrito = (DefaultTableModel) tablaCarrito.getModel();

            // Define los índices de las columnas que quieres transferir
            int[] indicesColumnas = {2, 0, 3, 4};  // Por ejemplo, Producto, Cantidad, Precio Unitario

            // Define los nuevos nombres de columnas para la tabla factura
            String[] nombresColumnas = {"Cantidad", "Producto", "Precio Unitario", "Subtotal"};

            // Pasar los datos a la factura
            fac.setDatosFactura(modeloCarrito, indicesColumnas, nombresColumnas);
            fac.setVisible(true);
            this.dispose();
            CarritoData.clear();
        }
    }//GEN-LAST:event_btnFinalizarCompraMouseClicked

    private void insertarEnvio(String cedulaUsuario) {
        ConexionBD conexion = new ConexionBD();
        try {
            String sql = "{CALL InsertEnvio(?)}";
            CallableStatement cs = conexion.conn.prepareCall(sql);
            cs.setString(1, cedulaUsuario);
            cs.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al insertar el envío: " + ex.getMessage());
        } finally {
            conexion.desconectar();
        }
    }

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
        if ("Tarjeta de Crédito".equals(metodoSeleccionado)) {
            // Crear una instancia de la ventana de Transferencia
            Credito ventanaTransferencia = new Credito();
            ventanaTransferencia.setVisible(true);
        } else {
            // Aquí puedes manejar otros casos de métodos de pago si es necesario
            // Por ejemplo, mostrar un mensaje al usuario o redirigir a una ventana diferente
            // JOptionPane.showMessageDialog(this, "Método de pago no soportado.");
        }
    }//GEN-LAST:event_btnConfirmarMetodoMouseClicked

    private void btnAñadirDireccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAñadirDireccionMouseClicked
        // Obtener el cantón seleccionado del JComboBox
        String canton = (String) comboCanton.getSelectedItem();

        // Obtener la dirección exacta del JTextField
        String direccionExacta = fieldDireccion.getText();

        // Concatenar ambos valores separados por una coma
        String direccionCompleta = canton + ", " + direccionExacta;

        // Obtener la cédula del usuario desde el JLabel
        String cedulaUsuario = lblCedula.getText();

        // Ejecutar el procedimiento almacenado para actualizar la dirección del usuario
        ConexionBD conexion = new ConexionBD();
        try {
            String sql = "{CALL ActualizarDireccionUsuario(?, ?)}";
            CallableStatement stmt = conexion.conn.prepareCall(sql);

            // Establecer los parámetros del SP
            stmt.setString(1, cedulaUsuario);
            stmt.setString(2, direccionCompleta);

            // Ejecutar el SP
            stmt.executeUpdate();

            // Mensaje de confirmación
            JOptionPane.showMessageDialog(this, "Dirección actualizada exitosamente.");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar la dirección: " + ex.getMessage());
        } finally {
            conexion.desconectar();
        }
    }//GEN-LAST:event_btnAñadirDireccionMouseClicked

    // Método para insertar detalles de la factura en la base de datos
    private void insertarDetallesFactura(int idFactura) {
        // Iterar sobre todas las filas seleccionadas en la tabla Carrito
        for (int i = 0; i < tablaCarrito.getRowCount(); i++) {
            // Obtener el nombre del producto y la talla desde la tabla Carrito
            String nombreProducto = (String) tablaCarrito.getValueAt(i, 0); // columna "Producto"
            String nombreTalla = (String) tablaCarrito.getValueAt(i, 1); // columna "Talla"

            int idProductoTalla = obtenerIdProductoTalla(nombreProducto, nombreTalla);

            double precioUnitario = ((Number) tablaCarrito.getValueAt(i, 3)).doubleValue(); // Corregido
            // Obtener la cantidad del producto desde la tabla Carrito
            int cantidadProducto = (int) tablaCarrito.getValueAt(i, 2); // columna "Cantidad"

            // Insertar datos en la tabla DetalleFactura usando el SP
            ConexionBD conexion = new ConexionBD();
            String sql = "{CALL DetalleFactura(?, NULL, ?, ?, ?, ?)}";
            try {
                CallableStatement cs = conexion.conn.prepareCall(sql);
                cs.setInt(1, 2); // Opción 2 para INSERT
                cs.setInt(2, cantidadProducto);
                cs.setDouble(3, precioUnitario);
                cs.setInt(4, idFactura);
                cs.setInt(5, idProductoTalla);
                cs.executeUpdate();
                System.out.println("Detalle de factura insertado correctamente");
            } catch (SQLException ex) {
                System.out.println("Error al insertar detalle de factura: " + ex.getMessage());
            }
        }
    }

    private int obtenerIdProductoTalla(String nombreProducto, String nombreTalla) {
        ConexionBD conexion = new ConexionBD();
        String sql = "SELECT pt.pro_talla_id "
                + "FROM productostallas pt "
                + "JOIN productos p ON pt.fk_pro_id = p.pro_id "
                + "JOIN tallas t ON pt.fk_talla_id = t.talla_id "
                + "WHERE p.pro_nombrePro = ? AND t.talla_nombre = ?";
        try {
            PreparedStatement ps = conexion.conn.prepareStatement(sql);
            ps.setString(1, nombreProducto);
            ps.setString(2, nombreTalla);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("pro_talla_id");
            } else {
                return -1; // No se encontró la combinación de producto y talla
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener id de producto y talla: " + ex.getMessage());
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

        // Obtener el valor del JLabel con el precio fijo
        double precioFijo = 0.0;
        try {
            precioFijo = Double.parseDouble(lblPrecioFijo.getText().replace("€", "").trim());
        } catch (NumberFormatException e) {
            // Manejar el caso en que el JLabel no tenga un formato numérico válido
            System.out.println("Error al convertir el valor del JLabel: " + e.getMessage());
        }

        // Actualizar los campos de subtotal e IVA
        fieldSubtotal.setText(String.format("%.2f", subtotalTotal));
        fieldIva.setText(String.format("%.2f", ivaTotal));

        // Calcular el total incluyendo el precio fijo
        double total = subtotalTotal + ivaTotal + precioFijo;
        fieldTotal.setText(String.format("%.2f", total));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAñadirDireccion;
    private javax.swing.JLabel btnAñadirMas;
    private javax.swing.JLabel btnConfirmarMetodo;
    private javax.swing.JLabel btnFinalizarCompra;
    private javax.swing.JComboBox<String> comboCanton;
    private javax.swing.JComboBox<String> comboMetodo;
    private javax.swing.JTextField fieldDireccion;
    private javax.swing.JTextField fieldEnvio;
    private javax.swing.JTextField fieldIva;
    private javax.swing.JTextField fieldSubtotal;
    private javax.swing.JTextField fieldTotal;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblCedulaLogin;
    private javax.swing.JLabel lblIDPago;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPrecioFijo;
    private javax.swing.JTable tablaCarrito;
    // End of variables declaration//GEN-END:variables
}
