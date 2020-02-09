package com.proyecto.listadorol.interfaces;

import android.content.Context;

import com.proyecto.listadorol.models.PersonMin;

import java.util.ArrayList;

public interface FormularioInterface {
    public interface View{
        void requestPermision();
        void selectPicture();
        void lanzarListado();
    }

    public interface Presenter{
        void onClickImage(Context text);
        void onClicksaveButton(PersonMin person);
        void onClickAdd();
        void resultPermission(int result);
    }





}
