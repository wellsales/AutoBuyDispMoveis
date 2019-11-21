package com.example.autobuydm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RotasActivity extends AppCompatActivity {
    Button btnAnuncios, btnAnunciar, btnVoltar, btnExcluirAnuncio;
    TextView username;
    private DBHelper db = new DBHelper(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotas);



        btnAnuncios = findViewById(R.id.btnAnuncios);
        btnAnunciar = findViewById(R.id.btnAnunciar);
        btnVoltar = findViewById(R.id.btnExcluirAnuncio);
        username = findViewById(R.id.username);
        btnExcluirAnuncio = findViewById(R.id.btnExcluirAnuncio);


        Intent it = getIntent();
        final String name = it.getStringExtra("username");
        username.setText(name);





        btnAnuncios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(RotasActivity.this, CatalogoActivity.class);
                it.putExtra("username", name);
                startActivity(it);
            }
        });

        btnAnunciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(RotasActivity.this, CadastroAnuncio.class);
                it.putExtra("username", name);
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


        btnExcluirAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(RotasActivity.this, ExcluirAnuncioActivity.class);
                it.putExtra("username", name);
                startActivity(it);
            }
        });
    }
}
