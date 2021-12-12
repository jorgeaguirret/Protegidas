package com.ayminformatica.protegidas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Descripcion extends AppCompatActivity {

    TextView tvweb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);

        tvweb = findViewById(R.id.tvweb);
        tvweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Webview.class);
                startActivity(intent);
            }
        });
        //ActionBar actionBar = getSupportActionBar();
        //.setBackgroundDrawable(getResources().getDrawable(R.drawable.appbar_background));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.navDescripcion);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.navPanelAlerta:
                        startActivity(new Intent(getApplicationContext(),
                                Panel_Alerta.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navSobreNosotros:
                        startActivity(new Intent(getApplicationContext(),
                                SobreNosotros.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navAjustes:
                        startActivity(new Intent(getApplicationContext(),
                                Ajustes.class));
                        overridePendingTransition(0,0);
                        return true;


                    case R.id.navDescripcion:
                        return true;

                    case R.id.navAmigosCercanos:
                        startActivity(new Intent(getApplicationContext(),
                                Amigos_Cercanos.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}
