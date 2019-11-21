package com.example.autobuydm;


import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.autobuydm.R;

import java.util.ArrayList;

public class CatalogoActivity extends AppCompatActivity {
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);
        ListView lista = (ListView) findViewById(R.id.lv_carros);
        ArrayAdapter adapter = new CarrosAdapter(this, adicionarCarros());
        lista.setAdapter(adapter);
        db = new DBHelper(this);
    }

    public ArrayList<Carros> adicionarCarros() {
        ArrayList<Carros> carros = new ArrayList<Carros>();
        db = new DBHelper(this);
        Carros e;

        Cursor cursor = db.SelectAllAnuncio();
        cursor.moveToFirst();
        if (cursor != null) {
            do {
                e = new Carros(cursor.getString(cursor.getColumnIndex("modelo")),
                        "R$ " + cursor.getString(cursor.getColumnIndex("preco")),
                        cursor.getBlob(cursor.getColumnIndex("foto")));
                carros.add(e);
            } while (cursor.moveToNext());
            Toast.makeText(CatalogoActivity.this, "ados no cursor", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CatalogoActivity.this, "Não tem dados no cursor", Toast.LENGTH_SHORT).show();
        }
        return carros;

    }

}
