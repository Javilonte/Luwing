package com.example.proyectolwing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewAnimator;

public class MainActivity extends AppCompatActivity {

    private static final int DATABASE_VERSION = 2;

    private ViewAnimator viewAnimator;
    private EditText txtusuario;
    private EditText txtcontraseña;

    private EditText txtCrearUsuario;
    private EditText txtIngresarPassword;
    private EditText txtRecrearPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewAnimator = (ViewAnimator) findViewById(R.id.viewAnimator);
        txtusuario = (EditText) findViewById(R.id.txtNumero);
        txtcontraseña = (EditText) findViewById(R.id.txtPassword);
        txtCrearUsuario = (EditText) findViewById(R.id.txtCrearNumero);
        txtIngresarPassword = (EditText) findViewById(R.id.txtCrearPassword);
        txtRecrearPassword = (EditText) findViewById(R.id.txtRecrearPassword);
        administrador();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
        }
    }

    //entrar al login
    public void ingresar(View v) {
        viewAnimator.showNext();
    }

    //regresar a la ventana 1

    //Metodo para logear
    public void continuar(View v) {
        String num = txtusuario.getText().toString();
        String pass = txtcontraseña.getText().toString();
        BaseLogin baseLogin = new BaseLogin(this, "Login", null, DATABASE_VERSION);
        SQLiteDatabase BaseDeDatos = baseLogin.getReadableDatabase();

        if (num.equals("") || pass.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor llene los campos ", Toast.LENGTH_SHORT).show();
        } else {
            Cursor fila = BaseDeDatos.rawQuery("select contraseña from login where numero='" + num + "'", null);
            if (fila.moveToFirst()) {
                if (pass.equals(fila.getString(0))) {
                    Intent intent = new Intent(MainActivity.this, Menu.class);
                    BaseDeDatos.close();
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(getApplicationContext(), "Usuario inexistente", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Ingresar a la tercer ventana
    public void crearcuenta(View v) {
        viewAnimator.showNext();
    }

    //regresar de la ventana 2
    public void regresar2(View v) {
        viewAnimator.showPrevious();
    }

    //Metodo para Crear usuario
    public void guardar(View v) {
        long resultado;
        String num = txtCrearUsuario.getText().toString();
        String pass = txtIngresarPassword.getText().toString();
        String repass = txtRecrearPassword.getText().toString();
        BaseLogin baseLogin = new BaseLogin(this, "Login", null, DATABASE_VERSION);
        SQLiteDatabase BaseDeDatos = baseLogin.getWritableDatabase();

        if (num.equals("") || pass.equals("") || repass.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor llene los campos ", Toast.LENGTH_SHORT).show();
        } else {
            if (num.length() != 10) {
                Toast.makeText(getApplicationContext(), "El teléfono debe que contener 10 dígitos", Toast.LENGTH_SHORT).show();
            } else {
                if (!pass.equals(repass)) {
                    Toast.makeText(getApplicationContext(), "Las contraseñas deben coincidir ", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues registro = new ContentValues();
                    registro.put("numero", num);
                    registro.put("contraseña", pass);
                    registro.put("tipocuenta", 0);
                    resultado = BaseDeDatos.insert("login", "null", registro);
                    if (resultado == -1) {
                        Toast.makeText(getApplicationContext(), "Cuenta ya existente", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                    }
                    BaseDeDatos.close();

                    txtCrearUsuario.setText("");
                    txtIngresarPassword.setText("");
                    txtRecrearPassword.setText("");
                }
            }
        }
    }

    //Metodo para crear administradores
    private void administrador() {
        BaseLogin baseLogin = new BaseLogin(this, "Login", null, DATABASE_VERSION);
        SQLiteDatabase BaseDeDatos = baseLogin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("numero", "3317396356");
        registro.put("contraseña", "lwing");
        registro.put("tipocuenta", 1);
        BaseDeDatos.insert("login", "null", registro);
    }

    //Declarar permisos

}