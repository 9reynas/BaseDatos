package proyect.my.of.example.miyuki.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hazexirs on 01/04/2018.
 */

public class DBhelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "contactos.sqlite";
    private static final int DB_SHEME_VERSION = 1;


    public DBhelper(Context context) {
        super(context, DB_NAME, null, DB_SHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseManager.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists contactos");
        onCreate(db);
    }
}
