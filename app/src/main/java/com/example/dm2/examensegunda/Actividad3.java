package com.example.dm2.examensegunda;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Actividad3 extends AppCompatActivity {

    private Spinner spinner1;
    private String valorSpinner1;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad3);
        spinner1=(Spinner)findViewById(R.id.spinner1);
        String[] datos=new String[]{"audio","disparo","explosion"};

        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,datos);
        spinner1.setAdapter(adaptador);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                valorSpinner1 = adapterView.getItemAtPosition(i).toString();
                reproducirsonido(valorSpinner1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void reproducirsonido(String valorSpinner1)
    {
        if (valorSpinner1.equals("audio"))
        {
            Toast toast = Toast.makeText(getApplicationContext(),"Reproduciendo audio",Toast.LENGTH_LONG);
            toast.show();
            mediaPlayer = MediaPlayer.create(this, R.raw.audio);
            mediaPlayer.start();
            mediaPlayer.setLooping(false);
        }
        if (valorSpinner1.equals("disparo"))
        {
            Toast toast = Toast.makeText(getApplicationContext(),"Reproduciendo disparo",Toast.LENGTH_LONG);
            toast.show();
            mediaPlayer = MediaPlayer.create(this, R.raw.disparo);
            mediaPlayer.start();
            mediaPlayer.setLooping(false);
        }
        if (valorSpinner1.equals("explosion"))
        {
            Toast toast = Toast.makeText(getApplicationContext(),"Reproduciendo explosion",Toast.LENGTH_LONG);
            toast.show();
            mediaPlayer = MediaPlayer.create(this, R.raw.explosion);
            mediaPlayer.start();
            mediaPlayer.setLooping(false);
        }
    }

    public void volver (View v)
    {
        finish();
    }
}
