package com.pedidos.segracsa.dao;

import java.util.List;

import com.pedidos.segracsa.modelo.InventarioDetalle;

public interface IInventarioDetalleDao {

    public List<InventarioDetalle> findAll();

    public int save(InventarioDetalle invdet);

    public int updateStock(int idDet, int cant, int salida);

    public InventarioDetalle buscarIdProd(long id);

    public int updateStockInventario(int idDet, int entrada, int salida, int stock);

    public int stockActual(int idProd);
}
