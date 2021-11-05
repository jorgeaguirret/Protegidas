package com.ayminformatica.protegidas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarPrincipal extends AppCompatActivity implements View.OnClickListener {

    EditText nom,ap,us,pas,fon,dir,com,rol;
    Button reg,can;
    daoUsuarioPrincipal dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_principal);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.appbar_background));

        nom=(EditText)findViewById(R.id.RegNombre);
        ap=(EditText)findViewById(R.id.RegApellido);
        us=(EditText)findViewById(R.id.RegUser);
        pas=(EditText)findViewById(R.id.RegPass);
        fon=(EditText)findViewById(R.id.RegFono);
        dir=(EditText)findViewById(R.id.RegDireccion);
        com=(EditText)findViewById(R.id.RegComuna);
        rol = (EditText) findViewById(R.id.RegRol);
        reg=(Button)findViewById(R.id.btnRegRegistrar);
        //can=(Button)findViewById(R.id.btnRegCancelar);

        reg.setOnClickListener(this);
        //can.setOnClickListener(this);

        dao=new daoUsuarioPrincipal(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegRegistrar:

                UsuarioPrincipal u=new UsuarioPrincipal();

                u.setNombre(nom.getText().toString());
                u.setApellido(ap.getText().toString());
                u.setUsuario(us.getText().toString());
                u.setPassword(pas.getText().toString());
                u.setTelefono(fon.getText().toString());
                u.setDireccion(dir.getText().toString());
                u.setComuna(com.getText().toString());
                u.setRol_id(Integer.parseInt(rol.getText().toString()));

               if (!u.isNull()){
                    Toast.makeText(this,"¡Error! Todos los campos son obligatorios...",Toast.LENGTH_LONG).show();
                }else if(dao.insertUsuario(u)){
                    //Toast.makeText(this,"Usuario Registrado Exitosamente",Toast.LENGTH_LONG).show();
                   Toast.makeText(this,"Por ser la primera vez, Sugerimos que Reinicies la aplicacion para que el GPS funcione correctamente",Toast.LENGTH_LONG).show();

                   Intent i2=new Intent(RegistrarPrincipal.this,MainActivity.class);
                    startActivity(i2);
                    finish();
                }else {
                    Toast.makeText(this,"El usuario '"+ u.Usuario+"' ya esta registrado!!",Toast.LENGTH_LONG).show();
                }

                break;

               /*

            case R.id.btnRegCancelar:
                Intent i = new Intent(RegistrarPrincipal.this,MainActivity.class);
                startActivity(i);
                finish();
                break;*/

        }
    }
}