package com.proyecto.listadorol.interfaces;

import com.proyecto.listadorol.models.PersonMin;

import java.util.ArrayList;

public interface ListadoInterface {
    public interface View{
        void lanzarFormulario(int id);

    }

    public interface Presenter{
        void onClickAdd();
        ArrayList<PersonMin> getAllPerson();
        void onClicRecyclerView(int id);

        ArrayList<PersonMin> BuscarPersona(String nombre, String raza, String fecha);
    }


}
