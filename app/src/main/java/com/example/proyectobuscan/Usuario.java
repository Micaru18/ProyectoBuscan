package com.example.proyectobuscan;

public class Usuario {

    private String nombre;
    private String apellidos;
    private String usuario;
    private String clave;
    private String correo;
    private String celular;
    private byte[] imagen;

    public Usuario() {
    }

    public Usuario(String nombre, String apellidos, String usuario, String clave, String correo, String celular) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.clave = clave;
        this.correo = correo;
        this.celular = celular;
    }

    public Usuario(String nombre, String apellidos, String usuario, String clave, String correo, String celular, byte[] imagen) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.clave = clave;
        this.correo = correo;
        this.celular = celular;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
