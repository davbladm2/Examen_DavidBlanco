package com.example.dm2.examen_davidblanco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnExit,btnTiempo,btnAtomicos,btnMultimedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("EXAMEN PROM");

        btnTiempo=(Button) findViewById(R.id.btnTiempo);
        btnAtomicos=(Button) findViewById(R.id.btnAtomicos);
        btnMultimedia=(Button)findViewById(R.id.btnMultimedia);
        btnExit=(Button)findViewById(R.id.btnExit);
    }

    public void pulsado(View v){
        Intent intent;
        //Pulsar botón tiempo
        if(v.getId()==R.id.btnTiempo){
            intent=new Intent(MainActivity.this,Ejercicio_tiempo.class);
            startActivity(intent);
        }

        //Pulsar botón atómicos
        if(v.getId()==R.id.btnAtomicos){
            intent=new Intent(MainActivity.this,Ejercicio_atomicos.class);
            startActivity(intent);
        }

        //Pulsar botón multimedia
        if(v.getId()==R.id.btnMultimedia){
            intent=new Intent(MainActivity.this,Ejercicio_multimedia.class);
            startActivity(intent);
        }

        //Pulsar botón exit
        if(v.getId()==R.id.btnExit){
            finish();
        }
    }
}
