package com.ayminformatica.protegidas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String CONTACTO_TABLE = "CONTACTO_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMNA_NOMBRE_CONTACTO = "NOMBRE_CONTACTO";
    public static final String COLUMNA_FONO_CONTACTO = "FONO_CONTACTO";
    public static final String COLUMNA_CONTACTO_ACTIVO = "CONTACTO_ACTIVO";
    public static final String TABLA_ALERTA = "TABLA_ALERTA";
    public static final String ALERTA_ID = "ALERTA_ID";
    public static final String BATERIA = "BATERIA";
    public static final String UBICACION = "UBICACION";
    public static final String MENSAJE_ALERTA = "MENSAJE_ALERTA";
    public static final String HORA_CREADA = "HORA_CREADA";
    public static final String NOMBRE_CONTACTO1 = "NOMBRE_CONTACTO1";
    public static final String FONO_CONTACTO1 = "FONO_CONTACTO1";
    public static final String NOMBRE_CONTACTO2 = "NOMBRE_CONTACTO2";
    public static final String FONO_CONTACTO2 = "FONO_CONTACTO2";
    public static final String NOMBRE_CONTACTO3 = "NOMBRE_CONTACTO3";
    public static final String FONO_CONTACTO3 = "FONO_CONTACTO3";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "contacto.db", null, 1);
    }

    //called when first time database is accessed
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CONTACTO_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " + COLUMNA_NOMBRE_CONTACTO + " TEXT, " + COLUMNA_FONO_CONTACTO + " TEXT)";
        db.execSQL(createTableStatement);
        String createAlertTable = "CREATE TABLE " + TABLA_ALERTA + "(" + ALERTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + BATERIA + " INT," + UBICACION + " TEXT, " + MENSAJE_ALERTA + " TEXT," + HORA_CREADA + " DATETIME DEFAULT CURRENT_TIMESTAMP, " + NOMBRE_CONTACTO1 + " TEXT, " + FONO_CONTACTO1 + " TEXT, " + NOMBRE_CONTACTO2 + " TEXT, " + FONO_CONTACTO2 + " TEXT, " + NOMBRE_CONTACTO3 + " TEXT, " + FONO_CONTACTO3 + " TEXT)";
        db.execSQL(createAlertTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(ContactModel contactModel,int id){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        String checkQuery="SELECT "+ COLUMNA_FONO_CONTACTO +" FROM "+CONTACTO_TABLE+" WHERE "+COLUMN_ID+"="+id;
        Cursor cursor = db.rawQuery(checkQuery,null);
        boolean exists = (cursor.getCount() > 0);
        long insert;
        cv.put(COLUMNA_NOMBRE_CONTACTO,contactModel.getNombre());
        cv.put(COLUMNA_FONO_CONTACTO,contactModel.getFono());
        if(exists){
            insert= db.update(CONTACTO_TABLE,cv,COLUMN_ID+"= ? ",new String[]{String.valueOf(id)});
        }
        else {
            insert = db.insert(CONTACTO_TABLE, null, cv);
            System.out.println("uno agregado "+contactModel.getNombre());
        }
        return insert==-1?false:true;
    }

    public boolean addOneAlert(AlertModel alertModel){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(BATERIA,alertModel.getBateria());
        cv.put(UBICACION,alertModel.getUbicacion());
        cv.put(MENSAJE_ALERTA,alertModel.getMensajeAlerta());
        cv.put(NOMBRE_CONTACTO1,alertModel.getNombreContacto1());
        cv.put(FONO_CONTACTO1,alertModel.getFonoContacto1());
        cv.put(NOMBRE_CONTACTO2,alertModel.getNombreContacto2());
        cv.put(FONO_CONTACTO2,alertModel.getFonoContacto2());
        cv.put(NOMBRE_CONTACTO3,alertModel.getNombreContacto3());
        cv.put(FONO_CONTACTO3,alertModel.getFonoContacto3());

        long insert = db.insert(TABLA_ALERTA, null, cv);
        return insert==-1?false:true;
    }

    public List<ContactModel> getEveryone(){
        List<ContactModel> returnList = new ArrayList<>();

        String queryString= "SELECT * FROM "+CONTACTO_TABLE;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);
        //check if database contains entries
        if(cursor.moveToFirst()){
            //loop through cursor and create new contact object
            do{
                int contactID = cursor.getInt(0);
                String nombreContacto = cursor.getString(1);
                String fonoContacto = cursor.getString(2);
                System.out.println("getting "+fonoContacto);
                ContactModel nuevoContacto = new ContactModel(contactID,nombreContacto,fonoContacto);
                returnList.add(nuevoContacto);
            }while (cursor.moveToNext());
        }else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<AlertModel> getAllAlerts(){
        List<AlertModel> returnList = new ArrayList<>();

        String queryString= "SELECT * FROM "+ TABLA_ALERTA;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);
        //check if database contains entries
        if(cursor.moveToFirst()){
            //loop through cursor and create new contact object
            do{
                int alertID = cursor.getInt(0);
                int bateria = cursor.getInt(1);
                String ubicacion = cursor.getString(2);
                String mensajeAlerta = cursor.getString(3);
                String horaCreada = cursor.getString(4);
                String nombreContacto1 = cursor.getString(5);
                String fonoContacto1 = cursor.getString(6);
                String nombreContacto2 = cursor.getString(7);
                String fonoContacto2 = cursor.getString(8);
                String nombreContacto3 = cursor.getString(9);
                String fonoContacto3 = cursor.getString(10);

                AlertModel newAlert = new AlertModel(alertID,bateria,ubicacion,mensajeAlerta,horaCreada,nombreContacto1,nombreContacto2,nombreContacto3,fonoContacto1,fonoContacto2,fonoContacto3);
                returnList.add(newAlert);
            }while (cursor.moveToNext());
        }else{

        }
        cursor.close();
        db.close();
        return returnList;
    }
}
