package com.tiendaonline.crud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CustommerFrame extends JFrame {
    private JTextField txt_id_custommer;
    private JTextField txt_fecha_registro;
    private JTextField txt_dni;
    private JTextField txt_nombre;
    private JTextField txt_apellido;
    private JTextField txt_direccion;
    private JTextField txt_telefono;
    private JTextField txt_email;
    private JTextField txt_ciudad;
    private JTextField txt_pais;
    private JLabel lbl_output;

    public CustommerFrame() {
        setTitle("CRUD Tabla Custommers 'Tienda Online'");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;

        // Labels and TextFields
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("ID Custommer:"), constraints);

        constraints.gridx = 1;
        txt_id_custommer = new JTextField(20);
        panel.add(txt_id_custommer, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Fecha Registro:"), constraints);

        // Obtener la fecha actual del sistema
        LocalDate fechaActual = LocalDate.now();

        // Formatear la fecha actual en el formato "dd/MM/yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fechaActual.format(formatter);

        constraints.gridx = 1;
        txt_fecha_registro = new JTextField(fechaFormateada, 20); // Establecer la fecha actual en el campo
        panel.add(txt_fecha_registro, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(new JLabel("DNI:"), constraints);

        constraints.gridx = 1;
        txt_dni = new JTextField(20);
        panel.add(txt_dni, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(new JLabel("Nombre:"), constraints);

        constraints.gridx = 1;
        txt_nombre = new JTextField(20);
        panel.add(txt_nombre, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(new JLabel("Apellido:"), constraints);

        constraints.gridx = 1;
        txt_apellido = new JTextField(20);
        panel.add(txt_apellido, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(new JLabel("Dirección:"), constraints);

        constraints.gridx = 1;
        txt_direccion = new JTextField(20);
        panel.add(txt_direccion, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(new JLabel("Teléfono:"), constraints);

        constraints.gridx = 1;
        txt_telefono = new JTextField(20);
        panel.add(txt_telefono, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        panel.add(new JLabel("Email:"), constraints);

        constraints.gridx = 1;
        txt_email = new JTextField(20);
        panel.add(txt_email, constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        panel.add(new JLabel("Ciudad:"), constraints);

        constraints.gridx = 1;
        txt_ciudad = new JTextField(20);
        panel.add(txt_ciudad, constraints);

        constraints.gridx = 0;
        constraints.gridy = 9;
        panel.add(new JLabel("País:"), constraints);

        constraints.gridx = 1;
        txt_pais = new JTextField(20);
        panel.add(txt_pais, constraints);

        // Label for output messages
        constraints.gridx = 0;
        constraints.gridy = 10;
        constraints.gridwidth = 2;
        lbl_output = new JLabel();
        lbl_output.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lbl_output, constraints);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton btn_Crear_Custommer = new JButton("CREAR");
        btn_Crear_Custommer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createCustommer();
            }
        });
        buttonPanel.add(btn_Crear_Custommer);

        JButton btn_Buscar_Custommer = new JButton("BUSCAR");
        btn_Buscar_Custommer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getCustommerByDni();
            }
        });
        buttonPanel.add(btn_Buscar_Custommer);

        JButton btn_Actualizar_Custommer = new JButton("ACTUALIZAR");
        btn_Actualizar_Custommer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateCustommer();
            }
        });
        buttonPanel.add(btn_Actualizar_Custommer);

        JButton btn_Eliminar_Custommer = new JButton("ELIMINAR");
        btn_Eliminar_Custommer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteCustommer();
            }
        });
        buttonPanel.add(btn_Eliminar_Custommer);

        constraints.gridx = 0;
        constraints.gridy = 11;
        constraints.gridwidth = 2;
        panel.add(buttonPanel, constraints);

        add(panel);
    }

    private void createCustommer() {
        Custommer custommer = new Custommer();
        custommer.setIdCustommer(Long.parseLong(txt_id_custommer.getText()));

        // Parsear la fecha ingresada en el campo de texto
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaRegistro = LocalDate.parse(txt_fecha_registro.getText(), formatter);
            custommer.setFechaRegistro(LocalDateTime.of(fechaRegistro, LocalDateTime.now().toLocalTime())); // Incluir la hora actual si es necesario
        } catch (DateTimeParseException e) {
            lbl_output.setText("Error al parsear la fecha. Formato esperado: dd/MM/yyyy");
            return; // Salir del método si hay un error de formato de fecha
        }

        custommer.setDni(txt_dni.getText());
        custommer.setNombre(txt_nombre.getText());
        custommer.setApellido(txt_apellido.getText());
        custommer.setDireccion(txt_direccion.getText());
        custommer.setTelefono(txt_telefono.getText());
        custommer.setEmail(txt_email.getText());
        custommer.setCiudad(txt_ciudad.getText());
        custommer.setPais(txt_pais.getText());

        CustommerDAO dao = new CustommerDAO();
        dao.createCustommer(custommer);
        lbl_output.setText("Custommer created successfully!");
    }

    private void getCustommerByDni() {
        String dni = txt_dni.getText();
        CustommerDAO dao = new CustommerDAO();
        Custommer custommer = dao.getCustommerByDni(dni);

        if (custommer != null) {
            txt_id_custommer.setText(String.valueOf(custommer.getIdCustommer()));

            // Formatear la fecha al campo de texto
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaFormateada = custommer.getFechaRegistro().format(formatter);
            txt_fecha_registro.setText(fechaFormateada);

            txt_dni.setText(custommer.getDni());
            txt_nombre.setText(custommer.getNombre());
            txt_apellido.setText(custommer.getApellido());
            txt_direccion.setText(custommer.getDireccion());
            txt_telefono.setText(custommer.getTelefono());
            txt_email.setText(custommer.getEmail());
            txt_ciudad.setText(custommer.getCiudad());
            txt_pais.setText(custommer.getPais());
            lbl_output.setText("Custommer found successfully!");
        } else {
            lbl_output.setText("Custommer not found!");
        }
    }

    private void updateCustommer() {
        Custommer custommer = new Custommer();
        custommer.setIdCustommer(Long.parseLong(txt_id_custommer.getText()));

        // Parsear la fecha ingresada en el campo de texto
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaRegistro = LocalDate.parse(txt_fecha_registro.getText(), formatter);
            custommer.setFechaRegistro(LocalDateTime.of(fechaRegistro, LocalDateTime.now().toLocalTime())); // Incluir la hora actual si es necesario
        } catch (DateTimeParseException e) {
            lbl_output.setText("Error al parsear la fecha. Formato esperado: dd/MM/yyyy");
            return; // Salir del método si hay un error de formato de fecha
        }

        custommer.setDni(txt_dni.getText());
        custommer.setNombre(txt_nombre.getText());
        custommer.setApellido(txt_apellido.getText());
        custommer.setDireccion(txt_direccion.getText());
        custommer.setTelefono(txt_telefono.getText());
        custommer.setEmail(txt_email.getText());
        custommer.setCiudad(txt_ciudad.getText());
        custommer.setPais(txt_pais.getText());

        CustommerDAO dao = new CustommerDAO();
        dao.updateCustommer(custommer);
        lbl_output.setText("Custommer updated successfully!");
    }

    private void deleteCustommer() {
        String dni = txt_dni.getText();
        CustommerDAO dao = new CustommerDAO();
        dao.deleteCustommerByDni(dni);
        lbl_output.setText("Custommer deleted successfully!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CustommerFrame().setVisible(true);
            }
        });
    }
}
