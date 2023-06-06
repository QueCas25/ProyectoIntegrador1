package com.example.proyectodismovjava;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrearAdmins extends AppCompatActivity {

    private ListView userListView;
    private List<String> userEmailList;
    private ArrayAdapter<String> adapter;

    private FirebaseFirestore db;
    private CollectionReference usersCollection;
    private CollectionReference adminsCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_admins);

        // Inicializar Firebase
        db = FirebaseFirestore.getInstance();
        usersCollection = db.collection("users");
        adminsCollection = db.collection("admins");

        // Inicializar lista de correos electrónicos de usuarios
        userEmailList = new ArrayList<>();

        // Configurar adaptador para la lista de usuarios
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userEmailList);

        // Configurar ListView
        userListView = findViewById(R.id.userListView);
        userListView.setAdapter(adapter);

        // Obtener datos de la colección 'users' de Firebase
        getUsersFromFirebase();

        // Manejar clics en la lista de usuarios
        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el correo electrónico del usuario seleccionado
                String userEmail = userEmailList.get(position);

                // Verificar si el usuario ya es un administrador
                if (isUserAdmin(userEmail)) {
                    Toast.makeText(CrearAdmins.this, "El usuario ya es un administrador.", Toast.LENGTH_SHORT).show();
                } else {
                    // Agregar usuario a la colección 'admins' de Firebase
                    addUserToAdmins(userEmail);

                    // Eliminar el correo electrónico de la lista y actualizar la vista
                    userEmailList.remove(position);
                    adapter.notifyDataSetChanged();

                    // Eliminar usuario de la colección 'users' de Firebase
                    deleteUserFromUsers(userEmail);

                    Toast.makeText(CrearAdmins.this, "Usuario ascendido a administrador.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getUsersFromFirebase() {
        usersCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String userEmail = document.getString("email");
                        userEmailList.add(userEmail);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(CrearAdmins.this, "Error al obtener usuarios.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isUserAdmin(String userEmail) {
        // Verificar si el usuario ya es un administrador en la colección 'admins' de Firebase
        // Implementa tu lógica aquí para verificar si el usuario ya está en la colección 'admins'
        // Puedes realizar una consulta en la colección 'admins' para comprobar la existencia del correo electrónico
        // Devuelve true si el usuario ya es un administrador, de lo contrario, devuelve false.
        // Ejemplo:
        // adminsCollection.whereEqualTo("email", userEmail).get().addOnCompleteListener(...)
        return false; // Cambiar a true si el usuario ya es un administrador
    }

    private void addUserToAdmins(String userEmail) {
        // Agregar usuario a la colección 'admins' de Firebase
        Map<String, Object> admin = new HashMap<>();
        admin.put("email", userEmail);

        adminsCollection.add(admin).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    // Éxito al agregar el usuario a la colección 'admins'
                    // Realiza cualquier acción adicional aquí si es necesario
                } else {
                    Toast.makeText(CrearAdmins.this, "Error al agregar el usuario a los administradores.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void deleteUserFromUsers(String userEmail) {
        // Eliminar usuario de la colección 'users' de Firebase
        usersCollection.whereEqualTo("email", userEmail).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String documentId = document.getId();
                        usersCollection.document(documentId).delete();
                    }
                } else {
                    Toast.makeText(CrearAdmins.this, "Error al eliminar el usuario de los usuarios.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}