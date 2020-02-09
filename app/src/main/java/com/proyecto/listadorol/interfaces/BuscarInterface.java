package com.proyecto.listadorol.interfaces;

import android.content.Context;

import com.proyecto.listadorol.models.PersonMin;

public interface BuscarInterface {

    public interface View{

        void lanzarListado();
    }

    public interface Presenter{

        void onClickAdd();

    }
}
