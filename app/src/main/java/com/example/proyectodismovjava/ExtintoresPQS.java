package com.example.proyectodismovjava;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class ExtintoresPQS extends AppCompatActivity{

    private SearchView txtBuscar;
    private ArrayList<ExtintoresModel> extintoresModels = new ArrayList<ExtintoresModel>();
    private ListView listView;
    int[] extintoresImages = {R.drawable.polvo_2_5, R.drawable.co2_2_5, R.drawable.polvo_4_5,R.drawable.co2_4_5,R.drawable.polvo_6kg,R.drawable.co2_6kg,R.drawable.polvo_9kg,R.drawable.co2_9kg,R.drawable.ext_acetato_de_potasio,R.drawable.polvo_35kg};
    private Button Regresar, Manuales, allButton, pqsButton, adpButton, co2Button, agregarProductoButton;;
    int sies = 1;
    private Switch mLanguageSwitch;
    private TextView PQSTitulo;
    private String selectedFilter = "all";
    private String currentSearchText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extintores_pqs);
        int adminVariable = getIntent().getIntExtra("admin", 0);

        PQSTitulo = findViewById(R.id.PolvoTituloTexto);
        Regresar = findViewById(R.id.PQS_BotonRegresar);
        txtBuscar = findViewById(R.id.txtBuscar);
        Manuales = findViewById(R.id.btnManuales);

        Manuales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adminVariable == sies) {
                    // El correo electronic pertenece a un administrador, abre la actividad de administrador
                    Intent intent = new Intent(ExtintoresPQS.this, ManualesPDF.class);
                    intent.putExtra("admin", 1);
                    startActivity(intent);
                    finish();
                } else {
                    // El correo electrónico no pertenece a un administrador, regresa a la actividad normal
                    Intent intent = new Intent(ExtintoresPQS.this, ManualesPDF.class);
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
                    Intent intent = new Intent(ExtintoresPQS.this, PantallaPrincipal.class);
                    intent.putExtra("admin", 1);
                    startActivity(intent);
                    finish();
                } else {
                    // El correo electrónico no pertenece a un administrador, regresa a la actividad normal
                    Intent intent = new Intent(ExtintoresPQS.this, PantallaPrincipal.class);
                    startActivity(intent);
                    finish();
                }
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

       // initSearchWidgets();
        SearchView searchView = (SearchView) findViewById(R.id.txtBuscar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                currentSearchText = s;
                ArrayList<ExtintoresModel> extintoresFiltrados = new ArrayList<ExtintoresModel>();
                for(ExtintoresModel extintor:extintoresModels){
                    if(extintor.getExtintorName().toLowerCase().contains(s.toLowerCase())){
                        if(selectedFilter.equals("all")){
                            extintoresFiltrados.add(extintor);
                        }
                        else{
                            if(extintor.getExtintorName().toLowerCase().contains(selectedFilter)){
                                extintoresFiltrados.add(extintor);
                            }
                        }
                    }
                }
                ExtintorAdapter adapter = new ExtintorAdapter(getApplicationContext(), 0, extintoresFiltrados);
                listView.setAdapter(adapter);
                return false;
            }
        });
        initFilterWidgets();
        setUpExtintoresModels();
        setUpList();
        //setUpOnclickListener();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ExtintoresModel list = (ExtintoresModel) (listView.getItemAtPosition(position));
                Intent intent = new Intent(ExtintoresPQS.this, ExtintoresStock.class);
                intent.putExtra("NAME", list.getExtintorName());
                intent.putExtra("PRICE",list.getExtintorPrice());
                intent.putExtra("IMAGE",list.getImage());
                startActivity(intent);
            }
        });




        agregarProductoButton = findViewById(R.id.agregarProductoButton);
        agregarProductoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la actividad para agregar productos
                Intent intent = new Intent(ExtintoresPQS.this, AgregarProductos.class);
                startActivityForResult(intent, 1);
            }
        });

        // ...
    }


    private void initFilterWidgets() {
        Button allButton = (Button) findViewById(R.id.allFilter);
        Button pqsButton = (Button) findViewById(R.id.pqsFilter);
        Button adpButton = (Button) findViewById(R.id.adpFilter);
        Button co2Button = (Button) findViewById(R.id.co2Filter);
    }

    private void setUpList(){
        listView = (ListView) findViewById(R.id.extListView);
        ExtintorAdapter adapter = new ExtintorAdapter(getApplicationContext(), 0, extintoresModels);
        listView.setAdapter(adapter);
    }
    public void setUpOnclickListener(){

        //listView.setOnItemClickListener((adapterView, view, position, l)-> {
          //      ExtintoresModel selectExtintor = (ExtintoresModel) (listView.getItemAtPosition(position));
            //    Intent intent = new Intent(ExtintoresPQS.this, ExtintoresStock.class);
              //  intent.putExtra("NAME", selectExtintor.getExtintorName());
                //startActivity(intent);
        //});

    }
    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Resources res = getResources();
        Configuration config = res.getConfiguration();
        config.locale = locale;
        res.updateConfiguration(config, res.getDisplayMetrics());

        PQSTitulo.setText(res.getString(R.string.PolvoTitulo));
        Regresar.setText(res.getString(R.string.VideoLlamadaRegresar));
    }

    private void setUpExtintoresModels(){
        String[] extintorName = getResources().getStringArray(R.array.ext_cap_txt);
        String[] extintorType = getResources().getStringArray(R.array.ext_typ_txt);
        String[] extintorPrice = getResources().getStringArray(R.array.ext_prc_txt);

        for(int i=0; i<extintorName.length; i++){
            extintoresModels.add(new ExtintoresModel(extintorName[i],extintorType[i],extintorPrice[i], extintoresImages[i]));
        }
    }

    public void initSearchWidgets(){

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Obtener los datos del producto agregado desde la actividad AgregarProductoActivity
            String productName = data.getStringExtra("PRODUCT_NAME");
            String productType = data.getStringExtra("PRODUCT_TYPE");
            String productPrice = data.getStringExtra("PRODUCT_PRICE");
            int productImage = data.getIntExtra("PRODUCT_IMAGE", 0);

            // Crear un nuevo modelo de producto y agregarlo a la lista
            ExtintoresModel newProduct = new ExtintoresModel(productName, productType, productPrice, productImage);
            extintoresModels.add(newProduct);

            // Actualizar la lista con el nuevo producto
            ExtintorAdapter adapter = new ExtintorAdapter(getApplicationContext(), 0, extintoresModels);
            listView.setAdapter(adapter);
        }
    }

    private void filterList(String status){
        selectedFilter = status;
        ArrayList<ExtintoresModel> extintoresFiltrados = new ArrayList<ExtintoresModel>();
        for(ExtintoresModel extintor: extintoresModels){
            if(extintor.getExtintorType().toLowerCase().contains(status)){
                if(currentSearchText == ""){
                    extintoresFiltrados.add(extintor);
                }else{
                    if(extintor.getExtintorType().toLowerCase().contains(currentSearchText.toLowerCase())){
                        extintoresFiltrados.add(extintor);
                    }
                }
            }
        }
        ExtintorAdapter adapter = new ExtintorAdapter(getApplicationContext(), 0, extintoresFiltrados);
        listView.setAdapter(adapter);
    }
    public void allFilterTapped(View view) {
        selectedFilter = "all";
        txtBuscar.setQuery("",false);
        txtBuscar.clearFocus();

        ExtintorAdapter adapter = new ExtintorAdapter(getApplicationContext(), 0, extintoresModels);
        listView.setAdapter(adapter);
    }

    public void pqsFilterTapped(View view) {
        filterList("pqs");
    }

    public void co2FilterTapped(View view) {
        filterList("co2");
    }

    public void adpFilterTapped(View view) {
        filterList("adp");
    }

}