package com.ayminformatica.protegidas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Llamada_emergencia extends AppCompatActivity {
    Button llamarCarabineros, llamarAmbulancia, llamarBomberos, llamarFonoMujer , btAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llamada_emergencia);



        //ActionBar actionBar = getSupportActionBar();
        //.setBackgroundDrawable(getResources().getDrawable(R.drawable.appbar_background));


        //---------Llamada de emergencia a carabineros
        llamarCarabineros = (Button) findViewById(R.id.llamar_carabineros);
        llamarCarabineros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Llamada_emergencia.this, "Llamando a carabineros", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:133"));                    //---Carabineros
                startActivity(intent);
            }
        });

        //---------Llamada de emergencia a ambulancia
        llamarAmbulancia = (Button) findViewById(R.id.llamar_ambulancia);
        llamarAmbulancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Llamada_emergencia.this, "Llamando a ambulancia", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:131"));                    //---Ambulancia
                startActivity(intent);
            }
        });

        //---------Llamada de emergencia a bomberos
        llamarBomberos = (Button) findViewById(R.id.llamar_bomberos);
        llamarBomberos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Llamada_emergencia.this, "Llamando a bomberos", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:132"));                    //---Bomberos
                startActivity(intent);
            }
        });

        //---------Llamada de emergencia a fono mujer
        llamarFonoMujer = (Button) findViewById(R.id.llamar_fono_mujer);
        llamarFonoMujer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Llamada_emergencia.this, "Llamando a fono mujer", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:1455"));                    //---Fono Mujer
                startActivity(intent);
            }
        });

        btAtras = (Button) findViewById(R.id.volver);
        btAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Panel_Alerta.class);
                startActivity(i);
            }
        });
    }
}