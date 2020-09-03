package com.example.proyectolwing;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BuscarAd extends AppCompatActivity {

    private static final int DATABASE_VERSION = 4;

    ArrayList<Usuarios> listDatos;
    RecyclerView recyclerView;
    ViewAnimator viewAnimator;

    Button buscar;
    EditText searchus;

    ImageView epimagen;

    EditText epUsuario;
    TextView epIdProyecto;
    EditText epDescripcion;
    EditText epFecha;
    EditText epNumero;

    Button eprecibido;
    Button epproceso;
    Button epterminado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_ad);

        recyclerView=(RecyclerView)findViewById(R.id.RecylerAd);
        viewAnimator=(ViewAnimator)findViewById(R.id.viewAnimator2);

        epUsuario=(EditText)findViewById(R.id.EPUsuario);
        epIdProyecto=(TextView) findViewById(R.id.EPIdProyecto);
        epDescripcion=(EditText)findViewById(R.id.EPDescripcion);
        epFecha=(EditText)findViewById(R.id.EPFecha);
        epNumero=(EditText) findViewById(R.id.EPNumero);
        epimagen=(ImageView)findViewById(R.id.EPImagen);

        buscar=(Button)findViewById(R.id.Buscar);
        searchus=(EditText)findViewById(R.id.SearchUs);

        eprecibido=(Button)findViewById(R.id.EPrecibido);
        epproceso=(Button)findViewById(R.id.EPProceso);
        epterminado=(Button)findViewById(R.id.EPTerminado);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        listDatos=new ArrayList<>();
        llenarUsuarios();
        }

        private  void verificarestado(){
            String id=epIdProyecto.getText().toString().substring(3);
            int valor=0;
            BaseLogin  baseLogin=new BaseLogin(this,"usuarios",null,DATABASE_VERSION);
            SQLiteDatabase BaseDeDatos=baseLogin.getReadableDatabase();
            Cursor fila = BaseDeDatos.rawQuery("select estado from usuarios where id='" + id + "'", null);
            fila.moveToFirst();
            valor=fila.getInt(0);
            switch (valor){
                case 1:
                    eprecibido.setBackgroundDrawable(getResources().getDrawable(R.drawable.boton_rounded2));
                    epproceso.setBackgroundDrawable(getResources().getDrawable(R.drawable.botonapagado));
                    epterminado.setBackgroundDrawable(getResources().getDrawable(R.drawable.botonapagado));
                    break;

                case 2:
                    eprecibido.setBackgroundDrawable(getResources().getDrawable(R.drawable.botonapagado));
                    epproceso.setBackgroundDrawable(getResources().getDrawable(R.drawable.boton_rounded3));
                    epterminado.setBackgroundDrawable(getResources().getDrawable(R.drawable.botonapagado));
                    break;

                case 3:
                    eprecibido.setBackgroundDrawable(getResources().getDrawable(R.drawable.botonapagado));
                    epproceso.setBackgroundDrawable(getResources().getDrawable(R.drawable.botonapagado));
                    epterminado.setBackgroundDrawable(getResources().getDrawable(R.drawable.boton_rounded4));
                    break;
            }
            BaseDeDatos.close();
        }

        //Metodo para mostrar la base de datos en recycleview
    private void llenarUsuarios() {

        BaseLogin  baseLogin=new BaseLogin(this,"usuarios",null,DATABASE_VERSION);
        SQLiteDatabase BaseDeDatos=baseLogin.getReadableDatabase();
        Cursor fila=BaseDeDatos.rawQuery("select * from usuarios",null);

        while(fila.moveToNext()){
           Usuarios  usuario=new Usuarios(null,null,null,null,null,null);
            usuario.setNombre(fila.getString(2));
            usuario.setIdProyecto(fila.getString(0));
            usuario.setDescripcion(fila.getString(3));
            usuario.setFecha(fila.getString(4));
            usuario.setNumero(fila.getString(1));
            usuario.setFoto(getImage(fila.getBlob(5)));
            listDatos.add(usuario);
        }
        Adaptador adaptador=new Adaptador(listDatos);
        recyclerView.setAdapter(adaptador);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAnimator.showNext();
                epUsuario.setText(listDatos.get(recyclerView.getChildAdapterPosition(view)).getNombre());
                epIdProyecto.setText("ID: "+listDatos.get(recyclerView.getChildAdapterPosition(view)).getIdProyecto());
                epDescripcion.setText(listDatos.get(recyclerView.getChildAdapterPosition(view)).getDescripcion());
                epFecha.setText(listDatos.get(recyclerView.getChildAdapterPosition(view)).getFecha());
                epNumero.setText(listDatos.get(recyclerView.getChildAdapterPosition(view)).getNumero());
                epimagen.setImageBitmap(listDatos.get(recyclerView.getChildAdapterPosition(view)).getFoto());
                verificarestado();
            }
        });
        BaseDeDatos.close();
    }

    //Metodo para modifcar base de datos
    public void EditarDatos(View v){
        BaseLogin  baseLogin=new BaseLogin(this,"usuarios",null,DATABASE_VERSION);
        SQLiteDatabase BaseDeDatos=baseLogin.getWritableDatabase();
        String usuario=epUsuario.getText().toString();
        String id=epIdProyecto.getText().toString().substring(3);
        String descripcion=epDescripcion.getText().toString();
        String fecha=epFecha.getText().toString();
        String numero=epNumero.getText().toString();
        ContentValues registro=new ContentValues();
        registro.put("usuario",usuario);
        registro.put("descripcion",descripcion);
        registro.put("fecha",fecha);
        registro.put("numero",numero);

       BaseDeDatos.update("usuarios",registro,"id=" + id,null);
       BaseDeDatos.close();
        listDatos.clear();
       Toast.makeText(getApplicationContext(),"Proyecto modificado correctamente",Toast.LENGTH_SHORT).show();
       llenarUsuarios();
        viewAnimator.showPrevious();
    }

    //Metodo para cargar foto
    public void CambiarFoto(View v){
        CargaImagen();
    }

    private void CargaImagen() {
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


    //Metodo para buscar por usuario
    public void BPBuscar(View v){
        listDatos.clear();
        String bpbusuario=searchus.getText().toString();
        BaseLogin  baseLogin=new BaseLogin(this,"usuarios",null,DATABASE_VERSION);
        SQLiteDatabase BaseDeDatos=baseLogin.getReadableDatabase();
        Cursor fila=BaseDeDatos.rawQuery("select * from usuarios",null);

        while(fila.moveToNext()){
            Usuarios  usuario=new Usuarios(null,null,null,null,null,null);
            usuario.setNombre(fila.getString(2));
            if(usuario.getNombre().equals(bpbusuario)||bpbusuario.equals("")) {
                usuario.setIdProyecto(fila.getString(0));
                usuario.setDescripcion(fila.getString(3));
                usuario.setFecha(fila.getString(4));
                usuario.setNumero(fila.getString(1));
                usuario.setFoto(getImage(fila.getBlob(5)));
                listDatos.add(usuario);
            }
        }
        BaseDeDatos.close();
        Adaptador adaptador=new Adaptador(listDatos);
        recyclerView.setAdapter(adaptador);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAnimator.showNext();
                epUsuario.setText(listDatos.get(recyclerView.getChildAdapterPosition(view)).getNombre());
                epIdProyecto.setText("ID: "+listDatos.get(recyclerView.getChildAdapterPosition(view)).getIdProyecto());
                epDescripcion.setText(listDatos.get(recyclerView.getChildAdapterPosition(view)).getDescripcion());
                epFecha.setText(listDatos.get(recyclerView.getChildAdapterPosition(view)).getFecha());
                epNumero.setText(listDatos.get(recyclerView.getChildAdapterPosition(view)).getNumero());
                epimagen.setImageBitmap(listDatos.get(recyclerView.getChildAdapterPosition(view)).getFoto());
            }
        });
    }

    public void EliminarDatos(View v) {
        BaseLogin  baseLogin=new BaseLogin(this,"usuarios",null,DATABASE_VERSION);
        final SQLiteDatabase BaseDeDatos=baseLogin.getWritableDatabase();
        final CharSequence[] opciones = {"Eliminar", "Cancelar"};
        final AlertDialog.Builder alertopciones = new AlertDialog.Builder(this);
        alertopciones.setTitle("Esta seguro de eliminar");
        alertopciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Eliminar")) {
                    String id=epIdProyecto.getText().toString().substring(3);
                    BaseDeDatos.delete("usuarios","id="+id,null);
                    BaseDeDatos.close();
                    listDatos.clear();
                    Toast.makeText(getApplicationContext(),"Proyecto eliminado",Toast.LENGTH_SHORT).show();
                    llenarUsuarios();
                    viewAnimator.showPrevious();
                } else {
                        dialogInterface.dismiss();
                    }
            }
        });
        alertopciones.show();
    }



