package com.example.proyectodismovjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Carrito extends AppCompatActivity {
    private List<String> lista = new ArrayList<>();
    private ArrayAdapter<String> adaptador;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        listView = findViewById(R.id.carrListView);

        List<String> lista = new ArrayList<>();

        String productName = getIntent().getStringExtra("Nombre");
        lista.add(productName);

        adaptador = new ArrayAdapter<>(this, R.layout.item_carrito,lista);

        listView.setAdapter(adaptador);

    }
}