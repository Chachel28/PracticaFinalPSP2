# Practica Final Ejercicio 2
- Enunciado
- Prerrequisitos
- Ejecución
### Enunciado
2. Aplicación 2:
    2. Paso 1: Leer de manera secuencial todos los registros y mostrar el tiempo
empleado en la lectura y la suma (sin usar sentencias SQL de suma) del
campo INGRESOS.
    2. Paso 2: Leer la base de datos anterior con cinco hilos de ejecución concurrentes
y mostrar el tiempo empleado en la lectura y la suma (sin usar
sentencias SQL de suma) del campo INGRESOS
### Prerrequisitos
- Tener una base de datos con las siguientes características:
> - Database: BBDD_PSP_1
>
> - Table: EMPLEADOS
>
> - DB_USER: DAM2020_PSP
>
> - DB_PASSWORD: DAM2020_PSP
>
> - ID: PK. Integer. Autoincremental
>
> - EMAIL: varchar(100)
>
> - INGRESOS: Integer

- Tener instalado java en el ordenador

### Ejecución
1. Paso 1: Iniciar la base de datos
2. Desde un terminal ejecutar el comando "java -jar" junto con la ruta del archivo .jar
    1. Ejemplo:
    ```
    java -jar C:\Users\alexg\IdeaProjects\PracticaFinalPSP2\PracticaFinalPSP2-1.0-SNAPSHOT-jar-with-dependencies.jar
    ```