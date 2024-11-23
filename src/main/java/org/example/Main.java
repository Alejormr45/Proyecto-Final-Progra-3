package org.example;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Generando datos de prueba...");

        // Generar usuarios y productos
        List<Usuario> usuarios = Usuario.generarUsuarios(20);
        List<Producto> productos = Producto.generarProductos(20);

        // Simular una venta
        Venta venta = new Venta(1);
        venta.agregarProducto(productos.get(0));
        venta.agregarProducto(productos.get(1));
        venta.calcularTotal();

        // Generar factura
        Usuario cliente = usuarios.get(0);
        venta.generarFactura(cliente.getNombreUsuario(), cliente.getCorreo());
    }
}
