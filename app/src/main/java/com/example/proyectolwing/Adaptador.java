package com.example.proyectolwing;

<<<<<<< HEAD
=======
import android.graphics.Color;
>>>>>>> 346976c... Commit secundario con avances
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
<<<<<<< HEAD
=======
import android.widget.LinearLayout;
>>>>>>> 346976c... Commit secundario con avances
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
<<<<<<< HEAD
=======
        switch(listDatos.get(position).getEstado()){
            case 1:
                holder.linearAdminlist.setBackgroundResource(R.drawable.boton_rounded2);
                break;

            case 2:
                holder.linearAdminlist.setBackgroundResource(R.drawable.boton_rounded3);
                break;

            case 3:
                holder.linearAdminlist.setBackgroundResource(R.drawable.boton_rounded4);
                break;
        }
>>>>>>> 346976c... Commit secundario con avances
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
<<<<<<< HEAD
=======
        LinearLayout linearAdminlist;

>>>>>>> 346976c... Commit secundario con avances

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            IdNombre=(TextView)itemView.findViewById(R.id.idNombre);
            IdProyecto=(TextView)itemView.findViewById(R.id.idProyecto);
            IdDescripcion=(TextView)itemView.findViewById(R.id.idDescripcion);
            foto=(ImageView)itemView.findViewById(R.id.Foto);
<<<<<<< HEAD
=======
            linearAdminlist=(LinearLayout)itemView.findViewById(R.id.LinearAdminlist);
>>>>>>> 346976c... Commit secundario con avances

        }

    }
}
