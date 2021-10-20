package com.ayminformatica.protegidas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaCoordenadas extends AppCompatActivity {
    DataBaseHelperGps miDB;
    ListView listac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_coordenadas2);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.appbar_background));

        listac = (ListView)findViewById(R.id.listView);
        miDB = new DataBaseHelperGps(this);

        ArrayList<String> listados = new ArrayList<>();
        Cursor data = miDB.getListaContenidos();
        if (data.getCount() == 0 ){
            Toast.makeText(this," No hay registros que mostrar",Toast.LENGTH_LONG).show();
        }else{
            while (data.moveToNext()){
                listados.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(
                        this,android.R.layout.simple_list_item_1,listados);
                listac.setAdapter(listAdapter);

            }
        }
    }
}