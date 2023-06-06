package com.example.proyectodismovjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ServiciosActivity extends AppCompatActivity {
    int sies = 1;
    private Button Regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios2);
        Regresar = findViewById(R.id.booton);

        int adminVariable = getIntent().getIntExtra("admin", 0);
        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adminVariable == sies) {
                    // El correo electronic pertenece a un administrador, abre la actividad de administrador
                    Intent intent = new Intent(ServiciosActivity.this, ActividadDelAdministrador.class);
                    startActivity(intent);
                    finish();
                } else {
                    // El correo electrónico no pertenece a un administrador, regresa a la actividad normal
                    Intent intent = new Intent(ServiciosActivity.this, PantallaPrincipal.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}