package com.example.proyectobuscan;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MascotaViewHolder extends RecyclerView.ViewHolder {

    TextView txtDescripcion;
    TextView txtComentar;
    TextView tvNombreMascota;
    ImageView imgFoto;

    public MascotaViewHolder(View itemView)
    {
        /* here i made changes, too. Basically the references */
        super(itemView);

        tvNombreMascota = itemView.findViewById(R.id.tvNombreMascota);
        txtDescripcion = (TextView) itemView.findViewById(R.id.tvDescripcion);
        //txtComentar = (TextView) itemView.findViewById(R.id.txtComentar);
        imgFoto = (ImageView) itemView.findViewById(R.id.ivFoto);

    }
}
