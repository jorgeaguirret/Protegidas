package com.ayminformatica.protegidas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ayminformatica.protegidas.R;

public class Emergency_dial extends AppCompatActivity {
    Button llamarCarabineros, llamarAmbulancia, llamarBomberos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_dial);



        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.appbar_background));


        //---------Emergency call to police
        llamarCarabineros = (Button) findViewById(R.id.llamar_carabineros);
        llamarCarabineros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Emergency_dial.this, "Calling Police", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:133"));                    //---Carabineros
                startActivity(intent);
            }
        });

        //---------Emergency call to ambulance
        llamarAmbulancia = (Button) findViewById(R.id.llamar_ambulancia);
        llamarAmbulancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Emergency_dial.this, "Calling Ambulance", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:131"));                    //---Ambulancia
                startActivity(intent);
            }
        });

        //---------Emergency call to fire brigade
        llamarBomberos = (Button) findViewById(R.id.llamar_bomberos);
        llamarBomberos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Emergency_dial.this, "Calling Fire Brigade", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:132"));                    //---Bomberos
                startActivity(intent);
            }
        });
    }
}