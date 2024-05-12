package com.pedidos.segracsa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pedidos.segracsa.modelo.*;

@Repository
public class ComprobanteDaoIm implements IComprobanteDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public int save(Comprobante obj) {
        String sql = "insert into comprobante(codigo_tipocom,fechaCom,codigo_cli,"
                + "preciototal,serieCom,numeracionCom,subTotal,igv,codigo_seguridad) values(?,?,?,?,?,?,?,?,?)";
        return jdbc.update(sql, obj.getCodigo_tipocom(), obj.getFechaCom(), obj.getCodigo_cli(),
                obj.getPreciototal(), obj.getSerieCom(), obj.getNumeracionCom(), obj.getSubTotal(), obj.getIgv(), obj.getCodigo_seguridad());

    }

    @Override
    public int saveDetalle(Detallecomp obj) {
        String sql = "insert into detallecomp(id_producto , codigo_com , cantidadPro , subtotal) values(?,?,?,?)";
        return jdbc.update(sql, obj.getId_producto(), obj.getCodigo_com(), obj.getCantidadPro(), obj.getSubTotal());
    }

    @Override
    public int LastIdComp() {
        String sql = "select max(codigo_com) from comprobante";
        return jdbc.queryForObject(sql, Integer.class);
    }

    @Override
    public String IdNumeracion(String codTipoCom) {
        String sql = "select lpad(ifnull(max(numeracionCom),0)+ 1 , 3 , 0) "
                + " from comprobante where codigo_tipoCom = ?";
        return jdbc.queryForObject(sql, String.class, codTipoCom);
    }

    @Override
    public List<Comprobante> listaComprobante() {
        String sql = "select * from comprobante order by 2,3 asc	";
        List<Comprobante> lst = jdbc.query(sql, BeanPropertyRowMapper.newInstance(Comprobante.class));
        return lst;
    }

    @Override
    public ReporteVenta CabeceraComprobante(int idComp) {
        String sql = "select codigo_com , codigo_tipocom ,nombrecli ,apellidoPCli , apellidoMCli,   fechacom , subtotal , igv , preciototal, codigo_seguridad  \n"
                + " from comprobante c inner join cliente cli \n"
                + " on c.codigo_cli = cli.codigo_cli \n"
                + " where codigo_com = ?";
        ReporteVenta prod = jdbc.queryForObject(sql, new Object[]{idComp}, BeanPropertyRowMapper.newInstance(ReporteVenta.class));
        return prod;
    }

    @Override
    public List<Compra> listaDetalleCompra(int idComp) {
        String sql = "select nombrePro , precioPro, cantidadPro as 'cantidad' , subTotal \n"
                + " from detallecomp d inner join producto p \n"
                + " on p.id_producto = d.id_producto \n"
                + " where codigo_com = ?";
        List<Compra> lst = jdbc.query(sql, BeanPropertyRowMapper.newInstance(Compra.class), new Object[]{idComp});
        return lst;
    }

    @Override
    public boolean validarComprobante(int codigoComprobante, String codigoSeguridad) {
        String sql = "SELECT COUNT(*) FROM comprobante WHERE codigo_com = ? AND codigo_seguridad = ?";
        int count = jdbc.queryForObject(sql, new Object[]{codigoComprobante, codigoSeguridad}, Integer.class);
        return count > 0;
    }
}
