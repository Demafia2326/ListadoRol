package com.proyecto.listadorol.views;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.proyecto.listadorol.Presenters.FormularioPresenter;
import com.proyecto.listadorol.R;
import com.proyecto.listadorol.interfaces.FormularioInterface;
import com.proyecto.listadorol.models.PeopleModel;
import com.proyecto.listadorol.models.PersonMin;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FormularioActivity extends AppCompatActivity implements FormularioInterface.View{

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
    ImageView galleryImageView;

    final private int CODE_READ_EXTERNAL_STORAGE_PERMISSION=123;

    Context myContext;


    private TextView errorname, errorclase, errortiempo, errorrarity;

    private FormularioPresenter presenter;

    private static final int REQUEST_SELECT_IMAGE = 201;
    private static final int REQUEST_CAPTURE_IMAGE = 200;

    final String pathFotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/demoAndroidImages/";

    private Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        presenter = new FormularioPresenter(this);

        myContext=this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.raza);
        String[] letra = {"Humano","Enano","Elfo","Mediano"};
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, letra));

        //setContentView(R.layout.activity_formulario);
        etFecha = (EditText) findViewById(R.id.fecha);
        ibObtenerFecha = (ImageButton) findViewById(R.id.id_obtener_fecha);
        ibObtenerFecha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.id_obtener_fecha:
                        obtenerFecha();
                        break;
                }
            }
        });

        galleryImageView = (ImageView) findViewById(R.id.imagen_R);
        //galleryImageView.setClickable(true);
        galleryImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                presenter.onClickImage(myContext);

            }
        });


         EditText nombre = findViewById(R.id.name_text);
         EditText edad = findViewById(R.id.edad_text);
         EditText player_name = findViewById(R.id.player_text);
         EditText personalidad = findViewById(R.id.personalidad);
         Spinner raza = findViewById(R.id.raza);
         EditText fecha = findViewById(R.id.fecha);
         ImageView P=findViewById(R.id.imagen_R);
        Switch caos = findViewById(R.id.switch1);


        Button Guardar = findViewById(R.id.Guardar);
        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log.d(TAG, "Pulsando botón ....");
                //presenter.onClickA();
                PersonMin person= new PersonMin();

                person.setAvatar_name(nombre.getText().toString());
                person.setEdad(Integer.parseInt(edad.getText().toString()) );
                person.setPlayer_name(player_name.getText().toString());
                person.setPersonalidad(personalidad.getText().toString());
                person.setRaza(raza.getSelectedItem().toString() );
                person.setFecha(fecha.getText().toString());
                if (caos.isChecked()) {
                    person.setCaos(1);
                }else{
                    person.setCaos(0);
                }

                Bitmap bitmap = ((BitmapDrawable) P.getDrawable()).getBitmap();
                if (bitmap != null) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    String fotoEnBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                    person.setImagen(fotoEnBase64);
                }


                presenter.onClicksaveButton(person);
                presenter.onClickAdd();

            }
        });

        Button Borrar = findViewById(R.id.Borrar);
        Borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonMin person= new PersonMin();

                person.getId();

                PeopleModel.getInstance().delete(person);

            }
        });




        /*
        Guardar.setEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log.d(TAG, "Pulsando botón flotante...");
                PersonMin person= new PersonMin();
                pelicula.setNombre(N.getText().toString());
                pelicula.setDirector(D.getText().toString());
                pelicula.setGenero(G.toString());
                pelicula.setFecha(F.getText().toString());
                pelicula.setSinopsis(S.getText().toString());
                pelicula.setActores(A.getText().toString());

                Bitmap bitmap = ((BitmapDrawable) P.getDrawable()).getBitmap();
                if (bitmap != null) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    String fotoEnBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                    pelicula.setImg(fotoEnBase64);
                }


                presenter.onClickAddSave(pelicula);
                // presenter.onClickAdd();

                });

                presenter.onClickAdd();
            }

        });
        */




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


    public void onClicksaveButton(PersonMin p) {

    }


    @Override
    public void requestPermision() {
        ActivityCompat.requestPermissions(
                FormularioActivity.this,
                new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                CODE_READ_EXTERNAL_STORAGE_PERMISSION
        );
    }



    @Override
    public void selectPicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, getResources().getString(R.string.choose_image)),
                REQUEST_SELECT_IMAGE);
        // YES YOU CAN
    }

    @Override
    public void lanzarListado() {
        Intent intent = new Intent(FormularioActivity.this, ListadoActivity.class); // (Donde vienes, a donde vas)
        startActivity(intent);
        finish();
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == Activity.RESULT_OK) {
            // Se carga la imagen desde un objeto Bitmap
            Uri selectedImage = data.getData();
            String selectedPath = selectedImage.getPath();

            if (selectedPath != null) {
                // Se leen los bytes de la imagen
                InputStream imageStream = null;
                try {
                    imageStream = getContentResolver().openInputStream(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                // Se transformam los bytes de la imagen a un Bitmap
                Bitmap bmp = BitmapFactory.decodeStream(imageStream);

                // Se carga el Bitmap en el ImageView
                ImageView imageView = findViewById(R.id.imagen_R);
                imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp,120,120,false));
            }
        }
    }








    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case CODE_READ_EXTERNAL_STORAGE_PERMISSION:
                presenter.resultPermission(grantResults[0]);


                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
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
