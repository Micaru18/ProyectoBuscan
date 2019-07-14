package com.example.proyectobuscan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "MASCOTA";

    public static final String NOMBRE = "nombre";
    public static final String DESCRIPCION = "descripcion";
    public static final String IMAGEN = "imagen";


    public static final String TABLE_NAME1 = "REGISTRO";

    public static final String NOMBRES = "nombre";
    public static final String APELLIDOS = "apellidos";
    public static final String NOMBREUSUARIO = "usuario";
    public static final String CLAVE = "clave";
    public static final String CORREO = "correo";
    public static final String CELULAR = "celular";
    public static final String IMGFOTO = "imgfoto";

    public static final String TABLE_NAME2 = "USUARIO";

    public static final String USUARIO = "usuario";
    public static final String CLAVE1 = "clave1";


    static final String DB_NAME = "PROYECTOBUSCAN.DB";

    static final int DB_VERSION = 7;


    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
            + NOMBRE + " TEXT NOT NULL, "
            + DESCRIPCION + " TEXT NOT NULL, "
            + IMAGEN + " BLOB NOT NULL);";

    private static final String CREATE_TABLE1 = "create table " + TABLE_NAME1 + "("
            + NOMBRES + " TEXT NOT NULL, "
            + APELLIDOS + " TEXT NOT NULL, "
            + NOMBREUSUARIO + " TEXT NOT NULL, "
            + CLAVE + " TEXT NOT NULL, "
            + CORREO + " TEXT NOT NULL, "
            + CELULAR + " TEXT NOT NULL, "
            + IMGFOTO + " BLOB NOT NULL);";

    private static final String CREATE_TABLE2 = "create table " + TABLE_NAME2 + "("
            + USUARIO + " TEXT NOT NULL, "
            + CLAVE1 + " TEXT NOT NULL);";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE1);
        db.execSQL(CREATE_TABLE2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);

    }
}
