package com.example.proyectodismovjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ExtintoresStock extends AppCompatActivity {

    ExtintoresModel selectedExtintor;
    private Button Regresar, EnviarCarro;

    int sies = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extintores_stock);

        int adminVariable = getIntent().getIntExtra("admin", 0);
        Regresar = findViewById(R.id.PQS_BBotonRegresar2);
        EnviarCarro = findViewById(R.id.BtnEnviarCarro);

        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adminVariable == sies) {
                    // El correo electronic pertenece a un administrador, abre la actividad de administrador
                    Intent intent = new Intent(ExtintoresStock.this, ExtintoresPQS.class);
                    intent.putExtra("admin", 1);
                    startActivity(intent);
                    finish();
                } else {
                    // El correo electrónico no pertenece a un administrador, regresa a la actividad normal
                    Intent intent = new Intent(ExtintoresStock.this, ExtintoresPQS.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        TextView tvName = (TextView)findViewById(R.id.NNAME);
        ImageView imageView = (ImageView) findViewById(R.id.IImage);
        TextView tvPrice = (TextView) findViewById(R.id.PPRICE);

        Bundle bundle = getIntent().getExtras();

        imageView.setImageResource(bundle.getInt("IMAGE"));
        tvName.setText(bundle.getString("NAME"));
        tvPrice.setText(bundle.getString("PRICE"));

        String name = tvName.getText().toString();

        EnviarCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExtintoresStock.this,Carrito.class);
                intent.putExtra("Nombre",name);
                startActivity(intent);
            }
        });
    }


}