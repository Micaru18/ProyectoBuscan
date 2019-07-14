package com.example.proyectobuscan;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<Mascota> list;
    MascotaAdapter adapter = null;
    private Button btnAdopcion;
    private Button btnEncontrados;
    private FloatingActionButton floatButtonM;
    private DBManager dbManager;
    ListView listView;

    RecyclerView rvLista;
    List<Mascota> listaMascotas = new ArrayList<>();

    private DBManager DbMascota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        dbManager = new DBManager(this);
       // listView = (ListView) findViewById(R.id.listView);
        dbManager.open();
        ArrayList<Mascota> arrayList = dbManager.fetch();

        btnAdopcion = (Button) findViewById(R.id.btnAdopcion);
        btnEncontrados = (Button)findViewById(R.id.btnEncontrados);
        floatButtonM = findViewById(R.id. floatButtonM);
        DbMascota = new DBManager(getApplicationContext());


        btnAdopcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AnimalesAdopcionActivity.class);
                startActivity(intent);
            }

        });

        btnEncontrados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MascotasEncontradasActivity.class);
                startActivity(intent);
            }
        });

        floatButtonM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }

        });

        rvLista = (RecyclerView) findViewById(R.id.rvLista);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        rvLista.setLayoutManager(lm);
        rvLista.setHasFixedSize(true);

        this.CargarData();
        this.CargaAdapter();
    }

    private void CargarData() {
        listaMascotas = DbMascota.fetch();
    }

    private void CargaAdapter()
    {
        MascotaAdapter adapter = new MascotaAdapter(listaMascotas);
        rvLista.setAdapter(adapter);
    }


}
