/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoaula;







import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

public class FacturasPDF {

    // Tamaño personalizado para la factura (en puntos)
    private static final float ANCHO = 420f; // Ancho ajustado
    private static final float ALTO = 600f;  // Altura ajustada

    public static void generarFactura(String rutaCarpeta, String idFactura, String fecha, String ciCliente, String direccion, String metodoPago, String subtotal, String iva, String total, String envio, DefaultTableModel modeloFactura) throws DocumentException, IOException {
        // Crear la carpeta si no existe
        File carpeta = new File(rutaCarpeta);
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        // Crear el archivo PDF
        String archivoPdf = rutaCarpeta + "/Factura_" + idFactura + ".pdf";
        Document document = new Document(new Rectangle(ANCHO, ALTO));
        PdfWriter.getInstance(document, new FileOutputStream(archivoPdf));
        document.open();

        // Fuente y estilo
        Font tituloFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD); // Fuente más pequeña para el título
        Font subtituloFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD); // Fuente más pequeña para subtítulos
        Font contenidoFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL); // Fuente más pequeña para contenido
        Font tablaFont = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL); // Fuente más pequeña para la tabla

        // Encabezado
        Paragraph encabezado = new Paragraph("Factura Electrónica Derick", tituloFont);
        encabezado.setAlignment(Element.ALIGN_CENTER);
        document.add(encabezado);

        // Logo
        try {
            Image logo = Image.getInstance("C:/Users/USER/OneDrive/Escritorio/ProyectoAula/imgs/logos.jpg");
            logo.scaleToFit(60, 60); // Escala el logo para que se ajuste al nuevo tamaño de la página
            logo.setAlignment(Element.ALIGN_CENTER);
            document.add(logo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Información de la factura
        PdfPTable infoTable = new PdfPTable(2);
        infoTable.setWidths(new int[]{1, 2});
        infoTable.setWidthPercentage(100);
        infoTable.addCell(getCelda("Nro. Factura:", subtituloFont));
        infoTable.addCell(getCelda(idFactura, contenidoFont));
        infoTable.addCell(getCelda("Fecha:", subtituloFont));
        infoTable.addCell(getCelda(fecha, contenidoFont));
        infoTable.addCell(getCelda("C.I Cliente:", subtituloFont));
        infoTable.addCell(getCelda(ciCliente, contenidoFont));
        infoTable.addCell(getCelda("Dirección:", subtituloFont));
        infoTable.addCell(getCelda(direccion, contenidoFont));
        infoTable.addCell(getCelda("Método de Pago:", subtituloFont));
        infoTable.addCell(getCelda(metodoPago, contenidoFont));
        infoTable.addCell(getCelda("Envío:", subtituloFont));
        infoTable.addCell(getCelda(envio, contenidoFont));
        document.add(infoTable);

        // Línea de separación
        document.add(Chunk.NEWLINE);
        document.add(getLineaSeparacion());
        document.add(Chunk.NEWLINE);

        // Tabla de productos
        PdfPTable tabla = new PdfPTable(4); // 4 columnas
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(10f);
        tabla.setSpacingAfter(10f);
        tabla.setWidths(new int[]{1, 3, 2, 2});
        
        // Cabecera de la tabla
        tabla.addCell(getCelda("Cantidad", subtituloFont, BaseColor.LIGHT_GRAY));
        tabla.addCell(getCelda("Producto", subtituloFont, BaseColor.LIGHT_GRAY));
        tabla.addCell(getCelda("Precio Unitario", subtituloFont, BaseColor.LIGHT_GRAY));
        tabla.addCell(getCelda("Total", subtituloFont, BaseColor.LIGHT_GRAY));

        // Datos de la tabla
        for (int i = 0; i < modeloFactura.getRowCount(); i++) {
            tabla.addCell(getCelda(modeloFactura.getValueAt(i, 0).toString(), tablaFont));
            tabla.addCell(getCelda(modeloFactura.getValueAt(i, 1).toString(), tablaFont));
            tabla.addCell(getCelda(modeloFactura.getValueAt(i, 2).toString(), tablaFont));
            tabla.addCell(getCelda(modeloFactura.getValueAt(i, 3).toString(), tablaFont));
        }

        document.add(tabla);

        // Línea de separación
        document.add(Chunk.NEWLINE);
        document.add(getLineaSeparacion());
        document.add(Chunk.NEWLINE);

        // Totales
        PdfPTable totalesTable = new PdfPTable(2);
        totalesTable.setWidths(new int[]{1, 2});
        totalesTable.setWidthPercentage(50);
        totalesTable.addCell(getCelda("Subtotal:", subtituloFont));
        totalesTable.addCell(getCelda(subtotal, contenidoFont));
        totalesTable.addCell(getCelda("IVA (15%):", subtituloFont));
        totalesTable.addCell(getCelda(iva, contenidoFont));
        totalesTable.addCell(getCelda("Total:", subtituloFont));
        totalesTable.addCell(getCelda(total, contenidoFont));
        document.add(totalesTable);

        // Línea de separación
        document.add(Chunk.NEWLINE);
        document.add(getLineaSeparacion());
        document.add(Chunk.NEWLINE);

        // Mensaje final
        Paragraph mensajeFinal = new Paragraph("¡Gracias por su compra!", subtituloFont);
        mensajeFinal.setAlignment(Element.ALIGN_CENTER);
        document.add(mensajeFinal);

        document.close();
    }

    private static Paragraph getLineaSeparacion() {
        Paragraph linea = new Paragraph();
        linea.setAlignment(Element.ALIGN_CENTER);
        linea.add(new Phrase("--------------------------------------------------"));
        return linea;
    }

    private static PdfPCell getCelda(String texto, Font fuente) {
        PdfPCell celda = new PdfPCell(new Phrase(texto, fuente));
        celda.setBorder(Rectangle.NO_BORDER);
        return celda;
    }

    private static PdfPCell getCelda(String texto, Font fuente, BaseColor color) {
        PdfPCell celda = new PdfPCell(new Phrase(texto, fuente));
        celda.setBackgroundColor(color);
        celda.setBorder(Rectangle.NO_BORDER);
        return celda;
    }
}
