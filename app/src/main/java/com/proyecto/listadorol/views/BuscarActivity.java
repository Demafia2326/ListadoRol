package com.proyecto.listadorol.views;

import android.app.DatePickerDialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.proyecto.listadorol.Presenters.BuscarPresenter;
import com.proyecto.listadorol.Presenters.FormularioPresenter;
import com.proyecto.listadorol.R;
import com.proyecto.listadorol.interfaces.BuscarInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class BuscarActivity extends AppCompatActivity {

    String TAG;

    private BuscarInterface.Presenter presenter;
    private EditText N,F;
    private Spinner G;
    private String p, p2, p3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new BuscarPresenter((BuscarInterface.View) this);



        N= findViewById(R.id.name_buscar);
        G= findViewById(R.id.raza_buscar);
        /*F=Busquedafecha;*/




        Button Buscar = findViewById(R.id.Buscar);
        Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String spinnerText = G.getSelectedItem().toString();

                p=N.getText().toString();
                p2=spinnerText;
                p3=F.getText().toString();
                Log.d("en buscar", spinnerText);
                presenter.onClickAdd();


            }
        });




    }

    private static final String CERO = "0";
    private static final String BARRA = "/";



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
