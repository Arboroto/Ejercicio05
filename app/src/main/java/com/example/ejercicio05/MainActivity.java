package com.example.ejercicio05;

import android.content.Intent;
import android.os.Bundle;

import com.example.ejercicio05.Adapters.NotasAdapter;
import com.example.ejercicio05.modelos.Nota;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private final int CREAR_NOTA = 1 ;
    private final int EDIT_NOTA = 2 ;
    //1. conjunto de datos
    private ArrayList<Nota> listaNotas;
    //2. Plantilla de los elementos. new resource file en layout, res: fila_nota.xml
    private int plantillaFilas;
    //3. Adapter para el ListView.
    private ListView listView;
    private NotasAdapter adapter;//MOVIDOTEEEEEEEEEEEEEEEEEEEEE.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //INICIALIZAMOS DATOS
        inicializaDatos();

        FloatingActionButton fab = findViewById(R.id.fab);//nombre del boton ese tan chulo
        //fin inicializa datos.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CrearNotaAc.class);
                startActivityForResult(intent, CREAR_NOTA);
            }
        });
    }

    private void inicializaDatos() {
        listaNotas = new ArrayList<>();
        plantillaFilas = R.layout.fila_nota;
        listView = findViewById(R.id.contenedorMain);
        adapter = new NotasAdapter(this, plantillaFilas,listaNotas);
        listView.setAdapter(adapter);//ponemos antes el adapter

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Nota nota = listaNotas.get(position);
                Intent intent = new Intent(MainActivity.this, EditNotaAct.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("NOTA", nota);
                bundle.putInt("POS", position);
                intent.putExtras(bundle);
                startActivityForResult(intent, EDIT_NOTA);
            }
        });
    }

    private void inicializaNotas() {
        for (int i = 0; i <10 ; i++) {
            Nota nota = new Nota("Titulo" + i, "Contenido" +
                     i, new Date());
            listaNotas.add(nota);
        }
        adapter.notifyDataSetChanged();//hay que hacerlo porque... si?
    }

    //nos cargamos 2 metodos por debajo del oncreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CREAR_NOTA && resultCode == RESULT_OK){
            if(data != null){
                if(data.getExtras() != null){
                    Nota nota = data.getExtras().getParcelable("NOTA");

                if(nota != null){
                    listaNotas.add(nota);
                    adapter.notifyDataSetChanged();
                }
                }
            }
        }
        if(requestCode == EDIT_NOTA && resultCode == RESULT_OK){
            if(data != null){
                if(data.getExtras() != null){
                    Nota nota = data.getExtras().getParcelable("NOTA");
                    int posicion = data.getExtras().getInt("POS");
                    if(nota != null){
                        listaNotas.set(posicion, nota);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }
}
