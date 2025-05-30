package otros.proyecto_final_progra;

import java.util.*;

public class ArbolPalabras {
    private NodoPalabra raiz;

    public void insertar(String palabra) {
        raiz = insertarRec(raiz, palabra);
    }

    private NodoPalabra insertarRec(NodoPalabra nodo, String palabra) {
        if (nodo == null) return new NodoPalabra(palabra);
        if (palabra.compareToIgnoreCase(nodo.palabra) < 0)
            nodo.izquierda = insertarRec(nodo.izquierda, palabra);
        else if (palabra.compareToIgnoreCase(nodo.palabra) > 0)
            nodo.derecha = insertarRec(nodo.derecha, palabra);
        return nodo;
    }

   public boolean contiene(String palabra) {
    return contieneRec(raiz, palabra.trim().toLowerCase());
}

private boolean contieneRec(NodoPalabra nodo, String palabra) {
    if (nodo == null) return false;
    String actual = nodo.palabra.trim().toLowerCase();
    if (palabra.equals(actual)) return true;
    if (palabra.compareTo(actual) < 0)
        return contieneRec(nodo.izquierda, palabra);
    else
        return contieneRec(nodo.derecha, palabra);
}


    public List<String> obtenerPalabrasOrdenadas() {
        List<String> resultado = new ArrayList<>();
        inOrden(raiz, resultado);
        return resultado;
    }

    private void inOrden(NodoPalabra nodo, List<String> resultado) {
        if (nodo != null) {
            inOrden(nodo.izquierda, resultado);
            resultado.add(nodo.palabra);
            inOrden(nodo.derecha, resultado);
        }
    }
}
