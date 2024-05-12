package com.pedidos.segracsa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.pedidos.segracsa.modelo.*;

@Repository
public class InventarioDetalleDaoImp implements IInventarioDetalleDao {

    @Autowired //el auto wired y el jdbc son demasiado importantes
    private JdbcTemplate jdbc;

    @Override
    public List<InventarioDetalle> findAll() {
        String sql = "select i.idInventario, i.fechaMov,p.nombrePro,i.entrada,i.salida,i.stock, i.precio from inventariodetalle i inner join producto p on i.id_producto=p.id_producto";
        List<InventarioDetalle> lista = jdbc.query(sql, BeanPropertyRowMapper.newInstance(InventarioDetalle.class));
        return lista;
    }

    @Override
    public int save(InventarioDetalle invdet) {

        String sql = "insert into inventariodetalle (idInventario,fechaMov,id_producto,tipoMovimiento,observacion,entrada,salida, stock,precio) values (?,?,?,?,?,?,?,?,?)";
        return jdbc.update(sql, invdet.getIdInventario(), invdet.getFechaMov(), invdet.getId_producto(), invdet.getTipoMovimiento(), invdet.getObservacion(), invdet.getEntrada(),
                invdet.getSalida(), invdet.getStock(), invdet.getPrecio());
    }

    @Override
    public InventarioDetalle buscarIdProd(long id) {
        String sql = "select * from inventariodetalle i inner join producto p on i.id_producto=p.id_producto where i.id_producto=? ";
        InventarioDetalle prod = jdbc.queryForObject(sql, new Object[]{id}, BeanPropertyRowMapper.newInstance(InventarioDetalle.class));
        System.out.println("Busca iD: " + prod);
        return prod;
    }

    @Override
    public int updateStockInventario(int idDet, int entrada, int salida, int stock) {
        String sql = "update inventariodetalle set entrada=" + entrada + ", salida=" + salida + ", stock=" + stock + " where id_producto=" + idDet + "";
        return jdbc.update(sql);
    }

    @Override
    public int updateStock(int idDet, int cant, int salida) {
        String sql = "update inventariodetalle set stock=" + cant + ", salida =" + salida + " where id_producto=" + idDet + "";
        return jdbc.update(sql);
    }

    @Override
    public int stockActual(int idProd) {
        String sql = "select stock from inventariodetalle where id_producto = ?";
        return this.jdbc.queryForObject(sql, new Object[]{idProd}, int.class);
    }

}
