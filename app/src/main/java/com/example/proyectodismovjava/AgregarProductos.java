package com.example.proyectodismovjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarProductos extends AppCompatActivity {

    private EditText productNameEditText;
    private EditText productTypeEditText;
    private EditText productPriceEditText;
    private Button agregarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_productos);

        productNameEditText = findViewById(R.id.productNameEditText);
        productTypeEditText = findViewById(R.id.productTypeEditText);
        productPriceEditText = findViewById(R.id.productPriceEditText);
        agregarButton = findViewById(R.id.agregarButton);

        agregarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los datos ingresados por el usuario
                String productName = productNameEditText.getText().toString();
                String productType = productTypeEditText.getText().toString();
                String productPrice = productPriceEditText.getText().toString();

                // Validar que se haya ingresado un nombre de producto
                if (TextUtils.isEmpty(productName)) {
                    Toast.makeText(AgregarProductos.this, "Ingrese un nombre de producto", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Crear un intent para devolver los datos del producto agregado a la actividad principal
                Intent resultIntent = new Intent();
                resultIntent.putExtra("PRODUCT_NAME", productName);
                resultIntent.putExtra("PRODUCT_TYPE", productType);
                resultIntent.putExtra("PRODUCT_PRICE", productPrice);
                resultIntent.putExtra("PRODUCT_IMAGE", R.drawable.default_product_image);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}