package com.pedidos.segracsa.modelo;

public class TipoComprobante {

    String codigo_tipocom;
    String nombreCom;
    String valorComp;

    public String getCodigo_tipocom() {
        return codigo_tipocom;
    }

    public void setCodigo_tipocom(String codigo_tipocom) {
        this.codigo_tipocom = codigo_tipocom;
    }

    public String getNombreCom() {
        return nombreCom;
    }

    public void setNombreCom(String nombreCom) {
        this.nombreCom = nombreCom;
    }

    public String getValorComp() {
        return valorComp;
    }

    public void setValorComp(String valorComp) {
        this.valorComp = valorComp;
    }

    @Override
    public String toString() {
        return "TipoComprobante [codigo_tipocom=" + codigo_tipocom + ", nombreCom=" + nombreCom + ", valorComp="
                + valorComp + "]";
    }

}
