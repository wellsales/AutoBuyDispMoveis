package com.example.autobuydm;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.autobuydm.R;


public class CadastroActivity extends AppCompatActivity{

    EditText edtUsuario, edtSenha1, edtSenha2;
    Button btnRegistrar, btnLimpar, btnCancelar;

    DBHelper db;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        db = new DBHelper(this);

        edtUsuario = (EditText)findViewById(R.id.edtCadastraUsuario);
        edtSenha1 = (EditText)findViewById(R.id.edtCadastraSenha);
        edtSenha2 = (EditText)findViewById(R.id.edtCadastraSenha2);

        btnLimpar = (Button)findViewById(R.id.btnLimpar);
        btnRegistrar= (Button)findViewById(R.id.btnRegistrarNovo);
        btnCancelar=(Button)findViewById(R.id.btnCancelar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String username = edtUsuario.getText().toString();
                    String s1 = edtSenha1.getText().toString();
                    String s2 = edtSenha2.getText().toString();

                    if(username.equals("")){
                        Toast.makeText(CadastroActivity.this,"Username nao inserido, tente novamente", Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(CadastroActivity.this, LoginActivity.class);
                    }

                    else if(s1.equals("") || s2.equals("")){
                        Toast.makeText(CadastroActivity.this, "Deve preencher a senha, tente novamente", Toast.LENGTH_SHORT).show();
                    }

                    else if(!s1.equals(s2)){
                        Toast.makeText(CadastroActivity.this, "As duas senhas não correspondem, tente novamente", Toast.LENGTH_SHORT).show();
                    } else {
                        //tudo ok
                        long res = db.CriarUsuario(username, s1);
                        if(res>0) {
                            Toast.makeText(CadastroActivity.this, "Registro OK", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(CadastroActivity.this, "Registro inválido, tente novamente", Toast.LENGTH_SHORT).show();
                        }
                    }




            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AQUI PEGO O TEXTO QUE QUERO APAGAR
                String textouser = edtUsuario.getText().toString();
                String textosenha = edtSenha1.getText().toString();
                String textosenha2 = edtSenha2.getText().toString();

                // AQUI EU FAÇO UM TESTE PARA VER SE O TAMANHO DO TEXTO É MAIOR QUE 0
                if (textouser.length() > 0 || textosenha.length() > 0 || textosenha2.length() > 0) {

                    // AQUI EU PEGO O TAMANHO DO TEXTO - 1 () É O ULTIMO CARACTERE A APAGAR
                    int indice = textouser.length() - textouser.length();
                    int indice1 = textosenha.length() - textosenha.length();
                    int indice2 = textosenha2.length() - textosenha2.length();

                    // AQUI EU SETO UMA SUBSTRING
                    // ( OU SEJA O TEXTO ANTERIRO MENOS O ULTIMO CARACTERE)
                    edtUsuario.setText(textouser.substring(0, indice));
                    edtSenha1.setText(textosenha.substring(0, indice1));
                    edtSenha2.setText(textosenha2.substring(0, indice2));

                    // AQUI EU SETO ONDE DEVE FICAR O CURSOR, NO FINAL DA STRING
                    edtUsuario.setSelection(indice);
                    edtSenha1.setSelection(indice1);
                    edtSenha2.setSelection(indice2);

                    // AQUI EU SETO O CUROR COMO VISIVÉL
                    edtUsuario.setCursorVisible(true);
                    edtSenha1.setCursorVisible(true);
                    edtSenha2.setCursorVisible(true);
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
