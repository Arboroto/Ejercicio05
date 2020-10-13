package com.example.ejercicio05.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ejercicio05.R;
import com.example.ejercicio05.modelos.Nota;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NotasAdapter extends ArrayAdapter<Nota> {

    /**
     *
     * @param context>sirve para saber que actividad está montando el LISTADO /array/
     * @param resource>Es la plantilla para ir mostrando cada nota.
     * @param objects>Es el conjunto de elementos que voya  mostrar.
     */

    private Context context;
    private int resource;
    private ArrayList<Nota> objects;

    public NotasAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Nota> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource=resource;
        this.objects = objects; //no casteamos porque en el constructor hemos cambiado el tipo de dato que pide, en objects.
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //nos cargamos el return porque sí
        View filaNota = LayoutInflater.from(context).inflate(resource, null);
        Nota nota = objects.get(position);
        TextView txtTitulo = filaNota.findViewById(R.id.txtTituloFilaNota);//titulo
        TextView txtFecha = filaNota.findViewById(R.id.txtFechFilanota);//fecha
        txtTitulo.setText(nota.getTitulo());
        //FECHA
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        txtFecha.setText(sdf.format(nota.getFecha()));

        return filaNota;
    }
}
