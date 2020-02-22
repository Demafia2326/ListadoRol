package com.proyecto.listadorol.models;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static android.content.ContentValues.TAG;

public class PersonMin {
    private Integer id=null;
    private String player_name=null;
    private String avatar_name=null;
    private String imagen=null;
    private String fecha=null;
    private Integer edad=null;
    private String personalidad=null;
    private String raza=null;
    private Integer caos=null;



    public PersonMin() {
    }

    public PersonMin( String imagen, String avatar_name, String player_name) {
        this.player_name = player_name;
        this.avatar_name = avatar_name;
        this.imagen = imagen;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public boolean setPlayer_name(String player_name) {
        if (!player_name.isEmpty()){
            this.player_name = player_name;
            return true;
        } else {
            return false;
        }

    }

    /*HACER VALIDACIONES EN LOS TEXT*/

    public String getAvatar_name() {
        return avatar_name;
    }

    public boolean setAvatar_name(String avatar_name) {
        boolean es=false;

        if (!avatar_name.isEmpty()){

            this.avatar_name = avatar_name;
            es= true;

        } else {
            es= false;
        }
        return es;
    }

    public String getImagen() {
        return imagen;
    }

    public boolean setImagen(String imagen) {
        if (!imagen.isEmpty()) {

            this.imagen = imagen;
            return true;
        }else{
            return false;
        }
    }

    public String getFecha() {
        return fecha;
    }

    public boolean setFecha(String fecha) {
        if (fecha.isEmpty()&& validateDate(fecha)){
            return false;
        }else {
            this.fecha = fecha;
            return true;
        }
    }

    public Integer getEdad() {
        return edad;
    }

    public boolean setEdad(Integer edad) {
        if (edad.equals(0) || edad.equals(1) || edad.equals(2) || edad.equals(3) || edad.equals(4) || edad.equals(5) || edad.equals(6) || edad.equals(7)
                || edad.equals(8) || edad.equals(9) || edad.equals(10) || edad.equals(11) || edad.equals(12))
        {
            return false;

        }else {
            this.edad = edad;
            return true;
        }
    }

    public String getPersonalidad() {
        return personalidad;
    }

    public boolean setPersonalidad(String personalidad) {
        if(!personalidad.isEmpty()){

            this.personalidad = personalidad;
            return true;
        }else {
            return false;
        }


    }

    public String getRaza() {
        return raza;
    }

    public boolean setRaza(String raza) {
        if(raza.matches("humano") || raza.matches("elfo") || raza.matches("enano") || raza.matches("mediano")){
            this.raza = raza;
            return true;
        }else{
            return false;
        }


    }

    public Integer getCaos() {
        return caos;
    }

    public void setCaos(Integer caos) {
        this.caos = caos;
    }


    public static boolean validateDate(CharSequence date){
        if(!date.equals("")){
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
        return false;
    }
}
