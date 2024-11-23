package org.example;


import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Producto {
    private static final Logger logger = LogManager.getLogger(Producto.class);

    private String nombre;
    private double precio;
    private int cantidadEnStock;

    public Producto(String nombre, double precio, int cantidadEnStock) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadEnStock = cantidadEnStock;
    }

    public static List<Producto> generarProductos(int cantidad) {
        Faker faker = new Faker();
        List<Producto> productos = new ArrayList<>();

        for (int i = 0; i < cantidad; i++) {
            String nombre = faker.commerce().productName();
            double precio = ThreadLocalRandom.current().nextDouble(50, 500);
            int stock = ThreadLocalRandom.current().nextInt(10, 100);

            Producto producto = new Producto(nombre, precio, stock);
            productos.add(producto);

            logger.info("Producto creado: Nombre={}, Precio={}, Stock={}", nombre, precio, stock);
        }

        return productos;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", cantidadEnStock=" + cantidadEnStock +
                '}';
    }
}
