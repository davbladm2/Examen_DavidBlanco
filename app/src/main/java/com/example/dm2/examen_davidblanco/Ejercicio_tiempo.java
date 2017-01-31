package com.example.dm2.examen_davidblanco;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ejercicio_tiempo extends AppCompatActivity {

    private Button btnBilbo,btnVitoria,btnDonosti,btnVolver;
    private TextView tvTiempoActual,tvResultado;
    private Tiempo tiempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_tiempo);
        setTitle("El tiempo");

        btnBilbo=(Button)findViewById(R.id.btnBilbo);
        btnVitoria=(Button)findViewById(R.id.btnVitoria);
        btnDonosti=(Button)findViewById(R.id.btnDonosti);
        tvTiempoActual=(TextView) findViewById(R.id.tvTiempoActual);
        tvResultado=(TextView)findViewById(R.id.tvResultado);
        btnVolver=(Button)findViewById(R.id.btnVolver);
    }

    public void pulsado(View v){
        Intent intent;
        CargarXmlTask tarea=new CargarXmlTask();

        //Pulsar botón Bilbo
        if(v.getId()==R.id.btnBilbo){
            tarea.execute("http://xml.tutiempo.net/xml/8050.xml");
            tvTiempoActual.setText(tvTiempoActual.getText()+" "+btnBilbo.getText().toString());
        }

        //Pulsar botón Vitoria
        if(v.getId()==R.id.btnVitoria){
            tarea.execute("http://xml.tutiempo.net/xml/8043.xml");
            tvTiempoActual.setText(tvTiempoActual.getText()+" "+btnVitoria.getText().toString());
        }

        //Pulsar botón Donosti
        if(v.getId()==R.id.btnDonosti){
            tarea.execute("http://xml.tutiempo.net/xml/4917.xml");
            tvTiempoActual.setText(tvTiempoActual.getText()+" "+btnDonosti.getText().toString());
        }

        //Pulsar botón exit
        if(v.getId()==R.id.btnVolver){
            intent=new Intent(Ejercicio_tiempo.this,MainActivity.class);
            startActivity(intent);
        }
    }

    private class CargarXmlTask extends AsyncTask<String,Integer,Boolean> {
        protected Boolean doInBackground(String... params) {
            RssParserDom rs=new RssParserDom(params[0]);
            tiempo=rs.parse();
            return true;
        }

        protected void onPostExecute(Boolean result) {
            tvResultado.setText("Hora: "+tiempo.getHora()+"\n Temperatura: "+tiempo.getTemperatura()+"\n Esatdo del cielo: "+tiempo.getEstado());
        }
    }
}
