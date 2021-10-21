package com.ayminformatica.protegidas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Historial extends AppCompatActivity {

    ListView lv_listaAlertas;
    DataBaseHelper dataBaseHelper;
    ArrayAdapter alertaArrayAdapter;
    Button bt_volver;
    int id=0;
    ModeloUsuarioPrincipal u;
    daoUsuarioPrincipal dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        lv_listaAlertas =findViewById(R.id.lv_listaAlertas);
        bt_volver = findViewById(R.id.volverAtras);

        Bundle b = getIntent().getExtras();
        id=b.getInt("id");
        dao=new daoUsuarioPrincipal(this);
        u=dao.getUsuarioById(id);

        dataBaseHelper = new DataBaseHelper(Historial.this);
        ShowCustomersOnListView(dataBaseHelper);

        bt_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Ajustes.class);
                i.putExtra("id",u.getUsuariop_id());
                startActivity(i);
            }
        });
    }

    private void ShowCustomersOnListView(DataBaseHelper dataBaseHelper) {
        alertaArrayAdapter = new ArrayAdapter<ModeloAlerta>(Historial.this, android.R.layout.simple_list_item_1, dataBaseHelper.getAllAlerts());
        if(alertaArrayAdapter.isEmpty()){
            Toast.makeText(Historial.this,"No hay registros para mostrar",Toast.LENGTH_SHORT).show();

        }
        else
            lv_listaAlertas.setAdapter(alertaArrayAdapter);
    }
}
