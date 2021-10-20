package com.ayminformatica.protegidas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Historial extends AppCompatActivity {

    ListView lv_listaAlertas;
    DataBaseHelper dataBaseHelper;
    ArrayAdapter alertaArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        lv_listaAlertas =findViewById(R.id.lv_listaAlertas);

        dataBaseHelper = new DataBaseHelper(Historial.this);
        ShowCustomersOnListView(dataBaseHelper);
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
