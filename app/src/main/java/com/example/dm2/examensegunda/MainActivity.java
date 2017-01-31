package com.example.dm2.examensegunda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void act1 (View v)
    {
        Intent intent = new Intent(MainActivity.this, Actividad1.class);
        startActivity(intent);
    }
    public void act2 (View v)
    {
        Intent intent = new Intent(MainActivity.this, Actividad2.class);
        startActivity(intent);
    }
    public void act3 (View v)
    {
        Intent intent = new Intent(MainActivity.this, Actividad3.class);
        startActivity(intent);
    }
    public void salir (View v)
    {
        finish();
    }
}
