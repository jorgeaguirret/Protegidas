package com.ayminformatica.protegidas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoUsuarioPrincipal {
    Context c;
    UsuarioPrincipal u;
    ArrayList<UsuarioPrincipal> lista;
    SQLiteDatabase sql;
    String bd="BDUsuarios";
    String tabla="create table if not exists usuarioprincipal(usuariop_id integer primary key autoincrement,  nombre text, apellido text, usuario text, pass text, telefono text, direccion text, comuna text, rol_id integer)";

    public daoUsuarioPrincipal(Context c){
        this.c=c;
        sql=c.openOrCreateDatabase(bd,c.MODE_PRIVATE,null);
        sql.execSQL(tabla);
        u=new UsuarioPrincipal();
    }


    public boolean insertUsuario(UsuarioPrincipal u){
        if (buscar(u.getUsuario())==0){
            ContentValues cv=new ContentValues();
            cv.put("nombre",u.getNombre());
            cv.put("apellido",u.getApellido());
            cv.put("usuario",u.getUsuario());
            cv.put("pass",u.getPassword());
            cv.put("telefono",u.getTelefono());
            cv.put("direccion",u.getDireccion());
            cv.put("comuna",u.getComuna());
            cv.put("rol_id",u.getRol_id());
            return (sql.insert("usuarioprincipal",null,cv)>0);
        }else {
            return false;
        }
    }

    public int buscar(String u){
        int x=0;
        lista=selectUsuario();
        for (UsuarioPrincipal us:lista) {
            if (us.getUsuario().equals(u)){
                x++;
            }
        }
        return x;
    }

    public ArrayList<UsuarioPrincipal> selectUsuario(){
        ArrayList<UsuarioPrincipal> lista=new ArrayList<UsuarioPrincipal>();
        lista.clear();
        Cursor cr=sql.rawQuery("select * from usuarioprincipal", null);
        if (cr!=null&&cr.moveToFirst()){
            do {
                UsuarioPrincipal u=new UsuarioPrincipal();
                u.setUsuariop_id(cr.getInt(0));
                u.setNombre(cr.getString(1));
                u.setApellido(cr.getString(2));
                u.setUsuario(cr.getString(3));
                u.setPassword(cr.getString(4));
                u.setTelefono(cr.getString(5));
                u.setDireccion(cr.getString(6));
                u.setComuna(cr.getString(7));
                u.setRol_id(cr.getInt(8));
                lista.add(u);
            }while (cr.moveToNext());
        }
        return lista;
    }

    public int login(String u, String p){
        int a=0;
        Cursor cr=sql.rawQuery("select * from usuarioprincipal", null);
        if (cr!=null&&cr.moveToFirst()){
            do {
                if (cr.getString(3).equals(u)&&cr.getString(4).equals(p)){
                    a++;
                }
            }while (cr.moveToNext());
        }
        return a;
    }

    public UsuarioPrincipal getUsuario(String u, String p){
        lista=selectUsuario();
        for (UsuarioPrincipal us: lista) {
            if (us.getUsuario().equals(u)&&us.getPassword().equals(p)){
                return us;
            }
        }
        return null;
    }

    public UsuarioPrincipal getUsuarioById(int usuariop_id){
        lista=selectUsuario();
        for (UsuarioPrincipal us: lista) {
            if (us.getUsuariop_id()==usuariop_id){
                return us;
            }
        }
        return null;
    }

    public boolean updateUsuario(UsuarioPrincipal u){
        ContentValues cv=new ContentValues();

        cv.put("nombre",u.getNombre());
        cv.put("apellido",u.getApellido());
        cv.put("usuario",u.getUsuario());
        cv.put("pass",u.getPassword());
        cv.put("telefono",u.getTelefono());
        cv.put("direccion",u.getDireccion());
        cv.put("comuna",u.getComuna());
        cv.put("rol_id",u.getRol_id());
        return (sql.update("usuarioprincipal",cv,"usuariop_id="+u.getUsuariop_id(),null)>0);
    }

    public boolean deleteUsuario(int usuariop_id){
        return (sql.delete("usuarioprincipal","usuariop_id"+usuariop_id,null)>0);
    }
}