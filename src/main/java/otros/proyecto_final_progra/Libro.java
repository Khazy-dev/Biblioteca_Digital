package otros.proyecto_final_progra;

public class Libro {
    String titulo;
    String autor;
    String fechaPublicacion;
    String isbn;
    int numPaginas;
    ArbolPalabras palabrasClave; // Árbol binario de búsqueda

    public Libro(String titulo, String autor, String fechaPublicacion, String isbn, int numPaginas) {
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
        this.isbn = isbn;
        this.numPaginas = numPaginas;
        this.palabrasClave = new ArbolPalabras();
    }
}
