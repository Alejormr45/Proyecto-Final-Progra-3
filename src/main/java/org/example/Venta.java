package org.example;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venta {
    private static final Logger logger = LogManager.getLogger(Venta.class);

    private int idVenta;
    private List<Producto> productosVendidos;
    private double totalVenta;
    private LocalDateTime fechaHora;

    public Venta(int idVenta) {
        this.idVenta = idVenta;
        this.productosVendidos = new ArrayList<>();
        this.fechaHora = LocalDateTime.now();
    }

    public void agregarProducto(Producto producto) {
        productosVendidos.add(producto);
        totalVenta += producto.getPrecio();
        logger.info("Producto agregado a la venta: ID Venta={}, Producto={}, Precio={}", idVenta, producto.getNombre(), producto.getPrecio());
    }

    public double calcularTotal() {
        logger.info("Total calculado para ID Venta={}: Total={}", idVenta, totalVenta);
        return totalVenta;
    }

    public void generarFactura(String nombreCliente, String correoCliente) {
        try {
            Factura.generarFactura(nombreCliente, correoCliente, productosVendidos, totalVenta);
            logger.info("Factura generada para ID Venta={} y Cliente={}", idVenta, nombreCliente);
        } catch (IOException e) {
            logger.error("Error al generar factura: {}", e.getMessage());
        }
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", productosVendidos=" + productosVendidos +
                ", totalVenta=" + totalVenta +
                ", fechaHora=" + fechaHora +
                '}';
    }
}
