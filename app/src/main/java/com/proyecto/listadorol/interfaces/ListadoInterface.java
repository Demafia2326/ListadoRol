package com.proyecto.listadorol.interfaces;

public interface ListadoInterface {
    public interface View{
        void lanzarFormulario();
    }

    public interface Presenter{
        public static void onClickAdd(){};
    }
}
