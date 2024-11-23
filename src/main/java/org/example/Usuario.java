package org.example;


import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private static final Logger logger = LogManager.getLogger(Usuario.class);

    private String nombreUsuario;
    private String correo;
    private String direccion;

    public Usuario(String nombreUsuario, String correo, String direccion) {
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.direccion = direccion;
    }

    public static List<Usuario> generarUsuarios(int cantidad) {
        Faker faker = new Faker();
        List<Usuario> usuarios = new ArrayList<>();

        for (int i = 0; i < cantidad; i++) {
            String nombre = faker.name().username();
            String correo = faker.internet().emailAddress();
            String direccion = faker.address().fullAddress();
            Usuario usuario = new Usuario(nombre, correo, direccion);

            usuarios.add(usuario);
            logger.info("Usuario creado: Nombre={}, Correo={}, Dirección={}", nombre, correo, direccion);
        }

        return usuarios;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}

