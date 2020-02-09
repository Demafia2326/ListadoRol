package com.proyecto.listadorol.models;

import java.util.Date;

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

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    /*HACER VALIDACIONES EN LOS TEXT*/

    public String getAvatar_name() {
        return avatar_name;
    }

    public void setAvatar_name(String avatar_name) {
        this.avatar_name = avatar_name;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getPersonalidad() {
        return personalidad;
    }

    public void setPersonalidad(String personalidad) {
        this.personalidad = personalidad;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Integer getCaos() {
        return caos;
    }

    public void setCaos(Integer caos) {
        this.caos = caos;
    }
}
