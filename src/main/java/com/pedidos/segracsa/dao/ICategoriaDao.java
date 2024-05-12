package com.pedidos.segracsa.dao;

import java.util.List;

import com.pedidos.segracsa.modelo.Categoria;

public interface ICategoriaDao {

    public List<Categoria> findAll();

}
