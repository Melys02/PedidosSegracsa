package com.pedidos.segracsa.dao;

import java.util.List;

import com.pedidos.segracsa.modelo.Cliente;

public interface IClienteDao {
    //LISTAR TODOS LOS REGISTROS

    public List<Cliente> findAll();
    //GUARDAR REGISTROS

    public int save(Cliente cli);
    //BUSCAR REGISTROS

    public Cliente findById(long id);
    //BORRAR REGISTRO

    public int delete(long id);
    //EDITAR

    public int update(Cliente cli);

}
