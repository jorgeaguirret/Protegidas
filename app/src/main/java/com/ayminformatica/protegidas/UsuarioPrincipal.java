package com.ayminformatica.protegidas;

public class UsuarioPrincipal {
    int usuariop_id;
    String Nombre, Apellido, Usuario, Password, Telefono, Direccion, Comuna;
    int rol_id;

    public UsuarioPrincipal() {
    }

    public UsuarioPrincipal(int usuariop_id, String nombre, String apellido, String usuario, String password, String telefono, String direccion, String comuna, int rol_id) {
        this.usuariop_id = usuariop_id;
        Nombre = nombre;
        Apellido = apellido;
        Usuario = usuario;
        Password = password;
        Telefono = telefono;
        Direccion = direccion;
        Comuna = comuna;
        this.rol_id = rol_id;
    }

    public boolean isNull(){
        if (Nombre.equals("")|| Apellido.equals("")||Usuario.equals("")|| Password.equals("")||Telefono.equals("")||Direccion.equals("")||Comuna.equals("") ){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "UsuarioPrincipal{" +
                "usuariop_id=" + usuariop_id +
                ", Nombre='" + Nombre + '\'' +
                ", Apellido='" + Apellido + '\'' +
                ", Usuario='" + Usuario + '\'' +
                ", Password='" + Password + '\'' +
                ", Telefono='" + Telefono + '\'' +
                ", Direccion='" + Direccion + '\'' +
                ", Comuna='" + Comuna + '\'' +
                ", rol_id=" + rol_id +
                '}';
    }

    public int getUsuariop_id() {
        return usuariop_id;
    }

    public void setUsuariop_id(int usuariop_id) {
        this.usuariop_id = usuariop_id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getComuna() {
        return Comuna;
    }

    public void setComuna(String comuna) {
        Comuna = comuna;
    }

    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }
}