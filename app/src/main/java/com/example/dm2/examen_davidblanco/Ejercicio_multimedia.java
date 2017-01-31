package com.example.dm2.examen_davidblanco;

import android.content.Intent;
import android.database.DataSetObserver;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejercicio_multimedia extends AppCompatActivity {

    private Spinner spinner;
    private ArrayAdapter<String> adaptador;
    private final String[] arrSpinner=new String[3];
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_multimedia);
        setTitle("Sonidos Multimedia");

        spinner=(Spinner) findViewById(R.id.spinner);

        try {
            InputStream is = getResources().openRawResource(R.raw.texto_raw);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String linea=br.readLine();
            int cont=0;
            while(linea!=null){
                arrSpinner[cont]=linea.toString();
                linea=br.readLine();
                cont++;
            }
            is.close();
            adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrSpinner);
            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adaptador);
        } catch (IOException e) {
            Log.e("Ficheros", "ERROR!! al leer fichero desde el recurso");
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinner.getSelectedItem().toString().equalsIgnoreCase("audio")){
                    cargarAudio();
                }

                if(spinner.getSelectedItem().toString().equalsIgnoreCase("disparo")){
                    cargarDisparo();
                }

                if(spinner.getSelectedItem().toString().equalsIgnoreCase("explosion")){
                    cargarExplosion();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

    public void cargarAudio(){
        mp=MediaPlayer.create(this,R.raw.audio);
        mp.start();
    }
    public void cargarDisparo(){
        mp=MediaPlayer.create(this,R.raw.disparo);
        mp.start();
    }
    public void cargarExplosion(){
        mp=MediaPlayer.create(this,R.raw.explosion);
        mp.start();
    }

    public void pulsado(View v){
        Intent intent;
        //Pulsar botón volver
        if(v.getId()==R.id.btnVolver){
            intent=new Intent(Ejercicio_multimedia.this,MainActivity.class);
            startActivity(intent);
        }
    }

}
