package com.example.proyectolwing;
        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import androidx.annotation.Nullable;

public class BaseLogin extends SQLiteOpenHelper{

    public BaseLogin(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("create table login( numero text primary key, contraseña text, tipocuenta int)");
<<<<<<< HEAD
        BaseDeDatos.execSQL("create table usuarios( id INTEGER primary key autoincrement, numero text, usuario text, descripcion text, fecha text, foto blob, estado int)");
=======
        BaseDeDatos.execSQL("create table usuarios( id INTEGER primary key autoincrement, numero text, usuario text, descripcion text, fecha text, foto blob, estado int, monto int)");
>>>>>>> 346976c... Commit secundario con avances
    }
    //tipo de cuenta 1= administrador
    //tipo de cuenta 0= clinte
    @Override
    public void onUpgrade(SQLiteDatabase BaseDeDatos, int i, int i1) {
        BaseDeDatos.execSQL("DROP TABLE IF EXISTS login");
        BaseDeDatos.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(BaseDeDatos);
    }
}
