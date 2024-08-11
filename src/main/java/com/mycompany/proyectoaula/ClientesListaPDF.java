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
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import com.toedter.calendar.JDateChooser;

public class ClientesListaPDF {

    private JDateChooser fechaMinCli;
    private JDateChooser fechaMaxCli;

    public ClientesListaPDF(JDateChooser fechaMin, JDateChooser fechaMax) {
        this.fechaMinCli = fechaMin;
        this.fechaMaxCli = fechaMax;
    }

    public void generarReporteClientes(JTable tablaListaClientes, JTable tablaTotalClientes) {
        Document document = new Document(PageSize.A4); // Configurar el tamaño de la página
        try {
            // Crear el directorio ReporteClientes si no existe
            File directorio = new File("ReporteClientes");
            if (!directorio.exists()) {
                directorio.mkdir();
            }

            // Obtener la fecha y hora actual para el nombre del archivo
            SimpleDateFormat sdfNombreArchivo = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String fechaHoraArchivo = sdfNombreArchivo.format(new Date());

            // Definir el archivo PDF con fecha y hora en el nombre
            File archivo = new File(directorio, "Lista_Clientes_" + fechaHoraArchivo + ".pdf");
            PdfWriter.getInstance(document, new FileOutputStream(archivo));

            document.open();

            // Añadir imagen al PDF
            try {
                // Reemplaza con la ruta de tu imagen
                Image imagen = Image.getInstance("C:/Users/USER/OneDrive/Escritorio/ProyectoAula/imgs/logos.jpg");
                imagen.setAlignment(Image.ALIGN_CENTER);
                imagen.scaleToFit(350, 100); // Ajusta el tamaño de la imagen
                document.add(imagen);
            } catch (IOException e) {
                e.printStackTrace();
                // Si hay un error cargando la imagen, el PDF aún se generará sin ella
            }

            // Agregar título
            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
            Paragraph titulo = new Paragraph("tienda Online Derick", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            // Agregar subtítulo
            Font fontSubtitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph subtitulo = new Paragraph("Reporte de Clientes", fontSubtitulo);
            subtitulo.setAlignment(Element.ALIGN_CENTER);
            document.add(subtitulo);

            document.add(Chunk.NEWLINE);

            // Agregar subtítulo con el lapso de fechas
            SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy");
            String fechaInicio = sdfFecha.format(fechaMinCli.getDate());
            String fechaFin = sdfFecha.format(fechaMaxCli.getDate());
            Paragraph subtituloFechas = new Paragraph(
                    "Clientes generados entre " + fechaInicio + " y " + fechaFin,
                    new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD)
            );
            subtituloFechas.setAlignment(Element.ALIGN_CENTER);
            document.add(subtituloFechas);

            document.add(Chunk.NEWLINE);

            // Agregar tabla tablaListaClientes
            Paragraph tablaTitulo1 = new Paragraph("Lista de Clientes", fontSubtitulo);
            tablaTitulo1.setAlignment(Element.ALIGN_CENTER);
            document.add(tablaTitulo1);

            document.add(Chunk.NEWLINE);
            PdfPTable pdfTable1 = new PdfPTable(tablaListaClientes.getColumnCount());

            // Establecer el ancho de las columnas
            float[] columnWidths1 = new float[tablaListaClientes.getColumnCount()];
            for (int i = 0; i < columnWidths1.length; i++) {
                columnWidths1[i] = 170f; // Ajusta el ancho de cada columna en puntos
            }
            pdfTable1.setWidths(columnWidths1);

            // Definir la fuente con el tamaño deseado
            Font font = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL); // Tamaño de fuente 8 puntos

            // Agregar encabezados de la tabla
            TableModel model1 = tablaListaClientes.getModel();
            for (int i = 0; i < model1.getColumnCount(); i++) {
                PdfPCell cell = new PdfPCell(new Phrase(model1.getColumnName(i), font));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setPadding(10); // Agregar padding a las celdas de encabezado
                pdfTable1.addCell(cell);
            }

            // Agregar datos de la tabla
            for (int i = 0; i < model1.getRowCount(); i++) {
                for (int j = 0; j < model1.getColumnCount(); j++) {
                    PdfPCell cell = new PdfPCell(new Phrase(model1.getValueAt(i, j) != null ? model1.getValueAt(i, j).toString() : "", font));
                    cell.setPadding(8); // Agregar padding a las celdas de datos
                    pdfTable1.addCell(cell);
                }
            }

            // Configurar los márgenes de la tabla
            pdfTable1.setSpacingBefore(10f); // Espacio antes de la tabla
            pdfTable1.setSpacingAfter(10f);  // Espacio después de la tabla

            document.add(pdfTable1);

            document.add(Chunk.NEWLINE);

            // Agregar tabla tablaTotalClientes
            Paragraph tablaTitulo2 = new Paragraph("Total de Clientes", fontSubtitulo);
            tablaTitulo2.setAlignment(Element.ALIGN_CENTER);
            document.add(tablaTitulo2);

            document.add(Chunk.NEWLINE);
            PdfPTable pdfTable2 = new PdfPTable(tablaTotalClientes.getColumnCount());

            // Establecer el ancho de las columnas
            float[] columnWidths2 = new float[tablaTotalClientes.getColumnCount()];
            for (int i = 0; i < columnWidths2.length; i++) {
                columnWidths2[i] = 170f; // Ajusta el ancho de cada columna en puntos
            }
            pdfTable2.setWidths(columnWidths2);

            // Agregar encabezados de la tabla
            TableModel model2 = tablaTotalClientes.getModel();
            for (int i = 0; i < model2.getColumnCount(); i++) {
                PdfPCell cell = new PdfPCell(new Phrase(model2.getColumnName(i), font));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setPadding(10); // Agregar padding a las celdas de encabezado
                pdfTable2.addCell(cell);
            }

            // Agregar datos de la tabla
            for (int i = 0; i < model2.getRowCount(); i++) {
                for (int j = 0; j < model2.getColumnCount(); j++) {
                    PdfPCell cell = new PdfPCell(new Phrase(model2.getValueAt(i, j) != null ? model2.getValueAt(i, j).toString() : "", font));
                    cell.setPadding(8); // Agregar padding a las celdas de datos
                    pdfTable2.addCell(cell);
                }
            }

            // Configurar los márgenes de la tabla
            pdfTable2.setSpacingBefore(10f); // Espacio antes de la tabla
            pdfTable2.setSpacingAfter(10f);  // Espacio después de la tabla

            document.add(pdfTable2);

            // Agregar la fecha y hora de generación del reporte
            Font fontInfo = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
            Paragraph info = new Paragraph();
            info.add(new Phrase("Reporte generado: ", fontInfo));
            
            // Formatear la fecha y hora actual
            SimpleDateFormat sdfFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String fechaHoraActual = sdfFechaHora.format(new Date());
            info.add(new Phrase(fechaHoraActual, fontInfo));
            info.setAlignment(Element.ALIGN_RIGHT);
            document.add(info);

            document.close();

            System.out.println("Reporte generado con éxito en " + archivo.getAbsolutePath());

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
