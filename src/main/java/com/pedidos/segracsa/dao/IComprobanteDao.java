package com.pedidos.segracsa.dao;

import java.util.List;

import com.pedidos.segracsa.modelo.*;

public interface IComprobanteDao {

    public int save(Comprobante obj);

    public int saveDetalle(Detallecomp obj);

    public int LastIdComp();

    public String IdNumeracion(String codTipoCom);

    public List<Comprobante> listaComprobante();

    public ReporteVenta CabeceraComprobante(int idComp);

    public List<Compra> listaDetalleCompra(int idComp);

    public boolean validarComprobante(int codigoComprobante, String codigoSeguridad);
}
