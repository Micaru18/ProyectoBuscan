package com.example.proyectobuscan;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MiPerfilActivity extends AppCompatActivity {

    private Button btnRegistrarMascota;
    private Button btnEstadoMascota;
    private Button btnRecompensas;
    private Button btnDonemos;
    private Button btnCambiarClave;
    private FloatingActionButton floatButtonMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);

        btnRegistrarMascota = (Button) findViewById(R.id.btnRegistrarMascota);
        btnEstadoMascota = (Button) findViewById(R.id.btnEstadoMascota);
        btnRecompensas = (Button) findViewById(R.id.btnRecompensas);
        btnDonemos = (Button)  findViewById(R.id.btnDonemos);
        btnCambiarClave = (Button) findViewById(R.id.btnCambiarClave);
        floatButtonMe = findViewById(R.id.floatButtonMe);

        floatButtonMe.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                startActivity(intent);
            }
        });

        btnRegistrarMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrarMascotaActivity.class);
                startActivity(intent);
            }

        });

        btnEstadoMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EstadoMascotaActivity.class);
                startActivity(intent);
            }

        });

        btnRecompensas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PuntosRecompensasActivity.class);
                startActivity(intent);
            }

        });

        btnDonemos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DonemosActivity.class);
                startActivity(intent);
            }

        });

        btnCambiarClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CambioClaveActivity.class);
                startActivity(intent);
            }

        });
    }
}
