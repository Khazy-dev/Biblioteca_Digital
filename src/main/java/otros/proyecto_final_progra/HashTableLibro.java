package otros.proyecto_final_progra;

import java.util.*;

public class HashTableLibro {
    private LinkedList<Libro>[] tabla;
    private int capacidad;

    @SuppressWarnings("unchecked")
    public HashTableLibro(int capacidad) {
        this.capacidad = capacidad;
        tabla = new LinkedList[capacidad];
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new LinkedList<>();
        }
    }

    private int hash(String titulo) {
        return Math.abs(titulo.toLowerCase().hashCode()) % capacidad;
    }

    public void insertar(Libro libro) {
        int indice = hash(libro.titulo);
        tabla[indice].add(libro);
    }

    public Libro buscarPorTitulo(String titulo) {
        int indice = hash(titulo);
        for (Libro libro : tabla[indice]) {
        if (libro.titulo.trim().equalsIgnoreCase(titulo.trim())) {
                return libro;
            }
        }
        return null;
    }

    public List<Libro> buscarPorPalabraClave(String palabra) {
        List<Libro> resultado = new ArrayList<>();
        for (LinkedList<Libro> lista : tabla) {
            for (Libro libro : lista) {
                if (libro.palabrasClave.contiene(palabra)) {
                    resultado.add(libro);
                }
            }
        }
        return resultado;
    }
}
