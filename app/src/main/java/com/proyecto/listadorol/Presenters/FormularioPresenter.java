package com.proyecto.listadorol.Presenters;

import android.text.Editable;

import com.proyecto.listadorol.interfaces.ListadoInterface;

import java.util.regex.Pattern;

public class FormularioPresenter {

    private  ListadoInterface.View view;




    public boolean validaNombre(Editable text) {
        String regex = "^[a-zA-Z\\s]+";

        boolean isValid = Pattern.matches(regex, text);

        return isValid;
    }


}
