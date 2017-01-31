package com.example.dm2.examensegunda;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class Actividad2 extends AppCompatActivity {

    private Button btninfo;
    private EditText txtelemento;
    private TextView lblsimbolo,lblnumero,lblpeso,lblpunto,lbldensidad;
    private String strelemento;
    private String resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);
        btninfo= (Button) findViewById(R.id.btninfo);
        txtelemento= (EditText) findViewById(R.id.txtelemento);
        strelemento=txtelemento.getText().toString();
        lblsimbolo= (TextView) findViewById(R.id.lblsimbolo);
        lblnumero= (TextView) findViewById(R.id.lblnumero);
        lblpeso= (TextView) findViewById(R.id.lblpeso);
        lblpunto= (TextView) findViewById(R.id.lblpunto);
        lbldensidad= (TextView) findViewById(R.id.lbldensidad);
        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String elemento= txtelemento.getText().toString();
                AsyncPost tarea=new AsyncPost();
                tarea.execute(elemento);
            }
        });
    }

    private class AsyncPost extends AsyncTask<String,Void,Void>
    {

        @Override
        protected Void doInBackground(String... params) {


            try {
                HttpURLConnection conn;
                URL url=new URL("http://www.webservicex.net/periodictable.asmx/GetAtomicNumber");

                //codificamos solo los valores de los parametros
                String param="ElementName="+ URLEncoder.encode(params[0],"UTF-8");

                conn= (HttpURLConnection) url.openConnection();

                //se estan cargamdo datos post si esta a true
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");

                //enviar el post
                PrintWriter out=new PrintWriter((conn.getOutputStream()));
                out.print(param);
                out.close();

                //construir la cadena para almacenar la respuesta del servidor
                String result="";

                //comenzar a escuchar el stream(flujo)
                Scanner inStream=new Scanner(conn.getInputStream());

                //procesa el stream(flujo) y lo almacena en un String

                while(inStream.hasNextLine())
                {
                    result=inStream.nextLine();
                    resultado+=result;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void result)
        {
            lblsimbolo.setText(lblsimbolo.getText().toString()+resultado);
        }
    }

    public void volver (View v)
    {
        finish();
    }
}
