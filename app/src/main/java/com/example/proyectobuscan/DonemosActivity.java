package com.example.proyectobuscan;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DonemosActivity extends AppCompatActivity {

    private Button btnClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donemos);

        btnClick = (Button) findViewById(R.id.btnClick);



        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFacebookPage ("https://es-la.facebook.com/ongpatitasdeamor.lima/");

            }
        });
    }

    private void goToFacebookPage(String id) {
        try {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(id));
            startActivity(intent);

        } catch (ActivityNotFoundException e) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://es-la.facebook.com/ongpatitasdeamor.lima/" + id));
            startActivity(intent);


        }
        {
        }
    }

    }
