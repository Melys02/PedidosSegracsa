package com.pedidos.segracsa.modelo;

public class Inventario {

    private int cod_inventario;
    private int id_producto;
    private int stock;
    private String nombrePro;

    public Inventario() {

    }

    public Inventario(int cod_inventario, int id_producto, int stock, String nombrePro) {
        super();
        this.cod_inventario = cod_inventario;
        this.id_producto = id_producto;
        this.stock = stock;
        this.nombrePro = nombrePro;
    }

    public int getCod_inventario() {
        return cod_inventario;
    }

    public void setCod_inventario(int cod_inventario) {
        this.cod_inventario = cod_inventario;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNombrePro() {
        return nombrePro;
    }

    public void setNombrePro(String nombrePro) {
        this.nombrePro = nombrePro;
    }

}
