package com.example.proyectolwing;

import android.graphics.Bitmap;

public class Usuarios {

    private String Nombre;
    private String idProyecto;
    private String Descripcion;
    private String Fecha;
    private String Numero;
<<<<<<< HEAD
    private Bitmap Foto;

    public Usuarios(String nombre, String idProyecto, String descripcion, String fecha, String numero, Bitmap foto) {
=======
    private String Monto;
    private Bitmap Foto;
    private int Estado;

    public Usuarios(String nombre, String idProyecto, String descripcion, String fecha, String numero, Bitmap foto, int estado, String monto) {
>>>>>>> 346976c... Commit secundario con avances
        this.Nombre = nombre;
        this.idProyecto = idProyecto;
        this.Descripcion = descripcion;
        this.Fecha=fecha;
        this.Numero=numero;
<<<<<<< HEAD
        this.Foto = foto;
=======
        this.Monto=monto;
        this.Foto = foto;
        this.Estado = estado;
>>>>>>> 346976c... Commit secundario con avances
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

<<<<<<< HEAD
=======
        public String getMonto(){ return Monto;}

>>>>>>> 346976c... Commit secundario con avances
        public Bitmap getFoto() {
            return Foto;
        }

<<<<<<< HEAD
=======
        public int getEstado(){return Estado;}

>>>>>>> 346976c... Commit secundario con avances
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

<<<<<<< HEAD
        public void  setFoto(Bitmap foto) {
            this.Foto = foto; }
=======
        public void setMonto(String monto){
        this.Monto = monto;
        }

        public void  setFoto(Bitmap foto) {
            this.Foto = foto; }

        public void setEstado(int estado){this.Estado = estado;}
>>>>>>> 346976c... Commit secundario con avances
    }

