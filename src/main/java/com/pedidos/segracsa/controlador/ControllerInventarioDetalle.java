package com.pedidos.segracsa.controlador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pedidos.segracsa.dao.*;
import com.pedidos.segracsa.modelo.*;

@Controller
public class ControllerInventarioDetalle {

    @Autowired
    private InventarioDetalleDaoImp dao;

    @Autowired
    private ProductoDaoImp daoPro;

    @GetMapping("/listarinventariodetalle")
    public String listar(Model modelo) {

        List<InventarioDetalle> lista = dao.findAll();
        modelo.addAttribute("listainventariodetalles", lista);
        modelo.addAttribute("listaproductos", ListarProductos());
        return "listadoinventariodetalle";
    }

    //este me abre el formulario y me prepara la lista de productos
    @GetMapping("/nuevoinventariodetalle")
    public String nuevo(Model modelo) {

        InventarioDetalle invdet = new InventarioDetalle();

        modelo.addAttribute("inventariodetalle", invdet);
        modelo.addAttribute("producto", daoPro.findAll());

        return "guardarinventariodetalle";
    }

    //este cuando ya tiene los valores que asigné en el formulario listo para guardar
    @PostMapping("/guardarinventariodetalle")
    public String guardar(@ModelAttribute("inventariodetalle") InventarioDetalle invdet,
            @RequestParam("id_producto") int id, RedirectAttributes attributes) {

        try {
            InventarioDetalle detalle = dao.buscarIdProd(id);
            int stockSalida = 0, stockEntrada = 0;
            int g = 0;
            if (invdet.getTipoMovimiento().equals("1")) {
                stockSalida = detalle.getStock() - invdet.getCantidad();
                int salidaTotal = detalle.getSalida() + invdet.getCantidad();
                g = dao.updateStockInventario(detalle.getId_producto(), detalle.getEntrada(), salidaTotal, stockSalida);
            } else {
                stockEntrada = detalle.getStock() + invdet.getCantidad();
                int entradaTotal = detalle.getEntrada() + invdet.getCantidad();
                g = dao.updateStockInventario(detalle.getId_producto(), entradaTotal, detalle.getSalida(), stockEntrada);
            }

            Producto objProd = daoPro.findById(detalle.getId_producto());
            int stock = dao.stockActual(detalle.getId_producto());

            if (stock >= 1 && stock <= 5) {
                attributes.addFlashAttribute("error", "Quedan en stock " + stock + " del productos de:" + objProd.getNombrePro());
            } else if (stock <= 0) {
                attributes.addFlashAttribute("error", "Stock insuficiente para el productos " + objProd.getNombrePro());
            }

            System.out.println("actualiza bien");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("actualiza mal");
        }
        return "redirect:/listarinventariodetalle";

    }

    public List<String> ListarProductos() {
        List<String> listProducto = new ArrayList<>();
        boolean encontro;

        List<InventarioDetalle> lista = dao.findAll();

        for (InventarioDetalle invdet : lista) {
            String pro = invdet.getNombrePro();
            encontro = false;
            for (String x : listProducto) {
                if (pro.equalsIgnoreCase(x)) {
                    encontro = true;
                    break;
                }
            }

            if (!encontro) {
                listProducto.add(pro);
            }
        }

        Collections.sort(listProducto);

        return listProducto;
    }

    /*public int obtenerNuevoStock(InventarioDetalle invdet) {
		
		//int iddelporductoaingresar=invdet.getCodigo_invdet();
		int stockInicial=invdet.getStockInicial();
		int cantidadingresada=invdet.getEntrada();
		int stockActual=stockInicial+cantidadingresada;
		
		
		return stockActual;
	}
	
	public void asignarStockFinalaStockInicial(List<InventarioDetalle>listainventariodetalle) {
		
		 for(InventarioDetalle i: listainventariodetalle) {
			 i.setStockInicial(i.getStockActual());			 
		 }
	}*/
}
