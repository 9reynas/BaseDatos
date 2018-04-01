package proyect.my.of.example.miyuki.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Hazexirs on 01/04/2018.
 */

public class DataBaseManager {

    public static final String TABLE_NAME = "contactos";


    public static final String CN_ID = "_id";
    public static final String CN_NAME = "nombre";
    public static final String CN_PHONE = "telefono";

    public static final String CREATE_TABLE = "create table "
            + TABLE_NAME + " (" + CN_ID + " integer primary key autoincrement, "
            + CN_NAME + " text not null,"
            + CN_PHONE + " text);";


    DBhelper helper;
    SQLiteDatabase db;


    public DataBaseManager(Context context) {
        helper = new DBhelper(context);
        db = helper.getWritableDatabase();
    }

    public void insertar(String nombre, String telefono) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CN_NAME, nombre);
        contentValues.put(CN_PHONE, telefono);

        db.insert(TABLE_NAME, null, contentValues);
    }

    public void delete(String nombre) {

        db.delete(TABLE_NAME, CN_NAME + "=?", new String[]{nombre});


    }
 public  void updateTel(String nombre,String telefono) {

     ContentValues contentValues = new ContentValues();
     contentValues.put(CN_NAME, nombre);
     contentValues.put(CN_PHONE, telefono);
     db.update(TABLE_NAME, contentValues, CN_NAME + "=?", new String[]{nombre});

 }
 public Cursor cargarCursorContactos(){

     String[] columnas = new String[]{CN_ID, CN_NAME, CN_PHONE};
     return db.query(TABLE_NAME, columnas, null, null, null, null, null);
 }

 public Cursor buscarContacto(String nombre){

     String[] columnas = new String[]{CN_ID, CN_NAME, CN_PHONE};
     return db.query(TABLE_NAME, columnas, CN_NAME + "=?", new String[]{nombre}, null, null, null);
 }



}
