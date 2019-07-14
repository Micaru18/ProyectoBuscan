package com.example.proyectobuscan;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.example.proyectobuscan.RegistrarMascotaActivity.imageViewToByte;

public class RegistrarUsuarioActivity extends AppCompatActivity {

    private Button btnRegistrarse;
    private Button btnSubir;
    private EditText txtIngreseNombre;
    private EditText txtIngreseApellido;
    private EditText txtIngreseUsuario;
    private EditText txtIngreseClave;
    private EditText txtIngreseCorreo;
    private EditText txtIngreseCelular;
    private ImageView imgUsu;
    private DBManager dbManager;
    final int REQUEST_CODE_GALLERY = 999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        init();
        dbManager = new DBManager(this);
        btnSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        RegistrarUsuarioActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    dbManager.insertUsuario(txtIngreseNombre.getText().toString().trim(), txtIngreseApellido.getText().toString().trim(),
                            txtIngreseUsuario.getText().toString().trim(), txtIngreseClave.getText().toString().trim(), txtIngreseCorreo.getText().toString().trim(),
                            txtIngreseCelular.getText().toString().trim(), imageViewToByte(imgUsu));
                    Toast.makeText(getApplicationContext(), "Registrado Correctamente!!", Toast.LENGTH_SHORT).show();
                    txtIngreseNombre.setText("");
                    txtIngreseApellido.setText("");
                    txtIngreseUsuario.setText("");
                    txtIngreseClave.setText("");
                    txtIngreseCorreo.setText("");
                    txtIngreseCelular.setText("");
                    imgUsu.setImageResource(R.mipmap.ic_launcher);
                    Intent intent = new Intent(RegistrarUsuarioActivity.this,LoginBusCan.class);
                    startActivity(intent);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    static  private Bitmap getBitmapFromDrawable(@NonNull Drawable drawable) {
        final Bitmap bmp = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bmp);
        drawable.setBounds(0,0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bmp;
    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = getBitmapFromDrawable(image.getDrawable());
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if(requestCode == REQUEST_CODE_GALLERY) {
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);           }
            else {
                Toast.makeText(getApplicationContext(),"No cuenta con permisos para acceder a la galeria de fotos!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgUsu.setImageBitmap(bitmap);

            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){
        txtIngreseNombre= (EditText) findViewById(R.id.txtIngreseNombre);
        txtIngreseApellido = (EditText) findViewById(R.id.txtIngreseApellido);
        txtIngreseUsuario = (EditText) findViewById(R.id.txtIngreseUsuario);
        txtIngreseClave = (EditText) findViewById(R.id.txtIngreseClave);
        txtIngreseCorreo = (EditText) findViewById(R.id.txtIngreseCorreo);
        txtIngreseCelular = (EditText) findViewById(R.id.txtIngreseCelular);
        btnSubir = (Button) findViewById(R.id.btnSubir);
        btnRegistrarse = (Button) findViewById(R.id.btnRegistrarse);
        imgUsu = (ImageView) findViewById(R.id.imgUsu);
    }

}
