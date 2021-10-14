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
    Button callPolice, callAmbulance, callFireBrigade, callFonoMujer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_dial);



        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.appbar_background));


        //---------Emergency call to police
        callPolice = (Button) findViewById(R.id.call_police);
        callPolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Emergency_dial.this, "Llamando a Carabineros", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:133"));                    //---Rehan's Number
                startActivity(intent);
            }
        });

        //---------Emergency call to ambulance
        callAmbulance = (Button) findViewById(R.id.call_ambulance);
        callAmbulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Emergency_dial.this, "LLamando a la ambulancia", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:131"));                    //---Shubham's Number
                startActivity(intent);
            }
        });

        //---------Emergency call to fire brigade
        callFireBrigade = (Button) findViewById(R.id.call_fire_brigade);
        callFireBrigade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Emergency_dial.this, "Llamando a los Bomberos", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:132"));                    //---Nikhil's Number
                startActivity(intent);
            }
        });

        //---------Emergency call to fire brigade
        callFonoMujer = (Button) findViewById(R.id.call_fono_mujer);
        callFonoMujer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Emergency_dial.this, "Llamando al Fono Mujer", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:1455"));                    //---Nikhil's Number
                startActivity(intent);
            }
        });
    }
}