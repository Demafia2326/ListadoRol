package com.proyecto.listadorol.Presenters;

import com.proyecto.listadorol.interfaces.ListadoInterface;

public class ListadoPresenter implements ListadoInterface.Presenter{

    private  ListadoInterface.View view;

    public ListadoPresenter(ListadoInterface.View view){
        this.view = view;
    }

    @Override
    public void onClickAdd(){
        view.lanzarFormulario();
    }

}
