package proyect.my.of.example.miyuki.myapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    DataBaseManager dataBaseManager;
    EditText nombre, telefono;
    Button insert, del, Updatetel, buscar, cargar;
    Cursor cursor;
    ListView listView;
    SimpleCursorAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBaseManager = new DataBaseManager(this);

        nombre = findViewById(R.id.editTextnombre);
        telefono = findViewById(R.id.editTexttel);

        insert = findViewById(R.id.insert);
        del = findViewById(R.id.del);
        buscar = findViewById(R.id.buscar);
        cargar = findViewById(R.id.reload);
        Updatetel = findViewById(R.id.UpdTel);


        insert.setOnClickListener(this);
        del.setOnClickListener(this);
        buscar.setOnClickListener(this);
        cargar.setOnClickListener(this);
        Updatetel.setOnClickListener(this);

        clean();


        listView = findViewById(R.id.listViewID);

        cursor = dataBaseManager.cargarCursorContactos();


        //  dataBaseManager.insertar("sara","123456");

        String[] from = new String[]{dataBaseManager.CN_NAME, dataBaseManager.CN_PHONE};
        int[] to = new int[]{android.R.id.text1, android.R.id.text2};
        adapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, cursor, from, to, 0);

        listView.setAdapter(adapter);
    }

    public void clean() {
        nombre.setText("");
        telefono.setText("");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.insert:

                dataBaseManager.insertar(nombre.getText().toString(), telefono.getText().toString());
                clean();
                break;

            case R.id.del:

                dataBaseManager.delete(nombre.getText().toString());
                clean();
                break;
            case R.id.UpdTel:
                dataBaseManager.updateTel(nombre.getText().toString(), telefono.getText().toString());
                clean();
                break;


            case R.id.buscar:
                cursor = dataBaseManager.buscarContacto(nombre.getText().toString());
                adapter.changeCursor(cursor);
                clean();
                break;

            case R.id.reload:
                cursor = dataBaseManager.cargarCursorContactos();
                adapter.changeCursor(cursor);

                clean();
                break;


        }

    }
}
