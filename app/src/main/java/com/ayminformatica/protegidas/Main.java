package com.ayminformatica.protegidas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends AppCompatActivity implements View.OnClickListener {

    EditText user,pass;
    Button btnEntrar,btnRegistrar;
    daoUsuarioPrincipal dao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.appbar_background));

        user=(EditText)findViewById(R.id.User);
        pass=(EditText)findViewById(R.id.Pass);
        btnEntrar=(Button)findViewById(R.id.btnEntrar);
        btnRegistrar=(Button)findViewById(R.id.btnRegistrar);


        btnEntrar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);


        dao=new daoUsuarioPrincipal(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEntrar:
                String u=user.getText().toString();
                String p=pass.getText().toString();
                if (u.equals("")&&p.equals("")){
                    Toast.makeText(this,"error: campos vacios",Toast.LENGTH_LONG).show();
                }else if (dao.login(u,p)==1){
                    ModeloUsuarioPrincipal ux=dao.getUsuario(u,p);
                    Toast.makeText(this,"Datos correctos",Toast.LENGTH_LONG).show();
                    Intent i2 = new Intent(Main.this,Panel_Alerta.class);
                    i2.putExtra("id",ux.getUsuariop_id());
                    startActivity(i2);

                }else {
                    Toast.makeText(this,"Usuario o Password incorrectos",Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btnRegistrar:
                Intent i = new Intent(Main.this, RegistrarPrincipal.class);
                startActivity(i);
                break;

            /*case R.id.btnRegistrarcc:
                Intent i2 = new Intent(Main.this, RegistrarCirculoCercano.class);
                startActivity(i2);
                break;*/
        }
    }
}