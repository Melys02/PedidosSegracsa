package com.pedidos.segracsa.dao;

import java.util.List;

import com.pedidos.segracsa.modelo.Inventario;

public interface IInventarioDao {

    public List<Inventario> findAll();

}
