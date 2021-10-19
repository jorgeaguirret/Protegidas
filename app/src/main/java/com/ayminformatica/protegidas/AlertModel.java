package com.ayminformatica.protegidas;

public class AlertModel {
    private int id;
    private int bateria;
    private String ubicacion;
    private String mensajeAlerta, horaCreada;
    private String nombreContacto1, nombreContacto2, nombreContacto3;
    private String fonoContacto1, fonoContacto2, fonoContacto3;

    public AlertModel(int id, int bateria, String ubicacion, String mensajeAlerta, String nombreContacto1, String nombreContacto2, String nombreContacto3, String fonoContacto1, String fonoContacto2, String fonoContacto3) {
        this.id = id;
        this.bateria = bateria;
        this.ubicacion = ubicacion;
        this.mensajeAlerta = mensajeAlerta;
        this.nombreContacto1 = nombreContacto1;
        this.nombreContacto2 = nombreContacto2;
        this.nombreContacto3 = nombreContacto3;
        this.fonoContacto1 = fonoContacto1;
        this.fonoContacto2 = fonoContacto2;
        this.fonoContacto3 = fonoContacto3;
    }

    public AlertModel(int id, int bateria, String ubicacion, String mensajeAlerta,String horaCreada, String nombreContacto1, String nombreContacto2, String nombreContacto3, String fonoContacto1, String fonoContacto2, String fonoContacto3) {
        this.id = id;
        this.bateria = bateria;
        this.ubicacion = ubicacion;
        this.mensajeAlerta = mensajeAlerta;
        this.horaCreada = horaCreada;
        this.nombreContacto1 = nombreContacto1;
        this.nombreContacto2 = nombreContacto2;
        this.nombreContacto3 = nombreContacto3;
        this.fonoContacto1 = fonoContacto1;
        this.fonoContacto2 = fonoContacto2;
        this.fonoContacto3 = fonoContacto3;
    }

    @Override
    public String toString() {
        return "Alerta Enviada" +
                "\nID Alerta:  " + id +
                ", \nNivel de batería:  " + bateria + '%' +
                ", \nUbicación:  '" + ubicacion + '\'' +
                ", \nMensaje de Alerta:  '" + mensajeAlerta + '\'' +
                ", \nEnviado el:  '" + horaCreada + '\'' +
                ", \nNombre de contacto N°1:  '" + nombreContacto1 + '\'' +
                ", \nTeléfono de contacto N°1:  '" + fonoContacto1 + '\'' +
                ", \nNombre de contacto N°2:  '" + nombreContacto2 + '\'' +
                ", \nTeléfono de contacto N°2:  '" + fonoContacto2 + '\'' +
                ", \nNombre de contacto N°3:  '" + nombreContacto3 + '\'' +
                ", \nTeléfono de contacto N°3:  '" + fonoContacto3 + '\'' +
                ", \n   ";

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getMensajeAlerta() {
        return mensajeAlerta;
    }

    public void setMensajeAlerta(String mensajeAlerta) {
        this.mensajeAlerta = mensajeAlerta;
    }

    public String getNombreContacto1() {
        return nombreContacto1;
    }

    public void setNombreContacto1(String nombreContacto1) {
        this.nombreContacto1 = nombreContacto1;
    }

    public String getNombreContacto2() {
        return nombreContacto2;
    }

    public void setNombreContacto2(String nombreContacto2) {
        this.nombreContacto2 = nombreContacto2;
    }

    public String getNombreContacto3() {
        return nombreContacto3;
    }

    public void setNombreContacto3(String nombreContacto3) {
        this.nombreContacto3 = nombreContacto3;
    }

    public String getFonoContacto1() {
        return fonoContacto1;
    }

    public void setFonoContacto1(String fonoContacto1) {
        this.fonoContacto1 = fonoContacto1;
    }

    public String getFonoContacto2() {
        return fonoContacto2;
    }

    public void setFonoContacto2(String fonoContacto2) {
        this.fonoContacto2 = fonoContacto2;
    }

    public String getFonoContacto3() {
        return fonoContacto3;
    }

    public void setFonoContacto3(String fonoContacto3) {
        this.fonoContacto3 = fonoContacto3;
    }
}
