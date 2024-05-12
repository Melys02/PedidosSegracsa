package com.pedidos.segracsa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pedidos.segracsa.modelo.*;

@Repository
public class TipoComprobanteDaoImp implements ITipoComprobanteDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<TipoComprobante> findAll() {
        String sql = "select * from tipocomprobante";
        List<TipoComprobante> lista = jdbc.query(sql, BeanPropertyRowMapper.newInstance(TipoComprobante.class));
        return lista;
    }

    @Override
    public TipoComprobante findbyId(String id) {
        String sql = "select * from tipocomprobante where codigo_tipocom=?";
        TipoComprobante cli = jdbc.queryForObject(sql, new Object[]{id}, BeanPropertyRowMapper.newInstance(TipoComprobante.class));
        return cli;
    }
}
