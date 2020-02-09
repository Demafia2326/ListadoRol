package com.proyecto.listadorol.Presenters;

import com.proyecto.listadorol.interfaces.BuscarInterface;
import com.proyecto.listadorol.models.PeopleModel;

public class BuscarPresenter implements BuscarInterface.Presenter{

    private  BuscarInterface.View view;
    private PeopleModel modelo;

    public BuscarPresenter(BuscarInterface.View view){
        this.view = view;
        modelo=PeopleModel.getInstance();
    }





    @Override
    public void onClickAdd() {
        view.lanzarListado();
    }
}
