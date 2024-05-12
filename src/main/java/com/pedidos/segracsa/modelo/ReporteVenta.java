package com.pedidos.segracsa.modelo;

public class ReporteVenta extends Comprobante {

    private String nombrecli;
    private String apellidoPCli;
    private String apellidoMCli;

    public String nombreCompletos() {
        return nombrecli + " " + apellidoPCli + " " + apellidoMCli;
    }

    @Override
    public String toString() {
        return super.toString() + "ReporteVenta [nombrecli=" + nombrecli + ", apellidoPCli=" + apellidoPCli + ", apellidoMCli="
                + apellidoMCli + ", detalle]";
    }

    public String getNombrecli() {
        return nombrecli;
    }

    public void setNombrecli(String nombrecli) {
        this.nombrecli = nombrecli;
    }

    public String getApellidoPCli() {
        return apellidoPCli;
    }

    public void setApellidoPCli(String apellidoPCli) {
        this.apellidoPCli = apellidoPCli;
    }

    public String getApellidoMCli() {
        return apellidoMCli;
    }

    public void setApellidoMCli(String apellidoMCli) {
        this.apellidoMCli = apellidoMCli;
    }
}
