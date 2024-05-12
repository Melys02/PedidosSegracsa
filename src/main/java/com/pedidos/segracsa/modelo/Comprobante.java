package com.pedidos.segracsa.modelo;

public class Comprobante {

    private int codigo_com;
    private String codigo_tipocom;
    private String fechaCom;
    private int codigo_cli;
    private double subTotal;
    private double igv;
    private double preciototal;
    private String serieCom;
    private String numeracionCom;
    private String codigo_seguridad;

    private int numeracionmasuno;

    public int getCodigo_com() {
        return codigo_com;
    }

    public void setCodigo_com(int codigo_com) {
        this.codigo_com = codigo_com;
    }

    public String getCodigo_tipocom() {
        return codigo_tipocom;
    }

    public void setCodigo_tipocom(String codigo_tipocom) {
        this.codigo_tipocom = codigo_tipocom;
    }

    public String getFechaCom() {
        return fechaCom;
    }

    public void setFechaCom(String fechaCom) {
        this.fechaCom = fechaCom;
    }

    public int getCodigo_cli() {
        return codigo_cli;
    }

    public void setCodigo_cli(int codigo_cli) {
        this.codigo_cli = codigo_cli;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getPreciototal() {
        return preciototal;
    }

    public void setPreciototal(double preciototal) {
        this.preciototal = preciototal;
    }

    public String getSerieCom() {
        return serieCom;
    }

    public void setSerieCom(String serieCom) {
        this.serieCom = serieCom;
    }

    public String getNumeracionCom() {
        return numeracionCom;
    }

    public void setNumeracionCom(String numeracionCom) {
        this.numeracionCom = numeracionCom;
    }

    @Override
    public String toString() {
        return "Comprobante [codigo_com=" + codigo_com + ", codigo_tipocom=" + codigo_tipocom + ", fechaCom=" + fechaCom
                + ", codigo_cli=" + codigo_cli + ", subTotal=" + subTotal + ", igv=" + igv + ", preciototal="
                + preciototal + ", serieCom=" + serieCom + ", numeracionCom=" + numeracionCom + ", numeracionmasuno="
                + numeracionmasuno + "]";
    }

    public int getNumeracionmasuno() {

        numeracionmasuno = Integer.parseInt(numeracionCom) + 1;

        return numeracionmasuno;
    }

    public void setNumeracionmasuno(int numeracionmasuno) {
        this.numeracionmasuno = numeracionmasuno;
    }

    public String getCodigo_seguridad() {
        return codigo_seguridad;
    }

    public void setCodigo_seguridad(String codigo_seguridad) {
        this.codigo_seguridad = codigo_seguridad;
    }

    
}
