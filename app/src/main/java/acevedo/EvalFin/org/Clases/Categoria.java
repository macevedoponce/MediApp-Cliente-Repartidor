package acevedo.EvalFin.org.Clases;

public class Categoria {
    int id;
    String nombre;
    int estado;
    String ruta_imagen;

    public Categoria() {
    }

    public Categoria(int id, String nombre, int estado, String ruta_imagen) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.ruta_imagen = ruta_imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getRuta_imagen() {
        return ruta_imagen;
    }

    public void setRuta_imagen(String ruta_imagen) {
        this.ruta_imagen = ruta_imagen;
    }
}
