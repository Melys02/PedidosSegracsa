package com.pedidos.segracsa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pedidos.segracsa.modelo.Categoria;

@Repository
public class CategoriaDaoImp implements ICategoriaDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<Categoria> findAll() {
        String sql = "select * from categoria";
        List<Categoria> lista = jdbc.query(sql, BeanPropertyRowMapper.newInstance(Categoria.class));
        return lista;
    }

}
