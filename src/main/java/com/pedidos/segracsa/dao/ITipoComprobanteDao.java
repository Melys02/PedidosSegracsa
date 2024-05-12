package com.pedidos.segracsa.dao;

import java.util.List;

import com.pedidos.segracsa.modelo.*;

public interface ITipoComprobanteDao {

    public List<TipoComprobante> findAll();

    public TipoComprobante findbyId(String id);
}
