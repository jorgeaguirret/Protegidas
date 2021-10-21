package com.ayminformatica.protegidas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;

public class Ajustes extends AppCompatActivity {

    Button btPicker, btHistorial, btUbicacionReal;
    TextView textview;
    int PLACE_PICKER_REQUEST = 1;
    //---------------------NOTIFICATION---------------------
    private TextView textView;
    private ProgressBar progressBar;
    private SeekBar seekBar;
    int id=0;
    ModeloUsuarioPrincipal u;
    daoUsuarioPrincipal dao;


    /*/--battery declaration-----
        int isCharging;

        static int battery_level=0;
        private Ringtone ringtone;


        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging=status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;



    public void batterylevel(){
        BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                context.unregisterReceiver(this);
                int raw_level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
                int level =-1;
                if(raw_level>=0 && scale>0){
                    level = (raw_level*100)/scale;
                }
                battery_level = level;
            }
        };
        IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mBatInfoReceiver, batteryLevelFilter);
         isCharging = BatteryManager.BATTERY_STATUS_CHARGING | BatteryManager.BATTERY_STATUS_FULL;
    }
*/
    //private TextView battery;
    Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ringtone = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));
        setContentView(R.layout.activity_ajustes);



        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.appbar_background));

        Bundle b = getIntent().getExtras();
        id=b.getInt("id");
        dao=new daoUsuarioPrincipal(this);
        u=dao.getUsuarioById(id);

        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressBar.setProgress(progress);
                textView.setText("" + progress + "%");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        //---------------------ALERTA CARGANDO-----------------









        //----------------RECOLECTOR DE UBICACIÓN---------------------

        btPicker = findViewById(R.id.bt_picker);
        btHistorial = findViewById(R.id.bt_historial);
        btUbicacionReal = findViewById(R.id.bt_Ubicacion);
        textview = findViewById(R.id.text_view);

        btPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(Ajustes.this),
                            PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });

        btHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Historial.class);
                startActivity(i);
            }
        });

        btUbicacionReal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivityGps.class);
                startActivity(i);
            }
        });


        //-----------------------MENU NAVEGACIÓN---------------------------

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.navAjustes);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navPanelAlerta:
                        //startActivity(new Intent(getApplicationContext(),
                        //        Panel_Alerta.class));
                        //overridePendingTransition(0, 0);
                        Intent intent = new Intent(Ajustes.this,Panel_Alerta.class);
                        intent.putExtra("id",u.getUsuariop_id());
                        startActivity(intent);
                        return true;

                    case R.id.navSobreNosotros:
                        //startActivity(new Intent(getApplicationContext(),
                        //        SobreNosotros.class));
                        //overridePendingTransition(0, 0);
                        Intent intent2 = new Intent(Ajustes.this,SobreNosotros.class);
                        intent2.putExtra("id",u.getUsuariop_id());
                        startActivity(intent2);
                        return true;

                    case R.id.navAjustes:
                        return true;

                    case R.id.navDescripcion:
                        //startActivity(new Intent(getApplicationContext(),
                        //        Descripcion.class));
                        //overridePendingTransition(0, 0);
                        Intent intent3 = new Intent(Ajustes.this,Descripcion.class);
                        intent3.putExtra("id",u.getUsuariop_id());
                        startActivity(intent3);
                        return true;

                    case R.id.navAmigosCercanos:
                        //startActivity(new Intent(getApplicationContext(),
                        //        Amigos_Cercanos.class));
                        //overridePendingTransition(0, 0);
                        Intent intent4 = new Intent(Ajustes.this,Amigos_Cercanos.class);
                        intent4.putExtra("id",u.getUsuariop_id());
                        startActivity(intent4);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int codigoSolicitud, int codigoResultado, @Nullable Intent data) {
        if(codigoSolicitud==PLACE_PICKER_REQUEST){
            if(codigoResultado==RESULT_OK){
                Place place = PlacePicker.getPlace(data, this);
                StringBuilder stringBuilder = new StringBuilder();
                String latitud = String.valueOf(place.getLatLng().latitude);
                String longitud = String.valueOf(place.getLatLng().longitude);
                stringBuilder.append("LATITUD: ");
                stringBuilder.append(latitud);
                stringBuilder.append("\nLONGITUD: ");
                stringBuilder.append(longitud);
                textview.setText(stringBuilder.toString());
            }
        }
    }
}

