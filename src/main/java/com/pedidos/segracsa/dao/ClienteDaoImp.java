package com.pedidos.segracsa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.pedidos.segracsa.modelo.Cliente;

@Repository
public class ClienteDaoImp implements IClienteDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<Cliente> findAll() {
        String sql = "select * from cliente order by 1 desc";
        List<Cliente> lista = jdbc.query(sql, BeanPropertyRowMapper.newInstance(Cliente.class));
        return lista;
    }

    @Override
    public int save(Cliente cli) {
        String sql = "insert into cliente(nombreCli,apellidoPCli,apellidoMCli)values(?,?,?)";
        return jdbc.update(sql, cli.getNombreCli(), cli.getApellidoPCli(), cli.getApellidoMCli());
    }

    @Override
    public Cliente findById(long id) {
        String sql = "select * from cliente where codigo_cli=?";
        Cliente cli = jdbc.queryForObject(sql, new Object[]{id}, BeanPropertyRowMapper.newInstance(Cliente.class));
        return cli;
    }

    @Override
    public int delete(long id) {
        String sql = "delete from cliente where codigo_cli=?";
        return jdbc.update(sql, id);
    }

    @Override
    public int update(Cliente cli) {
        String sql = "update cliente set nombreCli=?,apellidoPCli=?,apellidoMCli=? where codigo_cli=?";
        return jdbc.update(sql, cli.getNombreCli(), cli.getApellidoPCli(), cli.getApellidoMCli(), cli.getCodigo_cli());
    }

}
