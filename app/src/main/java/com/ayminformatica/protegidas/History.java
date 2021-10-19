package com.ayminformatica.protegidas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ayminformatica.protegidas.R;

public class History extends AppCompatActivity {

    ListView lv_listaAlertas;
    DataBaseHelper dataBaseHelper;
    ArrayAdapter alertaArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        lv_listaAlertas =findViewById(R.id.lv_listaAlertas);

        dataBaseHelper = new DataBaseHelper(History.this);
        ShowCustomersOnListView(dataBaseHelper);
    }

    private void ShowCustomersOnListView(DataBaseHelper dataBaseHelper) {
        alertaArrayAdapter = new ArrayAdapter<AlertModel>(History.this, android.R.layout.simple_list_item_1, dataBaseHelper.getAllAlerts());
        if(alertaArrayAdapter.isEmpty()){
            Toast.makeText(History.this,"No entries to show",Toast.LENGTH_SHORT).show();

        }
        else
            lv_listaAlertas.setAdapter(alertaArrayAdapter);
    }
}
