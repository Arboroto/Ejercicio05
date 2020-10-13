package com.example.ejercicio05;

import android.content.Intent;
import android.os.Bundle;

import com.example.ejercicio05.modelos.Nota;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EditNotaAct extends AppCompatActivity {

    private EditText txtTitulo, txtContenido, txtFecha;
    private Nota nota;
    private int posicion;
    private SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_nota);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtTitulo = findViewById(R.id.txtTituloEDIT);
        txtContenido = findViewById(R.id.txtContenidoEDIT);
        txtFecha = findViewById(R.id.txtFechaEdit);

        if(getIntent().getExtras() != null){
            nota = getIntent().getExtras().getParcelable("NOTA");
            posicion = getIntent().getExtras().getInt("POS");

            if(nota != null){
                txtTitulo.setText(nota.getTitulo());
                txtContenido.setText(nota.getContenido());
                simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                txtFecha.setText(simpleDateFormat.format(nota.getFecha()));

            }else{
                nota = new Nota();
                txtTitulo.setHint("Titulo...");
                //y asi con todos. aunq no es necesario-.
            }
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtTitulo.getText().toString().isEmpty() && !txtContenido.getText().toString().isEmpty() &&
                        !txtFecha.getText().toString().isEmpty()){
                    nota.setTitulo(txtTitulo.getText().toString());
                    nota.setContenido(txtContenido.getText().toString());
                    try {
                        nota.setFecha(simpleDateFormat.parse(txtFecha.getText().toString()));
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("NOTA", nota);
                        bundle.putInt("POS", posicion);
                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent);
                        finish();
                    } catch (ParseException e) {
                        Toast.makeText(EditNotaAct.this, "la fecha esta mal", Toast.LENGTH_LONG);
                    }
                }
            }
        });
    }
}