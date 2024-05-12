package com.pedidos.segracsa.dao;

import java.util.List;

import com.pedidos.segracsa.modelo.Producto;

public interface IProductoDao {

    //LISTAR TODOS LOS REGISTROS
    public List<Producto> findAll();

    //GUARDAR REGISTROS
    public int save(Producto prod);

    //BUSCAR 
    public Producto findById(long id);

    //BORRAR
    public int delete(long id);

    //EDITAR
    public int updateP(Producto prod);

    public List<Producto> filter(String categoria);

    public List<Producto> filterAll(String termino);

    public List<Producto> findAllOrderBy();

    public Producto buscarPorCodigoComercial(String codigoComercial);
}
