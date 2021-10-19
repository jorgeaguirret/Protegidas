package com.ayminformatica.protegidas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Panel_Alerta extends AppCompatActivity {
    TextView tv;
    Button alerta;
    static int nivel_bateria =0;
    private TextView battery;
    double actualLat, actualLong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_alerta);



        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.appbar_background));

        //battery = (TextView)this.findViewById(R.id.text1);
        nivel_bateria();



        //-------------------SPEED MESSAGES--------------------
        Button boton1 = findViewById(R.id.emergencia);
        Button boton2 = findViewById(R.id.ayudame);
        Button boton3 = findViewById(R.id.ven_aqui_ahora);
        Button boton4 = findViewById(R.id.llama_a_la_policia);
        ImageButton boton5 = findViewById(R.id.llamada_de_emergencia);
        final EditText mensaje = findViewById(R.id.editText2);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje.setText("Emergencia!!!");
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje.setText("Ayudame!!!");
            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje.setText("Ven aqui ahora!!!");
            }
        });


        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje.setText("Llama a la policia");
            }
        });


        //------------------speed dial--------------------

        boton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSpeedDial();
            }
        });





        tv = findViewById(R.id.loc);
        alerta = findViewById(R.id.send_alert);
        actualLat =MainActivity.actualLat;
        actualLong =MainActivity.actualLong;
        double homeLat= UbicacionCasa.homeLat;
        double homeLong= UbicacionCasa.homeLong;
        String diff = String.format("%.2f", distancia(actualLat,homeLat, actualLong,homeLong));
        // tv.setText("Distance between home and your current location is "+diff+" km");




        alerta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final ArrayList<String> fono= new ArrayList<>();
                final ArrayList<String> nombre= new ArrayList<>();

                DataBaseHelper dataBaseHelper = new DataBaseHelper(Panel_Alerta.this);
                List<ModeloContacto> everyone = dataBaseHelper.getEveryone();
                System.out.println(everyone.toString());
                if(!everyone.isEmpty()) {
                    //-----Solved the problem of app crashing with less than 3 contacts saved
                    try {
                        for(int i=0; i<3; i++) {
                            if(everyone.size()>i) {
                                fono.add(everyone.get(i).getFono());
                                nombre.add(everyone.get(i).getNombre());
                            }
                            else{
                                fono.add(null);
                                nombre.add(null);
                            }
                        }
//                        phone.add(everyone.get(1).getPhone());
//                        name.add(everyone.get(1).getName());
//                        phone.add(everyone.get(2).getPhone());
//                        name.add(everyone.get(2).getName());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

                String msg_temp="";
                String tipo_mensaje = mensaje.getText().toString();
                String ubicacion = "https://maps.google.com/?q="+ actualLat +","+ actualLong;
                System.out.println("Tipo de mensaje: "+tipo_mensaje);
                if(nivel_bateria <=10)
                {
                    msg_temp="Sent from PERSONAL SAFETY ALERTZ." + tipo_mensaje+" My battery is about to die (Automatic alert).\nBateria: "+ nivel_bateria +"%.\nUbicacion:  "+ubicacion;
                }
                else
                {
                    msg_temp="Sent from PERSONAL SAFETY ALERTZ." + tipo_mensaje+" (Manual Alert).\nBateria: "+ nivel_bateria +"%.\nUbicacion:  "+ubicacion;
                }
                ModeloAlerta alertModel = new ModeloAlerta(-1, nivel_bateria,ubicacion,msg_temp,nombre.get(0),nombre.get(1),nombre.get(2),fono.get(0),fono.get(1),fono.get(2));
                boolean success = dataBaseHelper.addOneAlert(alertModel);
                String successMsg= success==true?"Agregado a la Base de Datos":"Ocurrio un error";
                Toast.makeText(Panel_Alerta.this,successMsg,Toast.LENGTH_LONG).show();
                SMS.sendSMS(fono,msg_temp);
                showMessageOKCancel("El mensaje sent successfully to your trusted contacts. Stay safe \uD83D\uDE00");
            }
        });






        //-----------MENU NAVEGACION-----------

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.dashboard:
                        return true;

                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(),
                                SobreNosotros.class));
                        overridePendingTransition(0,0);
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

    private void nivel_bateria(){
        BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                context.unregisterReceiver(this);
                int raw_nivel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
                int nivel =-1;
                if(raw_nivel>=0 && scale>0){
                    nivel = (raw_nivel*100)/scale;
                }
                nivel_bateria = nivel;
                //battery.setText(String.valueOf(level) + "%");
            }
        };
        IntentFilter filtroNivelBateria = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mBatInfoReceiver, filtroNivelBateria);
    }

    public static double distancia(double lat1,
                                   double lat2, double lon1,
                                   double lon2)
    {

        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return(c * r);
    }


    //Mensaje de pantalla cuando se envia la alerta

    private void showMessageOKCancel(String Mensaje) {
        new android.app.AlertDialog.Builder(Panel_Alerta.this)
                .setMessage(Mensaje)
                .setPositiveButton("CERRAR",null)
                .create()
                .show();
    }


    public void openSpeedDial()
    {
        Intent intent = new Intent(this, Llamada_emergencia.class);
        startActivity(intent);
    }
}