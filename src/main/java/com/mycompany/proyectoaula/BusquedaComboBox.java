/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoaula;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BusquedaComboBox extends JFrame {

    private JComboBox<String> cmbBusqueda;
    private DefaultComboBoxModel<String> modelo;
    private String[] opciones = {"Pantalones", "Camisas", "Zapatos", "Gorras", "Chaquetas"};
    private JTextField txtBusqueda;

    public BusquedaComboBox() {
        modelo = new DefaultComboBoxModel<>();
        cmbBusqueda = new JComboBox<>(modelo);

        // Crear un JTextField para la búsqueda
        txtBusqueda = new JTextField();
        txtBusqueda.setColumns(20);

        // Agregar un listener para buscar cuando se escribe en el JTextField
        txtBusqueda.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buscar(txtBusqueda.getText());
            }
        });

        // Agregar el JTextField y el JComboBox al panel
        JPanel panel = new JPanel();
        panel.add(txtBusqueda);
        panel.add(cmbBusqueda);

        // Agregar el panel al frame
        add(panel, BorderLayout.CENTER);

        // Configurar el frame
        setSize(300, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void buscar(String texto) {
        // Limpiar el modelo
        modelo.removeAllElements();

        // Buscar opciones que coincidan con el texto
        for (String opcion : opciones) {
            if (opcion.toLowerCase().contains(texto.toLowerCase())) {
                modelo.addElement(opcion);
            }
        }
    }

    public static void main(String[] args) {
        new BusquedaComboBox();
    }
}