//Proceso de imagenes
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
                    guardarFotoSQLITE(imageBitmap);
                    epimagen.setImageBitmap(imageBitmap);
                    break;

                case 10:
                    Uri mipath = data.getData();
                    epimagen.setImageURI(mipath);
                    epimagen.buildDrawingCache();
                    Bitmap bmap = epimagen.getDrawingCache();
                    guardarFotoSQLITE(bmap);
                    break;
            }
        }
    }

    private void guardarFotoSQLITE(Bitmap bitmap){
        String id=epIdProyecto.getText().toString().substring(3);
        BaseLogin  baseLogin=new BaseLogin(this,"usuarios",null,DATABASE_VERSION);
        SQLiteDatabase BaseDeDatos=baseLogin.getWritableDatabase();
        ContentValues registro=new ContentValues();
        registro.put("foto",getBytes(bitmap));
        BaseDeDatos.update("usuarios",registro,"id=" + id,null);
        BaseDeDatos.close();
    }

        // convert from bitmap to byte array
        public static byte[] getBytes(Bitmap bitmap) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
            return stream.toByteArray();
        }

        // convert from byte array to bitmap
        public static Bitmap getImage(byte[] image) {
            return BitmapFactory.decodeByteArray(image, 0, image.length);
        }

    public void Recibido(View v){
        int valor=1;
        String id=epIdProyecto.getText().toString().substring(3);
        BaseLogin  baseLogin=new BaseLogin(this,"usuarios",null,DATABASE_VERSION);
        SQLiteDatabase BaseDeDatos=baseLogin.getWritableDatabase();
        ContentValues registro=new ContentValues();
        registro.put("estado",valor);
        BaseDeDatos.update("usuarios",registro,"id=" + id,null);
        BaseDeDatos.close();
        verificarestado();
    }

    public void Proceso(View v){
        int valor=2;
        String id=epIdProyecto.getText().toString().substring(3);
        BaseLogin  baseLogin=new BaseLogin(this,"usuarios",null,DATABASE_VERSION);
        SQLiteDatabase BaseDeDatos=baseLogin.getWritableDatabase();
        ContentValues registro=new ContentValues();
        registro.put("estado",valor);
        BaseDeDatos.update("usuarios",registro,"id=" + id,null);
        BaseDeDatos.close();
        verificarestado();
    }
    public void Terminado(View v){
        int valor=3;
        String id=epIdProyecto.getText().toString().substring(3);
        BaseLogin  baseLogin=new BaseLogin(this,"usuarios",null,DATABASE_VERSION);
        SQLiteDatabase BaseDeDatos=baseLogin.getWritableDatabase();
        ContentValues registro=new ContentValues();
        registro.put("estado",valor);
        BaseDeDatos.update("usuarios",registro,"id=" + id,null);
        BaseDeDatos.close();
        verificarestado();
    }
}

