package com.pedidos.segracsa.controlador;

import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pedidos.segracsa.dao.*;
import com.pedidos.segracsa.modelo.*;

@Controller
public class ControllerProducto {

    @Autowired
    private ProductoDaoImp dao;

    @Autowired
    private CategoriaDaoImp daoCat;

    @Autowired
    private InventarioDetalleDaoImp inventarioDao;

    @GetMapping("/listarP")
    public String listar(Model modelo) {
        List<Producto> lista = dao.findAll();

        modelo.addAttribute("categoria", "");

        modelo.addAttribute("search", "");
        modelo.addAttribute("listaproductos", lista);
        modelo.addAttribute("listacategorias", ListarCategorias());
        return "listadoproductos";
    }

    @GetMapping("/nuevoP")
    public String nuevo(Model modelo) {
        Producto prod = new Producto();
        modelo.addAttribute("producto", prod);
        modelo.addAttribute("categoria", daoCat.findAll());
        return "guardarproducto";
    }

    @PostMapping("/listarP")
    public String ConsultarFiltro(Model modelo, @RequestParam("categoria") String categoria,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "accion", required = false) String accion) {

        List<Producto> lista;

        // Select
        if (accion == null || accion.equals("1")) {
            lista = dao.filter(categoria);
            search = "";
        } else {
            // Input
            categoria = "";
            lista = dao.filterAll(search);
        }

        modelo.addAttribute("search", search);
        modelo.addAttribute("categoria", categoria);
        modelo.addAttribute("listaproductos", lista);
        modelo.addAttribute("listacategorias", ListarCategorias());
        return "listadoproductos";
    }

    @PostMapping("/guardarP")
    public String guardar(@ModelAttribute("producto") Producto prod) {
        //Inicializa
        InventarioDetalle inv = new InventarioDetalle();
        //Guarda Producto
        int g = dao.save(prod);

        Producto rsp = dao.buscarPorCodigoComercial(prod.getCodigoPro());

        //Guarda Inventario
        inv.setFechaMov(datetoString(new Date()));
        inv.setId_producto(rsp.getId_producto());
        inv.setTipoMovimiento("ENTRADA");
        inv.setObservacion(" ");
        inv.setEntrada(0);
        inv.setSalida(0);
        inv.setStock(0);
        inv.setPrecio(new BigDecimal(prod.getPrecioPro()));
        int i = inventarioDao.save(inv);
        System.out.println("Guarda Inventario : " + i);

        return "redirect:/listarP";
    }

    @GetMapping("/borrarP/{id}")
    public String borrar(@PathVariable("id") int id) {
        dao.delete(id);
        return "redirect:/listarP";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") int id, Model modelo) {
        Producto prod = dao.findById(id);
        modelo.addAttribute("producto", prod);
        modelo.addAttribute("categoria", daoCat.findAll());

        return "editarproducto";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("producto") Producto prod) {
        dao.updateP(prod);
        return "redirect:/listarP";
    }

    public List<String> ListarCategorias() {
        List<String> listCategoria = new ArrayList<>();
        boolean encontro;

        List<Producto> lista = dao.findAll();

        for (Producto p : lista) {
            String cat = p.getNombreCat();
            encontro = false;
            for (String x : listCategoria) {
                if (cat.equalsIgnoreCase(x)) {
                    encontro = true;
                    break;
                }
            }

            if (!encontro) {
                listCategoria.add(cat);
            }
        }

        Collections.sort(listCategoria);

        return listCategoria;
    }

    public static String datetoString(Date fecha) {
        String s = "";
        try {
            Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            s = formatter.format(fecha);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return s;
    }
}
