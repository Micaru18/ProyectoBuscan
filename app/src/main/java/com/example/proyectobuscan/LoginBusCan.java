package com.example.proyectobuscan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginBusCan extends AppCompatActivity {

    private Button btnIniciar;
    private EditText txtIngreseUsuario;
    private EditText txtIngreseClave;
    private TextView txtRegistrate;

    private DBManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_bus_can);

        btnIniciar = (Button) findViewById(R.id.btnIniciar);
        txtIngreseUsuario = (EditText) findViewById(R.id.txtIngreseUsuario);
        txtIngreseClave = (EditText) findViewById(R.id.txtIngreseClave);
        txtRegistrate = (TextView) findViewById(R.id.txtRegistrate);
        db = new DBManager(this);

        txtRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrarUsuarioActivity.class);
                startActivity(intent);
            }
        });

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Validar()) {
                    Toast.makeText(getApplicationContext(), "Password Valido", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                    startActivity(intent);
                } else

                {
                    Toast.makeText(getApplicationContext(), "ERRORRRRR", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //            (? =. * [0-9]) un dígito debe aparecer al menos una vez
    //            (? =. * [a-z]) una letra minúscula debe aparecer al menos una vez
    //            (? =. * [A-Z]) una letra mayúscula debe aparecer al menos una vez
    //            (? =. * [@ # $% ^ & + =]) un carácter especial debe aparecer al menos una vez
    //            (? = \\ S + $) no se permiten espacios en blanco en toda la cadena
    //            {8,} al menos 8 caracteres


    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

    private boolean Validar() {
        boolean exito = false;

        Usuario usuario = db.validarSesion(txtIngreseUsuario.getText().toString(), txtIngreseClave.getText().toString());

        if (usuario != null)
            exito = true;

        return exito;
    }
}