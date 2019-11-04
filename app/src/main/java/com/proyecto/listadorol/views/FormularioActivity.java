package com.proyecto.listadorol.views;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.proyecto.listadorol.R;

public class FormularioActivity extends AppCompatActivity {

    String TAG;
    ImageButton button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] letra = {"Humano","Enano","Elfo","Mediano"};
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, letra));




    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    @Override
    protected void onStop(){
        super.onStop();
        this.TAG = this.TAG + " Stop";
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        this.TAG = this.TAG + " Destroy";
    }


    @Override
    protected void onStart(){
        super.onStart();
        String TAG = "aniGRUD/MainActivitiy start";
    }


    @Override
    protected void onPause(){
        super.onPause();
        String TAG = "aniGRUD/MainActivitiy pause";
    }


    @Override
    protected void onRestart(){
        super.onRestart();
        String TAG = "aniGRUD/MainActivitiy Restart";
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
