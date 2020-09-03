package com.example.proyectolwing;

import android.graphics.Bitmap;

public class Usuarios {

    private String Nombre;
    private String idProyecto;
    private String Descripcion;
    private String Fecha;
    private String Numero;
    private Bitmap Foto;

    public Usuarios(String nombre, String idProyecto, String descripcion, String fecha, String numero, Bitmap foto) {
        this.Nombre = nombre;
        this.idProyecto = idProyecto;
        this.Descripcion = descripcion;
        this.Fecha=fecha;
        this.Numero=numero;
        this.Foto = foto;
    }


    public String getNombre() {
            return Nombre;
        }

        public String getIdProyecto() {
            return idProyecto;
        }

        public String getDescripcion() {
            return Descripcion;
        }

        public String getFecha() {
        return Fecha;
    }

        public String getNumero() {
        return Numero;
    }

        public Bitmap getFoto() {
            return Foto;
        }

        public void setNombre(String nombre) {
           this.Nombre = nombre;
        }

        public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
        }

        public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion; }

        public void setFecha(String fecha) {
        this.Fecha = fecha; }

        public void setNumero(String numero) {
        this.Numero = numero; }

        public void  setFoto(Bitmap foto) {
            this.Foto = foto; }
    }

