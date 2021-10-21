package com.ayminformatica.protegidas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Descripcion extends AppCompatActivity {

    int id=0;
    ModeloUsuarioPrincipal u;
    daoUsuarioPrincipal dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.appbar_background));

        Bundle b = getIntent().getExtras();
        id=b.getInt("id");
        dao=new daoUsuarioPrincipal(this);
        u=dao.getUsuarioById(id);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.navDescripcion);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.navPanelAlerta:
                        //startActivity(new Intent(getApplicationContext(),
                        //        Panel_Alerta.class));
                        //overridePendingTransition(0,0);
                        Intent intent = new Intent(Descripcion.this,Panel_Alerta.class);
                        intent.putExtra("id",u.getUsuariop_id());
                        startActivity(intent);
                        return true;

                    case R.id.navSobreNosotros:
                        //startActivity(new Intent(getApplicationContext(),
                        //       SobreNosotros.class));
                        //overridePendingTransition(0,0);
                        Intent intent2 = new Intent(Descripcion.this,SobreNosotros.class);
                        intent2.putExtra("id",u.getUsuariop_id());
                        startActivity(intent2);
                        return true;

                    case R.id.navAjustes:
                        //startActivity(new Intent(getApplicationContext(),
                        //        Ajustes.class));
                        //overridePendingTransition(0,0);
                        Intent intent3 = new Intent(Descripcion.this,Ajustes.class);
                        intent3.putExtra("id",u.getUsuariop_id());
                        startActivity(intent3);
                        return true;


                    case R.id.navDescripcion:
                        return true;

                    case R.id.navAmigosCercanos:
                        //startActivity(new Intent(getApplicationContext(),
                        //        Amigos_Cercanos.class));
                        //overridePendingTransition(0,0);
                        Intent intent4 = new Intent(Descripcion.this,Amigos_Cercanos.class);
                        intent4.putExtra("id",u.getUsuariop_id());
                        startActivity(intent4);
                        return true;
                }
                return false;
            }
        });
    }
}
