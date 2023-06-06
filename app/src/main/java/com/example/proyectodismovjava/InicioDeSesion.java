package com.example.proyectodismovjava;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Locale;

public class InicioDeSesion extends AppCompatActivity {

            private TextView appname, Login, TextoBajo,  QueSomosVariable, SubtituloLogin;
            private FirebaseAuth mAuth;
            private Switch mLanguageSwitch;
            private TextInputLayout mEmailLayout;
            private TextInputLayout mPasswordLayout;
            private TextInputEditText mEmailField;
            private TextInputEditText mPasswordField;
            private Button mLoginButton;

    private String[] jefes = {"jjquecas@gmail.com", "jefe2@example.com", "jefe3@example.com", "jefe4@example.com", "jefe5@example.com", "jefe6@example.com"}; // arreglo con los correos de los jefes

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_inicio_de_sesion);

                mAuth = FirebaseAuth.getInstance();
                Login = findViewById(R.id.TituloLogin);
                QueSomosVariable = findViewById(R.id.QueSomos);
                SubtituloLogin = findViewById(R.id.SubTituloEnLogin);
                mEmailLayout = findViewById(R.id.email_layout);
                mPasswordLayout = findViewById(R.id.password_layout);
                mEmailField = findViewById(R.id.email_field);
                mPasswordField = findViewById(R.id.password_field);
                mLoginButton = findViewById(R.id.login_button);
                TextoBajo = findViewById(R.id.textoBajo);


                mLoginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String email = mEmailField.getText().toString();
                        String password = mPasswordField.getText().toString();

                        if (TextUtils.isEmpty(email)) {
                            mEmailLayout.setError("Por favor ingrese su correo electrónico.");
                            return;
                        }

                        if (TextUtils.isEmpty(password)) {
                            mPasswordLayout.setError("Por favor ingrese su contraseña.");
                            return;
                        }


                        mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(InicioDeSesion.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if (user != null && user.isEmailVerified()) {
                                        FirebaseAuth.getInstance().getAccessToken(true);
                                        // Verificar si el correo electrónico pertenece a un administrador
                                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                                        db.collection("administradores")
                                                .whereEqualTo("email", email)
                                                .get()
                                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                        if (task.isSuccessful()) {
                                                            QuerySnapshot querySnapshot = task.getResult();
                                                            if (querySnapshot != null && !querySnapshot.isEmpty()) {
                                                                DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
                                                                if (documentSnapshot.exists()) {
                                                                    // El correo electrónico pertenece a un administrador, abre la actividad de administrador
                                                                    Intent intent = new Intent(InicioDeSesion.this, ActividadDelAdministrador.class);
                                                                    intent.putExtra("user", user.getEmail());
                                                                    startActivity(intent);
                                                                    finish();
                                                                } else {
                                                                    Intent intent = new Intent(InicioDeSesion.this, PantallaPrincipal.class);
                                                                    intent.putExtra("user", user.getEmail());
                                                                    startActivity(intent);
                                                                    finish();
                                                                }
                                                            } else {
                                                                // No se encontraron documentos en la colección

                                                                Intent intent = new Intent(InicioDeSesion.this, PantallaPrincipal.class);
                                                                startActivity(intent);
                                                                finish();
                                                            }
                                                        } else {
                                                            // Manejo de errores


                                                            Intent intent = new Intent(InicioDeSesion.this, PantallaPrincipal.class);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    }
                                                });
                                    } else {
                                        // Correo electrónico no verificado
                                        Toast.makeText(InicioDeSesion.this, "Debe verificar su correo electrónico antes de iniciar sesión.", Toast.LENGTH_SHORT).show();
                                        mAuth.signOut();
                                    }
                                } else {
                                    // Inicio de sesión fallido
                                    Toast.makeText(InicioDeSesion.this, "Inicio de sesión fallido. Verifique su correo electrónico y/o contraseña.", Toast.LENGTH_SHORT).show();
                                    Log.d("InicioDeSesion", "Error al iniciar sesión: ", task.getException());
                                }
                            }

                        });

                    }

                });


                TextoBajo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), AdminInicioDeSesion.class);
                        startActivity(intent);
                    }
                });

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

                    private void setLocale(String lang) {
                        Locale locale = new Locale(lang);
                        Locale.setDefault(locale);
                        Resources res = getResources();
                        Configuration config = res.getConfiguration();
                        config.locale = locale;
                        res.updateConfiguration(config, res.getDisplayMetrics());

                        Login.setText(getString(R.string.Titulo));
                        QueSomosVariable.setText(getString(R.string.QueSomos));
                        SubtituloLogin.setText(getString(R.string.SubTituloDelLogin));
                        mEmailLayout.setHint(getString(R.string.emaillogin));
                        mPasswordLayout.setHint(getString(R.string.ContraLogin));
                        mLoginButton.setText(getString(R.string.InicioSesion));
                        TextoBajo.setText(getString(R.string.AunNoTeR));

                    }
                }