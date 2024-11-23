package org.example;


import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.Row;


public class Factura {
    private static final String DIRECTORIO_FACTURAS = "facturas/";

    public static void generarFactura(String nombreCliente, String correoCliente, List<Producto> productos, double totalVenta) throws IOException {
        // Crear directorio para almacenar facturas si no existe
        Files.createDirectories(Path.of(DIRECTORIO_FACTURAS));

        // Crear el archivo Excel (representación de la factura)
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Factura");

            // Encabezado de la tienda
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("TIENDA DE ELECTRÓNICA - FACTURA");
            header.createCell(1).setCellValue("Fecha: " + LocalDate.now());

            // Información del cliente
            Row clientInfo = sheet.createRow(2);
            clientInfo.createCell(0).setCellValue("Cliente:");
            clientInfo.createCell(1).setCellValue(nombreCliente);
            clientInfo.createCell(2).setCellValue("Correo:");
            clientInfo.createCell(3).setCellValue(correoCliente);

            // Tabla de productos
            Row tableHeader = sheet.createRow(4);
            tableHeader.createCell(0).setCellValue("Producto");
            tableHeader.createCell(1).setCellValue("Precio Unitario");
            tableHeader.createCell(2).setCellValue("Cantidad");
            tableHeader.createCell(3).setCellValue("Total");

            int rowNum = 5;
            for (Producto producto : productos) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(producto.getNombre());
                row.createCell(1).setCellValue(producto.getPrecio());
                row.createCell(2).setCellValue(1); // Suponiendo 1 unidad por producto en esta venta
                row.createCell(3).setCellValue(producto.getPrecio());
            }

            // Total de la venta
            Row totalRow = sheet.createRow(rowNum);
            totalRow.createCell(2).setCellValue("Total:");
            totalRow.createCell(3).setCellValue(totalVenta);

            // Guardar el archivo
            String nombreArchivo = DIRECTORIO_FACTURAS + "Factura_" + nombreCliente + "_" + LocalDate.now() + ".xlsx";
            try (FileOutputStream out = new FileOutputStream(nombreArchivo)) {
                workbook.write(out);
            }

            System.out.println("Factura generada: " + nombreArchivo);
        }
    }
}
