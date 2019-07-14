package com.example.proyectobuscan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class DBManager {
    static private DatabaseHelper dbHelper;
    static private SQLiteDatabase database;
    private Context context;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public ArrayList<Integer> getData(String sql) {
        Cursor cursor;
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        this.open();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            arrayList.add(cursor.getInt(0));
        }
        this.close();
        return arrayList;
    }

    public void insertMascota(String nombre, String descripcion, byte[] imagen) {
        this.open();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NOMBRE, nombre);
        contentValue.put(DatabaseHelper.DESCRIPCION, descripcion);
        contentValue.put(DatabaseHelper.IMAGEN, imagen);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
        this.close();
    }

    public ArrayList<Mascota> fetch() {
        this.open();
        ArrayList<Mascota> arrayList = new ArrayList<>();
        String[] columns = new String[]{DatabaseHelper.NOMBRE,
                DatabaseHelper.DESCRIPCION, DatabaseHelper.IMAGEN};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns,
                null, null
                , null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                String nombre = cursor.getString(0);
                String descripcion = cursor.getString(1);
                byte[] imagen = cursor.getBlob(2);

                arrayList.add(new Mascota(nombre, descripcion, imagen));
            }
            this.close();

        }

        return arrayList;
    }

    public Usuario validarSesion(String usuario, String contrasena) {
        this.open();
        Usuario usu = null;

            String[] columnas = new String[]{
                    //aqui colocas las columnas que necesitas consultar
                    DatabaseHelper.NOMBRES, DatabaseHelper.APELLIDOS, DatabaseHelper.NOMBREUSUARIO,
                    DatabaseHelper.CLAVE, DatabaseHelper.CORREO, DatabaseHelper.CELULAR, DatabaseHelper.IMGFOTO};

        ;
        String selection = DatabaseHelper.NOMBREUSUARIO + "= ? AND " + DatabaseHelper.CLAVE + "= ?";

        String [] args = {usuario, contrasena};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME1, columnas, selection, args, null, null, null);

        if (cursor != null)
        {
            cursor.moveToFirst();
            if (cursor.moveToNext())
            {
                String nombre = cursor.getString(0);
                String apellidos = cursor.getString(1);
                String nombreusuario = cursor.getString(2);
                String clave = cursor.getString(3);
                String correo = cursor.getString(4);
                String celular = cursor.getString(5);

                usu = new Usuario(nombre, apellidos, nombreusuario, clave, correo, celular);
            }
        }
        return usu;
    }


        public void update(String nombre, String descripcion, byte[] imagen){
            this.open();
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseHelper.NOMBRE, nombre);
            contentValues.put(DatabaseHelper.DESCRIPCION, descripcion);
            contentValues.put(DatabaseHelper.IMAGEN, imagen);

            int i = database.update(DatabaseHelper.TABLE_NAME
                    , contentValues,
                    null,
                    null);
            this.close();

        }

    public void insertUsuario(String nombre, String apellidos, String usuario, String clave,
                              String correo, String celular, byte[] imgfoto) {
        this.open();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NOMBRES, nombre);
        contentValue.put(DatabaseHelper.APELLIDOS, apellidos);
        contentValue.put(DatabaseHelper.NOMBREUSUARIO, usuario);
        contentValue.put(DatabaseHelper.CLAVE, clave);
        contentValue.put(DatabaseHelper.CORREO, correo);
        contentValue.put(DatabaseHelper.CELULAR, celular);
        contentValue.put(DatabaseHelper.IMGFOTO, imgfoto);
        database.insert(DatabaseHelper.TABLE_NAME1, null, contentValue);
        this.close();
    }
}


