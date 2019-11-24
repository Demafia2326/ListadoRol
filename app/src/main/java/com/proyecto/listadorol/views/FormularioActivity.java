package com.proyecto.listadorol.views;

import android.app.DatePickerDialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.proyecto.listadorol.Presenters.FormularioPresenter;
import com.proyecto.listadorol.Presenters.ListadoPresenter;
import com.proyecto.listadorol.R;
import com.proyecto.listadorol.interfaces.FormularioInterface;
import com.proyecto.listadorol.interfaces.ListadoInterface;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FormularioActivity extends AppCompatActivity {

    String TAG;
    ImageButton button;

    private static final String CERO = "0";
    private static final String BARRA = "/";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    //Widgets
    EditText etFecha;
    ImageButton ibObtenerFecha;

    private EditText nombre, clase, tiempo, rarity;
    private TextView errorname, errorclase, errortiempo, errorrarity;

    private FormularioPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        presenter = new FormularioPresenter();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] letra = {"Humano","Enano","Elfo","Mediano"};
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, letra));

        //setContentView(R.layout.activity_formulario);
        etFecha = (EditText) findViewById(R.id.et_mostrar_fecha_picker);
        ibObtenerFecha = (ImageButton) findViewById(R.id.ib_obtener_fecha);
        ibObtenerFecha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.ib_obtener_fecha:
                        obtenerFecha();
                        break;
                }
            }
        });



        Button Guardar = findViewById(R.id.Guardar);
        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log.d(TAG, "Pulsando botón flotante...");

                ListadoInterface.Presenter.onClickAdd();
            }
        });
        Guardar.setEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListadoInterface.Presenter.onClickAdd();
            }
        });

        nombre = findViewById(R.id.name_text);
        errorname = findViewById(R.id.error_name);
        errorname.setVisibility(View.INVISIBLE);
        nombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!presenter.validaNombre(nombre.getText())) {
                        errorname.setVisibility(View.VISIBLE);
                    } else {
                        errorname.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

    }




        @Override
        public boolean onSupportNavigateUp() {
            onBackPressed();
            return false;
        }

        @Override
        protected void onStop() {
            super.onStop();
            this.TAG = this.TAG + " Stop";
        }


        @Override
        protected void onDestroy() {
            super.onDestroy();
            this.TAG = this.TAG + " Destroy";
        }


        @Override
        protected void onStart() {
            super.onStart();
            String TAG = "aniGRUD/MainActivitiy start";
        }


        @Override
        protected void onPause() {
            super.onPause();
            String TAG = "aniGRUD/MainActivitiy pause";
        }


        @Override
        protected void onRestart() {
            super.onRestart();
            String TAG = "aniGRUD/MainActivitiy Restart";
        }

        @Override
        protected void onResume() {
            super.onResume();

        }

    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                etFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();
    }

    public boolean validateDate(CharSequence date){
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        // Input to be parsed should strictly follow the defined date format above.
        format.setLenient(false);

        try {
            format.parse(date.toString());
        } catch (ParseException e) {
            Log.d(TAG, "Fecha " + date + " no valida ");
            return false;
        }
        return true;
    }

    public void addTextChangedListener(EditText input, final TextInputLayout layout, final boolean validarFecha) {
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(validarFecha){
                    if (!validateDate(s)) {
                        layout.setError("Formato incorrecto");
                        layout.setErrorEnabled(true);
                    }
                    else{
                        layout.setError(null);
                        layout.setErrorEnabled(false);
                    }
                }
                else if (s.length() == 0) {
                    layout.setError("Campo obligatorio");
                    layout.setErrorEnabled(true);
                }
                else{
                    layout.setError(null);
                    layout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }




}
