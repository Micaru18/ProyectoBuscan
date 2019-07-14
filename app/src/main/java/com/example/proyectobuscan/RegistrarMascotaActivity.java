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


public class RegistrarMascotaActivity extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtDescripcion;
    private Button btnImagen;
    private ImageView imgFoto;
    private Button btnRegistrar;
    private DBManager dbManager;
    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_mascota);
        init();
        dbManager = new DBManager(this);
        btnImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        RegistrarMascotaActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    dbManager.insertMascota(txtNombre.getText().toString().trim(), txtDescripcion.getText().toString().trim(), imageViewToByte(imgFoto));
                    Toast.makeText(getApplicationContext(), "Registrado Correctamente!!", Toast.LENGTH_SHORT).show();
                    txtNombre.setText("");
                    txtDescripcion.setText("");
                    imgFoto.setImageResource(R.mipmap.ic_launcher);
                    Intent intent = new Intent(RegistrarMascotaActivity.this,PrincipalActivity.class);
                    startActivity(intent);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

          //     final String nombre = txtNombre.getText().toString();
            //    final String descripcion = txtDescripcion.getText().toString();
                //  dbManager.insertMascota(nombre, descripcion,imageViewToByte(imgFoto));
               // Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class)
                 //      .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(intent);
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
                    imgFoto.setImageBitmap(bitmap);

                } catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
            super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);
        btnImagen = (Button) findViewById(R.id.btnImagen);
        btnRegistrar = (Button) findViewById(R.id.btnIniciar);
        imgFoto = (ImageView) findViewById(R.id.imgFoto);
    }
}