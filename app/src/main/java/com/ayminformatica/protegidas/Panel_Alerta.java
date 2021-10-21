package com.ayminformatica.protegidas;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.SEND_SMS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.BatteryManager;
import android.os.Build;
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
    TextView tv,nombre;
    Button alerta;
    static int nivel_bateria =0;
    private TextView battery;

    double actualLati, actualLongi;




    private ArrayList permisosParaSolicitar;
    private ArrayList permisosRechazados = new ArrayList();
    private ArrayList permisos = new ArrayList();
    public static double actualLong =0;
    public static double actualLat =0;

    private final static int ALL_PERMISSIONS_RESULT = 101;


    int id=0;
    ModeloUsuarioPrincipal u;
    daoUsuarioPrincipal dao;


//comentansdo nomas
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_alerta);

        permisos.add(ACCESS_FINE_LOCATION);
        permisos.add(ACCESS_COARSE_LOCATION);
        permisos.add(SEND_SMS);
        permisos.add(READ_CONTACTS);
        permisos.add(CALL_PHONE);

        permisosParaSolicitar = findUnAskedPermissions(permisos);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            if (permisosParaSolicitar.size() > 0)
                requestPermissions((String[]) permisosParaSolicitar.toArray(new String[permisosParaSolicitar.size()]), ALL_PERMISSIONS_RESULT);


        }


        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.appbar_background));

        permisos.add(ACCESS_FINE_LOCATION);
        permisos.add(ACCESS_COARSE_LOCATION);
        permisos.add(SEND_SMS);
        permisos.add(READ_CONTACTS);
        permisos.add(CALL_PHONE);

        permisosParaSolicitar = findUnAskedPermissions(permisos);
        //obtener los permisos que hemos solicitado antes pero que no se otorgan.
        // Almacenaremos esto en una lista global para acceder más tarde.


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            if (permisosParaSolicitar.size() > 0)
                requestPermissions((String[]) permisosParaSolicitar.toArray(new String[permisosParaSolicitar.size()]), ALL_PERMISSIONS_RESULT);
        }

        //battery = (TextView)this.findViewById(R.id.text1);
        nivel_bateria();

        nombre=(TextView)findViewById(R.id.nombreUsuario);

        Bundle b = getIntent().getExtras();
        id=b.getInt("id");
        dao=new daoUsuarioPrincipal(this);

        u=dao.getUsuarioById(id);

        nombre.setText(u.getNombre()+" "+u.getApellido());



        //-------------------MENSAJES RAPIDOS--------------------
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
            public void onClick(View v) { mensaje.setText("Ven a mi casa ahora!!!");
            }
        });


        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { mensaje.setText("Llama a la policia");
            }
        });


        //------------------BOTON LLAMADA RAPIDA--------------------

        boton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSpeedDial();
            }
        });





        tv = findViewById(R.id.loc);
        alerta = findViewById(R.id.send_alert);
        actualLati =MainActivity.actualLat;
        actualLongi =MainActivity.actualLong;
        double homeLat= UbicacionCasa.homeLat;
        double homeLong= UbicacionCasa.homeLong;
        String diff = String.format("%.2f", distancia(actualLati,homeLat, actualLongi,homeLong));
        // tv.setText("La distancia entre su casa y su ubicación actual es "+diff+" km");




        alerta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final ArrayList<String> fono= new ArrayList<>();
                final ArrayList<String> nombre= new ArrayList<>();

                DataBaseHelper dataBaseHelper = new DataBaseHelper(Panel_Alerta.this);
                List<ModeloContacto> everyone = dataBaseHelper.getEveryone();
                System.out.println(everyone.toString());
                if(!everyone.isEmpty()) {
                    //-----Se Resolvió el problema de falla de la aplicación con menos de 3 contactos guardados
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
                String ubicacion = "https://maps.google.com/?q="+ actualLati +","+ actualLongi;
                System.out.println("Tipo de mensaje: "+tipo_mensaje);
                if(nivel_bateria <=10)
                {
                    msg_temp="Enviado desde la app PROTEGIDAS." + tipo_mensaje+" Mi bateria se agotara (Alerta automatica).\nBateria: "+ nivel_bateria +"%.\nUbicacion:  "+ubicacion;
                }
                else
                {
                    msg_temp="Enviado desde la app PROTEGIDAS." + tipo_mensaje+" (Alerta Manual).\nBateria: "+ nivel_bateria +"%.\nUbicacion:  "+ubicacion;
                }
                ModeloAlerta alertModel = new ModeloAlerta(-1, nivel_bateria,ubicacion,msg_temp,nombre.get(0),nombre.get(1),nombre.get(2),fono.get(0),fono.get(1),fono.get(2));
                boolean success = dataBaseHelper.addOneAlert(alertModel);
                String successMsg= success==true?"Agregado a la Base de Datos":"Ocurrio un error";
                Toast.makeText(Panel_Alerta.this,successMsg,Toast.LENGTH_LONG).show();
                SMS.sendSMS(fono,msg_temp);
                showMessageOKCancel("El mensaje se envio satisfactoriamente a tus contactos. Mantente segura! \uD83D\uDE00");
            }
        });






        //-----------MENU NAVEGACION-----------

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.navPanelAlerta);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.navPanelAlerta:
                        return true;

                    case R.id.navSobreNosotros:
                        //startActivity(new Intent(getApplicationContext(),
                        //        SobreNosotros.class));
                        //overridePendingTransition(0,0);
                        Intent intent = new Intent(Panel_Alerta.this,SobreNosotros.class);
                        intent.putExtra("id",u.getUsuariop_id());
                        startActivity(intent);
                        return true;

                    case R.id.navAjustes:
                        //startActivity(new Intent(getApplicationContext(),
                        //        Ajustes.class));
                        //overridePendingTransition(0,0);
                        Intent intent2 = new Intent(Panel_Alerta.this,Ajustes.class);
                        intent2.putExtra("id",u.getUsuariop_id());
                        startActivity(intent2);
                        return true;

                    case R.id.navDescripcion:
                        //startActivity(new Intent(getApplicationContext(),
                          //      Descripcion.class));
                        //overridePendingTransition(0,0);
                        Intent intent3 = new Intent(Panel_Alerta.this,Descripcion.class);
                        intent3.putExtra("id",u.getUsuariop_id());
                        startActivity(intent3);
                        return true;

                    case R.id.navAmigosCercanos:
                        //startActivity(new Intent(getApplicationContext(),
                                //Amigos_Cercanos.class));
                        //overridePendingTransition(0,0);
                        Intent intent4 = new Intent(Panel_Alerta.this,Amigos_Cercanos.class);
                        intent4.putExtra("id",u.getUsuariop_id());
                        startActivity(intent4);
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

        // El módulo matemático contiene una función
        // llamado toRadians que se convierte de
        // grados a radianes.
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // formula de Haversine
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radio de la tierra en km.
        // Para las millas usar 3956.
        double r = 6371;

        // Calculando el resultado
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

    private ArrayList findUnAskedPermissions(ArrayList wanted) {
        ArrayList result = new ArrayList();

        for (Object perm : wanted) {
            if (!hasPermission((String) perm)) {
                result.add(perm);
                Toast.makeText(this,"Por ser la primera vez, Sugerimos que Reinicies la aplicacion para que el GPS funcione correctamente",Toast.LENGTH_LONG).show();
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }
}