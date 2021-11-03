package com.ayminformatica.protegidas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.widget.TextView;
import android.widget.Toast;


import android.annotation.TargetApi;
import android.os.Build;

import java.util.ArrayList;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.CALL_PHONE;

public class MainActivity extends AppCompatActivity {

    private ArrayList permisosParaSolicitar;
    private ArrayList permisosRechazados = new ArrayList();
    private ArrayList permisos = new ArrayList();
    public static double actualLong =0;
    public static double actualLat =0;

    private final static int ALL_PERMISSIONS_RESULT = 101;
    SeguimientoUbicacion seguimientoUbicacion, su;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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





        su =new SeguimientoUbicacion(MainActivity.this);
        seguimientoUbicacion = new SeguimientoUbicacion(MainActivity.this);


        if (seguimientoUbicacion.canGetLocation()) {


            double longitud = seguimientoUbicacion.getLongitud();
            double latitud = seguimientoUbicacion.getLatitud();
            this.actualLat =latitud;
            this.actualLong =longitud;
            System.out.println("lat:"+longitud+" - "+latitud);

            //Coordenadas mostradas al inicio de la app
/*Aqui controlo el mensaje de inicio*/       Toast.makeText(getApplicationContext(), "Mi Longitud es:  " + Double.toString(longitud) + "\nMi Latitud es:  " + Double.toString(latitud), Toast.LENGTH_LONG).show();
        } else {

            seguimientoUbicacion.showSettingsAlert();
        }










//        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
//                .getBoolean("isFirstRun", true);
//
//        if (isFirstRun) {
//            //show start activity
//
//            startActivity(new Intent(MainActivity.this, HomeLocation.class));
//            Toast.makeText(MainActivity.this, "First Run", Toast.LENGTH_LONG)
//                    .show();
//        }
//
//
//        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
//                .putBoolean("isFirstRun", false).commit();
        if(UbicacionCasa.homeLong==0 && UbicacionCasa.homeLat==0) {
            Intent myIntent = new Intent(getApplicationContext(), UbicacionCasa.class);
            startActivityForResult(myIntent, 0);
            onBackPressed();
        }
        else {
            Intent myIntent = new Intent(getApplicationContext(), Panel_Alerta.class);
            startActivityForResult(myIntent, 0);
        }
        tv = findViewById(R.id.loc);
        double currLat=MainActivity.actualLat;
        double currLong=MainActivity.actualLong;
        double homeLat= UbicacionCasa.homeLat;
        double homeLong= UbicacionCasa.homeLong;

        // tv.setText("Current data"+currLat+"-"+currLong+". Home data "+homeLat+"-"+homeLong);

    }

    private ArrayList findUnAskedPermissions(ArrayList wanted) {
        ArrayList result = new ArrayList();

        for (Object perm : wanted) {
            if (!hasPermission((String) perm)) {
                result.add(perm);
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


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {

            case ALL_PERMISSIONS_RESULT:
                for (Object perms : permisosParaSolicitar) {
                    if (!hasPermission((String) perms)) {
                        permisosRechazados.add(perms);
                    }
                }

                if (permisosRechazados.size() > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale((String) permisosRechazados.get(0))) {
                            showMessageOKCancel("Estos permisos son obligatorios para la aplicación. Por favor, permite el acceso",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions((String[]) permisosRechazados.toArray(new String[permisosRechazados.size()]), ALL_PERMISSIONS_RESULT);
                                            }
                                        }
                                    });
                            return;
                        }
                    }

                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancelar", null)
                .create()
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        seguimientoUbicacion.stopListener();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent myIntent = new Intent(getApplicationContext(), Panel_Alerta.class);
        startActivityForResult(myIntent, 0);
    }
}