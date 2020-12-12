package com.example.proyectolwing;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrearProyecto extends AppCompatActivity {

<<<<<<< HEAD
    private static final int DATABASE_VERSION = 4;
=======
    private static final int DATABASE_VERSION = 5;
>>>>>>> 346976c... Commit secundario con avances

    EditText cpusuario;
    EditText cpdescripcion;
    EditText cpfecha;
    EditText cpnumero;
<<<<<<< HEAD
=======
    EditText cpmonto;
>>>>>>> 346976c... Commit secundario con avances

    ImageView cpimagen;
   private Bitmap imagen=null;
   private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_proyecto);

        cpusuario = (EditText) findViewById(R.id.CPUsuario);
        cpdescripcion = (EditText) findViewById(R.id.CPDescripcion);
        cpfecha = (EditText) findViewById(R.id.CPFecha);
        cpnumero = (EditText) findViewById(R.id.CPNumero);
        cpimagen = (ImageView) findViewById(R.id.CPImagen);
<<<<<<< HEAD
=======
        cpmonto = (EditText) findViewById(R.id.CPMonto);
>>>>>>> 346976c... Commit secundario con avances


    }

    public void CPGuardar(View v) {
        String usuario = cpusuario.getText().toString();
        String descripcion = cpdescripcion.getText().toString();
        String fecha = cpfecha.getText().toString();
        String numero = cpnumero.getText().toString();
<<<<<<< HEAD
=======
        String monto = cpmonto.getText().toString();
>>>>>>> 346976c... Commit secundario con avances



        BaseLogin baseLogin = new BaseLogin(this, "usuarios", null, DATABASE_VERSION);
        SQLiteDatabase BaseDeDatos = baseLogin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("usuario", usuario);
        registro.put("descripcion", descripcion);
        registro.put("fecha", fecha);
        registro.put("numero", numero);
        registro.put("estado",1);
<<<<<<< HEAD
=======
        registro.put("monto", monto);
>>>>>>> 346976c... Commit secundario con avances
        BaseDeDatos.insert("usuarios", null, registro);
        Toast.makeText(getApplicationContext(), "Guardado", Toast.LENGTH_SHORT).show();

        Cursor fila = BaseDeDatos.rawQuery("select id from usuarios where usuario='" + usuario + "'", null);
        fila.moveToLast();
        Toast.makeText(getApplicationContext(), "El id del proyecto es: " + fila.getString(0), Toast.LENGTH_LONG).show();

        if (imagen==null){
            cpimagen.buildDrawingCache();
            Bitmap bmap = cpimagen.getDrawingCache();
            imagen=bmap;
        }
        id=fila.getString(0);
        guardarFotoSQLITE(imagen);

        cpusuario.setText("");
        cpdescripcion.setText("");
        cpfecha.setText("");
        cpnumero.setText("");
        BaseDeDatos.close();

        onBackPressed();
    }

    public void SubirFoto(View v) {
        final CharSequence[] opciones = {"Tomar Foto", "Cargar Imagen", "Cancelar"};
        final AlertDialog.Builder alertopciones = new AlertDialog.Builder(this);
        alertopciones.setTitle("Selecciona una opción");
        alertopciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Tomar Foto")) {
                    tomarFoto();
                } else {
                    if (opciones[i].equals("Cargar Imagen")) {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent, "Selccione la Aplicación"), 10);
                    } else {
                        dialogInterface.dismiss();
                    }
                }
            }
        });
        alertopciones.show();
    }


    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "Backup_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    static final int REQUEST_TAKE_PHOTO = 20;

    private void tomarFoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, "com.example.android.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI.toString());
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO );
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 20:
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    imagen=imageBitmap;
                    cpimagen.setImageBitmap(imageBitmap);
                    break;

                case 10:
                    Uri mipath = data.getData();
                    cpimagen.setImageURI(mipath);
                    cpimagen.buildDrawingCache();
                    Bitmap bmap = cpimagen.getDrawingCache();
                    imagen=bmap;
                    break;
            }

        }

    }

    private void guardarFotoSQLITE(Bitmap bitmap){
        BaseLogin  baseLogin=new BaseLogin(this,"usuarios",null,DATABASE_VERSION);
        SQLiteDatabase BaseDeDatos=baseLogin.getWritableDatabase();
        ContentValues registro=new ContentValues();
        registro.put("foto",getBytes(bitmap));
        BaseDeDatos.update("usuarios",registro,"id=" + id,null);
        BaseDeDatos.close();
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

}