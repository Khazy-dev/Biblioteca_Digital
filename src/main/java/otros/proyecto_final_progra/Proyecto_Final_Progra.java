package otros.proyecto_final_progra;

import java.io.*;
import java.util.*;

public class Proyecto_Final_Progra {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el tamaño de la tabla hash (ej: 17): ");
        int tamanoTabla = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        HashTableLibro biblioteca = new HashTableLibro(tamanoTabla);

        System.out.print("Ingrese la ruta del archivo de entrada: ");
        String ruta = scanner.nextLine();

       try (BufferedReader br = new BufferedReader(
        new InputStreamReader(new FileInputStream(ruta), "UTF-8"))) {
    String linea;
    while ((linea = br.readLine()) != null) {
         if (linea.trim().isEmpty()) continue; //ignora lineas vacias
        Libro libro = parsearLinea(linea);
        if (libro != null) {
            biblioteca.insertar(libro);
        }
    }
} catch (IOException e) {
    System.out.println("Error al leer el archivo: " + e.getMessage());
    return;
}

        // Menú
        int opcion = 0;
        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Buscar libros por palabra clave");
            System.out.println("3. Salir");
            System.out.print("Ingrese opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título del libro a buscar: ");
                    String titulo = scanner.nextLine();
                    Libro libro = biblioteca.buscarPorTitulo(titulo);
                    if (libro != null) {
                        mostrarLibro(libro);
                    } else {
                        System.out.println("Libro no encontrado.");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese la palabra clave a buscar: ");
                    String palabra = scanner.nextLine();
                    List<Libro> libros = biblioteca.buscarPorPalabraClave(palabra);
                    if (!libros.isEmpty()) {
                        System.out.println("Libros que contienen la palabra clave '" + palabra + "':");
                        for (Libro l : libros) {
                            System.out.println("- " + l.titulo);
                        }
                    } else {
                        System.out.println("Ningún libro contiene esa palabra clave.");
                    }
                    break;
                case 3:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 3);

        scanner.close();
    }

    // Parsear línea del archivo de entrada y crear objeto Libro
    private static Libro parsearLinea(String linea) {
        try {
            int idxTitulo = linea.indexOf('[');
            int idxDatos = linea.indexOf(']');
            int idxPalabras = linea.indexOf("palabras(");
            int idxPalabrasFin = linea.indexOf(')', idxPalabras);

            String titulo = linea.substring(0, idxTitulo).trim();

            String datos = linea.substring(idxTitulo + 1, idxDatos);
            String[] datosArray = datos.split(",");

            String autor = datosArray[0].trim();
            String fecha = datosArray[1].trim();
            String isbn = datosArray[2].trim();
            int paginas = Integer.parseInt(datosArray[3].trim());

            String palabrasClaveStr = linea.substring(idxPalabras + 9, idxPalabrasFin);
            String[] palabras = palabrasClaveStr.split(",");

            Libro libro = new Libro(titulo, autor, fecha, isbn, paginas);
            for (String palabra : palabras) {
                libro.palabrasClave.insertar(palabra.trim());
            }
            
            return libro;
        } catch (Exception e) {
            System.out.println("Error al parsear la línea: " + linea);
            return null;
        }
    }

    // Mostrar datos completos del libro
    private static void mostrarLibro(Libro libro) {
        
        System.out.println("----INF DEL LIBRO----");
        System.out.println("Título: " + libro.titulo);
        System.out.println("Autor: " + libro.autor);
        System.out.println("Fecha de publicación: " + libro.fechaPublicacion);
        System.out.println("ISBN: " + libro.isbn);
        System.out.println("Número de páginas: " + libro.numPaginas);
        System.out.println("Palabras clave (ordenadas): ");
        System.out.println("---------------------");
        for (String palabra : libro.palabrasClave.obtenerPalabrasOrdenadas()) {
            System.out.println("- " + palabra);
        }
    }
}
