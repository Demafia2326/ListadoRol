package com.proyecto.listadorol.views;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.proyecto.listadorol.Presenters.ListadoPresenter;
import com.proyecto.listadorol.R;
import com.proyecto.listadorol.interfaces.ListadoInterface;
import com.proyecto.listadorol.models.PeopleModel;
import com.proyecto.listadorol.models.PersonMin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity implements ListadoInterface.View{


    String TAG="Prueba01/ListadoActivity";
    private ListadoInterface.Presenter pre;

    private ArrayList<PersonMin> items;
    //private RecyclerView reciclerView;*/
    private int n;


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
                pre.onClickAdd();
            }
        });


        // Inicializa el RecyclerView
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listadoRecyclerView);

        PersonAdapter adaptador=new PersonAdapter(pre.getAllPerson());

        // Crea el Adaptador con los datos de la lista anterior
        //PersonAdapter adaptador = new PersonAdapter(items);



        items = pre.getAllPerson();
        adaptador=new PersonAdapter(items);

        adaptador.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                // Acción al pulsar el elemento
                int position = recyclerView.getChildAdapterPosition(v);
                Log.d(TAG," Click RV"+position+" "+items.get(position).getId().toString());
                pre.onClicRecyclerView(items.get(position).getId());
            }
        });

        // Asocia el Adaptador al RecyclerView
        recyclerView.setAdapter(adaptador);

        // Muestra el RecyclerView en vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        if(n==1) {
            Bundle extras = getIntent().getExtras();
            String d1 = extras.getString("1");
            String d2 = extras.getString("2");
            String d3 = extras.getString("3");

            items = pre.BuscarPersona(d1, d2, d3);
        }else{
            items = pre.getAllPerson();

        }

    }


    @Override
    public void lanzarFormulario(int id) {
        Log.d(TAG,"Lanzando lista...");
        if(id==-1){
            Intent intent = new Intent(ListadoActivity.this,
                    FormularioActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(ListadoActivity.this,
                    FormularioActivity.class);

            startActivity(intent);
        }


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


