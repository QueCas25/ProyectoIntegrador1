package com.example.proyectodismovjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.barteksc.pdfviewer.PDFView;

public class ManualCO2 extends AppCompatActivity {

    int sies = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_co2);
        Button Regresar = findViewById(R.id.btnRgresar);
        int adminVariable = getIntent().getIntExtra("admin", 0);

        PDFView pdfView = findViewById(R.id.pdfCO2);

        pdfView.fromAsset("hs_extintor_co2_2015.pdf").load();

        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adminVariable == sies) {
                    // El correo electronic pertenece a un administrador, abre la actividad de administrador
                    Intent intent = new Intent(ManualCO2.this, ManualesPDF.class);
                    startActivity(intent);
                    finish();
                } else {
                    // El correo electrónico no pertenece a un administrador, regresa a la actividad normal
                    Intent intent = new Intent(ManualCO2.this, ManualesPDF.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}