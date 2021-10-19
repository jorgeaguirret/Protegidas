package com.ayminformatica.protegidas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SobreNosotros extends AppCompatActivity {
    static int battery_level=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre_nosotros);



        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.appbar_background));


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.about);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(),
                                Panel_Alerta.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.about:
                        return true;

                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(),
                                Ajustes.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.description:
                        startActivity(new Intent(getApplicationContext(),
                                Descripcion.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.close_friends:
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
