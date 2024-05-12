package com.pedidos.segracsa.modelo;

public class Producto {
    //ATRIBUTOS

    private int id_producto;
    private String codigoPro;
    private String nombrePro;
    private double precioPro;
    private int id_cat;
    private String nombreCat;

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getCodigoPro() {
        return codigoPro;
    }

    public void setCodigoPro(String codigoPro) {
        this.codigoPro = codigoPro;
    }

    public String getNombrePro() {
        return nombrePro;
    }

    public void setNombrePro(String nombrePro) {
        this.nombrePro = nombrePro;
    }

    public double getPrecioPro() {
        return precioPro;
    }

    public void setPrecioPro(double precioPro) {
        this.precioPro = precioPro;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getNombreCat() {
        return nombreCat;
    }

    public void setNombreCat(String nombreCat) {
        this.nombreCat = nombreCat;
    }

    @Override
    public String toString() {
        return "Producto [id_producto=" + id_producto + ", codigoPro=" + codigoPro + ", nombrePro=" + nombrePro
                + ", precioPro=" + precioPro + ", id_cat=" + id_cat + ", nombreCat=" + nombreCat + "]";
    }

}
