package com.example.autobuydm;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AnuncioActivity extends AppCompatActivity {
    TextView txtMarca, txtModelo, txtPreco, txtusername;
    ImageView imgView;
    Button btnVoltar;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncio);

        Intent it = getIntent();
        final String name = it.getStringExtra("username");

        txtMarca = findViewById(R.id.txtMarca);
        txtModelo = findViewById(R.id.txtModelo);
        txtPreco = findViewById(R.id.txtPreco);
        imgView = findViewById(R.id.imageView);
        btnVoltar = findViewById(R.id.btnExcluirAnuncio);
        txtusername = findViewById(R.id.username);
        txtusername.setText(name);




        db = new DBHelper(this);

        String modelo = it.getStringExtra("modelo");

        Cursor cursor = db.SelectByAnuncio(modelo);
        cursor.moveToFirst();
        txtMarca.setText(cursor.getString(cursor.getColumnIndex("marca")));
        txtModelo.setText(cursor.getString(cursor.getColumnIndex("modelo")));
        txtPreco.setText(cursor.getString(cursor.getColumnIndex("preco")));

        byte[] imgByte = cursor.getBlob(cursor.getColumnIndex("foto"));
        Bitmap bt = BitmapFactory.decodeByteArray(imgByte, 0 , imgByte.length);
        imgView.setImageBitmap(bt);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(AnuncioActivity.this, CatalogoActivity.class);
                it.putExtra("username", name);
                startActivity(it);
            }
        });


    }
}
