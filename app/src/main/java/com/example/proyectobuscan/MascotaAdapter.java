package com.example.proyectobuscan;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaViewHolder> {

    /* here i made quite a lot of changes */
    private List<Mascota> listaMascota;

    public MascotaAdapter(List<Mascota> listaMascota){
        this.listaMascota = listaMascota;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_player,
                viewGroup, false);

        return new MascotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder mvh, int i)
    {
        mvh.txtDescripcion.setText(listaMascota.get(i).getDescripcion());
        byte[] imagenMascota = listaMascota.get(i).getImagen();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imagenMascota, 0, imagenMascota.length);
        mvh.imgFoto.setImageBitmap(bitmap);
        mvh.tvNombreMascota.setText(listaMascota.get(i).getNombre());
    }

    @Override
    public int getItemCount()
    {
        return listaMascota.size();
    }
}
