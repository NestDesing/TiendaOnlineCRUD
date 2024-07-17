package com.tiendaonline.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustommerDAO {

    private Connection connection;

    public CustommerDAO() {
        // Aquí deberías inicializar la conexión a la base de datos
        this.connection = DatabaseConnection.getConnection();
    }

    // Método para crear un nuevo cliente
    public void createCustommer(Custommer custommer) {
        String query = "INSERT INTO custommers (id_custommer, fecha_registro, dni, nombre, apellido, direccion, telefono, email, ciudad, pais) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, custommer.getIdCustommer());
            statement.setObject(2, custommer.getFechaRegistro());
            statement.setString(3, custommer.getDni());
            statement.setString(4, custommer.getNombre());
            statement.setString(5, custommer.getApellido());
            statement.setString(6, custommer.getDireccion());
            statement.setString(7, custommer.getTelefono());
            statement.setString(8, custommer.getEmail());
            statement.setString(9, custommer.getCiudad());
            statement.setString(10, custommer.getPais());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar un cliente por su DNI
    public Custommer getCustommerByDni(String dni) {
        Custommer custommer = null;
        String query = "SELECT * FROM custommers WHERE dni = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, dni);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                custommer = new Custommer();
                custommer.setIdCustommer(resultSet.getLong("id_custommer"));
                custommer.setFechaRegistro(resultSet.getObject("fecha_registro", LocalDateTime.class));
                custommer.setDni(resultSet.getString("dni"));
                custommer.setNombre(resultSet.getString("nombre"));
                custommer.setApellido(resultSet.getString("apellido"));
                custommer.setDireccion(resultSet.getString("direccion"));
                custommer.setTelefono(resultSet.getString("telefono"));
                custommer.setEmail(resultSet.getString("email"));
                custommer.setCiudad(resultSet.getString("ciudad"));
                custommer.setPais(resultSet.getString("pais"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return custommer;
    }

    // Método para actualizar un cliente
    public void updateCustommer(Custommer custommer) {
        String query = "UPDATE custommers SET fecha_registro = ?, nombre = ?, apellido = ?, direccion = ?, telefono = ?, " +
                "email = ?, ciudad = ?, pais = ? WHERE id_custommer = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, custommer.getFechaRegistro());
            statement.setString(2, custommer.getNombre());
            statement.setString(3, custommer.getApellido());
            statement.setString(4, custommer.getDireccion());
            statement.setString(5, custommer.getTelefono());
            statement.setString(6, custommer.getEmail());
            statement.setString(7, custommer.getCiudad());
            statement.setString(8, custommer.getPais());
            statement.setLong(9, custommer.getIdCustommer());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un cliente por su DNI
    public void deleteCustommerByDni(String dni) {
        String query = "DELETE FROM custommers WHERE dni = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, dni);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
