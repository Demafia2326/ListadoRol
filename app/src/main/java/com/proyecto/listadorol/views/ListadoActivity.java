package com.proyecto.listadorol.views;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.proyecto.listadorol.Presenters.ListadoPresenter;
import com.proyecto.listadorol.R;
import com.proyecto.listadorol.interfaces.ListadoInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

public class ListadoActivity extends AppCompatActivity implements ListadoInterface.View{


    String TAG="Prueba01/ListadoActivity";
    private ListadoInterface.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        presenter = new ListadoPresenter(this);

        FloatingActionButton fab = findViewById(R.id.fabAnadir);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Pulsando boton flotante");
                presenter.onClickAdd();
            }
        });
    }


    @Override
    public void lanzarFormulario() {
        Log.d(TAG,"Lanzando lista...");
        Intent intent = new Intent(ListadoActivity.this, FormularioActivity.class);
        startActivity(intent);
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


