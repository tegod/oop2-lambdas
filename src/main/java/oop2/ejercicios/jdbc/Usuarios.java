package oop2.ejercicios.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
interface SQLconsumer {
    void conectar(Connection conn) throws SQLException;
}

public class Usuarios {

    private final String jdbcUrl;

    public Usuarios(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    private void gestionConeccion(SQLconsumer c, String mensaje) {
        try (Connection connection = DriverManager.getConnection(this.jdbcUrl)) {
            connection.setAutoCommit(false);

            c.conectar(connection);

            try {
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(mensaje, e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(mensaje, e);
        }
    }

    public void insertar(String nombre, String email) {
        gestionConeccion(conn -> {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO usuarios (nombre, email) VALUES (?, ?)")) {
                statement.setString(1, nombre);
                statement.setString(2, email);
                statement.executeUpdate();
            }
        }, "Error al insertar usuario");

    }

    public void actualizarEmail(int id, String nuevoEmail) {
        gestionConeccion(conn -> {
            try (PreparedStatement statement = conn.prepareStatement("UPDATE usuarios SET email = ? WHERE id = ?")) {
                statement.setString(1, nuevoEmail);
                statement.setInt(2, id);
                statement.executeUpdate();
            }
        }, "Error al actualizar usuario");
    }
}