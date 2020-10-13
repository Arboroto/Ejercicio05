package com.example.ejercicio05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejercicio05.modelos.Nota;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CrearNotaAc extends AppCompatActivity {

    private TextView txtTitulo, txtContenido, txtFecha;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_nota);

        inicializarVariables();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtTitulo.getText().toString().isEmpty() &&
                        !txtContenido.getText().toString().isEmpty() &&
                        !txtFecha.getText().toString().isEmpty()){
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Nota nota = new Nota(txtTitulo.getText().toString(), txtContenido.getText().toString(), simpleDateFormat.parse(txtFecha.getText().toString()));
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("NOTA", nota);
                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent);
                        finish();
                    } catch (ParseException e) {
                        e.printStackTrace();
                        Toast.makeText(CrearNotaAc.this, "El formato de la fecha no es correcto", Toast.LENGTH_LONG);
                    }
                }
            }
        });
    }

    private void inicializarVariables() {
        txtTitulo = findViewById(R.id.txtTituloNotaCrear);
        txtContenido = findViewById(R.id.txtContenidoNotaCrear);
        txtFecha = findViewById(R.id.dateCrear);
        btnGuardar = findViewById(R.id.btnCrearNotaAcn);
    }
}