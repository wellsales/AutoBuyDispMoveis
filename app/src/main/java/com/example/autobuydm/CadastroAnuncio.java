package com.example.autobuydm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autobuydm.R;

import java.io.ByteArrayOutputStream;

public class CadastroAnuncio extends AppCompatActivity {
    private EditText edtMarca, edtModelo, edtPreco;
    private ImageView imgFoto;
    private Button btnTirarFoto, btnCadastrar;
    private Bitmap imgBitmap;
    private final int TIRAR_FOTO = 1;
    private DBHelper db = new DBHelper(this);
    private String CaminhoFoto;
    private String username;
    private TextView txtusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_anuncio);

      db = new DBHelper(this);
        Intent it = getIntent();
          username = it.getStringExtra("username");




        edtMarca = findViewById(R.id.edtMarca);
        edtModelo = findViewById(R.id.edtModelo);
        edtPreco = findViewById(R.id.edtPreco);
        imgFoto = findViewById(R.id.imgFoto);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnTirarFoto = findViewById(R.id.btnTirarFoto);
        txtusername = findViewById(R.id.username);
        txtusername.setText(username);


        btnTirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (it.resolveActivity(getPackageManager()) != null){
                    /*File arqFoto = null;
                    try{
                        arqFoto = criarArquivoImagem();
                    } catch(IOException e){
                    }
                    if(arqFoto != null){
                        Uri fotUri = (Uri) FileProvider.getUriForFile(context, "com.exemple.android.fileProvider", arqFoto);
                        it.putExtra(MediaStore.EXTRA_OUTPUT, fotUri);*/
                        startActivityForResult(it, TIRAR_FOTO);
                    //}
                }
            }
        });



        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgFoto.setImageBitmap(imgBitmap);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imgBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] imgBytes = stream.toByteArray();

                //Por informaçoes de entrada em uma instancia de Anuncio
                Anuncio anuncio = new Anuncio();
                anuncio.setMarca(edtMarca.getText().toString());
                anuncio.setModelo(edtModelo.getText().toString());
                anuncio.setPreco(Double.parseDouble(edtPreco.getText().toString()));
                anuncio.setFoto(imgBytes);
                anuncio.setUsuario(username);

                //Por no bd enviando somente a instancia de Anuncio
                long sucesso = db.criarAnuncio(anuncio);

                if(sucesso != 0){

                    Intent it = new Intent(CadastroAnuncio.this, AnuncioActivity.class);
                    it.putExtra("modelo", edtModelo.getText().toString());
                    it.putExtra("username",username);
                    startActivity(it);
                }
                else{
                    Toast.makeText(CadastroAnuncio.this, "Falha ao inserir no BD", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent it){
        super.onActivityResult(requestCode ,resultCode, it);
        if (requestCode == TIRAR_FOTO && resultCode == RESULT_OK){
            imgBitmap = (Bitmap) it.getExtras().get("data");

            imgFoto.setImageBitmap(imgBitmap);
        }
    }

    /*private File criarArquivoImagem() throws IOException {
        String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        
        String imgFileName = "PNG_" + time + "_";
        File Diretorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File img = File.createTempFile(
            imgFileName,
            ".png",
            Diretorio
        );
        CaminhoFoto = img.getAbsolutePath();
        return img;
    }
    */
}
