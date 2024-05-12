package com.pedidos.segracsa.modelo;

public class Compra extends Producto {

    private int cantidad;

    public double Total() {
        return getCantidad() * getPrecioPro();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
