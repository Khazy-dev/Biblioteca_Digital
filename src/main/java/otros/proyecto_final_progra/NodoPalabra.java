package otros.proyecto_final_progra;

public class NodoPalabra {
    String palabra;
    NodoPalabra izquierda, derecha;

    public NodoPalabra(String palabra) {
        this.palabra = palabra;
        this.izquierda = null;
        this.derecha = null;
    }
}
