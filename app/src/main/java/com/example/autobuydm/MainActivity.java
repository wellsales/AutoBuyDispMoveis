package com.example.autobuydm;


import androidx.appcompat.app.AppCompatActivity;

                import android.content.Intent;
                import android.os.Bundle;
                import android.view.View;
                import android.widget.Button;

                import com.example.autobuydm.R;

public class MainActivity extends AppCompatActivity {

    Button btnEntrar, btnRegistrar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnRegistrar = (Button)findViewById(R.id.btnRegistrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(i);
            }
        });


    }
}