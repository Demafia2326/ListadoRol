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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ListadoActivity extends AppCompatActivity implements ListadoInterface.View{


    String TAG="Prueba01/ListadoActivity";
    private ListadoInterface.Presenter pre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        pre = new ListadoPresenter(this);

        FloatingActionButton fab = findViewById(R.id.fabAnadir);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Pulsando boton flotante");
                ListadoInterface.Presenter.onClickAdd();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Log.d(TAG,"Lanzando lista...");
                Intent intent = new Intent(ListadoActivity.this, BuscarActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings1:
                Log.i("ActionBar", "ORDENAR");
                return true;
            case R.id.action_settings2:
                Log.i("ActionBar", "CONFIGURACION");
                return true;
            case R.id.action_settings3:
                Log.i("Actionbar","AppCRUD");
            default:
                return super.onOptionsItemSelected(item);
        }
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


