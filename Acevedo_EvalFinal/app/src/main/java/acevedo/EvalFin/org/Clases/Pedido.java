package acevedo.EvalFin.org.Clases;

public class Pedido {
    int id,estado;
    double longitud, latitud;
    String persona_recepcion,celular_persona_recepcion, total,fecha,cantidad,nombreProducto,precio_unitario,img_url;

    public Pedido() {
    }


    public Pedido(int id, int estado, double longitud, double latitud, String persona_recepcion, String celular_persona_recepcion, String total, String fecha, String cantidad, String nombreProducto, String precio_unitario, String img_url) {
        this.id = id;
        this.estado = estado;
        this.longitud = longitud;
        this.latitud = latitud;
        this.persona_recepcion = persona_recepcion;
        this.celular_persona_recepcion = celular_persona_recepcion;
        this.total = total;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.nombreProducto = nombreProducto;
        this.precio_unitario = precio_unitario;
        this.img_url = img_url;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(String precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public String getPersona_recepcion() {
        return persona_recepcion;
    }

    public void setPersona_recepcion(String persona_recepcion) {
        this.persona_recepcion = persona_recepcion;
    }

    public String getCelular_persona_recepcion() {
        return celular_persona_recepcion;
    }

    public void setCelular_persona_recepcion(String celular_persona_recepcion) {
        this.celular_persona_recepcion = celular_persona_recepcion;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
}
