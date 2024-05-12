package com.pedidos.segracsa.modelo;

import java.math.BigDecimal;

public class InventarioDetalle {

    private int idInventario;
    private String fechaMov;
    private int id_producto;
    private String tipoMovimiento;
    private String observacion;
    private int entrada;
    private int salida;
    private int stock;
    private BigDecimal precio;
    private String nombrePro;
    private int cantidad;

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public String getFechaMov() {
        return fechaMov;
    }

    public void setFechaMov(String fechaMov) {
        this.fechaMov = fechaMov;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getEntrada() {
        return entrada;
    }

    public void setEntrada(int entrada) {
        this.entrada = entrada;
    }

    public int getSalida() {
        return salida;
    }

    public void setSalida(int salida) {
        this.salida = salida;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getNombrePro() {
        return nombrePro;
    }

    public void setNombrePro(String nombrePro) {
        this.nombrePro = nombrePro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "InventarioDetalle [idInventario=" + idInventario + ", fechaMov=" + fechaMov + ", id_producto="
                + id_producto + ", tipoMovimiento=" + tipoMovimiento + ", observacion=" + observacion + ", entrada="
                + entrada + ", salida=" + salida + ", stock=" + stock + ", precio=" + precio + ", nombrePro="
                + nombrePro + ", cantidad=" + cantidad + "]";
    }

}
