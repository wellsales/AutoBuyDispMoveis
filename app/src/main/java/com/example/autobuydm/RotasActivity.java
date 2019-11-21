package com.example.autobuydm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RotasActivity extends AppCompatActivity {
    Button btnAnuncios, btnAnunciar, btnVoltar;
    private DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotas);



        btnAnuncios = findViewById(R.id.btnAnuncios);
        btnAnunciar = findViewById(R.id.btnAnunciar);
        btnVoltar = findViewById(R.id.btnVoltar);



        btnAnuncios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(RotasActivity.this, CatalogoActivity.class);
                startActivity(it);
            }
        });

        btnAnunciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(RotasActivity.this, CadastroAnuncio.class);
                startActivity(it);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(RotasActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });

    }
}
