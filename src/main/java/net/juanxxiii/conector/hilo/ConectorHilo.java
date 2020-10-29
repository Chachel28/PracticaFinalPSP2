package net.juanxxiii.conector.hilo;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConectorHilo extends Thread {
    private static final Logger LOGGER = Logger.getLogger(ConectorHilo.class.getName());

    private final int rangoInicial;
    private final int rangoFinal;
    private int sumaTotal = 0;

    public ConectorHilo(int rangoInicial, int rangoFinal) {
        this.rangoFinal = rangoFinal;
        this.rangoInicial = rangoInicial;
    }

    @Override
    public void run() {
        super.run();
        leerDatos();
    }

    private synchronized void leerDatos() {
        try (Connection connection =
                     DriverManager.getConnection(
                             "jdbc:mysql://localhost:3306/bbdd_psp_1",
                             "DAM2020_PSP",
                             "DAM2020_PSP");
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM empleados WHERE id BETWEEN "
                        + rangoInicial
                        + " AND "
                        + rangoFinal);
            while (resultSet.next()) {
                sumaTotal += resultSet.getInt("Ingresos");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Interrupted!", e);
        }

    }

    public int getSumaTotal() {
        return sumaTotal;
    }
}
