package com.pedidos.segracsa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pedidos.segracsa.modelo.Inventario;

@Repository
public class InventarioDaoImp implements IInventarioDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<Inventario> findAll() {
        String sql = " select i.cod_inventario,p.nombrePro,i.stock from inventario i inner join producto p on i.id_producto=p.id_producto";
        List<Inventario> lista = jdbc.query(sql, BeanPropertyRowMapper.newInstance(Inventario.class));
        return lista;
    }
}
