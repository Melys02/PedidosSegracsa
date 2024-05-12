package com.pedidos.segracsa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pedidos.segracsa.modelo.Producto;

@Repository
public class ProductoDaoImp implements IProductoDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<Producto> findAll() {
        String sql = "select p.id_producto, p.codigoPro, p.nombrePro, p.precioPro, c.nombreCat from producto p inner join categoria c on p.id_cat = c.id_cat order by 1 desc";
        List<Producto> lista = jdbc.query(sql, BeanPropertyRowMapper.newInstance(Producto.class));
        return lista;
    }

    @Override
    public int save(Producto prod) {
        String sql = "insert into producto(codigoPro,nombrePro,precioPro,id_cat) values(?,?,?,?)";
        return jdbc.update(sql, prod.getCodigoPro(), prod.getNombrePro(), prod.getPrecioPro(), prod.getId_cat());
    }

    @Override
    public Producto findById(long id) {
        String sql = "select * from producto where id_producto=?";
        Producto prod = jdbc.queryForObject(sql, new Object[]{id}, BeanPropertyRowMapper.newInstance(Producto.class));
        return prod;
    }

    @Override
    public int delete(long id) {
        String sql = "delete from producto where id_producto=?";
        return jdbc.update(sql, id);
    }

    @Override
    public int updateP(Producto prod) {
        String sql = "update producto set codigoPro=?, nombrePro=?, precioPro=?, id_cat=? where id_producto=?";
        return jdbc.update(sql, prod.getCodigoPro(), prod.getNombrePro(), prod.getPrecioPro(), prod.getId_cat(), prod.getId_producto());
    }

    @Override
    public List<Producto> filter(String categoria) {
        String sql = "select p.id_producto, p.codigoPro, p.nombrePro, p.precioPro, "
                + "c.nombreCat from producto p "
                + "inner join categoria c on p.id_cat = c.id_cat"
                + " where c.nombreCat like '%" + categoria + "%'";
        List<Producto> lista = jdbc.query(sql, BeanPropertyRowMapper.newInstance(Producto.class));
        return lista;
    }

    @Override
    public List<Producto> filterAll(String termino) {
        String sql = "select p.id_producto, p.codigoPro, p.nombrePro, p.precioPro,"
                + "c.nombreCat from producto p  "
                + "inner join categoria c on p.id_cat = c.id_cat"
                + " where c.nombreCat like '%" + termino + "%' or "
                + "p.codigoPro like '%" + termino + "%' or "
                + "p.nombrePro like '%" + termino + "%'";
        List<Producto> lista = jdbc.query(sql, BeanPropertyRowMapper.newInstance(Producto.class));
        return lista;
    }

    @Override
    public List<Producto> findAllOrderBy() {
        String sql = "select p.id_producto, p.codigoPro, p.nombrePro, p.precioPro, c.nombreCat"
                + "  from producto p inner join categoria c on p.id_cat = c.id_cat"
                + " order by p.nombrePro asc";
        List<Producto> lista = jdbc.query(sql, BeanPropertyRowMapper.newInstance(Producto.class));
        return lista;
    }

    @Override
    public Producto buscarPorCodigoComercial(String codigoComercial) {
        String sql = "select * from producto where 	codigoPro=?";
        Producto prod = jdbc.queryForObject(sql, new Object[]{codigoComercial}, BeanPropertyRowMapper.newInstance(Producto.class));
        return prod;
    }
}
