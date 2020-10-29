package net.juanxxiii.conector.secuencial;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConectorSecuencial {
    private static final Logger LOGGER = Logger.getLogger(ConectorSecuencial.class.getName());
    private int sumaTotal = 0;
    private int numeroRegistros = 0;

    public int getSumaTotal() {
        return sumaTotal;
    }

    public int getNumeroRegistros() {
        return numeroRegistros;
    }

    public void leerDatos() {
        try (Connection connection =
                     DriverManager.getConnection(
                             "jdbc:mysql://localhost:3306/bbdd_psp_1",
                             "DAM2020_PSP",
                             "DAM2020_PSP");
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM empleados");
            while (resultSet.next()) {
                sumaTotal += resultSet.getInt("Ingresos");
                numeroRegistros++;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Interrupted!", e);
        }
    }
}
