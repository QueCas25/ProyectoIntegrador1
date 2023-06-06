package com.example.proyectodismovjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManualesPDF extends AppCompatActivity {

    int sies = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manuales_pdf);

        Button ManualPQS = findViewById(R.id.btnExtintorPQS);
        Button ManualCO2 = findViewById(R.id.btnExtintorCO2);
        Button ManualADP = findViewById(R.id.btnExtintorADP);
        Button Regresar = findViewById(R.id.btnaTRS);

        int adminVariable = getIntent().getIntExtra("admin", 0);

        ManualPQS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adminVariable == sies) {
                    // El correo electronic pertenece a un administrador, abre la actividad de administrador
                    Intent intent = new Intent(ManualesPDF.this, ManualPQS.class);
                    intent.putExtra("admin", 1);
                    startActivity(intent);
                    finish();
                } else {
                    // El correo electrónico no pertenece a un administrador, regresa a la actividad normal
                    Intent intent = new Intent(ManualesPDF.this, ManualPQS.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        ManualCO2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adminVariable == sies) {
                    // El correo electronic pertenece a un administrador, abre la actividad de administrador
                    Intent intent = new Intent(ManualesPDF.this, ManualCO2.class);
                    intent.putExtra("admin", 1);
                    startActivity(intent);
                    finish();
                } else {
                    // El correo electrónico no pertenece a un administrador, regresa a la actividad normal
                    Intent intent = new Intent(ManualesPDF.this, ManualCO2.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        ManualADP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adminVariable == sies) {
                    // El correo electronic pertenece a un administrador, abre la actividad de administrador
                    Intent intent = new Intent(ManualesPDF.this, ManualADP.class);
                    intent.putExtra("admin", 1);
                    startActivity(intent);
                    finish();
                } else {
                    // El correo electrónico no pertenece a un administrador, regresa a la actividad normal
                    Intent intent = new Intent(ManualesPDF.this, ManualADP.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adminVariable == sies) {
                    // El correo electronic pertenece a un administrador, abre la actividad de administrador
                    Intent intent = new Intent(ManualesPDF.this, ExtintoresPQS.class);
                    startActivity(intent);
                    finish();
                } else {
                    // El correo electrónico no pertenece a un administrador, regresa a la actividad normal
                    Intent intent = new Intent(ManualesPDF.this, ExtintoresPQS.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}