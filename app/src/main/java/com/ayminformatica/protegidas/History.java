package com.ayminformatica.protegidas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ayminformatica.protegidas.R;

public class History extends AppCompatActivity {

    ListView lv_customerList;
    DataBaseHelper dataBaseHelper;
    ArrayAdapter alertArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.appbar_background));

        lv_customerList=findViewById(R.id.lv_custList);

        dataBaseHelper = new DataBaseHelper(History.this);
        ShowCustomersOnListView(dataBaseHelper);
    }

    private void ShowCustomersOnListView(DataBaseHelper dataBaseHelper) {
        alertArrayAdapter = new ArrayAdapter<AlertModel>(History.this, android.R.layout.simple_list_item_1, dataBaseHelper.getAllAlerts());
        if(alertArrayAdapter.isEmpty()){
            Toast.makeText(History.this," No hay lista que mostrar",Toast.LENGTH_SHORT).show();

        }
        else
        lv_customerList.setAdapter(alertArrayAdapter);
    }
}
