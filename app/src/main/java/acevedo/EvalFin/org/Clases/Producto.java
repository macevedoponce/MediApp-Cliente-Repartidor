package acevedo.EvalFin.org.Clases;

public class Producto {
    int id;
    int id_categoria;
    String codigo;
    String nombre;
    double precio_venta;
    int stock;
    String presentacion;
    String ruta_imagen;
    String descripcion;
    int estado;

    public Producto() {
    }

    public Producto(int id, int id_categoria, String codigo, String nombre, double precio_venta, int stock, String presentacion, String ruta_imagen, String descripcion, int estado) {
        this.id = id;
        this.id_categoria = id_categoria;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio_venta = precio_venta;
        this.stock = stock;
        this.presentacion = presentacion;
        this.ruta_imagen = ruta_imagen;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getRuta_imagen() {
        return ruta_imagen;
    }

    public void setRuta_imagen(String ruta_imagen) {
        this.ruta_imagen = ruta_imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}



