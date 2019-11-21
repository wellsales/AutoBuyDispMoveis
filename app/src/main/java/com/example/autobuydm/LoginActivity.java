package com.example.autobuydm;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.autobuydm.R;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsuario, edtSenha;
    Button btnLogin, btnCancelar;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent i = getIntent();
        db = new DBHelper(this);

        edtUsuario = (EditText)findViewById(R.id.edtUsuario);
        edtSenha = (EditText)findViewById(R.id.edtSenha);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnCancelar = (Button)findViewById(R.id.btnCancelar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsuario.getText().toString();
                String senha = edtSenha.getText().toString();


                if(username.equals("")) {
                    Toast.makeText(LoginActivity.this, "Username nao inserido, tente novamente", Toast.LENGTH_SHORT).show();
                } else if (senha.equals("")) {
                    Toast.makeText(LoginActivity.this, "Senha nao inserida, tente novamente", Toast.LENGTH_SHORT).show();
                }else {
                    //tudo OK
                    String res = db.ValidarLogin(username, senha);

                    if(res.equals("OK")) {
                        Toast.makeText(LoginActivity.this, "Login OK", Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(LoginActivity.this, RotasActivity.class);
                        it.putExtra("username", username);
                        startActivity(it);

                    }else {
                        Toast.makeText(LoginActivity.this, "Login errado, tente novamente", Toast.LENGTH_SHORT).show();

                    }

                   
                }
            }
        });




        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });



    }
}
