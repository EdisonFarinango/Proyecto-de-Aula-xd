package com.mycompany.proyectoaula;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import com.toedter.calendar.JDateChooser;

public class GenerarReporteFacturaPDF {

    private JDateChooser fechaMin;
    private JDateChooser fechaMax;
    private JTextField fieldTotalGanancias; // Añadir campo para el total de ganancias

    public GenerarReporteFacturaPDF(JDateChooser fechaMin, JDateChooser fechaMax, JTextField fieldTotalGanancias) {
        this.fechaMin = fechaMin;
        this.fechaMax = fechaMax;
        this.fieldTotalGanancias = fieldTotalGanancias;
    }

    public void generarReporteFacturas(JTable tabla) {
        Document document = new Document(PageSize.A4); // Configurar el tamaño de la página
        try {
            // Crear el directorio Reportes si no existe
            File directorio = new File("Reportes");
            if (!directorio.exists()) {
                directorio.mkdir();
            }

            // Obtener la fecha y hora actual para el nombre del archivo
            SimpleDateFormat sdfNombreArchivo = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String fechaHoraArchivo = sdfNombreArchivo.format(new Date());

            // Definir el archivo PDF con fecha y hora en el nombre
            File archivo = new File(directorio, "Lista_Facturas_" + fechaHoraArchivo + ".pdf");
            PdfWriter.getInstance(document, new FileOutputStream(archivo));

            document.open();

            // Añadir imagen al PDF (opcional)
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
            Font fontTitulo1 = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
            Paragraph titulo1 = new Paragraph("Tienda Online Derick", fontTitulo1);
            titulo1.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo1);

            // Agregar título
            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
            Paragraph titulo = new Paragraph("Reporte Facturas", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            // Agregar subtítulo con el lapso de fechas
            Font fontSubtitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph subtitulo = new Paragraph("Facturas generadas entre "
                    + formatFecha(fechaMin.getDate()) + " y "
                    + formatFecha(fechaMax.getDate()), fontSubtitulo);
            subtitulo.setAlignment(Element.ALIGN_CENTER);
            document.add(subtitulo);

            document.add(Chunk.NEWLINE);

            // Agregar la tabla
            PdfPTable pdfTable = new PdfPTable(tabla.getColumnCount());

            // Establecer el ancho de las columnas
            float[] columnWidths = new float[tabla.getColumnCount()];
            for (int i = 0; i < columnWidths.length; i++) {
                columnWidths[i] = 170f; // Ajustar el ancho de cada columna en puntos
            }
            pdfTable.setWidths(columnWidths);

            // Definir la fuente con el tamaño deseado
            Font font = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL); // Tamaño de fuente 8 puntos

            // Agregar encabezados de la tabla
            TableModel model = tabla.getModel();
            for (int i = 0; i < model.getColumnCount(); i++) {
                PdfPCell cell = new PdfPCell(new Phrase(model.getColumnName(i), font));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setPadding(10); // Agregar padding a las celdas de encabezado
                pdfTable.addCell(cell);
            }

            // Agregar datos de la tabla
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Object value = model.getValueAt(i, j);
                    PdfPCell cell = new PdfPCell(new Phrase(value != null ? value.toString() : "", font));
                    cell.setPadding(8); // Agregar padding a las celdas de datos
                    pdfTable.addCell(cell);
                }
            }

            // Configurar los márgenes de la tabla
            pdfTable.setSpacingBefore(10f); // Espacio antes de la tabla
            pdfTable.setSpacingAfter(10f);  // Espacio después de la tabla

            document.add(pdfTable);

            // Agregar el total de ganancias
            Font fontTotal = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
            String totalGanancias = fieldTotalGanancias.getText(); // Obtener el texto del campo de total de ganancias
            Paragraph totalGananciasParrafo = new Paragraph("" + totalGanancias, fontTotal);
            totalGananciasParrafo.setAlignment(Element.ALIGN_LEFT);
            document.add(totalGananciasParrafo);

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

    private String formatFecha(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(fecha);
        } else {
            return "No definida";
        }
    }
}
