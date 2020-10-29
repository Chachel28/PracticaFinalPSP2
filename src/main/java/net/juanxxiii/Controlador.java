package net.juanxxiii;


import net.juanxxiii.conector.hilo.ConectorHilo;
import net.juanxxiii.conector.secuencial.ConectorSecuencial;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controlador {
    private static final Logger LOGGER = Logger.getLogger(Controlador.class.getName());

    public static void main(String[] args) {
        int numHilos = 5;
        int sumaTotal = 0;
        int particion;
        int resto;
        long tiempoInicial;
        long tiempoFinal;
        ConectorSecuencial conectorSecuencial = new ConectorSecuencial();
        List<ConectorHilo> hilos = new ArrayList<>();

        tiempoInicial = System.currentTimeMillis();
        conectorSecuencial.leerDatos();
        tiempoFinal = System.currentTimeMillis();
        System.out.println("La suma total es: " + conectorSecuencial.getSumaTotal());
        System.out.println("El tiempo que se ha tardado en leer demanera secuencial es: "
                + (tiempoFinal - tiempoInicial)
                + " milisegundos");

        particion = conectorSecuencial.getNumeroRegistros() / numHilos;
        resto = conectorSecuencial.getNumeroRegistros() % numHilos;
        for (int i = 0; i < numHilos; i++) {
            if(resto > 0 && i == numHilos-1) {
                hilos.add(new ConectorHilo((particion * i) + 1, particion * (i+1) + resto));
            }else {
                hilos.add(new ConectorHilo((particion * i) + 1, (particion * (i + 1))));
            }
        }
        tiempoInicial = System.currentTimeMillis();
        hilos.forEach(ConectorHilo::start);
        for (ConectorHilo hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                LOGGER.log(Level.WARNING, "Interrupted!", e);
                Thread.currentThread().interrupt();
            }
        }
        tiempoFinal = System.currentTimeMillis();
        for (ConectorHilo hilo : hilos) {
            sumaTotal += hilo.getSumaTotal();
        }
        System.out.println("La suma total es: " + sumaTotal);
        System.out.println("El tiempo que se ha tardado en leer demanera secuencial es: "
                + (tiempoFinal - tiempoInicial)
                + " milisegundos");
    }
}
