package com.example.proyectobuscan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CambioClaveActivity extends AppCompatActivity {

    private EditText txtIngresarC;
    private EditText txtConNueva;
    private EditText txtConfirmaC;
    private Button btnCambiarC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_clave);

        btnCambiarC = (Button) findViewById(R.id.btnCambiarC);

        btnCambiarC.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                startActivity(intent);
            }
        });
    }
}
