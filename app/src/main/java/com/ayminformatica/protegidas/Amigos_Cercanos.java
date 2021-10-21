package com.ayminformatica.protegidas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class Amigos_Cercanos extends AppCompatActivity {
    Button agregar_c;
    EditText agregar_no1, agregar_no2, agregar_no3;
    Cursor cursor = null;
    static String fono1, fono2, fono3;
    DataBaseHelper dataBaseHelper = new DataBaseHelper(Amigos_Cercanos.this);
    int id=0;
    ModeloUsuarioPrincipal u;
    daoUsuarioPrincipal dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amigos_cercanos);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.appbar_background));

        Bundle b = getIntent().getExtras();
        id=b.getInt("id");
        dao=new daoUsuarioPrincipal(this);
        u=dao.getUsuarioById(id);

        agregar_no1 = (EditText)findViewById(R.id.fono1);
        agregar_no2 = (EditText)findViewById(R.id.fono2);
        agregar_no3 = (EditText)findViewById(R.id.fono3);

        List<ModeloContacto> everyone = dataBaseHelper.getEveryone();
        if(!everyone.isEmpty()) {
            for(ModeloContacto i:everyone)
                System.out.println(i.getNombre());
            try {
                agregar_no1.setText(everyone.get(0).getFono());
                agregar_no2.setText(everyone.get(1).getFono());
                agregar_no3.setText(everyone.get(2).getFono());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            System.out.println("Ejecutando....");
        }


        onClickAgregar1();
        onClickAgregar2();
        onClickAgregar3();

        //------------MENU NAVEGACIÓN---------
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.navAmigosCercanos);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.navPanelAlerta:
                        //startActivity(new Intent(getApplicationContext(),
                          //      Panel_Alerta.class));
                        //overridePendingTransition(0,0);
                        Intent intent = new Intent(Amigos_Cercanos.this,Panel_Alerta.class);
                        intent.putExtra("id",u.getUsuariop_id());
                        startActivity(intent);
                        return true;

                    case R.id.navSobreNosotros:
                        //startActivity(new Intent(getApplicationContext(),
                        //        SobreNosotros.class));
                        //overridePendingTransition(0,0);
                        Intent intent2 = new Intent(Amigos_Cercanos.this,SobreNosotros.class);
                        intent2.putExtra("id",u.getUsuariop_id());
                        startActivity(intent2);
                        return true;

                    case R.id.navAjustes:
                        //startActivity(new Intent(getApplicationContext(),
                        //        Ajustes.class));
                        //overridePendingTransition(0,0);
                        Intent intent3 = new Intent(Amigos_Cercanos.this,Ajustes.class);
                        intent3.putExtra("id",u.getUsuariop_id());
                        startActivity(intent3);
                        return true;


                    case R.id.navDescripcion:
                        //startActivity(new Intent(getApplicationContext(),
                        //        Descripcion.class));
                        //overridePendingTransition(0,0);
                        Intent intent4 = new Intent(Amigos_Cercanos.this,Descripcion.class);
                        intent4.putExtra("id",u.getUsuariop_id());
                        startActivity(intent4);
                        return true;

                    case R.id.navAmigosCercanos:
                        return true;
                }
                return false;
            }
        });
    }

    public void onClickAgregar1()
    {
        agregar_c = (Button)findViewById(R.id.agregarContacto1);

        //-----Con esto abre la lista de contactos como una nueva activity
        try {
            agregar_c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_PICK);
                    i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                    startActivityForResult(i, 1);
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void onClickAgregar2()
    {
        agregar_c = (Button)findViewById(R.id.agregarContacto2);

        //-----Con esto abre la lista de contactos como una nueva activity
        try {
            agregar_c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_PICK);
                    i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                    startActivityForResult(i, 2);
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void onClickAgregar3()
    {
        agregar_c = (Button)findViewById(R.id.agregarContacto3);

        //-----Con esto abre la lista de contactos como una nueva activity
        try {
            agregar_c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_PICK);
                    i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                    startActivityForResult(i, 3);
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int codigoSolicitud, int codigoResultado, Intent data)
    {
        //--------si se hace clic en el boton agregar contacto1
        if((codigoSolicitud==1) && (codigoResultado==RESULT_OK))
        {
            try{
                Uri uri = data.getData();
                cursor = getContentResolver().query(uri, new String[] {ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.Contacts.DISPLAY_NAME}, null, null, null);
                if(cursor != null && cursor.moveToNext())
                {
                    String fono = cursor.getString(0);
                    String nombre = cursor.getString(1);
                    ModeloContacto contactModel = new ModeloContacto(codigoSolicitud,nombre,fono);
                    agregar_no1.setText(fono);
                    boolean status = dataBaseHelper.addOne(contactModel,codigoSolicitud);
                    String ToastMsg = status==true?"Contacto agregado exitosamente":"Error al agregar contacto";
                    Toast.makeText(Amigos_Cercanos.this,ToastMsg,Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        //--------si se hace clic en el boton agregar contacto2
        if((codigoSolicitud==2) && (codigoResultado==RESULT_OK))
        {
            try{
                Uri uri = data.getData();
                cursor = getContentResolver().query(uri, new String[] {ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.Contacts.DISPLAY_NAME}, null, null, null);
                if(cursor != null && cursor.moveToNext())
                {
                    String fono = cursor.getString(0);
                    String nombre = cursor.getString(1);
                    ModeloContacto contactModel = new ModeloContacto(codigoSolicitud,nombre,fono);
                    agregar_no2.setText(fono);
                    boolean status = dataBaseHelper.addOne(contactModel,codigoSolicitud);
                    String ToastMsg = status==true?"Contacto agregado exitosamente":"Error al agregar contacto";
                    Toast.makeText(Amigos_Cercanos.this,ToastMsg,Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        //--------si se hace clic en el boton agregar contacto3
        if((codigoSolicitud==3) && (codigoResultado==RESULT_OK))
        {
            try{
                Uri uri = data.getData();
                cursor = getContentResolver().query(uri, new String[] {ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.Contacts.DISPLAY_NAME}, null, null, null);
                if(cursor != null && cursor.moveToNext())
                {
                    String fono = cursor.getString(0);
                    String nombre = cursor.getString(1);
                    ModeloContacto contactModel = new ModeloContacto(codigoSolicitud,nombre,fono);
                    agregar_no3.setText(fono);
                    boolean status = dataBaseHelper.addOne(contactModel,codigoSolicitud);
                    String ToastMsg = status==true?"Contacto agregado exitosamente":"Error al agregar contacto";
                    Toast.makeText(Amigos_Cercanos.this,ToastMsg,Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}



