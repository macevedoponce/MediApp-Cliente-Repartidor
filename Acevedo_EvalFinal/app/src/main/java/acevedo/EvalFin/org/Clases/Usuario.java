package acevedo.EvalFin.org.Clases;

public class Usuario {
    int id,genero,tipo_user,tipo_documento;
    String documento,nombres,apPaterno,apMaterno,celular,f_nacimiento,correo,password,img_url;

    public Usuario() {
    }

    public Usuario(int id, int genero, int tipo_user, int tipo_documento, String documento, String nombres, String apPaterno, String apMaterno, String celular, String f_nacimiento, String correo, String password, String img_url) {
        this.id = id;
        this.genero = genero;
        this.tipo_user = tipo_user;
        this.tipo_documento = tipo_documento;
        this.documento = documento;
        this.nombres = nombres;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.celular = celular;
        this.f_nacimiento = f_nacimiento;
        this.correo = correo;
        this.password = password;
        this.img_url = img_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public int getTipo_user() {
        return tipo_user;
    }

    public void setTipo_user(int tipo_user) {
        this.tipo_user = tipo_user;
    }

    public int getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(int tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getF_nacimiento() {
        return f_nacimiento;
    }

    public void setF_nacimiento(String f_nacimiento) {
        this.f_nacimiento = f_nacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", genero=" + genero +
                ", tipo_user=" + tipo_user +
                ", tipo_documento=" + tipo_documento +
                ", documento='" + documento + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apPaterno='" + apPaterno + '\'' +
                ", apMaterno='" + apMaterno + '\'' +
                ", celular='" + celular + '\'' +
                ", f_nacimiento='" + f_nacimiento + '\'' +
                ", correo='" + correo + '\'' +
                ", password='" + password + '\'' +
                ", img_url='" + img_url + '\'' +
                '}';
    }
}
