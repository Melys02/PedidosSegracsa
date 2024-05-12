package com.pedidos.segracsa.dao;

import com.pedidos.segracsa.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDaoIm implements IUsuarioDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Usuario login(String correo, String password) {
        String sql = "SELECT * FROM Usuario WHERE correo=? AND password=?";
        try {
            Usuario obj = jdbc.queryForObject(sql, new Object[]{correo, password},
                    BeanPropertyRowMapper.newInstance(Usuario.class));
            return obj;
        } catch (Exception e) {
            return null; 
        }
    }

}
