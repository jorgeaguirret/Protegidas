package com.ayminformatica.protegidas;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BackgroundService extends Service {


    static int nivel_bateria =0;
    static int automatic_alert_battery=0;
    double actualLat, actualLong;
    SeguimientoUbicacion seguimientoUbicacion, ubicacion;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("Entered the background service...");
        batterylevel();
        System.out.println("Battery level hai "+BackgroundService.nivel_bateria);

        ubicacion =new SeguimientoUbicacion(BackgroundService.this);
        seguimientoUbicacion = new SeguimientoUbicacion(BackgroundService.this);


        if (seguimientoUbicacion.canGetLocation()) {


            double longitude = seguimientoUbicacion.getLongitud();
            double latitude = seguimientoUbicacion.getLatitud();
            this.actualLat =latitude;
            this.actualLong =longitude;
            System.out.println("lat:"+longitude+" - "+latitude);

            Toast.makeText(getApplicationContext(), "Longitude:" + Double.toString(longitude) + "\nLatitude:" + Double.toString(latitude), Toast.LENGTH_SHORT).show();
        } else {

            seguimientoUbicacion.showSettingsAlert();
        }

//        currLat=MainActivity.currLat;
//        currLong=MainActivity.currLong;
//        double homeLat=HomeLocation.homeLat;
//        double homeLong=HomeLocation.homeLong;
//        String diff = String.format("%.2f",distance(currLat,homeLat,currLong,homeLong));

//        DataBaseHelper dataBaseHelper = new DataBaseHelper(BackgroundService.this);
//        automatic_alert_battery = dataBaseHelper.getAutomatic_alert_battery();

        if(nivel_bateria <=62) {
            automatic_alert();
            Toast.makeText(BackgroundService.this,"background service",Toast.LENGTH_SHORT).show();

        }
        return START_STICKY;
    }

    private void automatic_alert() {
    batterylevel();

        final ArrayList<String> phone= new ArrayList<>();
        final ArrayList<String> name= new ArrayList<>();

        DataBaseHelper dataBaseHelper = new DataBaseHelper(BackgroundService.this);
        List<ContactModel> everyone = dataBaseHelper.getEveryone();
        System.out.println(everyone.toString());
        if(!everyone.isEmpty()) {
            phone.add(everyone.get(0).getFono());
            name.add(everyone.get(0).getNombre());
            phone.add(everyone.get(1).getFono());
            name.add(everyone.get(1).getNombre());
            phone.add(everyone.get(2).getFono());
            name.add(everyone.get(2).getNombre());
        }
        String loc = "https://maps.google.com/?q="+ actualLat +","+ actualLong;

        String msg_temp="Sent from SAFETY BATTERY ALERT.  My battery is about to die (Automatic alert).\n Battery: "+ nivel_bateria +"%.\n Current location: "+loc;
//        AlertModel alertModel = new AlertModel(-1,battery_level,loc,msg_temp,name.get(0),name.get(1),name.get(2),phone.get(0),phone.get(1),phone.get(2));
//        boolean success = dataBaseHelper.addOneAlert(alertModel);
//        String successMsg= success==true?"Added to database":"Error occurred";
//        SMS.sendSMS(phone,msg_temp);
        System.out.println("Sending "+msg_temp);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void batterylevel(){
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
                nivel_bateria = level;
                //battery.setText(String.valueOf(level) + "%");
            }
        };
        IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mBatInfoReceiver, batteryLevelFilter);
    }


    public static double distance(double lat1,
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

}
