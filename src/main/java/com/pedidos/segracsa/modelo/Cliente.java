package com.pedidos.segracsa.modelo;

public class Cliente {
    //ATRIBUTOS

    private int codigo_cli;
    private String nombreCli;
    private String apellidoPCli;
    private String apellidoMCli;

    //CONSTRUCTORES
    public Cliente() {

    }

    public Cliente(int codigo_cli, String nombreCli, String apellidoPCli, String apellidoMCli) {
        super();
        this.codigo_cli = codigo_cli;
        this.nombreCli = nombreCli;
        this.apellidoPCli = apellidoPCli;
        this.apellidoMCli = apellidoMCli;
    }

    public String nomCompletos() {
        return nombreCli + " , " + apellidoPCli + " " + apellidoMCli;
    }

    //METODOS GET Y SET
    public int getCodigo_cli() {
        return codigo_cli;
    }

    public void setCodigo_cli(int codigo_cli) {
        this.codigo_cli = codigo_cli;
    }

    public String getNombreCli() {
        return nombreCli;
    }

    public void setNombreCli(String nombreCli) {
        this.nombreCli = nombreCli;
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

    @Override
    public String toString() {
        return "Cliente [codigo_cli=" + codigo_cli + ", nombreCli=" + nombreCli + ", apellidoPCli=" + apellidoPCli
                + ", apellidoMCli=" + apellidoMCli + "]";
    }

}
