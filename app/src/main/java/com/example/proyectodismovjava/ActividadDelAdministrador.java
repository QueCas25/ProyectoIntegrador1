package com.example.proyectodismovjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import com.example.proyectodismovjava.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Locale;

public class ActividadDelAdministrador extends AppCompatActivity {
    FirebaseAuth auth;
    int admin;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private Switch mLanguageSwitch;
    private Button ServiciosBtn, Productos, Contacto, Perfil, Chat, Videollamada, CerrarSesion;
    String username;
    private TextView Administrador, Titulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_actividad_del_administrador);
        auth = FirebaseAuth.getInstance();
        Administrador  = findViewById(R.id.TituloMenuAdmin);
        ServiciosBtn = findViewById(R.id.botonServiciosAdmin);
        Productos = findViewById(R.id.botonProductosAdmin);
        Contacto = findViewById(R.id.botonContactoAdmin);
        Perfil = findViewById(R.id.botonPerfilAdmin);
        Chat = findViewById(R.id.botonChatAdmin);
        Videollamada = findViewById(R.id.botonVideollamadaAdmin);
        CerrarSesion = findViewById(R.id.botonCerrarSesionAdmin);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users");
        username = auth.getCurrentUser().getEmail().toString().split("@")[0];
        /*Comienzan funciones al aplanar botones*/
        ServiciosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActividadDelAdministrador.this,ServiciosActivity.class);
                intent.putExtra("admin", 1);
                startActivity(intent);
            }
        });

        Productos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActividadDelAdministrador.this, ExtintoresPQS.class);
                intent.putExtra("admin", 1);
                startActivity(intent);
            }
        });

        Contacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActividadDelAdministrador.this, ActividadContacto.class);
                intent.putExtra("admin", 1);
                startActivity(intent);
            }
        });

        Perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActividadDelAdministrador.this, CrearAdmins.class);
                intent.putExtra("admin", 1);
                startActivity(intent);
            }
        });

        Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActividadDelAdministrador.this, UsersActivity.class);
                intent.putExtra("admin", 1);
                startActivity(intent);
            }
        });

        Videollamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActividadDelAdministrador.this, videocall.class);
                intent.putExtra("admin", 1);
                startActivity(intent);
            }
        });

        CerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActividadDelAdministrador.this, InicioDeSesion.class);
                startActivity(intent);
            }
        });

        /*Terminan funciones al aplanar botones*/


            /*Switch para Cambio de idioma */
            mLanguageSwitch = findViewById(R.id.language_switch);
            mLanguageSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        setLocale("en");
                    } else {
                        setLocale("es");
                    }
                }
            });

    }

            /* Funcion de Cambio de idioma */
            private void setLocale(String lang) {
                Locale locale = new Locale(lang);
                Locale.setDefault(locale);
                Resources res = getResources();
                Configuration config = res.getConfiguration();
                config.locale = locale;
                res.updateConfiguration(config, res.getDisplayMetrics());
                Administrador.setText(res.getString(R.string.TituloAdminEnMenu));
                ServiciosBtn.setText(res.getString(R.string.btnServicios));
                Productos.setText(res.getString(R.string.BotonProductos));
                Contacto.setText(res.getString(R.string.btnContacto));
                Perfil.setText(res.getString(R.string.btnPerfil));
                Chat.setText(getString(R.string.BotonChat));
                Videollamada.setText(getString(R.string.BotonVideoLlamada));
                CerrarSesion.setText(getString(R.string.BotonRegresar));

            }

}