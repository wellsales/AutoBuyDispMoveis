package com.example.autobuydm;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autobuydm.R;

import java.util.ArrayList;

public class CatalogoActivity extends AppCompatActivity {
    DBHelper db;
    private TextView txtusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);
        ListView lista = (ListView) findViewById(R.id.lv_carros);
        final ArrayAdapter adapter = new CarrosAdapter(this, adicionarCarros());
        lista.setAdapter(adapter);
        db = new DBHelper(this);

        Intent it = getIntent();
        final String name = it.getStringExtra("username");
        txtusername = findViewById(R.id.username);
        txtusername.setText(name);

        //AÇAO CLIQUE TELA LISTA ANUNCIO
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //
                Carros obj = (Carros) adapter.getItem(i);
                String value = obj.getNome().toString();
                Intent it = new Intent(CatalogoActivity.this, AnuncioActivity.class);
                it.putExtra("modelo", value);
                it.putExtra("username", name);
                startActivity(it);
            }
        });







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

        } else {
            Toast.makeText(CatalogoActivity.this, "Não tem dados no cursor", Toast.LENGTH_SHORT).show();
        }
        return carros;

    }

}
