package com.proyecto.listadorol.Presenters;

import com.proyecto.listadorol.interfaces.ListadoInterface;
import com.proyecto.listadorol.models.PeopleModel;
import com.proyecto.listadorol.models.PersonMin;

import java.util.ArrayList;

public class ListadoPresenter implements ListadoInterface.Presenter{

    private  ListadoInterface.View view;

    private PeopleModel person;

    public ListadoPresenter(ListadoInterface.View view){
        this.view = view;
        person= PeopleModel.getInstance();
    }

    public void onClickAdd(){

        view.lanzarFormulario(-1);
    }

    @Override
    public ArrayList<PersonMin> getAllPerson(){
        return person.getAllPerson();
    }

    @Override
    public void onClicRecyclerView(int id){
        view.lanzarFormulario(id);
    }


    public ArrayList<PersonMin> BuscarPersona(String nombre , String raza, String fecha){
        person = PeopleModel.getInstance();
        return  person.BuscarPersona(nombre, raza, fecha);
    }



}
