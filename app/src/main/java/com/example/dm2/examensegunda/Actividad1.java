package com.example.dm2.examensegunda;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Actividad1 extends AppCompatActivity {

    private TextView lblactual,lblhora,lbltemperatura,lblestado;
    private ArrayList<Pronostico> pronosticos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad1);
        lblactual=(TextView)findViewById(R.id.lblactual);
        lblhora=(TextView)findViewById(R.id.lblhora);
        lbltemperatura=(TextView)findViewById(R.id.lbltemperatura);
        lblestado=(TextView)findViewById(R.id.lblestado);
    }

    public void bilbao (View v)
    {
        lblactual.setText("Tiempo actual en: BILBO-BILBAO");
        CargarXmlTask tarea = new CargarXmlTask();
        tarea.execute("http://xml.tutiempo.net/xml/8050.xml");
    }
    public void vitoria (View v)
    {
        lblactual.setText("Tiempo actual en: VITORIA-GASTEIZ");
        CargarXmlTask tarea = new CargarXmlTask();
        tarea.execute("http://xml.tutiempo.net/xml/8043.xml");
    }
    public void donostia (View v)
    {
        lblactual.setText("Tiempo actual en: DONOSTIA-SAN SEBASTIAN");
        CargarXmlTask tarea = new CargarXmlTask();
        tarea.execute("http://xml.tutiempo.net/xml/4917.xml");
    }

    public void volver (View v)
    {
        finish();
    }

    private class CargarXmlTask extends AsyncTask<String,Integer,Boolean> {

        protected Boolean doInBackground(String... params) {

            RssParserDom domparser =new RssParserDom(params[0]);

            pronosticos = domparser.parse();

            return true;
        }

        protected void onPostExecute(Boolean result) {

            lblhora.setText("Hora: "+pronosticos.get(0).getHora());
            lbltemperatura.setText("Temperatura: "+pronosticos.get(0).getTemperatura());
            lblestado.setText("Estado del cielo: "+pronosticos.get(0).getEstado());
        }

    }
}
