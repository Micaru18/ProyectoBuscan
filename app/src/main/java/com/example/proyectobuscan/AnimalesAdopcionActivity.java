package com.example.proyectobuscan;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AnimalesAdopcionActivity extends AppCompatActivity {

    private FloatingActionButton floatButtonMen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animales_adopcion);

        floatButtonMen = findViewById(R.id.floatButtonMen);

        floatButtonMen.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                startActivity(intent);
            }
        });


    }
}
