package com.example.dm2.examen_davidblanco;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Ejercicio_atomicos extends AppCompatActivity {

    private EditText etElemento;
    private String elemento;
    private String TAG="Response";
    private SoapPrimitive resultado;
    private TextView tvDensidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_atomicos);
        etElemento=(EditText) findViewById(R.id.etElemento);
        tvDensidad=(TextView)findViewById(R.id.tvDensidad);
    }

    public void pulsado(View v){
        Intent intent;
        //Pulsar botón Información
        if(v.getId()==R.id.btnInformacion){
            elemento=etElemento.getText().toString();
            Log.e("Elemento"," "+elemento);
            AsyncCallWS task=new AsyncCallWS();
            task.execute();
        }

        //Pulsar botón volver
        if(v.getId()==R.id.btnVolver){
            intent=new Intent(Ejercicio_atomicos.this,MainActivity.class);
            startActivity(intent);
        }
    }

    public class AsyncCallWS extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            Log.i(TAG,"metodo onPreExecute");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.i(TAG,"metodo doInBackground");
            calcular();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.i(TAG,"metodo onPostExecute");
            tvDensidad.setText(resultado.toString());
        }

        private void calcular(){
            String SOAP_ACTION="http://www.webserviceX.NET/GetAtomicNumber";
            String METHOD_NAME="GetAtomicNumber";
            String NAMESPACE="http://www.webserviceX.NET";
            String URL="http://www.webservicex.net/periodictable.asmx?op=GetAtomicNumber";
            try{
                SoapObject request=new SoapObject(NAMESPACE,METHOD_NAME);
                request.addProperty("ElementName",elemento);
                SoapSerializationEnvelope soapEnvelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet=true;
                soapEnvelope.setOutputSoapObject(request);
                HttpTransportSE transport=new HttpTransportSE(URL);
                transport.call(SOAP_ACTION,soapEnvelope);
                resultado=(SoapPrimitive)soapEnvelope.getResponse();
            }catch (Exception ex){
                Log.e(TAG,"ERROR: "+ex.getMessage());
            }
        }
    }
}
