package com.example.proyectolwing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador
        extends RecyclerView.Adapter<Adaptador.ViewHolderDatos>
        implements View.OnClickListener
    {
    @NonNull
    ArrayList<Usuarios> listDatos;
    private View.OnClickListener listener;

    public Adaptador(ArrayList<Usuarios> listDatos){
    this.listDatos=listDatos;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adminlist,null,false);

        view.setOnClickListener(this);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.ViewHolderDatos holder, int position) {
        holder.IdNombre.setText(listDatos.get(position).getNombre());
        holder.IdProyecto.setText(listDatos.get(position).getIdProyecto());
        holder.IdDescripcion.setText(listDatos.get(position).getDescripcion());
        holder.foto.setImageBitmap(listDatos.get(position).getFoto());
    }

    @Override
    public int getItemCount() {

        return listDatos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

        @Override
        public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
        }

        public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView IdNombre, IdProyecto, IdDescripcion;
        ImageView foto;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            IdNombre=(TextView)itemView.findViewById(R.id.idNombre);
            IdProyecto=(TextView)itemView.findViewById(R.id.idProyecto);
            IdDescripcion=(TextView)itemView.findViewById(R.id.idDescripcion);
            foto=(ImageView)itemView.findViewById(R.id.Foto);

        }

    }
}
