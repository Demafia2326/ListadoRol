package com.proyecto.listadorol.Presenters;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.Editable;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.proyecto.listadorol.interfaces.BuscarInterface;
import com.proyecto.listadorol.interfaces.FormularioInterface;
import com.proyecto.listadorol.interfaces.ListadoInterface;
import com.proyecto.listadorol.models.PeopleModel;
import com.proyecto.listadorol.models.PersonMin;

import java.util.regex.Pattern;

public class FormularioPresenter implements FormularioInterface.Presenter, BuscarInterface.Presenter {

    private  FormularioInterface.View view;
    private PeopleModel modelo;

    public FormularioPresenter(FormularioInterface.View view){
        this.view = view;
        modelo=PeopleModel.getInstance();
    }


    public boolean validaNombre(Editable text) {
        String regex = "^[a-zA-Z\\s]+";

        boolean isValid = Pattern.matches(regex, text);

        return isValid;
    }


    @Override
    public void onClickImage(Context text) {
        int ReadPermission = ContextCompat.checkSelfPermission(text, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (ReadPermission != PackageManager.PERMISSION_GRANTED) {
            view.requestPermision();
        }else{
            view.selectPicture();
        }
    }

    @Override
    public void onClicksaveButton(PersonMin p) {
        if(modelo.addNew(p)== true){
            view.lanzarListado();
        }else{
            //error
        }
    }

    @Override
    public void onClickAdd() {
        view.lanzarListado();
    }

    @Override
    public void resultPermission(int result){
        if (result == PackageManager.PERMISSION_GRANTED) {
            // Permiso aceptado
            Log.d("AppCrud", "Permiso acceptado");
            //view.launchGallery;
        } else {
            // Permiso rechazado
            Log.d("AppCrud", "Permiso Denegado");

            //view.showError("Sin Permisos no puedes entrar");
        }
    }


}
