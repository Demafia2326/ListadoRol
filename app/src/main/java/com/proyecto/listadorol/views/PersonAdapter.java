package com.proyecto.listadorol.views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.listadorol.R;
import com.proyecto.listadorol.models.PersonMin;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.AcontecimientoViewHolder>
        implements View.OnClickListener {

    private ArrayList<PersonMin> items;

    private View.OnClickListener listener;

    // Clase interna:
    // Se implementa el ViewHolder que se encargará
    // de almacenar la vista del elemento y sus datos
    public static class AcontecimientoViewHolder
            extends RecyclerView.ViewHolder {

        private TextView avatar_name;
        private TextView player_name;
        private ImageView image_View;

        public AcontecimientoViewHolder(View itemView) {
            super(itemView);
            avatar_name = (TextView) itemView.findViewById(R.id.NameText);
            player_name = (TextView) itemView.findViewById(R.id.sournameText);
            image_View=  itemView.findViewById(R.id.imageView);
        }

        public void AcontecimientoBind(PersonMin person) {
            avatar_name.setText(person.getPlayer_name());
            player_name.setText(person.getAvatar_name());
            byte[] decodedString;
                if (person.getImagen()==null){
                    image_View.setImageResource(R.drawable.rol);
                }else{
                    decodedString = Base64.decode(person.getImagen(), Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    image_View.setImageBitmap(decodedByte);
                }

        }
    }

    public PersonAdapter(@NonNull ArrayList<PersonMin> items) {
        this.items = items;
    }






    @Override
    public AcontecimientoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_data, parent, false);
        row.setOnClickListener(this);

        AcontecimientoViewHolder avh = new AcontecimientoViewHolder(row);
        return avh;
    }

    // Se encarga de actualizar los datos de un ViewHolder ya existente.
    @Override
    public void onBindViewHolder(AcontecimientoViewHolder viewHolder, int position) {
        PersonMin person = items.get(position);
        viewHolder.AcontecimientoBind(person);
    }

    // Indica el número de elementos de la colección de datos.
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Asigna un listener al elemento
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }
}