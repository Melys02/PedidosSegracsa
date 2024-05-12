package com.pedidos.segracsa.modelo;

public class Detallecomp {

    private int id_producto;
    private int codigo_com;
    private int cantidadPro;
    private double subTotal;

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCodigo_com() {
        return codigo_com;
    }

    public void setCodigo_com(int codigo_com) {
        this.codigo_com = codigo_com;
    }

    public int getCantidadPro() {
        return cantidadPro;
    }

    public void setCantidadPro(int cantidadPro) {
        this.cantidadPro = cantidadPro;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

}
